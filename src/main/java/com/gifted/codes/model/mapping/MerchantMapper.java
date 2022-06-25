package com.gifted.codes.model.mapping;

import com.gifted.codes.model.api.merchant.MerchantResponse;
import com.gifted.codes.model.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    @Mapping(target = "providerId", source = "provider.id")
    MerchantResponse toResponse(Merchant provider);

}
