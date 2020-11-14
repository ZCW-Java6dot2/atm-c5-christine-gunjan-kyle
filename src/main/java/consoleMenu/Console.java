package consoleMenu;


import com.sun.deploy.security.SelectableSecurityManager;
import user.UserManager;
import account.AccountManager;
import account.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    public static Scanner scan = new Scanner(System.in);
    private int choice;
    private String username;
    private int pin; //should pin be string or int?

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        scan.nextLine();
        return scan.next();
    }

    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        return scan.nextInt();
    }

    public void welcome(){
        System.out.println("****************************************");
        System.out.println("*****  Welcome to Gukych Bank ATM  *****");
        System.out.println("****************************************");

        choice = getIntInput("Press 1 to log in,\nPress 2 to open a new bank account:  ");
        //IT IS NOT CURRENTLY RECOGNIZING 1 OR 2
        while ((choice != 1) && (choice != 2)) {
            System.out.println("Incorrect selection. Try again.");
            choice = getIntInput("Press 1 to log in. Press 2 to open a new bank account.");
        }
        if (choice == 1){
            logInInput();
        } else {
            newBankUser();
        }
    }

    public void logInInput(){

        Boolean userAvailable=false;
        String accountTypeSelected="";
        UserManager userManager=new UserManager();
        String accountIdToPass = "";
       // Account account=new Account();
        AccountManager accountManager = new AccountManager();
        username = getStringInput("Please enter your username:  ");
        pin = getIntInput("Please enter your pin:  ");
        //call function from UserMenu and pass these as parameters.

        userAvailable= userManager.login(username,pin);
        if(userAvailable)
        {
            //display menu to get account type
           ArrayList<Account> accountsOfUser= accountManager.getAccounts(username);

           if(accountsOfUser.size() != 3)
           {
               //Will go through loop to see how it filters the account type
           }
           else
           {
               accountTypeSelected= displayAllTypes();
             for(int i=0;i<accountsOfUser.size();i++)
               {
                   if(accountsOfUser.get(i).getAccountType().equals(accountTypeSelected))
                     accountIdToPass=accountsOfUser.get(i).getAccountId();
                }
               accountManager.menuOfTransactions(accountIdToPass);

           }
        }
        else
        {
            System.out.println("Incorrect selection. Try again.");
            welcome();
        }
    }

    public void newBankUser(){
        username = getStringInput("Please enter your preferred username:  ");
        while (username.equals("already existing username")){
            //^^must compare username to list of usernames in UserManager
            System.out.println("Apologies - this username is taken. Pick another.  ");
            username = getStringInput("Please enter your preferred username:  ");
        }
        //create new user with initial 1234 pin (UserManager?)
        getStringInput("Your temporary pin is 1234. Press any key to change it.");
        //call changePin() method in UserManager
        //then call Main.userMenu() for account editing options
    }

    public String displayAllTypes()
    {
        Integer choiceOfAccount=0;
        System.out.println("**** ACCOUNT TYPES *****  ");
        System.out.println("1.  Checking");
        System.out.println("2.  Savings");
        System.out.println("3.  Investment");
        choiceOfAccount = getIntInput("Please select from these options: :  ");
        if(choiceOfAccount.equals(1))
            return "CHECKING";
            else if(choiceOfAccount.equals(2))
                return "SAVINGS";
            else
                return "INVESTMENT";

      // return choiceOfAccount;
    }
}