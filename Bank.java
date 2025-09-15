import java.io.*;
import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    // Create a new account
    public void createSavingsAccount(String accountNumber, String accountHolderName, double balance, String pin, double interestRate) {
        SavingsAccount account = new SavingsAccount(accountNumber, accountHolderName, balance, pin, interestRate);
        accounts.add(account);
        System.out.println("Savings Account created successfully.");
    }

    public void createCheckingAccount(String accountNumber, String accountHolderName, double balance, String pin) {
        CheckingAccount account = new CheckingAccount(accountNumber, accountHolderName, balance, pin);
        accounts.add(account);
        System.out.println("Checking Account created successfully.");
    }

    public Account findAccount(String accountNumber, String pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {

                if (pin == null || account.getPin().equals(pin)) {
                    return account;
                }
            }
        }
        return null;
    }


    public void transferMoney(String fromAccountNumber, String fromPin, String toAccountNumber, double amount) {
        System.out.println("Initiating transfer...");


        Account fromAccount = findAccount(fromAccountNumber, fromPin);

        if (fromAccount == null) {
            System.out.println("Transfer failed: Invalid sender account details.");
            return;
        } else {
            System.out.println("Sender account found: " + fromAccountNumber);
        }

        Account toAccount = findAccount(toAccountNumber, null);

        if (toAccount == null) {
            System.out.println("Transfer failed: Invalid recipient account details.");
            return;
        } else {
            System.out.println("Recipient account found: " + toAccountNumber);
        }


        if (fromAccount.withdraw(amount)) {
            System.out.println("Withdrawal from sender successful.");
            toAccount.deposit(amount);
            System.out.println("Deposit to recipient successful.");

            fromAccount.addTransaction("Transferred $" + amount + " to Account: " + toAccountNumber);
            toAccount.addTransaction("Received $" + amount + " from Account: " + fromAccountNumber);
            System.out.println("Transfer of $" + amount + " from " + fromAccountNumber + " to " + toAccountNumber + " was successful.");
        } else {
            System.out.println("Transfer failed: Insufficient balance in sender account.");
        }
    }


    public void applyInterestToSavings() {
        for (Account account : accounts) {
            if (account instanceof SavingsAccount) {
                account.calculateInterest();
            }
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(accounts);
            System.out.println("Accounts saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            accounts = (ArrayList<Account>) in.readObject();
            System.out.println("Accounts loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
