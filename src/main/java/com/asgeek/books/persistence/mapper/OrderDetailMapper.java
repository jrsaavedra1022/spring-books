package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.OrderDetailDTO;
import com.asgeek.books.persistence.entity.OrderDetail;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { BookMapper.class })
public interface OrderDetailMapper {

    @Mappings({
            @Mapping(source = "id.bookId", target = "bookId"),
            @Mapping(source = "state", target = "active")
    })
    OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);
    List<OrderDetailDTO> toOrderDetailsDTO(List<OrderDetail> orderDetails);

    @InheritInverseConfiguration
    @Mappings({
       @Mapping(target = "book", ignore = true),
       @Mapping(target = "order", ignore = true),
       @Mapping(target = "id.orderId", ignore = true)
    })
    OrderDetail toOrderDetail(OrderDetailDTO orderDetail);
}
