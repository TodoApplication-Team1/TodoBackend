package com.nest.todo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nest.todo.controller.dto.CategoryDto;
import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.entities.Users;
import com.nest.todo.service.TodoService;





@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(path = "/hai", method = RequestMethod.GET)
	public String getHome() {
		return "User";
	}
	
	@GetMapping("/signin")
	public ResponseEntity<?> getUser(@RequestParam String userName, @RequestParam String password){
		
		return new ResponseEntity<>(todoService.getUserDetails(userName,password), HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> postUser(@RequestBody UsersDto usersDto){
		
		return new ResponseEntity<>(todoService.addUser(usersDto), HttpStatus.OK);
	}
	
	@PostMapping("/task")
	public ResponseEntity<?> postTask(@RequestBody TasksDto tasksDto){
		
		return new ResponseEntity<>(todoService.addTask(tasksDto), HttpStatus.OK);
	}
	
	@PostMapping("/category")
	public ResponseEntity<?> postCategory(@RequestBody CategoryDto categoryDto){
		
		return new ResponseEntity<>(todoService.addCategory(categoryDto), HttpStatus.OK);
	}
	
	@GetMapping("/getCategory")
	public ResponseEntity<?> getCategories(){
		
		return new ResponseEntity<>(todoService.getCategory(), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/Category")
	public ResponseEntity<?> deleteCategory(@RequestParam String ctgry)  {

		
		return new ResponseEntity<>(todoService.deleteSelectedCategory(ctgry), HttpStatus.OK);

	}
}
