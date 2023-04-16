/**
 *
 * Module name\Class name: ReviewFrame (class)
 * @since 2023-04-05
 * @author Ana Emily Castillo Perez
 *
 *
 * Description of the class/module:
 *
 * Explanation of important functions:
 *
 * Important data structure in class/important methods in class:
 *
 *
 * Any algorithms used?
 *
 */

package GUI;

import javax.swing.*;

public class ReviewFrame extends JFrame {

    private JPanel reviewPanel;
    private JTextArea welcomeToNextPaneTextArea;

    /**
     *
     * This method is for creating and displaying a desktop window to a specific size when user has selected a flight from the list and has clicked on OK button in MainFrame.
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
