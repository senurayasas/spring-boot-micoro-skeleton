package com.gifted.codes.service;

import com.gifted.codes.model.api.code.CodeRequest;
import com.gifted.codes.model.api.code.CodeResponse;
import com.gifted.codes.model.mapping.CodeMapper;
import com.gifted.codes.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;
    private final CodeMapper codeMapper;

    public CodeResponse create(CodeRequest request) {
        //TODO magic
        return null;
    }

    public Optional<CodeResponse> findByReference(String reference) {
        return codeRepository.findByReference(reference)
                .map(codeMapper::toResponse);
    }
}
