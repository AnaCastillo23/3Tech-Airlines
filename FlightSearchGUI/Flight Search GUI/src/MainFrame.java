/**
 *
 * The Flight Search GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "MainFrame" helps user search for flights that are available for reservation.
 * Makes use of an API to accomplish this task, along with user input.
 *
 * @author Ana Emily Castillo Perez
 * @version 1.4.1
 * @since  2023-04-05
 *
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;


public class MainFrame extends JFrame {
    private JTextField tfDeparture;
    private JTextField tfArrival;
    private JTextField tfDepartureDate;
    private JTextField tfReturnDate;
    private JRadioButton roundTripRadioButton;
    private JRadioButton oneWayRadioButton;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel flightPanel;
    private JList selectFlightList;


    /**
     *
     * This method is for creating and displaying a desktop window to a specific size as program runs. Displays text boxes for user to input flight characteristics.
     *
     */
    public MainFrame() {
        setContentPane(flightPanel);
        setTitle("Flight Information");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        OKButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the OK button of current frame if user decides to continue
             * with flight reservation.
             *
             */

            @Override
            public void actionPerformed(ActionEvent e) {
                searchFlight();
                //might need to connect review frame here instead of calling searchFlight
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the CANCEL button of current frame if user decides to cancel
             * flight reservation and return to dashboard.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //go back to dashboard frame




            }
        });
    }

    /**
     *
     * Method for searching a flight which works in conjunction with API and user input.
     *
     */
    private void searchFlight() {
        String departureLocation = tfDeparture.getText();
        String arrivalLocation = tfArrival.getText();
        String departureDate = tfDepartureDate.getText();
        String returnDate = tfReturnDate.getText();


        if (departureLocation.isEmpty() || arrivalLocation.isEmpty() || departureDate.isEmpty() || returnDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //add if statement for locations formatter






        if (valDate(departureDate) && valDateReturn(returnDate)) { //add error check for sanity of date here?
            dispose();
            Review review = new Review();
            review.setVisible(true);//might need to change this because of use of API. Might need to change it to be used with search button after error checks have come clean (then API comes in) and then the OK button connects to review frame
        }
    }

    //add locations formatter (how does API make use of location input?)







    /**
     *
     * Method for validating departing date format from user input.
     *
     * @param departureDate departureDate
     * @return Boolean
     *
     */
    public boolean valDate(String departureDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        try {
            formatter.parse(departureDate);
            return true;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter departure date in correct format.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     *
     * Method for validating returning date format from user input.
     *
     * @param returnDate returnDate
     * @return Boolean
     *
     */
    public boolean valDateReturn(String returnDate) {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        try {
            formatter2.parse(returnDate);
            return true;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter return date in correct format.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     *
     * This method checks for the sanity of departure date.
     *
     * @param departureDate
     * @return
     *
     */
    /*public boolean isDateSane(String departureDate) {
        try {
            DateFormat dateSanity = new SimpleDateFormat(departureDate);
            dateSanity.setLenient(false);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter date in correct format.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }*/

    //add method for sanity of return date







    /**
     *
     * Main Program. Creates a new frame.
     *
     * @param args Unused.
     *
     */
    public static void main(String[] args) {
        MainFrame flightFrame = new MainFrame();
    }
}
