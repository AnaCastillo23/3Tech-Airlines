package ClassTest;

import Class.Flight;
import Class.Airport;
import Class.Airline;
import Class.Seats;

import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;


@RunWith(JUnit4.class)
public class FlightTest {
    private static Flight flight1;
    private static Flight flight2;
    private static Airport departureAirport;
    private static Airport arrivalAirport;
    private static Airline airline;
    private static Seats seats;


    @BeforeClass
    public static void flightSetUp() throws Exception {
        flight1 = new Flight();
        //flight2 = new Flight("WN2834", "05/06/2023", "05/08/2023");


        departureAirport = new Airport("BNA", "Nashville International Airport");
        arrivalAirport = new Airport("LHR", "London Heathrow Airport");

        airline = new Airline("AAL", "American Airlines");

        ArrayList<String> reservedSeats = new ArrayList<>();
        ArrayList<String> seatClasses = new ArrayList<>();
        reservedSeats.add("1A");
        reservedSeats.add("1B");
        seatClasses.add("first-class");
        seatClasses.add("first-class");
        seats = new Seats(reservedSeats, seatClasses);
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
        flight1.setDepartureAirport(departureAirport); //import airport
    }

    @Test
    public void setArrivalAirport() {
        flight1.setArrivalAirport(arrivalAirport); //import airport
    }

    @Test
    public void setAirline() {
        flight1.setAirline(airline);
    }

    @Test
    public void setSeats() {
        flight1.setSeats(seats); //import seats
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
        flight1.setDepartureAirport(departureAirport);
        Assert.assertEquals(departureAirport, flight1.getDepartureAirport());
    }

    @Test
    public void getArrivalAirport() {
        flight1.setArrivalAirport(arrivalAirport);
        Assert.assertEquals(arrivalAirport, flight1.getArrivalAirport());
    }

    @Test
    public void getAirline() {
        flight1.setAirline(airline);
        Assert.assertEquals(airline, flight1.getAirline());
    }

    @Test
    public void getSeats() {
        flight1.setSeats(seats);
        Assert.assertEquals(seats, flight1.getSeats());
    }
}