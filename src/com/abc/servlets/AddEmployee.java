package com.abc.servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.beans.EmployeeBean;
import com.abc.dao.EmployeeDao;
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post method called");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Employee</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
//		int eid=Integer.parseInt(request.getParameter("eid"));
		String ename=request.getParameter("ename");
		String password=request.getParameter("password");
		//System.out.println(eid);
		System.out.println(password + ename);
		
		
		EmployeeBean bean=new EmployeeBean(ename, password);
		EmployeeDao.save(bean);
		out.print("<h4>Employee added successfully</h4>");
		request.getRequestDispatcher("addEmployeeform.html").include(request, response);
		
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
