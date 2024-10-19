package exercise2;

import java.util.Scanner;

public class GameTesterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose Game Tester Type:");
        System.out.println("1. Full-Time Game Tester");
        System.out.println("2. Part-Time Game Tester");
        System.out.print("Enter your choice (1 or 2): ");
        String choice = scanner.nextLine();

        System.out.print("Enter the name of the Game Tester: ");
        String name = scanner.nextLine();

        GameTester tester = null;

        if (choice.equals("1")) {
            tester = new FullTimeGameTester(name);
            System.out.printf("The salary for %s (Full-Time) is: %.2f%n", tester.getName(), tester.calculateSalary());
        } else if (choice.equals("2")) {
            System.out.print("Enter the number of hours worked: ");
            int hoursWorked;
            while (true) {
                if (scanner.hasNextInt()) {
                    hoursWorked = scanner.nextInt();
                    if (hoursWorked >= 0) break;
                }
                System.out.print("Please enter a valid number of hours worked: ");
                scanner.nextLine(); // Clear the invalid input
            }
            tester = new PartTimeGameTester(name, hoursWorked);
            System.out.printf("The salary for %s (Part-Time) is: %.2f%n", tester.getName(), tester.calculateSalary());
        } else {
            System.out.println("Invalid choice. Please select either 1 or 2.");
        }

        scanner.close();
    }
}
