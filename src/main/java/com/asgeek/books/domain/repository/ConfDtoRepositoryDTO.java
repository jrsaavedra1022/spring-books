package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.config.Dto;

import java.util.List;

public interface ConfDtoRepositoryDTO {
    List<Dto> getListByDtoName(String dtoName);

    List<Dto> getDistinctDtoNames();
}
