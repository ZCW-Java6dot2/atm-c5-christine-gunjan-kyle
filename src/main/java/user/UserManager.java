package user;

import consoleMenu.Console;
import java.util.HashMap;
import java.util.*;
public class UserManager {
    //where we will keep our 3 User objects that auto generate at begin?
    HashMap<String,Integer> userPassword;//= new HashMap<User,Integer>();
    public UserManager()
    {
        this.userPassword=new HashMap<String, Integer>();
        userPassword.put("GUNJAN",1255);
        userPassword.put("CHRISTINE",7784);
        userPassword.put("KYLE",9945);
    }
    public UserManager(HashMap<String, Integer> userPassword) {

        this.userPassword = userPassword;

    }
    //storage for all usernames and pin number
    int savedPin = 1234;//autoset new user pin equal to 1234?

    public Boolean login(String userName , Integer passCode) {
        //matches username/pin at login(called from Console)
        //if match, redirects to Menu.userMenu
        for (HashMap.Entry<String, Integer> keyValue : this.userPassword.entrySet()) {
            String receivedName=keyValue.getKey().toUpperCase();
            Integer receivedPassCode=keyValue.getValue();
            if (receivedName.equals(userName.toUpperCase()) && receivedPassCode.equals(passCode)) {
                return true;
            }
         }
        return false;
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