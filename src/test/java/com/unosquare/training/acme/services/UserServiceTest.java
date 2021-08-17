package com.unosquare.training.acme.services;

import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private User SetUserEntity() {
        User user = new User();
        user.setId(1);
        user.setName("Silvester");
        user.setUsername("silver_s");
        user.setSchool("cucei");
        user.setRole("instructor");
        return user;
    }

    private UserDto SetUserDto() {
        UserDto user = new UserDto();
        user.setId(1);
        user.setName("Silvester");
        user.setUsername("silver_s");
        user.setSchool("cucei");
        user.setRole("instructor");
        return user;
    }

    @Test
    void saveUser() {
        User userToSave = SetUserEntity();
        userToSave.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));

        Mockito.when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(userToSave);
        User user = userRepository.save(SetUserEntity());

        assertEquals("Silvester", user.getName());
        assertEquals(1, user.getId());
        assertTrue(checkPass("password", user.getPassword()));
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            return true;
        }
        return false;
    }

    @Test
    void getUserById() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(SetUserEntity()));
        assertEquals(userRepository.findById(1).get().getName(), "Silvester");

    }

    @Test
    void getUserByUserName() {
        Mockito.when(userRepository.getUserByUserName("silver_s")).thenReturn(SetUserEntity());
        UserDto result = userService.GetUserByUserName("silver_s");
        assertEquals("silver_s", result.getUsername());

    }
}