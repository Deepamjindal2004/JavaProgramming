package exercise3;

public class PersonalMortgage extends Mortgage {
    private static final double PRIME_RATE = 3.0;

    public PersonalMortgage(String mortgageNumber, String customerName, double mortgageAmount, int term) {
        super(mortgageNumber, customerName, mortgageAmount, PRIME_RATE + 2.0, term);
    }

    @Override
    public String getMortgageInfo() {
        return String.format("Personal Mortgage #%s: Customer: %s, Amount: %.2f, Interest Rate: %.2f%%, Term: %d years",
                getMortgageNumber(), getCustomerName(), getMortgageAmount(), getInterestRate(), getTerm());
    }
}
