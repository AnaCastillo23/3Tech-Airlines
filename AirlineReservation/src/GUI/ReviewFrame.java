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
    static ArrayList<String> departureSeats;
    static ArrayList<String> returnSeats;
    static SeatFrame departureSeatFrame;
    static SeatFrame returnSeatFrame;
    static double initialBasePrice;
    static double basePrice;
    static double seatChangeFees;
    static double baggageFees;
    static double tax;
    static double taxPercentage;
    static double totalPrice;
    static BaggageFrame departureBaggageFrame;
    static BaggageFrame returnBaggageFrame;
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

            departurePartyFrame = new PassengerFrame();
            departureBaggageFrame = new BaggageFrame(); // 1 free check in baggage per passenger

            flightsToReview = new FlightsToReview();
            ArrayList<Flight> displayFlights = flightsToReview.getFlightsToDisplay();

            if (displayFlights.size() > 1) {
                // round trip
                roundTrip = true;
            } else {
                roundTrip = false;
            }


            // make method generateReservationID() <---- create unique reservation number
            //int reservationID = generateReservationID();
            int reservationID = 222;// temp
            generateReservationID();

            departureFlight = displayFlights.get(0);
            departureDate = departureFlight.getDepartureDate();
            departureFlightNumber = departureFlight.getFlightID();
            departurePartySize = 1; // temp
            departureParty = copyArrayList(departurePartyFrame.getPassengerList());
            ;

            // Create two instances of SeatFrame if roundtrip - false is one way
            departureSeatFrame = new SeatFrame(false);
            departureSeatFrame.generateSeatingMap(departurePartySize, 30 + rnd.nextInt(45));
            departureSeats = departureSeatFrame.getAssignedSeats(departurePartySize);
            System.out.println("Seats " + departureSeats);

            if (roundTrip) {
                // round trip
                returnParty = new ArrayList<Passenger>();
                returnSeats = new ArrayList<String>();
                returnPartyFrame = new PassengerFrame();

                returnFlight = displayFlights.get(1);
                returnDate = returnFlight.getDepartureDate();
                returnFlightNumber = returnFlight.getFlightID();
                returnPartySize = 1; // temp
                returnParty = null; // temp

                returnSeatFrame = new SeatFrame(true);
                returnSeatFrame.generateSeatingMap(returnPartySize, 30 + rnd.nextInt(45));
                returnSeats = returnSeatFrame.getAssignedSeats(returnPartySize);
            }

            basePrice = flightsToReview.getTotalPrice();
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
                    finalSeats = new Seats(departureSeats, null);
                    departureFlight.setSeats(finalSeats);

                    Reservation reservation;
                    if (roundTrip) {
                        // return reservation
                        finalSeats = new Seats(returnSeats, null);
                        returnFlight.setSeats(finalSeats);

                        reservation = new Reservation(reservationID, basePrice, tax, totalPrice, departureDate, returnDate, departureFlightNumber, returnFlightNumber,
                                departurePartySize, returnPartySize, departureParty, returnParty);
                        reservation.setDepartureFlight(departureFlight);
                        reservation.setReturnFlight(returnFlight);

                    } else {
                        // one way trip
                        reservation = new Reservation(reservationID, basePrice, tax, totalPrice, departureDate, departureFlightNumber, departurePartySize, departureParty);
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
                }
            });

            addPassengerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double initialBasePrice = flightsToReview.getTotalPrice();
                    departurePartyFrame.setVisible(true);
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

                    //  NEEDS DIALOG BOX asking which flight TO CHANGE!!!!!!!!!
                    // DO NOT DELETE
                /*
                if(roundTrip) {
                    if (!myDialog.isVisible()) {
                        myDialog.setVisible(true);
                    }
                    myDialog.addConfirmListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String text = myDialog.getTextFieldText();
                            textField.setText(text);
                        }
                    });
                    JPanel panel = new JPanel();
                    panel.add(textField);
                    panel.add(showDialogBtn);

                    add(panel);
               }*/
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
                }
            });
            updateListButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*
                    System.out.println();
                    System.out.println("refreshDisplay test");
                    System.out.println("---------------------");
                    System.out.println("departure Seats: " + departureSeats);
                    System.out.println("base price: " + basePrice);
                    System.out.println("seat change fee: " + seatChangeFees);
                    System.out.println("baggage fee: " + baggageFees);
                    System.out.println("tax: " + tax);
                    System.out.println("tax percentage: " + taxPercentage + "%");
                    System.out.println("total price: " + totalPrice);
                    System.out.println("Party: " + departureParty.size());
                    System.out.println();*/
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
                }
            });
        }
    }


    public static void resetStaticVariables() {
        // reset static variables
        departureFlight = null;
        returnFlight = null;
        departureParty = null;
        returnParty = null;
        departureSeats = null;
        returnSeats = null;
        departureSeatFrame = null;
        returnSeatFrame = null;
        basePrice = 0;
        seatChangeFees = 0;
        baggageFees = 0;
        tax = 0;
        taxPercentage = 0;
        totalPrice = 0;
    }

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

    // IF TOTAL IS NEGATIVE THEN ADDING IT TO TOTAL WOULD WORK
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
            basePrice += initialBasePrice * newPassengerCount;
            updateTotal(basePrice, false);
        } else {
            // add later
        }

    }

    // IF TOTAL IS NEGATIVE THEN ADDING IT TO TOTAL WOULD WORK
    public static void addSeatsToReview(boolean returnTrip) {
        SeatChange seatChange;
        Seats seatChanges;

        double tempFee = 0;
        if(!returnTrip){
            // one way trip
            // seats are added to flight
            seatChange = new SeatChange();
            departureSeats = seatChange.getReservedSeats(false);
            // should change fee aswell
            tempFee = seatChange.getTotal(false);
            seatChangeFees += tempFee;
            seatChange.deleteSeatsToChange(false);

        } else {
            // TEMPFEE????
            // return trip feature will be added later
            returnSeatFrame.setVisible(true);

            // seats are added to flight
            seatChange = new SeatChange();
            returnSeats = seatChange.getReservedSeats(true);
            // should change fee aswell
            seatChangeFees += seatChange.getTotal(true);
            seatChange.deleteSeatsToChange(true);

        }
        basePrice += tempFee;
        updateTotal(basePrice, false);

        // call refresh-display method here

    }

    public static void addBaggageToReview(ArrayList<Baggage> baggageList, double baggageFee, boolean returnTrip) {
        if(!returnTrip) {
            // not taxable
            baggageFees = baggageFee;
            departureBaggageList = baggageList;
        } else {
            // add later
        }
        System.out.println("baggage fee(review): " + baggageFee);
        System.out.println("baggage fee(review): " + baggageFee);
        updateTotal(basePrice, false);
    }


    //  FOR ROUND TRIP - FIX LATER
/*
    class MyDialog extends JDialog {
        private JTextField textfield = new JTextField(10);
        private JButton confirmBtn = new JButton("Confirm");

        public MyDialog(JFrame frame, String title) {
            super(frame, title, false);
            JPanel panel = new JPanel();
            panel.add(textfield);
            panel.add(confirmBtn);

            add(panel);
            pack();
            setLocationRelativeTo(frame);
        }

        public String getTextFieldText() {
            return textfield.getText();
        }

        public void addConfirmListener(ActionListener listener) {
            confirmBtn.addActionListener(listener);
        }
    }

 */
    public int generateReservationID() {
        int reservationID;

        Random rand = new Random();
        reservationID = 100000 + rand.nextInt(899999);

        return reservationID;
    }

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
        }

        // Display Total
        numDepartTraveler.setText("x" + departurePartySize + " Traveler(s):");
        departBasePriceLabel.setText("$" + formatter.format(basePrice));
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
        }
    }

    public static ArrayList<Passenger> copyArrayList(ArrayList<Passenger> oldList) {
        ArrayList<Passenger> clonedList = new ArrayList<Passenger>(oldList.size());
        for (Passenger passenger : oldList) {
            clonedList.add(passenger);
        }
        return clonedList;
    }
}
