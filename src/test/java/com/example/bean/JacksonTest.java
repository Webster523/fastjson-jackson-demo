package com.example.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;



class JacksonTest {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    void test() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
//        user.setName("Simon Roger");
        user.setPassword("123");
        user.setAddress("London");
        user.setWebsiteUrl("http://www.baidu.com");
        user.setRegisterDate(new Date());
        user.setBirthday(LocalDateTime.now());

        String string = objectMapper.writeValueAsString(user);
        System.out.println(string);
    }
}
