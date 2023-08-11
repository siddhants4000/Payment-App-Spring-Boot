package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.model.WrapperResponse;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public WrapperResponse<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/delete")
    public WrapperResponse<UserResponse> deleteUser(@RequestParam UUID userId) {
        return userService.deleteUser(userId);
    }

    @PostMapping("/block")
    public WrapperResponse<UserResponse> blockUser(@RequestParam UUID userId) {
        return userService.blockUser(userId);
    }

    @PostMapping("/activate")
    public WrapperResponse<UserResponse> activateUser(@RequestParam UUID userId) {
        return userService.activateUser(userId);
    }

}
