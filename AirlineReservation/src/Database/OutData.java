package Database;

import Class.Account;
import Class.Reservation;
import Class.Flight;
import Class.Airport;
import Class.Airline;
import Class.Baggage;
import Class.Seats;
import Class.Passenger;

import GUI.LoginFrame;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * The OutData class is responsible for retrieving data from a database and converting it into usable objects.
 */
public class OutData {
    public String test_datah = "{ \"all_accounts\": [ { \"username\": \"cfig32\", \"account\": { \"username\": \"cfig32\", \"password\": \"123\", \"first_name\": \"Carlos\", \"last_name\": \"Figueroa\", \"address\": \"1400 Trash st. Burbank CA.\", \"email_address\": \"cfig32@AOL.com\", \"phone_number\": \"(818)666-1111\", \"reservation_list\": [ { \"reservation_ID\": 2222222, \"flight_price\": \"111.1\", \"flight_seat_fee\": \"25.6\", \"flight_baggage_fee\": \"35.0\", \"flight_tax\": \"50.3\", \"flight_total\": \"222.0\", \"departure_date\": \"05/08/2023\", \"return_date\": null, \"round_trip\": false, \"departure_flight_number\": \"AA3248\", \"return_flight_number\": null, \"departure_party_size\": 1, \"return_party_size\": 0, \"departure_flight\": { \"flight_ID\": \"AA3248\", \"departure_date\": \"05/08/2023\", \"arrival_date\": \"05/09/2023\", \"departure_time\": \"23:30:00\", \"arrival_time\": \"11:11:00\", \"departure_location\": \"Los Angeles\", \"arrival_location\": \"Tokyo\", \"departure_airport\": { \"airport_code\": \"LAX\", \"airport_name\": \"Los Angeles Int'l\" }, \"arrival_airport\": { \"airport_code\": \"HND\", \"airport_name\": \"Tokyo Int'l Airport\" }, \"airline\": { \"airline_id\": \"AAL\", \"airline_name\": \"American Airlines\" }, \"baggage\": [ { \"passenger_name\": \"Carlos Figueroa\", \"number_bags\": 4 } ], \"seats\": { \"reserved_seat_numbers\": [ \"2A\" ], \"seat_classes\": [ \"first-class\" ] } }, \"return_flight\": null, \"departure_party\": [ { \"first_name\": \"Carlos\", \"last_name\": \"Figueroa\", \"passport_country\": \"United States\", \"gender\": \"male\", \"date_of_birth\": \"09/14/1994\" } ], \"return_party\": null } ] } }, { \"username\": \"bob123\", \"account\": { \"username\": \"bob123\", \"password\": \"123\", \"first_name\": \"bob\", \"last_name\": \"sanchez\", \"address\": \"666 Garbage st. Burbank CA.\", \"email_address\": \"cfig32@AOL.com\", \"phone_number\": \"(666)666-4545\", \"reservation_list\": [ { \"reservation_ID\": 6546521, \"flight_price\": \"462.1\", \"flight_seat_fee\": \"254.6\", \"flight_baggage_fee\": \"35.0\", \"flight_tax\": \"301.3\", \"flight_total\": \"1053.0\", \"departure_date\": \"08/25/2023\", \"return_date\": null, \"round_trip\": false, \"departure_flight_number\": \"SW6125\", \"return_flight_number\": null, \"departure_party_size\": 3, \"return_party_size\": 0, \"departure_flight\": { \"flight_ID\": \"SW6125\", \"departure_date\": \"08/25/2023\", \"arrival_date\": \"08/25/2023\", \"departure_time\": \"03:33:00\", \"arrival_time\": \"07:13:00\", \"departure_location\": \"Las Vegas\", \"arrival_location\": \"New York\", \"departure_airport\": { \"airport_code\": \"LAS\", \"airport_name\": \"Las Vegas Int'l\" }, \"arrival_airport\": { \"airport_code\": \"NYA\", \"airport_name\": \"New York Airport\" }, \"airline\": { \"airline_id\": \"SWA\", \"airline_name\": \"idk\" }, \"baggage\": [ { \"passenger_name\": \"bob sanchez\", \"number_bags\": 1 }, { \"passenger_name\": \"sam schwartz\", \"number_bags\": 4 }, { \"passenger_name\": \"dick richard wellington\", \"number_bags\": 2 } ], \"seats\": { \"reserved_seat_numbers\": [ \"23A\", \"41B\", \"30C\" ], \"seat_classes\": [ \"econ-class\", \"econ-class\", \"econ-class\" ] } }, \"return_flight\": null, \"departure_party\": [ { \"first_name\": \"bob\", \"last_name\": \"sanchez\", \"passport_country\": \"United States\", \"gender\": \"male\", \"date_of_birth\": \"10/24/1954\" }, { \"first_name\": \"sam\", \"last_name\": \"schwartz\", \"passport_country\": \"Germany\", \"gender\": \"male\", \"date_of_birth\": \"02/02/1964\" }, { \"first_name\": \"dick richard\", \"last_name\": \"wellington\", \"passport_country\": \"Wales\", \"gender\": \"female\", \"date_of_birth\": \"10/30/2005\" } ], \"return_party\": null } ] } } ] }";

