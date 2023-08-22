package com.example.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;


class JacksonTest {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
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
        String str = "{\"id\":1,\"age\": 88, \"password\":\"123\",\"address\":\"London\",\"websiteUrl\":\"http://www.baidu.com\",\"registerDate\":\"2023-08-22 00:13:40\",\"birthday\":\"2023-08-22 00:13:40\"}";
        User user = objectMapper.readValue(str, User.class);
        System.out.println(user);
    }

    @Test
    void testDeserializeWithGeneric() throws JsonProcessingException {
        User user = new User();
        user.setName("Simon Roger");
        user.setWebsiteUrl("http://www.baidu.com");

        ResultVo<User> resultVo = ResultVo.buildSuccess(user);
        String jsonStr = objectMapper.writeValueAsString(resultVo);

        ResultVo<User> userResultVo = objectMapper.readValue(jsonStr, new TypeReference<ResultVo<User>>() {});
        User data = userResultVo.getData();
    }
}
