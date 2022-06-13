package com.gifted.codes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gifted.codes.model.api.merchant.MerchantConfiguration;
import com.gifted.codes.model.api.merchant.MerchantConfigurationDenomination;
import com.gifted.codes.model.api.merchant.MerchantRequest;
import com.gifted.codes.model.api.merchant.MerchantResponse;
import com.gifted.codes.service.MerchantService;
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
@WebMvcTest(controllers = MerchantController.class)
class MerchantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MerchantService merchantService;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private static final String BASE_PATH = "/merchants";

    @Test
    void findById_serviceReturns_returns200() throws Exception {
        //Given
        given(merchantService.findById(1L))
                .willReturn(Optional.of(merchantResponse()));

        //When
        mockMvc.perform(get(BASE_PATH + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(FixturesHelper.fixture("merchant/single_merchant.json")));
    }

    @Test
    void findById_serviceReturnsEmpty_returns404() throws Exception {
        //Given
        given(merchantService.findById(any()))
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
        given(merchantService.find(any()))
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
        final var providerList = List.of(merchantResponse());
        final var merchantResponses = new PageImpl<>(providerList, Pageable.ofSize(1), 1);

        given(merchantService.find(any()))
                .willReturn(merchantResponses);

        //When
        mockMvc.perform(get(BASE_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(FixturesHelper.fixture("merchant/single_paged_merchant.json")));
    }

    @Test
    void create_serviceReturnsResponse_returns201() throws Exception {
        //Given
        final var request = merchantRequest();

        given(merchantService.create(request))
                .willReturn(merchantResponse());

        //When
        mockMvc.perform(post(BASE_PATH)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(FixturesHelper.fixture("merchant/single_merchant.json")));
    }

    @Test
    void update_serviceReturnsResponse_returns200() throws Exception {
        //Given
        final var request = merchantRequest();
        given(merchantService.update(1L, request))
                .willReturn(merchantResponse());

        //When
        mockMvc.perform(put(BASE_PATH + "/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(FixturesHelper.fixture("merchant/single_merchant.json")));
    }

    private MerchantRequest merchantRequest() {
        return MerchantRequest.builder()
                .externalId(512L)
                .providerId(1L)
                .configuration(MerchantConfiguration.builder()
                        .bufferCount(5)
                        .currency("EUR")
                        .externalProductId("U512")
                        .denominations(List.of(MerchantConfigurationDenomination.builder()
                                .denomination(50000L)
                                .build()))
                        .build())
                .build();
    }

    private MerchantResponse merchantResponse() {
        return MerchantResponse.builder()
                .id(1L)
                .providerId(1L)
                .externalId(512L)
                .configurations(List.of(MerchantConfiguration.builder()
                        .bufferCount(5)
                        .currency("EUR")
                        .externalProductId("U512")
                        .denominations(List.of(MerchantConfigurationDenomination.builder()
                                .denomination(50000L)
                                .build()))
                        .build()))
                .build();
    }
}
