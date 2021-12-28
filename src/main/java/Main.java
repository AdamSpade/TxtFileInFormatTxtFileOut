import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/* Main class to run the project */
public class Main {

    /* Main method used as entry point */
    public static void main(String[] args) {

        /* opens an InputStream to the data file path */
        InputStream inputStream = Main.class.getResourceAsStream(String.valueOf(DataFileHandler.getInput()));

        /* creates an InputStreamReader for the input stream */
        InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

        /* opens a BufferedReader to read line-by-line from the file */
        BufferedReader bufferedReader = new BufferedReader(reader);

        /* uses the 'createPersonObjFromData' method in Utility class to read the lines
        from the input file and assign each new Person object to the 'people' ArrayList */
        List<Person> people = Utility.createPersonObjFromData(bufferedReader);

        /* PrintWriter used to write the expected output. Using methods from the Utility class,
        the output data is printed to a new text file */
        try{

            PrintWriter printWriter = new PrintWriter(DataFileHandler.getOutput());
            Utility.addHouseholdOutputToPrintWriter(Utility.groupByHousehold(people), printWriter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}