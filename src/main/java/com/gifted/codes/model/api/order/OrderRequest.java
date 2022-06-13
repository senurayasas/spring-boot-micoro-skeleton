package com.gifted.codes.model.api.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderRequest {

    Long merchantId;
    String currency;
    String countryCode;
    Long value;
    Integer count;
    SalesChannel salesChannel;

}
