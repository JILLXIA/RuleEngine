package com.example.xyd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.xyd.dao")
public class XydApplication {

	public static void main(String[] args) {
		SpringApplication.run(XydApplication.class, args);
	}

}
