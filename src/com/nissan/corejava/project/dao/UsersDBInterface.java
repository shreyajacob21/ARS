package com.nissan.corejava.project.dao;

import java.sql.*;

import com.nissan.corejava.project.utilities.User;

public class UsersDBInterface {
	Statement st;
	Connection con;
	public UsersDBInterface() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinereservation?useSSL=false","root","netflix");
			st = con.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void saveUserDetails(User user)
	{
		try {
			st.executeUpdate("insert into users(name,phoneno,email,username) values('"+user.getName()+"','"+user.getPhoneNumber()+"','"+user.getEmailId()+"','"+user.getUserName()+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
