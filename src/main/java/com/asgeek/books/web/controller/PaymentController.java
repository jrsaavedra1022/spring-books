package com.asgeek.books.web.controller;

import com.asgeek.books.domain.dto.PaymentDTO;
import com.asgeek.books.domain.service.PaymentService;
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
@Tag(name = "Payments", description = "payments resources")
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAll(){
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}/clients/{client_id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable("id") String paymentId, @PathVariable("client_id") int clientId){
        return paymentService.getPayment(paymentId, clientId)
                .map(paymentDTO -> new ResponseEntity<>(paymentDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/clients/{client_id}")
    public ResponseEntity<List<PaymentDTO>> getPaymentByClientId(@PathVariable("client_id") int clientId){
        return new ResponseEntity<>(paymentService.getPaymentByClientId(clientId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> save(@RequestBody PaymentDTO paymentDTO){
        try{
            List<Object> errors = UtilFieldValidation.getInstance().inputFieldValidation(paymentDTO, "PaymentDTO");
            if(!errors.isEmpty()){
                Map<String, List<Object>> response = new HashMap<>();
                response.put("errors", errors);
                throw new GenericResponseException(response, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(paymentService.save(paymentDTO), HttpStatus.CREATED);
        }catch(UtilException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}/clients/{client_id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String paymentId, @PathVariable("client_id") int clientId){
        if(paymentService.delete(paymentId, clientId))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
