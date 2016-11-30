package com.michalak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.michalak.model.Circle;


// Derby connection jars include  /lib/derby.jar, /libderbyclient.jar



@Component // this class gets automatically created because of <context:component-scan 
public class JdbcDaoImpl {	//STANDARD DAO

	@Autowired //From spring.xml dataSource is created and passed to this object
	private DataSource datasource;
	
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public Circle getCircle(int circleId) {

		
		
		
		Connection conn = null;
		try {
//STEP1 Connection to database Always Same	
		//	String driver = "org.apache.derby.jdbc.ClientDriver";
		//	Class.forName(driver).newInstance(); 												// Driver initialisation
		//	conn = DriverManager.getConnection("jdbc:derby://localhost:1527//db"); 				// connection
			
		conn = datasource.getConnection();	//above code replace to this by bean in=dataSource in spring.xml
			
//STEP2 PreparedStatement based on query	
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?"); // PreparedStatement
			ps.setInt(1, circleId);
//STEP3 Execiuting the query
			Circle circle = null;
			ResultSet rs = ps.executeQuery();
//STEP4 Parsing thru result set	
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

}
