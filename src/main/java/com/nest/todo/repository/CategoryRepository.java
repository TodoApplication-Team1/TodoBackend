package com.nest.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nest.todo.entities.Category;
import com.nest.todo.entities.Task;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query(value = "Select c.id from Category c where c.categoryName=:name")
	public Integer getCategoryId(String name);

}
