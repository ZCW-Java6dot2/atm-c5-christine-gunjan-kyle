package consoleMenu;


import account.Account;
import account.AccountManager;
import user.UserManager;
import consoleMenu.Console;
import user.User;

import java.util.ArrayList;
import java.util.Random;

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
    public Menu() {

    }

    public void accountMenu(Account account)
    {
        this.currentAccount = account;
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

    public void matchTransactionMethod(int choice){
        Random random=new Random();
        Double balcanceNewAccount=0d;
        String accountType = "";
        Integer accountId = 010;

        switch (choice){
            case 1:
                accountId+=random.nextInt(998)+1;
                balcanceNewAccount = Console.getDoubleInput("Please enter the amount :  ");
                accountType = Console.getStringInput("Enter account type CHECKING/SAVINGS/INVESTMENT:  ");
                currentUser.addAccount(String.valueOf(accountId),balcanceNewAccount,accountType);
                ArrayList<Account> modifiedAccounts= currentUser.getAccounts();
                for (int i = 0; i < modifiedAccounts.size(); i++) {
                    if(modifiedAccounts.get(i).getAccountId().equalsIgnoreCase(String.valueOf(accountId)))
                    {
                        currentAccount=modifiedAccounts.get(i);
                    }

                }
                accountMenu(currentAccount);
               // userOptionsMenu(currentUser);

                break;
            case 2:
                System.out.println(currentAccount.getBalance());
                break;
            case 3:
                Double withdrawAmount = 0.0;
                withdrawAmount = Console.getDoubleInput("Please enter the amount :  ");
                currentAccount.withdraw(withdrawAmount);
                System.out.println(currentAccount.getBalance());
                accountMenu(currentAccount);
                break;
            case 4:
                Double depositAmount = 0.0;
                depositAmount = Console.getDoubleInput("Please enter the amount :  ");
                currentAccount.deposit(depositAmount);
                accountMenu(currentAccount);
                break;
            case 5:
                //internal transfer
                break;
            case 6:
                userOptionsMenu(currentUser);
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
              this.matchTransactionMethod(1);
                return "";
            case 5:
                return "";
            case 6:

                newPin = Console.getIntInput("Please enter the new pin : ");
                manager.changePin(currentUser.getUserName(), newPin);
                return "";

            default:
                return ("Invalid entry");
            }
        }

    }

