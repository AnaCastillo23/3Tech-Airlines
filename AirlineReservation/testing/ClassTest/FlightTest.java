package ClassTest;

import Class.Flight;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class FlightTest {
    private static Flight flight1;
    private static Flight flight2;

    @BeforeClass
    public static void flightSetUp() throws Exception {
        flight1 = new Flight();
        flight2 = new Flight("WN2834", "05/06/2023", "05/08/2023", "11:00 am", "4:44 pm","KIAD","LAX" );
        Airport departureAirport = new Airport();
        Airport arrivalAirport = new Aiport();

    }
    @Test
    public void setFlightID() {
        flight1.setFlightID("WN2834");
    }
    @Test
    public void setDepartureDate() {
        flight1.setDepartureDate("05/06/2023");
    }

    @Test
    public void setArrivalDate() {
        flight1.setArrivalDate("05/08/2023");
    }

    @Test
    public void setDepartureTime() {
        flight1.setDepartureTime("11:00 am");
    }

    @Test
    public void setArrivalTime() {
        flight1.setArrivalTime("4:44 pm");
    }

    @Test
    public void setDepartureLocation() {
        flight1.setDepartureLocation("KIAD");
    }

    @Test
    public void setArrivalLocation() {
        flight1.setArrivalLocation("LAX");
    }

    @Test
    public void setDepartureAirport() {
        flight1.SetDepartureAirport(departureAirport); //import airport
    }

    @Test
    public void setArrivalAirport() {
        flight1.setArrivalLocation(); //import airport
    }

    @Test
    public void setAirline() {
        flight1.setAirline();
    }

    @Test
    public void setSeats() {
        flight1.setSeats(); //import seats
    }

    //Testing getters
    @Test
    public void getFlightID() {

    }

    @Test
    public void getDepartureDate() {

    }

    @Test
    public void getArrivalDate() {

    }

    @Test
    public void getDepartureTime() {

    }

    @Test
    public void getArrivalTime() {

    }

    @Test
    public void getDepartureLocation() {

    }

    @Test
    public void getArrivalLocation() {

    }

    @Test
    public void getDepartureAirport() {

    }

    @Test
    public void getArrivalAirport() {

    }

    @Test
    public void getAirline() {

    }

    @Test
    public void getSeats() {

    }
}