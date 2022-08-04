package com.asgeek.books.domain.service;

import com.asgeek.books.domain.config.ResponseCode;
import com.asgeek.books.domain.repository.ConfResponseCodeRespositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfResponseCodeService {

    @Autowired
    private ConfResponseCodeRespositoryDTO confResponseCodeRespositoryDTO;

    public Optional<ResponseCode> getResponseCode(String code){
        return confResponseCodeRespositoryDTO.getResponseCode(code);
    }
}
