package GUI;

import Class.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public RegisterFrame() {
        setContentPane(panelRegister);
        setTitle("Register");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

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

    public static void main(String[] args) {
        RegisterFrame registerFrame = new RegisterFrame();
    }
}
