package Class;

public class Baggage {
    private String fullName;
    private int numBags;


    public Baggage() {

    }

    public Baggage(String fullName, int numBags) {
        this.fullName = fullName;
        this.numBags = numBags;
    }


    public String getFullName() {
        return fullName;
    }

    public int getNumBags() {
        return numBags;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setNumBags(int numBags) {
        this.numBags = numBags;
    }
}
