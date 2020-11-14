package account;
import user.User;
import consoleMenu.Menu;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {


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


   public void menuForTransactions(String accountIdSelected) {
      Menu menu = new Menu();
      User user = new User();
      AccountManager accountManager = new AccountManager();
      Integer transactionChoice = menu.transactionMenu();
      String nameOfUser = user.getUserNameByAccount(accountIdSelected);
      ArrayList<Account> accountsOfUser = accountManager.getAccounts(nameOfUser);
      for (int i = 0; i < accountsOfUser.size(); i++) {
         String accountId = accountsOfUser.get(i).getAccountId();
         Double accountBalance = accountsOfUser.get(i).getBalance();
         String accountType = accountsOfUser.get(i).getAccountType();

         if (accountIdSelected.substring(0,2).equals(accountType.substring(0,2)))
         {
            Checking checking = new Checking(accountId, accountBalance, accountType);
            System.out.println(checking.getBalance());
            break;

           // transactionChoice= menu.transactionMenu();
         }
         else if (accountIdSelected.substring(0,2).equals(accountType.substring(0,2))) {
            Savings savings = new Savings(accountId, accountBalance, accountType);
            System.out.println(savings.getBalance());
            break;

            //transactionChoice= menu.transactionMenu();
         }
         else  if (accountIdSelected.substring(0,2).equals(accountType.substring(0,2))) {
               Investment investment = new Investment(accountId, accountBalance, accountType);
               System.out.println(investment.getBalance());
               break;
            }


         }
      }


   }

