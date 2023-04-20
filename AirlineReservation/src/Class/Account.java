package Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that gives user account functionality such as registration, login confirmation, account settings, and to save
 * flight reservations.
 * <p>
 *
 * @since 04/18/2023
 * @author Carlos Figueroa
 * <p>
 *
 * <b>Explanation of important functions:</b>  Constructors are used for account registration, getters are used to get information
 * from the user throughout the app, setters are used to change manage account info, getLoginAccount() and register(Account account)
 * saves all Account objects into a hashmap, isRegistered(Account account) and accountExists(String username) are used for
 * account validation, and addReservation(Reservation reservation) and getReservationList() are used to add and get flight
 * resevation information associated with the account.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>Account()</i></li>
 * <li><i>Account(String username, String password, String firstName, String lastName, String address, String emailAddress, String phoneNumber)</i></li>
 * <li><i>getUsername()</i></li>
 * <li><i>getPassword()</i></li>
 * <li><i>getFirstName()</i></li>
 * <li><i>getLastName()</i></li>
 * <li><i>getAddress()</i></li>
 * <li><i>getEmailAddress()</i></li>
 * <li><i>getPhoneNumber()</i></li>
 * <li><i>setUsername(String username)</i></li>
 * <li><i>setPassword(String password)</i></li>
 * <li><i>setFirstName(String firstName)</i></li>
 * <li><i>setLastName(String lastName)</i></li>
 * <li><i>setAddress(String address)</i></li>
 * <li><i>setEmailAddress(String emailAddress)</i></li>
 * <li><i>setPhoneNumber(String phoneNumber)</i></li>
 * <li><i>getLoginAccount()</i></li>
 * <li><i>register(Account account)</i></li>
 * <li><i>isRegistered(Account account)</i></li>
 * <li><i>accountExists(String username)</i></li>
 * <li><i>addReservation(Reservation reservation)</i></li>
 * <li><i>getReservationList()</i></li>
 * </ul>
 * <p>
 *
 * <b>Data-structures used:</b>  Hashmap containing app's entire userbase's account information.  Map&lt;username, Account>
 *
 */
public class Account {
    private ArrayList<Reservation> reservationList;
    private Reservation reservation;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String emailAddress;
    private String phoneNumber;

    private static final Map<String, Account> loginAccount = new HashMap<>();


    /**
     *
     * Empty Constructor method used to register accounts
     *
     */
    public Account() {
        reservationList = new ArrayList<Reservation>();
        this.username = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.address = null;
        this.emailAddress = null;
        this.phoneNumber = null;
    }

    /**
     *
     * Constructor Method used to register accounts
     * @param username username requirement: "at least 8 characters and unique"
     * @param password password requirement: "At least 8 characters, one uppercase, one lowercase, one number, one special character"
     * @param firstName user's first name
     * @param lastName user's last name
     * @param address user's home address
     * @param emailAddress user's preferred email address
     * @param phoneNumber user's phone number
     *
     */
    public Account(String username, String password, String firstName, String lastName, String address, String emailAddress, String phoneNumber) {
        reservationList = new ArrayList<Reservation>();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }


    /**
     * Method for obtaining the account's username
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Method for obtaining the user's password
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Method for obtaining the user's first name
     * @return firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Method for obtaining the user's last name
     * @return lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Method for obtaining the user's home address
     * @return address
     */
    public String getAddress() { return this.address; }

    /**
     * Method for obtaining the user's email address
     * @return emailAddress
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Method for obtaining the user's phone number
     * @return phoneNumber
     */
    public String getPhoneNumber() { return this.phoneNumber; }

    /**
     * Method for setting the account's username
     * @param username username requirement: "at least 8 characters and unique"
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method for setting the user's password
     * @param password password requirement: "At least 8 characters, one uppercase, one lowercase, one number, one special character"
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method for setting the user's first name
     * @param firstName user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method for setting the user's last name
     * @param lastName user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method for setting the user's address
     * @param address user's home address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method for setting the user's email address
     * @param emailAddress user's email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Method for setting the user's phone number
     * @param phoneNumber user's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * Method that returns hashmap containing all app accounts
     * @return loginAccount
     */
    public Map<String, Account> getLoginAccount() {
        return loginAccount;
    }

    /**
     * Method that takes new registered user's account information and stores it into hashmap with unique key.
     * @param account contains new user's account information
     */
    public void registerOrUpdate(Account account) {
        loginAccount.put(account.getUsername(), account);
    }


    /**
     * Method that confirms if account is in hashmap
     * @return boolean
     */
    public static boolean isRegistered(Account account){
        if(loginAccount.containsKey(account.getUsername()) && loginAccount.containsValue(account)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that confirms if account already exists
     * @return boolean
     */
    public static boolean accountExists(String username) {
        if(loginAccount.containsKey(username)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that adds one or more flight reservations to user's account.
     * @param reservation contains all flight information for flight user has booked.
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Method that adds one or more flight reservations to user's account.
     * @param reservation contains all flight information for flight user has booked.
     */
    public void addReservationToAccount(Reservation reservation) {
        reservationList.add(reservation);
    }

    /**
     * Method that gets a list of user's booked flight reservations.
     * @return reservationList list of user's booked flight reservations.
     */
    public ArrayList<Reservation> getReservationList() {
        return this.reservationList;
    }
}
