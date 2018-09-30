package com.nissan.corejava.project.services;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.nissan.corejava.project.dao.UserLoginDBInterface;
import com.nissan.corejava.project.dao.UsersDBInterface;
import com.nissan.corejava.project.exceptions.UserDefinedException;
import com.nissan.corejava.project.model.User;

public class UserRegistration {
	//Registers a new user with the details provided
		public static void register()
		{
			try{
				User user = new User();
				String read;
				int flag;
				@SuppressWarnings("resource")
				Scanner input = new Scanner(System.in);
				System.out.println("Enter Name");
				user.setName(input.nextLine());
				System.out.println("Enter Phone Number");
				flag = 0;
				do{
					read = input.nextLine();
					try{
						if(Pattern.matches("[0-9]{10}", read))
							{
								flag = 1;
								user.setPhoneNumber(read);
							}
						else
							throw new UserDefinedException("Input Valid Phone Number");
					}
					catch(UserDefinedException e)
					{
						System.out.println(e);
					}
				}while(flag == 0);
				System.out.println("Enter Email Id");
				flag = 0;
				do{
					read = input.nextLine();
					try{
						if(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", read))
							{
								flag = 1;
								user.setEmailId(read);
							}
						else
							throw new UserDefinedException("Input Valid Email Id");
					}
					catch(UserDefinedException e)
					{
						System.out.println(e);
					}
				}while(flag == 0);
				System.out.println("Enter username");
				read = input.nextLine();
				user.setUserName(read);
				System.out.println("Enter password");
				read = input.nextLine();
				try {
					UserLoginDBInterface userLog = new UserLoginDBInterface();
					userLog.saveUserCredentials(user, read);
					UsersDBInterface usersDb = new UsersDBInterface();
					usersDb.saveUserDetails(user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("User Registered Successfully");
				}
				catch(Exception e) {
					System.out.println("User not registered");
				}
			
		}
}
