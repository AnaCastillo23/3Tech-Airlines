package ClassTest;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//import BeforeAll;
//import DisplayName;
//import TestInstance;

//import static Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(JUnit4.class)
public class AccountTest {

    private static Account account1;
    private static Account account2;

    @BeforeClass
    public static void setUp() throws Exception {
        account1 = new Account();
        account2 = new Account("Nathan674", "UniquePassword2!","Nathan","Sanchez",
                "999 Colorado st. Denver CO", "NathCO@Gmail.com", "(555)478-4444");
    }

    @Test
    public void setUsername() {
        account1.setUsername("Bob123");
    }

    @Test
    public void setPassword() {
        account1.setPassword("UniquePassword1!");
    }

    @Test
    public void setFirstName() {
        account1.setFirstName("Bob");
    }

    @Test
    public void setLastName() {
        account1.setLastName("Jones");
    }

    @Test
    public void setAddress() {
        account1.setAddress("1234 Palm ave. Burbank CA");
    }

    @Test
    public void setEmailAddress() {
        account1.setEmailAddress("BobJones222@AOL.com");
    }

    @Test
    public void setPhoneNumber() {
        account1.setPhoneNumber("(818)445-9854");
    }


    @Test
    public void registerOrUpdate() {
        account1.registerOrUpdate(account1);
    }

    @Test
    public void isRegistered() {
        //assertEquals(null, account1.isRegistered(account1.getLoginAccount().get("Dylan666")), "Check if account Dylan666 is registered");
        //Assert.assertTrue("Check if account Bob123 is registered", account1.isRegistered(account1.getLoginAccount().get("Bob123")));
        //Assert.assertFalse("Check if account CoolKid98 is registered",account1.isRegistered(account1.getLoginAccount().get("CoolKid98")));
        //Assert.assertFalse("Check if account Nathan674 is registered",account1.isRegistered(account1.getLoginAccount().get("Nathan674")));
    }


    @Test
    public void getLoginAccount() {
        //account1.getLoginAccount();
    }

    @Test
    public void getUsername() {
        //assertEquals(null, account2.getLoginAccount().get("Bob123").getUsername());
        //Assert.assertEquals(account2.getUsername(), account1.getLoginAccount().get(account2.getUsername()).getUsername());
        //Assert.assertEquals(account1.getUsername(), account1.getLoginAccount().get(account1.getUsername()).getUsername());
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void getFirstName() {
    }

    @Test
    public void getLastName() {
    }

    @Test
    public void getAddress() {
    }

    @Test
    public void getEmailAddress() {
    }

    @Test
    public void getPhoneNumber() {
    }

    @Test
    public void setReservation() {
    }

    @Test
    public void getReservation() {
    }

    @Test
    public void addReservationToAccount() {
    }

    @Test
    public void getReservationList() {
    }
}