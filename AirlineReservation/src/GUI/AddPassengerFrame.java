package GUI;

import DataStructures.AccountAccessor;
import Class.Account;
import Class.Flight;
import Class.Passenger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddPassengerFrame extends JFrame {
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JButton addPassengerButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private JComboBox cbPassport;
    private ButtonGroup genderRadioButtonGroup;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JComboBox cbMonth;
    private JComboBox cbYear;
    private JComboBox cbDay;
    private JPanel addPassengerPanel;
    private JButton clearButton;
    private JCheckBox checkBoxAccountOwner;

    AccountAccessor accountAccessor;
    Account loginAccount;
    Flight departingFlight;

    ArrayList<Passenger> originalPassengerList;
    ArrayList<Passenger> passengerList;

    String gender;


    public AddPassengerFrame() {
        setContentPane(addPassengerPanel);
        setTitle("Add Passenger");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        passengerList = new ArrayList<>();
        genderRadioButtonGroup = new ButtonGroup();
        genderRadioButtonGroup.add(maleRadioButton);
        genderRadioButtonGroup.add(femaleRadioButton);

        accountAccessor = new AccountAccessor();
        loginAccount = new Account();
        String username = accountAccessor.getLoginUsername();
        loginAccount = loginAccount.getLoginAccount().get(username);

        // ADD COUNTRY, GENDER, DOB TO ACCOUNT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Passenger defaultPassenger = new Passenger(loginAccount.getFirstName(), loginAccount.getLastName(), null, null, null);
        addPassenger(defaultPassenger);


        addPassengerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = tfFirstName.getText();
                String lastName = tfLastName.getText();
                String passportCountry = cbPassport.getSelectedItem().toString();
                String dateOfBirth = cbMonth.getSelectedItem() + "\\" + cbDay.getSelectedItem() + "\\" + cbYear.getSelectedItem();

                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(passportCountry);
                System.out.println(gender);
                System.out.println(dateOfBirth);

                Passenger passenger = new Passenger(firstName,lastName,passportCountry,gender,dateOfBirth);
                addPassenger(passenger);
            }
        });

        maleRadioButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!maleRadioButton.isCursorSet()) {
                    gender = "male";
                    System.out.println("if -> !maleRadioButton.isCursorSet(): " + gender);
                } else {
                    gender = "female";
                    System.out.println("else -> !maleRadioButton.isCursorSet(): " + gender);
                }
            }
        });
        femaleRadioButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (femaleRadioButton.isCursorSet()) {
                    gender = "male";
                    System.out.println("if -> femaleRadioButton.isCursorSet(): " + gender);
                } else {
                    gender = "female";
                    System.out.println("else -> femaleRadioButton.isCursorSet(): " + gender);
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                originalPassengerList = passengerList;

                ReviewFrame.updateParty(passengerList);

                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passengerList.size() > 1) {
                    passengerList = originalPassengerList; //undo changes
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please add at least one party member for flight.", "Party Size is Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }

    public void addPassenger(Passenger passenger) {
        passengerList.add(passenger);
        clearForm();
        updatePartyDisplay();
    }

    public void updatePartyDisplay() {
        // panel and scroll stuff
    }

    public void clearForm() {

    }

    public static void main(String[] args) {
        AddPassengerFrame addPassengerFrame = new AddPassengerFrame();
        addPassengerFrame.setVisible(true);
    }
}
