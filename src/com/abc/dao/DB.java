package com.abc.dao;

import java.sql.*;

public class DB {

	public static Connection getCon() {
		Connection con =null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("checking");

		
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse?useSSL=false", "root",null);
			System.out.println("once again checking");
			
		} catch (Exception e) {
			System.out.println(e);
			//System.out.println("maria db not found");
		}
		return con;
	}
}
