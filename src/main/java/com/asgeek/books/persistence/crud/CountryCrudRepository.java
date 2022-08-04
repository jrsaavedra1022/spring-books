package com.asgeek.books.persistence.crud;

import com.asgeek.books.persistence.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryCrudRepository extends CrudRepository<Country, Integer> {
}
