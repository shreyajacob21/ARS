package com.nissan.corejava.project.dao;

import java.sql.*;

import com.nissan.corejava.project.utilities.User;

public class UserLoginDBInterface
{
	Statement st;
	Connection con;
	public UserLoginDBInterface() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinereservation?useSSL=false","root","netflix");
		st = con.createStatement();
	}
	//Verify User credentials from the value in database
	public boolean verifyUser(String userName,String password)
	{
		try{
		ResultSet rs = st.executeQuery("select password from userlogin where username ='"+userName+"'");
		rs.next();
		if(rs.getString("password").equals(password))
			return true;
		}catch(SQLException e){}
	
			return false;
		
	}
	
	public void saveUserCredentials(User user,String password) throws SQLException
	{
		st.executeUpdate("insert into userlogin(username,password) values('"+user.getUserName()+"','"+password+"')");
	}
	
}
