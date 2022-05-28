import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class contains ways to prompt the user for information and ensure that
 * the information given is acceptable and fits within the boundaries given to
 * work with the rest of the program.
 * 
 * @author Aaron and Raine
 *
 */
public class UserInputManager {

	/**
	 * This method prompts the user for a true/false question and gives two possible
	 * answers, one corresponding to true and the other false.
	 * 
	 * @param prompt       This is the true or false type question which the user
	 *                     will be asked
	 * @param errorMessage This is a message printed when the user input does not
	 *                     match either answer
	 * @param trueAnswer   This is the string corresponding to "true"
	 * @param falseAnswer  This is the string corresponding to "false"
	 * @return the boolean of if the user answered true or false to the prompt
	 */
	public boolean booleanInput(String prompt, String errorMessage, String trueAnswer, String falseAnswer) {
		Scanner reader = new Scanner(System.in);
		boolean validInput = false;
		boolean result = false;

		//loop of asking for input
		while (!validInput) {
			//prompt user
			System.out.print(prompt + " (" + trueAnswer + ", " + falseAnswer + ")");
			String input = reader.next(); 
			if (input.toLowerCase().equals(trueAnswer)) {
				//user input dictates "true" outcome
				validInput = true;
				result = true;
			} else if (input.toLowerCase().equals(falseAnswer)) {
				//user input dictates "false" outcome
				validInput = true;
				result = false;
			} else {
				//user input is invalid
				System.out.println(errorMessage);
			}
		}
		return result;
	}

	/**
	 * This method prompts the user for a string. It reads out a prompt and if the
	 * user gives an answer that is not accepted, it prints out an error message and
	 * loops. It serves to confirm the answer the user gave is acceptable and if it
	 * is, it returns it.
	 * 
	 * @param prompt       This is the prompt the user must respond to in the
	 *                     console.
	 * @param errorMessage This is the message displayed on a non permitted answer
	 * @param options      This is the string list of acceptable answers the user
	 *                     may give
	 * @return the confirmed valid answer given by the user
	 */
	public String stringInput(String prompt, String errorMessage, ArrayList<String> options) {
		Scanner reader = new Scanner(System.in);
		boolean validInput = false;
		String finalInput = "";
		
		//loop asking for user input
		while (!validInput) {
			//prompt user
			System.out.print(prompt + stringOptions(options));
			String input = reader.next();
			if (options.contains(input.toLowerCase())) {
				//if user input is one of the valid options
				validInput = true;
				finalInput = input;
			} else {
				//user input not a valid option
				System.out.println(errorMessage);
			}
		}
		return finalInput.toLowerCase();
	}

	/**
	 * This method prompts the user for an integer. It gives the possible options
	 * within a range the user may select from, if the user gives a non accepted
	 * answer, it displays an error message and repeats. It confirms if the integer
	 * the user gave is acceptable and if it is, it returns it.
	 * 
	 * @param prompt       This is the prompt that will be given to the user.
	 * @param errorMessage This will be printed to the console upon invalid answer
	 *                     to the prompt
	 * @param min          This is the minimum accepted integer value the user may
	 *                     answer
	 * @param max          This is the maximum accepted integer value the user may
	 *                     answer
	 * @return This returns the confirmed valid answer the user has given
	 */
	public int intInput(String prompt, String errorMessage, int min, int max) {
		Scanner reader = new Scanner(System.in);
		boolean validInput = false;
		int inputNum = 0;

		//loop for asking user for input
		while (!validInput) {
			System.out.print(prompt + intOptions(min, max));
			String input = reader.next();
			try {
				inputNum = Integer.parseInt(input);
				if (inputNum >= min && inputNum <= max)
					validInput = true;
			} catch (Exception e) {
				//user doesn't input integer
				System.out.println(errorMessage);
			}
			if (!validInput)
				//invalid input
				System.out.println(errorMessage);
		}
		return inputNum;
	}

	/**
	 * This method gives a string that will be added at the end of the prompt
	 * so the user knows what their options are.
	 * 
	 * @param options This is the list of acceptable choices for the user
	 * @return The string informing the user what their choices are
	 */
	private String stringOptions(ArrayList<String> options) {
		String finalString = " (";
		for (int optionN = 0; optionN < options.size(); optionN++) {
			finalString += options.get(optionN);
			if (optionN < options.size() - 1)
				finalString += ", ";
		}
		finalString += ")";
		return finalString;
	}

	/**
	 * This method gives a string which says what the minimum and maximum integer
	 * inputs for something may be. It is used to prompt the user and make sure they
	 * know what the bounds of their choice are.
	 * 
	 * @param min This is the minimum integer the user may choose
	 * @param max This is the maximum integer the user may choose
	 * @return The string saying what the integer range the user may choose for an
	 *         input is
	 */
	private String intOptions(int min, int max) {
		return " (between " + min + " and " + max + ")";
	}
}