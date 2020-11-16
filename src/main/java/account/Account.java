package account;

import consoleMenu.Console;
import user.UserManager;

import java.util.ArrayList;

public class Account extends ArrayList<Account> {

    private String accountType;
    private Double balance;
    private String accountId;
    private Double depositAmount;
    private Double withdrawAmount;
    private Double transferAmount;
    // correct me if this is wrong on print history
    private ArrayList<String> printHistory = new ArrayList<String>();


    public Account() {
    }

    public Account(String accountId, Double balance, String accountType) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void deposit(Double depositAmount) {
        setBalance(balance + depositAmount);
        System.out.println("Your new balance is " + balance);
        printHistory.add("Deposit: " + depositAmount.toString() + " Balance: " + balance.toString() +" Account: " + getAccountId().toString());
    }

    public double withdraw(Double withdrawAmount) {
        Double newWithdrawAmount = withdrawAmount;
        while (newWithdrawAmount > getBalance()) {
            System.out.println("You are not that rich, you only have " + getBalance() + " in your account.\nTry staying within your budget.");
            newWithdrawAmount = Console.getDoubleInput("Please enter the amount :  ");
        }
        setBalance(getBalance() - newWithdrawAmount);
        System.out.println("Your remaining balance is " + getBalance());
        printHistory.add("Withdraw: " + withdrawAmount.toString() + " Balance: " + balance.toString() + " Account: " + getAccountId().toString());
        return getBalance();
    }

    public void printHistory() {
        for (String s : printHistory) {
            System.out.println(s);
        }
    }
}


