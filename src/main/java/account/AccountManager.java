package account;
import user.User;
import consoleMenu.Menu;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {

   User currentUser;

   public AccountManager(User currentUser){
      this.currentUser = currentUser;
   }

   public ArrayList<Account> getAccounts(String username) {
      ArrayList<Account> accountsReceived;

      Account account1 = new Account("CH-gunjan001", 5000d, "CHECKING");
      Account account2 = new Account("SA-gunjan002", 1500d, "SAVINGS");
      Account account3 = new Account("IN-gunjan003", 150000d, "INVESTMENT");
      ArrayList<Account> accountDummyPassed = new ArrayList<Account>();
      accountDummyPassed.add(account1);
      accountDummyPassed.add(account2);
      accountDummyPassed.add(account3);
      User user = new User();
      user.setUserName(username);
      user.setAccounts(accountDummyPassed);

      accountsReceived = user.getAccountsByUserName(username);
      return accountsReceived;
   }
   }

