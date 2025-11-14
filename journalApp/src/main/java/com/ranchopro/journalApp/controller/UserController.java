package com.ranchopro.journalApp.controller;

import com.ranchopro.journalApp.entity.User;
import com.ranchopro.journalApp.repository.UserRepository;
import com.ranchopro.journalApp.service.UserService;
import com.ranchopro.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    WeatherService weatherService;

//    @GetMapping
//    public List<User> getAllUsers(){
//
//        return userService.getAll();
//    }
    // Update User Details lIke UserName OR Password. you need to provide credentials in header in Authentication file which is in database username and password

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        //security context  will give you access to name from header you don't have to pass it in url
        //Securitycontextholder hold the  data of header and authorization data  //when user login then its details stored in securityContext
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        String userName = authen.getName();
        System.out.println(userName);
        User userInDB = userService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());

        userService.saveNewUser(userInDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        // user authenticated first then gives username
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        String userName = authen.getName();
        userRepository.deleteByUserName(authen.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> GetWeatherDetails() {
        // user authenticated first then gives username
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hi " + authen.getName() + " Weather feels like " + weatherService.getWeather("Mumbai").getCurrent().getFeelslike(), HttpStatus.OK);
    }

}
