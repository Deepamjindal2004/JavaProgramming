package exercise2;

public class PartTimeGameTester extends GameTester {
    private int hoursWorked;
    private static final double HOURLY_RATE = 20.0;

    public PartTimeGameTester(String name, int hoursWorked) {
        super(name, false);
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * HOURLY_RATE;
    }
}
