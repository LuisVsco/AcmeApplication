package com.unosquare.training.acme.controller;

import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.security.JWT;
import com.unosquare.training.acme.services.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public UserDto GetUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object objId = auth.getPrincipal();
        Integer userId = Integer.valueOf(objId.toString());
        return userService.GetUserById(userId);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String pwd) {
        UserDto user = userService.GetUserByUserName(username);
        BCrypt.checkpw(pwd, user.getPassword());

        String token = JWT.generate(user.getId().toString());

        return token;
    }
}
