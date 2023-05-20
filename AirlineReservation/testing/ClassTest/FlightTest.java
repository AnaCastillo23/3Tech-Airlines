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

/**
 *
 * This is a testing file for the Flight.java class
 *
 * @since 05/05/2023
 * @author Ana Emily Castillo Perez, Carlos Figueroa
 * <p>
 * <b>Explanation of important functions:</b> File tests getters and setters from our Flight.java class
 * as well as newly implemented getters and setters.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>flightSetUp():</i> used to store newly create flight objects.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */

@RunWith(JUnit4.class)
public class FlightTest {
    private static Flight flight1;
    private static Flight flight2;
    private static Airport departureAirport;
    private static Airport arrivalAirport;
    private static Airline airline;
    private static Seats seats;


    /**
     *
     * Method for creating new flight objects implementing the Flight.java class
     *
     */
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

    /**
     *
     * For setting the Flight ID.
     *
     */
    @Test
    public void setFlightID() {
        flight1.setFlightID("AI126");
    }
    /**
     *
     * For setting a flight's Departure Date and pass it onto the API.
     *
     */
    @Test
    public void setDepartureDate() {
        flight1.setDepartureDate("05/06/2023");
    }

    /**
     *
     * For setting Arrival Date.
     *
     */
    @Test
    public void setArrivalDate() {
        flight1.setArrivalDate("05/08/2023");
    }

    /**
     *
     * For setting Departure Time.
     *
     */
    @Test
    public void setDepartureTime() {
        flight1.setDepartureTime("10:00 am");
    }

    /**
     *
     * For setting Arrival Time.
     *
     */
    @Test
    public void setArrivalTime() {
        flight1.setArrivalTime("3:25 pm");
    }

    /**
     *
     * For setting Departure Location.
     *
     */
    @Test
    public void setDepartureLocation() {
        flight1.setDepartureLocation("LAX");
    }

    /**
     *
     * For setting Arrival Location.
     *
     */
    @Test
    public void setArrivalLocation() {
        flight1.setArrivalLocation("KLGA");
    }

    /**
     *
     * For setting departure Airport.
     *
     */
    @Test
    public void setDepartureAirport() {
        flight1.setDepartureAirport(departureAirport); //import airport
    }

    /**
     *
     * For setting arrival Airport.
     *
     */
    @Test
    public void setArrivalAirport() {
        flight1.setArrivalAirport(arrivalAirport); //import airport
    }

    /**
     *
     * For setting arrival Airline.
     *
     */
    @Test
    public void setAirline() {
        flight1.setAirline(airline);
    }

    /**
     *
     * For setting seats.
     *
     */
    @Test
    public void setSeats() {
        flight1.setSeats(seats); //import seats
    }

    //Testing getters
    /**
     *
     * For obtaining the Flight's ID.
     *
     */
    @Test
    public void getFlightID() {
        flight1.setFlightID("AI126");
        Assert.assertEquals("AI126", flight1.getFlightID());
        Assert.assertEquals("WN2834", flight2.getFlightID());
    }

    /**
     *
     * For obtaining the departure date.
     *
     */
    @Test
    public void getDepartureDate() {
        flight1.setDepartureDate("05/06/2023");
        Assert.assertEquals("05/06/2023", flight1.getDepartureDate());
        Assert.assertEquals("05/06/2023",flight2.getDepartureDate());
    }

    /**
     *
     * For obtaining the arrival date.
     *
     */
    @Test
    public void getArrivalDate() {
        flight1.setArrivalDate("05/08/2023");
        Assert.assertEquals("05/08/2023", flight1.getArrivalDate());
        Assert.assertEquals("05/08/2023", flight2.getArrivalDate());
    }

    /**
     *
     * For obtaining the departure time.
     *
     */
    @Test
    public void getDepartureTime() {
        flight1.setDepartureTime("10:00 am");
        Assert.assertEquals("10:00 am", flight1.getDepartureTime());
        Assert.assertEquals("11:00 am", flight2.getDepartureTime());
    }

    /**
     *
     * For obtaining the arrival time.
     *
     */
    @Test
    public void getArrivalTime() {
        flight1.setArrivalTime("3:25 pm");
        Assert.assertEquals("3:25 pm", flight1.getArrivalTime());
        Assert.assertEquals("4:44 pm", flight2.getArrivalTime());
    }

    /**
     *
     * For obtaining the departure location.
     *
     */
    @Test
    public void getDepartureLocation() {
        flight1.setDepartureLocation("LAX");
        Assert.assertEquals("LAX", flight1.getDepartureLocation());
        Assert.assertEquals("KIAD", flight2.getDepartureLocation());
    }

    /**
     *
     * For obtaining the arrival location.
     *
     */
    @Test
    public void getArrivalLocation() {
        flight1.setArrivalLocation("KLGA");
        Assert.assertEquals("KLGA", flight1.getArrivalLocation());
        Assert.assertEquals("LAX", flight2.getArrivalLocation());
    }

    /**
     *
     * For obtaining the departure airport.
     *
     */
    @Test
    public void getDepartureAirport() {
        flight1.setDepartureAirport(departureAirport);
        Assert.assertEquals(departureAirport, flight1.getDepartureAirport());
    }

    /**
     *
     * For obtaining the arrival airport.
     *
     */
    @Test
    public void getArrivalAirport() {
        flight1.setArrivalAirport(arrivalAirport);
        Assert.assertEquals(arrivalAirport, flight1.getArrivalAirport());
    }

    /**
     *
     * For obtaining the airline.
     *
     */
    @Test
    public void getAirline() {
        flight1.setAirline(airline);
        Assert.assertEquals(airline, flight1.getAirline());
    }

    /**
     *
     * For obtaining the seats.
     *
     */
    @Test
    public void getSeats() {
        flight1.setSeats(seats);
        Assert.assertEquals(seats, flight1.getSeats());
    }
}