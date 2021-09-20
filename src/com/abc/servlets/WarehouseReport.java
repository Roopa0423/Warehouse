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
import com.abc.beans.SupplyProductBean;
import com.abc.dao.ProductDao;
@WebServlet("/WarehouseReport")
public class WarehouseReport extends HttpServlet {
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
		request.getRequestDispatcher("navadmin.html").include(request, response);
		
		
		
		out.println("<div class='container'>");
		List<Integer> space = ProductDao.getAvailableSpace();
		out.println("<h1>Warehouse Limit :"+space.get(0)+" </h1><h1>Space Left : "+space.get(1)+"</h1>");
		List<SupplyProductBean> list=ProductDao.viewSuppliedProducts();
		
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<tr><th>product id</th><th>Product Name</th><th>Supplier id</th><th>Supplier Name</th><th>Status </th><th>Supply Date</th><th>Supplied Quantity</th><th>Remaining Quantity</th></tr>");
		for(SupplyProductBean bean:list){
			out.println("<tr><td>"+bean.getPid()+"</td><td>"+bean.getPname()+"</td><td>"+bean.getSid()+"</td><td>"+bean.getSname()+"</td><td>"+bean.getSstatus()+"</td><td>"+bean.getDate()+"</td><td>"+bean.getQuantity()+"</td><td>"+bean.getRemainingQuantity()+"</td></tr>");
		}
		out.println("</table>");
		out.println("<a class='btn btn-primary' href='GenerateReport'>Generate Report</a>");
		
		out.println("</div>");
		
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
}
