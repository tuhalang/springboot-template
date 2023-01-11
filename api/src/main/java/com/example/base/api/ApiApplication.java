package com.example.base.api;

import com.example.base.common.utils.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        System.out.println(StringUtils.isEmpty(""));
        SpringApplication.run(ApiApplication.class, args);
    }

}
