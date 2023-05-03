package GUI;

import DataStructures.FlightsToReview;
import Class.Flight;
import Class.Passenger;
import Class.Reservation;
import Class.Airport;
import DataStructures.ReservationToCheckout;
import Managers.PriceGenerator;

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
    private JButton confirmButton;
    private JButton chooseSeatsButton;
    private JButton baggageButton;
    private JButton cancelButton;
    private JButton addPassengerButton;
    private JLabel Depart;
    private JLabel Arrival;

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
    SeatFrame departureSeatFrame;
    SeatFrame returnSeatFrame;


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
        setVisible(true);

        departureParty = new ArrayList<Passenger>();
        returnParty = new ArrayList<Passenger>();

        flightsToReview = new FlightsToReview();
        ArrayList<Flight> displayFlights = flightsToReview.getFlightsToDisplay();

        // generate seating (2D Array) for plane then call SeatsFrame when user clicks Seats


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


        // Create two instances of SeatFrame if roundtrip - false is one way
        departureSeatFrame = new SeatFrame(false);
        // numRows should be random
        departureSeatFrame.generateSeatingMap(1, 40);


        // Use variables above and Flight methods to display booked flight/s
            /*
            - FlightID
            - Depart and Arrival airport IATO codes
            - Depart and Arrival airport location/city
            - Depart and Arrival Date and Time
            - Airline name
            -
             */
        //Ana-Still not done


        Airport departureAirport = departureFlight.getDepartureAirport();
        double flightPrice = flightsToReview.getTotalPrice();
        System.out.println("Flight price " + flightPrice);
        System.out.println("Flight price " + String.format("%.2f", flightPrice));

        // Get Flight tax, added fees, and calculate total -> update to Reservation
        PriceGenerator calcTax = new PriceGenerator();
        double tax = calcTax.getTax(flightPrice);
        //fees = calcTax.getFees(); <--- Seating and Baggage
        //totalPrice = flightPrice + fees + tax ;
        double totalPrice = flightPrice + tax;



        /**
         * Action listener used to code the CONFIRM button of current frame if user decides to confirm
         * flight reservation.
         */
        chooseSeatsButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // dialog box asking which flight if roundtrip

                departureSeatFrame.setVisible(true);

                // seats are added to flight in SeatFrame()

            }
        });

        //add listeners for confirming flight booking which will return a confirmation frame thanking user for booking
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // depart reservation
                Reservation reservation;
                if(roundTrip) {
                    // return reservation
                    reservation = new Reservation(reservationID, flightPrice, tax, totalPrice, departureDate, returnDate, departureFlightNumber, returnFlightNumber,
                            departurePartySize, returnPartySize, departureParty, returnParty);
                    reservation.setDepartureFlight(departureFlight);
                    reservation.setReturnFlight(returnFlight);
                } else {
                    // one way trip
                    reservation = new Reservation(reservationID, flightPrice, tax, totalPrice, departureDate, departureFlightNumber, departurePartySize, departureParty);
                    reservation.setDepartureFlight(departureFlight);
                }
                // Go to checkout

                ReservationToCheckout checkout = new ReservationToCheckout(reservation);    // reservation to checkout
                CheckoutFrame checkoutFrame = new CheckoutFrame();

                flightsToReview.clearFlightToReview();     // clear temp data structure
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
