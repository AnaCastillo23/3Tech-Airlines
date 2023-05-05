package GUI;

import DataStructures.AccountAccessor;
import Class.Account;
import Class.Baggage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BaggageFrame extends JFrame {
    private JPanel baggagePanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JScrollPane baggageScroll;
    private JPanel baggageListPanel;

    ButtonClicked clicked;
    AccountAccessor accountAccessor;
    Account loginAccount;
    ArrayList<Baggage> baggageList;
    int addBaggageCount;
    ArrayList<JLabel> numBagsInventory;

    public BaggageFrame() {
        setContentPane(baggagePanel);
        setTitle("Add Baggage");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        clicked = new ButtonClicked();

        baggageList = new ArrayList<Baggage>();

        accountAccessor = new AccountAccessor();
        loginAccount = new Account();
        String username = accountAccessor.getLoginUsername();
        loginAccount = loginAccount.getLoginAccount().get(username);

        String fullname = loginAccount.getFirstName() + " " + loginAccount.getLastName();

        Baggage defaultPassenger = new Baggage(fullname, 1);
        addFreeBaggage(defaultPassenger);

        confirmButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

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

            }
        });
    }

    public void addFreeBaggage(Baggage defaultPassenger) {
        baggageList.add(defaultPassenger);
        updateDisplay();
    }

    private void addToBaggage(Baggage passenger) {
        baggageList.add(passenger);
        addBaggageCount++;
        updateDisplay();
    }

    private double getBaggagePrice(int numBags) {
        double baggagePrice = 0;

        switch(numBags) {
            case 1:
               baggagePrice = 0;
               break;
            case 2:
                baggagePrice = 35;
                break;
            case 3:
                baggagePrice = 50;
                break;
            case 4:
                baggagePrice = 100;
                break;
        }
        return baggagePrice;
    }

    private void updateDisplay() {
        numBagsInventory = new ArrayList<>();

        System.out.println(numBagsInventory);

        baggageListPanel = new JPanel(new GridLayout(baggageList.size(),1));
        baggageScroll.setViewportView(baggageListPanel);

        for(int i = 0; i < baggageList.size(); i++) {
            JPanel baggagePanel = new JPanel(new GridLayout(2,6));

            baggagePanel.add(new JLabel(baggageList.get(i).getFullName()));
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
         * Action listener that identifies which "Book" button is clicked
         */
        public void actionPerformed(ActionEvent e) {
            if (e.toString().contains("cmd=+")) {
                // increment*   numBagsLabel*
                // 0123456789   0123456789012
                int incrementIndex = Integer.parseInt(((JButton) e.getSource()).getName().substring(9));
                JLabel numBagsLabel = numBagsInventory.get(incrementIndex);
                int numBagsValue = Integer.parseInt(numBagsLabel.getText().substring(12));

                if(numBagsValue == 1) {
                    numBagsValue = 2;
                    numBagsLabel.setText("2");
                } else if(numBagsValue == 2) {
                    numBagsValue = 3;
                    numBagsLabel.setText("3");
                } else if(numBagsValue == 3) {
                    numBagsValue = 4;
                    numBagsLabel.setText("4");
                } else {
                    // numBagsValue == 4
                    JOptionPane.showMessageDialog(this, "Baggage limit reached!", "Baggage Error!", JOptionPane.ERROR_MESSAGE);
                }

            } else if(e.toString().contains("cmd=-")) {
                // decrement*
                // 0123456789
                int decrementIndex = Integer.parseInt(((JButton) e.getSource()).getName().substring(9));
                JLabel numBagsLabel = numBagsInventory.get(decrementIndex);
                int numBagsValue = Integer.parseInt(numBagsLabel.getText().substring(12));

                if(numBagsValue == 4) {
                    numBagsValue = 3;
                    numBagsLabel.setText("3");
                } else if(numBagsValue == 3) {
                    numBagsValue = 2;
                    numBagsLabel.setText("2");
                } else if(numBagsValue == 2) {
                    numBagsValue = 1;
                    numBagsLabel.setText("1");
                } else {
                    // numBagsValue == 1
                    JOptionPane.showMessageDialog(this, "First Check-in Baggage is free!", "Baggage Error!", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                System.out.println("ButtonClicked.clicker ERROR");
            }
        }
    }


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

    public static ArrayList<String> copyArrayList(ArrayList<String> oldList) {
        ArrayList<String> clonedList = new ArrayList<String>(oldList.size());
        for (String seatNum: oldList) {
            clonedList.add(new String(seatNum));
        }
        return clonedList;
    }
}
