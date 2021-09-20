package com.abc.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.beans.ProductBean;
import com.abc.beans.SupplyProductBean;
import com.abc.dao.ProductDao;
@WebServlet("/SupplyProduct")
public class SupplyProduct extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post called");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Product Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navEmployee.html").include(request, response);
		
		out.println("<div class='container'>");
		String id1=request.getParameter("productid");
		int pid=Integer.parseInt(id1);
		String id2=request.getParameter("supplierid");
		int sid=Integer.parseInt(id2);
		
		String pname=request.getParameter("productName");
		String sname=request.getParameter("suppliername");
		Date date = Date.valueOf(request.getParameter("date"));
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // pattern specific
//		LocalDate date = LocalDate.parse(dateString, formatter);
		String sstatus = request.getParameter("productStatus");
		int quantity=Integer.parseInt(request.getParameter("productQuantity"));
		
		
		System.out.println("hello");
		SupplyProductBean bean=new SupplyProductBean(sname,pname,sid,pid,date,sstatus,quantity,quantity);
		int i=ProductDao.suppliedProduct(bean);
		if(i>0){
			out.println("<h3>Product supplied successfully</h3>");
		}else{
			out.println("<h3>Sorry, unable to supply product.</h3><p>We may have sortage of products. Kindly visit later.</p>");
		}
		out.println("</div>");
		
		request.getRequestDispatcher("supplyproductform.html").include(request, response);
		out.println("</div>");
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
