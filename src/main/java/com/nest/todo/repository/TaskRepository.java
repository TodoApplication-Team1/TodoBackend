package com.nest.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nest.todo.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query(value = "SELECT * FROM Task where DATE_PART('day',date-NOW())=0", nativeQuery = true)
	List<Task> getAll();

	@Query(value = "SELECT task_name FROM Task where DATE_PART('day',date-NOW())=0", nativeQuery = true)
	List<String> getTaskName();
}
