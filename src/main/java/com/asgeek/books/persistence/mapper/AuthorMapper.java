package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.AuthorDTO;
import com.asgeek.books.persistence.entity.Author;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CountryMapper.class })
public interface AuthorMapper {

    @Mappings({})
    AuthorDTO toAuthorDTO(Author author);
    List<AuthorDTO> toAuthorsDTO(List<Author> authors);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "books", ignore = true)
    })
    Author toAuthor(AuthorDTO author);
}
