package com.nest.todo.mapper;

import com.nest.todo.controller.dto.UsersProfileDto;
import com.nest.todo.entities.Users;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-12T12:37:42+0530",
    comments = "version: 1.4.1.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.1 (Eclipse Adoptium)"
)
public class UsersProfileMapperImpl implements UsersProfileMapper {

    @Override
    public Users updateUsersProfile(UsersProfileDto usersProfileDto, Users users) {
        

        users.setAddress( usersProfileDto.getAddress() );
        users.setMobile( usersProfileDto.getMobile() );
        users.setPhone( usersProfileDto.getPhone() );

        return users;
    }
}
