package com.abc.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.abc.beans.EmployeeBean;

public class EmployeeDao {

		public static int save(EmployeeBean bean){
			//System.out.println(bean.toString());
			
			int status=0;
			try{
				System.out.println("connectionnnnnnnnnnn");
				try(Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("insert into Employee2 (ename, password) values (?,?)")){
//				ps.setInt(1,bean.getEid());
				ps.setString(1,bean.getEname());
				ps.setString(2,bean.getPassword());
				status=ps.executeUpdate();
				con.close();
				}
			}catch(Exception e){System.out.println(e);}
			
			return status;
		}
		public static int update(EmployeeBean bean){
			int status=0;
			try{
				Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("update Employee2 set ename=?,password=? where eid=?");
				//ps.setInt(1,bean.getEid());
				ps.setString(1,bean.getEname());
				ps.setString(2,bean.getPassword());
			
				status=ps.executeUpdate();
				con.close();
				
			}catch(Exception e){System.out.println(e);}
			
			return status;
		}
		public static List<EmployeeBean> view(){
			List<EmployeeBean> list=new ArrayList<EmployeeBean>();
			try{
				try(Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("select * from Employee2")){
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					EmployeeBean bean=new EmployeeBean();
					bean.setEid(rs.getInt("eid"));
					bean.setEname(rs.getString("ename"));
				
					bean.setPassword(rs.getString("password"));
				
					list.add(bean);
				}
				con.close();
				}
			}catch(Exception e){System.out.println(e);}
			
			return list;
		}
		public static EmployeeBean viewById(int eid){
			EmployeeBean bean=new EmployeeBean();
			try{
				try(Connection con=DB.getCon();
				PreparedStatement ps=con.prepareStatement("select * from employee1 where eid=?")){
				ps.setInt(1,eid);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					bean.setEid(rs.getInt(1));
					bean.setEname(rs.getString(2));
					bean.setPassword(rs.getString(3));
					
				}
				con.close();
				}
			}catch(Exception e){System.out.println(e);}
			
			return bean;
		}
////		public static int delete(int eid){
//			int status=0;
//			try{
//				try(Connection con=DB.getCon();
//				PreparedStatement ps=con.prepareStatement("delete from Employee2 where eid=?")){
//				ps.setInt(1,eid);
//				status=ps.executeUpdate();
//				con.close();
//				}
//			}catch(Exception e){System.out.println(e);}
//			
//			return status;
//		}
//
		public static boolean authenticate(String ename,String password){
			boolean status=false;
			try{
				try(Connection con=DB.getCon();
	
				PreparedStatement ps=con.prepareStatement("select * from Employee2 where ename=? and password=?")){
				ps.setString(1,ename);
				ps.setString(2,password);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					status=true;
				}
				con.close();
				}
			}catch(Exception e){System.out.println(e);}
			
			return status;
		}
	}



