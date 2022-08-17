package com.nest.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.nest.todo.controller.dto.CategoryDto;

import com.nest.todo.entities.Category;

@Mapper
public interface CategoryMapper {
	

	Category updateCategory(CategoryDto categoryDto, @MappingTarget Category category);
}
