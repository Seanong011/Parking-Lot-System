import java.util.Scanner;

public class Admin
{
	private Parking lot;

	public Admin(Parking lot)
	{
		this.lot = lot;
	}

	private String adminUsername = "Admin";
	private String adminPassword = "!Admin123";

	public boolean login(String user, String password)
	{
		return user.equals(adminUsername) && password.equals(adminPassword);
	}

	public void showMenu()
	{
		Scanner read = new Scanner(System.in);

		while (true)
		{
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. View CSV Database");
			System.out.println("2. Reset Database");
			System.out.println("3. Logout");
			System.out.print("Choose an option: ");

			int option = read.nextInt();
			read.nextLine();

			switch (option)
			{
				case 1: 
				{
					System.out.println("Opening Database...");
					Database.openCSV();
					break;
				}
				case 2:
				{
					System.out.println("Resetting System...");
					Database.resetDatabase();
					lot.clearParkingLot();
					break;
				}
				case 3:
				{
					System.out.println("Logging out...");
					return;
				} 
				default:
					System.out.println("Invalid Option. Try again");
			}
		}
	}
}