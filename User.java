package com.theatre;

import java.sql.SQLException;
import java.util.Scanner;

public class User {
	private SeatBooking seatBooking;
	
	public User() {
		seatBooking = new SeatBooking();
	}
	
	//logic to display available seats
	public void displayAvailableSeats() {
		try {
			seatBooking.showAvailableSeats();
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	// logic for seat booking
	public void bookSeat() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Seat Number to book: ");
		String seatNumber = scanner.nextLine();
		
		try {
			seatBooking.bookSeat(seatNumber);
		}catch(SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("There is no such seat.");
		}
	}
	
	// logic for cancelling seat
	public void cancelSeat() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Seat Number to be cancelled: ");
		String cancelSeat = scanner.nextLine();
		
		try {
			seatBooking.cancelSeat(cancelSeat);
			System.out.println("Seat cancelled successfully.");
		}catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
	}
}
