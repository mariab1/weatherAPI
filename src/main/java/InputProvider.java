import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InputProvider {

    public String getCityFromUserInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Sisesta linn (näit Tallinn,EE): ");
        return in.next();
    }

    public String getCityFromDataFile(String filename) {
        String sCurrentLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            sCurrentLine = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sCurrentLine;
    }
}
