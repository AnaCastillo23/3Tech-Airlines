package GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SeatFrame extends JFrame {
    private JPanel seatsPanel;
    private JButton button1;
    private JButton button2;
    private JPanel walkwayPanel;
    private JPanel leftSeatingPanel;
    private JPanel rightSeatingPanel;
    private JScrollPane seatingScrollPane;
    private JPanel mainSeatingPanel;
    private JButton seatButton;

    ButtonClicked clicked = new ButtonClicked();

    public SeatFrame() {
        setContentPane(seatsPanel);
        setTitle("Seats");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Open right when ReviewFrame opens
        // only put code here for when ReviewFrame open
            // generate 2D seating array with random number of rows


    }


    public String generateSeatPrice(String seatingType) {
        // 30 - 60 rows
        String seatPrice = null;

        Random rand = new Random();

        System.out.println(seatingType);
        if(seatingType.equals("first-class")) {
            // first class $800 - $1100 upgrade
            seatPrice = Double.toString(800.0 + (300.0) * rand.nextDouble());
        } else if (seatingType.equals("business-class")) {
            // business class $400 - $799 upgrade
            seatPrice = Double.toString(400.0 + (399.0) * rand.nextDouble());
        } else if(seatingType.equals("first-row-economy-class")){
            // "economy-class" < $250
            seatPrice = Double.toString( 50.0 + (250.0) * rand.nextDouble());
        }
/*
        System.out.println(seatingType.equals("first-class"));
        System.out.println(seatingType.equals("business-class"));
        System.out.println(seatingType.equals("first-row-economy-class"));
        System.out.println(seatPrice);*/
        return seatPrice;
    }
    public void openSeatingMap(int numRows) {
        setVisible(true);

        mainSeatingPanel = new JPanel(new GridLayout(1, 3));
        seatingScrollPane.setViewportView(mainSeatingPanel);

        String[][] seatingArray = new String[numRows][6];
        // seatingArray = [[1A,1B,1C,  1D,1E,1F],   [2A,2B,2C,  2D,2E,2F],   [3A,3B,3C,  3D,3E,3F],....,[NA,NB,NC,  ND,NE,NF]]
        //                0: 0  1  2    3  4  5    1: 0  1  2    3  4  5    2: 0  1  2    3  4  5      N: 0  1  2    3  4  5

        leftSeatingPanel = new JPanel(new GridLayout(numRows, 3));
        walkwayPanel = new JPanel(new GridLayout(numRows * 3, 3));
        rightSeatingPanel = new JPanel(new GridLayout(numRows, 3));

        boolean firstEcon = false;
        String rowPrice = null;
        for(int i = 0; i< numRows; i++) {
            if(i == 0) {
                rowPrice = generateSeatPrice("first-class");
            } else if(i == 3) {
                rowPrice = generateSeatPrice("business-class");
            } else if(((double) i / (numRows - 3)) >= 0.1818 && !firstEcon) {
                rowPrice = generateSeatPrice("first-row-economy-class");
                firstEcon = true;
            } else if(((double) i / (numRows - 3)) >= 0.1818 && firstEcon) {
                rowPrice = Double.toString(Double.parseDouble(rowPrice) - (Double.parseDouble(rowPrice) * 0.05));
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
                if (j < 3){
                    // left side
                    if (j != 0 && j % 2 == 0 && i < 3) {
                        // first class - 2 seats per row
                        leftSeatingPanel.add(new JLabel(""));
                    } else {
                        seatButton.setName(new StringBuilder("seat").append(i).append(k).toString());
                        seatButton.setText("$" + String.valueOf(Math.round(Double.parseDouble(rowPrice))));
                        seatButton.addActionListener(clicked); // <= private class ButtonClicked implements ActionListener
                        leftSeatingPanel.add(seatButton);
                    }
                } else{
                    // right side
                    if (j % 3 == 0 && i < 3) {
                        // first class - 2 seats per row
                        rightSeatingPanel.add(new JLabel(""));
                    } else {
                        seatButton.setName(new StringBuilder("seat").append(i).append(k).toString());
                        seatButton.setText("$" + String.valueOf(Math.round(Double.parseDouble(rowPrice))));
                        seatButton.addActionListener(clicked); // <= private class ButtonClicked implements ActionListener
                        rightSeatingPanel.add(seatButton);
                    }
                }
            }
        }

        // walkwayPanel
        int j = 0;
        int k = 1;
        for(int i = 0; i < numRows*3; i++) {
            JPanel emptyPanel = new JPanel();
            if(j != 0 && j % 3 == 0) {
                emptyPanel.add(new JLabel(String.valueOf(k)));
                k += 1;
            }
            walkwayPanel.add(emptyPanel);
            j += 1;
        }

        mainSeatingPanel.add(leftSeatingPanel);
        mainSeatingPanel.add(walkwayPanel);
        mainSeatingPanel.add(rightSeatingPanel);

        setVisible(false);
        setVisible(true);
    }


    private class ButtonClicked implements ActionListener {
        /**
         * Action listener that identifies which "Book" button is clicked
         */
        public void actionPerformed(ActionEvent e) {

        }
    }

    public static void main(String[] args) {
        //SeatFrame seatFrame = new SeatFrame();
        //seatFrame.openSeatingMap(30);
    }
}
