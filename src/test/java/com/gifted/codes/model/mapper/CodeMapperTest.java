package com.gifted.codes.model.mapper;

import com.gifted.codes.model.entity.Code;
import com.gifted.codes.model.entity.Merchant;
import com.gifted.codes.model.mapping.CodeMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CodeMapperTest {

    private static final CodeMapper MAPPER = Mappers.getMapper(CodeMapper.class);

    @Test
    void toResponse_fieldsUsed_fieldsMapped() {
        //Given
        final var merchant = merchant();
        final var codeEntity = new Code();
        codeEntity.setCode("abc-123");
        codeEntity.setPin("1234");
        codeEntity.setDenomination(50000L);
        codeEntity.setExportedAt(OffsetDateTime.now());
        codeEntity.setMerchant(merchant);

        //When
        final var codeResponse = MAPPER.toResponse(codeEntity);

        //Then
        assertThat(codeResponse).usingRecursiveComparison()
                .ignoringFields("merchantId", "updatedBy", "merchant", "revokals", "version", "denomination",
                        "codeOrder", "createdAt", "createdBy", "providerReference", "exportedAt", "id", "updatedAt")
                .isEqualTo(codeEntity);
        assertThat(codeResponse.getMerchantId()).isEqualTo(merchant.getId());
    }

    private Merchant merchant() {
        final var merchant = new Merchant();
        merchant.setId(5123L);
        return merchant;
    }
}
