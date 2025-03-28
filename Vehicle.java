import java.time.Duration;
import java.time.LocalDateTime;

public class Vehicle 
{
    // Vehicle variables
    private String plateNumber;
    private String vehicleType;
    private String vehicleColor;
    private String vehicleModel;
    private int ticketID;  
    private LocalDateTime vehicleEntryTime; 
    private LocalDateTime vehicleExitTime; 
    private long parkingDuration; 
    private boolean feePaid = false; 
    private double fee = 0;
    private boolean status; 
    private double deduction;

    // Setters
    public void setPlateNumber(String registration) 
    {
        plateNumber = registration;
    }

    public void setVehicleType(String typeOfVehicle) 
    {
        vehicleType = typeOfVehicle;
    }

    public void setVehicleColor(String colorOfVehicle) 
    {
        vehicleColor = colorOfVehicle;
    }

    public void setVehicleModel(String modelOfVehicle) 
    {
        vehicleModel = modelOfVehicle;
    }

    public void setTicketID(int entranceTicket) 
    {
        ticketID = entranceTicket;
    }

    public void setVehicleEntryTime(LocalDateTime entryTime) 
    {
        vehicleEntryTime = entryTime;
    }

    public void setVehicleExitTime(LocalDateTime exitTime) 
    {
        vehicleExitTime = exitTime;
    }

    public void setFeePaid(boolean paymentStatus) 
    {
        feePaid = paymentStatus;
    }

    public void setFee(double payment) 
    {
        fee = payment;
    }

    public void setStatus(boolean personStatus) 
    {
        status = personStatus;
    }

    public void setDeduction(double discount) 
    {
        deduction = discount;
    }

    // Getters
    public String getPlateNumber() 
    {
        return plateNumber;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }

    public String getVehicleColor() 
    {
        return vehicleColor;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }

    public int getTicketID() 
    {
        return ticketID;
    }

    public LocalDateTime getVehicleEntryTime() 
    {
        return vehicleEntryTime;
    }

    public LocalDateTime getVehicleExitTime() 
    {
        return vehicleExitTime;
    }

    public boolean getFeePaid() 
    {
        return feePaid;
    }

    public double getFee() 
    {
        return fee;
    }

    public boolean getStatus() 
    {
        return status;
    }

    public long getParkingDuration()
    {
        // Condition
        LocalDateTime exitTime = (vehicleExitTime == null) ? LocalDateTime.now() : vehicleExitTime;
        // Compute duration to long.
        return Duration.between(vehicleEntryTime, exitTime).toMinutes();
    }

    public double calculateFee()
    {
        if (vehicleEntryTime == null || vehicleExitTime == null)
        {
            System.out.println("Error: Entry or Exit time missing.");
            return 0.0;
        }

        long minutes = getParkingDuration();
        double hours = Math.ceil(minutes / 60.0);

        double rate = 0;
        if (getVehicleType().equalsIgnoreCase("TYPE 1"))
        {
            rate = Parking.TYPE_ONE;
        }
        else if (getVehicleType().equalsIgnoreCase("TYPE 2"))
        {
            rate = Parking.TYPE_TWO;
        }
        else if (getVehicleType().equalsIgnoreCase("TYPE 3"))
        {
            rate = Parking.TYPE_THREE;
        }

        double totalFee = hours * rate;

        if (status)
        {
            deduction = hours * Parking.TYPE_DISCOUNT;
            totalFee -= deduction;
        }

        this.fee = totalFee;
        return this.fee;
    }

    public double getDeduction() 
    {
        return deduction;
    }
}