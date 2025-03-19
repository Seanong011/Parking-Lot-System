import java.util.ArrayList;
import java.time.Duration;
import java.util.InputMismatchException;
import java.time.LocalDateTime;
import java.util.Scanner;

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
            int loginOption = 0;
            boolean validInput = false;

            while (!validInput)
            {
    			System.out.println("\n--- Welcome to Angelite Parking Lot Database System ---");
    			System.out.println("Login as: ");
    			System.out.println("1. Operator");
    			System.out.println("2. Admin");
    			System.out.println("3. Exit Program");
    			System.out.print("Choose an option [1-3]: ");

                // Prompt
    			try
                {
                    loginOption = read.nextInt();
                    read.nextLine();

                    if ((loginOption < 1) || (loginOption > 3))
                    {
                        System.out.println("Invalid Option.");
                    }
                    else
                    {
                        validInput = true;
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Invalid Option");
                    read.nextLine();
                }

                // Operator
    			if (loginOption == 1)
    			{
                    int option = 0;
                    boolean operatorInput = true;

                    while (operatorInput)
                    {
                        System.out.println("\n--- Parking Lot Database ---");
                        System.out.println("1. Vehicle Entry");
                        System.out.println("2. Check Available Slots");
                        System.out.println("3. Vehicle Exit");
                        System.out.println("4. Reset Ticket");
                        System.out.println("5. View Database");
                        System.out.println("6. Return to Menu");
                        System.out.print("Choose an option [1-6]: ");

                        try 
                        {
                            option = read.nextInt();
                            read.nextLine();

                            if ((option < 1) || (option > 6))
                            {
                                System.out.println("Invalid Option");
                            }
                            else 
                            {
                                operatorInput = true;
                            }
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("Invalid Option.");
                            read.nextLine();
                        }

                        switch(option)
                        {
                            case 1:
                                if (lot.getAvailableSlots() == 0)
                                {
                                    System.out.println("Parking lot is full! Can not accept more vehicles.");
                                }
                                else 
                                {
                                    // hasNumeric already calls getString in its own.
                                    // Plate No.
                                    String plateNumber = InputValidation.getString("Enter Plate Number: ");

                                    // Vehicle Type
                                    String vehicleType = InputValidation.getType("Enter Vehicle Type (Type 1 / Type 2 / Type 3): ");

                                    // Vehicle Color
                                    String vehicleColor = InputValidation.hasNumeric("Enter Vehicle Color: ");

                                    // Vehicle Model
                                    String vehicleModel = InputValidation.hasNumeric("Enter Vehicle Model: ");

                                    // Time In
                                    LocalDateTime entryTime = LocalDateTime.now();

                                    // PWD Status
                                    String getStatus = InputValidation.getStatus("Is the customer a PWD/Senior Citizen? (y/n): ");
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
                                    int ticketToRemove = -1;
                                    System.out.print("Enter Ticket ID to remove vehicle: ");
                                    try 
                                    {
                                        ticketToRemove = read.nextInt();
                                        read.nextLine();
                                    }
                                    catch (InputMismatchException e)
                                    {
                                        System.out.println("Invalid Option");
                                        read.nextLine();
                                        return;
                                    }

                                    String plateNumberRemove = InputValidation.getString("Enter Plate Number to remove vehicle: ");
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

                // Admin
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

                // Exit
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
}
