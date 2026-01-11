package com.yeditepe.UserService.controller;

import com.yeditepe.UserService.DTO.LoginRequest;
import com.yeditepe.UserService.DTO.UserRequest;
import com.yeditepe.UserService.entity.User;
import com.yeditepe.UserService.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "BasicAuth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> all() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User newUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/{id}")
    public User one(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }

    @PutMapping("/{id}")
    public User replaceUser(@PathVariable Long id, @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
