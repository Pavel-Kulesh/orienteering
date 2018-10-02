package com.itacademy.jd2.pk.hop.service.impl.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringContextExample {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context-example.xml");
		
		
		
		System.out.println(context.getBean(MainBean.class));
	}
}
