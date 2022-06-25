package com.gifted.codes.model.mapper;

import com.gifted.codes.model.ProviderImplementation;
import com.gifted.codes.model.ProviderType;
import com.gifted.codes.model.entity.Provider;
import com.gifted.codes.model.mapping.ProviderMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class ProviderMapperTest {

    private static final ProviderMapper MAPPER = Mappers.getMapper(ProviderMapper.class);

    @Test
    void toResponse_fieldsUsed_fieldsMapped() {
        //Given
        final var providerEntity = new Provider();
        providerEntity.setId(12L);
        providerEntity.setBillingGroup("BG5");
        providerEntity.setImplementation(ProviderImplementation.TILLO);
        providerEntity.setType(ProviderType.MANUAL);

        //When
        final var providerResponse = MAPPER.toResponse(providerEntity);

        //Then
        assertThat(providerResponse).usingRecursiveComparison()
                .isEqualTo(providerEntity);
    }
}
