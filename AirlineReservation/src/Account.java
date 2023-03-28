import java.util.HashMap;
import java.util.Map;

public class Account {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;

    private static final Map<String, String> loginAccount = new HashMap<>();


    public Account() {
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
    }

    public Account(String username, String password, String firstName, String lastName, String emailAddress) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
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

    public String getEmailAddress() {
        return this.emailAddress;
    }


    public Map<String, String> getLoginAccount() {
        return loginAccount;
    }

    public void register(Account account) {
        loginAccount.put(account.getUsername(), account.getPassword());
    }

    public void login(Account account) {
        if(isRegistered(account)) {

        } else {

        }
    }

    public boolean isRegistered(Account account){
        if(loginAccount.containsKey(account.getUsername()) && loginAccount.containsValue(account.getPassword())){
            return true;
        } else {
            return false;
        }
    }
}
