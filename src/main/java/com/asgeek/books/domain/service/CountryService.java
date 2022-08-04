package com.asgeek.books.domain.service;

import com.asgeek.books.domain.dto.CountryDTO;
import com.asgeek.books.domain.repository.CountryRepositoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepositoryDTO countryRepositoryDTO;

    public List<CountryDTO> getAll(){
        return countryRepositoryDTO.getAll();
    }

    public Optional<CountryDTO> getCountry(int countryId){
        return countryRepositoryDTO.getCountry(countryId);
    }

    public CountryDTO save(CountryDTO country){
        return countryRepositoryDTO.save(country);
    }

    public boolean delete(int countryId){
        return getCountry(countryId).map(countryDTO -> {
            countryRepositoryDTO.delete(countryId);
            return true;
        }).orElse(false);
    }

}
