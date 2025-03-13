import java.util.ArrayList;
import java.util.Iterator;

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
    private static final long OVERTIME = 1440;
	 	
    // Parked vehicles
    private ArrayList<Vehicle> parkedVehicles;

    public Parking()
    {
    	this.parkedVehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle)
    {
        parkedVehicles.add(vehicle);
        Database.writeToCSV(vehicle);
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

    public void removeVehicle(int ticketID, String plateNumber) 
    {
        Iterator<Vehicle> iterator = parkedVehicles.iterator();
        while (iterator.hasNext())
        {
            Vehicle v = iterator.next();
            if (v.getTicketID() == ticketID && v.getPlateNumber().equals(plateNumber))
            {
                v.setVehicleExitTime(LocalDateTime.now());
                v.calculateFee(); 
                System.out.println("Total Fee for Ticket ID " + ticketID + ": ₱" + v.getFee());
                v.setFeePaid(true);

                iterator.remove(); 
                Database.updateCSV(parkedVehicles); 
                return;
            }
        }
            System.out.println("Vehicle with Ticket ID " + ticketID + "and Plate Number " + plateNumber + " not found.");
    }
}