package com.gifted.codes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gifted.codes.model.api.order.Code;
import com.gifted.codes.model.api.order.OrderRequest;
import com.gifted.codes.model.api.order.OrderResponse;
import com.gifted.codes.model.api.order.SalesChannel;
import com.gifted.codes.service.OrderService;
import com.gifted.test.FixturesHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WithMockUser(username = "user")
@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private static final String BASE_PATH = "/orders";

    @Test
    void findById_serviceReturns_returns200() throws Exception {
        //Given
        given(orderService.findById(1L))
                .willReturn(Optional.of(orderResponse()));

        //When
        mockMvc.perform(get(BASE_PATH + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isOk())
                .andExpect(content().json(FixturesHelper.fixture("order/single_order.json")));
    }

    @Test
    void findById_serviceReturnsEmpty_returns404() throws Exception {
        //Given
        given(orderService.findById(anyLong()))
                .willReturn(Optional.empty());

        //When
        mockMvc.perform(get(BASE_PATH + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isNotFound());
    }

    @Test
    void create_serviceReturnsResponse_returns201() throws Exception {
        //Given
        final var request = List.of(orderRequest());

        given(orderService.create(request))
                .willReturn(List.of(orderResponse()));

        //When
        mockMvc.perform(post(BASE_PATH)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(FixturesHelper.fixture("order/order_list.json")));
    }

    private OrderRequest orderRequest() {
        return OrderRequest.builder()
                .currency("SEK")
                .count(1)
                .countryCode("SE")
                .merchantId(589L)
                .value(50000L)
                .salesChannel(SalesChannel.builder()
                        .type("B2B")
                        .name("Some name")
                        .build())
                .build();
    }

    private OrderResponse orderResponse() {
        return OrderResponse.builder()
                .id(5L)
                .currency("SEK")
                .merchantId(589L)
                .count(1L)
                .codes(List.of(Code.builder()
                        .reference("hello-goodbye")
                        .code("super-secret")
                        .pin("1234")
                        .build()))
                .build();
    }
}
