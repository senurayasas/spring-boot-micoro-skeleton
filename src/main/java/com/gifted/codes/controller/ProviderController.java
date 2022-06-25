package com.gifted.codes.controller;

import com.gifted.codes.model.api.provider.ProviderRequest;
import com.gifted.codes.model.api.provider.ProviderResponse;
import com.gifted.codes.service.ProviderService;
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
@RequestMapping("/providers")
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping("/{id}")
    public ProviderResponse findById(@PathVariable Long id) {
        return providerService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Page<ProviderResponse> find(@ParameterObject Pageable pageable) {
        return providerService.find(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProviderResponse create(@Valid @RequestBody ProviderRequest request) {
        return providerService.create(request);
    }

    @PutMapping("/{id}")
    public ProviderResponse update(@PathVariable Long id, @Valid @RequestBody ProviderRequest request) {
        return providerService.update(id, request);
    }
}