    /**
     * Retrieves account data from the database and creates an Account object.
     *
     * @param jsonObjectArrayList An ArrayList of JSONObjects containing the account data.
     * @param index The index of the account data in the ArrayList.
     * @return An Account object created from the retrieved data.
     */
    public Account getAccountData(ArrayList<JSONObject> jsonObjectArrayList, int index) {
        JSONObject jsonObject = jsonObjectArrayList.get(index).getJSONObject("account");

        String username = jsonObject.get("username").toString();
        String password = jsonObject.get("password").toString();
        String firstName = jsonObject.get("first_name").toString();
        String lastName = jsonObject.get("last_name").toString();
        String address = jsonObject.get("address").toString();
        String emailAddress = jsonObject.get("email_address").toString();
        String phoneNumber = jsonObject.get("phone_number").toString();

        return new Account(username, password, firstName, lastName, address, emailAddress, phoneNumber);
    }

    /**
     * Retrieves reservation data from the database and creates a Reservation object.
     *
     * @param jsonObjectArrayList An ArrayList of JSONObjects containing the reservation data.
     * @param index The index of the reservation data in the ArrayList.
     * @return A Reservation object created from the retrieved data.
     */
    public Reservation getReservationData(ArrayList<JSONObject> jsonObjectArrayList, int index) {
        JSONObject jsonReservation = jsonObjectArrayList.get(index);

        int reservationID = Integer.parseInt(jsonReservation.get("reservation_ID").toString());

        Double flightPrice = Double.parseDouble(jsonReservation.get("flight_price").toString());
        Double flightSeatFee;
        try {
            flightSeatFee = Double.parseDouble(jsonReservation.get("flight_seat_fee").toString());
        } catch (Exception e) {
            flightSeatFee = 0.0;
        }
        Double flightBaggageFee;
        try {
            flightBaggageFee = Double.parseDouble(jsonReservation.get("flight_baggage_fee").toString());
        } catch(Exception e) {
            flightBaggageFee = 0.0;
        }
        Double flightTax = Double.parseDouble(jsonReservation.get("flight_tax").toString());
        Double flightTotal = Double.parseDouble(jsonReservation.get("flight_total").toString());
        String departureDate = jsonReservation.get("departure_date").toString();
        String departureFlightNumber = jsonReservation.get("departure_flight_number").toString();
        int departurePartySize = Integer.parseInt(jsonReservation.get("departure_party_size").toString());

        String roundTripStr = jsonReservation.get("round_trip").toString();
        boolean roundTrip;
        if (roundTripStr.equals("true")) {
            roundTrip = true;
            String returnDate = jsonReservation.get("return_date").toString();
            String returnFlightNumber = jsonReservation.get("return_flight_number").toString();
            int returnPartySize = Integer.parseInt(jsonReservation.get("return_party_size").toString());

            return new Reservation();
        } else {
            roundTrip = false;
            return new Reservation(reservationID, flightPrice, flightSeatFee, flightBaggageFee, flightTax, flightTotal,
                    departureDate, departureFlightNumber, departurePartySize, null);
        }
    }

    /**
     * Retrieves flight data from the database and creates a Flight object.
     *
     * @param jsonObject The JSONObject containing the flight data.
     * @param index The index of the flight data in the JSONObject.
     * @return A Flight object created from the retrieved data.
     */
    public Flight getFlightData(JSONObject jsonObject, int index) {
        String flightID = jsonObject.get("flight_ID").toString();
        String departureDate = jsonObject.get("departure_date").toString();
        String arrivalDate = jsonObject.get("arrival_date").toString();
        String departureTime = jsonObject.get("departure_time").toString();
        String arrivalTime = jsonObject.get("arrival_time").toString();
        String departureLocation = jsonObject.get("departure_location").toString();
        String arrivalLocation = jsonObject.get("arrival_location").toString();

        return new Flight(flightID, departureDate, arrivalDate, departureTime, arrivalTime, departureLocation, arrivalLocation);
    }

