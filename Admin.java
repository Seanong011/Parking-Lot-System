import java.util.Scanner;

public class Admin
{
	private String admin = "Admin";
	private String pass = "!Admin123";

	public boolean login(String user, String password)
	{
		return user.equals(admin) && pass.equals(password);
	}

	public void showMenu()
	{
		Scanner read = new Scanner(System.in);

		while (true)
		{
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. Edit CSV Database");
			System.out.println("2. Reset Database");
			System.out.println("3. Logout");
			System.out.print("Choose an option: ");

			int option = read.nextInt();
			read.nextLine();

			switch (option)
			{
				case 1: 
				{
					System.out.println("Opening Database in edit mode...");
					// Database.editCSV();
					break;
				}
				case 2:
				{
					System.out.println("Resetting database...");
					Database.resetDatabase();
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