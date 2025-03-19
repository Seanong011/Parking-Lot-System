import java.util.Scanner;

public class InputValidation {
	private static Scanner read = new Scanner(System.in);

	// Integer should not be allowed
	public static String getString(String prompt)
	{
		String input;
		while (true)
		{
			System.out.print(prompt);
			input = read.nextLine().trim();
			if (!input.isEmpty())
			{
				return input;	
			}
			System.out.println("Empty Input: Please try again.");
		}
	}

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
			System.out.println("Error Input: Please try again.");
		}
	}

	public static String getStatus(String prompt)
	{
		String input;
		while (true)
		{
			System.out.println(prompt);
			input = read.nextLine().trim().toLowerCase();
			if (input.equals("y") || input.equals("n"))
			{
				return input;
			}
			System.out.println("Error Input: Please try again.");
		}
	}
}