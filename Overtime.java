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

	@Override
	public void run()
	{
		while (true)
		{
			checkOvertime();
			try {
				Thread.sleep(60000);
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
				System.out.println("Removing vehicle with Ticket ID " + removeID + "and Plate Number: " + removePlateNumber);
				lot.removeVehicle(removeID, removePlateNumber);
				// Update database
				Database.updateCSV(lot.getParkedVehicles());
				break;
			}
		}
	}
}