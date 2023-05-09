package App;


import Database.DatabaseFileIO;
import Database.OutData;
import GUI.LoginFrame;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        try {
            DatabaseFileIO dataO = new DatabaseFileIO();
            String databaseStr = dataO.databaseFileReader();

            OutData outData = new OutData();
            outData.getDatabase(databaseStr);
        } catch(Exception e) {
            System.out.println("Error :" + e.toString());
            System.out.println("OR database is empty");
        }

        LoginFrame loginFrame = new LoginFrame();
    }
}
