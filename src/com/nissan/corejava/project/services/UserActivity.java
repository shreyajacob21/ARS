package com.nissan.corejava.project.services;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.nissan.corejava.project.dao.FlightDBInterface;
import com.nissan.corejava.project.dao.FlightScheduleDBInterface;
import com.nissan.corejava.project.dao.UserLoginDBInterface;
import com.nissan.corejava.project.dao.UsersDBInterface;
import com.nissan.corejava.project.exceptions.UserDefinedException;
public class UserActivity {
		static Scanner sc=new Scanner(System.in);
		static UsersDBInterface ub;
		
		static String userName;
	public static void main(String userNm) {
		userName=userNm;
		ub=new UsersDBInterface();
		System.out.println("1.Book a flight 2.View Bookings 3.Cancel Bookings 4.Edit Profile ");
		int ch=sc.nextInt();
		sc.nextLine();
		switch(ch){
		case 1:
			bookAFlight();
			break;
		case 2:
			viewBookings();
			break;
		case 3:
			cancelBookings();
			break;
		case 4:
			editProfile();
			break;
		}
	}
	public static void bookAFlight(){
		System.out.println("Enter source");
		String source=sc.nextLine();
		System.out.println("Enter destination");
		String dest=sc.nextLine();
		//To get the list of flights having the specified itinerary
		FlightDBInterface fdb=new FlightDBInterface();
		List<String> list=fdb.show(source, dest);
		System.out.println("Enter the date : (dd/MM/yyyy)");
		String date=sc.nextLine();
		System.out.println("No of passengers : ");
		int no=sc.nextInt();
		sc.nextLine();
		FlightScheduleDBInterface sdb=new FlightScheduleDBInterface();
		String fl=sdb.show(date,list,no);
	}
	public static void viewBookings(){
		
	}
	public static void cancelBookings(){
		
	}
	public static void editProfile(){
		UserLoginDBInterface ulb=new UserLoginDBInterface();
		int ch=0,flag=0;
		do{
		System.out.println("1.Edit Name 2.Edit MailId 3.Edit PhoneNo 4.Edit Password 5.Back");
		ch=sc.nextInt();
		switch(ch){
		case 1:
			String name=sc.nextLine();
			ub.editName(name,userName);
			break;
		case 2:
			flag = 0;
			do{
				String read = sc.nextLine();
				try{
					if(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", read))
						{
							flag = 1;
							ub.editMailId(read,userName);	
						}
					else
						throw new UserDefinedException("Input Valid Email Id");
				}
				catch(UserDefinedException e)
				{
					System.out.println(e);
				}
			}while(flag == 0);
			break;
		case 3:
			flag = 0;
			do{
			String	read = sc.nextLine();
				try{
					if(Pattern.matches("[0-9]{10}", read))
						{
							flag = 1;
							ub.editPhoneNo(read,userName);
						}
					else
						throw new UserDefinedException("Input Valid Phone Number");
				}
				catch(UserDefinedException e)
				{
					System.out.println(e);
				}
			}while(flag == 0);
	
			break;
		case 4:
			String pass=sc.nextLine();
			ulb.editPassword(pass,userName);
			break;
		case 5:
			break;
		default:
			System.out.println("Invalid choice!!");
		}
		}while(ch!=5);
	}
}