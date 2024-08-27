/*Create a comprehensive Java program for an online banking system. This system should allow users to create accounts, deposit and withdraw funds, transfer money between accounts, view transaction history, and manage personal information*/

import java.util.Scanner;
import java.util.ArrayList;

class BankAccount {
    String AccountHolderName, AccountNumber;
    char AccountType;
    double balance;
    ArrayList<String> TransactionHistory;

    BankAccount(String name, String number, char type, double balance) {
        this.AccountHolderName = name;
        this.AccountNumber = number;
        this.AccountType = type;
        this.balance = balance;
        this.TransactionHistory = new ArrayList<>();
        addTransaction("Account created with balance: " + balance);
    }

    void DepositMoney(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited Amount: " + amount + "\nNew Balance: " + balance);
            addTransaction("Deposited: " + amount);
        } else {
            System.out.println("Invalid Amount");
        }
    }

    void WithdrawMoney(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew Amount: " + amount + "\nRemaining Balance: " + balance);
            addTransaction("Withdrew: " + amount);
        } else {
            System.out.println("Invalid Amount or Insufficient Balance");
        }
    }

    void TransferMoney(BankAccount TargetAccount, double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.WithdrawMoney(amount);
            TargetAccount.DepositMoney(amount);
            System.out.println("Transferred: " + amount + " to Account Number: " + TargetAccount.getAccountNumber());
            addTransaction("Transferred: " + amount + " to " + TargetAccount.getAccountNumber());
        } else {
            System.out.println("Invalid Amount or Insufficient Balance");
        }
    }

    void DisplayAccountInfo() {
        System.out.println("Account Holder Name: " + AccountHolderName + "\nAccount Number: " + AccountNumber + "\nAccount Type: " + (AccountType == 'S' ? "Savings" : "Current") + "\nBalance: " + balance);
    }

    void DisplayTransactionHistory() {
        System.out.println("Transaction History for Account Number: " + AccountNumber);
        for (String transaction : TransactionHistory) {
            System.out.println(transaction);
        }
    }

    void addTransaction(String TransactionDetail) {
        TransactionHistory.add(TransactionDetail);
    }

    String getAccountNumber() {
        return AccountNumber;
    }
}

class Task1
{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();
        while (true) {
            System.out.println("--------WELCOME TO ONLINE BANKING SYSTEM--------" + 
                               "\n1. Create Account" +
                               "\n2. Deposit Money" +
                               "\n3. Withdraw Money" +
                               "\n4. Transfer Money" +
                               "\n5. View Transaction History" +
                               "\n6. Display Account Information" +
                               "\n7. Exit\nChoose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter Account Holder Name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter Account Number: ");
                    String number = sc.nextLine();
                    System.out.println("Enter Account Type (S for Savings, C for Current): ");
                    char type = sc.nextLine().charAt(0);
                    System.out.println("Enter Initial Balance: ");
                    double balance = sc.nextDouble();
                    BankAccount account = new BankAccount(name, number, type, balance);
                    accounts.add(account);
                    System.out.println("ACCOUNT CREATED SUCCESSFULLY!");
                    break;
                case 2:
                    System.out.println("Enter Account Number: ");
                    String DepositAccountNumber = sc.nextLine();
                    BankAccount DepositAccount = findAccountByNumber(accounts, DepositAccountNumber);
                    if (DepositAccount != null) {
                        System.out.println("Enter Amount to Deposit: ");
                        double DepositAmount = sc.nextDouble();
                        DepositAccount.DepositMoney(DepositAmount);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 3:
                    System.out.println("Enter Account Number: ");
                    String WithdrawAccountNumber = sc.nextLine();
                    BankAccount WithdrawAccount = findAccountByNumber(accounts, WithdrawAccountNumber);
                    if (WithdrawAccount != null) {
                        System.out.println("Enter Amount to Withdraw: ");
                        double WithdrawAmount = sc.nextDouble();
                        WithdrawAccount.WithdrawMoney(WithdrawAmount);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 4:
                    System.out.println("Enter Your Account Number: ");
                    String fromAccountNumber = sc.nextLine();
                    BankAccount fromAccount = findAccountByNumber(accounts, fromAccountNumber);
                    if (fromAccount != null) {
                        System.out.println("Enter Target Account Number: ");
                        String toAccountNumber = sc.nextLine();
                        BankAccount toAccount = findAccountByNumber(accounts, toAccountNumber);
                        if (toAccount != null) {
                            System.out.println("Enter Amount to Transfer: ");
                            double TransferAmount = sc.nextDouble();
                            fromAccount.TransferMoney(toAccount, TransferAmount);
                        } else {
                            System.out.println("Target Account not found!");
                        }
                    } else {
                        System.out.println("Your Account not found!");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    String HistoryAccountNumber = sc.nextLine();
                    BankAccount HistoryAccount = findAccountByNumber(accounts, HistoryAccountNumber);
                    if (HistoryAccount != null) {
                        HistoryAccount.DisplayTransactionHistory();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 6:
                    System.out.print("Enter Account Number: ");
                    String infoAccountNumber = sc.nextLine();
                    BankAccount infoAccount = findAccountByNumber(accounts, infoAccountNumber);
                    if (infoAccount != null) {
                        infoAccount.DisplayAccountInfo();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;
                case 7:
                    System.out.println("Thank you for using the Online Banking System. Visit Again!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please choose a valid option.");
            }
        }
    }

    private static BankAccount findAccountByNumber(ArrayList<BankAccount> accounts, String AccountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(AccountNumber)) {
                return account;
            }
        }
        return null;
    }
}
