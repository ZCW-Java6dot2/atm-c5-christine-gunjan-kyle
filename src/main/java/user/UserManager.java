package user;

import consoleMenu.Console;

public class UserManager {
    //where we will keep our 3 User objects that auto generate at begin?

    //storage for all usernames and pin number
    int savedPin = 1234;//autoset new user pin equal to 1234?

    public void login(){
        //matches username/pin at login(called from Console)
        //if match, redirects to Menu.userMenu
    }

    public void changePin(){
        int pin = Console.getIntInput("Enter your current pin:  ");
        while (pin != savedPin) {
            pin = Console.getIntInput("WROOOONG! Try again:  ");
        }
        if (pin == savedPin){
            savedPin = Console.getIntInput("Good job. Enter a new pin:  ");
        }
    }
}
