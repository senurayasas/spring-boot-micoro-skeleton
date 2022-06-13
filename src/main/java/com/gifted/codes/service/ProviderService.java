package com.gifted.codes.service;

import com.gifted.codes.model.api.provider.ProviderRequest;
import com.gifted.codes.model.api.provider.ProviderResponse;
import com.gifted.codes.model.mapping.ProviderMapper;
import com.gifted.codes.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    public Page<ProviderResponse> find(Pageable pageable) {
        return providerRepository.findAll(pageable)
                .map(providerMapper::toResponse);
    }

    public Optional<ProviderResponse> findById(Long id) {
        return providerRepository.findById(id)
                .map(providerMapper::toResponse);
    }

    public ProviderResponse update(Long id, ProviderRequest request) {
        //TODO magic
        return null;
    }

    public ProviderResponse create(ProviderRequest request) {
        //TODO magic
        return null;
    }
}
