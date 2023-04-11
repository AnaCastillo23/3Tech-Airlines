package GUI;

import Class.Flight;
import Class.Airport;
import API.FlightController;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 *
 * This method is for creating and displaying a desktop window to a specific size as program runs. Displays text boxes for user to input flight characteristics.
 *
 */
public class FlightSearchFrame extends JFrame {
    private JTextField tfDeparture;
    private JTextField tfArrival;
    private JTextField tfDepartureDate;
    private JTextField tfReturnDate;
    private JRadioButton roundTripRadioButton;
    private JRadioButton oneWayRadioButton;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel flightPanel;
    private JPanel searchList;
    private JScrollPane searchScroll;

    public FlightSearchFrame() {
        setContentPane(flightPanel);
        setTitle("Flight Information");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Button actions
        OKButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the OK button of current frame if user decides to continue
             * with flight reservation.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //insert method name here for displaying flight info in a new window for its review before user confirms reservation
                FlightController flightController = new FlightController();

                Airport airport = new Airport();
                Flight flight = new Flight();

                ArrayList<JSONObject> searchData = new ArrayList<>();


                if(searchFlight()) {
                    airport.setAirportCode(tfDeparture.getText());
                    //airport.setAirportCode(tfArrival.getText());
                    flight.setDepartureDate(tfDepartureDate.getText());

                    try {
                        System.out.println("hello");
                        ArrayList<JSONObject> flightDataArrayList = flightController.getFlightData(flight, airport);

                        for (int i = 0; i < flightDataArrayList.size(); i++) {
                            JSONObject jsonObj1 = (JSONObject) flightDataArrayList.get(i);
                            System.out.println("jsonObj(" + i + ") : " + jsonObj1.get("destination"));
                            Object obj = jsonObj1.get("destination");

                            JSONObject jsonObj2 = new JSONObject(String.valueOf(obj));
                            System.out.println("code(" + i + ") : " + jsonObj2.get("code"));
                            String code = (String) jsonObj2.get("code");

                            System.out.println(tfArrival.getText());
                            System.out.println(code);
                            if (code.equals(String.valueOf(tfArrival.getText()))) {
                                System.out.println(true);
                                searchData.add(jsonObj1);
                            } else {
                                System.out.println(false);
                            }
                        }

                        generateSearchList(searchData);

                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
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
            }
        });

        //add listener to radio buttons to be able to display user's choice of trip in review window (next window). Might need to say that this listener extends the review window?
        //How to group two radio buttons so that only one can be chosen at a time?
    }

    private void generateSearchList(ArrayList<JSONObject> obj) {
        System.out.println(obj);
        System.out.println(obj.size());
        System.out.println();

        searchList = new JPanel(new FlowLayout(FlowLayout.LEFT));

        for(int i = 0; i < obj.size(); i++) {
            JLabel label = new JLabel();

            searchList.add(label);
            label.setText("Flight Number: " + obj.get(i).get("ident"));
            searchScroll.setViewportView(searchList);

        }
    }

    /**
     *
     * Method for searching a flight which works in conjunction with API and user input.
     *
     */
    private boolean searchFlight() {
        String departureLocation = tfDeparture.getText();
        String arrivalLocation = tfArrival.getText();
        String departureDate = tfDepartureDate.getText(); //how would program know if date was entered correctly using correct number of characters
        String returnDate = tfReturnDate.getText(); //how would program know if date was entered correctly using correct number of characters

        if (departureLocation.isEmpty() || arrivalLocation.isEmpty() || departureDate.isEmpty() || returnDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //add if statement for locations formatter



        //commented out until date formatter is fixed
        /*
        if (valDate(departureDate) && valDateReturn(returnDate)) { //add error check for sanity of date here?
            dispose();
            ReviewFrame review = new ReviewFrame();
            review.setVisible(true);//might need to change this because of use of API. Might need to change it to be used with search button after error checks have come clean (then API comes in) and then the OK button connects to review frame
            return false;
        }*/
        return true;
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
        FlightSearchFrame flightFrame = new FlightSearchFrame();
    }
}
