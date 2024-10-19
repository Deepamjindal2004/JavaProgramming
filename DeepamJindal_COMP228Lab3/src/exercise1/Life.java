package exercise1;

public class Life extends Insurance {

    public Life() {
        super("Life Insurance");
    }

    @Override
    void setInsuranceCost(double inputFee) {
        monthlyCost = inputFee;
    }

    @Override
    void displayInfo() {
        System.out.println("Your insurance type is: " + getTypeOfInsurance());
        System.out.println("Your monthly cost is: $" + getMonthlyCost());
    }
}
