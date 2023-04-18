package GUI;

import API.FlightModel;
import Helper.ScheduledDeparturesFilter;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @since 03/27/2023
 * @author Ana Emily Castillo Perez (built structure of frame) and Carlos Figueroa (implemented API into GUI).
 * <p>
 * <b>Description of the class/module:</b> The Flight Search GUI is used to create a desktop application
 * for a flight reservation system. This specific GUI called "FlightSearchFrame" helps user search for flights that are available for reservation.
 * Makes use of an API to accomplish this task, along with user input.
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
    ButtonClicked clicked = new ButtonClicked();

    ArrayList<JSONObject> searchData;

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
                //Ana-insert method name here for displaying flight info in a new window for its review before user confirms reservation!!!!!
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
                //dispose();
                //Ana-go back to dashboard??????????
            }
        });


        // bookButton will be assigned to the book button the user selects in flight search results
        // call method first to assign bookButton
    }

    /**
     *
     * Method for printing the available flights into a list after user has clicked the OK button
     * @param searchResult searchResult
     *
     */
    private void generateSearchList(ArrayList<JSONObject> searchResult) {
        System.out.println(searchResult);
        System.out.println(searchResult.size());
        System.out.println();

        searchList = new JPanel(new FlowLayout(FlowLayout.LEFT));


        // bookButton is assigned here:
        // loop
            // assigns every FlightDisplay object and its assigned book button with int i and stores into arrayList<FlightDisplay> and arrayList<Button>
                // FlightDisplay will contain for both depart and arrival: airportCode, cityName, date, time, airline operator
                    // arrayList<FlightDisplay> should be global and then reset if roundtrip is selected
                // every book button will be automatically be assigned a unique name: JButton bookButtonTemp.setName("bookButton" + str(i))
            // bookButtonList.add(bookButtonTemp) for every iteration
        for (int i = 0; i < searchResult.size(); i++) {
            // pane maker
            JLabel label = new JLabel();
            searchList.add(label);
            label.setText("Flight Number: " + searchResult.get(i).get("ident"));
            searchScroll.setViewportView(searchList);


            // This works....need to fix pane
            bookButton = new JButton();
            // Apply an identifier to the Button:
            bookButton.setName(new StringBuilder("bookButton").append(i).toString());
            bookButton.setText("Book");
            //button.setBackground(Color.LIGHT_GRAY);
            bookButton.addActionListener(clicked); // <= private class ButtonClicked implements ActionListener
            //gbc.gridx = j;
            //gbc.gridy = i;
            searchList.add(bookButton);
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

        if (departureLocation.isEmpty() || arrivalLocation.isEmpty() || departureDate.isEmpty() || returnDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out any empty fields.", "Invalid Flight Information", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (valDate(departureDate) && valDateReturn(returnDate)) {
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

    /**
     *
     * Action Listener used for?
     *
     */
    private class ButtonClicked implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            System.out.println(btn.getName());
            System.out.println(e.toString());
            if (e.toString().contains("cmd=Book")) {
                System.out.println(true);
                // Using searchResult.get(i) data to Class folder to book reservation and store flight information
                // reset searchData array and bookButton array
                // if booking round-trip, then clear search list and display destination -> home flights
                // fill in searchData array and bookButton array based on api call

                // go to ReviewFrame
                // if(!roundTrip) -> dispose()
                if(true) {
                    dispose();
                    ReviewFrame review = new ReviewFrame();
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