import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

// This code reads the content of all .sprite assets in a specific folder
// These assets are part of the contents of the "1.pak" archive

// HOW TO USE
// 1. Open the folder containing this script
// 2. Create a folder called "sprites"
// 3. Move the .sprite assets into the sprites folder
// 4. Run
// Note: You MUST cd into the directory containing this script for this to work properly

// RUN
//      cd <dictory-containing-this-script>
//      java SpriteAssetsReader.java

public class SpriteAssetsReader {
    public static void main(String[] args) {
        String spritesDirName = "sprites";
        File dir = new File(spritesDirName);

        if(!dir.exists()) {
            System.out.println("Error: cannot find directory '" + spritesDirName + "'");
        }

        String[] dirFiles = dir.list();
        
        for(String filename : dirFiles) {
            if(filename.endsWith(".sprite")) {
                String filePath = spritesDirName + File.separatorChar + filename;

                try {
                    loadSpriteSheet(filePath, 0);
                }
                catch(Exception e) {
                    System.err.println("Error: " + e.getLocalizedMessage());
                }
            }
        }
    }

    // Variant of a method called in the spritesheet class constructor
    // (at first commit time the method is e.a())
    private static void loadSpriteSheet(String imagePath, int n) throws Exception {
        InputStream inputStream = new FileInputStream(imagePath);

        int tileCount = inputStream.read();
        byte tileWidth = (byte)inputStream.read();
        byte tileHeight = (byte)inputStream.read();
        // In the original code this is closed later but it's not needed anymore
        inputStream.close();

        int lastSeparatorIndex = imagePath.lastIndexOf(File.separatorChar);
        System.out.println(imagePath.substring(lastSeparatorIndex + 1));
        System.out.println("  tiles: " + tileCount);
        System.out.println("  width:  " + tileWidth);
        System.out.println("  height: " + tileHeight);

        // Till here, the second parameter is not used
    }
}