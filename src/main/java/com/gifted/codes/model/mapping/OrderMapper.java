package com.gifted.codes.model.mapping;

import com.gifted.codes.model.api.order.OrderResponse;
import com.gifted.codes.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "merchantId", source = "merchant.id")
    OrderResponse toResponse(Order provider);

}
