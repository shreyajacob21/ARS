package com.nissan.corejava.project.services;

import java.sql.SQLException;
import java.util.Scanner;

import com.nissan.corejava.project.dao.AdminLoginDBInterface;
import com.nissan.corejava.project.dao.FlightDBInterface;
import com.nissan.corejava.project.dao.FlightScheduleDBInterface;
import com.nissan.corejava.project.utilities.Flight;
import com.nissan.corejava.project.utilities.FlightSchedule;
import com.nissan.corejava.project.utilities.IAdminActivity;

public class AdminActivity implements IAdminActivity{

	public void adminActivities()
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		do{
			System.out.println("1.Add new flight\n2.Schedule flights\n3.Cancel a flight\n4.Reschedule flight\n5.View Flight Details\n6.View Flight Schedule\n7.View Bookings\n8.Change Password\n9.Exit");
			int choice = input.nextInt();
			switch(choice)
			{
				case 1 : add();break;
				case 2 : schedule();break;
				case 3 : cancel();break;
				case 4 : reschedule();break;
				case 5 : view();break;
				case 6 : viewSchedule();break;
				case 7 : viewBookings();break;
				case 8 : changePassword();break;
				case 9 : return;
			}
		}while(true);
	}
	
	private void viewBookings() {
		
		
	}
	
	//get new password
	private void changePassword() 
	{
		System.out.println("Enter new Password");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String password = input.nextLine();
		AdminLoginDBInterface adminLoginDb;
		try {
			adminLoginDb = new AdminLoginDBInterface();
			adminLoginDb.passwordChange(password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		
	}
	
	//get details of new flight
	@Override
	public void add() 
	{
		Flight flight = new Flight();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter fligt number");
		flight.setName(input.nextLine());
		System.out.println("Enter airlines");
		flight.setAirlines(input.nextLine());
		System.out.println("Enter class(domestic/international)");
		flight.setFlightClass(input.nextLine());
		System.out.println("Enter Departure City");
		flight.setSource(input.nextLine());
		System.out.println("Enter Arrival City");
		flight.setDestination(input.nextLine());
		System.out.println("Enter Total capacity");
		flight.setCapacity(input.nextInt());
		FlightDBInterface flightDb= new FlightDBInterface();
		flightDb.addFlight(flight);
		
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	//get details for reschedule
	@Override
	public void reschedule() {
		FlightSchedule flightSchedule = new FlightSchedule();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter flight number");
		flightSchedule.setName(input.nextLine()); 
		System.out.println("Enter new date");
		flightSchedule.setDate(input.nextLine());
		System.out.println("Enter new departure time");
		flightSchedule.setDate(input.nextLine());
		System.out.println("Enter new arrival time");
		flightSchedule.setArrival(input.nextLine());
		System.out.println("Enter new amount");
		flightSchedule.setAmount(input.nextInt());
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	//Get all details to schedule a flight
	@Override
	public void schedule() 
	{
		FlightSchedule flightSchedule = new FlightSchedule();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter flight number");
		flightSchedule.setName(input.nextLine());
		System.out.println("Enter date");
		flightSchedule.setDate(input.nextLine());
		System.out.println("Enter departure time");
		flightSchedule.setDeparture(input.nextLine());
		System.out.println("Enter arrival time");
		flightSchedule.setArrival(input.nextLine());
		System.out.println("Enter Amount");
		flightSchedule.setAmount(input.nextInt());
		FlightScheduleDBInterface flightScheduleDb = new FlightScheduleDBInterface();
		flightScheduleDb.addFlightSchedule(flightSchedule);
	}

	@Override
	public void viewSchedule() {
		FlightScheduleDBInterface flightSchedules = new FlightScheduleDBInterface();
		flightSchedules.viewFlightSchedule();
		
	}

	@Override
	public void view() {
		FlightDBInterface flightDb = new FlightDBInterface();
		flightDb.viewFlights();
		
	}

}
