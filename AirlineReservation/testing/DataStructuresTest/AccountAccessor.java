package DataStructuresTest;

import java.util.HashMap;
import java.util.Map;

public class AccountAccessor {
    private static final Map<String, String> accountAccessor = new HashMap<>();
    private String username;


    public AccountAccessor() {
        // empty
    }
    // call when user logs in
    public AccountAccessor(String username) {
        this.username = username;
        accountAccessor.put("Current Login", username);
    }


    public String getLoginUsername() {
        return accountAccessor.get("Current Login");
    }

    public void logoutAccount() {
        accountAccessor.clear();
    }
}
