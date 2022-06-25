package com.gifted.codes.model.api.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SalesChannel {

    String type;
    String name;

}
