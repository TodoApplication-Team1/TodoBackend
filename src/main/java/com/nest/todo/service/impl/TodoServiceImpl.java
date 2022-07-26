package com.nest.todo.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nest.todo.controller.dto.CategoryDto;
import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.entities.Category;
import com.nest.todo.entities.Task;
import com.nest.todo.entities.Users;
import com.nest.todo.mapper.CategoryMapper;
import com.nest.todo.mapper.TaskMapper;
import com.nest.todo.mapper.UsersMapper;
import com.nest.todo.repository.CategoryRepository;
import com.nest.todo.repository.TaskRepository;
import com.nest.todo.repository.TodoRepository;
import com.nest.todo.service.TodoService;
@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public String getUserDetails(String userName, String password){
		List<Users> users=todoRepository.findAll();
		
		int c=0;
		for(Users u: users) {
			
			if((u.getPassword()).equals(password) && (u.getUserName()).equals(userName)){
				c++;
			}
			
		}
		if(c==1) {
			return "OK";
		}
		return "Not OK";
		
	}
    
	@Override
	public Users addUser(UsersDto usersDto) {
		Users user = new Users();
		user=usersMapper.updateUser(usersDto, user);
		todoRepository.save(user);
		user=todoRepository.findById(user.getId()).get();
		return user;
	}

	@Override
	public Task addTask(TasksDto tasksDto) {
		Task task = new Task();
		task=taskMapper.updateTask(tasksDto, task);
		taskRepository.save(task);
		task=taskRepository.findById(task.getId()).get();
		return task;
	}

	@Override
	public List<String> addCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = new Category();
		category=categoryMapper.updateCategory(categoryDto, category);
		categoryRepository.save(category);
		List<Category> categories=categoryRepository.findAll();
		List<String> categoryD = new ArrayList<String>();
		for(Category c: categories) {
			categoryD.add(c.getCategoryName());
		}
		return categoryD;
	}

	@Override
	public List<String> getCategory() {
		List<Category> categories=categoryRepository.findAll();
		List<String> categoryDto = new ArrayList<String>();
		for(Category c: categories) {
			categoryDto.add(c.getCategoryName());
		}
		return categoryDto;
	}

	@Override
	public List<String> deleteSelectedCategory(String ctgry) {
		// TODO Auto-generated method stub
		
		categoryRepository.deleteById(categoryRepository.getCategoryId(ctgry));
		return getCategory();
		
	}
}
