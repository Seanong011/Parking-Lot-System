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

        // Overtime
        Overtime overtime = new Overtime(lot);
        Thread overtimeScan = new Thread(overtime);
        overtimeScan.start();

		while (true)
		{
			System.out.println("\n--- Welcome to Angelite Parking Lot Database System ---");
			System.out.println("Login as: ");
			System.out.println("1. Operator");
			System.out.println("2. Admin");
			System.out.println("3. Exit Program");
			System.out.print("Choose and option: ");

			int loginOption = read.nextInt();
			read.nextLine();

			if (loginOption == 1)
			{
                while (true)
                {
                    System.out.println("\n--- Parking Lot Database ---");
                    System.out.println("1. Vehicle Entry");
                    System.out.println("2. Check Available Slots");
                    System.out.println("3. Vehicle Exit");
                    System.out.println("4. Reset Ticket");
                    System.out.println("5. View Database");
                    System.out.println("6. Return to Menu");
                    System.out.print("Choose an option: ");

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
                                // Add vehicle to lot
                                lot.addVehicle(vehicle);
                                
                                // Print entry receipt
                                Receipt.printEntry(ticketID, plateNumber, entryTime, status);
                            }
                            break;
                        case 2:
                            {
                                int avaiableSlots = lot.getAvailableSlots();
                                int occupiedSlots = Parking.MAX_CAPACITY - avaiableSlots;

                                System.out.println("Vehicles Parked: " + occupiedSlots);
                                System.out.println("Available Slots: " + avaiableSlots);
                                break;
                            }
                        case 3:
                            {
                                System.out.print("Enter Ticket ID to remove vehicle: ");
                                int ticketToRemove = read.nextInt();
                                read.nextLine();

                                System.out.print("Enter Plate Number to remove vehicle: ");
                                String plateNumberRemove = read.nextLine();

                                Vehicle vehicle = lot.findVehicle(ticketToRemove, plateNumberRemove);
                                if (vehicle == null)
                                {
                                    System.out.println("Error: Vehicle not found.");
                                    break;
                                }
                                vehicle.setVehicleExitTime(LocalDateTime.now());
                                LocalDateTime exitTime = vehicle.getVehicleExitTime();
                                vehicle.setFeePaid(true);
                                boolean feePaid = vehicle.getFeePaid();
                                double fee = vehicle.calculateFee();
                                long parkingDuration = vehicle.getParkingDuration();
                                double discount = vehicle.getDeduction();
                                boolean status = vehicle.getStatus();
                                
                                Receipt.printExit(ticketToRemove, plateNumberRemove, exitTime, parkingDuration, fee, status, discount, feePaid);
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
                                System.out.println("Viewing Vehicles...");
                                Database.openCSV();
                                break;
                            }
                        case 6:
                            {
                                System.out.println("Returning to Menu...");
                                break;
                            }
                        default:
                            System.out.println("Invalid Option. Try again.");
                    }
                    if (option == 6)
                    {
                        break;
                    }
                }
			}
			else if (loginOption == 2)
			{
				System.out.print("Enter User: ");
				String user = read.nextLine();

				System.out.print("Enter Password: ");
				String password = read.nextLine();

				Admin admin = new Admin(lot);
				if (admin.login(user, password))
				{
					System.out.println("Admin Login Successful!");
					admin.showMenu();
				}
				else
				{
					System.out.println("Incorrect Username or Password.");
				}
			}
			else if (loginOption == 3)
			{
				System.out.println("Exiting program...");
				read.close();
				System.exit(0);
				break;	
			}
		}
	}
}
