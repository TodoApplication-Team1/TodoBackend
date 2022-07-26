package com.nest.todo.mapper;

import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.entities.Users;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-25T10:47:42+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class UsersMapperImpl implements UsersMapper {

    @Override
    public UsersDto convertToUsersDto(Users users) {
        if ( users == null ) {
            return null;
        }

        UsersDto usersDto = new UsersDto();

        usersDto.setPassword( users.getPassword() );
        usersDto.setUserName( users.getUserName() );

        return usersDto;
    }

    @Override
    public Users updateUser(UsersDto usersDto, Users users) {
        if ( usersDto == null ) {
            return null;
        }

        users.setPassword( usersDto.getPassword() );
        users.setUserName( usersDto.getUserName() );

        return users;
    }
}
