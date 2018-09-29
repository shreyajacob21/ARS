package com.nissan.corejava.project.utilities;

import java.sql.SQLException;

import com.nissan.corejava.project.dao.*;

public class AdminLogin extends Login{
	static int count = 0;
	@Override
	public boolean verify() 
	{
		
		boolean result=false;
		try {
			AdminLoginDBInterface adm = new AdminLoginDBInterface();
			result = adm.verifyAdmin(getUserName(),getPassword());
			
		} catch (ClassNotFoundException | SQLException e) {
			/*e.printStackTrace();*/

		}
		return result;
	}

}
