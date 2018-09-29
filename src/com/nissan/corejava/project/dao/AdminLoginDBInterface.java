package com.nissan.corejava.project.dao;

import java.sql.*;

public class AdminLoginDBInterface {
	
	Statement  st;
	public AdminLoginDBInterface() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinereservation?useSSL=false","root","netflix");
		st = con.createStatement();
	}
	//Verify admin login credentials
	public boolean verifyAdmin(String userName,String password) throws SQLException, ClassNotFoundException
	{		
		ResultSet rs = st.executeQuery("select * from adminlogin where username = '"+ userName+"'");
		rs.next();
		if(rs.getString(3).equals(password))
			return true;
		else
			return false;
	}
	//Change password of admin
	public void passwordChange(String newPassword)
	{
		try {
			st.executeUpdate("Update adminlogin set password = '"+newPassword+"'");
			System.out.println("Password Updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
