package com.ranchopro.journalApp.service;

import com.ranchopro.journalApp.entity.User;
import com.ranchopro.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

// userDetails service is an interface so we willmake its implementation class
// this is used to fetch user details. from DB It’s a Spring Security interface that tells Spring how to load user information (username, password, roles/authorities).
//If you provide a UserDetailsService → Spring knows how to fetch your users.

// it is used when we try to login andour pasword issavein db so it will get our user by username and match our passowrd fromdatabseone
@Component
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().
                    username(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userDetails;
        }
        throw new UsernameNotFoundException("User Not Found Username" + username);
    }
}
