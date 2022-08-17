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
import com.nest.todo.mapper.UsersProfileMapper;
import com.nest.todo.mapper.UsersProfileMapperImpl;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);

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

	@Bean
	public UsersProfileMapper getUsersProfileMapper() {
		return new UsersProfileMapperImpl();
	}

}
