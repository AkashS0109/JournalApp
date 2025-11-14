package com.ranchopro.journalApp.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;       // this will provide abstraction to use and interact with redis

    @Disabled
    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("email", "aka@gmail.com");          //setting value here so we can get  it in  rediz console but we r not getting it even if are stting from redis we can't get it here
        // serializer and deserialization is different in both so its creating issue so we need create the bean of redis template and set serialization and deserialization according ot it

        String c = (String) redisTemplate.opsForValue().get("name");
        System.out.println(c);
    }
// when the  user will hit the api from same place suppose in weather api 100 people from mumbai search the
// temperature of mumbai so rather than hitting api 100 time redis will store the result in suppose 1 st time and
// store that in redis for suppose 10 min and clean it after that this will reduce latency time

}
