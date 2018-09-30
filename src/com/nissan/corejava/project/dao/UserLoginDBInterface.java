package com.nissan.corejava.project.dao;

import java.sql.*;

import com.nissan.corejava.project.model.User;

public class UserLoginDBInterface
{
	Statement st;
	Connection con;
	public UserLoginDBInterface() 
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinereservation?useSSL=false","root","netflix");
			st = con.createStatement();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	//Verify User credentials from the value in database
	public String verifyUser(String userName,String password)
	{
		try{
		ResultSet rs = st.executeQuery("select password from userlogin where username ='"+userName+"'");
		rs.next();
		if(rs.getString("password").equals(password))
			return userName;
		}catch(SQLException e){}
	
			return null;
		
	}
	
	public void saveUserCredentials(User user,String password) throws SQLException
	{
		st.executeUpdate("insert into userlogin(username,password) values('"+user.getUserName()+"','"+password+"')");
	}
	
	public void editPassword(String name, String password){
		try {
			st.executeUpdate("Update userlogin set password = '"+password+"' where username='"+name+"'");
			System.out.println("Password Updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
