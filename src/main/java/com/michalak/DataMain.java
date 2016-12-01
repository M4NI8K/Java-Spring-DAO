package com.michalak;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.michalak.dao.JdbcDaoImpl;
import com.michalak.model.Circle;

public class DataMain {

	public static void main(String[] args) {
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class); // make shore jdbcDaoImpl is LOWERCASE 
		
		
	//	Circle circle = dao.getCircle(1);
		
	//	System.out.println(circle.getName());
	// use of JdbcTemplate	type int
	
		//System.out.println(dao.getCircleCount());
	
	//	System.out.println(dao.getCircleName(1));
		 
		
		
	System.out.println(dao.getAllCircles().size());	//Getting list size from METHOD4
		
	}
	
	
}
