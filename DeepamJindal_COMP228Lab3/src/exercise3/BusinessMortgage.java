package exercise3;

public class BusinessMortgage extends Mortgage {
    private static final double PRIME_RATE = 3.0;

    public BusinessMortgage(String mortgageNumber, String customerName, double mortgageAmount, int term) {
        super(mortgageNumber, customerName, mortgageAmount, PRIME_RATE + 1.0, term);
    }

    @Override
    public String getMortgageInfo() {
        return String.format("Business Mortgage #%s: Customer: %s, Amount: %.2f, Interest Rate: %.2f%%, Term: %d years",
                getMortgageNumber(), getCustomerName(), getMortgageAmount(), getInterestRate(), getTerm());
    }
}
