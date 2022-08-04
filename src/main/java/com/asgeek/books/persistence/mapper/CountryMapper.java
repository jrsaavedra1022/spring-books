package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.CountryDTO;
import com.asgeek.books.persistence.entity.Country;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mappings({})
    CountryDTO toCountryDTO(Country country);
    List<CountryDTO> toCountriesDTO(List<Country> countries);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "authors", ignore = true),
            @Mapping(target = "editorials", ignore = true)
    })
    Country toCountry(CountryDTO country);

}
