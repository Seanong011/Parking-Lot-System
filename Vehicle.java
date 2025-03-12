// Import libraries
import java.time.LocalDateTime;
import java.time.Duration;

public class Vehicle {
    // Vehicle variables
    private String plateNumber;
    private String vehicleType;
    private String vehicleColor;
    private String vehicleModel;
    private int ticketID; 
    private int parkingSlotNumber; 
    private LocalDateTime vehicleEntryTime; 
    private LocalDateTime vehicleExitTime; 
    private long parkingDuration; 
    private boolean feePaid = false; 
    private double fee; 
    private boolean status; 
    private double deduction;

    // Setters
    public void setPlateNumber(String registration) {
        plateNumber = registration;
    }

    public void setVehicleType(String typeOfVehicle) {
        vehicleType = typeOfVehicle;
    }

    public void setVehicleColor(String colorOfVehicle) {
        vehicleColor = colorOfVehicle;
    }

    public void setVehicleModel(String modelOfVehicle) {
        vehicleModel = modelOfVehicle;
    }

    public void setTicketID(int entranceTicket) {
        ticketID = entranceTicket;
    }

    public void setParkingSlotNumber(int slotNumber) {
        parkingSlotNumber = slotNumber;
    }

    public void setVehicleEntryTime(LocalDateTime entryTime) {
        vehicleEntryTime = entryTime;
    }

    public void setVehicleExitTime(LocalDateTime exitTime) {
        vehicleExitTime = exitTime;
    }

    public void setFeePaid(boolean paymentStatus) {
        feePaid = paymentStatus;
    }

    public void setFee(double payment) {
        fee = payment;
    }

    public void setStatus(boolean personStatus) {
        status = personStatus;
    }

    public void setDeduction(double reduction){
        deduction = reduction;
    }

    // Getters
    public String getPlateNumber() {
        return plateNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public int getTicketID() {
        return ticketID;
    }

    public int getParkingSlotNumber() {
        return parkingSlotNumber;
    }

    public LocalDateTime getVehicleEntryTime() {
        return vehicleEntryTime;
    }

    public LocalDateTime getVehicleExitTime() {
        return vehicleExitTime;
    }

    public boolean getFeePaid() {
        return feePaid;
    }

    public double getFee() {
        return fee;
    }

    public boolean getStatus() {
        return status;
    }

    public double getDeduction() {
        return deduction;
    }

    // Get parking duration
    public long getParkingDuration()
    {
        // Condition
        if (vehicleEntryTime == null || vehicleExitTime == null)
        {
            return 0;
        }
        else 
        {   
            // Compute duration to long
            return Duration.between(vehicleEntryTime, vehicleExitTime).toMinutes();
        }
    }

    // Get Parking fee
    public void calculateFee()
    {
        if (vehicleEntryTime == null || vehicleExitTime == null)
        {
            System.out.println("Error: Entry or Exit time missing.");
            return;
        }

        long minutes = getParkingDuration();
        double hours = minutes / 60.00;

        double rate = 0;
        if (getVehicleType().equalsIgnoreCase("TYPE 1"))
        {
            rate = Parking.TYPE_ONE_RATE;
        }
        else if (getVehicleType().equalsIgnoreCase("TYPE 2"))
        {
            rate = Parking.TYPE_TWO_RATE;
        }
        else if (vehicleType().equalsIgnoreCase("TYPE 3"))
        {
            rate = Parking.TYPE_THREE_RATE;
        }

        double totalFee = hours * rate;

        if (status)
        {
            totalFee = totalFee - (hours * Parking.TYPE_DISCOUNT);
        }

        this.fee = totalFee;
        this.feePaid = false;
    }
}