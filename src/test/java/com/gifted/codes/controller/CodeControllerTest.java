package com.gifted.codes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gifted.codes.model.api.code.CodeRequest;
import com.gifted.codes.model.api.code.CodeResponse;
import com.gifted.codes.service.CodeService;
import com.gifted.test.FixturesHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WithMockUser(username = "user")
@WebMvcTest(controllers = CodeController.class)
class CodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CodeService codeService;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private static final String BASE_PATH = "/codes";

    @Test
    void findByReference_serviceReturns_returns200() throws Exception {
        //Given
        final var reference = "b6d45960-b211-43b2-8118-b1bed9cc9028";
        given(codeService.findByReference(reference))
                .willReturn(Optional.of(codeResponse()));

        //When
        mockMvc.perform(get(BASE_PATH + "/" + reference)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(FixturesHelper.fixture("code/single_code.json")));
    }

    @Test
    void findByReference_serviceReturnsEmpty_returns404() throws Exception {
        //Given
        given(codeService.findByReference(any()))
                .willReturn(Optional.empty());

        //When
        mockMvc.perform(get(BASE_PATH + "/" + "b6d45960-b211-43b2-8118-b1bed9cc9028")
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isNotFound());
    }


    @Test
    void create_serviceReturnsResponse_returns201() throws Exception {
        //Given
        final var request = codeRequest();

        given(codeService.create(request))
                .willReturn(codeResponse());

        //When
        mockMvc.perform(post(BASE_PATH)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(FixturesHelper.fixture("code/single_code.json")));
    }

    private CodeRequest codeRequest() {
        return CodeRequest.builder()
                .merchantId(510L)
                .code("super-secret")
                .pin("1234")
                .build();
    }

    private CodeResponse codeResponse() {
        return CodeResponse.builder()
                .reference("b6d45960-b211-43b2-8118-b1bed9cc9028")
                .merchantId(510L)
                .code("super-secret")
                .pin("1234")
                .build();
    }
}
