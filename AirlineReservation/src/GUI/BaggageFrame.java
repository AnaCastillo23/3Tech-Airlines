package GUI;

import DataStructures.AccountAccessor;
import Class.Account;
import Class.Baggage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The BaggageFrame is used in conjunction with information recollected from
 * our system and that is stored in our database (in order to be able to assign baggage to each of the added passengers in the reservation).
 * @since 03/27/2023
 * @author Carlos Figueroa (developed code, added documentation comments) and Ana Emily Castillo Perez (added documentation comments).
 * <p>
 * <b>Explanation of important functions:</b> Frame prompts user to increase or decrease the number of baggage per each passenger.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>getBaggagePrice(ArrayList<Baggage> baggageList):</i> used to calculate the price for the total amount of addded baggage.</li>
 * <li><i>updateDisplay():</i> used to update the price for the reservation.</li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 *
 */

public class BaggageFrame extends JFrame {
    private JPanel baggagePanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JScrollPane baggageScroll;
    private JPanel baggageListPanel;

    ButtonClicked clicked;
    AccountAccessor accountAccessor;
    Account loginAccount;
    ArrayList<Baggage> initialBaggageList;
    ArrayList<Baggage> baggageList;
    //ArrayList<Integer> initialAddBaggageCounter;
    //ArrayList<Integer> addBaggageCounter;
    ArrayList<JLabel> numBagsInventory;


