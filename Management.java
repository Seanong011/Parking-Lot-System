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
			System.out.println("1. Add Vehicle");
			System.out.println("2. Check Available Slots");
			System.out.println("3. Reset Ticket System");
			System.out.println("4. Reset Database");
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
						boolean status;
						if (getStatus.equals("y"))
						{
							status = true;
						}
						else 
						{
							status = false;
						}

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
						// TODO: Insert vehicle details to csv file
						lot.add(vehicle);
						System.out.println("Approved!" + "Here is your Number: " + String.format("%04d", ticketID));
					}
					break;
				case 2:
					{
						System.out.println("Available Slots: " + lot.getAvailableSlot());
						break;
					}
				case 3:
					{
						lot.resetTicket();
						System.out.println("Ticket Reset successful!");
						break;
					}
				case 4: 
					break;
				case 5:
					{
						System.out.println("Exiting Program...");
						read.close();
						return;
					}
			}
		}
	}
}
