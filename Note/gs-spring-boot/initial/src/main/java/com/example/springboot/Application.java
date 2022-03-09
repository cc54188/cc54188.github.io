package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication  // 主程序類，Spring的底層註解@import，給容器導入一個元件，將主配置類(@S...B...標記的類)的所在包及下面所有子包裏的元件掃描到Spring容器
public class Application {

	public static void main(String[] args) {
		// 啟動spring應用程式，run @SpringBootApplication標記的類別
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}


	}
	
}
