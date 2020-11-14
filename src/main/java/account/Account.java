package account;

import java.util.List;
import java.util.Map;

public class Account {
    private String accountNumber;
    private Integer accountType;

    public Account(String accountId, Integer accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }
    public String getAccountNumber() { return  accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public Integer getAccountType() { return accountType; }
    public void setAccountType(Integer accountType) { this.accountType = accountType; }


}
