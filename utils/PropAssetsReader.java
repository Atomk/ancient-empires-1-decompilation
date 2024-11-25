import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

// -- modified version od SpriteAssetReader --
// This code reads the content of all .prop assets in a specific folder
// These assets are part of the contents of the "1.pak" archive
// It was specifically needed to figure out what data the game loads from the file "tiles0.prop".

// HOW TO USE
// 1. Open the folder containing this script
// 2. Create a folder called "props"
// TODO take a path as input instead, there is only one prop file
// 3. Move the .prop assets into the sprites folder
// 4. Run
// Note: You MUST cd into the directory containing this script for this to work properly

// RUN
//      cd <dictory-containing-this-script>
//      java PropAssetsReader.java

public class PropAssetsReader {
    public static void main(String[] args) {
        String dirName = "props";
        File dir = new File(dirName);

        if(!dir.exists()) {
            System.out.println("Error: cannot find directory '" + dirName + "'");
        }

        String[] dirFiles = dir.list();

        for(String filename : dirFiles) {
            if(filename.endsWith(".prop")) {
                String filePath = dirName + File.separatorChar + filename;

                try {
                    readAsset(filePath);
                }
                catch(Exception e) {
                    System.err.println("Error: " + e.getLocalizedMessage());
                }
            }
        }
    }

    // Data read statements are copied from the original code that reads "tiles0.prop".
    private static void readAsset(String path) throws Exception {
        InputStream inputStream = new FileInputStream(path);
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        short numBytes = dataInputStream.readShort();
        short unused = dataInputStream.readShort();   // Unused in original code
        byte[] bytes = new byte[numBytes];
        for (short i = 0; i < numBytes; i++) {
            bytes[i] = dataInputStream.readByte();
        }

        // Original code did not close streams...
        dataInputStream.close();
        inputStream.close();

        int lastSeparatorIndex = path.lastIndexOf(File.separatorChar);
        System.out.println(path.substring(lastSeparatorIndex + 1));
        System.out.println("  bytes count:\t" + numBytes);
        System.out.println("  unused:\t" + unused);
        for (short i = 0; i < numBytes; i++) {
            System.out.println("  byte["+i+"]: " + (i < 10 ? " " : "") + bytes[i]);
        }
    }
}

/*

***** OUTPUT *****

In bytes array, the key/index is the index of the tile in the terrain tileset created by the game,
while the value is the terrain type of that tile, which is used to calculate unit movement and defence bonus.

tiles0.prop
  bytes count:  35
  unused:       1
  byte[0]:  4     // tiles0_00.png -> MOUNTAIN (only time this value appears)
  byte[1]:  2     // tiles0_01.png -> FOREST (only time this value appears)
  byte[2]:  3     // tiles0_02.png -> HILL (only time this value appears)
  byte[3]:  1     // tiles0_03.png -> GRASS (all the tiles below are grass variations with borders)
  byte[4]:  1     // tiles0_04.png ->
  byte[5]:  1     // tiles0_05.png ->
  byte[6]:  1     // tiles0_06.png ->
  byte[7]:  1     // tiles0_07.png ->
  byte[8]:  1     // tiles0_08.png ->
  byte[9]:  1     // tiles0_09.png ->
  byte[10]: 1     // tiles0_10.png ->
  byte[11]: 1     // tiles0_11.png ->
  byte[12]: 1     // tiles0_12.png ->
  byte[13]: 1     // tiles0_13.png ->
  byte[14]: 1     // tiles0_14.png ->
  byte[15]: 1     // tiles0_15.png ->
  byte[16]: 1     // tiles0_16.png ->
  byte[17]: 1     // tiles0_17.png ->
  byte[18]: 0     // tiles0_18.png -> ROAD
  byte[19]: 6     // tiles0_19.png -> BRIDGE (horizontal)
  byte[20]: 6     // tiles0_20.png -> BRIDGE (vertical)
  byte[21]: 5     // tiles0_21.png -> WATER (frame 1)
  byte[22]: 5     // tiles0_22.png -> WATER (frame 2)
  // The tiles below are generated via code, based on the "buildings.png" tileset
  byte[23]: 7     // TOWN (blue)
  byte[24]: 8     // CASTLE (blue)
  byte[25]: 8     // CASTLE (blue)
  byte[26]: 7     // TOWN (red)
  byte[27]: 8     // CASTLE (red)
  byte[28]: 8     // CASTLE (red)
  byte[29]: 7     // TOWN (neutral)
  byte[30]: 8     // CASTLE (neutral)
  byte[31]: 8     // CASTLE (neutral)
  byte[32]: 7     // TOWN (????)
  byte[33]: 8     // CASTLE (????)
  byte[34]: 8     // CASTLE (????)
 */