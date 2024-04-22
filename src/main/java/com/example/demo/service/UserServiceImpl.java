package com.example.demo.service;

import com.example.demo.dto.user.DtoUser;
import com.example.demo.dto.user.DtoUserLogin;
import com.example.demo.entities.User;
import com.example.demo.infra.security.TokenService;
import com.example.demo.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{
    TokenService tokenService;
    UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User save(DtoUserLogin dtoUser) {
        dtoUser.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
        User user = new User(dtoUser);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUserToken(DtoUser dtoUser) {
        User user = new User(dtoUser);

        return (User)userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public User findUser(String encode) {
        Long userId = Long.parseLong(tokenService.decodeToken(encode)[0]);

        return userRepository.getReferenceById(userId);
    }
}
