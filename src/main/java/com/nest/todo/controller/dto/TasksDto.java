package com.nest.todo.controller.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksDto {
	
//	SimpleDateFormat simpleformat = new SimpleDateFormat("hh:mm:s");
	private String category;
	private String taskName;
	private LocalTime time;
	
	private Date date;
}
