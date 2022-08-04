package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.dto.PaymentDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryDTO {

    List<PaymentDTO> getAll();
    Optional<PaymentDTO> getPayment(String paymentId, int clientId);
    List<PaymentDTO> getPaymentByClientId(int clientId);
    PaymentDTO save(PaymentDTO payment);
    void delete(String paymentId, int clientId);

}
