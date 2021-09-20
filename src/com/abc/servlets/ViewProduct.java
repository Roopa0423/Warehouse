package com.abc.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.beans.ProductBean;
import com.abc.beans.EmployeeBean;
import com.abc.dao.ProductDao;
import com.abc.dao.EmployeeDao;
@WebServlet("/ViewProduct")
public class ViewProduct extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Product</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navEmployee.html").include(request, response);
		
		out.println("<div class='container'>");
		
		List<ProductBean> list=ProductDao.view();
		
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>pid</th><th>PName</th><th>Pquantity</th><th>Suppliedr</th></tr>");
		for(ProductBean bean:list){
			out.println("<tr><td>"+bean.getPid()+"</td><td>"+bean.getPname()+"</td><td>"+bean.getPquantity()+"</td><td>"+bean.getSupplied()+"</td><td>// <a href='DeleteProduct?pid="+bean.getPid()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
