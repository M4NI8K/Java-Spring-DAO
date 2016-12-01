package com.michalak.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //Stereotype like @Component "more specyfic"
public class HibernateDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	//METHOD1 passes sql String and return type to jdbcTemplate.queryForObject 
	public int getCircleCount(){
		String hql="select count(c) from Circle c";
		Query query = getSessionFactory().openSession().createQuery(hql);
	
		return ((Long) query.uniqueResult()).intValue();
		
		
	}
}
