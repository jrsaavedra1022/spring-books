package com.asgeek.books.persistence.crud;

import com.asgeek.books.persistence.entity.ConfDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConfDtoCrudRepository extends CrudRepository<ConfDto, Integer> {

    List<ConfDto> findByDtoName(String dtoName);

    @Query(value = "SELECT DISTINCT d.dtoName, d.dtoClasName FROM ConfDto d")
    Optional<List<Object[]>> findDistinctDtoNames();
}
