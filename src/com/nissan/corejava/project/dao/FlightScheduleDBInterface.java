package com.nissan.corejava.project.dao;

import java.sql.*;

import com.nissan.corejava.project.utilities.FlightSchedule;

public class FlightScheduleDBInterface {

	Statement st;
	Connection con;
	public FlightScheduleDBInterface() 
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinereservation?useSSL=false","root","netflix");
			st = con.createStatement();
		} catch (ClassNotFoundException| SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addFlightSchedule(FlightSchedule flightSchedule)
	{
		try {
			ResultSet rs =st.executeQuery("select * from flight where flightid = '"+flightSchedule.getName()+"'");
			rs.next();
			flightSchedule.setSeatAvailability(rs.getInt("capacity"));
			st.executeUpdate("insert into flightschedule(flightid,date,departuretime,arrivaltime,availability,amount) values('"+flightSchedule.getName()+"','"+flightSchedule.getDate()+"','"+flightSchedule.getDeparture()+"','"+flightSchedule.getArrival()+"','"+flightSchedule.getSeatAvailability()+"',"+flightSchedule.getAmount()+")");
			System.out.println("Flight Scheduled");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewFlightSchedule()
	{
		System.out.println("FlightId\tDate\tDeparture Time\tArrival Time\tAvailabilty\tAmount");
		try {
			ResultSet rs = st.executeQuery("select * from flightschedule");
			while(rs.next())
			{
				System.out.println(rs.getString("flightid")+"\t"+rs.getString("date")+"\t"+rs.getString("departuretime")+"\t"+rs.getString("arrivaltime")+"\t"+rs.getString("availability")+"\t"+rs.getInt("amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rescheduleFlight(FlightSchedule flightSchedule) {
		try {
			st.executeUpdate("Update flightschedule set date = '"+flightSchedule.getDate()+"' where flightid = '"+flightSchedule.getName()+"'");
			st.executeUpdate("Update flightschedule set departuretime = '"+flightSchedule.getDeparture()+"'where flightid = '"+flightSchedule.getName()+"'");
			st.executeUpdate("Update flightschedule set arrivaltime = '"+flightSchedule.getArrival()+"'where flightid = '"+flightSchedule.getName()+"'");
			st.executeUpdate("Update flightschedule set amount = '"+flightSchedule.getAmount()+"'where flightid = '"+flightSchedule.getName()+"'");
			System.out.println("Flight Rescheduled");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
