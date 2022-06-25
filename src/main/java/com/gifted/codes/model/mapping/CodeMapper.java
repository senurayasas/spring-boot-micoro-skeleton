package com.gifted.codes.model.mapping;

import com.gifted.codes.model.api.code.CodeResponse;
import com.gifted.codes.model.entity.Code;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CodeMapper {

    @Mapping(target = "merchantId", source = "merchant.id")
    CodeResponse toResponse(Code input);


    /*
        String reference;
    Long merchantId;
    String code;
    String pin;
    OffsetDateTime expiresAt;
     */
}
