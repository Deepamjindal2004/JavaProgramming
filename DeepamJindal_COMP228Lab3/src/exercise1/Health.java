package exercise1;

public class Health extends Insurance {

    //constructor
    public Health() {
        super("Health Insurance");
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
