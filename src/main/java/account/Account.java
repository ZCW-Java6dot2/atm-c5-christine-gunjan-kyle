package account;

import java.util.ArrayList;

public class Account extends ArrayList<Account> {

    private String accountType;
    private Double balance;
    private String accountId;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


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
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) { this.balance = balance; }


}
