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
@WebServlet("/EditEmployee")
public class EditEmployee extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response, int id) throws ServletException, IOException {
		String id1=request.getParameter("eid");
		int eid=Integer.parseInt("eid");
		
		//int eid=Integer.parseInt(id);
		String ename=request.getParameter("ename");
		String password=request.getParameter("password");
		
		EmployeeBean bean=new EmployeeBean(eid,ename,  password);
		EmployeeDao.update(bean);
		response.sendRedirect("ViewEmployee");
	}

}
