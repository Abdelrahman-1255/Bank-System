import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        String filename = "accounts.dat";


        bank.loadFromFile(filename);

        int choice;
        do {
            System.out.println("\nBanking System");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Checking Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. View Transaction History");
            System.out.println("7. Transfer Money");
            System.out.println("8. Apply Interest to Savings Accounts");
            System.out.println("9. Save and Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();
                    System.out.print("Set a 4-digit PIN: ");
                    String pin = scanner.next();
                    System.out.print("Enter interest rate: ");
                    double interestRate = scanner.nextDouble();
                    bank.createSavingsAccount(accountNumber, name, balance, pin, interestRate);
                    break;

                case 2:
                    System.out.print("Enter account holder name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    balance = scanner.nextDouble();
                    System.out.print("Set a 4-digit PIN: ");
                    pin = scanner.next();
                    bank.createCheckingAccount(accountNumber, name, balance, pin);
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    pin = scanner.next();
                    Account depositAccount = bank.findAccount(accountNumber, pin);
                    if (depositAccount != null) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Invalid account number or PIN.");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    pin = scanner.next();
                    Account withdrawAccount = bank.findAccount(accountNumber, pin);
                    if (withdrawAccount != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawalAmount);
                    } else {
                        System.out.println("Invalid account number or PIN.");
                    }
                    break;

                case 5:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    pin = scanner.next();
                    Account balanceAccount = bank.findAccount(accountNumber, pin);
                    if (balanceAccount != null) {
                        System.out.println("Current balance: $" + balanceAccount.getBalance());
                    } else {
                        System.out.println("Invalid account number or PIN.");
                    }
                    break;

                case 6:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter PIN: ");
                    pin = scanner.next();
                    Account historyAccount = bank.findAccount(accountNumber, pin);
                    if (historyAccount != null) {
                        historyAccount.viewTransactionHistory();
                    } else {
                        System.out.println("Invalid account number or PIN.");
                    }
                    break;

                case 7:
                    System.out.print("Enter your account number: ");
                    String fromAccount = scanner.nextLine();
                    System.out.print("Enter your PIN: ");
                    String fromPin = scanner.next();
                    System.out.print("Enter recipient's account number: ");
                    String toAccount = scanner.next();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    bank.transferMoney(fromAccount, fromPin, toAccount, transferAmount);
                    break;

                case 8:
                    bank.applyInterestToSavings();
                    break;

                case 9:
                    bank.saveToFile(filename);
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please choose again.");
            }
        } while (choice != 9);

        scanner.close();
    }
}
