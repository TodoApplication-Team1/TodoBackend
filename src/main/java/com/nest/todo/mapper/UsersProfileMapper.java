package com.nest.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.nest.todo.controller.dto.UsersProfileDto;
import com.nest.todo.entities.Users;

@Mapper
public interface UsersProfileMapper {
	

	Users updateUsersProfile(UsersProfileDto usersProfileDto, @MappingTarget Users users);
}
