package com.example.hulkstore.service;

import com.example.hulkstore.model.User;

import java.util.List;

public interface UserService {
    User createUser (User user);

    User updateUser (User user);

    List<User> getAllUsers();

    User getUserById(long userId);

    void deleteUser(long id);
}
