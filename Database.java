import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Database 
{
    private static final String FILE_NAME = "parking_data.csv";

    // Write new vehicle data to CSV
    public static void writeToCSV(Vehicle vehicle) 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            String record = vehicle.getTicketID() + "," + 
                            vehicle.getPlateNumber() + "," + 
                            vehicle.getVehicleType() + "," + 
                            vehicle.getVehicleColor() + "," + 
                            vehicle.getVehicleModel() + "," + 
                            vehicle.getVehicleEntryTime() + "," + 
                            "N/A" + "," + 
                            "false" + "," + 
                            "0.0";

            writer.println(record);
            System.out.println("Vehicle written to CSV: " + vehicle.getPlateNumber());

        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    // Load data from previous session
    public static List<Vehicle> readFromCSV() 
    {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) 
        {
            String line = reader.readLine(); // Read header
            while ((line = reader.readLine()) != null) 
            {
                String[] data = line.split(",");
                if (data.length >= 6) 
                { // Ensure correct format
                    Vehicle vehicle = new Vehicle();
                    vehicle.setTicketID(Integer.parseInt(data[0]));
                    vehicle.setPlateNumber(data[1]);
                    vehicle.setVehicleType(data[2]);
                    vehicle.setVehicleColor(data[3]);
                    vehicle.setVehicleModel(data[4]);
                    vehicle.setVehicleEntryTime(LocalDateTime.parse(data[5]));

                    // LOAD - If exit time exists, load it
                    if (!data[6].equals("N/A")) {
                        vehicle.setVehicleExitTime(LocalDateTime.parse(data[6]));
                    }

                    // LOAD - Load fee paid status
                    vehicle.setFeePaid(Boolean.parseBoolean(data[7]));

                    // LOAD - Load fee amount
                    vehicle.setFee(Double.parseDouble(data[8]));

                    vehicles.add(vehicle);
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return vehicles;
    }

    // Update CSV file when a vehicle exits
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

    // Resets database
    public static void resetDatabase() 
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) 
        {
            writer.println("TicketID,PlateNumber,VehicleType,Color,Model,EntryTime,ExitTime,FeePaid,Fee");
            System.out.println("Database reset successfull.");
        } 
        catch (IOException e) 
        {
            System.err.println("Error resetting CSV: " + e.getMessage());
        }
    }

   // Views database
    public static void openCSV()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME)))
        {
            System.out.println("\n--- Parking Lot Database (Read-Only) ---");
            String line;
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
}