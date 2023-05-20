package GUI;

import DataStructures.AccountAccessor;
import Class.Account;
import Class.Reservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageReservationFrame extends JFrame {
    private JButton backButton;
    private JPanel manageReservationPanel;
    private JPanel reservationListPanel;
    private JScrollPane reservationListScroll;
    private JPanel reservationPanel;

    /**
     * The ManageReservationFrame is used in conjunction with information recollected from
     * our system and that is stored in our database (in order to allow user to manage their reservations).
     * @since 03/27/2023
     * @author Carlos Figueroa (developed code, added documentation comments) and Ana Emily Castillo Perez (added documentation comments).
     * <p>
     * <b>Explanation of important functions:</b> Frame allows user to manage their recently booked reservations.
     * <p>
     * <b>Important data structure in class/important methods in class:</b>
     * <ul>
     * <li><i>generateReservations():</i> used to display the recent reservations in an user's account.</li>
     * </ul>
     * <p>
     *
     * <b>Any algorithms used?</b> Not at the moment.
     *
     */

    ButtonClicked clicked = new ButtonClicked();

    public ManageReservationFrame() {
        setContentPane(manageReservationPanel);
        setTitle("Manage Reservations");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        generateReservations();

        backButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs. Used to code the back button, taking user to their dashboard.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardFrame dashboard = new DashboardFrame();

                setVisible(false);
                dashboard.setVisible(true);
            }
        });
    }

    /**
     *
     * For displaying user's reservations.
     */
    private void generateReservations() {
        Account loginAccount = new Account();
        AccountAccessor accountAccessor = new AccountAccessor();
        ArrayList<Reservation> reservationsList = new ArrayList<Reservation>();

        String username = accountAccessor.getLoginUsername();
        loginAccount = loginAccount.getLoginAccount().get(username);

        reservationsList = loginAccount.getReservationList();

        reservationListPanel = new JPanel();
        reservationListPanel.setLayout(new GridLayout(reservationsList.size(), 1, 0, 10));
        reservationListScroll.setViewportView(reservationListPanel);

        for (int i = 0; i < reservationsList.size(); i++) {
            try {
                reservationPanel = new JPanel();
                reservationPanel.setLayout(new GridLayout(2, 2));
                // reservationPanels fill vertically - looks ugly w/ few reservations, looks good w/ alot of reservations

                Reservation reservation = reservationsList.get(i);
                if(reservation.isRoundTrip()) {
                    reservationPanel.add(new JLabel(reservation.getDepartureFlight().getDepartureAirport().getAirportCode()
                    + " <===> " + reservation.getDepartureFlight().getArrivalAirport().getAirportCode()));
                } else {
                    reservationPanel.add(new JLabel(reservation.getDepartureFlight().getDepartureAirport().getAirportCode()
                            + " ====> " + reservation.getDepartureFlight().getArrivalAirport().getAirportCode()));
                }
                reservationPanel.add(new JLabel(""));
                // maybe include city names

                JButton viewButton = new JButton();
                viewButton.setName(new StringBuilder("viewButton").append(i).toString());
                viewButton.setText("View");
                viewButton.addActionListener(clicked);
                reservationPanel.add(viewButton);

                JButton cancelButton = new JButton();
                cancelButton.setName(new StringBuilder("cancelButton").append(i).toString());
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(clicked);
                reservationPanel.add(cancelButton);

                reservationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                reservationListPanel.add(reservationPanel);

            }catch(Exception e) {
                System.out.println("Error");
            }
        }
    }


    /**
     *
     * ActionListener Class specifically designed for auto-generated view and cancel buttons.
     *
     */
    private class ButtonClicked implements ActionListener {
        /**
         * Action listener that identifies whether View or Cancel are clicked
         */
        public void actionPerformed(ActionEvent e) {
            if (e.toString().contains("cmd=View")) {
                // pull up itinerary                        //0123456789012
            } else if(e.toString().contains("cmd=Cancel")) {//cancelButton1
                Account loginAccount = new Account();
                AccountAccessor accountAccessor = new AccountAccessor();
                String username = accountAccessor.getLoginUsername();
                loginAccount = loginAccount.getLoginAccount().get(username);

                int buttonIndex = Integer.parseInt(((JButton) e.getSource()).getName().substring(12));
                System.out.println("Cancel" + buttonIndex);

                loginAccount.getReservationList().remove(loginAccount.getReservationList().get(buttonIndex));
                generateReservations();
            } else {
                // ERROR
            }
        }
    }
}
