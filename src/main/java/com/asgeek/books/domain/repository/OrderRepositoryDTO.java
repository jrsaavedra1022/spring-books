package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryDTO {

    List<OrderDTO> getAll();
    Optional<OrderDTO> getOrder(int orderId);

    List<OrderDTO> getByClient(int clientId);
    OrderDTO save(OrderDTO order);
    void delete(int orderId);

}
