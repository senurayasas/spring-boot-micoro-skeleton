package com.gifted.codes.service;

import com.gifted.codes.CodeApplication;
import com.gifted.codes.model.CodeProvider;
import com.gifted.codes.model.api.order.OrderRequest;
import com.gifted.codes.model.api.order.OrderResponse;
import com.gifted.codes.model.entity.Merchant;
import com.gifted.codes.model.mapping.OrderMapper;
import com.gifted.codes.repository.MerchantRepository;
import com.gifted.codes.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MerchantRepository merchantRepository;
    private final OrderMapper orderMapper;
    private final List<CodeProvider> codeProviders;

    public Optional<OrderResponse> findById(long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toResponse);
    }

    public List<OrderResponse> create(@Valid List<OrderRequest> orderRequest) {


        //TODO magic
        orderRequest.forEach(this::handleOrder);


        return null;
    }

    private void handleOrder(OrderRequest orderRequest) {
        //TODO magic
        final Merchant merchant = merchantRepository.findById(orderRequest.getMerchantId())
                .orElseThrow();

        final CodeProvider provider = codeProviders.stream()
                .filter(codeProvider -> codeProvider.canProvide(merchant.getProvider().getImplementation()))
                .collect(CodeApplication.toSingleton());


        provider.provide(orderRequest);
    }
}
