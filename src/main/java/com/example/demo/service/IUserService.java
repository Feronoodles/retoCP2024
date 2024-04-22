package com.example.demo.service;

import com.example.demo.dto.user.DtoUser;
import com.example.demo.dto.user.DtoUserLogin;
import com.example.demo.entities.User;

public interface IUserService {

    public User save(DtoUserLogin dtoUser);

    public User updateUserToken(DtoUser dtoUser);

    public User findById(Long id);
    public User findUser(String encode);
}
