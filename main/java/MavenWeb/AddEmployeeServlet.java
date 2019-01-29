package MavenWeb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MavenWeb.Employee;

public class AddEmployeeServlet extends HttpServlet {
	
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Employee emp = new Employee();
		emp.setEmpno(request.getParameter("empno"));
	     emp.setName(request.getParameter("name"));
	    emp.setSalary(request.getParameter("salary"));
		
		 EmployeeDao dao=new EmployeeDao();
         try {
			dao.add(emp);
		} catch (DataAccessException e) {
		
			e.printStackTrace();
		}
		
		
    		HttpSession session=request.getSession();
    		session.setAttribute("message","Record added successfully");
    		
    		response.sendRedirect("addEmployee.jsp");
		
		}
    		
	}
    	


