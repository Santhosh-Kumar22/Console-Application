package com.theatre;

import java.sql.SQLException;

public interface AdminOperations {
	void addSeat(String seatNumber) throws SQLException;
}
