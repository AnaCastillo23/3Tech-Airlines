package Class;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String emailAddress;
    private String phoneNumber;

    private static final Map<String, Account> loginAccount = new HashMap<>();


    public Account() {
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.emailAddress = "";
        this.phoneNumber = "";
    }

    public Account(String username, String password, String firstName, String lastName, String address, String emailAddress, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() { return this.address; }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getPhoneNumber() { return this.phoneNumber; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public static Map<String, Account> getLoginAccount() {
        return loginAccount;
    }

    public static void register(Account account) {
        loginAccount.put(account.getUsername(), account);
    }

    /*
    public boolean login(Account account) {
        if(isRegistered(account)) {
            return true;
        } else {
            return false;
        }
    }*/

    public static boolean isRegistered(Account account){
        if(loginAccount.containsKey(account.getUsername()) && loginAccount.containsValue(account)){
            return true;
        } else {
            return false;
        }
    }

    public static boolean accountExists(String username) {
        if(loginAccount.containsKey(username)) {
            return true;
        } else {
            return false;
        }
    }
}
