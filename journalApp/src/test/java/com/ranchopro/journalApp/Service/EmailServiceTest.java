package com.ranchopro.journalApp.Service;

import com.ranchopro.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail() {
        emailService.sendEmail("ra944399@gmail.com", "Testing Java Mail Sender", "Hi App Kaise hai");
    }
}
