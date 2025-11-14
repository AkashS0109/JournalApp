package com.ranchopro.journalApp.repository;


import com.ranchopro.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepository;


    @Test
    public void testSaveNewUser() {
        List<User> users = userRepository.getUsersForSentimentAnalysis();
        System.out.println("Users found: " + users.size());
        Assertions.assertTrue(users.size() > 0, "No users found matching query!");
    }
}
