package com.gifted.codes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gifted.codes.model.ProviderImplementation;
import com.gifted.codes.model.ProviderType;
import com.gifted.codes.model.api.provider.ProviderRequest;
import com.gifted.codes.model.api.provider.ProviderResponse;
import com.gifted.codes.service.ProviderService;
import com.gifted.test.FixturesHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WithMockUser(username = "user")
@WebMvcTest(controllers = ProviderController.class)
class ProviderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProviderService providerService;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private static final String BASE_PATH = "/providers";

    @Test
    void findById_serviceReturns_returns200() throws Exception {
        //Given
        given(providerService.findById(1L))
                .willReturn(Optional.of(providerResponse()));

        //When
        mockMvc.perform(get(BASE_PATH + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(FixturesHelper.fixture("provider/single_provider.json")));
    }

    @Test
    void findById_serviceReturnsEmpty_returns404() throws Exception {
        //Given
        given(providerService.findById(any()))
                .willReturn(Optional.empty());

        //When
        mockMvc.perform(get(BASE_PATH + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isNotFound());
    }

    @Test
    void find_serviceReturnsEmpty_returnsEmpty200() throws Exception {
        //Given
        given(providerService.find(any()))
                .willReturn(Page.empty());

        //When
        mockMvc.perform(get(BASE_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(FixturesHelper.fixture("empty_paged_response.json")));
    }

    @Test
    void find_serviceReturnsContent_returnsPaged200() throws Exception {
        //Given
        final var providerList = List.of(providerResponse());
        final var providerResponses = new PageImpl<>(providerList, Pageable.ofSize(1), 1);
        given(providerService.find(any()))
                .willReturn(providerResponses);

        //When
        mockMvc.perform(get(BASE_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(FixturesHelper.fixture("provider/single_paged_provider.json")));
    }

    @Test
    void create_serviceReturnsResponse_returns201() throws Exception {
        //Given
        final var request = providerRequest();
        given(providerService.create(request))
                .willReturn(providerResponse());

        //When
        mockMvc.perform(post(BASE_PATH)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(FixturesHelper.fixture("provider/single_provider.json")));
    }

    @Test
    void update_serviceReturnsResponse_returns200() throws Exception {
        //Given
        final var request = providerRequest();
        given(providerService.update(1L, request))
                .willReturn(providerResponse());

        //When
        mockMvc.perform(put(BASE_PATH + "/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(FixturesHelper.fixture("provider/single_provider.json")));
    }

    private ProviderRequest providerRequest() {
        return ProviderRequest.builder()
                .implementation(ProviderImplementation.INTERSOLVE)
                .type(ProviderType.API)
                .billingGroup("is1")
                .build();
    }

    private ProviderResponse providerResponse() {
        return ProviderResponse.builder()
                .id(1L)
                .implementation(ProviderImplementation.INTERSOLVE)
                .type(ProviderType.API)
                .billingGroup("is1")
                .build();
    }
}
