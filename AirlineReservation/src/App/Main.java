package App;


import Database.DatabaseFileIO;
import Database.OutData;
import GUI.LoginFrame;

import java.io.IOException;

/**
 * The Main class is the entry point of the flight booking application.
 * It initializes the necessary components and starts the application by launching the login frame.
 * <p>
 * @since 05/04/2023
 * @author Carlos Figueroa
 * <b>Explanation of important functions:</b>  main() launches the application to the login page.
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>main(String[] args)</i></li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 */
public class Main {

    /**
     * The main method is the entry point of the application.
     * It reads the database txt file, retrieves the database string, and initializes the application's data.
     * Then, it launches the login frame to start the application.
     *
     * @param args command line arguments (not used in this application).
     * @throws IOException if there is an error reading the database file.
     */
    public static void main(String[] args) throws IOException {

        try {
            DatabaseFileIO data = new DatabaseFileIO();
            String databaseStr = data.databaseFileReader();

            OutData outData = new OutData();
            outData.getDatabase(databaseStr);
        } catch(Exception e) {
            System.out.println("Error :" + e.toString());
            System.out.println("OR database is empty");
        }

        LoginFrame loginFrame = new LoginFrame();
    }
}
