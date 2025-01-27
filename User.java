package com.theatre;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
	private SeatBooking seatBooking;
	
	public User() {
		seatBooking = new SeatBooking();
	}
	
	//logic to display available seats
	public void displayAvailableSeats() {
		seatBooking.showAvailableSeats();
	}
	
	// logic for seat booking
	public void bookSeat() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Seat Number to book: ");
		String seatNumber = scanner.nextLine();
		
		seatBooking.bookSeat(seatNumber);
	}
	
	// logic for cancelling seat
	public void cancelSeat() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Seat Number to be cancelled: ");
		String cancelSeat = scanner.nextLine();
		
		seatBooking.cancelSeat(cancelSeat);
		System.out.println("Seat cancelled successfully.");
	}
}
