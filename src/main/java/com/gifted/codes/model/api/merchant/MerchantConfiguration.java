package com.gifted.codes.model.api.merchant;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MerchantConfiguration {

    String currency;
    int bufferCount;
    String externalProductId;
    List<MerchantConfigurationDenomination> denominations;

}
