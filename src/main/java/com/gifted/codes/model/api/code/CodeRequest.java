package com.gifted.codes.model.api.code;

import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@Builder
public class CodeRequest {

    Long merchantId;
    String code;
    String pin;
    OffsetDateTime expiresAt;

}
