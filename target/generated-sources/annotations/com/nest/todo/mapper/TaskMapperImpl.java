package com.nest.todo.mapper;

import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.entities.Task;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-25T10:47:42+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TasksDto convertToTasksDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TasksDto tasksDto = new TasksDto();

        tasksDto.setCategory( task.getCategory() );
        tasksDto.setDate( task.getDate() );
        tasksDto.setTaskName( task.getTaskName() );
        tasksDto.setTime( task.getTime() );

        return tasksDto;
    }

    @Override
    public Task updateTask(TasksDto tasksDto, Task task) {
        if ( tasksDto == null ) {
            return null;
        }

        task.setCategory( tasksDto.getCategory() );
        task.setDate( tasksDto.getDate() );
        task.setTaskName( tasksDto.getTaskName() );
        task.setTime( tasksDto.getTime() );

        return task;
    }
}
