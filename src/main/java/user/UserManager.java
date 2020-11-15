package user;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import consoleMenu.Console;
import account.Account;
import utils.CSVUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;
public class UserManager {
    //storage for all usernames and pin number
    private static final int defaultPassword = 1234;
    HashMap<String,Integer> userPassword; //= new HashMap<User,Integer>();
    ArrayList<User> users;

    public UserManager() {
        this.userPassword=new HashMap<String, Integer>();
       /* userPassword.put("GUNJAN",1255);
        userPassword.put("CHRISTINE",7784);
        userPassword.put("KYLE",9945);*/
        this.loadUserPassCodeData();
       // System.out.println(userPassword);
        users = new ArrayList<User>();
        Account account1 = new Account("001", 18000d, "CHECKING");
        Account account2 = new Account("002", 1500d, "SAVINGS");
        Account account3 = new Account("003", 55000d, "INVESTMENT");
        ArrayList<Account> gunjansAccounts = new ArrayList<Account>();
        gunjansAccounts.add(account1);
        gunjansAccounts.add(account2);
        gunjansAccounts.add(account3);
        Account account4 = new Account("004", 12000d, "CHECKING");
        Account account5 = new Account("005", 45000d, "SAVINGS");
        Account account6 = new Account("006", 80000d, "INVESTMENT");
        ArrayList<Account> kylesAccount = new ArrayList<Account>();
        kylesAccount.add(account4);
        kylesAccount.add(account5);
        kylesAccount.add(account6);
        Account account7 = new Account("007", 2000d, "CHECKING");
        Account account8 = new Account("008", 3300d, "SAVINGS");
        Account account9 = new Account("009", 50000d, "INVESTMENT");
        ArrayList<Account> christinesAccount = new ArrayList<Account>();
        christinesAccount.add(account7);
        christinesAccount.add(account8);
        christinesAccount.add(account9);

        users.add(new User("GUNJAN", gunjansAccounts));
        users.add(new User("KYLE",kylesAccount));
        users.add(new User("CHRISTINE",christinesAccount));

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
      //  System.out.println(name + pin);
         this.createUser(name);
         this.userPassword.replace(name, pin);
       // System.out.println("after"+name + pin);
        System.out.println("Pin successfully changed.");
    }

    public void createUser(String name){
        this.userPassword.put(name, defaultPassword);

        this.users.add(new User(name, new ArrayList<Account>()));
    }

    public void loadUserPassCodeData(){
        // (1)
        String csvFile = "/Users/gunjan/Dev/atm-c5-christine-gunjan-kyle/userPass.csv";
        String line = "";
        String csvSplitBy = ",";


        // (2)
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
           // nextId = (int)Integer.parseInt(br.readLine());

            while ((line = br.readLine()) != null) {
           // split line with comma
                String[] beer = line.split(csvSplitBy);

            // (4)
                String userName = beer[0];
                Integer passCode = Integer.parseInt(beer[1]);
            // (5)
                this.userPassword.put(userName,passCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}