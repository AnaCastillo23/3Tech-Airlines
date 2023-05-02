package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AccountFrame GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "AccountFrame" helps user edit any information from their account.
 * <p>
 * @since 05/01/2023
 * @author Ana Emily Castillo Perez (added user validation) and Carlos Figueroa (built structure of frame).
 * <p>
 * <b>Explanation of important functions:</b> GUI implements user input into text fields that lets user input new account information. Such inputs include
 * change in email, password, personal information, payment information.
 * The system will only output error messages if any text fields are empty and with the aid of an API, changes will be reflected
 * according to saved changes by user.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>add method name:</i> used for      .</li>
 * Add as many as needed
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */

public class AccountFrame extends JFrame {
    private JPanel accountFrame;
    private JButton goBackButton;
    private JTextField tfEmail;
    private JTextField tfConfirmEmail;
    private JButton saveEmailButton;
    private JButton emailCancelButton;
    private JPasswordField tfCurrentPassword;
    private JPasswordField tfNewPassword;
    private JButton savePasswordButton;
    private JButton passwordCancelButton;
    private JPasswordField tfConfirmPassword;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JButton savePersonalInfButton;
    private JButton personalInfoCancelButton;
    private JTextField tfAddress;
    private JTextField tfPhone;
    private JTextField tfCardNumber;
    private JTextField tfCountry;
    private JTextField tfBillingAddress;
    private JButton paymentSaveButton;
    private JButton paymentCancelButton;
    private JComboBox dropDownYear;
    private JComboBox dropDownMonth;
    //check where this is
    private JTextField textField10; //where is this on frame?
    private JComboBox comboBox1; //where is this on frame?
    private JTextField textField11; //where is this on frame?


    public AccountFrame() {
        setContentPane(accountFrame);
        setTitle("Secure Checkout");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Action listeners
        saveEmailButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the SAVE button in the current frame for storing
             * user's changes to their email. Saves newly entered info into user's account
             *
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (emailInputChecker()) {
                    save new info into account
                }*/
                emailInputChecker();

            }
        });
        savePasswordButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the SAVE button in the current frame for storing
             * user's changes to their password. Saves newly entered info into user's account.
             *
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (passwordInputChecker()) {
                    save new info into account
                }*/
                //testing methods individually
                passwordInputChecker(); //called methods and made them void, but will make them boolean once account is connected with this frame

            }
        });
        savePersonalInfButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the SAVE button in the current frame for storing
             * user's changes to their personal info. Saves newly entered info into user's account.
             *
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (personalInfoChecker()) {
                    save new info into account
                }*/
                personalInfoChecker();
            }
        });
        paymentSaveButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the SAVE button in the current frame for storing
             * user's changes to their payment. Saves newly entered info into user's account.
             *
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                /*if (paymentInfoChecker) {
                    save new info into account
                }*/
                paymentInfoChecker();
            }
        });
        //Cancel buttons listeners
        emailCancelButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the CANCEL button in the current and discarding any information entered.
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        passwordCancelButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the CANCEL button in the current and discarding any information entered.
             *
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        personalInfoCancelButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the CANCEL button in the current and discarding any information entered.
             *
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Go back listener
        goBackButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the GO BACK button which takes user back to their dashboard.
             *
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardFrame dashboard = new DashboardFrame();
                setVisible(false);
                dashboard.setVisible(true);
            }
        });
    }

    //Methods for user validation
    /**
     *
     * Method for validating email that user inputs.
     *
     */
    public void emailInputChecker() {
        String newEmail = tfEmail.getText();
        String confirmEmail = tfConfirmEmail.getText();

        if (newEmail.isEmpty() || confirmEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "all good.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     *
     * Method for validating password that user inputs.
     *
     */
    public void passwordInputChecker() {
        char[] currentPassword = tfCurrentPassword.getPassword();
        char[] newPassword = tfNewPassword.getPassword();
        char[] confirmNewPassword = tfConfirmPassword.getPassword();

        //Error checking password still doesn't work
        String stringCurrentPassword = new String(currentPassword);
        String stringNewPassword = new String(newPassword);
        String stringConfirmNewPassword = new String(confirmNewPassword);

        if ((stringCurrentPassword.length() == 0) || (stringNewPassword.length() == 0) || (stringConfirmNewPassword.length() == 0)) {
            JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "all good", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * Method for validating personal information that user inputs.
     *
     */
    public void personalInfoChecker() {
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String address = tfAddress.getText();
        String phoneNumber = tfPhone.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "all good", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
            /*Error checking phone number doesn't work yet
            if (phoneNumber.matches("^[0-9]*$" && phoneNumber.length()==10 )) {
                //return false;
            } else {
                return true;
            }*/
        }
    }

    public void paymentInfoChecker() {
        String cardNumber = tfCardNumber.getText();
        String country = tfCountry.getText();
        String billingAddress = tfBillingAddress.getText();

        if (cardNumber.isEmpty() || country.isEmpty() || billingAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "all good.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        }
    }



    /**
     *
     * Main Program
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        AccountFrame accountFrame = new AccountFrame();
    }
}
