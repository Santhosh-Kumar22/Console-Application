package com.theatre;

import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
	private SeatBooking seatBooking;
	
	public Admin() {
		seatBooking = new SeatBooking();
	}
	
	
	// admin to add new seat
	public void addNewSeat() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the seat number to add: ");
		
		String seatNumber = scanner.nextLine();
		
		try {
			seatBooking.addSeat(seatNumber);
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	
	// admin to view available seats
	public void viewAvailableSeats() {
		try {
			seatBooking.showAvailableSeats();
		}catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	
	// admin to view users
	public void viewUsers() {
		try {
			seatBooking.showAvailableUsers();
		}catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
}
