package com.abc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.beans.ProductBean;
import com.abc.dao.ProductDao;
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		System.out.println("do post method");
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Book Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		request.getRequestDispatcher("navEmployee.html").include(request, response);
		
		out.println("<div class='container'>");
		
		System.out.println(request.getParameter("productid"));
		int pid=Integer.parseInt(request.getParameter("productid"));
		String pname=request.getParameter("name");
		Double pquantity=Double.parseDouble(request.getParameter("quantity"));
	//Double  supplied=Double.parseDouble(request.getParameter("supplied"));
		Double supplied=Double.parseDouble("0");
		
		ProductBean bean=new ProductBean(pname,pid,pquantity,supplied);
		int i=ProductDao.save(bean);
		if(i>0){
			out.println("<h3>Product saved successfully</h3>");
		}
		request.getRequestDispatcher("addproductform.html").include(request, response);
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
