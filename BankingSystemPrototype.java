//module banking_Information_System {
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
//	            System.out.println("Transaction history:");
	            // ...
	        } else {
	            System.out.println("Invalid account number.");
	        }
	    }
	}

	public class BankingSystemPrototype {
	    public static void main(String[] args) {
	        BankingSystem bankingSystem = new BankingSystem();

	        // User registration
	        String accountNumber = bankingSystem.registerUser("John Doe", "123 Main St", "john@example.com", 1000.0);
	        System.out.println("Registration successful. Account Number: " + accountNumber);

	        // Update account information
	        bankingSystem.updateAccountInfo(accountNumber, "John Doe", "456 Elm St", "john@example.com");

	        // Deposit
	        bankingSystem.deposit(accountNumber, 500.0);

	        // Withdrawal
	        bankingSystem.withdraw(accountNumber, 200.0);

	        // Fund transfer
	        String recipientAccountNumber = bankingSystem.registerUser("Jane Smith", "789 Oak St", "jane@example.com", 0.0);
	        bankingSystem.transferFunds(accountNumber, recipientAccountNumber, 300.0);

	        // Display account statement
	        bankingSystem.displayAccountStatement(accountNumber);
	    }
	}

//}