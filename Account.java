import java.io.Serializable;
import java.util.ArrayList;

public abstract class Account implements Serializable {
    private String accountNumber;
    private String accountHolderName;
    protected double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    public Account(String accountNumber, String accountHolderName, double balance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    public abstract void calculateInterest();

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
            addTransaction("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrew: $" + amount);
            return true;
        } else {
            System.out.println("Withdrawal failed: Insufficient balance or invalid amount.");
            return false;
        }
    }


    @Override
    public String toString() {
        return "Account [Account Number=" + accountNumber + ", Account Holder=" + accountHolderName + ", Balance=$" + balance + "]";
    }
}
