package account;

public class Account {

    private String accountType;

    private Double balance;
    private String accountId;

    public Account(String accountId, Double balance, String accountType) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
    }
    public String getAccountId() { return  accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) { this.balance = balance; }


}
