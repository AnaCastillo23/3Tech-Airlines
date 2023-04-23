package Helper;

import Class.Reservation;

import java.util.HashMap;
import java.util.Map;

public class ReservationToCheckout {
    private static final Map<String, Reservation> reservationToPayment = new HashMap<>();

    public ReservationToCheckout() {

    }

    public ReservationToCheckout(Reservation reservation) {
        //this.bookedFlights = bookedFlights;
        setReservationToPayment(reservation);
    }

    public void setReservationToPayment(Reservation bookedFlights) {
        reservationToPayment.put("checkout", bookedFlights);
    }

    public Reservation getReservationToPayment() {
        return reservationToPayment.get("checkout");
    }

    public void deleteCheckout() {
        reservationToPayment.clear();
    }
}
