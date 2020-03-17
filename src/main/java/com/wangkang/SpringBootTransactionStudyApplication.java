package com.wangkang;

import com.wangkang.mapper.PropagationTestMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackageClasses = PropagationTestMapper.class)
public class SpringBootTransactionStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTransactionStudyApplication.class, args);
	}

}
