package com.gifted.codes.model.api.order;

import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@Builder
public class Code {

    String reference;
    String code;
    String pin;
    OffsetDateTime expiresAt;

}
