
import account.Account;
import org.junit.Test;
import user.User;
import user.UserManager;
import java.util.ArrayList;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

// Test the expected Account class from ATM.
public class AccountTest {

    @Test
    public void getAccountIdTest() {
        Account a = new Account("001" ,5000d,"SAVINGS");

        assertEquals("001", a.getAccountId());
    }

    @Test
    public void getBalanceTest() {
        Account a = new Account("001" ,5000d,"SAVINGS");

        assertEquals(5000, a.getBalance(), 0.0001);
    }

    @Test
    public void getAccountTypeTest() {
        Account a = new Account("001" ,5000d,"SAVINGS");

        assertEquals("SAVINGS", a.getAccountType());
    }

    @Test
    public void depositTest() {
        Account a = new Account("001" ,5000d,"SAVINGS");
        a.deposit(250d);
        assertEquals((5000 + 250), a.getBalance(), 0.0001);
    }

    @Test
    public void withdrawalTest() {
        Account a = new Account("001" ,5000d,"SAVINGS");
        a.withdraw(250d);
        assertEquals((5000 - 250), a.getBalance(), 0.0001);
    }

    @Test
    public void doesUserExistTest() {
        UserManager manager = new UserManager();
        boolean expected = true;
        boolean actual = manager.doesUserExist("GUNJAN");
        assertEquals(expected, actual);
    }

    @Test
    public void getUserTest() {
        UserManager manager = new UserManager();
        User actual = manager.getUser("KYLE");
        assertNotNull(actual);
    }

   @Test
   public void openAccount() throws IOException {
       User user = new User();
       user.setUserName("Test123");
       ArrayList <Account> accounts = new ArrayList<Account>();
       Account a1 = new Account("008",15000.0d,"CHECKING");
       accounts.add(a1);
       user.setAccounts(accounts);
       Account a2 = new Account("009",450000.0d,"INVESTMENT");
       accounts.add(a2);
       user.setAccounts(accounts);
       assertEquals(2,user.getAccounts().size());
   }

    @Test
    public void loginTest() {
         UserManager manager = new UserManager();
         assertEquals(true , manager.login("gunjan",1255));

    }
    @Test
    public void changePinTest() {
        UserManager manager = new UserManager();
        assertEquals(true , manager.login("gunjan",1255));
        manager.changePin("gunjan",4544);
        assertEquals(true , manager.login("gunjan",4544));

    }
/*
 @Test
    public void transferTest() {
        Console console = mock(Console.class);
        when(console.getDoubleInput("prompt")).thenReturn(150d);

 //       assertEquals(test.getUniqueId(), 43);
//        Account s = new Account("001" ,5000d,"SAVINGS");
//        s.transfer(5050d);
//        assertEquals(s.getBalance(), s.getBalance(), 0.0001);
    }

    @Test
    public void printTransaction() {



//        Account a = new Account(0.0);
//        a.deposit(100.0);
//        assertEquals(100.0, a.balance(), 0.0001);
    }

       //@Test
  //  public void closeAccount() {
//        Account a = new Account(200.0);
//        Double actual = a.withdraw(100.0);
//        assertEquals(100.0, actual, 0.0001);
  //  }


*/

}
