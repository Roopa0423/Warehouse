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
import com.abc.beans.SellProductBean;
import com.abc.beans.SupplyProductBean;
import com.abc.dao.ProductDao;
@WebServlet("/ViewSoldProducts")
public class ViewSoldProducts extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Supply Product</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navEmployee.html").include(request, response);
		
		out.println("<div class='container'>");
		
		List<SellProductBean> list=ProductDao.viewSoldProducts();
		
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>Product Id</th><th>Seller Id</th><th>Sold Quantity</th><th>Date Sold</th></tr>");
		for(SellProductBean bean:list){
			out.println("<tr><td>"+bean.getPid()+"</td><td>"+bean.getSeid()+"</td><td>"+bean.getPquantity()+"</td><td>"+bean.getDate()+"</td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
