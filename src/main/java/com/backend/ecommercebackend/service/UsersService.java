package com.backend.ecommercebackend.service;

import com.backend.ecommercebackend.dto.UsersDto;
import com.backend.ecommercebackend.dto.request.UsersRequest;

import java.util.List;

public interface UsersService {

    List<UsersDto> getAll();
    UsersDto get(Long id);
    UsersDto save(UsersRequest request);
    UsersDto update(Long id ,UsersRequest request);
    void delete(Long id);

}
