package com.asgeek.books.persistence;

import com.asgeek.books.domain.dto.OrderDTO;
import com.asgeek.books.domain.repository.OrderRepositoryDTO;
import com.asgeek.books.persistence.crud.OrderCrudRepository;
import com.asgeek.books.persistence.entity.Order;
import com.asgeek.books.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository implements OrderRepositoryDTO {

    @Autowired
    private OrderCrudRepository orderCrudRepository;

    @Autowired
    private OrderMapper mapper;

    @Override
    public List<OrderDTO> getAll() {
        return mapper.toOrdersDTO((List<Order>) orderCrudRepository.findAll());
    }

    @Override
    public Optional<OrderDTO> getOrder(int orderId) {
        return orderCrudRepository.findById(orderId)
                .map(order -> mapper.toOrderDTO(order));
    }

    @Override
    public List<OrderDTO> getByClient(int clientId) {
        return mapper.toOrdersDTO(orderCrudRepository.findByClientId(clientId));
    }

    @Override
    public OrderDTO save(OrderDTO order) {
        Order newOrder = mapper.toOrder(order);
        newOrder.getOrderDetails().forEach(orderDetail -> orderDetail.setOrder(newOrder));

        return mapper.toOrderDTO(orderCrudRepository.save(newOrder));
    }

    @Override
    public void delete(int orderId) {
        orderCrudRepository.deleteById(orderId);
    }
}
