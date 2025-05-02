import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }
    /**
     * Executes the main loop of the password generator and checker program.
     * This method displays a welcome message, presents a menu of options to the user,
     * and continuously processes user input until the user chooses to quit.
     * 
     * The loop handles the following options:
     * 1. Generate a new password
     * 2. Check password strength
     * 3. Display password security information
     * 4. Quit the program
     * 
     * After each operation (except quitting), the menu is reprinted for the next selection.
     * If an invalid option is entered, an error message is displayed and the menu is reprinted.
     * 
     * This method does not take any parameters and does not return any value.
     * It relies on class-level variables and methods to handle user input and perform operations.
     */
    public void mainLoop() {
        System.out.println("Welcome to the Thunderdome, baby. How can we be of service today?? :)");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {
            userOption = keyboard.next();

            switch (userOption) {
                case "1":
                    requestPassword();
                    printMenu();
                    break;
                case "2":
                    checkPassword();
                    printMenu();
                    break;
                case "3":
                    printUsefulInfo();
                    printMenu();
                    break;
                case "4":
                    printQuitMessage();
                    break;
                default:
                    System.out.println("Kindly select one of the available commands");
                    printMenu();
                    break;
            }
        }
    }






    /**
     * Generates a random password based on the specified length and the current alphabet configuration.
     *
     * This method creates a password by randomly selecting characters from the alphabet
     * defined in the Generator class. The selection process ensures an even distribution
     * of characters across the entire alphabet range.
     *
     * @param   length    The desired length of the password. Must be greater than 0.
     * @return            A new Password object containing the generated password string.
     * @throws  IllegalArgumentException    If the specified length is less than 1.
     * @see     Alphabet
     * @see     Password
     * @since   1.0.0
     */
    private Password GeneratePassword(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Password length must be at least 1");
        }
        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(pass.toString());
    }




/**
 * Displays a comprehensive list of password security tips to the user.
 *
 * This method prints a series of best practices and guidelines for creating
 * and managing secure passwords. The tips cover various aspects of password
 * security, including length, complexity, uniqueness, and general security
 * practices.
 *
 * The information is presented in a numbered list format, making it easy
 * for users to read and remember. Each tip is printed on a new line for
 * better readability.
 *
 * Usage note: This method is typically called when the user selects the
 * option to view password security information from the main menu.
 *
 * @since 1.0.0
 */
public void printUsefulInfo() {
    System.out.println("\n=== Password Security Tips ===");
    System.out.println("1. Use a minimum password length of 8 or more characters if permitted");
    System.out.println("2. Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");
    System.out.println("3. Generate passwords randomly where feasible");
    System.out.println("4. Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");
    System.out.println("5. Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences,\n   usernames, relative or pet names, romantic links (current or past) and biographical information (e.g., ID numbers, ancestors' names or dates).");
    System.out.println("6. Avoid using information that the user's colleagues and/or acquaintances might know to be associated with the user");
    System.out.println("7. Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    System.out.println("8. Use a unique password for each of your important accounts");
    System.out.println("9. Use a password manager to generate and store complex passwords securely");
    System.out.println("10. Enable two-factor authentication (2FA) whenever possible for additional security");
    System.out.println("11. Regularly update your passwords, especially if you suspect they might have been compromised");
    System.out.println("12. Avoid sharing your passwords with others, even if they claim to be from IT support");
    System.out.println("13. Do not use the same password for multiple accounts if the accounts are not related to each other");
    System.out.println("14. Consider using passphrases: long sequences of random words that are easy to remember but hard to crack");
    System.out.println("15. Set up security questions with answers that are not easily guessable or found on social media");
    System.out.println("16. If you suspect your password has been compromised, contact your IT support team immediately");
    System.out.println("==============================");
}





    public void requestPassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams;

        System.out.println("Let's get started. Enter the desired password length:");

        do {
            String input;
            correctParams = false;

            do {
                System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeLower = true;

            do {
                System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeUpper = true;

            do {
                System.out.println("Do you want Numbers \"1234...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeNum = true;

            do {
                System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
                input = keyboard.next();
                PasswordRequestError(input);
            } while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no"));

            if (isInclude(input)) IncludeSym = true;

            //No Pool Selected
            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("You have selected no characters to generate your " +
                        "password, at least one of your answers should be Yes\n");
                correctParams = true;
            }

        } while (correctParams);

        System.out.println("Great! Now enter the length of the password");
        int length = keyboard.nextInt();

        final Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        final Password password = generator.GeneratePassword(length);

        System.err.println("Your generated password -> " + password);
    }

    boolean isInclude(String Input) {                  // Determines if the input string indicates inclusion
        if (Input.equalsIgnoreCase("yes")) {           // Checks if the input is "yes" (case-insensitive)
            return true;                               // Returns true if the input is "yes"
        } 
        else {
            return false;                              // Returns false for any input other than "yes"
        }
    }



    public void PasswordRequestError(String i) {
        if (!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }


    /**
     * Prompts the user to enter a password and checks its strength.
     * This method asks the user to input a password, creates a Password object,
     * calculates the password's strength score, and displays the result.
     * 
     * The strength calculation is performed by the Password class's calculateScore method.
     * The exact scoring mechanism is defined in the Password class.
     * 
     * This method does not return any value but prints the score to the console.
     */
    public void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = keyboard.next();

        final Password p = new Password(input);

        System.out.println(p.calculateScore());
    }

public void printMenu() {
    System.out.println("\n==========================================");
    System.out.println("   Password Generator and Checker Menu");
    System.out.println("==========================================");
    System.out.println("1. Generate a new password");
    System.out.println("2. Check password strength");
    System.out.println("3. Display password security information");
    System.out.println("4. Quit");
    System.out.println("==========================================");
    System.out.print("Please enter your choice (1-4): ");
}
    public void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
