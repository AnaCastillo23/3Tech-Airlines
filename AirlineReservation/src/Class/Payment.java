package Class;

/**
 * The Payment class represents the payment information of a user.
 * It stores the card holder name, expiration date, card number, country, address, city, state, and zip code.
 *
 * @since 05/17/2023
 * @author Carlos Figueroa
 * <b>Explanation of important functions:</b> Class is used to create an instance of a payment method to save to account
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>public Payment():</i> used to store payment propeerties.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */
public class Payment {
    private String cardHolderName;
    private String expirationDate;
    private String cardNumber;
    private String country;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    /**
     * Constructs an empty Payment object.
     */
    public Payment() {
    }

    /**
     * Constructs a Payment object with the specified payment information.
     *
     * @param cardHolderName  the card holder name
     * @param expirationDate  the expiration date
     * @param cardNumber      the card number
     * @param country         the country
     * @param address         the address
     * @param city            the city
     * @param state           the state
     * @param zipCode         the zip code
     */
    public Payment(String cardHolderName, String expirationDate, String cardNumber, String country, String address, String city, String state, String zipCode) {
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cardNumber = cardNumber;
        this.country = country;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /**
     * Retrieves the card holder name.
     *
     * @return the card holder name
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Retrieves the expiration date.
     *
     * @return the expiration date
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Retrieves the card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Retrieves the country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Retrieves the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Retrieves the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Retrieves the state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Retrieves the zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the card holder name.
     *
     * @param cardHolderName the card holder name to set
     */
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    /**
     * Sets the expiration date.
     *
     * @param expirationDate the expiration date to set
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Sets the card number.
     *
     * @param cardNumber the card number to set
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Sets the country.
     *
     * @param country the country to set
     */
    /**
     * Sets the address.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the city.
     *
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state.
     *
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the zip code.
     *
     * @param zipCode the zip code to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
