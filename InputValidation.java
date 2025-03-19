import java.util.Scanner;

public class InputValidation {
	private static Scanner read = new Scanner(System.in);

	// Checks for empty input.
	public static String getString(String prompt)
	{
		String input;
		while (true)
		{
			System.out.print(prompt);
			input = read.nextLine().trim().toUpperCase();

			if (input.isEmpty())
			{
				System.out.println("Empty Input, Please try again.");
				continue;	
			}
			return input;
		}
	}

	// Checks string input for numeric data type.
	public static String hasNumeric(String prompt)
	{
		String input;
		while (true)
		{
			input = getString(prompt);
			boolean stringNumeric = false;

			for (int i = 0; i < input.length(); i++)
			{
				if (Character.isDigit(input.charAt(i)))
				{
					stringNumeric = true;
					break;
				}
			}

			if (stringNumeric == true)
			{
				System.out.println("Error Input, Please try again.");
				continue;
			}
			return input;
		}
	}

	// Checks for invalid vehicleType.
	public static String getType(String prompt)
	{
		String input;
		while (true)
		{
			System.out.print(prompt);
			input = read.nextLine().trim().toUpperCase();
			if (input.equals("TYPE 1") || input.equals("TYPE 2") || input.equals("TYPE 3"))
			{
				return input;
			}
			System.out.println("Error Input, Please try again.");
		}
	}

	// Checks for invalid status.
	public static String getStatus(String prompt)
	{
		String input;
		while (true)
		{
			System.out.print(prompt);
			input = read.nextLine().trim().toLowerCase();
			if (input.equals("y") || input.equals("n"))
			{
				return input;
			}
			System.out.println("Error Input, Please try again.");
		}
	}
}