package exercise1;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        // Create an account with an initial balance of $500
        Account account = new Account(500);

        // Create an ExecutorService with a thread pool of size 3
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Create a list to hold the transactions
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(account, "deposit", 200));
        transactions.add(new Transaction(account, "withdraw", 100));
        transactions.add(new Transaction(account, "deposit", 50));
        transactions.add(new Transaction(account, "withdraw", 30));
        transactions.add(new Transaction(account, "withdraw", 500)); // Insufficient funds

        // Submit each transaction to the executor
        for (Transaction transaction : transactions) {
            executor.execute(transaction);
        }

        // Shutdown the executor
        executor.shutdown();
    }
}
