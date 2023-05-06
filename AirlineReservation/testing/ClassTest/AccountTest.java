package ClassTest;

import Class.Account;
import Class.Passenger;
import Class.Reservation;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        account1.registerOrUpdate(account1);
        account1.registerOrUpdate(account2);
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
        account1.registerOrUpdate(account2);
    }


    @Test
    public void getLoginAccount() {
        account1.setUsername("Bob123");
        account1.registerOrUpdate(account1);
        Assert.assertEquals(account1, account1.getLoginAccount().get("Bob123"));

        Assert.assertEquals(account2, account1.getLoginAccount().get("Nathan674"));
    }

    @Test
    public void getUsername() {
        Assert.assertEquals("Bob123", account1.getUsername());
        Assert.assertEquals("Nathan674", account2.getUsername());
    }

    @Test
    public void getPassword() {
        Assert.assertEquals("UniquePassword1!", account1.getPassword());
        Assert.assertEquals("UniquePassword2!", account2.getPassword());
    }

    @Test
    public void getFirstName() {
        account1.setFirstName("Bob");
        Assert.assertEquals("Bob", account1.getFirstName());
        Assert.assertEquals("Nathan", account2.getFirstName());
    }

    @Test
    public void getLastName() {
        Assert.assertEquals("Jones", account1.getLastName());
        Assert.assertEquals("Sanchez", account2.getLastName());
    }

    @Test
    public void getAddress() {
        account1.setAddress("1234 Palm ave. Burbank CA");
        Assert.assertEquals("1234 Palm ave. Burbank CA", account1.getAddress());
        Assert.assertEquals("999 Colorado st. Denver CO", account2.getAddress());
    }

    @Test
    public void getEmailAddress() {
        account1.setEmailAddress("BobJones222@AOL.com");
        Assert.assertEquals("BobJones222@AOL.com", account1.getEmailAddress());
        Assert.assertEquals("NathCO@Gmail.com", account2.getEmailAddress());
    }

    @Test
    public void getPhoneNumber() {
        account1.setPhoneNumber("(818)445-9854");
        Assert.assertEquals("(818)445-9854", account1.getPhoneNumber());
        Assert.assertEquals("(555)478-4444", account2.getPhoneNumber());
    }

    @Test
    public void setReservation() {
        ArrayList<Passenger> departureParty = new ArrayList<Passenger>();
        Passenger passenger1 = new Passenger("Nathan", "Sanchez", "United States", "male", "November 24, 2023");
        departureParty.add(passenger1);

        Reservation reservation = new Reservation(235753, 219.0, 111.1, 219.0 + 111.1,
                "05/07/2023", "SW1454", 1, departureParty);

        account2.setReservation(reservation);
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