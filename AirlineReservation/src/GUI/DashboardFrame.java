package GUI;

import Class.Account;
import Class.Reservation;
import Class.Flight;
import Class.Airport;
import Class.Airline;
import DataStructures.AccountAccessor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {
    private JPanel dashboardPanel;
    private JButton bookFlightButton;
    private JButton manageReservationButton;
    private JButton accountButton;
    private JButton logOutButton;

    public DashboardFrame() {
        setContentPane(dashboardPanel);
        setTitle("3-Tech Dashboard");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        bookFlightButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used for advancing to the flight search frame after book button is clicked.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FlightSearchFrame flightSearchFrame = new FlightSearchFrame();

                setVisible(false);
                flightSearchFrame.setVisible(true);
            }
        });

        manageReservationButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used for advancing to the user's recent reservations when manage reservation button is clicked.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Account account = new Account();

                // TEST
                AccountAccessor accountAccessor = new AccountAccessor();
                String loginUsername = accountAccessor.getLoginUsername();
                account = account.getLoginAccount().get(loginUsername);  // updatedAccount will have login account info
                testReservation(account);

                ManageReservationFrame manageReservationFrame = new ManageReservationFrame();
                setVisible(false);
                manageReservationFrame.setVisible(true);
            }
        });

        accountButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used for advancing user to their account when account button is clicked.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountFrame accountFrame = new AccountFrame();
                setVisible(false);
                accountFrame.setVisible(true);
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used for logging user out when button is clicked.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountAccessor accountAccessor = new AccountAccessor();
                LoginFrame loginFrame = new LoginFrame();

                accountAccessor.logoutAccount();
                setVisible(false);
                loginFrame.setVisible(true);
            }
        });
    }


    /**
     *
     * Method for retrieving a user's reservations and its details. Else, if user has no reservations an error message is printed.
     *
     * @param account account
     *
     */
    public void testReservation(Account account) {
        if(account.getReservationList().size() > 0) {
            // TEST
            for(int i = 0; i < account.getReservationList().size(); i++) {
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
                try {
                    System.out.println(account.getPayment().getCardHolderName());
                    System.out.println(account.getPayment().getCardNumber());
                    System.out.println(account.getPayment().getExpirationDate());
                    System.out.println(account.getPayment().getAddress());
                    System.out.println(account.getPayment().getCity());
                    System.out.println(account.getPayment().getState());
                    System.out.println(account.getPayment().getCountry());
                    System.out.println(account.getPayment().getZipCode());
                    System.out.println();
                } catch(Exception e) {
                    System.out.println(e.toString());
                }

                System.out.println("------------------------------------------------------------------------");
                System.out.println("-------------------------------Itinerary----------------------------------");
                System.out.println();
                System.out.println("Reservation Info:");
                System.out.println("\tReservation ID: " + reservation1.getReservationID());
                System.out.println("\tReservation Base Price: " + "$" + String.format("%.2f", reservation1.getFlightPrice()));
                System.out.println("\tReservation Seat Fee: " + "$" + String.format("%.2f", reservation1.getFlightSeatFee()));
                System.out.println("\tReservation Baggage Fee: " + "$" + String.format("%.2f", reservation1.getFlightBaggageFee()));
                System.out.println("\tReservation Tax Price: " + "$" + String.format("%.2f", reservation1.getFlightTax()));
                System.out.println("\tReservation Total Price: " + "$" + String.format("%.2f", reservation1.getFlightTotal()));
                System.out.println("\tRound-Trip? : " + reservation1.isRoundTrip());
                System.out.println("\tDeparture Flight Number: " + reservation1.getDepartureFlightNumber());
                System.out.println("\tReturn Flight Number: " + reservation1.getReturnFlightNumber());
                System.out.println("\tDeparture Date: " + reservation1.getDepartureDate());
                System.out.println("\tReturn Date: " + reservation1.getReturnDate());
                System.out.println("\tDeparture Party: " + reservation1.getDepartureParty());
                System.out.println("\tDeparture Party Size: " + reservation1.getDeparturePartySize());
                for(int k = 0; k < reservation1.getReturnParty().size(); k++) {
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

                System.out.println("\tDeparture Flight Baggage:");
                System.out.println("\t\tBaggage Name: " + departFlight1.getBaggage().get(0).getPassengerName());
                System.out.println("\t\tBaggage Amount: " + departFlight1.getBaggage().get(0).getNumBags());

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

                    System.out.println("\tReturn Flight Seats:");
                    System.out.println("\t\tSeat Number(s): " + departFlight2.getSeats().getReservedSeatNumbers());
                    System.out.println("\t\tSeat Class: " + departFlight2.getSeats().getSeatClasses());

                    try {
                        System.out.println("\tDeparture Flight Baggage:");
                        System.out.println("\t\tBaggage Name: " + departFlight2.getBaggage().get(0).getPassengerName());
                        System.out.println("\t\tBaggage Amount: " + departFlight2.getBaggage().get(0).getNumBags());
                    } catch(Exception e) {
                        System.out.println(e.toString());
                    }

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
        } else {
            System.out.println("Account has no reservations");
        }
    }

    /**
     *
     * Main program.
     *
     * @param args Unused.
     *
     */
    public static void main(String[] args) {
        DashboardFrame dashboardFrame = new DashboardFrame();
    }
}
