package booking;

public class ServiceCharge {
	
	private int customerId;
	private int productServiceId;
	private String bookDate;
	private String bookTime;
	private int bookingId;

	/*
	 * public ServiceCharge(int customerId, int productServiceId, String bookDate,
	 * String bookTime) { this.customerId = customerId; this.productServiceId =
	 * productServiceId; this.bookDate = bookDate; this.bookTime = bookTime; }
	 */
	
	public ServiceCharge(int customerId, int productServiceId, String bookDate, String bookTime, int bookingId) {
		this.customerId = customerId;
		this.productServiceId = productServiceId;
		this.bookDate = bookDate;
		this.bookTime = bookTime;
		this.bookingId = bookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductServiceId() {
		return productServiceId;
	}

	public void setProductServiceId(int productServiceId) {
		this.productServiceId = productServiceId;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

}
