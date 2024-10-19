package exercise2;

public abstract class GameTester {
    private String name;
    private boolean isFullTime;

    public GameTester(String name, boolean isFullTime) {
        this.name = name;
        this.isFullTime = isFullTime;
    }

    public String getName() {
        return name;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public abstract double calculateSalary();
}
