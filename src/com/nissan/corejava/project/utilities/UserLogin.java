package com.nissan.corejava.project.utilities;

import java.sql.SQLException;
import com.nissan.corejava.project.dao.UserLoginDBInterface;


public class UserLogin extends Login {
	static int count = 0;
	@Override
	public boolean verify()
	{
		boolean result=false;
		try {
			UserLoginDBInterface user = new UserLoginDBInterface();
			result = user.verifyUser(getUserName(),getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){ }
		return result;
	}
}
