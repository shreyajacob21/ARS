package com.nissan.corejava.project.services;

import java.sql.SQLException;
import java.util.Scanner;

import com.nissan.corejava.project.dao.AdminLoginDBInterface;
import com.nissan.corejava.project.dao.FlightDBInterface;
import com.nissan.corejava.project.dao.FlightScheduleDBInterface;
import com.nissan.corejava.project.model.Flight;
import com.nissan.corejava.project.model.FlightSchedule;

public class AdminActivity implements IAdminActivity{

	public void adminActivities()
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		do{
			System.out.println("1.Add new flight\n2.Edit flight details\n3.Schedule flights\n4.Cancel a flight\n5.Reschedule flight\n6.View Flight Details\n7.View Flight Schedule\n8.View Bookings\n9.Change Password\n10.Exit");
			int choice = input.nextInt();
			switch(choice)
			{
				case 1 : add();break;
				case 2 : edit();break;
				case 3 : schedule();break;
				case 4 : cancel();break;
				case 5 : reschedule();break;
				case 6 : view();break;
				case 7 : viewSchedule();break;
				case 8 : viewBookings();break;
				case 9 : changePassword();break;
				case 10 : return;
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

	//Edit details of flight
	@Override
	public void edit() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter flight number");
		String flightNo = input.nextLine();
		FlightDBInterface flightDb = new FlightDBInterface();
		flightDb.searchFlight(flightNo);
		System.out.println("Enter option to edit :\n1.Airlines\n2.Class\n3.Departure City\n4.Arrival City\n5.Capacity");
		int choice = input.nextInt();
		input.nextLine();
		System.out.println("Enter new value");
		String newValue = input.nextLine();
		switch(choice)
		{
			case 1 : flightDb.editFlightDetails("airlines",newValue,flightNo);break;
			case 2 : flightDb.editFlightDetails("class",newValue,flightNo);break;
			case 3 : flightDb.editFlightDetails("source",newValue,flightNo);break;
			case 4 : flightDb.editFlightDetails("destination",newValue,flightNo);break;
			case 5 : flightDb.editFlightDetails("capacity",newValue,flightNo);break;
			default:System.out.println("Invalid choice");
		}
		
	}

	//get details for reschedule
	@Override
	public void reschedule() 
	{
		FlightSchedule flightSchedule = new FlightSchedule();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter flight number");
		flightSchedule.setName(input.nextLine()); 
		System.out.println("Enter new date");
		flightSchedule.setDate(input.nextLine());
		System.out.println("Enter new departure time");
		flightSchedule.setDeparture(input.nextLine());
		System.out.println("Enter new arrival time");
		flightSchedule.setArrival(input.nextLine());
		System.out.println("Enter new amount");
		flightSchedule.setAmount(input.nextInt());
		FlightScheduleDBInterface flightScheduleDb = new FlightScheduleDBInterface();
		flightScheduleDb.rescheduleFlight(flightSchedule);
	}

	//Cancel a flight schedule
	@Override
	public void cancel() {
		/*FlightSchedule flightSchedule = new FlightSchedule();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter flight ID");
		flightSchedule.setName(input.nextLine());
		System.out.println("Enter Date");
		flightSchedule.setDate(input.nextLine());
		
		try{
			int capacity;
			int availability;
			if(((capacity-availability)/capacity)<0.25)
				flightSchedule.cancelSchedule(id);
		}
		catch(Exception e)
		{}*/
		
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
