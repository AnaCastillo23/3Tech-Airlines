package GUI;

import DataStructures.AccountAccessor;
import DataStructures.SeatChange;

import Class.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class SeatFrame extends JFrame {
    private JPanel seatsPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel walkwayPanel;
    private JPanel leftSeatingPanel;
    private JPanel rightSeatingPanel;
    private JScrollPane seatingScrollPane;
    private JPanel mainSeatingPanel;
    private JButton seatButton;
    private JButton[][] seatButtonList;
    boolean[][] initialSeatingArray;   // seatingArray = initialSeatingArray if cancel
    boolean[][] seatingArray;
    double[][] seatPrices;
    ArrayList<String> reservedSeats;

    AccountAccessor accountAccessor;
    Account loginAccount;
    ButtonClicked clicked = new ButtonClicked();
    int numRows;
    int partySize;
    boolean roundTrip;
    double total;


    public SeatFrame(boolean roundTrip) {
        setContentPane(seatsPanel);
        setTitle("Seating Map");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.roundTrip = roundTrip;

        accountAccessor = new AccountAccessor();
        loginAccount = new Account();
        String username = accountAccessor.getLoginUsername();
        loginAccount = loginAccount.getLoginAccount().get(username);

        // Open right when ReviewFrame opens (invisible until seat button is clicked in review)
        // only put code here for when ReviewFrame open
            // generate 2D seating array with random number of rows

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!roundTrip) {
                    // if departureFlight
                    if (reservedSeats.size() < partySize) {
                        JOptionPane.showMessageDialog(null, "Please select seats for all party members.", "Confirmation Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // confirm
                        initialSeatingArray = seatingArray;

                        // add reservedSeats list to flight instance under login account
                        SeatChange seatChange = new SeatChange(reservedSeats, total, roundTrip);


                        // MAYBE USE STATIC "ReviewFrame.variable" INSTEAD OF SeatChange datastructure
                        //ReviewFrame.departureSeats = reservedSeats;
                        // static method updates original instance using SeatChange()
                        ReviewFrame.updateSeatChanges(false);

                        setVisible(false);
                    }
                } else {
                    // roundtrip features will be added later
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seatingArray = initialSeatingArray;
                total = 0;
                setVisible(false);
            }
        });
    }


    public void updatePartySize(int partySize) {
        this.partySize = partySize;
        getAssignedSeats(this.partySize);
    }

    public ArrayList<String> getAssignedSeats(int partySize) {
        // these seats are free of charge...only pay when changing seats
        int rndRow = 0;
        int rndCol = 0;
        int counter = 0;
        do {
            rndRow = new Random().nextInt(numRows);
            rndCol = new Random().nextInt(6);
            if(seatingArray[rndRow][rndCol]) {
                System.out.println("clicked Button");
                clicked.actionPerformed(new ActionEvent(seatButtonList[rndRow][rndCol], ActionEvent.ACTION_FIRST, "youractioncommand"));
                counter += 1;
            }
        } while (counter < partySize);

        // return ArrayList of assigned seats!!!!!!!!!!!!!!!!!!!!
        return reservedSeats;
    }

    public Double generateSeatPrice(String seatingType) {
        // 30 - 60 rows
        double seatPrice = 0.0;

        Random rand = new Random();

        System.out.println(seatingType);
        if(seatingType.equals("first-class")) {
            // first class $800 - $1100 upgrade
            seatPrice = 800.0 + (300.0) * rand.nextDouble();
        } else if (seatingType.equals("business-class")) {
            // business class $400 - $799 upgrade
            seatPrice = 400.0 + (399.0) * rand.nextDouble();
        } else if(seatingType.equals("economy-class")){
            // "economy-class" < $250
            seatPrice = 50.0 + (250.0) * rand.nextDouble();
        }
/*
        System.out.println(seatingType.equals("first-class"));
        System.out.println(seatingType.equals("business-class"));
        System.out.println(seatingType.equals("first-row-economy-class"));
        System.out.println(seatPrice);*/
        return seatPrice;
    }
    public void generateSeatingMap(int partySize, int numRows) {
        reservedSeats = new ArrayList<>();
        this.numRows = numRows;
        this.partySize = partySize;


        mainSeatingPanel = new JPanel(new GridLayout(1, 3));
        seatingScrollPane.setViewportView(mainSeatingPanel);

        seatPrices = new double[numRows][6];
        seatButtonList = new JButton[numRows][6];
        seatingArray = new boolean[numRows][6];
        // seatingArray = [[1A,1B,1C,  1D,1E,1F],   [2A,2B,2C,  2D,2E,2F],   [3A,3B,3C,  3D,3E,3F],....,[NA,NB,NC,  ND,NE,NF]]
        //                0: 0  1  2    3  4  5    1: 0  1  2    3  4  5    2: 0  1  2    3  4  5      N: 0  1  2    3  4  5

        leftSeatingPanel = new JPanel(new GridLayout(numRows, 3));
        walkwayPanel = new JPanel(new GridLayout(numRows * 3 + 1, 3));
        rightSeatingPanel = new JPanel(new GridLayout(numRows, 3));

        boolean firstEcon = false;
        double rowPrice = 0.0;
        String cabinClass = null;
        for(int i = 0; i< numRows; i++) {
            if(i == 0) {
                cabinClass = "first-class";
                rowPrice = generateSeatPrice("first-class");
            } else if(i == 3) {
                cabinClass = "business-class";
                rowPrice = generateSeatPrice(cabinClass);
            } else if(((double) i / (numRows - 3)) >= 0.1818 && !firstEcon) {
                cabinClass = "economy-class";
                rowPrice = generateSeatPrice(cabinClass);
                firstEcon = true;
            } else if(((double) i / (numRows - 3)) >= 0.1818 && firstEcon) {
                rowPrice = rowPrice - (rowPrice * 0.05);
            }

            for(int j = 0; j < 6; j++) {

                char k = 0;
                switch(j) {
                    case 0:
                        k = 'A';
                        break;
                    case 1:
                        k = 'B';
                        break;
                    case 2:
                        k = 'C';
                        break;
                    case 3:
                        k = 'D';
                        break;
                    case 4:
                        k = 'E';
                        break;
                    case 5:
                        k = 'F';
                        break;
                }

                seatButton = new JButton();

                Random rand = new Random();
                int openSeat = rand.nextInt(2);
                if(openSeat == 1) {
                    seatingArray[i][j] = true;
                    seatButton.setText("$" + Math.round(rowPrice));
                } else {
                    seatingArray[i][j] = false;
                    seatButton.setText("X");
                }

                if (j < 3){
                    // left side
                    if (j != 0 && j % 2 == 0 && i < 3) {
                        // first class - 2 seats per row
                        leftSeatingPanel.add(new JLabel(""));
                    } else {
                        seatButton.setName("seat" + i + k);
                        seatButton.addActionListener(clicked); // <= private class ButtonClicked implements ActionListener
                        leftSeatingPanel.add(seatButton);
                        seatButtonList[i][j] = seatButton;
                    }
                } else{
                    // right side
                    if (j % 3 == 0 && i < 3) {
                        // first class - 2 seats per row
                        rightSeatingPanel.add(new JLabel(""));
                    } else {
                        seatButton.setName("seat" + i + k);
                        seatButton.addActionListener(clicked); // <= private class ButtonClicked implements ActionListener
                        rightSeatingPanel.add(seatButton);
                        seatButtonList[i][j] = seatButton;
                    }
                }
                seatPrices[i][j] = rowPrice;
            }
        }

        // walkwayPanel
        int j = 0;
        int k = 1;
        JPanel initialPanel = new JPanel();
        walkwayPanel.add(initialPanel);
        for(int i = 0; i < numRows*3; i++) {
            JPanel emptyPanel = new JPanel();
            if(j % 3 == 0) {
                emptyPanel.add(new JLabel(String.valueOf(k)));
                k += 1;
            }
            walkwayPanel.add(emptyPanel);
            j += 1;
        }

        mainSeatingPanel.add(leftSeatingPanel);
        mainSeatingPanel.add(walkwayPanel);
        mainSeatingPanel.add(rightSeatingPanel);


        initialSeatingArray = seatingArray;
    }


    private class ButtonClicked extends Component implements ActionListener {
        /**
         * Action listener that identifies which "Book" button is clicked
         */
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getSource());
            JButton clickedSeatButton = (JButton) e.getSource();
            String seatName = clickedSeatButton.getName();

            String seatNumber = seatName.substring(4);
            System.out.println(e.getActionCommand());
            System.out.println(seatNumber);

            int row;
            char column;
            if(seatNumber.length() > 2) {
                // double digit rows
                row = Integer.parseInt(seatNumber.substring(0,2));
                column = seatNumber.charAt(2);
            } else {
                // single digit rows
                row = Integer.parseInt(seatNumber.substring(0,1));
                column = seatNumber.charAt(1);
            }
            System.out.println(row + " " + column);

            int j = 0;
            switch(column) {
                case 'A':
                    j = 0;
                    break;
                case 'B':
                    j = 1;
                    break;
                case 'C':
                    j = 2;
                    break;
                case 'D':
                    j = 3;
                    break;
                case 'E':
                    j = 4;
                    break;
                case 'F':
                    j = 5;
                    break;
            }
            System.out.println(j);
            System.out.println();

            if (seatingArray[row][j] && reservedSeats.size() < partySize) {
                // available seat
                reservedSeats.add(seatNumber);
                seatingArray[row][j] = false;
                clickedSeatButton.setBorder(BorderFactory.createLineBorder(Color.yellow,3));
                total += seatPrices[row][j];
            } else {
                if(reservedSeats.contains(seatNumber)) {
                    // deselect seat
                    reservedSeats.remove(seatNumber);
                    seatingArray[row][j] = true;
                    System.out.println("hello??");
                    clickedSeatButton.setBorder(new JButton().getBorder());
                    total -= seatPrices[row][j];
                } else {
                    if (reservedSeats.size() >= partySize) {
                        JOptionPane.showMessageDialog(this, "Number of selected seats exceeds party size. Deselect seat to reserve seat", "Seat Selection Limit Reached", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Seat already taken. Please select another seat.", "Invalid Seat Selection", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            //reserveSeat(clickedSeatButton, seatNumber, row, column);
        }
    }


    public static void main(String[] args) {
        SeatFrame seatFrame = new SeatFrame(false);
        seatFrame.generateSeatingMap(1, 30);
        seatFrame.setVisible(true);
    }
}
