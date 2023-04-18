package GUI;

import Class.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @since 04/18/2023
 * @author Carlos Figueroa (developed structure) and Ana Emily Castillo Perez (added documentiton comments).
 * <p>
 * <b>Description of the class/module:</b> The Register GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "RegisterFrame" helps users to create a new account
 * in order to book a flight.
 * <p>
 * <b>Explanation of important functions:</b> GUI implements user input into text fields by gathering useername and password that
 * user wants to use to create the account. This information is saved in the system for supporting returning users.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>registerUser():</i> used for error checking usesr input.</li>
 *
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class RegisterFrame extends JFrame {
    private JPanel panelRegister;
    private JButton buttonRegister;
    private JButton buttonCancel;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfUsername;
    private JTextField tfAddress;
    private JTextField tfEmailAddress;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JTextField tfPhone;

    /**
     *
     *Method is for creating and displaying a desktop window to a specific size as RegisterFrame runs.
     *
     */
    public RegisterFrame() {
        setContentPane(panelRegister);
        setTitle("Register");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buttonRegister.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the Register button of current frame if user decides to continue
             * enrollment in the system.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the Cancel button of current frame if user decides to not continue with the
             * enrollment in the system.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    /**
     *
     * Method for error checking user input in text fields of the current frame and activating following frame to continue
     * with booking.
     * Checks if text fields are empty.
     *
     */
    private void registerUser() {
        String username = tfUsername.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String address = tfAddress.getText();
        String emailAddress = tfEmailAddress.getText();
        String phone = tfPhone.getText();

        if(username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || emailAddress.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are items that require your attention", "Invalid Registration", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", "Invalid Registration", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // TRY AGAIN!
            if(!Account.accountExists(username)) {
                Account account = new Account(username,password,firstName,lastName,address,emailAddress,phone);
                Account.register(account);

                JOptionPane.showMessageDialog(this, "Account successfully created", "Registered", JOptionPane.PLAIN_MESSAGE);
                LoginFrame loginFrame = new LoginFrame();
                setVisible(false);
                loginFrame.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "That username is already taken", "Invalid Registration", JOptionPane.ERROR_MESSAGE);
                //return;
            }
        } catch(Exception e) {

        }
    }

    /**
     *
     * Main Program. Creates a new frame (new object of the current frame).
     *
     * @param args Unused.
     *
     */
    public static void main(String[] args) {
        RegisterFrame registerFrame = new RegisterFrame();
    }
}
