import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel panelMain;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton logInButton;
    private JButton forgotPasswordButton;
    private JButton registerButton;


    public MainFrame() {
        setContentPane(panelMain);
        setTitle("3Tech-Airlines");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
    }
}
