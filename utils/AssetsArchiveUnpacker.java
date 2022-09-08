import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

// This code extracts the contents of the file 1.pak
// You MUST cd into the directory containing this script for this to work properly
// An "output" directory will be created in the same folder as this script
// Usage:
//      cd <dictory-containing-this-script>
//      java AssetsArchiveUnpacker.java

public class AssetsArchiveUnpacker {
    public static void main(String[] args) {
        try {
            extractAssetFiles("../assets/1.pak");
        } catch (Exception e) {
            System.err.println("Error: " + e.getLocalizedMessage());
        }
    }

    // Variant of AppCanvas.readAssetsPackage() method
    private static void extractAssetFiles(String filename) throws Exception {
        InputStream inputStream = new FileInputStream(filename);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        short s = dataInputStream.readShort();
        int filesCount = dataInputStream.readShort();
        System.out.println("file: " + filename);
        //System.out.println("s: " + s);
        System.out.println("filesCount: " + filesCount);
        System.out.println("---");

        String[] assetsFileName = new String[filesCount];
        int[] unusedArr = new int[filesCount];
        int[] assetFilesSizes = new int[filesCount];

        for (int j = 0; j < filesCount; ++j) {
            assetsFileName[j] = dataInputStream.readUTF();
            unusedArr[j] = dataInputStream.readInt() + s;
            assetFilesSizes[j] = dataInputStream.readShort();

            // 's' is an offset, probably byte from the start of the file.
            // The first part of the archive is metadata only (name, number of bytes)
            // while the second part is for data only
            // Since this data is not needed tha archive could be smaller

            // (unusedArr[n] - s) => starting "address" of the nth file data
            // System.out.println(j + ": " + assetsFileName[j] + " // u: " + unusedArr[j] + " // u-s: " + (unusedArr[j] - s));
            // System.out.println("bytes: " + assetFilesSizes[j]);
        }
        byte[][] assetsFileBytes = new byte[filesCount][];
        for (int j = 0; j < filesCount; ++j) {
            assetsFileBytes[j] = new byte[assetFilesSizes[j]];
            dataInputStream.readFully(assetsFileBytes[j]);
        }
        dataInputStream.close();

        // ---------------
        //  CREATE FILES
        // ---------------

        // Create directory (if it doesn't exist) in the current terminal location
        String dirName = "output";
        File dir = new File(dirName);
        if(dir.mkdir()) {
            System.out.println("Created directory: " + dir.getPath());
        }

        // Create files
        for(int i=0; i<assetsFileName.length; i++) {
            String filePath = dirName + File.separatorChar + assetsFileName[i];
            createFile(filePath, assetsFileBytes[i]);
            // TODO you probably don't need the createFile wrapper, the method below should auto-create a file if not existent
            //writeBytesToFile(filePath, assetsFileBytes[i]);
        }
    }

    private static void createFile(String filePath, byte[] data) throws Exception {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("Creating file: " + file.getName());
                writeBytesToFile(filePath, data);
            } else {
                /* TODO if the file exists it's safer to delete first intead of overwriting,
                 * what if you change asset package and the new file has less bytes than the original?
                 * Are the exceeding bytes kept or removed? */
                System.out.println("Overwriting file: " + file.getName());
                writeBytesToFile(filePath, data);
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // https://stackoverflow.com/a/4350109
    private static void writeBytesToFile(String filePath, byte[] data) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(data);
            //fos.close();  // Thid is done automatically since you had created the instance of "fos"
                            // inside the try (it's called try-with-resources statement)
        } catch (Exception e) {
            System.out.println("Cannot write to file.");
            e.printStackTrace();
        }
    }
}
