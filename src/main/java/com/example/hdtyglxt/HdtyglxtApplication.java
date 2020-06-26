package com.example.hdtyglxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.example.hdtyglxt.mapper")
public class HdtyglxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdtyglxtApplication.class, args);
    }

}
