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
	 	
    // Parked vehicles
    private ArrayList<Vehicle> parkedVehicles;

    public Parking()
    {
    	this.parkedVehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle)
    {
        parkedVehicles.add(vehicle);
        CSVHandler.writeToCSV(vehicle);
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

    public void removeVehicle(int ticketID) 
    {
        Iterator<Vehicle> iterator = parkedVehicles.iterator();
        while (iterator.hasNext())
        {
            Vehicle v = iterator.next();
            if (v.getTicketID() == ticketID)
            {
                v.setVehicleExitTime(LocalDateTime.now());
                v.calculateFee(); 
                System.out.println("Total Fee for Ticket ID " + ticketID + ": ₱" + v.getFee());
                v.setFeePaid(true);

                iterator.remove(); 
                CSVHandler.updateCSV(parkedVehicles); 
                return;
            }
        }
            System.out.println("Vehicle with Ticket ID " + ticketID + " not found.");
    }
}