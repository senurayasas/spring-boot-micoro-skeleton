package com.gifted.codes.controller;

import com.gifted.codes.model.api.order.OrderRequest;
import com.gifted.codes.model.api.order.OrderResponse;
import com.gifted.codes.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        return orderService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderResponse> create(@Valid @RequestBody List<OrderRequest> orderRequest) {
        return orderService.create(orderRequest);
    }
}
