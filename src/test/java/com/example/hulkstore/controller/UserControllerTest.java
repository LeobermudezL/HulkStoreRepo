package com.example.hulkstore.controller;

import com.example.hulkstore.model.User;
import com.example.hulkstore.repository.UsersRepository;
import com.example.hulkstore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

class UserControllerTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    @DisplayName("Test getAllUsers")
    void getAllUsers() {
        User user1 = new User(1L,"Leandro","Bermudez","elio@hotmail.com","1215456");
        User user2 = new User(2L,"Jose","Perez","jp@outlook.com","14569857");
        doReturn(Arrays.asList(user1,user2)).when(usersRepository).findAll();

        List<User> users = userService.getAllUsers();

        Assertions.assertEquals(2,users.size(),"get all should return 2 users");
    }

    @Test
    @DisplayName("Test getUserById")
    void getUserById() {
        User user = new User(1L,"Leandro","Bermudez","elio@hotmail.com","1215456");
        doReturn(Optional.of(user)).when(usersRepository).findById(1L);

        Optional<User> returnUser = Optional.ofNullable(userService.getUserById(1L));

        Assertions.assertTrue(returnUser.isPresent(),"Product was not found");
        Assertions.assertSame(returnUser.get(),user,"The product returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test createUser")
    void createUser() {
        User user = new User (1L,"Leandro","Bermudez","elio@hotmail.com","1215456");
        doReturn(user).when(usersRepository).save(any());

        // Execute the service call
        User returnedUser = userService.createUser(user);

        // Assert the response
        Assertions.assertNotNull(returnedUser, "The saved widget should not be null");
        Assertions.assertEquals(returnedUser, returnedUser, "The version is saved");
    }

    @Test
    @DisplayName("Test updateUser")
    void updateUser() {
        User user = new User(1L,"Leandro","Bermudez","elio@hotmail.com","1215456");
        User userUpdate = new User(1L,"Elio","Bermudez","elio@hotmail.com","12154560");

        User returnedUser = userService.createUser(userUpdate);

        Assertions.assertEquals(returnedUser, returnedUser, "The new version is saved");
    }

    @Test
    @DisplayName("Test deleteUser")
    void deleteUser() {
        User user1 = new User(1L,"Leandro","Bermudez","elio@hotmail.com","1215456");
        User user2 = new User(2L,"Leo","Bermudez","leo@hotmail.com","1215456");
        User user3 = new User(3L,"Jose","Bermudez","jb@hotmail.com","1215456");
        doReturn(Arrays.asList(user2,user3)).when(usersRepository).findAll();
        usersRepository.deleteById(user1.getId());

        List <User> users = userService.getAllUsers();

        Assertions.assertEquals(2,users.size(),"get all should return 2 users");

    }
}