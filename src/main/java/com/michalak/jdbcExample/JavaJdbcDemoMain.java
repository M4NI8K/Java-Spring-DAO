package com.michalak.jdbcExample;

import com.michalak.dao.JdbcDaoImpl;
import com.michalak.model.Circle;



/*		Main class 4 normal JCDB USAGE 
 * 
 * 
 */

public class JavaJdbcDemoMain {

	public static void main(String[] args) {
		Circle circle = new  JdbcDaoImpl().getCircle(circleId, name);
		System.out.println(circle.getName());
	}
	
	
	

	
}
