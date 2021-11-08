package com.sunil.booking;

import java.util.Date;
import java.util.List;

import com.sunil.booking.model.MovieShow;
import com.sunil.booking.model.TheatreSeat;
import com.sunil.booking.enums.BookingStatus;
import com.sunil.booking.payment.Payment;

public class Booking {
	  private String bookingNumber;
	  private int numberOfSeats;
	  private Date createdOn;
	  private BookingStatus status;

	  private MovieShow show;
	  private List<TheatreSeat> seats;
	  private Payment payment;


	  public boolean makePayment(Payment payment) {
		  return true;
	  }
	  
	  public boolean cancel() {
		  return  true;
	  }
	  
	  public boolean assignSeats(List<TheatreSeat> seats) {
		  return true;
	  }

}
