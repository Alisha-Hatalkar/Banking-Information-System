import java.util.*;

class User {
    private String name;
    private String address;
    private String contactInformation;
    private double balance;

    // Constructor
    public User(String name, String address, String contactInformation, double initialDeposit) {
        this.name = name;
        this.address = address;
        this.contactInformation = contactInformation;
        this.balance = initialDeposit;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class BankingSystem {
    private Map<String, User> users;
    private int accountNumberCounter;

    public BankingSystem() {
        users = new HashMap<>();
        accountNumberCounter = 1;
    }

    public String registerUser(String name, String address, String contactInformation, double initialDeposit) {
        String accountNumber = String.format("%06d", accountNumberCounter++);
        User user = new User(name, address, contactInformation, initialDeposit);
        users.put(accountNumber, user);
        return accountNumber;
    }

    public void updateAccountInfo(String accountNumber, String name, String address, String contactInformation) {
        User user = users.get(accountNumber);
        if (user != null) {
            user.setName(name);
            user.setAddress(address);
            user.setContactInformation(contactInformation);
            System.out.println("Account information updated successfully.");
        } else {
            System.out.println("Invalid account number.");
        }
    }

    public void deposit(String accountNumber, double amount) {
        User user = users.get(accountNumber);
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            System.out.println("Deposit successful. New balance: " + user.getBalance());
        } else {
            System.out.println("Invalid account number.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        User user = users.get(accountNumber);
        if (user != null) {
            if (amount <= user.getBalance()) {
                user.setBalance(user.getBalance() - amount);
                System.out.println("Withdrawal successful. New balance: " + user.getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Invalid account number.");
        }
    }

    public void transferFunds(String senderAccountNumber, String recipientAccountNumber, double amount) {
        User sender = users.get(senderAccountNumber);
        User recipient = users.get(recipientAccountNumber);

        if (sender != null && recipient != null) {
            if (amount <= sender.getBalance()) {
                sender.setBalance(sender.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                System.out.println("Fund transfer successful.");
                System.out.println("Sender's new balance: " + sender.getBalance());
                System.out.println("Recipient's new balance: " + recipient.getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Invalid account number(s).");
        }
    }

    public void displayAccountStatement(String accountNumber) {
        User user = users.get(accountNumber);
        if (user != null) {
            System.out.println("Account Statement for Account Number: " + accountNumber);
            // Display transaction history, dates, amounts, and remaining balances
            System.out.println("Transaction history:");
            // ...
        } else {
            System.out.println("Invalid account number.");
        }
    }
}

public class BankingSystemPrototype {
    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("********** Banking Information System **********");
            System.out.println("1. Register User");
            System.out.println("2. Update Account Information");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Display Account Statement");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter contact information: ");
                    String contactInformation = scanner.nextLine();
                    System.out.print("Enter initial deposit amount: ");
                    double initialDeposit = scanner.nextDouble();

                    String accountNumber = bankingSystem.registerUser(name, address, contactInformation, initialDeposit);
                    System.out.println("Registration successful. Account Number: " + accountNumber);
                    break;
                case 2:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter updated name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter updated address: ");
                    address = scanner.nextLine();
                    System.out.print("Enter updated contact information: ");
                    contactInformation = scanner.nextLine();

                    bankingSystem.updateAccountInfo(accountNumber, name, address, contactInformation);
                    break;
                case 3:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();

                    bankingSystem.deposit(accountNumber, depositAmount);
                    break;
                case 4:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();

                    bankingSystem.withdraw(accountNumber, withdrawalAmount);
                    break;
                case 5:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter sender's account number: ");
                    String senderAccountNumber = scanner.nextLine();
                    System.out.print("Enter recipient's account number: ");
                    String recipientAccountNumber = scanner.nextLine();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();

                    bankingSystem.transferFunds(senderAccountNumber, recipientAccountNumber, transferAmount);
                    break;
                case 6:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();

                    bankingSystem.displayAccountStatement(accountNumber);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }
    }
}
