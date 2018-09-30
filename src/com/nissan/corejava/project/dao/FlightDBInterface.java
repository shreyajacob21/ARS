package com.nissan.corejava.project.dao;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

import com.nissan.corejava.project.model.Flight;

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
			e.printStackTrace();
		}
	}

	public void searchFlight(String flightNo) {
		try {
			ResultSet rs = st.executeQuery("select * from flight where flightid = '"+flightNo+"'");
			rs.next();
			System.out.println("Flight No : " +rs.getString("flightid"));
			System.out.println("Airlines : "+rs.getString("airlines"));
			System.out.println("Class : "+rs.getString("class"));
			System.out.println("Departure City : "+rs.getString("source"));
			System.out.println("Arrival City : "+rs.getString("destination"));
			System.out.println("Capacity : "+rs.getInt("capacity"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editFlightDetails(String option, String newValue,String flightNo) {
		try {
			if(option.equals("capacity"))
				{
					int capacity = Integer.parseInt(newValue);
					st.executeUpdate("Update flight set " + option + "='"+ capacity+"' where flightid = '"+flightNo+"'");
				}
			else
				st.executeUpdate("Update flight set " + option + "='"+ newValue+"' where flightid = '"+flightNo+"'");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public List<String> show(String src,String dest){
		List<String> ar =new ArrayList<String>(50);
		try {
			ResultSet rs = st.executeQuery("select flightid from flight where source='"+src+"'"+"AND destination='"+dest+"'");
			while(rs.next())
			{
			ar.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ar;
		
	}
}
