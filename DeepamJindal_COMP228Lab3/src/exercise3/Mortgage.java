package exercise3;

public abstract class Mortgage implements MortgageConstants {
    private String mortgageNumber;
    private String customerName;
    private double mortgageAmount;
    private double interestRate;
    private int term;

    public Mortgage(String mortgageNumber, String customerName, double mortgageAmount, double interestRate, int term) {
        if (mortgageAmount > MAX_MORTGAGE_AMOUNT) {
            throw new IllegalArgumentException("Mortgage amount cannot exceed $300,000.");
        }
        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        this.mortgageAmount = mortgageAmount;
        this.interestRate = interestRate;
        this.term = (term == 1 || term == 3 || term == 5) ? term : SHORT_TERM;
    }

    public abstract String getMortgageInfo();

    public double calculateTotalAmountOwed() {
        double totalInterest = (mortgageAmount * interestRate / 100) * term;
        return mortgageAmount + totalInterest;
    }

    public String getMortgageNumber() {
        return mortgageNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getMortgageAmount() {
        return mortgageAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTerm() {
        return term;
    }
}