    /**
     * Retrieves airport data from the database and creates an Airport object.
     *
     * @param jsonObject The JSONObject containing the airport data.
     * @param index      The index of the airport data in the JSONObject.
     * @return An Airport object created from the retrieved data.
     */
    public Airport getAirportData(JSONObject jsonObject, int index) {
        String airportCode = jsonObject.get("airport_code").toString();
        String airportName = jsonObject.get("airport_name").toString();

        return new Airport(airportCode, airportName);
    }

    /**
     * Retrieves airline data from the database and creates an Airline object.
     *
     * @param jsonObject The JSONObject containing the airline data.
     * @param index      The index of the airline data in the JSONObject.
     * @return An Airline object created from the retrieved data.
     */
    public Airline getAirlineData(JSONObject jsonObject, int index) {
        String airlineID = jsonObject.get("airline_id").toString();
        String airlineName = jsonObject.get("airline_name").toString();

        return new Airline(airlineID, airlineName);
    }

    /**
     * Retrieves baggage data from the database and creates an ArrayList of Baggage objects.
     *
     * @param jsonObjectArrayList An ArrayList of JSONObjects containing the baggage data.
     * @param index               The index of the baggage data in the ArrayList.
     * @return An ArrayList of Baggage objects created from the retrieved data.
     */
    public ArrayList<Baggage> getBaggageData(JSONArray jsonObjectArrayList, int index) {
        //JSONObject jsonBaggage = jsonObjectArrayList.getJSONObject();
        ArrayList<Baggage> baggageList = new ArrayList<>();

        for (int i = 0; i < jsonObjectArrayList.length(); i++) {
            JSONObject jsonObject = jsonObjectArrayList.getJSONObject(i);

            String passengerName = jsonObject.get("passenger_name").toString();
            int numBags = Integer.parseInt(jsonObject.get("number_bags").toString());

            Baggage baggage = new Baggage(passengerName, numBags);
            baggageList.add(baggage);
        }

        return baggageList;
    }

    /**
     * Retrieves seats data from the database and creates a Seats object.
     *
     * @param jsonObject The JSONObject containing the seats data.
     * @param index      The index of the seats data in the JSONObject.
     * @return A Seats object created from the retrieved data.
     */
    public Seats getSeatsData(JSONObject jsonObject, int index) {
        JSONArray jsonArraySeatNumbers = jsonObject.getJSONArray("reserved_seat_numbers");
        JSONArray jsonArraySeatClasses = jsonObject.getJSONArray("seat_classes");

        ArrayList<String> reservedSeatNumbers = JSONArrayToList2(jsonArraySeatNumbers);
        ArrayList<String> seatClasses = JSONArrayToList2(jsonArraySeatClasses);

        return new Seats(reservedSeatNumbers, seatClasses);
    }

    /**
     * Retrieves party data from the database and creates an ArrayList of Passenger objects.
     *
     * @param jsonObjectArrayList An ArrayList of JSONObjects containing the party data.
     * @param index               The index of the party data in the ArrayList.
     * @return An ArrayList of Passenger objects created from the retrieved data.
     */
    public ArrayList<Passenger> getPartyData(JSONArray jsonObjectArrayList, int index) {
        ArrayList<Passenger> partyList = new ArrayList<>();

        for (int i = 0; i < jsonObjectArrayList.length(); i++) {
            JSONObject jsonObject = jsonObjectArrayList.getJSONObject(i);

            String firstName = jsonObject.get("first_name").toString();
            String lastName = jsonObject.get("last_name").toString();
            String passportCountry = jsonObject.get("passport_country").toString();
            String gender = jsonObject.get("gender").toString();
            String dateOfBirth = jsonObject.get("date_of_birth").toString();

            Passenger passenger = new Passenger(firstName, lastName, passportCountry, gender, dateOfBirth);
            partyList.add(passenger);
        }

        return partyList;
    }


