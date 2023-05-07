package Database;

import java.io.FileWriter;
import java.io.IOException;

public class DatabaseFileIO {

    public void databaseFileReader(String databaseStr) throws IOException {

        // attach a file to FileWriter
        FileWriter fw = new FileWriter("C:\\Users\\Carlos\\IdeaProjects\\3Tech-Airlines\\AirlineReservation\\src\\Database\\databaseFile.txt");

        // read character wise from string and write
        // into FileWriter
        for (int i = 0; i < databaseStr.length(); i++)
            fw.write(databaseStr.charAt(i));

        System.out.println("Writing successful");
        //close the file
        fw.close();

    }
}
