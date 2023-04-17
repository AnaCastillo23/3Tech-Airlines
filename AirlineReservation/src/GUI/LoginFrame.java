/**
 *
 * Module name\Class name: LoginFrame (class)
 * @since
 * @author Carlos Figueroa
 *
 *
 * Description of the class/module:
 *
 * Explanation of important functions:
 *
 * Important data structure in class/important methods in class:
 *
 *
 * Any algorithms used?
 *
 */

package GUI;

import Class.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
