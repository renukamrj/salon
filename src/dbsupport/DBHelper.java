package dbsupport;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import booking.Customer;
import booking.ProductService;
import booking.ServiceCharge;

public class DBHelper {

	public static ArrayList<Customer> getCustomerList() {
		ArrayList<Customer> clist = new ArrayList<Customer>();

		try {
			Connection conn = DBSupport.establishConnection();
			Statement useStatement = conn.createStatement();
			useStatement.executeQuery("USE salon");

			Statement queryStatement = conn.createStatement();
			String query = "SELECT customerId,firstName,lastName,phoneNumber,address,dateOfBirth FROM salon.customer;";

			ResultSet rs = queryStatement.executeQuery(query);
			while (rs.next()) {
				int customerId = rs.getInt("customerId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				String dateOfBirth = rs.getString("dateOfBirth");

				clist.add(new Customer(customerId, firstName, lastName, phoneNumber, address, dateOfBirth));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clist;
	}

	public static int addCustomer(Customer customer) {
		try {
			Connection conn = DBSupport.establishConnection();
			Statement useStatement = conn.createStatement();
			useStatement.executeQuery("USE salon");

			Statement queryStatement = conn.createStatement();
			String query = String.format(
					"INSERT INTO customer (customerId,firstName,lastName,phoneNumber,address,dateOfBirth)VALUES(%d,'%s','%s','%s','%s','%s');",
					customer.getCustomerId(), customer.getFirstName(), customer.getLastName(),
					customer.getPhoneNumber(), customer.getAddress(), customer.getDateOfBirth());
			queryStatement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet results = queryStatement.getGeneratedKeys();
			results.next();
			return results.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static ArrayList<ProductService> getProductServiceList() {
		ArrayList<ProductService> plist = new ArrayList<ProductService>();
		try {
			Connection conn = DBSupport.establishConnection();
			Statement useStatement = conn.createStatement();
			useStatement.executeQuery("USE salon");

			Statement queryStatement = conn.createStatement();
			String query = "SELECT productServiceId,productName, productService_desc,price FROM salon.productservice;";

			ResultSet rs = queryStatement.executeQuery(query);
			while (rs.next()) {
				int productServiceId = rs.getInt("productServiceId");
				String productName = rs.getString("productName");
				String productService_desc = rs.getString("productService_desc");
				double price = rs.getDouble("price");

				plist.add(new ProductService(productServiceId, productName, productService_desc, price));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plist;

	}

	public static Customer findCustomer(int customerId) {
		for (Customer cust : getCustomerList()) {
			if (cust.getCustomerId() == customerId)
				return cust;
		}
		return null;
	}

	public static ProductService findProductService(int productServiceId) {
		for (ProductService prod : getProductServiceList()) {
			if (prod.getProductServiceId() == productServiceId)
				return prod;
		}
		return null;
	}

	public static void addServiceCharge(ServiceCharge serviceCharge) {
		try {
			Connection conn = DBSupport.establishConnection();
			Statement useStatement = conn.createStatement();
			useStatement.executeQuery("USE salon");

			Statement queryStatement = conn.createStatement();
			String query = String.format(
					"INSERT INTO servicecharge (customerId,productServiceId,bookDate,bookTime,bookingId)VALUES(%d,%d,'%s','%s',%d);",
					serviceCharge.getCustomerId(), serviceCharge.getProductServiceId(), serviceCharge.getBookDate(),
					serviceCharge.getBookTime(), serviceCharge.getBookingId());
			queryStatement.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<ServiceCharge> getServiceCharge(int bookingId) {
		ArrayList<ServiceCharge> slist = new ArrayList<ServiceCharge>();

		try {
			Connection conn = DBSupport.establishConnection();
			Statement useStatement = conn.createStatement();
			useStatement.executeQuery("USE salon");

			Statement queryStatement = conn.createStatement();
			String query = String.format(
					"SELECT customerId, productServiceId,bookDate,bookTime FROM servicecharge where bookingId = %d;",
					bookingId);

			ResultSet rs = queryStatement.executeQuery(query);
			while (rs.next()) {
				int customerId = rs.getInt("customerId");
				int productServiceId = rs.getInt("productServiceId");
				String bookDate = rs.getString("bookDate");
				String bookTime = rs.getString("bookTime");
				slist.add(new ServiceCharge(customerId, productServiceId, bookDate, bookTime, bookingId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slist;
	}
}
