package exercise1;

import java.util.Scanner;

public class InsuranceDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] types = new String[]{"Health", "Life"};

        System.out.print("Please select your insurance type (Health/Life): ");
        String userSelect = scanner.nextLine();

        Insurance insurance = null;

        if (userSelect.equalsIgnoreCase(types[0])) {
            insurance = new Health();
        } else if (userSelect.equalsIgnoreCase(types[1])) {
            insurance = new Life();
        } else {
            System.out.println("Invalid selection. Please enter either 'Health' or 'Life'.");
            return;
        }

        System.out.print("Please enter your monthly fee: ");
        double inputFee = scanner.nextDouble();

        insurance.setInsuranceCost(inputFee);

        System.out.println("\nInsurance Details:");
        insurance.displayInfo();

        scanner.close();
    }
}