    public BaggageFrame() {
        setContentPane(baggagePanel);
        setTitle("Add Baggage");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        clicked = new ButtonClicked();

        baggageList = new ArrayList<Baggage>();
        //addBaggageCounter = new ArrayList<>();
        //initialAddBaggageCounter = new ArrayList<>();

        accountAccessor = new AccountAccessor();
        loginAccount = new Account();
        String username = accountAccessor.getLoginUsername();
        loginAccount = loginAccount.getLoginAccount().get(username);

        String fullname = loginAccount.getFirstName() + " " + loginAccount.getLastName();

        Baggage defaultPassenger = new Baggage(fullname, 1);
        addFreeBaggage(defaultPassenger);

        initialBaggageList = BaggageFrame.copyArrayList1(baggageList);

        confirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                initialBaggageList = BaggageFrame.copyArrayList1(baggageList);// send updated baggage data to review
                //initialAddBaggageCounter = copyArrayList2(addBaggageCounter);

                // send updated baggage data to review
                System.out.println("getTitle().contains(Add Return Baggage) == " + getTitle().contains("Add Return Baggage"));
                ReviewFrame.addBaggageToReview(baggageList, getBaggagePrice(baggageList), getTitle().contains("Add Return Baggage"));

                setVisible(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                baggageList = BaggageFrame.copyArrayList1(initialBaggageList);
                //addBaggageCounter = copyArrayList2(initialAddBaggageCounter); // reset
                setVisible(false);
            }
        });
    }

    /**
     *
     * Method for adding baggage to a list of total baggage for the reservation.
     *
     * @param defaultPassenger defaultPassenger
     *
     */
    public void addFreeBaggage(Baggage defaultPassenger) {
        baggageList.add(defaultPassenger);
        //addBaggageCounter.add(1);
        //initialAddBaggageCounter = copyArrayList2(addBaggageCounter);
        initialBaggageList = BaggageFrame.copyArrayList1(baggageList);
        ReviewFrame.addBaggageToReview(baggageList, 0, false);
        updateDisplay();
    }

    private void addToBaggage(Baggage passenger) {
        baggageList.add(passenger);
        //addBaggageCount.add(1);   <-- not sure how this method will be used
        updateDisplay();
    }

    /**
     *
     * Method for calculating the total price of added baggage and add it to the grand total
     * for the reservation
     *
     * @param baggageList baggageList
     * @return baggagePrice
     *
     */
    private double getBaggagePrice(ArrayList<Baggage> baggageList) {
        double baggagePrice = 0;

        System.out.println();
        System.out.println("---------------------");

        for(int i = 0; i < baggageList.size(); i++) {
            System.out.println(i + ") baggageList : " + baggageList.get(i).getNumBags());
            switch (baggageList.get(i).getNumBags()) {
                case 1:
                    baggagePrice += 0;
                    break;
                case 2:
                    baggagePrice += 35;
                    break;
                case 3:
                    baggagePrice += 85;
                    break;
                case 4:
                    baggagePrice += 185;
                    break;
            }
            System.out.println(i + ") baggage fee(frame): " + baggagePrice);
        }
        return baggagePrice;
    }

    /**
     *
     * Method for updating the grand total of the reservation after baggage has been added.
     *
     */
    private void updateDisplay() {
        numBagsInventory = new ArrayList<>();

        System.out.println(numBagsInventory);

        baggageListPanel = new JPanel(new GridLayout(baggageList.size(),1));
        baggageScroll.setViewportView(baggageListPanel);

        for(int i = 0; i < baggageList.size(); i++) {
            JPanel baggagePanel = new JPanel(new GridLayout(2,6));

            baggagePanel.add(new JLabel(baggageList.get(i).getPassengerName()));
            baggagePanel.add(new JLabel(""));
            baggagePanel.add(new JLabel(""));
            baggagePanel.add(new JLabel("Number of bags:"));
            baggagePanel.add(new JLabel(""));
            baggagePanel.add(new JLabel(""));

            baggagePanel.add(new JLabel(""));
            baggagePanel.add(new JLabel(""));

            JButton decrement = new JButton();
            decrement.setName("decrement" + i);
            decrement.setText("-");
            decrement.addActionListener(clicked);
            baggagePanel.add(decrement);

            //numBagsInventory.add(i);
            JLabel numBagsLabel = new JLabel();
            numBagsLabel.setName("numBagsLabel" + i);
            numBagsLabel.setText(String.valueOf(baggageList.get(i).getNumBags()));
            numBagsInventory.add(numBagsLabel);
            baggagePanel.add(numBagsLabel);

            JButton increment = new JButton();
            increment.setName("increment" + i);
            increment.setText("+");
            increment.addActionListener(clicked);
            baggagePanel.add(increment);

            baggageListPanel.add(baggagePanel);
        }

    }

    private class ButtonClicked extends Component implements ActionListener {
        /**
         *
         * Action listener that identifies which "Book" button is clicked.
         *
         */
        public void actionPerformed(ActionEvent e) {
            if (e.toString().contains("cmd=+")) {
                // increment*   numBagsLabel*
                // 0123456789   0123456789012
                int incrementIndex = Integer.parseInt(((JButton) e.getSource()).getName().substring(9));
                JLabel numBagsLabel = numBagsInventory.get(incrementIndex);
                int numBagsValue = Integer.parseInt(numBagsLabel.getText().substring(0,1));

                if(numBagsValue == 1) {
                    numBagsValue = 2;
                    numBagsLabel.setText("2");
                    //addBaggageCounter.add(incrementIndex, addBaggageCounter.get(incrementIndex) + 1);
                } else if(numBagsValue == 2) {
                    numBagsValue = 3;
                    numBagsLabel.setText("3");
                    //addBaggageCounter.add(incrementIndex, addBaggageCounter.get(incrementIndex) + 1);
                } else if(numBagsValue == 3) {
                    numBagsValue = 4;
                    numBagsLabel.setText("4");
                    //addBaggageCounter.add(incrementIndex, addBaggageCounter.get(incrementIndex) + 1);
                } else {
                    // numBagsValue == 4
                    numBagsValue = 4;
                    JOptionPane.showMessageDialog(this, "Baggage limit reached!", "Baggage Error!", JOptionPane.ERROR_MESSAGE);
                }

                Baggage incrementedBaggage = baggageList.get(incrementIndex);
                incrementedBaggage.setNumBags(numBagsValue);
                Baggage updateBaggage = baggageList.get(incrementIndex);
                updateBaggage.setNumBags(numBagsValue);
                baggageList.remove(incrementIndex);
                baggageList.add(incrementIndex, updateBaggage);

            } else if(e.toString().contains("cmd=-")) {
                // decrement*
                // 0123456789
                int decrementIndex = Integer.parseInt(((JButton) e.getSource()).getName().substring(9));
                JLabel numBagsLabel = numBagsInventory.get(decrementIndex);
                System.out.println(numBagsLabel.getName());
                int numBagsValue = Integer.parseInt(numBagsLabel.getText().substring(0,1));

                if(numBagsValue == 4) {
                    numBagsValue = 3;
                    numBagsLabel.setText("3");
                    //addBaggageCounter.add(decrementIndex, addBaggageCounter.get(decrementIndex) - 1);
                } else if(numBagsValue == 3) {
                    numBagsValue = 2;
                    numBagsLabel.setText("2");
                    //addBaggageCounter.add(decrementIndex, addBaggageCounter.get(decrementIndex) - 1);
                } else if(numBagsValue == 2) {
                    numBagsValue = 1;
                    numBagsLabel.setText("1");
                    //addBaggageCounter.add(decrementIndex, addBaggageCounter.get(decrementIndex) - 1);
                } else {
                    // numBagsValue == 1
                    numBagsValue = 1;
                    JOptionPane.showMessageDialog(this, "First Check-in Baggage is free!", "Baggage Error!", JOptionPane.ERROR_MESSAGE);
                }

                Baggage decrementedBaggage = baggageList.get(decrementIndex);
                decrementedBaggage.setNumBags(numBagsValue);
                Baggage updateBaggage = baggageList.get(decrementIndex);
                updateBaggage.setNumBags(numBagsValue);
                baggageList.remove(decrementIndex);
                baggageList.add(decrementIndex, updateBaggage);

            } else {
                System.out.println("ButtonClicked.clicker ERROR");
            }
        }
    }

    //Unused method
    public static boolean[][] copyArray(boolean[][] src) {
        if (src == null) {
            return null;
        }
        boolean[][] copy = new boolean[src.length][];
        for (int i = 0; i < src.length; i++) {
            copy[i] = new boolean[src[i].length];
            System.arraycopy(src[i], 0, copy[i], 0, src[i].length);
        }
        return copy;
    }

    /**
     *
     * Method for cloning the baggage array into a new array list.
     *
     * @param oldList oldList
     * @return clonedList
     *
     */
    public static ArrayList<Baggage> copyArrayList1(ArrayList<Baggage> oldList) {
        ArrayList<Baggage> clonedList = new ArrayList<Baggage>(oldList.size());
        for (Baggage baggage: oldList) {
            clonedList.add(baggage);
        }
        return clonedList;
    }

    //Unused method
    public static ArrayList<Integer> copyArrayList2(ArrayList<Integer> oldList) {
        ArrayList<Integer> clonedList = new ArrayList<Integer>(oldList.size());
        for (Integer seatNum : oldList) {
            clonedList.add(seatNum);
        }
        return clonedList;
    }
}
