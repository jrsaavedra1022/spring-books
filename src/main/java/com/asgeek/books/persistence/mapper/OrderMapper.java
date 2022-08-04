package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.OrderDTO;
import com.asgeek.books.persistence.entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ClientMapper.class, OrderDetailMapper.class })
public interface OrderMapper {

    @Mappings({
            @Mapping( source = "orderDetails", target = "details")
    })
    OrderDTO toOrderDTO(Order order);
    List<OrderDTO> toOrdersDTO(List<Order> orders);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "client", ignore = true)
    })
    Order toOrder(OrderDTO order);
}
