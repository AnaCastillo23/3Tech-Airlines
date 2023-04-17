/**
 *
 * Module name\Class name: ReviewFrame (class)
 * @since 2023-04-05
 * @author Ana Emily Castillo Perez
 *
 *
 * Description of the class/module: The Review GUI is used to display a desktop application
 * window which displays user's selection of flight that they wish to book in order for the user to review if
 * details about the flight are correct.
 *
 * Explanation of important functions: Displays user's choice of flight for its review. Displays CONFIRM button (to finish booking)
 * and CANCEL button (to return users to their dashboard(??????)).
 *
 * Important data structure in class/important methods in class:
 * ReviewFrame(): used for displaying a Java Swing window where flight details are displayed for passenger's review.
 *
 * Any algorithms used? Not at the moment.
 *
 */

package GUI;

import javax.swing.*;

public class ReviewFrame extends JFrame {

    private JPanel reviewPanel;
    private JTextArea welcomeToNextPaneTextArea;

    /**
     *
     * Method is for creating and displaying a desktop window to a specific size when user has selected a flight from the list and has clicked on NEXT button in FlightSearchFrame.
     *
     */
    public ReviewFrame() {
        setContentPane(reviewPanel);
        setTitle("Flight Information");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Action listener used to code the CONFIRM button of current frame if user decides to confirm
     * flight reservation.
     */
    //Ana-Still not done
    //add listeners for confirming flight booking which will return a confirmation frame thanking user for booking

    /**
     * Action listener used to code the CANCEL button of current frame if user decides to cancel
     * flight reservation and return to dashboard.
     */
    //Ana-Still not done
    //add listener for canceling booking which returns user to dashboard
}
