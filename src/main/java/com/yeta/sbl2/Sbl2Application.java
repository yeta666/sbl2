package com.yeta.sbl2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描Mybatis mapper包路径
@MapperScan(basePackages = "com.yeta.sbl2.mapper")
//开启定时任务
@EnableScheduling
//开启异步任务
@EnableAsync
public class Sbl2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sbl2Application.class, args);
	}
}
