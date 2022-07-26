package com.nest.todo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nest.todo.entities.Users;




@Repository
public interface TodoRepository extends JpaRepository<Users, Integer>{
	@Query("from Users u where u.userName=:Name")
	List<Users> getUser(@Param(value = "Name") String userName);
	
}
