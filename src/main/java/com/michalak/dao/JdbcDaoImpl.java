package com.michalak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.michalak.model.Circle;

@Component // this class gets automatically created because of <context:component-scan 
public class JdbcDaoImpl {	//STANDARD DAO

	public Circle getCircle(int circleId) {

		// Derby connection 
		// 2 lib   derby.jar, derbyclient.jar
		//
		
		
		Connection conn = null;
		try {
			String driver = "org.apache.derby.jdbc.ClientDriver";
			Class.forName(driver).newInstance(); // Driver initialization
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527//db"); // connection
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM circle where id = ?"); // PreparedStatement
																								// with
																								// query
			ps.setInt(1, circleId);

			Circle circle = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				circle = new Circle(circleId, rs.getString("name"));

			}
			rs.close();
			ps.close();
			return circle;
		} catch (Exception e) {
			throw new RuntimeException();
		}finally{
			try {
				conn.close();										//conn close
			} catch (SQLException e) {								
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
