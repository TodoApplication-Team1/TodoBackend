package com.nest.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.entities.Task;


@Mapper
public interface TaskMapper {
	TasksDto convertToTasksDto(Task task);
	
	Task updateTask(TasksDto tasksDto, @MappingTarget Task task);
}
