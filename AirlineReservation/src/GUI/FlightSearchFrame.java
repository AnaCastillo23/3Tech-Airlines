package GUI;

import Class.Flight;
import Class.Airport;
import Class.FlightSearchHelper;
import API.FlightController;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


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


    /**
     *
     * Method is for creating and displaying a desktop window to a specific size as program runs.
     *
     */
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

                FlightSearchHelper searchHelper;

                ArrayList<JSONObject> searchData = new ArrayList<>();


                if(searchFlight()) {
                    //airport.setAirportCode(tfDeparture.getText());
                        //airport.setAirportCode(tfArrival.getText());
                    //flight.setDepartureDate(tfDepartureDate.getText());

                    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");

                    searchHelper = new FlightSearchHelper(tfDeparture.getText(), tfDepartureDate.getText());


                    try {
                        System.out.println("hello");
                        ArrayList<JSONObject> flightDataArrayList = flightController.getFlightData(searchHelper);
                        //System.out.println(flightDataArrayList);

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
                //go back to dashboard??????????
            }
        });
    }

    /**
     *
     * Method for printing the available flights into a list after user has clicked the OK button
     * @param obj obj
     *
     */
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
     * Method for error checking user input in Flight Search Frame and activating API
     * (list of available flights according to user's specifications)
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

        if (valDate(departureDate) && valDateReturn(returnDate)) { //add error check for sanity of date here?
            /*
            THIS CODE WILL BE CALLED AFTER USER SELECTS FLIGHT
            dispose();
            ReviewFrame review = new ReviewFrame();
            review.setVisible(true);//might need to change this because of use of API. Might need to change it to be used with search button after error checks have come clean (then API comes in) and then the OK button connects to review frame
            */
            return true;
        }
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
     * Main Program. Creates a new frame.
     *
     * @param args Unused.
     *
     */
    public static void main(String[] args) {
        FlightSearchFrame flightFrame = new FlightSearchFrame();
    }
}
