package com.gifted.codes.service.provider;

import com.gifted.codes.model.CodeProvider;
import com.gifted.codes.model.ProviderImplementation;
import com.gifted.codes.model.api.order.OrderRequest;
import org.springframework.stereotype.Component;

@Component
public class TilloProvider implements CodeProvider {

    @Override
    public void provide(OrderRequest request) {

    }

    @Override
    public boolean canProvide(ProviderImplementation impl) {
        return ProviderImplementation.TILLO.equals(impl);
    }
}
