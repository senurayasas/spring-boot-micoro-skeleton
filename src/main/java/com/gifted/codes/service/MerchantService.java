package com.gifted.codes.service;

import com.gifted.codes.model.api.merchant.MerchantRequest;
import com.gifted.codes.model.api.merchant.MerchantResponse;
import com.gifted.codes.model.mapping.MerchantMapper;
import com.gifted.codes.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    public Optional<MerchantResponse> findById(Long id) {
        return merchantRepository.findById(id)
                .map(merchantMapper::toResponse);
    }

    public Page<MerchantResponse> find(Pageable pageable) {
        return merchantRepository.findAll(pageable)
                .map(merchantMapper::toResponse);
    }

    public MerchantResponse create(MerchantRequest request) {
        return null; //TODO magic
    }

    public MerchantResponse update(Long id, MerchantRequest request) {
        return null; //TODO magic
    }
}
