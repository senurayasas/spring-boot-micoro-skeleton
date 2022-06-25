package com.gifted.codes.model.api.merchant;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MerchantResponse {

    Long id;
    Long providerId;
    Long externalId;
    List<MerchantConfiguration> configurations;
}
