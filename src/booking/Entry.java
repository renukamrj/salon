package booking;

import java.util.ArrayList;
import java.util.Scanner;

import dbsupport.DBHelper;

public class Entry {
	private static double taxRate = 0.08875;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		int custId;
		int prodServiceId;
		String bookDate, bookTime;

		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Customer Id:");
		custId = scan.nextInt();

		Customer customer = DBHelper.findCustomer(custId);
		if (customer != null) {
			System.out.println("Customer Name:" + customer.getFirstName() + " " + customer.getLastName());
			System.out.println("Customer Phone:" + customer.getPhoneNumber());
			System.out.println("Customer Address:" + customer.getAddress());
			System.out.println("Customer DateOfBirth:" + customer.getDateOfBirth());
		} else {
			customer = new Customer();
			System.out.print("Enter Customer First Name:");
			customer.setFirstName(scan.next());
			System.out.print("Enter Customer Last Name:");
			customer.setLastName(scan.next());
			System.out.print("Enter Customer Phone:");
			customer.setPhoneNumber(scan.next());
			System.out.print("Enter Customer Address:");
			customer.setAddress(scan.next());
			System.out.print("Enter DateOfBirth:");
			customer.setDateOfBirth(scan.next());
			customer.setCustomerId(DBHelper.addCustomer(customer));
		}

		System.out.println("Availabe Services:");
		System.out.println("Prod Id\tProd Name\tDescription\tPrice");
		for (ProductService prod : DBHelper.getProductServiceList()) {
			System.out.printf("%d\t%s\t%s\t%s\n", prod.getProductServiceId(), prod.getProductName(),
					prod.getProductService_desc(), prod.getPrice());

		}

		System.out.print("Select ProductService Id:");
		prodServiceId = scan.nextInt();
		int bookingId = (int) (Math.random() * (90000 - 10000));

		ProductService prod = DBHelper.findProductService(prodServiceId);
		if (prod != null) {
			System.out.print("Enter BookDate:");
			bookDate = scan.next();
			System.out.print("Enter BookTime:");
			bookTime = scan.next();
			DBHelper.addServiceCharge(new ServiceCharge(customer.getCustomerId(), prod.getProductServiceId(), bookDate, bookTime, bookingId));

			System.out.print("Want to Add more ProductService (Y/N)?");
			char moreService = scan.next().charAt(0);

			if (moreService == 'Y' || moreService == 'y') {
				System.out.print("Select ProductService Id:");
				prodServiceId = scan.nextInt();

				prod = DBHelper.findProductService(prodServiceId);
				if (prod != null) {
					System.out.print("Enter BookDate:");
					bookDate = scan.next();
					System.out.print("Enter BookTime:");
					bookTime = scan.next();
					DBHelper.addServiceCharge(new ServiceCharge(customer.getCustomerId(), prod.getProductServiceId(), bookDate, bookTime, bookingId));
				} else {
					System.out.println("ProductService you selected is not valid");
				}
			}
		} else {
			System.out.println("ProductService you selected is not valid");
		}

		printInvoice(DBHelper.getServiceCharge(bookingId));

	}
	
	public static void printInvoice(ArrayList<ServiceCharge> serviceCharges) {
		Customer customer = null;
		ProductService prod = null;
		double totalTax = 0, totalPrice = 0;
		for (ServiceCharge service : serviceCharges) {
			if (customer == null) {
				customer = DBHelper.findCustomer(service.getCustomerId());
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("---------------------- **** Invoice *** -----------------------------------------");
				System.out.println("---------------------------------------------------------------------------------");
				System.out.println("Customer Name: " + customer.getFirstName() + " " + customer.getLastName());
				System.out.println("Phone: " + customer.getPhoneNumber());
				System.out.println("Address: " + customer.getAddress());
				System.out.println("Date Of Birth: " + customer.getDateOfBirth());
				System.out.println("---------------------------------------------------------------------------------");
			}

			if (prod == null)
				System.out.println("Prod Id\tProd Name\tDescription\tPrice\tTax\tTotal\tBookDate\tBookTime");

			prod = DBHelper.findProductService(service.getProductServiceId());

			double tax = prod.getPrice() * taxRate;
			double total = prod.getPrice() + tax;
			System.out.printf("%d\t%s\t%s\t%s\t%.2f\t%.2f\t%s\t%s\n", prod.getProductServiceId(), prod.getProductName(),
					prod.getProductService_desc(), prod.getPrice(), tax, total,service.getBookDate(), service.getBookTime());
			totalTax += tax;
			totalPrice += total;
		}
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("\t\t\t\tTax Amount:%.2f\n", totalTax);
		System.out.printf("\t\t\t\tNet Total:%.2f\n", totalPrice);
		System.out.println("---------------------------------------------------------------------------------");
	}

}
