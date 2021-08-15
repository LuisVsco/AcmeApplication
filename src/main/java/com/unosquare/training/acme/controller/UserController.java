package com.unosquare.training.acme.controller;

import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public User SaveUser(@RequestBody User user) {
        return userService.SaveUser(user);
    }

    @GetMapping(value = "/user")
    public User GetUser(@RequestParam final Integer id) {

        return userService.GetUserById(id);
    }

}
