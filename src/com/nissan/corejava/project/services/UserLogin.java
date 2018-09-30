package com.nissan.corejava.project.services;


import com.nissan.corejava.project.dao.UserLoginDBInterface;


public class UserLogin extends Login {
	static int count = 0;
	@Override
	public String verify()
	{
		String result=null;
		UserLoginDBInterface user = new UserLoginDBInterface();
		result = user.verifyUser(getUserName(),getPassword());
		return result;
	}
}
