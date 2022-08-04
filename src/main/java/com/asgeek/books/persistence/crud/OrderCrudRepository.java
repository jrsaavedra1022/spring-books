package com.asgeek.books.persistence.crud;

import com.asgeek.books.persistence.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderCrudRepository extends CrudRepository<Order, Integer> {

    List<Order> findByClientId(int clientId);
}
