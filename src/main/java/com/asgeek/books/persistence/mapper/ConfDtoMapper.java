package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.config.Dto;
import com.asgeek.books.persistence.entity.ConfDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConfDtoMapper {

    @Mappings({})
    List<Dto> toConfDtosDTO(List<ConfDto> confDtos);
}
