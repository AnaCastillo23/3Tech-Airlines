package GUI;

import DataStructures.AccountAccessor;
import Class.Account;
import Class.Flight;
import Class.Passenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PassengerFrame extends JFrame {
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
    private JScrollPane passengerScroll;
    private JPanel passengerListPanel;


    AccountAccessor accountAccessor;
    Account loginAccount;
    Flight departingFlight;

    ArrayList<Passenger> originalPassengerList;
    ArrayList<Passenger> passengerList;

    String gender;
    int newPassengerCounter;

    /**
     * The PassengerFrame is used in conjunction with information recollected from
     * our system and that is stored in our database (in order to be able to add passengers to a current reservation).
     * @since 03/27/2023
     * @author Carlos Figueroa (developed code, added documentation comments) and Ana Emily Castillo Perez (added documentation comments).
     * <p>
     * <b>Explanation of important functions:</b> Frame allows user to input details about each passenger.
     * <p>
     * <b>Important data structure in class/important methods in class:</b>
     * <ul>
     * <li><i>addPassenger(Passenger passenger):</i> used to add new passengers.</li>
     * </ul>
     * <p>
     *
     * <b>Any algorithms used?</b> Not at the moment.
     *
     */

    public PassengerFrame() {
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
        originalPassengerList = copyArrayList(passengerList);

        newPassengerCounter = 0; // initial passenger is charged after booking in FlightSearchFrame, so = 0

        addPassengerButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used to confirm addition of passenger.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = tfFirstName.getText();
                String lastName = tfLastName.getText();
                String passportCountry = cbPassport.getSelectedItem().toString();
                String dateOfBirth = cbMonth.getSelectedItem() + "-" + cbDay.getSelectedItem() + "-" + cbYear.getSelectedItem();

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
             * Invoked when an action occurs. Used to signify passenger is male.
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
             * Invoked when an action occurs. Used to signify passenger is female.
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
             * Invoked when an action occurs. Used to confirm addition of passenger.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                originalPassengerList = copyArrayList(passengerList);

                // if return-flight == false
                ReviewFrame.addToParty(passengerList,newPassengerCounter, getTitle().contains("Return"));
                newPassengerCounter = 0;
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used to cancel addition of passenger.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passengerList.size() > 0) {
                    passengerList = copyArrayList(originalPassengerList); //undo changes
                    newPassengerCounter = 0;
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please add at least one party member for flight.", "Party Size is Empty", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used to clear the form.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }

    /**
     *
     * Method for adding a passenger onto the reservation.
     *
     * @param passenger passenger
     */
    public void addPassenger(Passenger passenger) {
        passengerList.add(passenger);
        newPassengerCounter++;
        clearForm();
        updatePartyDisplay();
    }

    /**
     *
     * Method for updating the passenger form with the entered data.
     *
     */
    public void updatePartyDisplay() {
        // panel and scroll stuff
        passengerListPanel = new JPanel(new GridLayout(passengerList.size(),1));
        passengerScroll.setViewportView(passengerListPanel);

        for(int i = 0; i < passengerList.size(); i++) {
            JPanel passengerPanel = new JPanel(new GridLayout(2,3));

            // row 1
            passengerPanel.add(new JLabel(passengerList.get(i).getFirstName() + " " + passengerList.get(i).getLastName()));
            passengerPanel.add(new JLabel(""));
            passengerPanel.add(new JLabel(""));

            // row 2
            passengerPanel.add(new JLabel(""));

            JButton editButton = new JButton();
            editButton.setName("editButton" + i);
            editButton.setText("Edit");
            //editButton.addActionListener(clicked);
            passengerPanel.add(editButton);

            JButton removeButton = new JButton();
            removeButton.setName("removeButton" + i);
            removeButton.setText("Remove");
            //removeButton.addActionListener(clicked);
            passengerPanel.add(removeButton);

            passengerListPanel.add(passengerPanel);
        }
    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }

    /**
     *
     * Method for clearing the passenger form.
     *
     */
    public void clearForm() {
        tfFirstName.setText("");
        tfLastName.setText("");
        cbPassport.setSelectedIndex(0);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        cbMonth.setSelectedIndex(0);
        cbDay.setSelectedIndex(0);
        cbYear.setSelectedIndex(0);
    }

    /**
     *
     * Main program.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        PassengerFrame passengerFrame = new PassengerFrame();
        passengerFrame.setVisible(true);
    }

    public static ArrayList<Passenger> copyArrayList(ArrayList<Passenger> oldList) {
        ArrayList<Passenger> clonedList = new ArrayList<Passenger>(oldList.size());
        for (Passenger passenger : oldList) {
            clonedList.add(passenger);
        }
        return clonedList;
    }
}
