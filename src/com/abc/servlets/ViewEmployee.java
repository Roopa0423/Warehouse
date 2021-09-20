package com.abc.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.beans.EmployeeBean;
import com.abc.dao.EmployeeDao;
@WebServlet("/ViewEmployee")
public class ViewEmployee extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Employee</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		List<EmployeeBean> list=EmployeeDao.view();
		
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>EId</th><th>EName</th><th>Password</th></tr>");
		for(EmployeeBean bean:list){
			out.println("<tr><td>"+bean.getEid()+"</td><td>"+bean.getEname()+"</td><td>"+bean.getPassword()+"</td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}
}

