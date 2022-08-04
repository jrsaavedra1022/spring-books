package com.asgeek.books.domain.service;

import com.asgeek.books.domain.dto.PaymentDTO;
import com.asgeek.books.domain.repository.PaymentRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepositoryDTO paymentRepositoryDTO;

    public List<PaymentDTO> getAll(){
        return paymentRepositoryDTO.getAll();
    }

    public Optional<PaymentDTO> getPayment(String paymentId, int clientId){
        return paymentRepositoryDTO.getPayment(paymentId, clientId);
    }

    public List<PaymentDTO> getPaymentByClientId(int clientId){
        return paymentRepositoryDTO.getPaymentByClientId(clientId);
    }

    public PaymentDTO save(PaymentDTO payment){
        return paymentRepositoryDTO.save(payment);
    }

    public boolean delete(String paymentId, int clientId){
        return getPayment(paymentId, clientId).map(paymentDTO -> {
            paymentRepositoryDTO.delete(paymentId, clientId);
            return true;
        }) .orElse(false);
    }

}
