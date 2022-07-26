package com.nest.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.entities.Users;


@Mapper
public interface UsersMapper {
	
	UsersDto convertToUsersDto( Users users);
	
	Users updateUser( UsersDto  usersDto, @MappingTarget  Users users);

}
