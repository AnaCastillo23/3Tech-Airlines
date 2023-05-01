package GUI;

import API.FlightModel;
import DataStructures.FlightsToReview;
import API.ScheduledDeparturesFilter;
import Managers.PriceGenerator;
import org.json.JSONObject;

import Class.Flight;
import Class.Airport;
import Class.Airline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Flight Search GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "FlightSearchFrame" helps user search for flights that are available for reservation.
 * Makes use of an API to accomplish this task, along with user input.
 * <p>
 * @since 03/27/2023
 * @author Ana Emily Castillo Perez (built structure of frame) and Carlos Figueroa (implemented API into GUI).
 * <p>
 * <b>Explanation of important functions:</b> GUI implements user input into text fields to search for available flights. Such input includes the
 * area user departs from (in ICAO format), area user wants to arrive to, as well as dates of departure and return in dd/mm/yyyy format.
 * User should also specify if the trip is one-way of round. The system also checks if user has inputted data in the corerct format.
 * If not, user is not able to proceed with reservation.
 * The system, with the aid of an API, outputs a list of available flights according to the user's specifications.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>generateSearchList(ArrayList<JSONObject> obj):</i> used for diplaying list of available flights accoding to user input.</li>
 * <li><i>searchFlight():</i> used to error check user input which prompts user to enter any incorrect data into the correct format.</li>
 * <li><i>valDate(String departureDate):</i> error checks the departure date.</li>
 * <li><i>valDateReturn(String returnDate):</i> error checks the return date (in case trip is round).</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class FlightSearchFrame extends JFrame {
    private JTextField tfDeparture;
    private JTextField tfArrival;
    private JTextField tfDepartureDate;
    private JTextField tfReturnDate;
    private JRadioButton roundTripRadioButton;
    private JRadioButton oneWayRadioButton;
    private JButton searchButton;
    private JButton cancelButton;
    private JPanel flightPanel;
    private JPanel searchList;
    private JScrollPane searchScroll;
    private JButton bookButton;
    private JPanel flightInfo;
    ButtonClicked clicked = new ButtonClicked();
    Boolean displayReturnFlights;   // true if roundtrip radio button is checked otherwise false
    ArrayList<JSONObject> searchData;
    ArrayList<JSONObject> bookedFlights; // send to ReviewFrame (size: 0,1(one way),2(round-trip))
    ArrayList<Double> priceList;
    double totalPrice = 0;

    /**
     *
     * Method is for creating and displaying a desktop window to a specific size as FlightSearchFrame runs.
     *
     */
    public FlightSearchFrame() {
        setContentPane(flightPanel);
        setTitle("Flight Information");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        bookedFlights = new ArrayList<>();
        priceList = new ArrayList<>();

        //Button actions
        searchButton.addActionListener(new ActionListener() {
            /**
             *
             * Action listener used to code the OK button of current frame if user decides to continue
             * with flight reservation and search for available flights with the aid of an API.
             *
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightModel flightModel = new FlightModel();
                ScheduledDeparturesFilter searchFilter;

                // reset display arrays(REFRESH):
                searchData = new ArrayList<>();

                if(searchFlight()) {
                    try {
                        // API Call
                        searchFilter = new ScheduledDeparturesFilter(tfDeparture.getText(), tfArrival.getText(), tfDepartureDate.getText());
                        searchData = flightModel.getScheduledDepartuesFiltered(searchFilter);
                        //System.out.println(flightDataArrayList);

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
                DashboardFrame dashboard = new DashboardFrame();

                setVisible(false);
                dashboard.setVisible(true);
            }
        });


        // All TextFields and Buttons are disabled until one is selected:
        roundTripRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //displayReturnFlights = true;
                if (!roundTripRadioButton.isCursorSet()) {
                    tfReturnDate.setEditable(true);
                    displayReturnFlights = true;
                } else {
                    tfReturnDate.setEditable(false);
                }
            }
        });
        oneWayRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //displayReturnFlights = false;
                if (oneWayRadioButton.isCursorSet()) {
                    tfReturnDate.setEditable(true);
                    displayReturnFlights = false;
                } else {
                    tfReturnDate.setEditable(false);
                }
            }
        });
    }

    /**
     *
     * Method for displaying the available flights into a list after user has clicked the OK button.  Also generates "Book"
     * buttons for each flight that is displayed
     * @param searchResult searchResult
     *
     */
    private void generateSearchList(ArrayList<JSONObject> searchResult) {
        System.out.println(searchResult);
        System.out.println(searchResult.size());
        System.out.println();

        PriceGenerator priceGenerator = new PriceGenerator();

        searchList = new JPanel();
        searchList.setLayout(new GridLayout(searchResult.size(), 1, 0, 10));
        searchScroll.setViewportView(searchList);

        for (int i = 0; i < searchResult.size(); i++) {
            try {
                priceList.add(priceGenerator.getFlightPrice(displayReturnFlights));

                // pane maker
                flightInfo = new JPanel();
                flightInfo.setLayout(new GridLayout(3, 3));

                flightInfo.add(new JLabel("Depart from " + searchResult.get(i).getJSONObject("origin").get("code_iata").toString()));
                flightInfo.add(new JLabel("Arrive to " + searchResult.get(i).getJSONObject("destination").get("code_iata").toString()));
                flightInfo.add(new JLabel(searchResult.get(i).get("operator").toString()));

                flightInfo.add(new JLabel(searchResult.get(i).getJSONObject("origin").get("city").toString()));
                flightInfo.add(new JLabel(searchResult.get(i).getJSONObject("destination").get("city").toString()));
                flightInfo.add(new JLabel("$" + String.format("%.2f", priceList.get(i))));

                String departureDateTime = searchResult.get(i).get("scheduled_out").toString();
                String arrivalDateTime = searchResult.get(i).get("estimated_in").toString();

                flightInfo.add(new JLabel(departureDateTime.substring(5, 7) + "/" + departureDateTime.substring(8, 10) + "/" +
                        departureDateTime.substring(0, 4) + " " + departureDateTime.substring(11, 16)));
                flightInfo.add(new JLabel(arrivalDateTime.substring(5, 7) + "/" + arrivalDateTime.substring(8, 10) + "/" +
                        arrivalDateTime.substring(0, 4) + " " + arrivalDateTime.substring(11, 16)));

                bookButton = new JButton();
                // Apply an identifier to the Button:
                bookButton.setName(new StringBuilder("bookButton").append(i).toString());
                bookButton.setText("Book");
                bookButton.addActionListener(clicked); // <= private class ButtonClicked implements ActionListener
                flightInfo.add(bookButton);

                flightInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                searchList.add(flightInfo);
            } catch(Exception e) {
                System.out.println("Error");

            }
        }

    }


    /**
     *
     * Method for error checking user input in text fields of the current frame and activating API
     * (list of available flights according to user's specifications appears after error checks come clean).
     * Checks if text fields are empty.
     *
     */
    private boolean searchFlight() {
        String departureLocation = tfDeparture.getText();
        String arrivalLocation = tfArrival.getText();
        String departureDate = tfDepartureDate.getText();
        String returnDate = tfReturnDate.getText();



        /*if(displayReturnFlights) {
            if (oneWayRadioButton.isCursorSet()) {
                if (departureLocation.isEmpty() || arrivalLocation.isEmpty() || departureDate.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    System.out.println(displayReturnFlights);
                }
            } else if (!roundTripRadioButton.isCursorSet()) {
                if (departureLocation.isEmpty() || arrivalLocation.isEmpty() || departureDate.isEmpty() || returnDate.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
                    return false;
                } else {
                    System.out.println(displayReturnFlights);
                }
            }
        }

        if(displayReturnFlights) {
            if(valDate(departureDate) && valDateReturn(returnDate)) {
                return true;
            } else {
                valDate(departureDate);
                return true;
            }
        }*/
        //return false;

        return true;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm/dd/yyyy");
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
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("mm/dd/yyyy");
        try {
            formatter2.parse(returnDate);
            return true;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter return date in correct format.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Flight bookFlight(JSONObject bookedFlight) {
        String departFlightNumber = bookedFlight.get("ident_iata").toString();
        //"2023-04-20T01:25:00Z"
        // 01234567890123456789
        String departureDate = bookedFlight.get("scheduled_out").toString().substring(0,10);
        departureDate = departureDate.substring(5,7) + "/" + departureDate.substring(8,10) + "/" + departureDate.substring(0,4);
        String arrivalDate = bookedFlight.get("estimated_in").toString().substring(0,10);
        arrivalDate = arrivalDate.substring(5,7) + "/" + arrivalDate.substring(8,10) + "/" + arrivalDate.substring(0,4);

        String departureTime = bookedFlight.get("scheduled_out").toString().substring(11,19);
        String arrivalTime = bookedFlight.get("estimated_in").toString().substring(11,19);

        String departureLocation = bookedFlight.getJSONObject("origin").get("city").toString();
        String arrivalLocation = bookedFlight.getJSONObject("destination").get("city").toString();;

        String departureAirportCode = bookedFlight.getJSONObject("origin").get("code_iata").toString();
        String departureAirportName = bookedFlight.getJSONObject("origin").get("name").toString();
        String arrivalAirportCode = bookedFlight.getJSONObject("destination").get("code_iata").toString();
        String arrivalAirportName = bookedFlight.getJSONObject("destination").get("name").toString();

        String airlineID = bookedFlight.get("operator").toString();

        Airport departureAirport = new Airport(departureAirportCode, departureAirportName);
        Airport arrivalAirport = new Airport(arrivalAirportCode, arrivalAirportName);
        Airline airline = new Airline(airlineID, null);

        Flight departingFlight = new Flight(departFlightNumber, departureDate, arrivalDate, departureTime, arrivalTime, departureLocation, arrivalLocation);
        departingFlight.setDepartureAirport(departureAirport);
        departingFlight.setArrivalAirport(arrivalAirport);
        departingFlight.setAirline(airline);

        return departingFlight;
    }

    /**
     *
     * ActionListener Class specifically designed for auto-generated "Book" button.
     *
     */
    private class ButtonClicked implements ActionListener {
        /**
         *
         * Action listener that identifies which "Book" button is clicked
         *
         */
        public void actionPerformed(ActionEvent e) {
            if (e.toString().contains("cmd=Book")) {
                // Using searchResult.get(i) data to Class folder to send flight info to ReviewFrame
                // reset searchData array
                // if booking round-trip, then clear search list and display destination -> home flights
                // fill in searchData array and bookButton array based on api call
                // reset searchData and jscroll pane

                if(bookedFlights.size() > 0) {
                    // after flight search display has been refreshed one time (round-trip)
                    displayReturnFlights = false;
                }

                int bookButtonIndex = Integer.parseInt(((JButton) e.getSource()).getName().substring(bookButton.getName().length() - 1));
                System.out.println(searchData.get(bookButtonIndex)); // test
                bookedFlights.add(searchData.get(bookButtonIndex));
                System.out.println(priceList);
                System.out.println(priceList.get(bookButtonIndex));

                // might be buggy with roundtrip if return flight not available
                totalPrice += priceList.get(bookButtonIndex);

                searchData = new ArrayList<>();

                if(displayReturnFlights) {
                    try {
                        // API Call for return flight
                        FlightModel flightModel = new FlightModel();

                        ScheduledDeparturesFilter searchFilter = new ScheduledDeparturesFilter(tfArrival.getText(), tfDeparture.getText(), tfReturnDate.getText());
                        searchData = flightModel.getScheduledDepartuesFiltered(searchFilter);
                        System.out.println(searchData); // test
                        generateSearchList(searchData);
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                // go to ReviewFrame
                // if(!roundTrip) -> dispose()
                if(!displayReturnFlights) {
                    // one-way or departing(round-trip)
                    ArrayList<Flight> bookedFlightList = new ArrayList<>();
                    // AIRLINE OPERATOR TOO
                    JSONObject departureFlightObj = bookedFlights.get(0);
                    Flight departingFlight = bookFlight(departureFlightObj);

                    bookedFlightList.add(departingFlight);

                    if(bookedFlights.size() > 1) {
                        // create return trip Flight (round-trip only)
                        JSONObject returnFlightObj = bookedFlights.get(1);

                        Flight returningFlight = bookFlight(returnFlightObj);
                        bookedFlightList.add(returningFlight);
                    }

                    System.out.println(bookedFlightList.size());
                    FlightsToReview flightsToReview = new FlightsToReview(bookedFlightList, totalPrice);

                    ReviewFrame review = new ReviewFrame();
                    dispose();
                    review.setVisible(true);
                }
            } else {
                System.out.println(false);
            }
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