package account;

import consoleMenu.Console;
import consoleMenu.Menu;
import user.UserManager;

import java.util.ArrayList;

public class Account extends ArrayList<Account> {

    private String accountType;
    private Double balance;
    private String accountId;
    private Double depositAmount;
    private Double withdrawAmount;
    private Double transferAmount;
    Menu menu = new Menu();

   public Account() {
   }
    public Account(String accountId, Double balance,String accountType) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType=accountType;
    }
    public String getAccountId() { return  accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public Double getBalance() { return balance; }

    public void setBalance(Double balance) { this.balance = balance; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public void deposit(Double depositAmount) {
        setBalance(balance + depositAmount);
        System.out.println("You're new balance is" + balance);
    }
    public double withdraw(Double withdrawAmount) {
       Double newWithdrawAmount = withdrawAmount;
       while(newWithdrawAmount > getBalance()) {
           System.out.println("You are not that rich, you only have " + getBalance() + " in your account.\nTry staying within your budget.");
           newWithdrawAmount = Console.getDoubleInput("Please enter the amount :  ");
       }
       setBalance(getBalance() - newWithdrawAmount);
        System.out.println("Your remaining balance is " + getBalance());
        return getBalance();
    }
    public void transfer(Double transferAmount) {
       if(transferAmount <= getBalance()) {
           setBalance(getBalance() - transferAmount);
           System.out.println("Your remaining balance is" + getBalance() + ".\n" +
                   "You transfer was successful");
       }else{
           System.out.println("I know you owe them money but money doesn't grow on trees your current " +
                   "balance is too low to transfer that much. You have" + getBalance() + "available try again.");
       }
    }
}


