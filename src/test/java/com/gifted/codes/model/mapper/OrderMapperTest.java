package com.gifted.codes.model.mapper;

import com.gifted.codes.model.entity.Code;
import com.gifted.codes.model.entity.Merchant;
import com.gifted.codes.model.entity.Order;
import com.gifted.codes.model.mapping.OrderMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.OffsetDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderMapperTest {

    private static final OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    @Test
    void toResponse_fieldsUsed_fieldsMapped() {
        //Given
        final var merchant = merchant();
        final var orderEntity = new Order();
        orderEntity.setId(92L);
        orderEntity.setMerchant(merchant);
        orderEntity.setAmount(5L);
        orderEntity.setCurrency("DKR");
        orderEntity.setCodes(codes());

        //When
        final var orderResponse = MAPPER.toResponse(orderEntity);

        //Then
        assertThat(orderResponse).usingRecursiveComparison()
                .ignoringFields("updatedAt", "merchantId")
                .isEqualTo(orderEntity);
        assertThat(orderResponse.getMerchantId()).isEqualTo(merchant.getId());
    }

    private List<Code> codes() {
        final var code = new Code();
        code.setId(9L);
        code.setReference("abc123-asfkaw34aglsdfg-sretjg");
        code.setPin("9512");
        code.setCode("super-secret");
        code.setExportedAt(OffsetDateTime.now());
        return List.of(code);
    }

    private Merchant merchant() {
        final var merchant = new Merchant();
        merchant.setId(31337L);
        return merchant;
    }
}
