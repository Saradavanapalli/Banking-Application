////
package code_alpha;




import java.util.*;

class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private double interestRate; // Annual interest rate

    public BankAccount(String accountNumber, String ownerName, double initialBalance, double interestRate) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.interestRate = interestRate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit of $" + amount + " successful.");
        } else {
            System.out.println("Invalid amount. Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal of $" + amount + " successful.");
        } else if (amount > 0 && balance < amount) {
            System.out.println("Insufficient funds. Withdrawal amount exceeds balance.");
        } else {
            System.out.println("Invalid amount. Withdrawal amount must be positive.");
        }
    }

    public void checkBalance() {
        System.out.println("Account Balance: $" + balance);
    }

    public void applyInterest() {
        double monthlyInterestRate = interestRate / 12;
        double interestAmount = balance * monthlyInterestRate;
        balance += interestAmount;
        System.out.println("Interest applied. Balance after interest: $" + balance);
    }

    public void transfer(BankAccount destinationAccount, double amount) {
        if (amount > 0 && balance >= amount) {
            withdraw(amount);
            destinationAccount.deposit(amount);
            System.out.println("Transfer of $" + amount + " to account " + destinationAccount.getAccountNumber() + " successful.");
        } else if (amount > 0 && balance < amount) {
            System.out.println("Insufficient funds. Transfer amount exceeds balance.");
        } else {
            System.out.println("Invalid amount. Transfer amount must be positive.");
        }
    }
}

public class SimpleBankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Manually create bank accounts
        BankAccount account1 = new BankAccount("001", "Alice", 1000.0, 0.05); // Account number "001", Initial balance $1000, interest rate 5%
        BankAccount account2 = new BankAccount("002", "Bob", 500.0, 0.03);   // Account number "002", Initial balance $500, interest rate 3%

        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to Simple Banking Application");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Apply Interest");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account1.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account1.withdraw(withdrawalAmount);
                    break;
                case 3:
                    account1.checkBalance();
                    break;
                case 4:
                    account1.applyInterest();
                    break;
                case 5:
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter destination account number: ");
                    String destinationAccountNumber = scanner.next();
                    BankAccount destinationAccount = null;
                    if (destinationAccountNumber.equals("001")) {
                        destinationAccount = account1;
                    } else if (destinationAccountNumber.equals("002")) {
                        destinationAccount = account2;
                    } else {
                        System.out.println("Destination account not found.");
                        break;
                    }
                    account1.transfer(destinationAccount, transferAmount);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using Simple Banking Application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }

        scanner.close();
    }
}
