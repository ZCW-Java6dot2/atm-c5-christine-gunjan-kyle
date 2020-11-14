package consoleMenu;


import user.UserManager;

public class Menu {
    UserManager manager = new UserManager();
    private int choice;

    public int userMenu(){
        System.out.println("Please select from these options:  ");
        System.out.println("1.  Add account");
        System.out.println("2.  Get account balance");
        System.out.println("3.  Change pin");
        System.out.println("4.  X");
        System.out.println("5.  X");
        System.out.println("6.  X");
        System.out.println("7.  X");
        System.out.println("8.  X");
        System.out.println("9.  X");
        System.out.println("10.  X");
        System.out.println("11.  X");
        System.out.println("12.  X");

        return choice;
    }

    public void matchMenuToMethod(int choice){
        //calls methods in User, UserManager, Account, Checking, Savings, Investment
        switch (choice){
            case 1:
                //method from account page
                break;
            case 2:
                //method from account page
                break;
            case 3:
                manager.changePin();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
        }
    }
}
