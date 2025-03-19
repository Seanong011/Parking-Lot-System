import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.Duration;

// thread for consistent scanning from runnable interface
public class Overtime implements Runnable
{
	private Parking lot;

	public Overtime(Parking lot)
	{
		this.lot = lot;
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			checkOvertime();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void checkOvertime()
	{
		Iterator<Vehicle> iterator = lot.getParkedVehicles().iterator();
		while (iterator.hasNext())
		{
			Vehicle v = iterator.next();
			long durationCheck = v.getParkingDuration();
			if (durationCheck > Parking.OVERTIME)
			{
				int removeID = v.getTicketID();
				String removePlateNumber = v.getPlateNumber();
				System.out.println("\nRemoving vehicle with Ticket ID " + String.format("%04d", removeID) + " and Plate Number: " + removePlateNumber);
				lot.removeVehicle(removeID, removePlateNumber);
				// Update database
				Database.updateCSV(lot.getParkedVehicles());
				break;
			}
		}
	}
}