package com.gifted.codes.controller;

import com.gifted.codes.model.api.merchant.MerchantRequest;
import com.gifted.codes.model.api.merchant.MerchantResponse;
import com.gifted.codes.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/{id}")
    public MerchantResponse findById(@PathVariable Long id) {
        return merchantService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Page<MerchantResponse> find(@ParameterObject Pageable pageable) {
        return merchantService.find(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MerchantResponse create(@Valid @RequestBody MerchantRequest request) {
        return merchantService.create(request);
    }

    @PutMapping("/{id}")
    public MerchantResponse update(@PathVariable Long id, @Valid @RequestBody MerchantRequest request) {
        return merchantService.update(id, request);
    }

}
