package Class;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Data init
public class Reservation {

    // helper class
    // one flight per destination - one way: one Flight class
    //                            - round trip: two Flight classes
    protected Flight departureFlight;
    protected Flight arrivalFlight;

    private int reservationID;
    private Date departureDate;//is this correct?
    private Date returnDate;//is this correct?
    private boolean roundTrip;
    private String departFlightNumber;
    private String returnFlightNumber;
    private int partySize;
    ArrayList party = new ArrayList(); //correct?
    //private int seatNumber; how to represent reserved seat?


    public Reservation(int reservationID, Date departureDate, Date returnDate, boolean roundTrip, String departFlightNumber, String returnFlightNumber, int partySize) {
        this.reservationID = reservationID;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.roundTrip = roundTrip;
        this.departFlightNumber = departFlightNumber;
        this.returnFlightNumber = returnFlightNumber;
        this.partySize = partySize;
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

    public ArrayList getParty() {//work in this
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

    /* ignore
    public void setFlightDate() {
        Date flightD = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.flightDate = dateFormat.format(flightD.getTime());
    }*/
}
