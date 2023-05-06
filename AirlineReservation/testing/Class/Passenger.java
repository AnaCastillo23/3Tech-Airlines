package Class;

public class Passenger {
    private String firstName;
    private String lastName;
    private String passportCountry;
    private String gender;
    private String dateOfBirth;

    public Passenger() {
        this.firstName = null;
        this.lastName = null;
        this.passportCountry = null;
        this.gender = null;
        this.dateOfBirth = null;
    }

    public Passenger(String firstName, String lastName, String passportCountry, String gender, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportCountry = passportCountry;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportCountry() {
        return passportCountry;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
