package com.michalak.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.michalak.model.Circle;


// Derby connection jars include  /lib/derby.jar, /libderbyclient.jar



@Component // this class gets automatically created because of <context:component-scan 
public class JdbcDaoImpl {	//STANDARD DAO
// **Varibles**
	//@Autowired //From spring.xml dataSource is created and passed to this object ***DRIVER MENAGMENT***
	private DataSource dataSource;	
		//not in use: private JdbcTemplate jdbcTemplate = new JdbcTemplate() ;// Initialised with DATASOURCE 
	//JdbcTemplate USED FOR PUSHING AND PULING SQL WITH USE OF dataSource(BD Connection tool) uses "?" for queers 
	private JdbcTemplate jdbcTemplate; 
	//LIKE JdbcTemplate but uses ":id" NAMED PARAMETERS not "?" Markers
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; // SQL WITH PARAMETERS INSTEAD ?
	
// not available	private SimpleJdbcTemplate simpleJdbcTemplate; 
	
	
//SETTERS AND GETTERS	
//***dataSource Autowired on setter to automatically when spring.xml dataSource	initialise with itself jdbcTemplate ****
	public DataSource getDataSource() {
		return dataSource;}
	@Autowired //From spring.xml dataSource is created and passed to this object ***DRIVER MENAGMENT***
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate =new JdbcTemplate(dataSource) ;	// JDBC INITIALIZATION initialisation in setDATASOURCE
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource) ;
		this.dataSource = dataSource;
		}

	
//***FETCH METHODS***	
 
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
//METHOD3	Returning Unique Object
	
	public Circle getCircleForId(int circleId)	{
		String sql = "SELECT * FROM circle WHERE id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{circleId}, new CircleMapper());
	}
	
//METHOD4	Returning LIST of Unique(SQL) Objects
	public List<Circle> getAllCircles(){
		String sql = "SELECT * FROM circle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
// INNER CLASS CircleMapper
	private static final class CircleMapper implements RowMapper<Circle> {

		//Returns type Circle defined by implements RowMapper<Circle> 
		//below mapRow method will be as many times as method sql string suggests 
		public Circle mapRow(ResultSet resultSet, int rowNum) throws SQLException { 
			//Above ResultSet resultSet stays the same and int rowNum increase 
			 
			Circle circle = new Circle(); 
			circle.setId(resultSet.getInt("id")); 			//sets int id
			circle.setName(resultSet.getString("name"));	//sets string name
			return circle;
		}//org.springframework.jdbc.core.RowMapper;
	}
	
//UPDATE/INSERT METHODS	
//METHOD1 USING	"?" MARKERS
	public void insertCircle(Circle circle){
		String sql = "INSERT INTO circle(id, name)VALUES (?,?)";
		jdbcTemplate.update(sql,new Object[] {circle.getId(), circle.getName()});
	}
		
//METHOD2 USING ":id" NAMED PARAMETERS	
	public void insertCircle2(Circle circle){
		String sql = "INSERT INTO circle(id, name) VALUES (:id,:name)";		
		//SqlParameterSource class that uses MapSqlParameterSource to define Parameter and its source
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", circle.getId()) 
															.addValue("name", circle.getName()); // .addValue ads another parameter
		namedParameterJdbcTemplate.update(sql, namedParameters);
	}
	
	
	
//CREATE BABLE METHOD	
	public void createTriangleTable (){
		String sql ="CREATE TABLE triangle (id INTIGER, name VARCHAR(50))";
		jdbcTemplate.execute(sql);	
	}
	
	
	
	
/*	***NO NEED FOR below CODE ANYMORE ***
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
