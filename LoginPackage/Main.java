package LoginPackage;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class Main {
    // HashMap to store the card number as the key and the User object as the value
    private static HashMap<String, User> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== TDG Bank ===");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void signUp(Scanner scanner) {
        System.out.println("\n--- Sign Up ---");

        // User credentials
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your gender (M/F): ");
        char gender = scanner.nextLine().charAt(0);

        // Birthday System
        System.out.print("Enter your birthday (YYYY-MM-DD): ");
        String birthdayInput = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(birthdayInput, formatter);

        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthday, currentDate).getYears();

        
        if (age < 18) {
            System.out.println("You must be at least 18 years old to sign up.");
            return;
        }

       
        System.out.print("Enter your phone number: ");
        int phoneNumber = scanner.nextInt();
        scanner.nextLine(); 

        // Generate card number and password
        Random random = new Random();
        String cardNumber = String.format("%08d", random.nextInt(100000000)); // 8-digit
        String password = String.format("%04d", random.nextInt(10000));    // 4-digit

        
        while (userDatabase.containsKey(cardNumber)) {
            cardNumber = String.format("%08d", random.nextInt(100000000));
        }

        // Data base
        User newUser = new User(fullName, password, gender, birthday);
        userDatabase.put(cardNumber, newUser);

        // Display card number and password
        System.out.println("\nRegistration complete!");
        System.out.println("Your card number: " + cardNumber);
        System.out.println("Your password: " + password);
        System.out.println("Please keep these credentials secure.");
    }

    public static void login(Scanner scanner) {
        System.out.println("\n--- Login ---");

        // Get card number
        System.out.print("Enter your card number: ");
        String cardNumber = scanner.nextLine();

        if (!userDatabase.containsKey(cardNumber)) {
            System.out.println("Card number does not exist. Please register.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = userDatabase.get(cardNumber);
        
        
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + user.getFullName() + "!");
        } else {
            System.out.println("Invalid card number or password. Please try again.");
        }
    }
}
