package Database;

import Class.Account;
import Class.Reservation;
import Class.Flight;
import Class.Airport;
import Class.Baggage;
import Class.Seats;
import Class.Passenger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class InData {
    /*      1) storeAccount(): store basic account info : username, pass, name, address, etc
            2) storeReservation(): for each reservation (loop) or reservationList
                3) For each reservation:
                    4) store info: reservationID, flight fees, dates, party size, etc
                    5) storeDepartureFlight(), storeReturnFlight(if roundtrip), storeDepartureParty(List), storeReturnParty(List)

                        I) storeDepartureFlight():
                            1) store info: flightID, dates, time, location
                            2) storeDepartureAirport(), storeArrivalAirport(), storeAirline(), storeBaggage(), storeSeats()
                                I) storeDepartureAirport():
                                    1) store: airportCode, airportName

                                II) storeArrivalAirport():
                                    1) store: airportCode, airportName

                                III) storeAirline():
                                    1) store: airlineID, airlineName

                                IV) storeBaggage():
                                    1) store: name(passenger), numBags

                                V) storeSeats():
                                    1) store: reservedSeatNumbers, seatClass

                      X II) storeReturnFlight():
                            1) store info: flightID, dates, time, location
                            2) storeDepartureAirport(), storeArrivalAirport(), storeAirline(), storeBaggage, storeSeats()
                                I) storeDepartureAirport():
                                    1) store: airportCode, airportName

                                II) storeArrivalAirport():
                                    1) store: airportCode, airportName

                                III) storeAirline():
                                    1) store: airlineID, airlineName

                                IV) storeBaggage():
                                    1) store: name(passenger), numBags

                                V) storeSeats():
                                    1) store: reservedSeatNumbers, seatClass

                        III) storeDepartureParty(List):
                            1) For each Passenger, store name, passport, gender, DOB

                     X  IV) storeReturnParty(List):
                            1) For each Passenger, store name, passport, gender, DOB


            n) update account HashMap storeAccountHashMap(hashmap)
    */

    //  TEST STORING DATA FROM TEST FILE FIRST!!!!!!!!!!!!!!!!!!

    public void updateDatabaseAccounts() throws IOException {
        Account account = new Account();
        ArrayList<Reservation> reservationList;

        Map<String, Account> map = account.getLoginAccount();

        /*
        key : bob123
        value : Class.Account@7413de8e
        key : cfig32
        value : Class.Account@396f946b
        */
        /*
        System.out.println("key : " + key);
        System.out.println("value : " + map.get(key));

         */


        //StringBuilder allAccounts = new StringBuilder();
        StringBuilder accountStr = new StringBuilder();
        
        ArrayList<String> allAccounts = new ArrayList<>(); // not sure what this is for

        int counter = 0;
        for (String key: map.keySet()) {
            System.out.println("key : " + key);
            System.out.println("value : " + map.get(key));

            account = map.get(key); // one account
            reservationList = account.getReservationList();  //reservation list per ONE account

            accountStr.append("{ ");
            accountStr.append("\"username\": \"" + key + "\",");
            accountStr.append(" \"account\": { \"username\": \"" + account.getUsername() + "\", \"password\": \"" + account.getPassword() + "\", \"first_name\": \"" + account.getFirstName() + "\", \"last_name\": \"" + account.getLastName() +
                    "\", \"address\": \"" + account.getAddress() + "\", \"email_address\": \"" + account.getEmailAddress() + "\", \"phone_number\": \"" + account.getPhoneNumber() + "\", \"reservation_list\": [ ");

            // this is departure only
            for(int i = 0; i < reservationList.size(); i++) {
                Reservation reservation = reservationList.get(i);
                Flight departureFlight = reservation.getDepartureFlight();
                Airport departureAirport = departureFlight.getDepartureAirport();
                Airport arrivalAirport = departureFlight.getArrivalAirport();

                if(!reservation.isRoundTrip()) {
                    accountStr.append("{ \"reservation_ID\": " + reservation.getReservationID() + ", \"flight_price\": \"" + reservation.getFlightPrice() + "\", \"flight_seat_fee\": \"" + reservation.getFlightSeatFee() +
                            "\", \"flight_baggage_fee\": \"" + reservation.getFlightBaggageFee() + "\", \"flight_tax\": \"" + reservation.getFlightTax() + "\", \"flight_total\": \"" + reservation.getFlightTotal() + "\", \"departure_date\":" +
                            " " + "\"" + reservation.getDepartureDate() + "\", \"return_date\": null, \"round_trip\": " + reservation.isRoundTrip() + ", \"departure_flight_number\": \"" + reservation.getDepartureFlightNumber() +
                            "\", \"return_flight_number\": null, \"departure_party_size\": " + reservation.getDeparturePartySize() + ", \"return_party_size\": " + reservation.getReturnPartySize() + ", \"departure_flight\": " +
                            "{ \"flight_ID\": \"" + departureFlight.getFlightID() + "\", \"departure_date\": \"" + departureFlight.getDepartureDate() + "\", \"arrival_date\": \"" + departureFlight.getArrivalDate() + "\"," +
                            " \"departure_time\": \"" + departureFlight.getDepartureTime() + "\", \"arrival_time\": \"" + departureFlight.getArrivalTime() + "\", \"departure_location\": \"" + departureFlight.getDepartureLocation() +
                            "\", \"arrival_location\": \"" + departureFlight.getArrivalLocation() + "\", \"departure_airport\": { \"airport_code\": \"" + departureAirport.getAirportCode() + "\", \"airport_name\": \"" +
                            departureAirport.getAirportName() + "\" }, \"arrival_airport\": { \"airport_code\": \"" + arrivalAirport.getAirportCode() + "\", \"airport_name\": \"" + arrivalAirport.getAirportName() + "\" }, " +
                            "\"airline\": { \"airline_id\": \"" + departureFlight.getAirline().getAirlineID() + "\", \"airline_name\": \"" + departureFlight.getAirline().getAirlineName() + "\" }, \"baggage\": [ ");

                    ArrayList<Baggage> departureBaggage = departureFlight.getBaggage();
                    for(int j = 0; j < departureBaggage.size(); j++) {
                        accountStr.append("{ \"passenger_name\": \"" + departureBaggage.get(j).getPassengerName() + "\", \"number_bags\": " + departureBaggage.get(j).getNumBags() + " }");
                        if(j != departureBaggage.size() - 1) {
                            accountStr.append(", ");
                        } else {
                            accountStr.append(" ], \"seats\": { \"reserved_seat_numbers\": [ ");
                        }
                    }
                    Seats seats = departureFlight.getSeats();
                    for(int j = 0; j < seats.getReservedSeatNumbers().size(); j++) {
                        accountStr.append("\"" + seats.getReservedSeatNumbers().get(j) + "\"");
                        if(j != seats.getReservedSeatNumbers().size() - 1) {
                            accountStr.append(", ");
                        } else {
                            accountStr.append(" ], \"seat_classes\": [ ");
                        }
                    }
                    for(int j = 0; j < seats.getReservedSeatNumbers().size(); j++) {
                        accountStr.append("\"" + seats.getReservedSeatNumbers().get(j) + "\"");
                        if(j != seats.getReservedSeatNumbers().size() - 1) {
                            accountStr.append(", ");
                        } else {
                            accountStr.append(" ] } }, ");
                        }
                    }

                    accountStr.append("\"return_flight\": null, \"departure_party\": [ ");

                    ArrayList<Passenger> departureParty = reservation.getDepartureParty();
                    for(int j = 0; j < departureParty.size(); j++) {
                        accountStr.append("{ \"first_name\": \"" + departureParty.get(j).getFirstName() + "\", \"last_name\": \"" + departureParty.get(j).getLastName() + "\", \"passport_country\": \""
                                + departureParty.get(j).getPassportCountry() + "\", \"gender\": \"" + departureParty.get(j).getGender() + "\", \"date_of_birth\": \"" + departureParty.get(j).getDateOfBirth()
                                + "\" }");
                        if(j != departureParty.size() - 1) {
                            accountStr.append(", ");
                        } else {
                            accountStr.append(" ], ");
                        }
                    }

                    accountStr.append("\"return_party\": null ");

                } else {

                }
                // end of reservation list and its objects
                accountStr.append("} ");
                if(i != reservationList.size() - 1) {
                    accountStr.append(", ");
                } else {
                    accountStr.append(" ");
                }
            }
            accountStr.append("] } }");
            if(counter != map.size() - 1) {
                accountStr.append(", ");
            } else {
                accountStr.append(" ");
            }
            counter++;
        }
        accountStr.append("] }");

        StringBuilder databaseStr = new StringBuilder("{ \"all_accounts\": [ ");
        databaseStr.append(accountStr);

        System.out.println(databaseStr);

        DatabaseFileIO dataI = new DatabaseFileIO();
        dataI.databaseFileWriter(databaseStr.toString());
    }

    public static void main(String[] args) throws IOException {
        InData inData = new InData();
        OutData outData = new OutData();

        outData.getDatabase(null);
        inData.updateDatabaseAccounts();
    }
}
