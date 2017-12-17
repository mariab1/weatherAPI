import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InputProvider {

    public String getCityFromUserInput(InputStream stream) {
        Scanner in = new Scanner(stream);
        return in.next();
    }

    public ArrayList<String> getCitiesFromDataStream(BufferedReader reader) throws Exception {
        ArrayList<String> lines = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines;
    }

    public BufferedReader getFileStream(String filename) throws Exception {
        return new BufferedReader(
            new FileReader(filename)
        );
    }
}
