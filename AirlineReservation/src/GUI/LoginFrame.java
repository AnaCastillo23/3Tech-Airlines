package GUI;

import Class.Account;
import Helper.AccountAccessor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Login GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "LoginFrame" helps users to log into their alreadyd existing account.
 * @since 04/18/2023
 * @author Carlos Figueroa (developed structure) and Ana Emily Castillo Perez (added documentiton comments).
 * <p>
 * <b>Explanation of important functions:</b> GUI implements user input into text fields by gathering username and password that
 * user has used to create the account. This information is saved in the system for supporting returning users.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>loginUser():</i> used for error checking user's input as well as checking if account already exists.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class LoginFrame extends JFrame {
    private JPanel panelLogin;
    private JPasswordField pfPassword;
    private JTextField tfUsername;
    private JButton logInButton;
    private JButton forgotPasswordButton;
    private JButton registerButton;

    /**
     *
     * Method is for creating and displaying a desktop window to a specific size as LoginFrame runs.
     *
     */
    public LoginFrame() {
        setContentPane(panelLogin);
        setTitle("3Tech-Airlines");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        logInButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the Login button of current frame if user decides to log into their
             * already existing account
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        forgotPasswordButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the forgot password button of current frame if user requires
             * assistance in case of a forgotten password
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        registerButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the Register button of current frame if user decides to continue
             * enrollment in the system.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterFrame registerFrame = new RegisterFrame();
                setVisible(false);
                registerFrame.setVisible(true);
            }
        });

        setVisible(true);
    }

    /**
     *
     * Method for error checking user input in text fields of the current frame.
     * Also checks if text fields are empty.
     * Logins a user by requiring username and password and checks if account already exists.
     *
     */
    private void loginUser() {
        String username = tfUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());

        if(username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username or password missing", "Invalid Login", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if(Account.accountExists(username)) {
                Account account = new Account();
                account = account.getLoginAccount().get(username);

                if(password.equals(account.getPassword())) {
                    JOptionPane.showMessageDialog(this, "Login successful", "account", JOptionPane.PLAIN_MESSAGE);

                    FlightSearchFrame flightSearchFrame = new FlightSearchFrame();
                    AccountAccessor accountAccessor = new AccountAccessor(username);  // account access throughout gui

                    setVisible(false);
                    flightSearchFrame.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "Wrong Password", "Invalid Login", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch(Exception e) {}
    }

    /**
     *
     * Main Program. Creates a new frame (new object of the current frame).
     * @param args Unused.
     */
    //@SpringBootApplication
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();

        //SpringApplication.run();
    }
}
