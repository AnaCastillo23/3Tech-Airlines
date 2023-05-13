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

/**
 *
 * This is a testing file for the Account.java class
 *
 * @since 05/05/2023
 * @author Carlos Figueroa
 * <p>
 * <b>Explanation of important functions:</b> File tests getters and setters from our Account.java class
 * as well as newly implemented getters and setters.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>setUp():</i> used to store newly create account objects.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
@RunWith(JUnit4.class)
public class AccountTest {

    private static Account account1;
    private static Account account2;
    private static Reservation reservation;

    /**
     *
     * Method for creating new account objects implementing the Account.java class
     *
     */
    @BeforeClass
    public static void setUp() throws Exception {
        account1 = new Account();
        account2 = new Account("Nathan674", "UniquePassword2!","Nathan","Sanchez",
                "999 Colorado st. Denver CO", "NathCO@Gmail.com", "(555)478-4444");


        account1.registerOrUpdate(account1);
        account1.registerOrUpdate(account2);


        ArrayList<Passenger> departureParty = new ArrayList<Passenger>();
        Passenger passenger1 = new Passenger("Nathan", "Sanchez", "United States", "male", "November 24, 2023");
        departureParty.add(passenger1);

        reservation = new Reservation(235753, 219.0, 111.1, 219.0 + 111.1,
                "05/07/2023", "SW1454", 1, departureParty);
    }

    /**
     *
     * For setting the username.
     *
     */
    @Test
    public void setUsername() {
        account1.setUsername("Bob123");
    }

    /**
     *
     * For setting the password.
     *
     */
    @Test
    public void setPassword() {
        account1.setPassword("UniquePassword1!");
    }

    /**
     *
     * For setting the user's first name.
     *
     */
    @Test
    public void setFirstName() {
        account1.setFirstName("Bob");
    }

    /**
     *
     * For setting the user's last name.
     *
     */
    @Test
    public void setLastName() {
        account1.setLastName("Jones");
    }

    /**
     *
     * For setting the user's address.
     *
     */
    @Test
    public void setAddress() {
        account1.setAddress("1234 Palm ave. Burbank CA");
    }

    /**
     *
     * For setting the user's email address.
     *
     */
    @Test
    public void setEmailAddress() {
        account1.setEmailAddress("BobJones222@AOL.com");
    }

    /**
     *
     * For setting the user's phone number.
     *
     */
    @Test
    public void setPhoneNumber() {
        account1.setPhoneNumber("(818)445-9854");
    }

    /**
     *
     * Method for either registering a new account or updating an existing account into a hashmap.
     *
     */
    @Test
    public void registerOrUpdate() {
        account1.registerOrUpdate(account1);
        account1.registerOrUpdate(account2);
    }

    /**
     *
     * For obtaining the user's account.
     *
     */
    @Test
    public void getLoginAccount() {
        account1.setUsername("Bob123");
        account1.registerOrUpdate(account1);
        Assert.assertEquals(account1, account1.getLoginAccount().get("Bob123"));

        Assert.assertEquals(account2, account1.getLoginAccount().get("Nathan674"));
    }

    /**
     *
     * For obtaining the user's username.
     *
     */
    @Test
    public void getUsername() {
        Assert.assertEquals("Bob123", account1.getUsername());
        Assert.assertEquals("Nathan674", account2.getUsername());
    }

    /**
     *
     * For obtaining the user's password.
     *
     */
    @Test
    public void getPassword() {
        Assert.assertEquals("UniquePassword1!", account1.getPassword());
        Assert.assertEquals("UniquePassword2!", account2.getPassword());
    }

    /**
     *
     * For obtaining the user's first name.
     *
     */
    @Test
    public void getFirstName() {
        account1.setFirstName("Bob");
        Assert.assertEquals("Bob", account1.getFirstName());
        Assert.assertEquals("Nathan", account2.getFirstName());
    }

    /**
     *
     * For obtaining the user's last name.
     *
     */
    @Test
    public void getLastName() {
        Assert.assertEquals("Jones", account1.getLastName());
        Assert.assertEquals("Sanchez", account2.getLastName());
    }

    /**
     *
     * For obtaining the user's address.
     *
     */
    @Test
    public void getAddress() {
        account1.setAddress("1234 Palm ave. Burbank CA");
        Assert.assertEquals("1234 Palm ave. Burbank CA", account1.getAddress());
        Assert.assertEquals("999 Colorado st. Denver CO", account2.getAddress());
    }

    /**
     *
     * For obtaining the user's email address.
     *
     */
    @Test
    public void getEmailAddress() {
        account1.setEmailAddress("BobJones222@AOL.com");
        Assert.assertEquals("BobJones222@AOL.com", account1.getEmailAddress());
        Assert.assertEquals("NathCO@Gmail.com", account2.getEmailAddress());
    }

    /**
     *
     * For obtaining the user's phone number.
     *
     */
    @Test
    public void getPhoneNumber() {
        account1.setPhoneNumber("(818)445-9854");
        Assert.assertEquals("(818)445-9854", account1.getPhoneNumber());
        Assert.assertEquals("(555)478-4444", account2.getPhoneNumber());
    }

    /**
     *
     * For setting a reservation.
     *
     */
    @Test
    public void setReservation() {
        account2.setReservation(reservation);
    }

    /**
     *
     * For obtaining a reservation.
     *
     */
    @Test
    public void getReservation() {
        account2.setReservation(reservation);
        Assert.assertEquals(reservation, account2.getReservation());
    }

    /**
     *
     * Method for adding a reservation into a user's account.
     *
     */
    @Test
    public void addReservationToAccount() {
        account2.addReservationToAccount(reservation);
    }

    /**
     *
     * For obtaining user's reservations.
     *
     */
    @Test
    public void getReservationList() {
        account2.addReservationToAccount(reservation);
        Assert.assertEquals(reservation, account2.getReservationList().get(0));
    }
}