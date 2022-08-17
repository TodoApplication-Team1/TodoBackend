package com.nest.todo.service;

import java.util.List;

import com.nest.todo.controller.dto.CategoryDto;
import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.controller.dto.UsersProfileDto;
import com.nest.todo.entities.Task;
import com.nest.todo.entities.Users;
import com.nest.todo.exception.CategoryAlreadyExistException;
import com.nest.todo.exception.CategoryNameNullException;
import com.nest.todo.exception.CategoryNotExistException;
import com.nest.todo.exception.DateNotValidException;
import com.nest.todo.exception.DateNullException;
import com.nest.todo.exception.TaskNullException;
import com.nest.todo.exception.TimeNotValidException;
import com.nest.todo.exception.TimeNullException;
import com.nest.todo.exception.UserAlreadyExistException;
import com.nest.todo.exception.UserNotExistException;

public interface TodoService {

	public Users getUserDetails(String email, String password);

	public Users addUser(UsersDto usersDto) throws UserAlreadyExistException;

	public Task addTask(int userId, int categoryId, TasksDto tasksDto)
			throws UserNotExistException, CategoryNotExistException,
			CategoryNameNullException, TaskNullException, TimeNullException, TimeNotValidException, DateNullException, DateNotValidException;

	public List<String> addCategory(int id, CategoryDto categoryDto)
			throws CategoryAlreadyExistException, UserNotExistException;

	public List<String> getCategoryList(int id);

	public void deleteSelectedCategory(int id);

	public Users editUsersProfile(int userId, UsersProfileDto usersProfileDto);

	public Users addUsersProfile(int userId,UsersProfileDto usersProfileDto);

	public List<Task> getTodayList(int userId);

	public List<String> getTaskNameList(int userId);

	public Users getProfile(int userId);

	public int getCategoryID(int id, String ctgry);

	public List<Task> getCalendarList();

}
