package com.unosquare.training.acme.services;

import org.mindrot.jbcrypt.BCrypt;
import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserDto SaveUser(UserDto userDto) {
        String encodedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        User user = new User();
        user.setName(userDto.getName());
        user.setSchool(userDto.getSchool());
        user.setRole(userDto.getRole());
        user.setUsername(userDto.getUsername());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);

        UserDto response = new UserDto();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSchool(user.getSchool());
        response.setRole(user.getRole());
        response.setUsername(user.getUsername());
        return response;
    }

    public UserDto GetUserById(final Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSchool(user.getSchool());
        userDto.setRole(user.getRole());
        return userDto;
    }
    public UserDto GetUserByUserName(final String username){
        User user = userRepository.getUserByUserName(username);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSchool(user.getSchool());
        userDto.setRole(user.getRole());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        return userDto;
    }

}
