package com.nissan.corejava.project.services;

import java.util.Scanner;
import com.nissan.corejava.project.exceptions.UserDefinedException;

public class Initiator {

	public static void main(String[] args)
	{
		boolean logged;
		String success;
		int choice;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Airline Reservation System");
		do{
			System.out.println("1.Admin\n2.User\n3.Exit");
			choice = input.nextInt();
			switch(choice)
			{
				case 1 : AdminLogin adminLogin = new AdminLogin();
						 do{
							 adminLogin.inputUserCredentials();
							 success = adminLogin.verify();
							 try{
								 if(success!=null)
								 {
									 System.out.println("Login SUCCESSFUL!");
									 AdminLogin.count = 0;
									 logged = true;
									 AdminActivity adminActivity = new AdminActivity();
									 adminActivity.adminActivities();
								 }
								 else
								 {
									 throw new UserDefinedException("Invalid User Credentials");
								 }
							 }
							 catch(UserDefinedException e)
							 {
								 logged = false;
								 System.out.println(e);
								 AdminLogin.count++;
							 }
						 }while(AdminLogin.count<3&&(!logged));
						 break;
				case 2 : System.out.println("1.Existing user\n2.New User");
				 		 choice = input.nextInt();
				 		 UserLogin userLogin = new UserLogin();
				 		 if(choice == 1)
				 		 {
				 			 do{
				 				 userLogin.inputUserCredentials();
					 			 
								 success = userLogin.verify();
								 try{
									 if(success!=null)
									 {
										 System.out.println("Login SUCCESSFUL!");
										 UserActivity.main(success);
										 UserLogin.count = 0;
										 logged = true;
									 }
									 else
									 {
										 throw new UserDefinedException("Invalid User Credentials");
									 }
								 }
								 catch(UserDefinedException e){
									 System.out.println(e);
									 UserLogin.count++;
									 logged = false;
								 }
				 			 }while(UserLogin.count<3&&(!logged));
				 		 }
				 		 else
				 		 {
				 			 UserRegistration.register();
				 		 }	
				 		 break;
				case 3 : break;
				default: System.out.println("Invalid choice");
			}
		}while(choice!=3);
	}

}
