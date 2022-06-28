package com.qhh.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: QiuHH
 * @CreateTime: 2022-06-28  14:23
 * @Description: TODO
 */
@SpringBootApplication
@MapperScan("com.qhh.edu.mapper")
@ComponentScan(basePackages = {"com.qhh"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
