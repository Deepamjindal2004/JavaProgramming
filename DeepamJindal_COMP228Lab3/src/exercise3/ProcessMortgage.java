package exercise3;

import java.util.Scanner;

public class ProcessMortgage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mortgage[] mortgages = new Mortgage[3];

        System.out.print("Enter the current prime interest rate: ");
        double currentPrimeRate = scanner.nextDouble();
        scanner.nextLine();

        for (int i = 0; i < mortgages.length; i++) {
            System.out.println("Enter details for mortgage " + (i + 1) + ":");

            System.out.print("Enter mortgage type (1 for Business, 2 for Personal): ");
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter mortgage number: ");
            String mortgageNumber = scanner.nextLine();

            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter mortgage amount (max $300,000): ");
            double mortgageAmount = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter mortgage term (1 for Short-term, 3 for Medium-term, 5 for Long-term): ");
            int term = scanner.nextInt();
            scanner.nextLine();

            if (type == 1) {
                mortgages[i] = new BusinessMortgage(mortgageNumber, customerName, mortgageAmount, term);
            } else if (type == 2) {
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, mortgageAmount, term);
            } else {
                System.out.println("Invalid mortgage type. Please enter 1 or 2.");
                i--;
                continue;
            }
        }

        System.out.println("\nAll Mortgages:");
        for (Mortgage mortgage : mortgages) {
            System.out.println(mortgage.getMortgageInfo());
            System.out.printf("Total Amount Owed: %.2f%n", mortgage.calculateTotalAmountOwed());
        }

        scanner.close();
    }
}
