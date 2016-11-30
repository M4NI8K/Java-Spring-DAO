package com.michalak;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.michalak.dao.JdbcDaoImpl;
import com.michalak.model.Circle;

public class DataMain {

	public static void main(String[] args) {
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class); // make shore jdbcDaoImpl is LOWERCASE 
		
		
		Circle circle = dao.getCircle(1);
		
		System.out.println(circle.getName());
		

	}
	
	
	
}
