import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OutputProvider {
    public void toFile(String filename, ArrayList<String> rows) {
        BufferedWriter output = null;
        try {
            File file = new File(filename);
            output = new BufferedWriter(new FileWriter(file));
            for (String row: rows) {
                output.write(row + "\n");
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}