package consoleMenu;


import com.sun.deploy.security.SelectableSecurityManager;
import user.User;
import user.UserManager;
import account.AccountManager;
import account.Account;

import java.io.IOException;
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
   // String accountIdToPass = "";
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

    public static Double getDoubleInput(String prompt) {
        Scanner scanner = new Scanner(System.in);

        Double userInputDouble = 0D;
        System.out.println(prompt);

        if (scanner.hasNextDouble()) {
            userInputDouble = scanner.nextDouble();
        } else
        {
            userInputDouble = Console.getDoubleInput ("Not a Number value, Please re-enter:");

        }

        return userInputDouble;
    }


    public void welcome() throws IOException {
        System.out.println("\n  vVVVv (___) wWWWw         (___)  vVVVv\n" +
                "  (___)  ~$~  (___)  vVVVv   ~$~   (___)\n" +
                "   ~$~   \\|    ~$~   (___)    |/    ~$~\n" +
                "   \\|   \\ |/   \\| /  \\~$~/   \\|    \\ |/\n" +
                "  \\\\|// \\\\|// \\\\|/// \\\\|//  \\\\|// \\\\\\|///\n" +
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("****************************************");
        System.out.println("***** \u001B[36m Welcome to Gukych Bank ATM\u001B[0m  ***** \u001B[36m ");
        System.out.println("\u001B[0m****************************************");


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

    public void logInInput() throws IOException {
        username = getStringInput("Please enter your username:  ");
        pin = getIntInput("Please enter your pin:  ");

        userAvailable= userManager.login(username,pin);
        if(userAvailable) {
            currentUser = userManager.getUser(username);
            menu.userOptionsMenu(currentUser);
        }
        else {
            System.out.println("Incorrect selection. Try again.");
            welcome();
        }

    }

  /*  public void decideOnMenu() throws IOException {

        if(userAvailable) {
            currentUser = userManager.getUser(username);
            ArrayList<Account> accountsOfUser= currentUser.getAccounts();
            if(accountsOfUser.size()==0) {

                //Add an account
            } else {
                //get all accounts belonging to this user
                Account accountSelected = new Account();
                //Display menu for user to select the account types.
             //   accountTypeSelected = menu.userOptionsMenu(currentUser);
                for(int i=0;i<accountsOfUser.size();i++) {
                    if(accountsOfUser.get(i).getAccountType().equals(accountTypeSelected))
                        accountSelected=accountsOfUser.get(i);
                }
                //For the selected account , display menu to do transaction
                menu.accountMenu(accountSelected);
            }
        } else {
            System.out.println("Incorrect selection. Try again.");
            welcome();
        }
    }*/

    public void newBankUser() throws IOException {
        username = getStringInput("Please enter your preferred username:  ");
        Boolean userTaken = userManager.doesUserExist(username);
        while (userTaken){
            System.out.println("Apologies - this username is taken. Pick another.  ");
            username = getStringInput("Please enter your preferred username:  ");
            userTaken = userManager.doesUserExist(username);
        }
       int newPin= getIntInput("Your temporary pin is 1234. To change, enter your new pin:");

        userManager.changePin(username, newPin);
        currentUser = userManager.getUser(username);
        userManager.printOnFile();
        menu.userOptionsMenu(currentUser);

    }

}