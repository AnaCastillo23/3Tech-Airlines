package GUI;

import DataStructures.AccountAccessor;
import DataStructures.ReservationToCheckout;

import Class.Account;
import Class.Reservation;
import Class.Flight;
import Class.Airline;
import Class.Airport;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutFrame extends JFrame {
    private JPanel checkoutFrame;
    private JPanel displayPanel;
    private JPanel paymentPanel;
    private JButton cancelButton;
    private JButton completeBookingButton;
    private JTextPane warningTextField;
    private JPanel displayTotal;

    public CheckoutFrame() {
        setContentPane(checkoutFrame);
        setTitle("Secure Checkout");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        completeBookingButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Account updatedAccount = new Account();
                ReservationToCheckout reservationToCheckout = new ReservationToCheckout();
                Reservation reservation = new Reservation();

                reservation = reservationToCheckout.getReservationToPayment();

                // Add Reservation to Account
                // update Reservation in logged-in account
                AccountAccessor accountAccessor = new AccountAccessor();
                String loginUsername = accountAccessor.getLoginUsername();
                updatedAccount = updatedAccount.getLoginAccount().get(loginUsername);  // updatedAccount will have login account info
                updatedAccount.addReservationToAccount(reservation);    // update login account with new flight reservation
                updatedAccount.registerOrUpdate(updatedAccount);        // update hashmap

                reservationToCheckout.deleteCheckout();

                //test
                testReservation(updatedAccount);

                DashboardFrame dashboardFrame = new DashboardFrame();
                setVisible(false);
                dashboardFrame.setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public void testReservation(Account account) {
        // TEST
        // get last reservation in list
        int i = account.getReservationList().size() - 1;

        Reservation reservation1 = account.getReservationList().get(i);
        //Reservation reservation1 = account.getReservation();
        Flight departFlight1 = reservation1.getDepartureFlight();
        Airport departureAirport1 = departFlight1.getDepartureAirport();
        Airport arrivalAirport1 = departFlight1.getArrivalAirport();
        Airline airline1 = departFlight1.getAirline();

        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
        System.out.println(account.getFirstName());
        System.out.println(account.getLastName());
        System.out.println(account.getAddress());
        System.out.println(account.getEmailAddress());
        System.out.println(account.getPhoneNumber());
        System.out.println();

        System.out.println("------------------------------------------------------------------------");
        System.out.println("-------------------------------Receipt----------------------------------");
        System.out.println();
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

        if (account.getReservationList().get(i).isRoundTrip()) {

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
        System.out.println();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println();
    }

}