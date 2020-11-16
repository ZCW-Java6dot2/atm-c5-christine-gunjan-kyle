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
        this.loadUserPassCodeData();
        users = new ArrayList<User>();
        Account account1 = new Account("001", 18000d, "SAVINGS");
      //  Account account2 = new Account("002", 55000d, "SAVINGS");
      //  Account account3 = new Account("003", 650000d, "INVESTMENT");
        ArrayList<Account> userAccounts = new ArrayList<Account>();
        userAccounts.add(account1);
       // userAccounts.add(account2);
       // userAccounts.add(account3);
        users.add(new User("DAKSH", userAccounts));
        this.loadUserAccountsData();


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
        //if match, returns true
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
         this.createUser(name);
         this.userPassword.replace(name, pin);
       //Remember to remove this pin display on UI
        System.out.println("Pin successfully changed." + name + "\n" + pin);
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
                String[] userPassCode = line.split(csvSplitBy);

            // (4)
                String userName = userPassCode[0];
                Integer passCode = Integer.parseInt(userPassCode[1]);
            // (5)
                this.userPassword.put(userName,passCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printOnFile() throws IOException {
        String csvFile = "/Users/gunjan/Dev/atm-c5-christine-gunjan-kyle/userPass.csv";
        FileWriter writer = new FileWriter(csvFile); //(1)
       // CSVUtils.writeLine(writer,new ArrayList<String>(Arrays.asList(String.valueOf(nextId))));  // (2)
        for (HashMap.Entry<String, Integer> keyValue : this.userPassword.entrySet())
        {
            ArrayList<String> list = new ArrayList<>();
            list.add(String.valueOf(keyValue.getKey()).toUpperCase());
            list.add(String.valueOf(keyValue.getValue()));
            CSVUtils.writeLine(writer, list);  // (4)
        }
       // (5)
        writer.flush();
        writer.close();
    }
public void loadUserAccountsData(){
        // (1)
        String csvFile = "/Users/gunjan/Dev/atm-c5-christine-gunjan-kyle/userAccounts.csv";
        String line = "";
        String lineNext="";
        String csvSplitBy = ",";

        ArrayList<User> users;
        // (2)
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // nextId = (int)Integer.parseInt(br.readLine());

            while ((line = br.readLine()) != null) {
               // split line with comma
                String[] userAccounts = line.split(csvSplitBy);
                // (4)
                String userName = userAccounts[0];
                Double accountBalance = Double.parseDouble(userAccounts[2]);
                String accountID =userAccounts[1];
                String accountType =userAccounts[3];
                Boolean userExist =  false;

                for(int i=0 ;i < this.users.size() && this.users !=null ;i++) {
                    if (this.users.get(i).getUserName().equalsIgnoreCase(userName)) {

                        Account account = new Account(accountID, accountBalance, accountType);
                        ArrayList<Account> accountReceived = this.users.get(i).getAccounts();
                        if(account.getAccountId() != accountID) {
                            accountReceived.add(account);
                        }
                          this.users.get(i).setAccounts(accountReceived);
                        userExist = true;
                    }
                }
                  if(!userExist)
                  {
                        User user = new User();
                        user.setUserName(userName);
                        Account account = new Account(accountID,accountBalance,accountType);
                        ArrayList<Account> accountNotExist = new ArrayList<Account>();
                        accountNotExist.add(account);
                        user.setAccounts(accountNotExist);
                        this.users.add(user);
                    }

                }



            }
          catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printOnFileUserAccounts() throws IOException {
        String csvFile = "/Users/gunjan/Dev/atm-c5-christine-gunjan-kyle/userAccounts.csv";
        FileWriter writer = new FileWriter(csvFile); //(1)


        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            ArrayList<Account> accountsFound=users.get(i).getAccounts();

            for (int j = 0; j < accountsFound.size(); j++) {
                ArrayList<String> listuserAccount = new ArrayList<>();
                listuserAccount.add(user.getUserName().toUpperCase());
                listuserAccount.add(accountsFound.get(j).getAccountId());
                listuserAccount.add(String.valueOf(accountsFound.get(j).getBalance())+"d");
                listuserAccount.add(accountsFound.get(j).getAccountType());
                CSVUtils.writeLine(writer, listuserAccount);

            }


        }

        writer.flush();
        writer.close();
    }


}