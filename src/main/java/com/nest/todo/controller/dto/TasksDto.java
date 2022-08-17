package com.nest.todo.controller.dto;

import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TasksDto {

	@NotEmpty
	private String categoryName;
	@NotEmpty
	private String taskName;

	@NotNull
	private LocalTime time;
	@NotNull
	private Date date;
}
