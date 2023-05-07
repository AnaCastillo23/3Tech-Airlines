package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeatChange {
    private static final Map<Boolean, SeatChangeHelper> seatToChange = new HashMap<>();

    public SeatChange() {

    }

    public SeatChange(ArrayList<String> reservedSeats, ArrayList<String> classSeats, double total, boolean returnTrip) {
        SeatChangeHelper seatChangeHelper = new SeatChangeHelper(reservedSeats, classSeats, total);
        setSeatsToChange(returnTrip, seatChangeHelper);
    }

    public ArrayList<String> getReservedSeats(boolean returnTrip) {
        return getSeatToChange(returnTrip).getReservedSeatsHelper();
    }

    public ArrayList<String> getClassSeats(boolean returnTrip) {
        return getSeatToChange(returnTrip).getClassSeatsHelper();
    }

    public double getTotal(boolean returnTrip) {
        return getSeatToChange(returnTrip).getTotalHelper();
    }

    public void setSeatsToChange (boolean returnTrip, SeatChangeHelper seatChangeHelper) {
        seatToChange.put(returnTrip, seatChangeHelper);
    }

    public SeatChangeHelper getSeatToChange(boolean returnTrip) {
        return seatToChange.get(returnTrip);
    }

    public void deleteSeatsToChange(boolean returnTrip) {
        seatToChange.remove(returnTrip);
    }


    private class SeatChangeHelper {
        ArrayList<String> reservedSeats;
        ArrayList<String> classSeats;
        double total;

        public SeatChangeHelper(ArrayList<String> reservedSeats, ArrayList<String> classSeats, double total) {
            this.reservedSeats = reservedSeats;
            this.classSeats = classSeats;
            this.total = total;
        }

        public ArrayList<String> getReservedSeatsHelper() {
            return reservedSeats;
        }

        public ArrayList<String> getClassSeatsHelper() {
            return classSeats;
        }

        public double getTotalHelper() {
            return total;
        }
    }
}
