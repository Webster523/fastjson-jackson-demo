package com.example.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;


class JacksonTest {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void test1() throws JsonProcessingException {
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

    @Test
    void test2() throws JsonProcessingException {
        String str = "{\"id\":1,\"age\":88, \"password\":\"123\",\"address\":\"London\",\"websiteUrl\":\"http://www.baidu.com\",\"registerDate\":\"2023-08-22 00:13:40\",\"birthday\":\"2023-08-22 00:13:40\"}";
        User user = objectMapper.readValue(str, User.class);
        System.out.println(user);
    }
}
