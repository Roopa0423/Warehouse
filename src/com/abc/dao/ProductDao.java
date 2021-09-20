package com.abc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.abc.beans.ProductBean;
import com.abc.beans.SellProductBean;
import com.abc.beans.SupplyProductBean;
import com.abc.beans.EmployeeBean;

public class ProductDao {

	public static int save(ProductBean bean) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("insert into product values(?,?,?,?)");
			ps.setInt(1, bean.getPid());
			ps.setString(2, bean.getPname());
			ps.setDouble(3, bean.getPquantity());
			ps.setDouble(4, bean.getSupplied());

			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static List<ProductBean> view() {
		List<ProductBean> list = new ArrayList<ProductBean>();
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from product");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setPid(rs.getInt("pid"));
				bean.setPname(rs.getString("pname"));
				bean.setPquantity(rs.getDouble("pquantity"));
				bean.setSupplied(rs.getDouble("supplied"));

				list.add(bean);
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public static int delete(int pid) {
		int status = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("delete from product where pid=?");
			ps.setInt(1, pid);
			status = ps.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static int getSuppllied(int pid) {
		int issued = 0;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from product where pid=?");
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				issued = rs.getInt("issued");
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return issued;
	}

	public static boolean checkSupply(int pid) {
		boolean status = false;
		try {
			Connection con = DB.getCon();
			PreparedStatement ps = con.prepareStatement("select * from product where pid=? and pquantity>supplied");
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				status = true;
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public static int suppliedProduct(SupplyProductBean bean) {
		int pid = bean.getPid();
		// boolean checkstatus=checkSupply(pid);
		boolean checkstatus = true;
		System.out.println("Check status: " + checkstatus);
		if (checkstatus) {
			int status = 0;
			try {

				try (Connection con = DB.getCon();
						PreparedStatement ps = con
								.prepareStatement("insert into supplyproduct values(?,?,?,?,?,?,?,?)")) {
					ps.setInt(1, bean.getPid());
					ps.setInt(2, bean.getSid());
					ps.setString(3, bean.getPname());
					ps.setString(4, bean.getSname());
					// java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
					ps.setDate(5, bean.getDate());
					ps.setString(6, bean.getSstatus());
					ps.setInt(7, bean.getQuantity());
					ps.setInt(8, bean.getRemainingQuantity());

					status = ps.executeUpdate();
					// if(status>0){
					// PreparedStatement ps2=con.prepareStatement("update supplyproduct set
					// supplied=? where pid=?");
					// ps2.setInt(1,getSuppllied(pid)+1);
					// ps2.setInt(2,pid);
					// status=ps2.executeUpdate();
					// }
					updateSpace(1, bean.getQuantity());
					con.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			return status;
		} else {
			return 0;
		}
	}

	public static int soldProduct(int pid, int sid, int quantity, Date date) {
		int status = 0;
		boolean available = false;
		try {
			available = checkQuantity(pid, quantity);
			if (available) {
				try (Connection con = DB.getCon();
						PreparedStatement ps = con.prepareStatement("insert into sellproduct1 values(?,?,?,?)")) {
					ps.setInt(1, pid);
					ps.setInt(2, sid);
					ps.setInt(3, quantity);
					ps.setDate(4, date);

					status = ps.executeUpdate();
					updateSpace(2, quantity);
					// if (status > 0) {
					// PreparedStatement ps2 = con.prepareStatement("update prduct set supplied=?
					// where pid=?");
					// ps2.setInt(1, getSuppllied(pid) - 1);
					// ps2.setInt(2, pid);
					// status = ps2.executeUpdate();
					// }
					con.close();
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	private static boolean checkQuantity(int pid, int quantity) {
		int existingQuantity = 0;
		int status = 0;
		try {
			try (Connection con = DB.getCon();
					PreparedStatement ps = con
							.prepareStatement("select remainingquantity from supplyproduct where pid=?")) {
				ps.setInt(1, pid);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					existingQuantity = rs.getInt(1);
				}
				if (existingQuantity - quantity >= 0) {
					int updateQuantity = existingQuantity - quantity;
					PreparedStatement ps1 = con
							.prepareStatement("update supplyproduct set remainingquantity =? where pid=?");
					ps1.setInt(1, updateQuantity);
					ps1.setInt(2, pid);
					status = ps1.executeUpdate();

					return true;
				}
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	private static void updateSpace(int productAdded, int quantity) {
		try {
			try (Connection con = DB.getCon(); PreparedStatement ps = con.prepareStatement("select * from space")) {
				// update space set max_space_left = ?
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					int maxSpace = rs.getInt("max_space_left");
					if (productAdded == 1) {
						maxSpace -= quantity;
					}
					if (productAdded == 2) {
						maxSpace += quantity;
					}
					PreparedStatement ps1 = con.prepareStatement("update space set max_space_left= ?");
					ps1.setInt(1, maxSpace);
					int status = ps1.executeUpdate();

					// ps =
				}
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static List<SupplyProductBean> viewSuppliedProducts() {
		List<SupplyProductBean> list = new ArrayList<SupplyProductBean>();
		try {
			try (Connection con = DB.getCon();
					PreparedStatement ps = con.prepareStatement("select * from supplyproduct order by sdate desc")) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					SupplyProductBean bean = new SupplyProductBean();
					bean.setPid(rs.getInt("pid"));
					bean.setSid(rs.getInt("sid"));
					bean.setPname(rs.getString("pname"));
					bean.setSname(rs.getString("sname"));
					bean.setDate(rs.getDate("sdate"));
					bean.setSstatus(rs.getString("sstatus"));
					bean.setQuantity(rs.getInt("pquantity"));
					bean.setRemainingQuantity(rs.getInt("remainingquantity"));
					list.add(bean);
				}
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public static List<SellProductBean> viewSoldProducts() {
		List<SellProductBean> list = new ArrayList<SellProductBean>();
		try {
			try (Connection con = DB.getCon();
					PreparedStatement ps = con.prepareStatement("select * from sellproduct1 order by date desc")) {
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					SellProductBean bean = new SellProductBean();
					bean.setPid(rs.getInt("pid"));
					bean.setSeid(rs.getInt("seid"));
					bean.setPquantity(rs.getInt("pquantity"));
					bean.setDate(rs.getDate("date"));
					list.add(bean);
				}
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	/*
	 * public static int update(LibrarianBean bean){ int status=0; try{ Connection
	 * con=DB.getCon(); PreparedStatement ps=con.
	 * prepareStatement("update e_librarian set name=?,email=?,password=?,mobile=? where id=?"
	 * ); ps.setString(1,bean.getName()); ps.setString(2,bean.getEmail());
	 * ps.setString(3,bean.getPassword()); ps.setLong(4,bean.getMobile());
	 * ps.setInt(5,bean.getId()); status=ps.executeUpdate(); con.close();
	 * 
	 * }catch(Exception e){System.out.println(e);}
	 * 
	 * return status; } public static LibrarianBean viewById(int id){ LibrarianBean
	 * bean=new LibrarianBean(); try{ Connection con=DB.getCon(); PreparedStatement
	 * ps=con.prepareStatement("select * from e_librarian where id=?");
	 * ps.setInt(1,id); ResultSet rs=ps.executeQuery(); if(rs.next()){
	 * bean.setId(rs.getInt(1)); bean.setName(rs.getString(2));
	 * bean.setPassword(rs.getString(3)); bean.setEmail(rs.getString(4));
	 * bean.setMobile(rs.getLong(5)); } con.close();
	 * 
	 * }catch(Exception e){System.out.println(e);}
	 * 
	 * return bean; }
	 */

	public static List<Integer> getAvailableSpace() {
		List<Integer> list = new ArrayList<Integer>();
		try {
			try (Connection con = DB.getCon(); PreparedStatement ps = con.prepareStatement("select * from space")) {
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					list.add(rs.getInt("max_space"));
					list.add(rs.getInt("max_space_left"));
				}
				con.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

}
