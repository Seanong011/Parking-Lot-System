import java.util.ArrayList;
import java.time.Duration;
import java.util.Iterator;
import java.time.LocalDateTime;

public class Parking
{
	// Capacity
	public static final int MAX_CAPACITY = 75;

	// Price variables
    public static final double TYPE_ONE = 25.00;
    public static final double TYPE_TWO = 35.00;
    public static final double TYPE_THREE = 50.00;
    public static final double TYPE_DISCOUNT = 5.00;

    // Ticket counter
    private static int ticketCounter = 0;

    // Limit
    public static final long OVERTIME = 1440;
	 	
    // Parked vehicles
    private ArrayList<Vehicle> parkedVehicles;

    // Parking constructor
    public Parking()
    {
        this.parkedVehicles = new ArrayList<>(Database.readFromCSV());
    }

    public ArrayList<Vehicle> getParkedVehicles()
    {
        return parkedVehicles;
    }

    public void addVehicle(Vehicle vehicle)
    {
        if (parkedVehicles.size() < MAX_CAPACITY)
        {
            parkedVehicles.add(vehicle);
            Database.writeToCSV(vehicle);
        }
    }

    public void clearParkingLot()
    {
        parkedVehicles.clear();
    }

    public static int generateTicket()
    {
    	return ++ticketCounter;
    }

    public static void resetTicket()
    {
    	ticketCounter = 0;
    }

    public int getAvailableSlots()
    {
    	return MAX_CAPACITY - parkedVehicles.size();
    }

    public Vehicle findVehicle(int ticketID, String plateNumber)
    {
        System.out.println("Searching for Ticket ID: " + ticketID + " | Plate: " + plateNumber);

        for (Vehicle v : parkedVehicles)
        {
            if (v.getTicketID() == ticketID && v.getPlateNumber().trim().equalsIgnoreCase(plateNumber.trim()))
            {
                System.out.println("Vehicle found");
                return v;
            }
        }
        return null;
    }

    public void removeVehicle(int ticketID, String plateNumber) 
    {
        Iterator<Vehicle> iterator = parkedVehicles.iterator();
        while (iterator.hasNext())
        {
            Vehicle v = iterator.next();
            if (v.getTicketID() == ticketID && v.getPlateNumber().equals(plateNumber))
            {
                iterator.remove(); 
                Database.updateCSV(parkedVehicles); 
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }
}