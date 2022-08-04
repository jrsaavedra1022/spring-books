package com.asgeek.books.domain.service;

import com.asgeek.books.domain.dto.OrderDTO;
import com.asgeek.books.domain.repository.OrderRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepositoryDTO orderRepositoryDTO;

    public List<OrderDTO> getAll(){
        return orderRepositoryDTO.getAll();
    }

    public Optional<OrderDTO> getOrder(int orderId){
        return orderRepositoryDTO.getOrder(orderId);
    }

    public List<OrderDTO> getByClient(int clientId){
        return orderRepositoryDTO.getByClient(clientId);
    }

    public OrderDTO save(OrderDTO order){
        return orderRepositoryDTO.save(order);
    }

    public boolean delete(int orderId){
        return getOrder(orderId).map(orderDTO -> {
            orderRepositoryDTO.delete(orderId);
            return true;
        }).orElse(false);
    }


}
