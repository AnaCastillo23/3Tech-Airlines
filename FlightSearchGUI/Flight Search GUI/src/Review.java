/**
 *
 * The Flight Search GUI is used to create a desktop application
 * for a flight reservation system. This GUI called "Review" helps user to review the flight they choose from a list
 * and confirm booking.
 * Makes use of an API to accomplish this task, along with user input.
 *
 * @author Ana Emily Castillo Perez
 * @version 1.1
 * @since  2023-04-05
 *
 */

import javax.swing.*;

public class Review extends JFrame {

    private JPanel reviewPanel;

    /**
     *
     * This method is for creating and displaying a desktop window to a specific size when user has selected a flight from the list and has clicked on OK button in MainFrame.
     *
     */
    public Review() {
        setContentPane(reviewPanel);
        setTitle("Flight Information");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * Action listener used to code the CONFIRM button of current frame if user decides to confirm
     * flight reservation.
     * @ return Returns the confirmation frame of flight registration.
     */
    //add listeners for confirming flight booking



    /**
     * Action listener used to code the CANCEL button of current frame if user decides to cancel
     * flight reservation and return to dashboard.
     * @ return Returns the dashboard frame.
     */
    //add listener for canceling booking

}
