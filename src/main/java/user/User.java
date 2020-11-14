package user;
import account.*;
import java.util.ArrayList;



public class User {
    private String userName;
    private ArrayList<Account> accounts;

    public User()
    {
        this.userName = "" ; //Entered by user during creating new user
        this.accounts= new ArrayList<Account>();
    }

    public User(String userName, ArrayList<Account> accounts) {
        this.userName = userName;
        this.accounts = accounts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userId) {
        this.userName = userId;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }



}
