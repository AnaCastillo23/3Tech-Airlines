package Class;

public class Baggage {
    private String passengerName;
    private int numBags;


    public Baggage() {

    }

    public Baggage(String passengerName, int numBags) {
        this.passengerName = passengerName;
        this.numBags = numBags;
    }


    public String getPassengerName() {
        return passengerName;
    }

    public int getNumBags() {
        return numBags;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setNumBags(int numBags) {
        this.numBags = numBags;
    }
}
