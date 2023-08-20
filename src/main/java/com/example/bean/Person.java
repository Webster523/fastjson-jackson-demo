package com.example.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Person {
    private Long id;
    private String name;
    @JSONField(serialize = false, deserialize = false)
    private String password;
    @JSONField(name = "addr")
    private String address;
    private String websiteUrl;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;
}
