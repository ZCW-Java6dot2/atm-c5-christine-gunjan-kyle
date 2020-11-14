package consoleMenu;


public class Menu {
    private int choice;

    public int userManagerMenu(){
        System.out.println("Please select from these options:  ");
        //calls methods in UserManager
        //1. change pin
        return choice;
    }

    public int userMenu(){
        System.out.println("Please select from these options:  ");
        //calls methods in User, Account, Checking, Savings, Investment
        return choice;
    }
}
