package com.yueba.yueba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yueba.yueba.mapper")
public class YuebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(YuebaApplication.class, args);
	}

}
