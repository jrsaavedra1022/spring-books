package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.PaymentDTO;
import com.asgeek.books.persistence.entity.Payment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ClientMapper.class })
public interface PaymentMapper {

    @Mappings({
            @Mapping(source = "id.clientId", target = "clientId"),
            @Mapping(source = "id.invoiceNumber", target = "invoiceNumber")
    })
    PaymentDTO toPaymentDTO(Payment payment);
    List<PaymentDTO> toPaymentsDTO(List<Payment> payments);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "client", ignore = true)
    })
    Payment toPayment(PaymentDTO payment);
}
