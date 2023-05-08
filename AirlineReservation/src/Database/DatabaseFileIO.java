package Database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseFileIO {

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
