package com.asgeek.books.persistence.crud;

import com.asgeek.books.persistence.entity.Payment;
import com.asgeek.books.persistence.entity.PaymentPK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentCrudRepository extends CrudRepository<Payment, PaymentPK> {

    List<Payment> findByClientId(Integer clientId);

}
