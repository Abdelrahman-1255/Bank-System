public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, String pin, double interestRate) {
        super(accountNumber, accountHolderName, balance, pin);
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest of $" + interest + " added to savings account.");
        addTransaction("Interest added: $" + interest);
    }

    @Override
    public String toString() {
        return "Savings Account: " + super.toString();
    }
}
