package Database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The DatabaseFileIO class provides methods for reading from and writing to the database file.
 * <p>
 * @since 05/04/2023
 * @author Carlos Figueroa
 * <b>Explanation of important functions:</b>  Allows the application to store and retrieve the database information from a text file..
 * <p>
 * <b>Important data structure in class/important methods in class:</b>
 * <ul>
 * <li><i>databaseFileWriter(String databaseStr)</i></li>
 * <li><i>databaseFileReader()</i></li>
 * </ul>
 * <p>
 *
 * <b>Any algorithms used?</b> Not at the moment.
 */
public class DatabaseFileIO {

    /**
     * Writes the provided database string to the database file.
     *
     * @param databaseStr the database string to be written.
     * @throws IOException if there is an error while writing to the file.
     */
    public void databaseFileWriter(String databaseStr) throws IOException {

        // attach a file to FileWriter
        FileWriter fw = new FileWriter("src/Database/databaseFile.txt");

        // read character wise from string and write
        // into FileWriter
        for (int i = 0; i < databaseStr.length(); i++)
            fw.write(databaseStr.charAt(i));

        System.out.println("Writing successful");
        //close the file
        fw.close();

    }

    /**
     * Reads the database string from the database file.
     *
     * @return the database string read from the file.
     * @throws IOException if there is an error while reading the file.
     */
    public String databaseFileReader() throws IOException {
        String databaseStr = null;

        int ch;

        // check if File exists or not
        FileReader fr = null;
        try
        {
            fr = new FileReader("src/Database/databaseFile.txt");
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }

        // read from FileReader till the end of file
        StringBuilder databaseStrBuilder = new StringBuilder();
        while ((ch=fr.read())!=-1) {
            System.out.print((char) ch);
            databaseStrBuilder.append((char) ch);
        }
        // close the file
        fr.close();

        databaseStr = databaseStrBuilder.toString();

        return databaseStr;
    }
}
