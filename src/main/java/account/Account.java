package account;

import java.util.ArrayList;

public class Account extends ArrayList<Account> {

    private String accountType;
    private Double balance;
    private String accountId;
    private Double depositAmount;
    private Double withdrawAmount;



   public Account()
   {

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
    public void withdraw(Double withdrawAmount) {
       if(withdrawAmount <= balance) {
           setBalance(balance - withdrawAmount);
           System.out.println("Your remaining balance is" + balance);
       } else
        System.out.println("You are not that rich, you only have" + balance + "in your account.\nTry staying within your budget.");
    }

}
