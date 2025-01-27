package com.theatre;

import java.sql.SQLException;

public interface SeatBookingInterface {
	// functions
	void bookSeat(String seatId);
	void cancelSeat(String seatNumber);
	void showAvailableSeats();
}
