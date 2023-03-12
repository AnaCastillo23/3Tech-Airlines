
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private final int reservationID;
    private final int flightID;
    private String flightDate;
    private final int seatNumber; //how to represent reserved seat?

    public Reservation(int reservationID, int flightID, String flightDate, int seatNumber) {
        this.reservationID = reservationID;
        this.flightID = flightID;
        this.flightDate = flightDate;
        this.seatNumber = seatNumber;
    }
    //Getters
    public int getReservationID() {
        return this.reservationID;
    }

    public int getFlightID() {
        return this.flightID;
    }

    public String getFlightDate() {
        return this.flightDate;
    }

    public int seatNumber() {
        return this.seatNumber;
    }

    public void setFlightDate() {
        Date flightD = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.flightDate = dateFormat.format(flightD.getTime());
    }
}
