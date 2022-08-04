package com.asgeek.books.domain.repository;

import com.asgeek.books.domain.dto.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryRepositoryDTO {

    List<CountryDTO> getAll();
    Optional<CountryDTO> getCountry(int countryId);
    CountryDTO save(CountryDTO country);
    void delete(int countryId);

}
