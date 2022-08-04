package com.asgeek.books.web.controller;

import com.asgeek.books.domain.dto.OrderDTO;
import com.asgeek.books.domain.service.OrderService;
import com.asgeek.books.utils.UtilException;
import com.asgeek.books.utils.UtilFieldValidation;
import com.asgeek.books.web.exceptions.GenericResponseException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Orders", description = "orders resources")
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") int orderId) {
        return orderService.getOrder(orderId)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK)
                ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<List<OrderDTO>> getByClient(@PathVariable("id") int clientId) {
        return new ResponseEntity<>(orderService.getByClient(clientId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> save(@RequestBody OrderDTO order) {
        try{
            List<Object> errors = UtilFieldValidation.getInstance().inputFieldValidation(order, "OrderDTO");
            if(!errors.isEmpty()){
                Map<String, List<Object>> response = new HashMap<>();
                response.put("errors", errors);
                throw new GenericResponseException(response, HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
        }catch(UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
