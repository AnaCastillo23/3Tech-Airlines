package Class;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

//Data init
public class Reservation {

    // helper class
    // one flight per destination - one way: one Flight class
    //                            - round trip: two Flight classes
    private Flight departureFlight;
    private Flight returnFlight;

    private int reservationID;
    private Date departureDate;
    private Date returnDate;
    private boolean roundTrip;
    private String departFlightNumber;
    private String returnFlightNumber;
    private int partySize;
    public static ArrayList<Passenger> party = new ArrayList<>();
    //private int seatNumber; how to represent reserved seat?


    public Reservation() {
        this.reservationID = 0;
        this.departureDate = null;
        this.returnDate = null;
        this.roundTrip = false;
        this.departFlightNumber = null;
        this.returnFlightNumber = null;
        this.partySize = 0;
    }

    // one way trip
    public Reservation(int reservationID, Date departureDate, String departFlightNumber, String returnFlightNumber, int partySize, ArrayList<Passenger> party) {
        this.reservationID = reservationID;
        this.departureDate = departureDate;
        this.returnDate = null;
        this.roundTrip = false;
        this.departFlightNumber = departFlightNumber;
        this.returnFlightNumber = null;
        this.partySize = partySize;
        this.party = party;
    }

    // round trip
    public Reservation(int reservationID, Date departureDate, Date returnDate, String departFlightNumber, String returnFlightNumber, int partySize, ArrayList<Passenger> party) {
        this.reservationID = reservationID;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.roundTrip = true;
        this.departFlightNumber = departFlightNumber;
        this.returnFlightNumber = returnFlightNumber;
        this.partySize = partySize;
        this.party = party;
    }

    //Getters
    public int getReservationID() {
        return this.reservationID;
    }

    public Date getDepartureDate() {
        return this.departureDate;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public boolean getFlightType() {
        return this.roundTrip;
    }

    public String getDepartFlightNumber() {
        return this.departFlightNumber;
    }

    public String getReturnFlightNumber() {
        return this.returnFlightNumber;
    }

    public int getPartySize() {
        return this.partySize;
    }

    public ArrayList<Passenger> getParty() {//work in this
        return this.getParty();
    }


    //Setters
    public void setReservationID (int reservationID) {
        this.reservationID = reservationID;
    }

    public void setDepartureDate (Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setReturnDate (Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setFlightType (boolean roundTrip) {
        this.roundTrip = roundTrip;
    }

    public void setDepartFlightNumber (String departFlightNumber) {
        this.departFlightNumber = departFlightNumber;
    }

    public void setReturnFlightNumber (String returnFlightNumber) {
        this.returnFlightNumber = returnFlightNumber;
    }

    public void setPartySize (int partySize) {
        this.partySize = partySize;
    }

    public void setParty (ArrayList party) { //work in this
        this.party = party;
    }

    public void setDepartureFlight(String flightID, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime, String departureLocation, String arrivalLocation) {
        departureFlight = new Flight(flightID, departureDate, arrivalDate, departureTime, arrivalTime, departureLocation, arrivalLocation);
    }

    // roundTrip
    public void setReturnFlight(String flightID, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime, String departureLocation, String arrivalLocation) {
        returnFlight = new Flight(flightID, departureDate, arrivalDate, departureTime, arrivalTime, departureLocation, arrivalLocation);
    }

    // NOT SURE IF THIS IS NEEDED
    /*
    public Flight getDepartureFlight() {
        return getDepartureFlight();
    }

    public Flight getReturnFlight() {
        return getReturnFlight();
    }*/
}
