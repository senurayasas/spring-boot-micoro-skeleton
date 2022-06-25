package com.gifted.codes.model.mapper;

import com.gifted.codes.model.entity.DenominationConfig;
import com.gifted.codes.model.entity.Merchant;
import com.gifted.codes.model.entity.MerchantConfig;
import com.gifted.codes.model.entity.Provider;
import com.gifted.codes.model.mapping.MerchantMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MerchantMapperTest {

    private static final MerchantMapper MAPPER = Mappers.getMapper(MerchantMapper.class);

    @Test
    void toResponse_fieldsUsed_fieldsMapped() {
        //Given
        final var provider = provider();
        final var merchantConfiguration = merchantConfigurationEntity();
        final var merchantEntity = new Merchant();
        merchantEntity.setId(5123L);
        merchantEntity.setProvider(provider);
        merchantEntity.setExternalId(5L);
        merchantEntity.setConfigurations(List.of(merchantConfiguration));


        //When
        final var merchantResponse = MAPPER.toResponse(merchantEntity);

        //Then
        assertThat(merchantResponse).usingRecursiveComparison()
                .ignoringFields("updatedAt", "providerId", "configuration")
                .isEqualTo(merchantEntity);

        assertThat(merchantResponse.getConfigurations()).usingRecursiveComparison()
                .isEqualTo(merchantEntity.getConfigurations());

        assertThat(merchantResponse.getProviderId()).isEqualTo(merchantEntity.getProvider().getId());
    }

    private Provider provider() {
        final var provider = new Provider();
        provider.setId(9L);
        return provider;
    }

    private MerchantConfig merchantConfigurationEntity() {
        final var merchantConfig = new MerchantConfig();
        merchantConfig.setBufferCount(5);
        merchantConfig.setCurrency("LKR");
        merchantConfig.setExternalProductId("U2512");
        merchantConfig.setDenominations(List.of(denominationConfigEntity()));
        return merchantConfig;
    }

    private DenominationConfig denominationConfigEntity() {
        final var denominationConfig = new DenominationConfig();
        denominationConfig.setDenomination(500L);
        return denominationConfig;
    }
}
