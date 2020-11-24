package com.example.hulkstore.service;

import com.example.hulkstore.exception.ResourceNotFoundException;
import com.example.hulkstore.model.User;
import com.example.hulkstore.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    static final String USER_RESOURCE_NOT_FOUND = "User not found with id";

    @Autowired
    UsersRepository usersRepository;
    @Override
    public User createUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> userDB = this.usersRepository.findById(user.getId());
        if(userDB.isPresent()){
            User userUpdate = userDB.get();
            userUpdate.setName(user.getName());
            userUpdate.setLastName(user.getLastName());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPassword(user.getPassword());
            usersRepository.save(userUpdate);
            return userUpdate;
        } else {
            throw new ResourceNotFoundException( USER_RESOURCE_NOT_FOUND+ user.getId());
        }
    }

    @Override
    public List<User> getAllUsers() {
        return this.usersRepository.findAll();
    }

    @Override
    public User getUserById(long userId) {
        Optional < User > userDB = this.usersRepository.findById(userId);
        if(userDB.isPresent()){
            return userDB.get();
        }else {
            throw new ResourceNotFoundException(USER_RESOURCE_NOT_FOUND + userId);
        }
    }

    @Override
    public void deleteUser(long id) {
        Optional < User > userDB = this.usersRepository.findById(id);
        if(userDB.isPresent()){
            this.usersRepository.delete(userDB.get());
        }else {
            throw new ResourceNotFoundException(USER_RESOURCE_NOT_FOUND + id);
        }
    }
}
