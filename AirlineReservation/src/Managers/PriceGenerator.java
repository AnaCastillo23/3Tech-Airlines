package Managers;

import java.util.Random;

public class PriceGenerator {
    public PriceGenerator() {

    }

    public double getFlightPrice(boolean international) {
        double flightPrice;
        double minPrice;
        double maxPrice;

        Random rand = new Random();

        // International tends to be more expensive than domestic
        if(international) {
            minPrice = 450;
            maxPrice = 1400;
            flightPrice =  minPrice + (maxPrice - minPrice) * rand.nextDouble();
        } else {
            minPrice = 80;
            maxPrice = 450;
            flightPrice =  minPrice + (maxPrice - minPrice) * rand.nextDouble();
        }
        return flightPrice;
    }

    public double getTax(Double flightPrice) {
        double taxPercentage = 0.0;
        double taxPrice = 0.0;

        Random rand = new Random();

        taxPercentage = 0.3 + (.6 - .3)*rand.nextDouble();
        System.out.println(taxPercentage);

        taxPrice = flightPrice * taxPercentage;
        System.out.println(taxPrice);

        return taxPrice;
    }

    public static void main(String[] args) {
        /*
        double price = getFlightPrice(false);
        System.out.println("Domestic");
        double tax = getTaxAndFees(price);
        System.out.println(price + " + " + tax + " = " + (price + tax));

        System.out.println();
        price = getFlightPrice(false);
        System.out.println("Domestic");
        tax = getTaxAndFees(price);
        System.out.println(price + " + " + tax + " = " + (price + tax));

        System.out.println();
        price = getFlightPrice(false);
        System.out.println("Domestic");
        tax = getTaxAndFees(price);
        System.out.println(price + " + " + tax + " = " + (price + tax));

        System.out.println();
        price = getFlightPrice(true);
        System.out.println("International");
        tax = getTaxAndFees(price);
        System.out.println(price + " + " + tax + " = " + (price + tax));

        System.out.println();
        price = getFlightPrice(true);
        System.out.println("International");
        tax = getTaxAndFees(price);
        System.out.println(price + " + " + tax + " = " + (price + tax));

        System.out.println();
        price = getFlightPrice(true);
        System.out.println("International");
        tax = getTaxAndFees(price);
        System.out.println(price + " + " + tax + " = " + (price + tax));
        */
    }
}
