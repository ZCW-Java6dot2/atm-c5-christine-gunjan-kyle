package consoleMenu;


import account.Account;
import account.AccountManager;
import user.UserManager;
import consoleMenu.Console;
import user.User;
public class Menu {
    AccountManager accountManager;
    UserManager manager;
    private int choice;
    private String accountId = "";
    User currentUser;
    Account currentAccount;

    public Menu(UserManager manager, AccountManager accountManager){
        this.manager = manager;
        this.accountManager = accountManager;
    }

    public void accountMenu(Account account)
    {
        currentAccount = account;
        int transactionChoice=0;
        this.accountId = accountId;
        System.out.println("****************************");
        System.out.println("1.  Add account");
        System.out.println("2.  Get account balance");
        System.out.println("3.  Make withdrawal");
        System.out.println("4.  Make deposit");
        System.out.println("5.  Transfer");
        System.out.println("6.  Go to Previous Menu");
        System.out.println("****************************");
        transactionChoice = Console.getIntInput("Please select from these options: :  ");
        matchTransactionMethod(transactionChoice);
    }

    private void matchTransactionMethod(int choice){
        switch (choice){
            case 1:
                //add account method
                break;
            case 2:
                System.out.println(currentAccount.getBalance());
                break;
            case 3:
                //withdrawal
                break;
            case 4:
                //deposit
                break;
            case 5:
                //internal transfer
                break;
            case 6:
                //previous menu
                break;
            default:
                System.out.println("Invalid entry");
                break;
        }
    }

    public String userOptionsMenu(User currentUser) {
        this.currentUser = currentUser;
        int newPin = -1;
        Integer choiceOfAccount = 0;
        System.out.println("**** ACCOUNT OPTIONS *****  ");
        System.out.println("1.  Checking");
        System.out.println("2.  Savings");
        System.out.println("3.  Investment");
        System.out.println("4.  Add new account");
        System.out.println("5.  Close account");
        System.out.println("6.  Change pin");
        System.out.println("7.  Go to previous menu");
        choiceOfAccount = Console.getIntInput("Please select from these options: :  ");

        switch (choiceOfAccount) {
            case 1:
                return "CHECKING";
            case 2:
                return "SAVINGS";
            case 3:
                return "INVESTMENT";
            case 4:
                return "";
            case 5:
                return "";
            case 6:
                manager.changePin(currentUser.getUserName(), newPin);
                return"";
            default:
                return("Invalid entry");
        }
    }
}
