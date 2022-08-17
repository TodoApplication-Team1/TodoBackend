package com.nest.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nest.todo.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query(value = "Select c.id from Category c where c.categoryName=:name")
	public Integer getCategoryId(String name);
	

	@Query(value = "DELETE FROM Category c where c.user_fk=:uid AND c.categoryName=:categy")
	void deleteCategory(@Param(value= "uid") int user_fk,@Param(value="categy") String categoryName);

}
