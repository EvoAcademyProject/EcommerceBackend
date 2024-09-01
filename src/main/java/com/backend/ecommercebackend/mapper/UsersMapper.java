package com.backend.ecommercebackend.mapper;

import com.backend.ecommercebackend.dto.UsersDto;
import com.backend.ecommercebackend.model.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    List<UsersDto> entityListToDtoList(List<Users> usersList);
    UsersDto entityToDto(Users users);

}
