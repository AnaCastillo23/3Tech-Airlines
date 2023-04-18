package GUI;

import Class.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @since 04/18/2023
 * @author Carlos Figueroa (developed structure) and Ana Emily Castillo Perez (added documentiton comments).
 * <p>
 * <b>Description of the class/module:</b> The Login GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "LoginFrame" helps users to log into their alreadyd existing account.
 * <p>
 * <b>Explanation of important functions:</b> GUI implements user input into text fields by gathering username and password that
 * user wants has used to create the account. This information is saved in the system for supporting returning users.
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
     * Method for...
     *
     */
    public LoginFrame() {
        setContentPane(panelLogin);
        setTitle("3Tech-Airlines");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        registerButton.addActionListener(new ActionListener() {
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
     * Registers a new user by setting a new username and password or checks if account already exists.
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
                Account account;
                account = Account.getLoginAccount().get(username);

                if(password.equals(account.getPassword())) {
                    JOptionPane.showMessageDialog(this, "Login successful", "account", JOptionPane.PLAIN_MESSAGE);

                    FlightSearchFrame flightSearchFrame = new FlightSearchFrame();
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
