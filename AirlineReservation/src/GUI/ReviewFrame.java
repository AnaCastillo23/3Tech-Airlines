package GUI;

import Helper.BookedFlightsReview;
import org.json.JSONObject;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The Review GUI is used to display a desktop application
 * window which displays user's selection of flight that they wish to book in order for the user to review if
 * details about the flight are correct.
 * @since 03/27/2023
 * @author Ana Emily Castillo Perez
 * <p>
 * <b>Explanation of important functions:</b> Displays user's choice of flight for its review. Displays CONFIRM button (to finish booking)
 * and CANCEL button (to return users to their dashboard).
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>ReviewFrame(): </i> used for displaying a Java Swing window where flight details are displayed for passenger's review.</li>
 *</ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */

public class ReviewFrame extends JFrame {

    private JPanel reviewPanel;
    private JTextArea welcomeToNextPaneTextArea;

    BookedFlightsReview bookedFlightsReview;
    BookedFlightsReview.BookedFlightBundle bookedFlights = new BookedFlightsReview.BookedFlightBundle();

    /**
     *
     * Method is for creating and displaying a desktop window to a specific size when user has selected a flight from the list and has clicked on NEXT button in FlightSearchFrame.
     *
     */
    public ReviewFrame() {
        setContentPane(reviewPanel);
        setTitle("Flight Information");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        bookedFlightsReview = new BookedFlightsReview();
        bookedFlights = bookedFlightsReview.getBookedFlightsReview("temp");

        System.out.println("Departing Flight: " + bookedFlights.getBookedFlights().get(0)); // test
        if(bookedFlights.getBookedFlights().size() > 0) {
            System.out.println("Returning Flight: " + bookedFlights.getBookedFlights().get(1)); // test
        }
    }



    /**
     * Action listener used to code the CONFIRM button of current frame if user decides to confirm
     * flight reservation.
     */
    //Ana-Still not done
    //add listeners for confirming flight booking which will return a confirmation frame thanking user for booking

    /**
     * Action listener used to code the CANCEL button of current frame if user decides to cancel
     * flight reservation and return to dashboard.
     */
    //Ana-Still not done
    //add listener for canceling booking which returns user to dashboard
}
