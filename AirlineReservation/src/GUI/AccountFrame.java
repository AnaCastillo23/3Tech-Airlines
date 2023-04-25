package GUI;

import javax.swing.*;

public class AccountFrame extends JFrame {
    private JPanel accountFrame;
    private JButton goBackButton;
    private JTextField exampleGmailComTextField;
    private JTextField textField2;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField textField3;
    private JTextField textField4;
    private JButton saveButton1;
    private JButton cancelButton1;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JTextField textField5;
    private JButton saveButton2;
    private JButton cancelButton2;
    private JTextField textField6;
    private JPasswordField passwordField2;
    private JComboBox comboBox2;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JComboBox comboBox1;
    private JTextField textField11;
    private JButton saveButton3;
    private JButton cancelButton3;


    public AccountFrame() {
        setContentPane(accountFrame);
        setTitle("Secure Checkout");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        AccountFrame accountFrame = new AccountFrame();
    }
}
