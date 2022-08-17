package com.nest.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nest.todo.controller.dto.CategoryDto;
import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.controller.dto.UsersProfileDto;
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
import com.nest.todo.service.TodoService;

@EnableScheduling
@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	private TodoService todoService;


	@GetMapping("/signin")
	public ResponseEntity<?> getUser(@RequestParam String email, @RequestParam String password) {

		return new ResponseEntity<>(todoService.getUserDetails(email, password), HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> postUser(@RequestBody UsersDto usersDto) throws UserAlreadyExistException {

		return new ResponseEntity<>(todoService.addUser(usersDto), HttpStatus.OK);
	}

	@PostMapping("/task")
	public ResponseEntity<?> postTask(@RequestParam int userId, @RequestParam int categoryId,
			@RequestBody TasksDto tasksDto)
			throws UserNotExistException, CategoryNotExistException,
			CategoryNameNullException, TaskNullException, TimeNullException, TimeNotValidException, DateNullException, DateNotValidException {

		return new ResponseEntity<>(todoService.addTask(userId, categoryId, tasksDto), HttpStatus.OK);
	}

	@PostMapping("/category")
	public ResponseEntity<?> postCategory(@RequestParam int id, @RequestBody CategoryDto categoryDto)
			throws CategoryAlreadyExistException, UserNotExistException {

		return new ResponseEntity<>(todoService.addCategory(id, categoryDto), HttpStatus.OK);
	}

	@GetMapping("/getCategory")
	public ResponseEntity<?> getCategories(@RequestParam int id) {

		return new ResponseEntity<>(todoService.getCategoryList(id), HttpStatus.OK);
	}

	@DeleteMapping(path = "/Category")
	public void deleteCategory(@RequestParam int categoryId) {

		todoService.deleteSelectedCategory(categoryId);
	}
	
	@GetMapping(path="/CategoryId")
	public int getCategoryId(@RequestParam int id, @RequestParam String ctgry) {

		return todoService.getCategoryID(id, ctgry);
	}

	@PutMapping("/editprofile")
	public ResponseEntity<?> putUsersProfile(@RequestParam int userId,@RequestBody UsersProfileDto usersProfileDto) {
		return new ResponseEntity<>(todoService.editUsersProfile(userId,usersProfileDto), HttpStatus.OK);
	}

	@PostMapping("/profile")
	public ResponseEntity<?> postUsersProfile(@RequestParam int userId,@RequestBody UsersProfileDto usersProfileDto) {
		return new ResponseEntity<>(todoService.addUsersProfile(userId,usersProfileDto), HttpStatus.OK);
	}
	
	@GetMapping("/getProfile")
	public ResponseEntity<?> getProfiles(@RequestParam int userId) {

		return new ResponseEntity<>(todoService.getProfile(userId), HttpStatus.OK);
	}
//	@Scheduled(fixedDelay = 1000)
	@GetMapping("/getTodayList")
	public ResponseEntity<?> getTodayList(@RequestParam int userId) {
		
		return new ResponseEntity<>(todoService.getTodayList(userId), HttpStatus.OK);
	}

	@GetMapping("/getTodayTaskName")
	public ResponseEntity<?> getTodayTaskName(@RequestParam int userId) {
		return new ResponseEntity<>(todoService.getTaskNameList(userId), HttpStatus.OK);
	}
	

	
	@GetMapping("/getCalendarList")
    public ResponseEntity<?> getCalendarList(){
        return new ResponseEntity<>(todoService.getCalendarList(),HttpStatus.OK);
    }
}
