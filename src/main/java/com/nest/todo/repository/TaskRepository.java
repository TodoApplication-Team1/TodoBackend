package com.nest.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nest.todo.entities.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
