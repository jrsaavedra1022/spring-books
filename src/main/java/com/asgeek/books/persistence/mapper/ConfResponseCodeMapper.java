package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.config.ResponseCode;
import com.asgeek.books.persistence.entity.ConfResponseCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConfResponseCodeMapper {

    @Mappings({})
    ResponseCode toConfResponseCodeDTO(ConfResponseCode confResponseCode);
}
