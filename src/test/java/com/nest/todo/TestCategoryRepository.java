package com.nest.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nest.todo.entities.Category;


public interface TestCategoryRepository  extends JpaRepository<Category, Integer>{

}
