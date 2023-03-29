package GUI;

import javax.swing.*;

public class LoginFrame extends JFrame {
    private JPanel panelLogin;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton logInButton;
    private JButton forgotPasswordButton;
    private JButton registerButton;


    public LoginFrame() {
        setContentPane(panelLogin);
        setTitle("3Tech-Airlines");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
    }
}
