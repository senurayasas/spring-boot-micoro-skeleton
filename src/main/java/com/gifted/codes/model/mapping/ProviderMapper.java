package com.gifted.codes.model.mapping;

import com.gifted.codes.model.api.provider.ProviderResponse;
import com.gifted.codes.model.entity.Provider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    ProviderResponse toResponse(Provider provider);

}
