package com.asgeek.books.persistence;

import com.asgeek.books.domain.dto.CountryDTO;
import com.asgeek.books.domain.repository.CountryRepositoryDTO;
import com.asgeek.books.persistence.crud.CountryCrudRepository;
import com.asgeek.books.persistence.entity.Country;
import com.asgeek.books.persistence.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepository implements CountryRepositoryDTO {

    @Autowired
    private CountryCrudRepository countryCrudRepository;

    @Autowired
    private CountryMapper mapper;

    @Override
    public List<CountryDTO> getAll() {
        List<Country> countries = (List<Country>) countryCrudRepository.findAll();
        return mapper.toCountriesDTO(countries);
    }

    @Override
    public Optional<CountryDTO> getCountry(int countryId) {
        return  countryCrudRepository.findById(countryId).map(country -> mapper.toCountryDTO(country));
    }

    @Override
    public CountryDTO save(CountryDTO country) {
        Country newCountry = mapper.toCountry(country);
        return mapper.toCountryDTO(countryCrudRepository.save(newCountry));
    }

    @Override
    public void delete(int countryId) {
        countryCrudRepository.deleteById(countryId);
    }
}
