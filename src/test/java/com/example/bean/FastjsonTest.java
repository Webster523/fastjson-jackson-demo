package com.example.bean;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alibaba.fastjson2.JSONWriter.Feature.WriteMapNullValue;

//import static com.alibaba.fastjson.JSON.toJSONString;


class FastjsonTest {
    @Test
    @DisplayName("序列化：将bean转为json字符串")
    void test1() {
        Person person = new Person();
        person.setId(1L);
//        person.setName("Simon Roger");
        person.setPassword("123");
        person.setAddress("London");
        person.setWebsiteUrl("http://www.baidu.com");
        person.setRegisterDate(new Date());
        person.setBirthday(LocalDateTime.now());

        // serialize
//        String jsonString = JSON.toJSONString(person, WriteMapNullValue);
//        String jsonString = JSON.toJSONString(person, SerializerFeature.WriteMapNullValue);
        String jsonString = JSON.toJSONString(person, true);
        System.out.println(jsonString);
    }

    @Test
    @DisplayName("测试fastjson的引用探测")
    void test2() {
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setId(33L);
        person.setName("Simon Roger");
        list.add(person);
        list.add(person);
        list.add(person);
//        String jsonString = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);
    }

    /**
     * 要求：输出的json字符串中，key是大写的
     * */
    @Test
    void testSerializeFilter() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Simon Roger");
        person.setPassword("123");
        person.setAddress("London");
        person.setWebsiteUrl("http://www.baidu.com");
        person.setRegisterDate(new Date());
        person.setBirthday(LocalDateTime.now());

        SerializeFilter filter = (NameFilter) (object, name, value) -> name.toUpperCase();
        String jsonString = JSON.toJSONString(person, filter);
        System.out.println(jsonString);
    }

    @Test
    @DisplayName("反序列化：json --> bean")
    void testDeserialize() {
        String jsonStr = "{\"address\":\"London\",\"birthday\":\"2023-08-20 18:41:06\",\"id\":1,\"name\":\"Simon Roger\",\"password\":\"123\",\"registerDate\":\"2023-08-20 18:41:06\",\"websiteUrl\":\"http://www.baidu.com\"}";
        Person person = JSON.parseObject(jsonStr, Person.class);
        System.out.println(person);
    }

    @Test
    void testDeserializeWithGeneric() {
        String jsonStr = "{\"address\":\"London\",\"birthday\":\"2023-08-20 18:41:06\",\"id\":1,\"name\":\"Simon Roger\",\"password\":\"123\",\"registerDate\":\"2023-08-20 18:41:06\",\"websiteUrl\":\"http://www.baidu.com\"}";
        Person person = JSON.parseObject(jsonStr, Person.class);
        // 模拟：将ResultVo返回给调用端
        ResultVo<Person> personResultVo = ResultVo.buildSuccess(person);
        String jsonString = JSON.toJSONString(personResultVo);

        // 模拟：调用端将jsonString进行反序列化
        ResultVo<Person> resultVo = JSON.parseObject(jsonString, new TypeReference<ResultVo<Person>>(){});
        Person data = resultVo.getData();
    }



}
