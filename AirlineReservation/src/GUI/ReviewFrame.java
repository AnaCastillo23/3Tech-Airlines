package GUI;

import DataStructures.FlightsToReview;
import Class.Flight;
import Class.Passenger;
import Class.Reservation;
import Class.Seats;
import Class.Baggage;
import DataStructures.ReservationToCheckout;
import DataStructures.SeatChange;
import Managers.PriceGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Review GUI is used to display a desktop application
 * window which displays user's selection of flight that they wish to book in order for the user to review if
 * details about the flight are correct.
 * @since 03/27/2023
 * @author Carlos Figueroa (code and documentation comments) & Ana Emily Castillo Perez (documentation comments)
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

    private JLabel departFlightIDLabel;
    private JLabel departureAirlineOPLabel;
    private JLabel departureAirportIATA;
    private JLabel arrivalAirportIATA1;
    private JLabel departureAirportLocation;
    private JLabel arrivalAirportLocation1;
    private JLabel departureDateAndTime;
    private JLabel arrivalDateAndTime1;

    private JLabel returningFlightTitleLabel;
    private JLabel returnFlightIDLabel;
    private JLabel returnAirlineOPLabel;
    private JLabel returnAirportIATA;
    private JLabel arrivalAirportIATA2;
    private JLabel returnAirportLocation;
    private JLabel arrivalAirportLocation2;
    private JLabel returnDateAndTime;
    private JLabel arrivalDateAndTime2;

    private JLabel numDepartTraveler;
    private JLabel departBasePriceLabel;
    private JLabel numReturnTraveler;
    private JLabel returnBasePriceLabel;
    private JLabel seatChangeLabel;
    private JLabel seatChangeFeeLabel;
    private JLabel baggageLabel;
    private JLabel baggageFeeLabel;
    private JLabel taxLabel;
    private JLabel taxTotalLabel;
    private JLabel totalLabel;
    private JLabel calcTotalLabel;


    private JButton updateListButton;

    FlightsToReview flightsToReview;

    private boolean roundTrip;
    String departureDate;
    String departureFlightNumber;
    String returnDate;
    String returnFlightNumber;
    static int departurePartySize;
    static int returnPartySize;
    static ArrayList<Passenger> departureParty;
    static ArrayList<Passenger> returnParty;
    static ArrayList<Baggage> departureBaggageList;
    static ArrayList<Baggage> returnBaggageList;
    static Flight departureFlight;
    static Flight returnFlight;
    static ArrayList<String> departureClassSeats;
    static ArrayList<String> departureSeats;
    static ArrayList<String> returnSeats;
    static SeatFrame departureSeatFrame;
    static SeatFrame returnSeatFrame;
    static double initialBasePrice;
    static double basePrice;
    static double departureBasePrice;
    static double returnBasePrice;
    static double seatChangeFees;
    static double baggageFees;
    static double tax;
    static double taxPercentage;
    static double totalPrice;
    static BaggageFrame departureBaggageFrame;
    static BaggageFrame returnBaggageFrame;
    static double departureBaggageFees;
    static double returnBaggageFees;
    PassengerFrame departurePartyFrame;
    PassengerFrame returnPartyFrame;


    /**
     *
     * Method is for creating and displaying a desktop window to a specific size when user has selected a flight from the list and has clicked on NEXT button in FlightSearchFrame.
     *
     */
    public ReviewFrame(boolean emptyInstance) {
        if(!emptyInstance) {
            setContentPane(reviewPanel);
            setTitle("Flight Information");
            setSize(450, 300);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);

            resetStaticVariables(); // incase static variables from previous instance are not null

            seatChangeFees = 0;
            baggageFees = 0;
            Random rnd = new Random();

            departureParty = new ArrayList<Passenger>();
            departureSeats = new ArrayList<String>();
            departureClassSeats = new ArrayList<>();

            departureClassSeats.add("economy-class");

            departurePartyFrame = new PassengerFrame();
            departurePartyFrame.setTitle("Add Departure Passengers");

            departureBaggageFrame = new BaggageFrame(); // 1 free check in baggage per passenger
            departureBaggageFrame.setTitle("Add Departure Baggage");

            flightsToReview = new FlightsToReview();
            ArrayList<Flight> displayFlights = flightsToReview.getFlightsToDisplay();

            departureBasePrice = flightsToReview.getTotalPrice().get(0);
            if (displayFlights.size() > 1) {
                // round trip
                roundTrip = true;
                returnBasePrice = flightsToReview.getTotalPrice().get(1);
            } else {
                roundTrip = false;
            }


            // make method generateReservationID() <---- create unique reservation number
            //int reservationID = generateReservationID();
            int reservationID = generateReservationID();

            departureFlight = displayFlights.get(0);
            departureDate = departureFlight.getDepartureDate();
            departureFlightNumber = departureFlight.getFlightID();
            departurePartySize = 1; // temp
            departureParty = copyArrayList(departurePartyFrame.getPassengerList());

            // Create two instances of SeatFrame if roundtrip - false is one way
            departureSeatFrame = new SeatFrame(false);
            departureSeatFrame.setTitle("Departure Seating Map");
            departureSeatFrame.generateSeatingMap(departurePartySize, 30 + rnd.nextInt(45));
            departureSeats = departureSeatFrame.getAssignedSeats(departurePartySize);
            System.out.println("Seats " + departureSeats);

            if (roundTrip) {
                // round trip
                System.out.println("Return trip!!!!!!!");
                returnParty = new ArrayList<Passenger>();
                returnSeats = new ArrayList<String>();

                returnFlight = displayFlights.get(1);
                returnDate = returnFlight.getDepartureDate();
                returnFlightNumber = returnFlight.getFlightID();
                returnPartySize = 1; // temp

                returnPartyFrame = new PassengerFrame();
                returnPartyFrame.setTitle("Add Return Passengers");
                returnParty = copyArrayList(returnPartyFrame.getPassengerList());

                returnBaggageFrame = new BaggageFrame(); // 1 free check in baggage per passenger
                returnBaggageFrame.setTitle("Add Return Baggage");

                returnSeatFrame = new SeatFrame(true);
                returnSeatFrame.setTitle("Return Seating Map");
                returnSeatFrame.generateSeatingMap(returnPartySize, 30 + rnd.nextInt(45));
                returnSeats = returnSeatFrame.getAssignedSeats(returnPartySize);
            }

            basePrice = departureBasePrice + returnBasePrice;
            initialBasePrice = basePrice;
            System.out.println("Flight price " + basePrice);
            System.out.println("Flight price " + String.format("%.2f", basePrice));

            // Get Flight tax, added fees, and calculate total -> update to Reservation
            updateTotal(basePrice, true);

            updateReviewDisplay();

            /**
             * Action listener used to code the CONFIRM button of current frame if user decides to confirm
             * flight reservation.
             */
            //add listeners for confirming flight booking which will return a confirmation frame thanking user for booking
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Seats finalSeats;

                    // depart reservation
                    finalSeats = new Seats(departureSeats, departureClassSeats);
                    departureFlight.setSeats(finalSeats);
                    departureFlight.setBaggage(departureBaggageList);

                    Reservation reservation;
                    if (roundTrip) {
                        // return reservation
                        finalSeats = new Seats(returnSeats, null);
                        returnFlight.setSeats(finalSeats);
                        returnFlight.setBaggage(returnBaggageList);

                        reservation = new Reservation(reservationID, basePrice, seatChangeFees, baggageFees, tax, totalPrice, departureDate, returnDate, departureFlightNumber, returnFlightNumber,
                                departurePartySize, returnPartySize, departureParty, returnParty);
                        reservation.setDepartureFlight(departureFlight);
                        reservation.setReturnFlight(returnFlight);

                    } else {
                        // one way trip
                        reservation = new Reservation(reservationID, basePrice, seatChangeFees, baggageFees, tax, totalPrice, departureDate, departureFlightNumber, departurePartySize, departureParty);
                        reservation.setDepartureFlight(departureFlight);
                    }
                    // Go to checkout
                    ReservationToCheckout checkout = new ReservationToCheckout(reservation);    // reservation to checkout
                    CheckoutFrame checkoutFrame = new CheckoutFrame();

                    // clear temp hashmap
                    flightsToReview.clearFlightToReview();

                    // clears all static variables
                    resetStaticVariables();

                    setVisible(false);
                    checkoutFrame.setVisible(true);
                    dispose();
                }
            });

            addPassengerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //double initialBasePrice = flightsToReview.getTotalPrice();
                    departurePartyFrame.setVisible(true);
                    returnPartyFrame.setVisible(true);
                }
            });

            chooseSeatsButton.addActionListener(new ActionListener() {
                /**
                 * Invoked when an action occurs.
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    // return flight feature will be added later
                    departureSeatFrame.setVisible(true);
                    returnSeatFrame.setVisible(true);
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

                    // clear temp hashmap
                    flightsToReview.clearFlightToReview();

                    // clears all static variables
                    resetStaticVariables();

                    setVisible(false);
                    dashboard.setVisible(true);
                    dispose();
                }
            });
            updateListButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateReviewDisplay();
                }
            });

            baggageButton.addActionListener(new ActionListener() {
                /**
                 * Invoked when an action occurs.
                 *
                 * @param e the event to be processed
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    departureBaggageFrame.setVisible(true);
                    returnBaggageFrame.setVisible(true);
                }
            });
        }
    }


    public static void resetStaticVariables() {
        departurePartySize = 0;
        returnPartySize = 0;
        departureParty = null;
        returnParty = null;
        departureBaggageList = null;
        returnBaggageList = null;
        departureFlight = null;
        returnFlight = null;
        departureClassSeats = null;
        departureSeats = null;
        returnSeats = null;
        departureSeatFrame = null;
        returnSeatFrame = null;
        initialBasePrice = 0;
        basePrice = 0;
        seatChangeFees = 0;
        baggageFees = 0;
        tax = 0;
        taxPercentage = 0;
        totalPrice = 0;
        departureBaggageFrame = null;
        returnBaggageFrame = null;
    }

    /**
     *
     * Method for updating the grand total for its review for the current reservation.
     *
     * @param updatedBasePrice updatedBasePrice
     * @param initialPricing initialPricing
     */
    public static void updateTotal(double updatedBasePrice, boolean initialPricing) {
        //fees = calcTax.getFees(); <--- Seating and Baggage
        //totalPrice = flightPrice + fees + tax ;

        basePrice = updatedBasePrice;
        if(initialPricing) {
            PriceGenerator calcTax = new PriceGenerator();
            tax = calcTax.getTax(basePrice);
            taxPercentage = tax / basePrice;
            totalPrice = basePrice + tax;
        } else {
            tax = basePrice * taxPercentage;
            totalPrice = basePrice + tax + baggageFees;
        }
    }

    /**
     *
     * Method for adding passengers to party for its review.
     *
     * @param passengerList passengerList
     * @param newPassengerCount newPassengerCount
     * @param returnFlight returnFlight
     */
    public static void addToParty(ArrayList<Passenger> passengerList, int newPassengerCount, boolean returnFlight) {

        if(!returnFlight) {
            System.out.println();
            for(int i = 0; i < departureParty.size(); i++) {
                System.out.print(departureParty.get(i).getFirstName() + ", ");
            }
            System.out.println();
            for(int i = 0; i < passengerList.size(); i++) {
                System.out.print(passengerList.get(i).getFirstName() + ", ");
            }
            System.out.println();

            // assign each new passenger a free check-in baggage
            for(int i = 0; i < passengerList.size(); i++) {
                if(!departureParty.contains(passengerList.get(i))) {
                    Baggage newPassenger = new Baggage(passengerList.get(i).getFirstName() + " " + passengerList.get(i).getLastName(), 1);
                    departureBaggageFrame.addFreeBaggage(newPassenger);
                }
            }
            departureParty = copyArrayList(passengerList);
            departurePartySize = passengerList.size();

            // assign seats to updated passengers - no seat change charge:
            departureSeatFrame.updatePartySize(departurePartySize);

            // add original base price per new passenger to total
            // add new passenger ticket costs to updated base price and then get it taxed
            basePrice = (departureBasePrice * passengerList.size()) + (returnBasePrice * returnPartySize);

        } else {

            System.out.println();
            for(int i = 0; i < returnParty.size(); i++) {
                System.out.print(returnParty.get(i).getFirstName() + ", ");
            }
            System.out.println();
            for(int i = 0; i < passengerList.size(); i++) {
                System.out.print(passengerList.get(i).getFirstName() + ", ");
            }
            System.out.println();

            // assign each new passenger a free check-in baggage
            for(int i = 0; i < passengerList.size(); i++) {
                if(!returnParty.contains(passengerList.get(i))) {
                    Baggage newPassenger = new Baggage(passengerList.get(i).getFirstName() + " " + passengerList.get(i).getLastName(), 1);
                    returnBaggageFrame.addFreeBaggage(newPassenger);
                }
            }
            returnParty = copyArrayList(passengerList);
            returnPartySize = passengerList.size();

            // assign seats to updated passengers - no seat change charge:
            returnSeatFrame.updatePartySize(returnPartySize);

            // add original base price per new passenger to total
            // add new passenger ticket costs to updated base price and then get it taxed
            basePrice = (returnBasePrice * passengerList.size()) + (departureBasePrice * departurePartySize);
        }
        updateTotal(basePrice, false);
    }

    /**
     *
     * Method for adding the seats user has selected into the current frame for their review.
     *
     * @param returnTrip returnTrip
     */
    public static void addSeatsToReview(boolean returnTrip) {
        SeatChange seatChange;
        Seats seatChanges;

        double tempFee = 0;
        if(!returnTrip){
            // one way trip
            // seats are added to flight
            seatChange = new SeatChange();
            departureSeats = seatChange.getReservedSeats(false);
            departureClassSeats = seatChange.getClassSeats(false);
            // should change fee aswell
            tempFee = seatChange.getTotal(false);
            seatChangeFees += tempFee;
            seatChange.deleteSeatsToChange(false);

        } else {
            // round trip
            // seats are added to flight
            seatChange = new SeatChange();
            returnSeats = seatChange.getReservedSeats(true);
            //returnClassSeats = seatChange.getSeatToChange(true);
            // should change fee aswell
            tempFee = seatChange.getTotal(true);
            seatChangeFees += seatChange.getTotal(true);
            seatChange.deleteSeatsToChange(true);

        }
        basePrice += tempFee;
        updateTotal(basePrice, false);

        // call refresh-display method here

    }

    /**
     *
     * Method for adding baggage that user has added into the current frame for calculating grand total.
     *
     * @param baggageList baggageList
     * @param baggageFee baggageFee
     * @param returnTrip returnTrip
     */
    public static void addBaggageToReview(ArrayList<Baggage> baggageList, double baggageFee, boolean returnTrip) {
        // not taxable
        System.out.println();
        System.out.println("return trip == " + returnTrip);
        if(!returnTrip) {
            departureBaggageFees = baggageFee;
            departureBaggageList = baggageList;
            System.out.println(departureBaggageFees);
        } else {
            returnBaggageFees = baggageFee;
            returnBaggageList = baggageList;
            System.out.println(returnBaggageFees);
        }
        baggageFees = departureBaggageFees + returnBaggageFees;
        System.out.println(baggageFees);

        System.out.println("baggage fee(review): " + baggageFee);
        System.out.println("baggage fee(review): " + baggageFee);
        updateTotal(basePrice, false);
    }


    public int generateReservationID() {
        int reservationID;

        Random rand = new Random();
        reservationID = 100000 + rand.nextInt(899999);

        return reservationID;
    }

    /**
     *
     * Used to refresh the grand total for the reservation when user has edited something in the reservation.
     *
     */
    public void updateReviewDisplay() {
        DecimalFormat formatter = new DecimalFormat("0.00");
        // Use variables above and Flight methods to display booked flight/s
        // SHOULD MAKE THIS A METHOD SO IT CAN BE REFRESHED AFTER SEAT, PARTY, CHANGES
                /*
                - FlightID
                - Depart and Arrival airport IATO codes
                - Depart and Arrival airport location/city
                - Depart and Arrival Date and Time
                - Airline name
                -
                 */
        returningFlightTitleLabel.setVisible(false);
        // Display base price, tax, fees, total
        departFlightIDLabel.setText("Flight ID : " + departureFlightNumber);
        departureAirlineOPLabel.setText("Airline : " + departureFlight.getAirline().getAirlineID());
        departureAirportIATA.setText(departureFlight.getDepartureAirport().getAirportName() + "(" + departureFlight.getDepartureAirport().getAirportCode() + ")");
        arrivalAirportIATA1.setText( departureFlight.getArrivalAirport().getAirportName() + "(" + departureFlight.getArrivalAirport().getAirportCode() + ")");
        departureAirportLocation.setText(departureFlight.getDepartureLocation());
        arrivalAirportLocation1.setText(departureFlight.getArrivalLocation());
        departureDateAndTime.setText(departureFlight.getDepartureDate() + " " + departureFlight.getDepartureTime());
        arrivalDateAndTime1.setText(departureFlight.getArrivalDate() + " " + departureFlight.getArrivalTime());

        if(roundTrip) {
            // display returning flight details
            returningFlightTitleLabel.setVisible(true);

            returnFlightIDLabel.setText("Flight ID : " + returnFlightNumber);
            returnAirlineOPLabel.setText("Airline : " + returnFlight.getAirline().getAirlineID());
            returnAirportIATA.setText(returnFlight.getDepartureAirport().getAirportName() + "(" + returnFlight.getDepartureAirport().getAirportCode() + ")");
            arrivalAirportIATA2.setText( returnFlight.getArrivalAirport().getAirportName() + "(" + returnFlight.getArrivalAirport().getAirportCode() + ")");
            returnAirportLocation.setText(returnFlight.getDepartureLocation());
            arrivalAirportLocation2.setText(returnFlight.getArrivalLocation());
            returnDateAndTime.setText(returnFlight.getDepartureDate() + " " + returnFlight.getDepartureTime());
            arrivalDateAndTime2.setText(returnFlight.getArrivalDate() + " " + returnFlight.getArrivalTime());
        }

        // Display Total
        numDepartTraveler.setText("Departure: x" + departurePartySize + " Traveler(s):");
        departBasePriceLabel.setText("$" + formatter.format(departureBasePrice) + " per Traveler");
        seatChangeLabel.setText("Seat Change Fees:");
        seatChangeFeeLabel.setText("$" + formatter.format(seatChangeFees));
        baggageLabel.setText("Baggage Fees:");
        baggageFeeLabel.setText("$" + formatter.format(baggageFees));
        taxLabel.setText("Tax:");
        taxTotalLabel.setText("$" + formatter.format(tax));
        totalLabel.setText("Total:");
        calcTotalLabel.setText("$" + formatter.format(totalPrice));

        if(!roundTrip) {
            numReturnTraveler.setVisible(false);
            returnBasePriceLabel.setVisible(false);
        } else {
            // set return price
            numReturnTraveler.setVisible(true);
            returnBasePriceLabel.setVisible(true);

            numReturnTraveler.setText("Return: x" + returnPartySize + " Traveler(s):");
            returnBasePriceLabel.setText("$" + formatter.format(returnBasePrice) + " per Traveler");
        }
    }

    /**
     *
     * Used to clone the list of passengers.
     *
     * @param oldList oldList
     * @return clonedList
     */
    public static ArrayList<Passenger> copyArrayList(ArrayList<Passenger> oldList) {
        ArrayList<Passenger> clonedList = new ArrayList<Passenger>(oldList.size());
        for (Passenger passenger : oldList) {
            clonedList.add(passenger);
        }
        return clonedList;
    }
}
