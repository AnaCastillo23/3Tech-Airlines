package GUI;

import Helper.AccountAccessor;
import Helper.FlightsToReview;
import Class.Flight;
import Class.Account;
import Class.Passenger;
import Class.Reservation;
import Class.Airport;
import Class.Airline;

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

    FlightsToReview flightsToReview;

    private boolean roundTrip;
    Flight departureFlight;
    String departureDate;
    String departureFlightNumber;
    int departurePartySize;
    ArrayList<Passenger> departureParty;

    Flight returnFlight;
    String returnDate;
    String returnFlightNumber;
    int returnPartySize;
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

        flightsToReview = new FlightsToReview();
        ArrayList<Flight> displayFlights = flightsToReview.getFlightsToDisplay("temp");

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
                // Add Reservation to Account
                //updatedAccount.setReservation(reservation);
                //updatedAccount.addReservationToAccount(reservation); <-- uncomm

                // Tester:
                //testReservation(updatedAccount);; <-- uncomm
                System.out.println();

                // update Reservation in logged in account
                AccountAccessor accountAccessor = new AccountAccessor();
                String loginUsername = accountAccessor.getLoginUsername();
                updatedAccount = tempAccount.getLoginAccount().get(loginUsername);  // updatedAccount will have login account info
                updatedAccount.addReservationToAccount(reservation);    // update login account with new flight reservation
                updatedAccount.registerOrUpdate(updatedAccount);        // update hashmap

                // Go To Payment
                
            }
        });

        /*
        Account tempAccount1 = tempAccount.getLoginAccount().get(loginUsername);
                System.out.println(updatedAccount.getUsername());
                System.out.println(updatedAccount.getPassword());
                System.out.println(updatedAccount.getFirstName());
                System.out.println(updatedAccount.getLastName());
                System.out.println(updatedAccount.getAddress());
                System.out.println(updatedAccount.getEmailAddress());
                System.out.println(updatedAccount.getPhoneNumber());
                testReservation(updatedAccount);
                System.out.println();
         */





        /**
         * Action listener used to code the CANCEL button of current frame if user decides to cancel
         * flight reservation and return to dashboard.
         */
        //Ana-Still not done
        //add listener for canceling booking which returns user to dashboard

    }



    public void testReservation(Account account) {
        // TEST
        Reservation reservation1 = account.getReservationList().get(0);
        //Reservation reservation1 = account.getReservation();
        Flight departFlight1 = reservation1.getDepartureFlight();
        Airport departureAirport1 = departFlight1.getDepartureAirport();
        Airport arrivalAirport1 = departFlight1.getArrivalAirport();
        Airline airline1 = departFlight1.getAirline();

        System.out.println("Reservation Info:");
        System.out.println("\tReservation ID: " + reservation1.getReservationID());
        System.out.println("\tRound-Trip? : " + reservation1.isRoundTrip());
        System.out.println("\tDeparture Flight Number: " + reservation1.getDepartureFlightNumber());
        System.out.println("\tReturn Flight Number: " + reservation1.getReturnFlightNumber());
        System.out.println("\tDeparture Date: " + reservation1.getDepartureDate());
        System.out.println("\tReturn Date: " + reservation1.getReturnDate());
        System.out.println("\tDeparture Party: " + reservation1.getDepartureParty());
        System.out.println("\tDeparture Party Size: " + reservation1.getDeparturePartySize());
        System.out.println("\tReturn Party: " + reservation1.getReturnParty());
        System.out.println("\tReturn Party Size: " + reservation1.getReturnPartySize());

        System.out.println("Departure Test:");
        System.out.println("\tDeparture Flight Info:");
        System.out.println("\t\tDeparture Flight ID: " + departFlight1.getFlightID());
        System.out.println("\t\tDeparture Date: " + departFlight1.getDepartureDate());
        System.out.println("\t\tDeparture Time: " + departFlight1.getDepartureTime());
        System.out.println("\t\tDeparture City: " + departFlight1.getDepartureLocation());
        System.out.println("\t\tArrival Date: " + departFlight1.getArrivalDate());
        System.out.println("\t\tArrival Time: " + departFlight1.getArrivalTime());
        System.out.println("\t\tArrival City: " + departFlight1.getArrivalLocation());

        System.out.println("\tDeparture Airport Info:");
        System.out.println("\t\tAirport Code: " + departureAirport1.getAirportCode());
        System.out.println("\t\tAirport Name: " + departureAirport1.getAirportName());

        System.out.println("\tArrival Airport Info:");
        System.out.println("\t\tAirport Code: " + arrivalAirport1.getAirportCode());
        System.out.println("\t\tAirport Name: " + arrivalAirport1.getAirportName());

        System.out.println("\tAirline Info:");
        System.out.println("\t\tAirline Code: " + airline1.getAirlineID());
        System.out.println("\t\tAirline Name: " + airline1.getAirlineName());

        if (roundTrip) {
            Flight departFlight2 = reservation1.getReturnFlight();
            Airport departureAirport2 = departFlight2.getDepartureAirport();
            Airport arrivalAirport2 = departFlight2.getArrivalAirport();
            Airline airline2 = departFlight2.getAirline();

            System.out.println("Return Test:");

            System.out.println("\tReturn Flight Info:");
            System.out.println("\t\tReturn Flight ID: " + departFlight2.getFlightID());
            System.out.println("\t\tDeparture Date: " + departFlight2.getDepartureDate());
            System.out.println("\t\tDeparture Time: " + departFlight2.getDepartureTime());
            System.out.println("\t\tDeparture City: " + departFlight2.getDepartureLocation());
            System.out.println("\t\tArrival Date: " + departFlight2.getArrivalDate());
            System.out.println("\t\tArrival Time: " + departFlight2.getArrivalTime());
            System.out.println("\t\tArrival City: " + departFlight2.getArrivalLocation());

            System.out.println("\tDeparture Airport Info:");
            System.out.println("\t\tAirport Code: " + departureAirport2.getAirportCode());
            System.out.println("\t\tAirport Name: " + departureAirport2.getAirportName());

            System.out.println("\tArrival Airport Info:");
            System.out.println("\t\tAirport Code: " + arrivalAirport2.getAirportCode());
            System.out.println("\t\tAirport Name: " + arrivalAirport2.getAirportName());

            System.out.println("\tAirline Info:");
            System.out.println("\t\tAirline Code: " + airline2.getAirlineID());
            System.out.println("\t\tAirline Name: " + airline2.getAirlineName());
        }
    }
}
