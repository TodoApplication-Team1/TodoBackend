package com.nest.todo.service;

import java.util.List;

import com.nest.todo.controller.dto.CategoryDto;
import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.entities.Category;
import com.nest.todo.entities.Task;
import com.nest.todo.entities.Users;


public interface TodoService {

	public String getUserDetails(String userName, String password);

	public Users addUser(UsersDto usersDto);

	public Task addTask(TasksDto tasksDto);

	public List<String> addCategory(CategoryDto categoryDto);

	public List<String> getCategory();

	public List<String> deleteSelectedCategory(String ctgry);

}
