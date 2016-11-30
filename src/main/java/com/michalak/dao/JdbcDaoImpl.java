package com.michalak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.michalak.model.Circle;


// Derby connection jars include  /lib/derby.jar, /libderbyclient.jar



@Component // this class gets automatically created because of <context:component-scan 
public class JdbcDaoImpl {	//STANDARD DAO
// **Object Varibles**
	//@Autowired //From spring.xml dataSource is created and passed to this object ***DRIVER MENAGMENT***
	private DataSource dataSource;	
	//private JdbcTemplate jdbcTemplate = new JdbcTemplate() ;//
	private JdbcTemplate jdbcTemplate; 
	
//SETTERS AND GETTERS	
//***dataSource Autowired on setter to automatically when spring.xml dataSource	initialize with itself jdbcTemplate ****
	public DataSource getDataSource() {
		return dataSource;}
	@Autowired //From spring.xml dataSource is created and passed to this object ***DRIVER MENAGMENT***
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate =new JdbcTemplate(dataSource) ;	// JDBC INITIALIZATION initialisation in setDATASOURCE
		this.dataSource = dataSource;
		}

	
	
 
//METHOD1 passes sql String and return type to jdbcTemplate.queryForObject 
	public int getCircleCount(){
		String sql = "Select COUNT(*) FROM circle";
	//	jdbcTemplate.setDataSource(getDatasource()); 	// JDBC INITIALIZATION initialisation in setDATASOURCE 
		return jdbcTemplate.queryForObject(sql,new Object[]{}, Integer.class); // jdbcTemplate.queryforint() depricated
	}
//METHOD2 as above + Object array(new Object[]{circleId}{circleId} with circleId parameter = ? from sql Sting )
	public String getCircleName(int circleId) {
		String sql = "SELECT name FROM circle WHERE id = ?";			
		return jdbcTemplate.queryForObject(sql,new Object[]{circleId}, String.class);
	}
//METHOD3	
	
	
	
/*	***NO NEED FOR BELOV CODE ANYMORE ***
	public Circle getCircle(int circleId) {

		Connection conn = null;
		try {
//STEP1 Connection to database Always Same	***DRIVER***
		//	String driver = "org.apache.derby.jdbc.ClientDriver";
		//	Class.forName(driver).newInstance(); 												// Driver initialisation
		//	conn = DriverManager.getConnection("jdbc:derby://localhost:1527//db"); 				// connection
			
		conn = datasource.getConnection();	//above code replace to this by bean in=dataSource in spring.xml
			
//STEP2 PreparedStatement based on query	***RE EXECUTION***
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?"); // PreparedStatement
			ps.setInt(1, circleId);
//STEP3 Execiuting the query                ***EXECUTION***
			Circle circle = null;
			ResultSet rs = ps.executeQuery();
//STEP4 Parsing thru result set				***POST EXECUTION***
			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));

			}
	//Closing		
			rs.close();
			ps.close();
			return circle;
		} catch (Exception e) {
			throw new RuntimeException();
		}finally{
			try {
	//Closing			
				conn.close();										//conn close
			} catch (SQLException e) {								
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
*/
	

}
