package com.wangkang;

import com.wangkang.mapper.User1Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackageClasses = User1Mapper.class)
public class SpringBootTransactionStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTransactionStudyApplication.class, args);
	}

}
