import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;

public class Management {
	public static void main(String[] args)
	{
		// Scanner variable
		Scanner read = new Scanner(System.in);

		// Parking Lot
		Parking lot = new Parking();

		while (true)
		{
			System.out.println("\n--- Parking Lot Database ---");
			System.out.println("1. Vehicle Entry");
			System.out.println("2. Check Available Slots");
			System.out.println("3. Vehicle Exit");
			System.out.println("4. Reset Ticket");
			System.out.println("5. Reset Database");
			System.out.println("Choose an option: ");

			int option = read.nextInt();
			read.nextLine();

			switch(option)
			{
				case 1:
					if (lot.getAvailableSlots() == 0)
					{
						System.out.println("Parking lot is full! Can not accept more vehicles");
					}
					else 
					{
						// Plate No.
						System.out.print("Enter Plate Number: ");
						String plateNumber = read.nextLine();

						// Vehicle Type
						System.out.print("Enter Vehicle Type: ");
						String vehicleType = read.nextLine();

						// Vehicle Color
						System.out.print("Enter Vehicle Color: ");
						String vehicleColor = read.nextLine();

						// Vehicle Model
						System.out.print("Enter Vehicle Model: ");
						String vehicleModel = read.nextLine();

						// Time In
						LocalDateTime entryTime = LocalDateTime.now();

						// PWD Status
						System.out.print("Is the customer a PWD/Senior Citizen? (y/n): ");
						String getStatus = read.nextLine().trim().toLowerCase();
						boolean status = getStatus.equals("y");

						// Vehicle properties
						Vehicle vehicle = new Vehicle();
						vehicle.setPlateNumber(plateNumber);
						vehicle.setVehicleType(vehicleType);
						vehicle.setVehicleColor(vehicleColor);
						vehicle.setVehicleModel(vehicleModel);
						vehicle.setVehicleEntryTime(entryTime);
						vehicle.setStatus(status);
						vehicle.setFeePaid(false);

						// Create vehicle object
						int ticketID = Parking.generateTicket();
						vehicle.setTicketID(ticketID);
						
						// Print entry receipt
						printEntry(ticketID, plateNumber, entryTime, status);
					}
					break;
				case 2:
					{
						System.out.println("Available Slots: " + lot.getAvailableSlots());
						break;
					}
				case 3:
					{
						System.out.print("Enter Ticket ID and Plate Number to remove vehicle: ");
					    int ticketToRemove = read.nextInt();
					    String plateNumberRemove = read.nextLine();
					    read.nextLine();

					    boolean statusPaid = vehicle.getFeePaid();
					    double fee = vehicle.calculateFee();
					    long duration = vehicle.getParkingDuration();
					   	double discount = vehicle.getDeduction();
					    
					    printExit(ticketID, plateNumber, exitTime, duration, fee, status, discount);
					    lot.removeVehicle(ticketToRemove, plateNumberRemove);
						break;
					}
				case 4: 
					{
					    lot.resetTicket();
						System.out.println("Ticket Reset successful!");
						break;
					}
				case 5:
					{
						System.out.println("Resetting database...");
    					Database.resetDatabase(); 
   						Parking.resetTicket(); 
    					break;
					}
				case 6:
					{
						System.out.println("Exiting program...");
						read.close();
						System.exit(0);
					    break;
					}
			}
		}
	}
	public static void printEntry(int ticketID, String plateNumber, LocalDateTime entryTime, boolean status)
	{
		System.out.println("\n--------------------------------------");
		System.out.println("   	    ENTRY RECEIPT             ");
		System.out.println("--------------------------------------");
		System.out.println("Ticket ID     : " + String.format("%04d", ticketID));
		System.out.println("Plate Number  : " + plateNumber);
		System.out.println("Entry Time    : " + entryTime);
		System.out.println("PWD/Senior  : " + (status ? "Yes" : "No"));
		System.out.println("--------------------------------------");
	}
	public static void printExit(int ticketID, String plateNumber, LocalDateTime exitTime, long parkingDuration, double fee, boolean status, double discount)
	{
		System.out.println("\n--------------------------------------");
		System.out.println("   	    EXIT RECEIPT             ");
		System.out.println("--------------------------------------");
		System.out.println("Ticket ID     : " + String.format("%04d", ticketID));
		System.out.println("Plate Number  : " + plateNumber);
		System.out.println("Exit Time   : " + exitTime);
		System.out.println("Duration      :" + duration);
		System.out.println("Fee	      :" + fee);
		System.out.println("PWD Discount  : " + (status ? "Yes" : "No"));
		System.out.println("Discount      : " + discount);
		System.out.println("--------------------------------------");
	}
}
