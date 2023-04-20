package GUI;

import Helper.BookedFlightsReview;
import Class.Reservation;
import Class.Passenger;
import Class.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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

    //BookedFlightsReview bookedFlightsReview;
    //BookedFlightsReview.BookedFlightBundle bookedFlights = new BookedFlightsReview.BookedFlightBundle();

    private boolean roundTrip;

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

        //bookedFlightsReview = new BookedFlightsReview();
        //bookedFlights = bookedFlightsReview.getBookedFlightsReview("temp");
/*
        if(bookedFlights.getBookedFlights().size() > 1) {
            roundTrip = true;
        } else {
            roundTrip = false;
        }

        // test
        System.out.println("Departing Flight: " + bookedFlights.getBookedFlights().get(0)); // test
        if(roundTrip) {
            System.out.println("Returning Flight: " + bookedFlights.getBookedFlights().get(1)); // test
        }*/

        // Use BookedFlightReview methods to display booked flight/s
            /*
            - FlightID
            - Depart and Arrival airport ICAO codes
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
        /*confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = new Account();

                // depart reservation
                // Send BookedFlightReview data to Reservation.java
                // generateReservationID() <---- ???????? create unique reservation number
                Date departureDate = bookedFlights.getDepartDates().get(0);
                String departFlightNumber = bookedFlights.getBookedFlights().get(0).getJSONObject("ident_iata").toString();
                int departurePartySize = 1; // temp
                ArrayList<Passenger> departureParty = null; // temp

                // departing flight


                if(roundTrip) {
                    // return reservation
                    Date returnDate = bookedFlights.getDepartDates().get(1);
                    String returnFlightNumber = bookedFlights.getBookedFlights().get(1).getJSONObject("ident_iata").toString();
                    int returnPartySize = 1; // temp
                    ArrayList<Passenger> returnParty = null; // temp

                    // returning flight

                    Reservation roundTripReservation = new Reservation(1111, departureDate, returnDate, departFlightNumber, returnFlightNumber, departurePartySize, returnPartySize, departureParty, returnParty);

                    //roundTripReservation.setDepartureFlight();
                    //roundTripReservation.setReturnFlight();
                } else {
                    // one way trip
                    Reservation oneWayTripReservation = new Reservation(1111, departureDate, departFlightNumber, departurePartySize, departureParty);

                    //oneWayTripReservation.setDepartureFlight(departFlightNumber, departureDate, , , , , );
                    account.addReservation(oneWayTripReservation);
                }
            }
        });
*/

        /**
         * Action listener used to code the CANCEL button of current frame if user decides to cancel
         * flight reservation and return to dashboard.
         */
        //Ana-Still not done
        //add listener for canceling booking which returns user to dashboard

    }



}
