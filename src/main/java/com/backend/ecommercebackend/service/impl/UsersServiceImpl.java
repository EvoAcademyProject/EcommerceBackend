package com.backend.ecommercebackend.service.impl;

import com.backend.ecommercebackend.dto.UsersDto;
import com.backend.ecommercebackend.dto.request.UsersRequest;
import com.backend.ecommercebackend.mapper.UsersMapper;
import com.backend.ecommercebackend.repository.user.UserRepository;
import com.backend.ecommercebackend.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersMapper mapper;
    private final UserRepository repository;

    @Override
    public List<UsersDto> getAll() {
       return mapper.entityListToDtoList(repository.findAll());
    }

    @Override
    public UsersDto get(Long id) {
        return null;
    }

    @Override
    public UsersDto save(UsersRequest request) {
        return null;
    }

    @Override
    public UsersDto update(Long id, UsersRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
