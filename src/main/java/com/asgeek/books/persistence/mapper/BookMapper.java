package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.BookDTO;
import com.asgeek.books.persistence.entity.Book;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = { AuthorMapper.class, EditorialMapper.class, CategoryMapper.class })
public interface BookMapper {

    @Mappings({
            @Mapping(source = "state", target = "available")
    })
    BookDTO toBookDTO(Book book);
    List<BookDTO> toBooksDTO(List<Book> books);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "editorial", ignore = true),
            @Mapping(target = "category", ignore = true),
            @Mapping(target = "author", ignore = true)
    })
    Book toBook(BookDTO book);
}
