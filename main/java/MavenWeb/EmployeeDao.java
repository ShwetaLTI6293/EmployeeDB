package MavenWeb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

//Data Access Object
 public class EmployeeDao  {
	 
	 public EmployeeDao() {
		// TODO Auto-generated constructor stub
	}

	public void add(Employee emp)  throws DataAccessException{
		 Connection conn=null;
		 PreparedStatement pstmt=null;
		
		 try {
			 InputStream is = 
					 this.getClass().getClassLoader().getResourceAsStream("dev-db.properties");
			
			 Properties dbProperties = new Properties();
			 dbProperties.load(is);
			 
			 String driverClassName=dbProperties.getProperty("driverClassName");
			 String url=dbProperties.getProperty("url");
			 String username=dbProperties.getProperty("username");
			 String password=dbProperties.getProperty("password");
			 
			 Class.forName(driverClassName);
			 conn=DriverManager.getConnection(url,username,password);
			 
			 pstmt=conn.prepareStatement("insert into empMaven values(?,?,?)");
			 pstmt.setString(1,emp.getEmpno());
			 pstmt.setString(2, emp.getName());
			 pstmt.setString(3, emp.getSalary());
			 pstmt.executeUpdate(); //any DML statement
		 }
		 catch(ClassNotFoundException | SQLException | IOException e) {
			throw new DataAccessException("Problem while inserting employee details");
		 }
		 finally {
			 try { pstmt.close(); } catch (Exception e) {  }
			 try { conn.close(); } catch (Exception e) {  }
		 }
	 }
 }
		 
		 
		 
	 
