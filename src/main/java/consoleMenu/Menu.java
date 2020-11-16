package consoleMenu;


import account.Account;
import account.AccountManager;
import user.UserManager;
import consoleMenu.Console;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Menu {
    AccountManager accountManager;
    UserManager manager;
    private int choice;
    private String accountId = "";
    User currentUser;
    Account currentAccount;
    Console console;

    public Menu(UserManager manager, AccountManager accountManager) {
        this.manager = manager;
        this.accountManager = accountManager;
    }

    public Menu() {

    }

    public void accountMenu(Account account) throws IOException {
        this.currentAccount = account;
        int transactionChoice = 0;
        this.accountId = accountId;
        System.out.println("****************************");
        System.out.println("1.  Add account");
        System.out.println("2.  Get account balance");
        System.out.println("3.  Make withdrawal");
        System.out.println("4.  Make deposit");
        System.out.println("5.  Transfer");
        System.out.println("6.  Print History");
        System.out.println("7.  Close Account");
        System.out.println("8.  Go to Previous Menu");
        System.out.println("****************************");
        transactionChoice = Console.getIntInput("Please select from these options: :  ");
        matchTransactionMethod(transactionChoice);
    }

    public void matchTransactionMethod(int choice) throws IOException {
//        Random random = new Random();
//        Double balcanceNewAccount = 0d;
//        String accountType = "";
//        Integer accountId = 010;
        this.currentUser = currentUser;
        switch (choice) {
            case 1:
                this.addAccount();
                accountMenu(currentAccount);
                // userOptionsMenu(currentUser);

                break;
            case 2:
                if (currentAccount.getBalance() != null){
                    System.out.println("Your current Balance is : " + currentAccount.getBalance());
                } else {
                    System.out.println("You don't have this account! Go make one, cheapo.");
                }
                accountMenu(currentAccount);
                break;
            case 3:
                Double withdrawAmount = 0.0;
                withdrawAmount = Console.getDoubleInput("Please enter the amount :  ");
                currentAccount.withdraw(withdrawAmount);
               // System.out.println(currentAccount.getBalance());
                accountMenu(currentAccount);
                break;
            case 4:
                Double depositAmount = 0.0;
                depositAmount = Console.getDoubleInput("Please enter the amount :  ");
                currentAccount.deposit(depositAmount);
                accountMenu(currentAccount);
                break;
            case 5:
                Double transferAmount = 0.0;
                transferAmount = Console.getDoubleInput("Please enter the amount : ");
                Integer choices = 0;
                System.out.println("1.  Checking");
                System.out.println("2.  Savings");
                System.out.println("3.  Investment");
                choices = Console.getIntInput("Please select from these options:  ");
                String accountTypeSelected = "";
                HashMap<Integer, String> accTypeMap = new HashMap<>();
                accTypeMap.put(1,"CHECKING");
                accTypeMap.put(2,"SAVINGS");
                accTypeMap.put(3,"INVESTMENT");
                String destinationAccType = accTypeMap.get(choices);
                Account destinationAccount = null;
                for (int i = 0; i < currentUser.getAccounts().size(); i++) {
                    if (currentUser.getAccounts().get(i).getAccountType().equals(destinationAccType)) {
                        destinationAccount = currentUser.getAccounts().get(i);
                    }
                }
                if (destinationAccount == null){
                    System.out.println("This account doesn't exist.");
                    accountMenu(currentAccount);
                }
                if (currentAccount == destinationAccount){
                    System.out.println("Error: you are transferring into the same account you're withdrawing from.");
                    accountMenu(currentAccount);
                }
                User.transfer(currentAccount, destinationAccount, transferAmount);
                break;
            case 6:
                currentAccount.printHistory();
                accountMenu(currentAccount);
                break;
            case 7:
                if (currentAccount.getBalance() > 0.0) {
                    Double moneyAmount = currentAccount.getBalance();
                    currentAccount.withdraw(moneyAmount);
                    System.out.println("Successful withdrawal of " + moneyAmount + " from " + currentAccount.getAccountId());
                    System.out.println(currentAccount.getAccountId() + " successfully emptied and closed.");
                }
                currentUser.removeAccount(currentAccount.getAccountId());
                try {
                    userOptionsMenu(currentUser);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 8:
                //manager.printOnFileUserAccounts();
                userOptionsMenu(currentUser);
                break;
            default:
                System.out.println("Invalid entry");
                accountMenu(currentAccount);
                //will that work?^
                break;
        }
    }

    public void userOptionsMenu(User currentUser) throws IOException {
        this.currentUser = currentUser;
        int newPin = -1;
        Integer choiceOfAccount = 0;
        Account accountSelected = new Account();
        String accountTypeSelected="";
        System.out.println("**** ACCOUNT OPTIONS *****  ");
        System.out.println("1.  Checking");
        System.out.println("2.  Savings");
        System.out.println("3.  Investment");
        System.out.println("4.  Add new account");
        System.out.println("5.  Change pin");
        System.out.println("6.  Log out of user");
        System.out.println("7.  Quit ATM");
        choiceOfAccount = Console.getIntInput("Please select from these options:  ");

        switch (choiceOfAccount) {

            case 1:
                 accountTypeSelected="CHECKING";
                //Account accountSelected = new Account();

                for(int i=0;i<currentUser.getAccounts().size();i++) {
                    if(currentUser.getAccounts().get(i).getAccountType().equals(accountTypeSelected))
                        accountSelected=currentUser.getAccounts().get(i);
                }
                //For the selected account , display menu to do transaction
                this.accountMenu(accountSelected);
                //return "CHECKING";

            case 2:
                 accountTypeSelected="SAVINGS";
                 accountSelected = new Account();

                for(int i=0;i<currentUser.getAccounts().size();i++) {
                    if(currentUser.getAccounts().get(i).getAccountType().equals(accountTypeSelected))
                        accountSelected=currentUser.getAccounts().get(i);
                }
                //For the selected account , display menu to do transaction
                this.accountMenu(accountSelected);

               // return "SAVINGS";
            case 3:
                accountTypeSelected="INVESTMENT";
                accountSelected = new Account();

                for(int i=0;i<currentUser.getAccounts().size();i++) {
                    if(currentUser.getAccounts().get(i).getAccountType().equals(accountTypeSelected))
                        accountSelected=currentUser.getAccounts().get(i);
                }
                //For the selected account , display menu to do transaction
                this.accountMenu(accountSelected);

                //return "INVESTMENT";
            case 4:
                this.matchTransactionMethod(1);
               // return "";
            case 5:
                newPin = Console.getIntInput("Please enter the new pin:  ");
                manager.changePin(currentUser.getUserName(), newPin);
               // return "";
            case 6:

                //print transaction history
                manager.printOnFile();
                console = new Console();
                console.welcome();
                break;
            case 7:
                System.out.print("Quiting the ATM, Program Ending");
                //print transaction history
                System.exit(0);

                break;
            default:
                System.out.println("Invalid entry");
        }

    }

    public void addAccount() throws IOException {
        Random random = new Random();
        Double balcanceNewAccount = 0d;
        String accountType = "";
        Integer accountId = 010;
        accountId += random.nextInt(998) + 1;
        accountType = Console.getStringInput("Enter account type CHECKING/SAVINGS/INVESTMENT:  ");
        balcanceNewAccount = Console.getDoubleInput("Please enter the amount :  ");
        currentUser.addAccount(String.valueOf(accountId), balcanceNewAccount, accountType);
        ArrayList<Account> modifiedAccounts = currentUser.getAccounts();
        for (int i = 0; i < modifiedAccounts.size(); i++) {
            if (modifiedAccounts.get(i).getAccountId().equalsIgnoreCase(String.valueOf(accountId))) {
                currentAccount = modifiedAccounts.get(i);
            }

        }
        manager.printOnFileUserAccounts();
    }
}

