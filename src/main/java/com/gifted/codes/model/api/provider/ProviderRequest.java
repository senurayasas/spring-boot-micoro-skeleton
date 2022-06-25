package com.gifted.codes.model.api.provider;

import com.gifted.codes.model.ProviderImplementation;
import com.gifted.codes.model.ProviderType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProviderRequest {

    ProviderType type;
    ProviderImplementation implementation;
    String billingGroup;

}
