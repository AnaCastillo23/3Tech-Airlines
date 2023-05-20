package Class;

/**
 * The Passenger class is used to keep track of a passenger's information.
 *
 * @since 03/27/2023
 * @author Carlos Figueroa (developed class) and Ana Emily Castillo Perez (added documentation comments).
 * <p>
 * <b>Explanation of important functions:</b> Class is used for storing all the information about newly added
 * passengers to a flight by the user.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>Passenger(String firstName, String lastName, String passportCountry, String gender, String dateOfBirth):</i> used to store passenger's properties.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
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

    /**
     *
     * Method for storing information into a new passenger object
     *
     * @param firstName firstName
     * @param lastName lastName
     * @param passportCountry passportCountry
     * @param gender gender
     * @param dateOfBirth dateOfBirth
     *
     */
    public Passenger(String firstName, String lastName, String passportCountry, String gender, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportCountry = passportCountry;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     *
     * For obtaining a passenger's first name.
     *
     * @return firstName
     *
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * For obtaining a passenger's last name.
     *
     * @return lastName
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * For obtaining a passenger's passport country.
     *
     * @return passportCountry
     *
     */
    public String getPassportCountry() {
        return passportCountry;
    }

    /**
     *
     * For obtaining a passenger's gender.
     *
     * @return gender
     *
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * For obtaining a passenger's date of birth.
     *
     * @return dateOfBirth
     *
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     *
     * For setting a passenger's first name.
     *
     * @param firstName firstName
     *
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * For setting a passenger's last name.
     * @param lastName lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * For setting a passenger's passport country.
     *
     * @param passportCountry passportCountry
     */
    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }

    /**
     *
     * For setting a passenger's gender.
     *
     * @param gender gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * For setting a passenger's date of birth.
     *
     * @param dateOfBirth dateOfBirth
     *
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
