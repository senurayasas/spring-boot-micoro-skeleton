package com.gifted.codes.model;

import com.gifted.codes.model.api.order.OrderRequest;

public interface CodeProvider {

    void provide(OrderRequest request);

    boolean canProvide(ProviderImplementation impl);

}
