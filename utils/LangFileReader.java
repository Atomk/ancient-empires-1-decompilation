import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

// This code reads the game file "lang.dat" and lists its contents in console

public class LangFileReader {
    public static void main(String[] args) {
        try {
            // This works only if you run "java LangFileReader.java"
            // while terminal is opened in this script's folder.
            // (path is relative to terminal current folder, not script folder)
            // TODO pass lang file absolute path as argument from CLI just to be sure?
            readLangFIle("../assets/lang.dat");
        } catch (Exception e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }

    // Variant of AppCanvas.loadAppStrings() method
    public static void readLangFIle(String filepath) throws Exception {
        InputStream inputStream = new FileInputStream(filepath);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        
        int stringsCount = dataInputStream.readInt();
        System.out.println("strings count: " + stringsCount);

        String[] stringsPartA = new String[63];
        String[] stringsPartB = new String[stringsCount - 63];

        for (int n = 0; n < stringsPartA.length; ++n) {
            stringsPartA[n] = dataInputStream.readUTF();
            // Adds quotation marks around the string to show if there are spaces at text start/end
            System.out.println(n + ":\t'" + stringsPartA[n] + "'");
        }
        for (int n = 0; n < stringsPartB.length; ++n) {
            stringsPartB[n] = dataInputStream.readUTF();
            System.out.println((n + 63) + ":\t'" + stringsPartB[n] + "'");
        }
        dataInputStream.close();
    }
}
