package com.nest.todo.mapper;

import com.nest.todo.controller.dto.CategoryDto;
import com.nest.todo.entities.Category;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-25T10:47:42+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto convertToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryName( category.getCategoryName() );

        return categoryDto;
    }

    @Override
    public Category updateCategory(CategoryDto categoryDto, Category category) {
        if ( categoryDto == null ) {
            return null;
        }

        category.setCategoryName( categoryDto.getCategoryName() );

        return category;
    }
}
