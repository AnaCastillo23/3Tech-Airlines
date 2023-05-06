package GUI;

import DataStructures.AccountAccessor;
import Class.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AccountFrame GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "AccountFrame" helps user edit any information from their account.
 * <p>
 * @since 05/01/2023
 * @author Carlos Figueroa (built structure of frame) and Ana Emily Castillo Perez (added user validation and documentation comments).
 * <p>
 * <b>Explanation of important functions:</b> GUI implements user input into text fields that lets user input new account information. Such inputs include
 * change in email, password, personal information, payment information.
 * The system will only output error messages if any text fields are empty and with the aid of an API, changes will be reflected
 * according to saved changes by user.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>emailInputChecker:</i>Error checks that user has entered an email in text field for system to update.</li>
 * <li><i>passwordInputChecker():</i>Error checks that user has entered a password in text field for system to update.</li>
 * <li><i>personalInfoChecker():</i>Takes in any information entered by user and updates it.</li>
 * <li><i>paymentInfoChecker():</i>Error checks that user has entered a valid credit card number, zip code and directs user's attention to any empty fields.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */

public class AccountFrame extends JFrame {
    private JPanel accountFrame;
    private JTextField tfEmail;
    private JTextField tfConfirmEmail;
    private JButton saveEmailButton;
    private JButton cancelEmailButton;
    private JPasswordField tfCurrentPassword;
    private JPasswordField tfNewPassword;
    private JPasswordField tfConfirmPassword;
    private JButton savePasswordButton;
    private JButton cancelPasswordButton;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfAddress;
    private JTextField tfPhone;
    private JButton savePersonalInfButton;
    private JButton cancelPersonalInfoButton;
    private JComboBox dropDownYear;
    private JComboBox dropDownMonth;
    private JTextField tfCardNumber;
    private JTextField tfCountry;
    private JTextField tfBillingAddress;
    private JTextField tfCity;
    private JComboBox dropDownState;
    private JTextField tfZipCode;
    private JButton savePaymentButton;
    private JButton cancelPaymentButton;
    private JButton goBackButton;

    private AccountAccessor accountAccessor;
    private Account loginAccount;


    public AccountFrame() {
        setContentPane(accountFrame);
        setTitle("Secure Checkout");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // access logged in account
        loginAccount = new Account();
        accountAccessor = new AccountAccessor();

        String username = accountAccessor.getLoginUsername();
        loginAccount = loginAccount.getLoginAccount().get(username);


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
                if (emailInputChecker()) {
                    //save new info into account
                    loginAccount.setEmailAddress("stuff from textfield"); // email setter
                    loginAccount.registerOrUpdate(loginAccount); // update hashmap
                }
                //emailInputChecker();

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
        savePaymentButton.addActionListener(new ActionListener() {
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
        cancelEmailButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the CANCEL button in the current and discarding any information entered.
             * @param e Event
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelPasswordButton.addActionListener(new ActionListener() {
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
        cancelPersonalInfoButton.addActionListener(new ActionListener() {
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
    public boolean emailInputChecker() {
        String newEmail = tfEmail.getText();
        String confirmEmail = tfConfirmEmail.getText();

        if (newEmail.isEmpty() || confirmEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an email to save.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            JOptionPane.showMessageDialog(this, "error check passed.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
            return true;
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

        String stringCurrentPassword = new String(currentPassword);
        String stringNewPassword = new String(newPassword);
        String stringConfirmNewPassword = new String(confirmNewPassword);

        if ((stringCurrentPassword.length() == 0) || (stringNewPassword.length() == 0) || (stringConfirmNewPassword.length() == 0)) {
            JOptionPane.showMessageDialog(this, "Please enter a password to save.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "error check passed.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
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


        if (phoneNumber.length() == 10) {
            JOptionPane.showMessageDialog(this, "error check passed.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else if (phoneNumber.length() >= 1 && phoneNumber.length() < 10) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else if (phoneNumber.length() == 0 && firstName.isEmpty() && lastName.isEmpty() && address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No new information to save was entered.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * Method for validating payment information that user inputs.
     *
     */
    public void paymentInfoChecker() {
        String cardNumber = tfCardNumber.getText();
        String country = tfCountry.getText();
        String billingAddress = tfBillingAddress.getText();
        String city = tfCity.getText();
        String zipCode = tfZipCode.getText();

        if (zipCode.length() == 5 && cardNumber.length() == 16) {
            if (country.isEmpty() || billingAddress.isEmpty() || city.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "error check has passed.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
            }
        } else if (zipCode.length() >= 1 && zipCode.length() < 5) {
            JOptionPane.showMessageDialog(this, "Please enter a valid zip code.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else if (zipCode.length() == 0 && cardNumber.length() == 0 && country.isEmpty() && billingAddress.isEmpty() && city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No new information to save was entered.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
        } else if (cardNumber.length() >= 1 && cardNumber.length() < 16) {
            JOptionPane.showMessageDialog(this, "Please enter a valid card number.", "Invalid Account Information", JOptionPane.ERROR_MESSAGE);
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
