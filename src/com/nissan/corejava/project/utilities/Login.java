package com.nissan.corejava.project.utilities;

import java.util.Scanner;

public abstract class Login {
	
	private String userName,password;
	//input user name and password 
	public void inputUserCredentials()
	{
		 @SuppressWarnings("resource")
		 Scanner input = new Scanner(System.in);
		 System.out.println("Input username");
		 setUserName(input.nextLine());
		 System.out.println("Input password");
		 setPassword(input.nextLine());
	}
	public abstract boolean verify() ;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
