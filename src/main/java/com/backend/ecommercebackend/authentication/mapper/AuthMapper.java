package com.backend.ecommercebackend.authentication.mapper;

import com.backend.ecommercebackend.authentication.dto.request.RegisterRequest;
import com.backend.ecommercebackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(request.getPassword()))")
      User RegisterDtoToEntity(RegisterRequest request, PasswordEncoder passwordEncoder);
}
