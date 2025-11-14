package com.ranchopro.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement         //search methods where transaction is written
@EnableScheduling                   // this tells there is somee methods using scheduling in our app
public class JournalAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(JournalAppApplication.class, args);
    }

    //MongoDatabaseFactory helps us to make connection with database and all sessions created also with database
    // MongoDatabaseFactory is  a interface and its implementation is SimpleMongoClientDatabase factory
    // here this is implementation of Platform transaction manager(interface) to use transaction in code
    // its implementation is MongoTransactionManager
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
