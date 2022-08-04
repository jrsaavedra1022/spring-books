package com.asgeek.books.persistence.mapper;

import com.asgeek.books.domain.dto.CategoryDTO;
import com.asgeek.books.persistence.entity.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "name", target = "category")
    })
    CategoryDTO toCategoryDTO(Category category);
    List<CategoryDTO> toCategoriesDTO(List<Category> categories);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "books", ignore = true)
    })
    Category toCategory(CategoryDTO category);
}
