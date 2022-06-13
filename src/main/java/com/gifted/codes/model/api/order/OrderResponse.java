package com.gifted.codes.model.api.order;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrderResponse {

    Long id;
    Long merchantId;
    Long amount;
    Long count;
    String currency;
    List<Code> codes;

}
