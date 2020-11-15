package user;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import consoleMenu.Console;
import account.Account;
import java.util.HashMap;
import java.util.*;
public class UserManager {
    //storage for all usernames and pin number
    private static final int defaultPassword = 1234;
    HashMap<String,Integer> userPassword; //= new HashMap<User,Integer>();
    ArrayList<User> users;

    public UserManager() {
        this.userPassword=new HashMap<String, Integer>();
        userPassword.put("GUNJAN",1255);
        userPassword.put("CHRISTINE",7784);
        userPassword.put("KYLE",9945);
        users = new ArrayList<User>();
        Account account1 = new Account("CH-gunjan001", 5000d, "CHECKING");
        Account account2 = new Account("SA-gunjan002", 1500d, "SAVINGS");
        Account account3 = new Account("IN-gunjan003", 150000d, "INVESTMENT");
        ArrayList<Account> gunjansAccounts = new ArrayList<Account>();
        gunjansAccounts.add(account1);
        gunjansAccounts.add(account2);
        gunjansAccounts.add(account3);
        users.add(new User("GUNJAN", gunjansAccounts));
    }

    public UserManager(HashMap<String, Integer> userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean doesUserExist(String name){
        return userPassword.containsKey(name);
    }

    public User getUser(String name) {
        for (User user : users) {
            if (name.equalsIgnoreCase(user.getUserName())) {
                return user;
            }
        }
        return null;
    }

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

    public void changePin(String name, int pin){
        this.userPassword.replace(name, pin);
        System.out.println("Pin successfully changed.");
    }

    public void createUser(String name){
        this.userPassword.put(name, defaultPassword);

        this.users.add(new User(name, new ArrayList<Account>()));
    }
}