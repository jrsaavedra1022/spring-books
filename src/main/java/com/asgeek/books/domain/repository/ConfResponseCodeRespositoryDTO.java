package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.config.ResponseCode;

import java.util.Optional;

public interface ConfResponseCodeRespositoryDTO {

    Optional<ResponseCode> getResponseCode(String code);
}
