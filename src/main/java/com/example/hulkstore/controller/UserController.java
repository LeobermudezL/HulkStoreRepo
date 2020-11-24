package com.example.hulkstore.controller;

import com.example.hulkstore.model.User;
import com.example.hulkstore.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags= {"UserController"})
@RestController
@RequestMapping("/api/v1")
@Component
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path="/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "getAllUsers", notes = "endpoint to verify users in DB", response = String.class)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
    @GetMapping(path="/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <User> getUserById(@PathVariable long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
    @PostMapping(path="/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.createUser(user));
    }
    @PutMapping(path="/users/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity < User > updateUser (@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok().body(userService.updateUser(user));
    }
    @DeleteMapping(path="/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return HttpStatus.OK;
    }
}
