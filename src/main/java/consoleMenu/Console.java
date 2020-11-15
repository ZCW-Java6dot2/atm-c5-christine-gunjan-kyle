package consoleMenu;


import com.sun.deploy.security.SelectableSecurityManager;
import user.User;
import user.UserManager;
import account.AccountManager;
import account.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    public static Scanner scan;
    private int choice;
    private String username;
    private int pin;
    AccountManager accountManager;
    UserManager userManager;
    Menu menu;
    Boolean userAvailable;
    String accountTypeSelected = "";
    String accountIdToPass = "";
    User currentUser;

    public Console(){
        scan = new Scanner(System.in);
        userManager = new UserManager();
        userAvailable = false;
        menu = new Menu(userManager, accountManager);
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        scan.nextLine();
        return scan.next();
    }

    public static int getIntInput(String prompt) {
        //System.out.print(prompt);
        //return scan.nextInt();
        Console console = new Console();
        Integer userInput = 0;
        System.out.println(prompt);
        if (scan.hasNextInt()) {
            userInput = scan.nextInt();
        } else
        {
            userInput = console.getIntInput ("Not a Number value, Please re-enter:");
        }
        return userInput;

    }

    public void welcome(){
        System.out.println("****************************************");
        System.out.println("*****  Welcome to Gukych Bank ATM  *****");
        System.out.println("****************************************");

        choice = getIntInput("Press 1 to log in.\nPress 2 to open a new bank account:  ");
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
        username = getStringInput("Please enter your username:  ");
        pin = getIntInput("Please enter your pin:  ");

        userAvailable= userManager.login(username,pin);

        if(userAvailable) {
            currentUser = userManager.getUser(username);
            //String nameOfUser = user.getUserNameByAccount(accountIdSelected);
            //ArrayList<Account> accountsOfUser = accountManager.getAccounts(nameOfUser);
            ArrayList<Account> accountsOfUser= currentUser.getAccounts();
           if(accountsOfUser.size() != 3) {
               //Will go through loop to see how it filters the account type
           } else {
               Account accountSelected = new Account();
               accountTypeSelected = menu.userOptionsMenu(currentUser);
             for(int i=0;i<accountsOfUser.size();i++) {
                   if(accountsOfUser.get(i).getAccountType().equals(accountTypeSelected))
                     accountSelected=accountsOfUser.get(i);
                }
               menu.accountMenu(accountSelected);
           }
        } else {
            System.out.println("Incorrect selection. Try again.");
            welcome();
        }
    }

    public void newBankUser(){
        username = getStringInput("Please enter your preferred username:  ");
        Boolean userTaken = userManager.doesUserExist(username);
        while (userTaken){
            System.out.println("Apologies - this username is taken. Pick another.  ");
            username = getStringInput("Please enter your preferred username:  ");
            userTaken = userManager.doesUserExist(username);
        }
        getIntInput("Your temporary pin is 1234. To change, enter your new pin.");
        userManager.changePin(username, 1234);
        menu.userOptionsMenu(currentUser);
    }

}