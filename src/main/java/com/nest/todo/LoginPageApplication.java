package com.nest.todo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nest.todo.mapper.CategoryMapper;
import com.nest.todo.mapper.CategoryMapperImpl;
import com.nest.todo.mapper.TaskMapper;
import com.nest.todo.mapper.TaskMapperImpl;
import com.nest.todo.mapper.UsersMapper;
import com.nest.todo.mapper.UsersMapperImpl;


@SpringBootApplication
public class LoginPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginPageApplication.class, args);
	}
	@Value(value = "${swagger.url}")
	public String url;
	
	@Bean
	public UsersMapper getUsersMapper() {
		return new UsersMapperImpl();
	}
	
	@Bean
	public TaskMapper getTaskMapper() {
		return new TaskMapperImpl();
	}
	
	@Bean
	public CategoryMapper getCategoryMapper() {
		return new CategoryMapperImpl();
	}


}
