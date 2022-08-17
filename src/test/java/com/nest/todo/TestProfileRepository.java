package com.nest.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nest.todo.entities.Users;

public interface TestProfileRepository extends JpaRepository<Users, Integer>{

}
