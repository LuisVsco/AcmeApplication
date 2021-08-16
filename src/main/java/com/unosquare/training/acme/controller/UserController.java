package com.unosquare.training.acme.controller;

import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public UserDto SaveUser(@RequestBody UserDto userDto) {
        return userService.SaveUser(userDto);
    }

    @GetMapping(value = "/user")
    public UserDto GetUser(@RequestParam final Integer id) {

        return userService.GetUserById(id);
    }

}
