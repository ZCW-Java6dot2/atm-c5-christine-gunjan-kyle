package consoleMenu;

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
        System.out.println("Press 1 to log in,");
        choice = getIntInput("Press 2 to open a new bank account:  ");
        //IT IS NOT CURRENTLY RECOGNIZING 1 OR 2
        while ((choice != 1) || (choice != 2)) {
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
        //call function from UserMenu and pass these as parameters.
    }

    public void newBankUser(){
        username = getStringInput("Please enter your preferred username:  ");
        while (username.equals("already existing username")){
            //^^must compare username to list of usernames in UserManager
            System.out.println("Apologies - this username is taken. Pick another.  ");
            username = getStringInput("Please enter your preferred username:  ");
        }
        getStringInput("Your temporary pin is 1234. Press any key to change it.");
        //call changePin() method in UserManager
        //then call Main.userMenu() for account editing options
    }
}
