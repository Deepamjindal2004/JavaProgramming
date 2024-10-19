package exercise1;

public abstract class Insurance {

    public String typeOfInsurance;
    public double monthlyCost;

    public Insurance(String type) {
        typeOfInsurance = type;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    abstract void setInsuranceCost(double inputFee);
    abstract void displayInfo();
}
