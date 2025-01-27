package com.theatre;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeatBooking implements SeatBookingInterface, AdminOperations{
	
	public Connection connection;
	List<String> availableSeats;
	Set<String> totalSeats;
	Set<String> theatreSeats;


	
	public SeatBooking() {
		connection = DbConnection.getConnection();
		availableSeats = new ArrayList<String>();
		totalSeats = new HashSet<>();
		this.theatreSeats = new HashSet<String>();

	}
	
	// Method to show available seats
	@Override
	public void showAvailableSeats() {
		String query = "SELECT * from seats WHERE is_booked=false ORDER BY seat_number";
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
			try {
				while(resultSet.next()) {
					availableSeats.add(resultSet.getString("seat_number"));
					System.out.print(resultSet.getString("seat_number") + " | ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(availableSeats.isEmpty()) {
				System.out.println("No seats available.");
			}
		
			
		
	}
	
	// Method to book a seat
	@Override
	public void bookSeat(String seatNumber) {
//		if(availableSeats.contains(seatNumber)) {
			String query = "UPDATE seats SET is_booked = true WHERE seat_number = ?";
			PreparedStatement preparedStatement = null;
			
			int rowsUpdated = 0;

			try {
				preparedStatement = connection.prepareStatement(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.setString(1, seatNumber);
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			
			try {
				rowsUpdated = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(rowsUpdated > 0) {
				System.out.println("Seat booked sucessfully.");
				System.out.println("Your Booking Details: " + seatNumber + " has been booked for you. Enjoy your movie.");
				availableSeats.remove(seatNumber);
				
			}else {
				System.out.println("Seat is already booked.");
			}
//		}else {
//			System.out.println("There is no such seat.");
//		}
		
	}
	
//	@Override
//	public void bookSeat(String seatNumber) {
//	    // Debug: Check available seats
//	    System.out.println("Available seats: " + availableSeats);
//	    
//	    if (availableSeats.contains(seatNumber)) {
//	        String query = "UPDATE seats SET is_booked = true WHERE seat_number = ?";
//	        PreparedStatement preparedStatement = null;
//	        
//	        int rowsUpdated = 0;
//	        
//	        try {
//	            preparedStatement = connection.prepareStatement(query);
//	            preparedStatement.setString(1, seatNumber);
//	            rowsUpdated = preparedStatement.executeUpdate();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//
//	        if (rowsUpdated > 0) {
//	            System.out.println("Seat booked successfully.");
//	            System.out.println("Your Booking Details: " + seatNumber + " has been booked for you. Enjoy your movie.");
//	            availableSeats.remove(seatNumber);
//	        } else {
//	            System.out.println("Seat is already booked.");
//	        }
//	    } else {
//	        System.out.println("There is no such seat.");
//	    }
//	}
//	
	
	// Method for admin to add a new seat
	@Override
	public void addSeat(String seatNumber) {
		
		String query = "select * from seats where is_booked = false";
		Connection connection = DbConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(resultSet.next()) {
				theatreSeats.add(resultSet.getString("seat_number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!theatreSeats.contains(seatNumber)) {
			String seatQuery = "INSERT into seats (seat_number, is_booked) values (?, false)";
			PreparedStatement preparedStatement1 = null;
			try {
				preparedStatement1 = connection.prepareStatement(seatQuery);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				preparedStatement1.setString(1, seatNumber);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				preparedStatement1.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
//			String query2 = "SELECT * from seats";
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(query2);
//			
//			while(resultSet.next()) {
				totalSeats.add(seatNumber);
				availableSeats.add(seatNumber);
//			}
			System.out.println("Seat " + seatNumber + " added successfully.");
		}else {
			System.out.println("Seat already present.");
		}
		
	}
	
	@Override
	public void cancelSeat(String seatNumber) {
			String query = "UPDATE seats SET is_booked = false WHERE seat_number = ?";
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = connection.prepareStatement(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				preparedStatement.setString(1, seatNumber);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			int rowsUpdated = 0;
			try {
				rowsUpdated = preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(rowsUpdated > 0) {
				availableSeats.add(seatNumber);
				System.out.println("Seat cancelled sucessfully.");
			}else {
				System.out.println("Seat cancellation failed.");
			}
		
	}
	
	public void showAvailableUsers() throws SQLException {
		String query = "SELECT * from users";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		
			while(resultSet.next()) {
//				availableSeats.add(resultSet.getString("seat_number"));
				System.out.println(resultSet.getString("userName"));
			}
//			if(availableSeats.isEmpty()) {
//				System.out.println("No seats available.");
//			}
	}

	
	
}
