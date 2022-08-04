package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.EditorialDTO;
import com.asgeek.books.persistence.entity.Editorial;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CountryMapper.class })
public interface EditorialMapper {

    @Mappings({
            @Mapping(source = "name", target = "editorial")
    })
    EditorialDTO toEditorialDTO(Editorial editorial);
    List<EditorialDTO> toEditorialsDTO(List<Editorial> editorials);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "books", ignore = true)
    })
    Editorial toEditorial(EditorialDTO editorial);
}
