package com.shirs.agileboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//引入工作流的注解
/*@SpringBootApplication(
        exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
        })*/
@SpringBootApplication
@MapperScan("com.shirs.agileboot.modules.*.mapper")
//@EnableWebSecurity
public class AgileBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgileBootApplication.class, args);
    }

}
