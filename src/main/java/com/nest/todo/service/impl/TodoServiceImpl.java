package com.nest.todo.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nest.todo.controller.dto.CategoryDto;
import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.controller.dto.UsersProfileDto;
import com.nest.todo.entities.Category;
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
import com.nest.todo.mapper.CategoryMapper;
import com.nest.todo.mapper.TaskMapper;
import com.nest.todo.mapper.UsersMapper;
import com.nest.todo.mapper.UsersProfileMapper;
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
	private UsersProfileMapper usersProfileMapper;

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	PasswordEncoder passwordEncoder;

	@Override
	public Users getUserDetails(String email, String password) {
		this.passwordEncoder = new BCryptPasswordEncoder();
		List<Users> users = todoRepository.findAll();
		Users u1 = new Users();

		for (Users u : users) {

			Boolean pwd = this.passwordEncoder.matches(password, u.getPassword());
			System.out.println(pwd);
			if (pwd && (u.getEmail()).equals(email)) {
				u1=u;
			}

		}
		return u1;

	}

	@Override
	public Users addUser(UsersDto usersDto) throws UserAlreadyExistException {
		this.passwordEncoder = new BCryptPasswordEncoder();
		List<Users> users = todoRepository.findAll();

		for (Users u : users) {
			if (usersDto.getEmail().equals(u.getEmail())) {
				throw new UserAlreadyExistException("User Already Exist");
			}
		}

		Users user = new Users();
		String encodedPassword = this.passwordEncoder.encode(usersDto.getPassword());
		usersDto.setPassword(encodedPassword);
		user = usersMapper.updateUser(usersDto, user);
		todoRepository.save(user);
		user = todoRepository.findById(user.getId()).get();
		return user;
	}

	@Override
	public Task addTask(int userId, int categoryId, TasksDto tasksDto)
			throws UserNotExistException, CategoryNotExistException, 
			CategoryNameNullException, TaskNullException, TimeNullException, TimeNotValidException, DateNullException, DateNotValidException {

//		long millis = System.currentTimeMillis();
//		java.util.Date date = new java.util.Date(millis);
		LocalDate date = LocalDate.now();
		System.out.println(date);
//		date.getTime();
		if (!(todoRepository.existsById(userId))) {
			throw new UserNotExistException("User not Exist");
		}
		
		Users user = todoRepository.findById(userId).get();

		
//		Long t= Long.parseLong(tasksDto.getTime());
		if (tasksDto.getCategoryName().equals("")) {
			throw new CategoryNameNullException("Category cannot be null");
		}
		
		
		int flag = 0;
		
		if (tasksDto.getTaskName().equals("")) {
			throw new TaskNullException("Task cannot be null");
		}

		if (tasksDto.getTime()==null) {
			throw new TimeNullException("Time cannot be null");
		}
		ZoneId defaultZoneId = ZoneId.systemDefault();
		if (tasksDto.getDate()==null) {
			throw new DateNullException("Date cannot be null");
		}
		//Converting the date to Instant
		Instant instant = tasksDto.getDate().toInstant();
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		System.out.println(localDate);
//		Time t = Time.valueOf(tasksDto.getTime());
		 LocalTime myObj = LocalTime.now();
		if (localDate.compareTo(date) < 0) {
			System.out.println(date);
//			System.out.println(date.before(tasksDto.getDate()));
			throw new DateNotValidException("Date is not valid");
		}
		if((localDate.compareTo(date) == 0)&&(tasksDto.getTime().compareTo(myObj)<0)) {
			throw new TimeNotValidException("Time is not valid");
		}
//
		
		List<Category> categories = user.getCategory();
		Task task = new Task();
		for (Category category : categories) {
			if (categoryRepository.existsById(categoryId)) {
				flag = 1;

			}

			if ((flag==1) && (category.getCategoryId() == categoryId)) {
				List<Task> tasks = category.getTasks();

				task = taskMapper.updateTask(tasksDto, task);
				tasks.add(task);
				taskRepository.save(task);
				category.setTasks(tasks);
//				categoryRepository.save(category);
//				taskRepository.save(task);
				
			}

		}
		if (flag == 0) {
			throw new CategoryNotExistException("Category is not exists");
		}  
		user.setCategory(categories);
		todoRepository.save(user);
		task = taskRepository.findById(task.getId()).get();
		System.out.println(task);
		
		return task;
	}

	@Override
	public List<String> addCategory(int id, CategoryDto categoryDto)
			throws CategoryAlreadyExistException, UserNotExistException {
		// TODO Auto-generated method stub
		List<String> categoryD = new ArrayList<String>();
		if (todoRepository.existsById(id)) {
			Users u = todoRepository.findById(id).get();
			List<Category> categories = u.getCategory();
			for (Category c : categories) {
				if (c.getCategoryName().equals(categoryDto.getCategoryName())) {
					throw new CategoryAlreadyExistException("Category is already exists");
				}
			}
			Category category = new Category();
			category = categoryMapper.updateCategory(categoryDto, category);
			category.setUser_fk(id);
			categories.add(category);
//		u.setCategory(categories);
			todoRepository.save(u);
//		categoryRepository.save(category);

			for (Category c : categories) {
				categoryD.add(c.getCategoryName());
			}
		} else {
			throw new UserNotExistException("User is not exist");
		}
		return categoryD;
	}

	@Override
	public List<String> getCategoryList(int id) {
		Users user = todoRepository.findById(id).get();
		List<Category> categories = user.getCategory();
		List<String> categoryDto = new ArrayList<String>();
		for (Category c : categories) {
			categoryDto.add(c.getCategoryName());
		}
		return categoryDto;
	}

	@Override
	public void deleteSelectedCategory(int id){
		// TODO Auto-generated method stub
				
		categoryRepository.deleteById(id);
	}		
		
	

	@Override
	public Users editUsersProfile(int userId,UsersProfileDto usersProfileDto) {
		Users user = todoRepository.findById(userId).get();
		if(!(usersProfileDto.getAddress()== "")) {
			user.setAddress(usersProfileDto.getAddress());
		}
		if(!(usersProfileDto.getMobile()== "")) {
			user.setMobile(usersProfileDto.getMobile());
		}
		if(!(usersProfileDto.getPhone()== "")) {
			user.setPhone(usersProfileDto.getPhone());
		}
//		user=usersProfileMapper.updateUsersProfile(usersProfileDto, user);
		todoRepository.save(user);
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public Users addUsersProfile(int userId,UsersProfileDto usersProfileDto) {
		
		Users user = todoRepository.findById(userId).get();

		user = usersProfileMapper.updateUsersProfile(usersProfileDto, user);
		todoRepository.save(user);
		user = todoRepository.findById(user.getId()).get();
		return user;
	}
	
	@Override
	public List<Task> getTodayList(int userId) {
		System.out.println("working");
		Users user=todoRepository.findById(userId).get();
		List<Category> categories=user.getCategory();
		List<Task> taskArray = new ArrayList<Task>();
		List<Task> tasks = taskRepository.getAll();
		for(Category c: categories) {
			for(Task t: c.getTasks()) {
				if(tasks.contains(t)) {
					taskArray.add(t);
				}
			}
		}
		// List<Task> tasks=taskRepository.findAll();
		return taskArray;
	}

	@Override
	public List<String> getTaskNameList(int userId) {
		Users user=todoRepository.findById(userId).get();
		List<String> task=new ArrayList<String>();
		List<String> newArray=new ArrayList<String>();
		List<Category> categories=user.getCategory();
		for(Category c: categories) {
			for(Task t: c.getTasks()) {
				task.add(t.getTaskName());
			}
		}
		System.out.println("tasks" + task);
		List<String> tasks = taskRepository.getTaskName();
		for(String name: tasks) {
			if(task.contains(name)) {
				newArray.add(name);
			}
		}
		System.out.println("task" + task);
		return newArray;
	}

	@Override
	public Users getProfile(int userId) {
		Users user = todoRepository.findById(userId).get();
		return user;
	}

	@Override
	public int getCategoryID(int id, String ctgry) {
		Users user = todoRepository.findById(id).get();
		int categoryID = 0;
		List<Category> category = user.getCategory();
		for(Category c: category){
			if(c.getCategoryName().equals(ctgry)) {
				categoryID= c.getCategoryId();
			}
		}
		return categoryID;
	}

	@Override
	public List<Task> getCalendarList() {
		System.out.println("working");
		// TODO Auto-generated method stub
		 List<Task> tasks=taskRepository.findAll();
		 System.out.println("size= "+tasks.size());
	        return tasks;
	}
}
