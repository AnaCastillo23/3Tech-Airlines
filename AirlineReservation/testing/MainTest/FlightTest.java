/*package ClassTest;

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
        flight2 = new Flight("WN2834", "05/06/2023", "05/08/2023", "11:00 am", "4:44 pm","KIAD","LAX" );


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
        flight1.setFlightID("AI126");
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
        flight1.setDepartureTime("10:00 am");
    }

    @Test
    public void setArrivalTime() {
        flight1.setArrivalTime("3:25 pm");
    }

    @Test
    public void setDepartureLocation() {
        flight1.setDepartureLocation("LAX");
    }

    @Test
    public void setArrivalLocation() {
        flight1.setArrivalLocation("KLGA");
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
        flight1.setFlightID("AI126");
        Assert.assertEquals("AI126", flight1.getFlightID());
        Assert.assertEquals("WN2834", flight2.getFlightID());
    }

    @Test
    public void getDepartureDate() {
        flight1.setDepartureDate("05/06/2023");
        Assert.assertEquals("05/06/2023", flight1.getDepartureDate());
        Assert.assertEquals("05/06/2023",flight2.getDepartureDate());
    }

    @Test
    public void getArrivalDate() {
        flight1.setArrivalDate("05/08/2023");
        Assert.assertEquals("05/08/2023", flight1.getArrivalDate());
        Assert.assertEquals("05/08/2023", flight2.getArrivalDate());
    }

    @Test
    public void getDepartureTime() {
        flight1.setDepartureTime("10:00 am");
        Assert.assertEquals("10:00 am", flight1.getDepartureTime());
        Assert.assertEquals("11:00 am", flight2.getDepartureTime());
    }

    @Test
    public void getArrivalTime() {
        flight1.setArrivalTime("3:25 pm");
        Assert.assertEquals("3:25 pm", flight1.getArrivalTime());
        Assert.assertEquals("4:44 pm", flight2.getArrivalTime());
    }

    @Test
    public void getDepartureLocation() {
        flight1.setDepartureLocation("LAX");
        Assert.assertEquals("LAX", flight1.getDepartureLocation());
        Assert.assertEquals("KIAD", flight2.getDepartureLocation());
    }

    @Test
    public void getArrivalLocation() {
        flight1.setArrivalLocation("KLGA");
        Assert.assertEquals("KLGA", flight1.getArrivalLocation());
        Assert.assertEquals("LAX", flight2.getArrivalLocation());
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
}*/