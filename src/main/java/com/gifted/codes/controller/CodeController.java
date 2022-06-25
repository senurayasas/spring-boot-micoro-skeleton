package com.gifted.codes.controller;

import com.gifted.codes.model.api.code.CodeRequest;
import com.gifted.codes.model.api.code.CodeResponse;
import com.gifted.codes.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/codes")
public class CodeController {

    private final CodeService codeService;

    @GetMapping("/{reference}")
    public CodeResponse findById(@PathVariable String reference) {
        return codeService.findByReference(reference)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CodeResponse create(@Valid @RequestBody CodeRequest request) {
        return codeService.create(request);
    }

}
