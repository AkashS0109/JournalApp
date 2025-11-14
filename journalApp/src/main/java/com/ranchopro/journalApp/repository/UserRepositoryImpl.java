package com.ranchopro.journalApp.repository;

import com.ranchopro.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;  // gives us abstraction  to use mongoTemplate  to interact with database mongodb

    // this file is used when we  write query id we don't use MongoRepository

    public List<User> getUsersForSentimentAnalysis() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is("Sam"));
//        query.addCriteria(Criteria.where("field").ne("value"));    //ne === notequalto  gte== greater than equal to

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        //getting users lists who opted the sentiment analysis
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
