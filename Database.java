//Group 8 - Exclamador, Ong, Trillanes & Yao
//Database (Parking Management CSV)
//CS - 101


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Database 
{
    private static final String FILE_NAME = "parking_data.csv";

    // WRITE TO CSV - Write new vehicle data to CSV
    public static void writeToCSV(Vehicle vehicle) 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) 
        {
            writer.println(vehicle.getTicketID() + "," + 
                           vehicle.getPlateNumber() + "," + 
                           vehicle.getVehicleType() + "," + 
                           vehicle.getVehicleColor() + "," + 
                           vehicle.getVehicleModel() + "," + 
                           vehicle.getVehicleEntryTime());
        } 
        catch (IOException e) 
        {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // UPDATE CSV - Update CSV file when a vehicle exits
    public static void updateCSV(List<Vehicle> vehicles) 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) 
        {
            writer.println("TicketID,PlateNumber,VehicleType,Color,Model,EntryTime,ExitTime,FeePaid,Fee");

            for (Vehicle v : vehicles) 
            {
                writer.println(v.getTicketID() + "," + 
                               v.getPlateNumber() + "," + 
                               v.getVehicleType() + "," + 
                               v.getVehicleColor() + "," + 
                               v.getVehicleModel() + "," + 
                               v.getVehicleEntryTime() + "," + 
                               (v.getVehicleExitTime() != null ? v.getVehicleExitTime() : "N/A") + "," + 
                               v.getFeePaid() + "," + 
                               v.getFee());
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Error updating CSV: " + e.getMessage());
        }
    }

    // RESET - Resets database
    public static void resetDatabase() 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) 
        {
            writer.println("TicketID,PlateNumber,VehicleType,Color,Model,EntryTime,ExitTime,FeePaid,Fee");
            System.out.println("Database reset successfully.");
        } 
        catch (IOException e) 
        {
            System.err.println("Error resetting CSV: " + e.getMessage());
        }
    }
}