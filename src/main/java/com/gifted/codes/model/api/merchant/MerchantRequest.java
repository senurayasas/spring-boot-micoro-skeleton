package com.gifted.codes.model.api.merchant;

import com.gifted.codes.model.api.validation.ValidMerchantRequest;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@ValidMerchantRequest
public class MerchantRequest {

    Long providerId;
    Long externalId;
    MerchantConfiguration configuration;

    //TODO magic

}
