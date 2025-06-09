package atminterface;

import java.util.Scanner;
import java.util.logging.Logger;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }
    private static final Logger logger = Logger.getLogger(ATM.class.getName());

    public void displayMenu() {
        logger.info("\n🏧 ATM Menu:");
        logger.info("1. Withdraw");
        logger.info("2. Deposit");
        logger.info("3. Check Balance");
        logger.info("4. Exit");
        logger.info("Choose an option (1-4):");
    }
    

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info(String.format("✅ Withdrawal successful. 💸 Amount withdrawn: ₹%.2f", amount));
            }
        } else {
            logger.warning("❌ Insufficient balance or invalid amount.");
        }
    }

    public void deposit(double amount) {
        if (account.deposit(amount)) {
            if (logger.isLoggable(java.util.logging.Level.INFO)) {
                logger.info(String.format("✅ Deposit successful. 💰 Amount deposited: ₹%.2f", amount));
            }
        } else {
            logger.warning("❌ Invalid deposit amount.");
        }
    }

    public void checkBalance() {
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info(String.format("💼 Current balance: ₹%.2f%n", account.getBalance()));
        }
    }
}


// Main class to run the ATM interface
public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize bank account with a starting balance
        BankAccount userAccount = new BankAccount(1000.00);
        ATM atm = new ATM(userAccount);

        boolean running = true;

        Logger.getLogger(ATMInterface.class.getName()).info("🏦 Welcome to the Java ATM!");

        while (running) {
            atm.displayMenu();
            int choice;

            // Validate menu input
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                Logger.getLogger(ATMInterface.class.getName()).warning("❌ Invalid input. Please enter a number (1-4).");
                scanner.next(); // Consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    Logger.getLogger(ATMInterface.class.getName()).info("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;

                case 2:
                    Logger.getLogger(ATMInterface.class.getName()).info("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;

                case 3:
                    atm.checkBalance();
                    break;

                case 4:
                    Logger.getLogger(ATMInterface.class.getName()).info("👋 Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;

                default:
                    Logger.getLogger(ATMInterface.class.getName()).warning("❌ Invalid option. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}
