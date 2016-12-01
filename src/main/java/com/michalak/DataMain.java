package com.michalak;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.michalak.dao.JdbcDaoImpl;
import com.michalak.dao.JdbcTemplateImpl;
import com.michalak.model.Circle;

public class DataMain {

	public static void main(String[] args) {
		
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		//No class template 
		JdbcDaoImpl dao = ctx.getBean("jdbcDaoImpl", JdbcDaoImpl.class); // make shore jdbcDaoImpl is LOWERCASE 
		//DAO Support class template  
		JdbcTemplateImpl dao2 = ctx.getBean("jdbcTemplateImpl", JdbcTemplateImpl.class);
	
		
		//	Circle circle = dao.getCircle(1);
		
	//	System.out.println(circle.getName());
	
	
	//System.out.println(dao.getCircleCount());
	
	//	System.out.println(dao.getCircleName(1));
		 
	dao.insertCircle2(new Circle(3, "Third Circle"));	
	//DAO Support class template method
	System.out.println(dao.getAllCircles().size());	//Getting list size from METHOD4
		
	
	
	
	}
	
	
}
