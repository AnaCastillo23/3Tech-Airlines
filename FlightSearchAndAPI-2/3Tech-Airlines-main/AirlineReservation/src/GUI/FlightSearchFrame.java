/**
 *
 * Module name\Class name: FlightSearchFrame (class)
 * @since 2023-04-05
 * @author Ana Emily Castillo Perez (built structure of frame) and Carlos Figueroa (implemented API into GUI).
 * (see on GitHub branch-ana>FlightSearchGUI for Ana's specific contributions to code and main>AirlineReservation for Carlos' specific contributions).
 *
 * Description of the class/module: The Flight Search GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "MainFrame" helps user search for flights that are available for reservation.
 * Makes use of an API to accomplish this task, along with user input.
 *
 * Explanation of important functions: GUI implements user input into text fields to search for available flights. Such input includes the
 * area user departs from (in ICAO format), area user wants to arrive to, as well as dates of departure and return in dd/mm/yyyy format.
 * User should also specify if the trip is one-way of round. The system also checks if user has inputted data in the corerct format.
 * If not, user is not able to proceed with reservation.
 * The system, with the aid of an API, outputs a list of available flights according to the user's specifications.
 *
 * Important data structure in class/important methods in class:
 * generateSearchList(ArrayList<JSONObject> obj): used for diplaying list of available flights accoding to user input.
 * searchFlight(): used to error check user input which prompts user to enter any incorrect data into the correct format.
 * valDate(String departureDate): error checks the departure date.
 * valDateReturn(String returnDate): error check the reeturn date (in case trip is round).
 *
 *
 * Any algorithms used? Not at the moment.
 *
 */

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
             * with flight reservation and search for available flights with the aid of an API.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //Ana-insert method name here for displaying flight info in a new window for its review before user confirms reservation!!!!!
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
     * Method for error checking user input in current frame and activating API
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
        //Ana-add if statement for locations formatter in ICAO format


        if (valDate(departureDate) && valDateReturn(returnDate)) { //add error check for sanity of date here?
            /*
            THIS CODE WILL BE CALLED AFTER USER SELECTS FLIGHT
            dispose();
            ReviewFrame review = new ReviewFrame();
            review.setVisible(true);//might need to change this because of use of API. Might need to change it to be used with search button after error checks have come clean (then API comes in) and then the OK button connects to review frame
            */
            return true;
        }
        return false;
    }


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
     * Main Program. Creates a new frame (new object of the current frame).
     *
     * @param args Unused.
     *
     */
    public static void main(String[] args) {
        FlightSearchFrame flightFrame = new FlightSearchFrame();
    }
}
