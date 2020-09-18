package com.example.pm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@RestController
@MapperScan(basePackages = "com.example.pm.dao")
@SpringBootApplication
public class ParkingManagerApplication {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String main() {
        return "首页";
    }

    public static void main(String[] args) {
        SpringApplication.run(ParkingManagerApplication.class, args);
    }

}
