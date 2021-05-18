package com.yy.yebmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class YebMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(YebMailApplication.class, args);
    }

}
