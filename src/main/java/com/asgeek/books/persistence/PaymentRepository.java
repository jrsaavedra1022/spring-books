package com.asgeek.books.persistence;

import com.asgeek.books.domain.dto.PaymentDTO;
import com.asgeek.books.domain.repository.PaymentRepositoryDTO;
import com.asgeek.books.persistence.crud.ClientCrudRepository;
import com.asgeek.books.persistence.crud.PaymentCrudRepository;
import com.asgeek.books.persistence.entity.Client;
import com.asgeek.books.persistence.entity.Payment;
import com.asgeek.books.persistence.entity.PaymentPK;
import com.asgeek.books.persistence.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentRepository implements PaymentRepositoryDTO {

    @Autowired
    private PaymentCrudRepository paymentCrudRepository;

    @Autowired
    private ClientCrudRepository clientCrudRepository;

    private final PaymentPK paymentPK = new PaymentPK();

    @Autowired
    private PaymentMapper mapper;

    @Override
    public List<PaymentDTO> getAll() {
        List<Payment> payments = (List<Payment>) paymentCrudRepository.findAll();
        return mapper.toPaymentsDTO(payments);
    }

    @Override
    public Optional<PaymentDTO> getPayment(String paymentId, int clientId) {
        paymentPK.setClientId(clientId);
        paymentPK.setInvoiceNumber(UUID.fromString(paymentId));

        return paymentCrudRepository.findById(paymentPK).map(payment -> mapper.toPaymentDTO(payment));
    }

    @Override
    public List<PaymentDTO> getPaymentByClientId(int clientId) {
        return mapper.toPaymentsDTO(paymentCrudRepository.findByClientId(clientId));
    }

    @Override
    public PaymentDTO save(PaymentDTO payment) {
        Payment newPayment = mapper.toPayment(payment);
        if(newPayment.getId().getInvoiceNumber()==null)
            newPayment.getId().setInvoiceNumber(UUID.randomUUID());

        Client client = clientCrudRepository.findById(newPayment.getId().getClientId()).orElse(null);
        newPayment.setClient(client);

        return mapper.toPaymentDTO(paymentCrudRepository.save(newPayment));
    }

    @Override
    public void delete(String paymentId, int clientId) {
        paymentPK.setClientId(clientId);
        paymentPK.setInvoiceNumber(UUID.fromString(paymentId));

        paymentCrudRepository.deleteById(paymentPK);
    }
}