    /**
     * Retrieves the database information and stores it in the application.
     *
     * @param databaseStr The database string containing the required information.
     */
    //  CHANGE THIS TO A CALLABLE METHOD
    public void getDatabase(String databaseStr) {
        OutData outData = new OutData();

        // All accounts in database
        System.out.println();
        System.out.println(1);
        ArrayList<JSONObject> accountList = outData.JSONArrayToList1(databaseStr, "all_accounts");


        // get database info and store to app
        for (int i = 0; i < accountList.size(); i++) {
            Account account = outData.getAccountData(accountList, i);
            System.out.println(2);
            // create reservation list here
            ArrayList<JSONObject> resevationList = outData.JSONArrayToList1(accountList.get(i).getJSONObject("account")
                    .toString(), "reservation_list");
            for (int j = 0; j < resevationList.size(); j++) {

                // Reservation
                Reservation reservation = outData.getReservationData(resevationList, j);


                // Flight - departure and returning
                // departure flight
                JSONObject jsonDepartureFlight = resevationList.get(j).getJSONObject("departure_flight");
                Flight departureFlight = outData.getFlightData(jsonDepartureFlight, i);

                // departure airport
                Airport departureAirport = outData.getAirportData(jsonDepartureFlight.getJSONObject("departure_airport"), j);
                // departure-arrival airport
                Airport arrivalAirport1 = outData.getAirportData(jsonDepartureFlight.getJSONObject("arrival_airport"), i);
                // departure airline
                Airline departureAirline = outData.getAirlineData(jsonDepartureFlight.getJSONObject("airline"), j);
                // departure baggage
                ArrayList<Baggage> departureBaggage = outData.getBaggageData(jsonDepartureFlight.getJSONArray("baggage"), j);
                // departure seats
                Seats departureSeats = outData.getSeatsData(jsonDepartureFlight.getJSONObject("seats"), j);
                if (reservation.isRoundTrip()) {
                    // return flight
                    JSONObject jsonReturnFlight = resevationList.get(i).getJSONObject("return_flight");
                    Flight returnFlight = outData.getFlightData(jsonReturnFlight, j);
                    // return airport
                    Airport returnAirport = outData.getAirportData(jsonReturnFlight.getJSONObject("departure_airport"), j);
                    // return-arrival airport
                    Airport arrivalAirport2 = outData.getAirportData(jsonReturnFlight.getJSONObject("arrival_airport"), j);
                    // return airline
                    Airline returnAirline = outData.getAirlineData(jsonReturnFlight.getJSONObject("airline"), j);
                    // return baggage
                    ArrayList<Baggage> returnBaggage = outData.getBaggageData(jsonReturnFlight.getJSONArray("baggage"), j);
                    // return seats
                    Seats returnSeats = outData.getSeatsData(jsonReturnFlight.getJSONObject("seats"), j);
                }


                // departure party
                ArrayList<Passenger> departureParty = new ArrayList<Passenger>();
                departureParty = outData.getPartyData(resevationList.get(j).getJSONArray("departure_party"), j);
                if (reservation.isRoundTrip()) {
                    // return party
                    ArrayList<Passenger> returnParty = new ArrayList<Passenger>();
                    returnParty = outData.getPartyData(resevationList.get(j).getJSONArray("return_party"), j);
                }

                // was set to null when making reservation instance
                reservation.setDepartureParty(departureParty);

                // store departure instances to departure flight
                departureFlight.setDepartureAirport(departureAirport);
                departureFlight.setArrivalAirport(arrivalAirport1);
                departureFlight.setAirline(departureAirline);
                departureFlight.setBaggage(departureBaggage);
                departureFlight.setSeats(departureSeats);
                // store flight(s) to reservation
                reservation.setDepartureFlight(departureFlight);

                // store return instances to return flight
                if (reservation.isRoundTrip()) {
                    // later
                }

                // store reservation to account
                account.addReservationToAccount(reservation);
            }

            // store account in hashmap
            account.registerOrUpdate(account);
        }
    }

    /**
     * Retrieves data from a JSON array and converts it into an ArrayList of JSONObjects.
     *
     * @param data The JSON array as a string.
     * @param key  The key used to extract the JSON array from the JSON object.
     * @return An ArrayList of JSONObjects converted from the JSON array.
     */
    public ArrayList<JSONObject> JSONArrayToList1(String data, String key) {

        ArrayList<JSONObject> jsonObjectArrayList = new ArrayList<>();
        JSONObject jsnobject = new JSONObject(data);

        JSONArray jsonArray = jsnobject.getJSONArray(key);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);
            jsonObjectArrayList.add(explrObject);
        }
        return jsonObjectArrayList;
    }

    /**
     * Converts a JSON array into an ArrayList of strings.
     *
     * @param jArray The JSON array to be converted.
     * @return An ArrayList of strings converted from the JSON array.
     */
    public ArrayList<String> JSONArrayToList2(JSONArray jArray) {
        ArrayList<String> listdata = new ArrayList<String>();
        if (jArray != null) {
            for (int i = 0; i < jArray.length(); i++) {
                listdata.add(jArray.getString(i));
            }
        }
        return listdata;
    }
}
