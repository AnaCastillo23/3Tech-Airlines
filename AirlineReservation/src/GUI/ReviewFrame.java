package GUI;

import DataStructures.FlightsToReview;
import Class.Flight;
import Class.Account;
import Class.Passenger;
import Class.Reservation;
import Class.Airport;
import Class.Airline;
import DataStructures.ReservationToCheckout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton confirmButton;
    private JButton chooseSeatsButton;
    private JButton baggageButton;
    private JButton cancelButton;
    private JButton button1;

    FlightsToReview flightsToReview;

    private boolean roundTrip;
    Flight departureFlight;
    String departureDate;
    String departureFlightNumber;
    int departurePartySize;

    Flight returnFlight;
    String returnDate;
    String returnFlightNumber;
    int returnPartySize;
    ArrayList<Passenger> departureParty;
    ArrayList<Passenger> returnParty;


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

        departureParty = new ArrayList<Passenger>();
        returnParty = new ArrayList<Passenger>();

        flightsToReview = new FlightsToReview();
        ArrayList<Flight> displayFlights = flightsToReview.getFlightsToDisplay();

        // make method generateReservationID() <---- create unique reservation number
        //int reservationID = generateReservationID();
        int reservationID = 222;// temp

        departureFlight = displayFlights.get(0);
        departureDate = departureFlight.getDepartureDate();
        departureFlightNumber = departureFlight.getFlightID();
        departurePartySize = 1; // temp
        departureParty = null;

        if(displayFlights.size() > 1) {
            // round trip
            roundTrip = true;

            returnFlight = displayFlights.get(1);
            returnDate = returnFlight.getDepartureDate();
            returnFlightNumber = returnFlight.getFlightID();
            returnPartySize = 1; // temp
            returnParty = null; // temp

        } else {
            // one-way trip
            roundTrip = false;
        }

        // Use variables above and Flight methods to display booked flight/s
            /*
            - FlightID
            - Depart and Arrival airport IATO codes
            - Depart and Arrival airport location/city
            - Depart and Arrival Date and Time
            - Airline name
            -
             */





        /**
         * Action listener used to code the CONFIRM button of current frame if user decides to confirm
         * flight reservation.
         */
        //Ana-Still not done
        //add listeners for confirming flight booking which will return a confirmation frame thanking user for booking
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account tempAccount = new Account();
                Account updatedAccount = new Account();

                // depart reservation
                Reservation reservation;
                if(roundTrip) {
                    // return reservation
                    reservation = new Reservation(reservationID, departureDate, returnDate, departureFlightNumber, returnFlightNumber,
                            departurePartySize, returnPartySize, departureParty, returnParty);
                    reservation.setDepartureFlight(departureFlight);
                    reservation.setReturnFlight(returnFlight);
                } else {
                    // one way trip
                    reservation = new Reservation(reservationID, departureDate, departureFlightNumber, departurePartySize, departureParty);
                    reservation.setDepartureFlight(departureFlight);
                }
                // Go to checkout

                CheckoutFrame checkoutFrame = new CheckoutFrame();
                ReservationToCheckout checkout = new ReservationToCheckout(reservation);    // reservation to checkout

                flightsToReview.getFlightsToDisplay().clear();     // clear temp data structure
                setVisible(false);
                checkoutFrame.setVisible(true);
            }
        });

        /**
         * Action listener used to code the CANCEL button of current frame if user decides to cancel
         * flight reservation and return to dashboard.
         */
        //Ana-Still not done
        //add listener for canceling booking which returns user to dashboard
        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardFrame dashboard = new DashboardFrame();

                flightsToReview.getFlightsToDisplay().clear();
                setVisible(false);
                dashboard.setVisible(true);
            }
        });
    }

}
