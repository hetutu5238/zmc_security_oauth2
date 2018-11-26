package cn.com.megalith;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.megalith.dao")
public class CustomerSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerSimpleApplication.class , args);
    }
}
