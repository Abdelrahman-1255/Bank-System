public class CheckingAccount extends Account {
    public CheckingAccount(String accountNumber, String accountHolderName, double balance, String pin) {
        super(accountNumber, accountHolderName, balance, pin);
    }

    @Override
    public void calculateInterest() {
        System.out.println("Checking accounts do not accrue interest.");
    }

    @Override
    public String toString() {
        return "Checking Account: " + super.toString();
    }
}
