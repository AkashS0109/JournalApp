package com.ranchopro.journalApp.service;

import com.ranchopro.journalApp.entity.User;
import com.ranchopro.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
// if you will use this you don't have to make instance  using  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passWordEncoder = new BCryptPasswordEncoder();

    //to get own loggs      use class name in which it is used  or by using anotation @
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveNewUser(User user) {
        try {
            user.setPassword(passWordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("User"));
            userRepository.save(user);
        } catch (Exception e) {
            // logger.error("Error Occured for {} :" ,user.getUserName(),e);   //here {} inside this the userName willprint
//            logger.info("HAHAHHAHHAHAHAHHAHHAHAHH");
            log.info("HAHAHAHAHHHAHAH");
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }


    public void saveAdmin(User user) {
        user.setPassword(passWordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("User", "ADMIN"));
        userRepository.save(user);
    }


    //get all entries
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //find byid
    public Optional<User> findById(ObjectId Id) {
        return userRepository.findById(Id);
    }

    // delete byId
    public void deleteByID(ObjectId id) {
        userRepository.deleteById(id);

    }

    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
