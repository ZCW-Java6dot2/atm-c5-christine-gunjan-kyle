package account;

import java.util.List;
import java.util.Map;

public class Account {
    private String accountNumber;
    private Integer accountType;
    private Integer balance;

    public Account(String accountId, Integer accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }
    public String getAccountNumber() { return  accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public Integer getAccountType() { return accountType; }
    public void setAccountType(Integer accountType) { this.accountType = accountType; }

    public Integer balance() {
//      return "Your balance is (String format account.balance)";
        return null;
    }
    public void deposit(Integer amountDeposited) {
//         amountDeposited + account.balance()
//         return "Your new balance is (String format account.balance)"
//
//         add this action to print history
    }
    public void withdraw(Integer amountWithdrawn) {
//         account.balance() - amountWithdrawn
//
//         if amountWithdrawn > account.balance()
//         return "You only have (String format account.balance) to withdraw"
//
//         add this action to the print history
//         add amountWithdrawn to cash in printout

    }




}
