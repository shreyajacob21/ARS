package com.nissan.corejava.project.dao;

import java.sql.*;

import com.nissan.corejava.project.utilities.Flight;

public class FlightDBInterface 
{
	Statement st;
	Connection con;
	public FlightDBInterface() 
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinereservation?useSSL=false","root","netflix");
			st = con.createStatement();
		} catch (ClassNotFoundException| SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addFlight(Flight flight)
	{
		try {
			st.executeUpdate("insert into flight values('"+flight.getName()+"','"+flight.getAirlines()+"','"+flight.getFlightClass()+"','"+flight.getSource()+"','"+flight.getDestination()+"',"+flight.getCapacity()+")");
			System.out.println("Flight added successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void viewFlights()
	{
		System.out.println("FlightId\tAirlines\tClass\tSource\tDestination\tCapacity");
		try {
			ResultSet rs = st.executeQuery("select * from flight");
			while(rs.next())
			{
				System.out.println(rs.getString("flightid")+"\t"+rs.getString("airlines")+"\t"+rs.getString("class")+"\t"+rs.getString("source")+"\t"+rs.getString("destination")+"\t"+rs.getInt("capacity"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
