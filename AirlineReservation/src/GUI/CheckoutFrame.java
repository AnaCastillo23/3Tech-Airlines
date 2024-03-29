package GUI;

import DataStructures.AccountAccessor;
import DataStructures.ReservationToCheckout;

import Class.Account;
import Class.Reservation;
import Class.Flight;
import Class.Airline;
import Class.Airport;
import Class.Payment;

import Database.InData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CheckoutFrame extends JFrame {
    private JPanel checkoutFrame;
    private JPanel displayPanel;
    private JPanel paymentPanel;
    private JButton cancelButton;
    private JButton completeBookingButton;
    private JTextPane warningTextField;
    private JPanel displayTotal;
    private JTextField tfCardHolderName;
    private JComboBox jcbMonth;
    private JComboBox jcbYear;
    private JTextField tfCardNumber;
    private JTextField tfCountry;
    private JTextField tfBillingAddress;
    private JTextField tfCity;
    private JComboBox jcbState;
    private JTextField tfZipCode;
    private JButton savePaymentButton;
    private JButton cancelPaymentButton;
    private JComboBox dropDownYear;
    private JCheckBox saveToAccount;
    double flightPrice;
    double tax;
    double totalPrice;
    Account updatedAccount;
    ReservationToCheckout reservationToCheckout;
    Reservation reservation;

    /**
     * The CheckoutFrame is used in conjunction with information recollected from
     * our system and that is stored in our database. Used to allow user to pay for reservation.
     * @since 03/27/2023
     * @author Carlos Figueroa (developed code, added documentation comments) and Ana Emily Castillo Perez (added documentation comments).
     * <p>
     * <b>Explanation of important functions:</b> Frame allows user to enter their credit card info and reserve.
     * <p>
     * <b>Important data structure in class/important methods in class:</b>
     * <ul>
     * <li><i>CheckoutFrame():</i> used to either adding a new reservation to an user's account or canceling the booking process.</li>
     * </ul>
     * <p>
     *
     * <b>Any algorithms used?</b> Not at the moment.
     *
     */

    public CheckoutFrame() {
        setContentPane(checkoutFrame);
        setTitle("Secure Checkout");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        updatedAccount = new Account();
        reservationToCheckout = new ReservationToCheckout();
        reservation = reservationToCheckout.getReservationToPayment();


        // Display Tax, Fees, and Total
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("---------------------------------Receipt----------------------------------");
        System.out.println();
        System.out.println(reservation.getDeparturePartySize() + " Traveler(s) : $" + String.format("%.2f", reservation.getFlightPrice()));
        System.out.println("Taxes : $" + String.format("%.2f", reservation.getFlightTax()));
        System.out.println("-------------------------------------------------");
        System.out.println("Trip Total : $" + String.format("%.2f", reservation.getFlightTotal()));
        System.out.println();
        System.out.println("--------------------------------------------------------------------------");


        completeBookingButton.addActionListener(new ActionListener() {
            /**
             *
             * Invoked when an action occurs. When booking is confirmed, this is used to add
             * the new reservation to the user's account.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validatePayment()) {
                    // Add Reservation to Account
                    // update Reservation in logged-in account
                    AccountAccessor accountAccessor = new AccountAccessor();
                    String loginUsername = accountAccessor.getLoginUsername();
                    updatedAccount = updatedAccount.getLoginAccount().get(loginUsername);  // updatedAccount will have login account info
                    updatedAccount.addReservationToAccount(reservation);    // update login account with new flight reservation
                    updatedAccount.registerOrUpdate(updatedAccount);        // update hashmap

                    // store/update account into database here
                    InData inData = new InData();
                    try {
                        inData.updateDatabaseAccounts();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                    reservationToCheckout.deleteCheckout();

                    //test
                    //testReservation(updatedAccount);

                    DashboardFrame dashboardFrame = new DashboardFrame();
                    setVisible(false);
                    dashboardFrame.setVisible(true);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used to cancel the reservation and returning to dashboard.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                reservationToCheckout.deleteCheckout();

                DashboardFrame dashboardFrame = new DashboardFrame();
                setVisible(false);
                dashboardFrame.setVisible(true);
            }
        });
    }

    //Unused method
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
        System.out.println("-------------------------------Itinerary----------------------------------");
        System.out.println();
        System.out.println("Reservation Info:");
        System.out.println("\tReservation ID: " + reservation1.getReservationID());
        System.out.println("\tReservation Base Price: " + "$" + String.format("%.2f", reservation1.getFlightPrice()));
        System.out.println("\tReservation Tax Price: " + "$" + String.format("%.2f", reservation1.getFlightTax()));
        System.out.println("\tReservation Total Price: " + "$" + String.format("%.2f", reservation1.getFlightTotal()));
        System.out.println("\tRound-Trip? : " + reservation1.isRoundTrip());
        System.out.println("\tDeparture Flight Number: " + reservation1.getDepartureFlightNumber());
        System.out.println("\tReturn Flight Number: " + reservation1.getReturnFlightNumber());
        System.out.println("\tDeparture Date: " + reservation1.getDepartureDate());
        System.out.println("\tReturn Date: " + reservation1.getReturnDate());
        for(int k = 0; k < reservation1.getDeparturePartySize(); k++) {
            System.out.println("\tDeparture Party: " + reservation1.getDepartureParty().get(k).getFirstName());
        }
        System.out.println("\tDeparture Party Size: " + reservation1.getDeparturePartySize());
        for(int k = 0; k < reservation1.getReturnPartySize(); k++) {
            System.out.println("\tReturn Party: " + reservation1.getReturnParty().get(k).getFirstName());
        }
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

        System.out.println("\tDeparture Flight Seats:");
        System.out.println("\t\tSeat Number(s): " + departFlight1.getSeats().getReservedSeatNumbers());
        System.out.println("\t\tSeat Class: " + departFlight1.getSeats().getSeatClasses());

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


    /**
     * Validates the payment information provided by the user.
     * Checks if all sections of the form have been filled out and displays an error message if any information is missing.
     * If the payment is valid and the user has selected to save it to their account, the payment information is saved in the account.
     *
     * @return true if the payment is valid and saved successfully (or not saved), false otherwise.
     */
    public boolean validatePayment() {
        String cardHolderName = tfCardHolderName.getText();
        String expirationMonth = jcbMonth.getSelectedItem().toString();
        String expirationYear = jcbYear.getSelectedItem().toString();
        String expirationDate = expirationMonth + "-" + expirationYear;
        String cardNumber = tfCardNumber.getText();
        String country = tfCountry.getText();
        String address = tfBillingAddress.getText();
        String city = tfCity.getText();
        String state = jcbState.getSelectedItem().toString();
        String zipCode = tfZipCode.getText();

        if (cardHolderName.isEmpty() || expirationMonth.equals("Month") || expirationYear.equals("Year") || cardNumber.isEmpty() ||
                country.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || zipCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill out all sections of the form.", "Invalid Payment Method", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if(saveToAccount.isSelected()) {
                Payment payment = new Payment(cardHolderName, expirationDate, cardNumber, country, address, city, state, zipCode);

                Account account = new Account();
                AccountAccessor accountAccessor = new AccountAccessor();
                String loginUsername = accountAccessor.getLoginUsername();
                account = account.getLoginAccount().get(loginUsername);
                account.setPayment(payment);
                account.registerOrUpdate(account);
            }

            return true;
        }
    }
}
