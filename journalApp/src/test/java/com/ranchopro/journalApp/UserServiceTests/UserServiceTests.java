package com.ranchopro.journalApp.UserServiceTests;

import com.ranchopro.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {


    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName() {
        assertEquals(4, 2 + 2);
        assertNotNull(userRepository.findByUserName("Ram"));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"

    })
    public void test(int a, int b, int excepted) {
        assertEquals(excepted, a + b);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Ram",
            "Sam",
            "Shyam"
    })
    public void testFindByUserName(String userName) {
        assertNotNull(userRepository.findByUserName(userName));
    }

}
