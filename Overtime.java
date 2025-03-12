import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.Duration;

public class Overtime implements Runnable // Separate thread for stable scanning
{
	private Parking lot;

	public Overtime(Parking lot)
	{
		this.lot = lot;
	}

	public void run()
	{
		Iterator<Vehicle> iterator = lot.getParkedVehicles().iterator();
		while (iterator.hasNext())
		{
			Vehicle v = iterator.next();
			long durationCheck = v.getParkingDuration();
			if (durationCheck > 1440)
			{
				int rmID = v.getTicketID();
				System.out.println("Removing vehicle with Ticket ID: " + rmID);
				lot.removeVehicle(rmID);
				// Update database
				CSVHandler.updateCSV(lot.getParkedVehicles());
				break;
			}
		}
	}
}