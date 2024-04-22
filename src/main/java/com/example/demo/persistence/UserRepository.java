package com.example.demo.persistence;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    public UserDetails findByUsername(String username);

    public User findByUsernameAndPassword(String username,String password);
}
