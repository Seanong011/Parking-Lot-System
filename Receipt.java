import java.time.LocalDateTime;

public class Receipt
{
	public static void printEntry(int ticketID, String plateNumber, LocalDateTime entryTime, boolean status)
	{
		System.out.println("\n--------------------------------------");
		System.out.println("   	    ENTRY RECEIPT             ");
		System.out.println("--------------------------------------");
		System.out.println("Ticket ID     : " + String.format("%04d", ticketID));
		System.out.println("Plate Number  : " + plateNumber);
		System.out.println("Entry Time    : " + entryTime);
		System.out.println("PWD/Senior  : " + (status ? "Yes" : "No"));
		System.out.println("--------------------------------------");
	}
	public static void printExit(int ticketID, String plateNumber, LocalDateTime exitTime, long parkingDuration, double fee, boolean status, double discount, boolean feePaid)
	{
		System.out.println("\n--------------------------------------");
		System.out.println("   	    EXIT RECEIPT             ");
		System.out.println("--------------------------------------");
		System.out.println("Ticket ID     : " + String.format("%04d", ticketID));
		System.out.println("Plate Number  : " + plateNumber);
		System.out.println("Exit Time     : " + exitTime);
		System.out.println("Duration      : " + parkingDuration);
		System.out.println("Fee	      : " + fee);
		System.out.println("PWD Discount  : " + (status ? "Yes" : "No"));
		System.out.println("Discount      : " + discount);
		System.out.println("Paid          : " + (feePaid ? "Yes" : "No"));
		System.out.println("--------------------------------------");
	}
}