package com.abc.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.beans.SupplyProductBean;
import com.abc.dao.ProductDao;
@WebServlet("/SellProduct")
public class SellProduct extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>sold Product</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navEmployee.html").include(request, response);
		
		out.println("<div class='container'>");
		String ppid=request.getParameter("productid");
		int pid=Integer.parseInt(ppid);
		String ssid=request.getParameter("sellerid");
		int sid=Integer.parseInt(ssid);
		String quantity = request.getParameter("productquantity");
		int squantity= Integer.parseInt(quantity);
		String date = request.getParameter("date");
		Date sdate =Date.valueOf(date);
		
		
	int i=ProductDao.soldProduct(pid,sid,squantity,sdate);
		if(i>0){
			out.println("<h3>product sold successfully</h3>");
		}else{
			out.println("<h3>Sorry, unable to sell product.</h3><p>We may have sortage of product. Kindly visit later.</p>");
		}
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
