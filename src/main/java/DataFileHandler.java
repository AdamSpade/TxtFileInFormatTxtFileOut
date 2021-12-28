import java.io.File;

/* Class containing the directory paths for input data and output data files */
public class DataFileHandler {

    private static File input = new File ("\\InputData.txt");
    private static String output = ".\\src\\main\\resources\\Output.txt";

    public static File getInput() {
        return input;
    }

    public static String getOutput() {
        return output;
    }
}
