package com.asgeek.books.persistence;

import com.asgeek.books.domain.config.ResponseCode;
import com.asgeek.books.domain.repository.ConfResponseCodeRespositoryDTO;
import com.asgeek.books.persistence.crud.ConfResponseCodeCrudRepository;
import com.asgeek.books.persistence.mapper.ConfResponseCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConfResponseCodeRepository implements ConfResponseCodeRespositoryDTO {

    @Autowired
    private ConfResponseCodeCrudRepository confResponseCodeCrudRepository;

    @Autowired
    private ConfResponseCodeMapper mapper;

    @Override
    public Optional<ResponseCode> getResponseCode(String code) {
        return confResponseCodeCrudRepository.findById(code).map(confResponseCode -> mapper.toConfResponseCodeDTO(confResponseCode));
    }
}
