package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPasswordField tfPhone; //has incorrect text field. Fix this
    private JComboBox dropDownYear;
    private JTextField tfCardNumber;
    private JTextField tfCountry;
    private JTextField tfBillingAddress;
    private JTextField textField10;
    private JComboBox comboBox1; //where is this on frame?
    private JTextField textField11;
    private JButton saveButton3;
    private JButton cancelButton3;
    private JComboBox dropDownMonth;


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
             * user's changes to their email
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //save new info into account

            }
        });
        savePasswordButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the SAVE button in the current frame for storing
             * user's changes to their password
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        savePersonalInfButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the SAVE button in the current frame for storing
             * user's changes to their personal info
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Cancel buttons listeners
        emailCancelButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the CANCEL button in the current frame for not storing
             * user's changes to their email
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        passwordCancelButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the CANCEL button in the current frame for not storing
             * user's changes to their password
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        personalInfoCancelButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the CANCEL button in the current frame for not storing
             * user's changes to their personal info
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Go back listener
        goBackButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the GO BACK button which takes user back to their dashboard
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardFrame dashboard = new DashboardFrame();
                setVisible(false);
                dashboard.setVisible(true);
            }
        });
    }

    public void inputChecker() {
        String newEmail = tfEmail.getText();
        String confirmEmail = tfConfirmEmail.getText();
        char[] currentPassword = tfCurrentPassword.getPassword();
        char[] newPassword = tfNewPassword.getPassword();
        char[] confirmPassword = tfConfirmPassword.getPassword();


    }

    public static void main(String[] args) {
        AccountFrame accountFrame = new AccountFrame();
    }
}
