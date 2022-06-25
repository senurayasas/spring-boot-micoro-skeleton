package com.gifted.codes.model.api.merchant;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MerchantConfigurationDenomination {

    Long denomination;
    Long denominationMin;
    Long denominationMax;

}
