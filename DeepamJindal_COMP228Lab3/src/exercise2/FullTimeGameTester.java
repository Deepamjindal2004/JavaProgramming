package exercise2;

public class FullTimeGameTester extends GameTester {
    private static final double BASE_SALARY = 3000.0;

    public FullTimeGameTester(String name) {
        super(name, true);
    }

    @Override
    public double calculateSalary() {
        return BASE_SALARY;
    }
}
