package com.michalak.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcTemplateImpl extends JdbcDaoSupport{
	
	
	
	//METHOD1 passes sql String and return type to jdbcTemplate.queryForObject 
		public int getCircleCount(){
			String sql = "Select COUNT(*) FROM circle";
		//	jdbcTemplate.setDataSource(getDatasource()); 	// JDBC INITIALIZATION initialisation in setDATASOURCE 
			return this.getJdbcTemplate().queryForObject(sql, Integer.class); // jdbcTemplate.queryforint() depricated
		}
}
