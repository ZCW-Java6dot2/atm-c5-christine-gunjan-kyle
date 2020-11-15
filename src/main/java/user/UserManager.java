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
      users = new ArrayList<User>();
        Account account1 = new Account("001", 18000d, "CHECKING");
        Account account2 = new Account("002", 1500d, "SAVINGS");
       ArrayList<Account> gunjansAccounts = new ArrayList<Account>();
        gunjansAccounts.add(account1);
        gunjansAccounts.add(account2);
      //  gunjansAccounts.add(account3);
        users.add(new User("GUNJAN", gunjansAccounts));
        this.loadUserAccountsData();

       // System.out.println(userPassword);

        /*
        Account account4 = new Account("004", 12000d, "CHECKING");
        Account account5 = new Account("005", 45000d, "SAVINGS");
        Account account6 = new Account("006", 80000d, "INVESTMENT");
        ArrayList<Account> kylesAccount = new ArrayList<Account>();
        kylesAccount.add(account4);
        kylesAccount.add(account5);
        kylesAccount.add(account6);
        Account account7 = new Account("007", 2000d, "CHECKING");
      //  Account account8 = new Account("008", 3300d, "SAVINGS");
       // Account account9 = new Account("009", 50000d, "INVESTMENT");
        ArrayList<Account> christinesAccount = new ArrayList<Account>();
        christinesAccount.add(account7);
      //  christinesAccount.add(account8);
       // christinesAccount.add(account9);


        users.add(new User("KYLE",kylesAccount));
        users.add(new User("CHRISTINE",christinesAccount));*/

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
            list.add(String.valueOf(keyValue.getKey()));
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
                        accountReceived.add(account);
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
                listuserAccount.add(user.getUserName());
                listuserAccount.add(accountsFound.get(j).getAccountId());
                listuserAccount.add(String.valueOf(accountsFound.get(j).getBalance())+"d");
                listuserAccount.add(accountsFound.get(j).getAccountType());
                CSVUtils.writeLine(writer, listuserAccount);

            }
          //  CSVUtils.writeLine(writer, listuserAccount);  // (4)

        }

        writer.flush();
        writer.close();
    }


}