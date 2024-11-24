/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Display
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Form
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.midlet.MIDlet
 *  javax.microedition.rms.RecordStore
 */
import com.nokia.mid.sound.Sound;
import com.nokia.mid.ui.FullCanvas;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStore;

// Docs showing how most of the methods here work:
// https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/lcdui/Canvas.html
public class AppCanvas
extends FullCanvas
implements Runnable,
CommandListener {
    public static final byte SETTINGS_MUSIC = 0;
    public static final byte SETTINGS_SOUND = 1;
    public static final byte SETTINGS_HELP = 2;
    public static final byte SETTINGS_FIGHT_ANIMATIONS = 3;

    public static final byte FONT_ALPHANUMERIC = 0;
    public static final byte FONT_NUMERIC = 1;

    public static final Font fontSmallPlain;
    //private static final Font fontMediumBold;
    public static int width2;
    public static int height2;
    public static int cenX;
    public static int f;
    private static final short[] fontSheetMinASCIIValue;
    private static final short[] fontSheetMaxASCIIValue;
    private static final byte[][] fontSheetOffsetToSpriteIndexTable;
    public Display appDisplay;
    private boolean isRunning = false;
    public a a_instance;
    public int width;
    public int height;
    // TODO make private to encapsulate / prevent external modification
    public int pressedKeysActions = 0;
    private int lastRequestedAction = ACTION_NONE;
    private long lastKeyPressedTime;
    private static SpriteSheet[] fontSheets;
    private static Random randomGen;
    public static boolean[] settings;
    public static String[] settingsNames;
    private Sprite spriteMask;
    private static Sound[] midiSounds;
    private static byte[][] assetsFileBytes;
    private static String[] assetsFileName;
    // TODO maybe rename to appStrings, more descriptive
    private static String[] stringsPartA;
    private static String[] stringsPartB;

    public AppCanvas(MIDlet mIDlet) {
        try {
            AppCanvas.loadAppStrings("/lang.dat", false);
            // TODO it seems the next 4 variables (height\width) are never modified, one set is static and the other is not, you can keep just one
            this.width = this.getWidth();
            this.height = this.getHeight();
            width2 = this.width;
            height2 = this.height;
            // Right shift divides by two
            cenX = width2 >> 1;
            f = height2 >> 1;
            this.appDisplay = Display.getDisplay((MIDlet)mIDlet);
            this.appDisplay.setCurrent((Displayable)this);
            // Executes the run() method of this class on another thread
            new Thread(this).start();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            this.showErrorForm(exception.toString());
        }
    }

    public static int int_a(int n) {
        // 208 = height of splashbg.png and splashfg.png
        return n * 208 / 128;
    }

    public static int int_b(int n) {
        // 176 = width of splashbg.png and splashfg.png
        return n * 176 / 128;
    }

    /** Returns a random integer (from all possible int values) */
    public static int randomInt() {
        return randomGen.nextInt();
    }

    // TODO the last three parameters are unused
    // TODO should probably call this "drawMask" but I don't know its purpose
    public void drawSpriteMask(Graphics graphics, int x, int y, int n3, int n4, int n5) {
        this.spriteMask.draw(graphics, x, y);
    }

    public void savePersistentData(String recordName, byte[] data) throws Exception {
        try {
            RecordStore.deleteRecordStore(recordName);
        }
        catch (Exception exception) {
            // empty catch block
        }
        RecordStore recordStore = RecordStore.openRecordStore(recordName, true);
        if (recordStore.getNumRecords() == 0) {
            recordStore.addRecord(data, 0, data.length);
        } else {
            recordStore.setRecord(1, data, 0, data.length);
        }
        recordStore.closeRecordStore();
    }

    public byte[] loadPersistentData(String recordName) throws Exception {
        RecordStore recordStore = RecordStore.openRecordStore(recordName, false);
        byte[] data = recordStore.getRecord(1);
        recordStore.closeRecordStore();
        return data;
    }

    public static int getSpriteFontTextWidth(byte fontIndex, String string) {
        return fontSheets[fontIndex].getSpritesWidth() * string.length();
    }

    public static int getSpriteFontCharHeight(byte fontIndex) {
        return fontSheets[fontIndex].getSpritesHeight();
    }

    public void showNotify() {
        this.pressedKeysActions = 0;
        if (this.a_instance != null) {
            this.a_instance.q();
        }
    }

    public void hideNotify() {
        this.pressedKeysActions = 0;
        /*if (this.a_instance != null) {
            this.a_instance.n();    // empty method
        }*/
    }

    public static void drawBoldWhiteText_XX(Graphics graphics, String text, int x, int y, byte fontIndex, int n4) {
        if ((n4 & 8) != 0) {
            x -= AppCanvas.getSpriteFontTextWidth(fontIndex, text);
        }
        AppCanvas.drawBoldWhiteText(graphics, text, x, y, fontIndex);
    }

    public static void drawBoldWhiteText(Graphics graphics, String text, int x, int y, byte fontIndex) {
        int stringLength = text.length();
        for (int j = 0; j < stringLength; ++j) {
            char currentChar = text.charAt(j);

            // Checks if the character is inside the interval of the font spritesheet
            if (currentChar < fontSheetMinASCIIValue[fontIndex] || currentChar > fontSheetMaxASCIIValue[fontIndex])
                continue;

            // There are some character that, while included the interval above,
            // are not included in the font spritesheets so they cannot be shown.
            // (e.g. the alphanumeric sheet is missing many symbols and the number '0')
            // The table below contains a value for every ASCII character in the interval
            // And that value is the index in the spritesheet, or -1 if it canno be displayed
            int offsetFromMinCharacter = currentChar - fontSheetMinASCIIValue[fontIndex];
            byte spriteIndex = fontSheetOffsetToSpriteIndexTable[fontIndex][offsetFromMinCharacter];

            if (spriteIndex != -1) {
                fontSheets[fontIndex].setCurrentIndex(spriteIndex);
                fontSheets[fontIndex].a(graphics, x, y);
                x += fontSheets[fontIndex].getSpritesWidth();
                continue;
            }

            // If the character is not inlcuded in the font spritesheet,
            // it's drawn using another font
            byte[] charByte = new byte[]{(byte)currentChar};
            String charString = new String(charByte);
            graphics.drawString(charString, x, y, 20);
            x += graphics.getFont().stringWidth(charString);
        }
    }

    // graphics code can only be executed inside this method. More info:
    // https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/lcdui/Canvas.html#paint(javax.microedition.lcdui.Graphics)
    public void paint(Graphics graphics) {
        if (this.isRunning) {
            this.a_instance.gameDraw(graphics);
        } else {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setFont(fontSmallPlain);
            graphics.setColor(0);
            // This text is shown at screen center while loading a level, white text on black screen
            graphics.drawString(
                AppCanvas.getGameText(24),  // 'LOADING . . .'
                this.width / 2,
                (this.height - fontSmallPlain.getHeight()) / 2,
                17);
        }
    }

    public static final int ACTION_NONE = 0;
    public static final int ACTION_UP = 1;
    public static final int ACTION_DOWN = 2;
    public static final int ACTION_LEFT = 4;
    public static final int ACTION_RIGHT = 8;
    public static final int ACTION_CONFIRM = 16;
    /** "You can view the attack range of any unit by pressing '0' when it is selected." */
    public static final int ACTION_UNIT_RANGE = 32;
    /** Scroll up the unit's description in the unit info panel. */
    public static final int ACTION_SCROLL_UP = 64;
    /** "You can view the characteristics of any unit by selecting it and pressing the '7' key".
         Can also scroll down the unit's description in the unit info panel. */
    public static final int ACTION_UNIT_INFO_OR_SCROLL_DOWN = 256;
    /** Undocumented - Immediately moves the view and cursor to your king's location. */
    public static final int ACTION_GOTO_KING = 512;
    public static final int ACTION_UI_CONFIRM = 1024;
    public static final int ACTION_CANCEL = 2048;

    // Some keys can have the same effect in the hame (e.g. UP and KEY_NUM2 both indicate "go up")
    public int getGameAction(int keyCode) {
        // Constants from FullCanvas (which this class extends) and Canvas (parent of FullCanvas)
        switch (keyCode) {
            case KEY_SOFTKEY1: {
                return ACTION_UI_CONFIRM;
            }
            case KEY_SOFTKEY2: {
                return ACTION_CANCEL;
            }
            case KEY_NUM0: {
                return ACTION_UNIT_RANGE;
            }
            case KEY_NUM5: {
                return ACTION_CONFIRM;
            }
            case KEY_NUM1: {
                return ACTION_SCROLL_UP;
            }
            case KEY_NUM3: {
                return 128;
            }
            case KEY_NUM7: {
                return ACTION_UNIT_INFO_OR_SCROLL_DOWN;
            }
            case KEY_NUM9: {
                return ACTION_GOTO_KING;
            }
            case KEY_NUM2: {
                return ACTION_UP;
            }
            case KEY_NUM8: {
                return ACTION_DOWN;
            }
            case KEY_NUM4: {
                return ACTION_LEFT;
            }
            case KEY_NUM6: {
                return ACTION_RIGHT;
            }
        }
        switch (super.getGameAction(keyCode)) {
            case UP: {
                return ACTION_UP;
            }
            case DOWN: {
                return ACTION_DOWN;
            }
            case LEFT: {
                return ACTION_LEFT;
            }
            case RIGHT: {
                return ACTION_RIGHT;
            }
            case FIRE: {
                return ACTION_CONFIRM;
            }
        }
        return 0;
    }

    public void keyPressed(int keyCode) {
        this.handleKeyPressedAction(this.getGameAction(keyCode));
    }

    public boolean isRequestingAction(int gameActionCode) {
        return (this.pressedKeysActions & gameActionCode) != 0;
    }

    public void keyReleased(int keyCode) {
        this.handleKeyReleasedAction(this.getGameAction(keyCode));
    }

    public boolean stoppedRequestingAction(int gameActionCode) {
        return this.lastRequestedAction == gameActionCode && System.currentTimeMillis() - this.lastKeyPressedTime >= 300L;
    }

    public void handleKeyPressedAction(int gameActionCode) {
        this.lastRequestedAction = gameActionCode;
        this.lastKeyPressedTime = System.currentTimeMillis();
        // All action codes are powers of two, so they can be used as bit flags
        this.pressedKeysActions |= gameActionCode;
        if (this.a_instance != null) {
            this.a_instance.d(gameActionCode);
        }
    }

    public void handleKeyReleasedAction(int gameActionCode) {
        if (gameActionCode == this.lastRequestedAction) {
            this.lastRequestedAction = ACTION_NONE;
        }
        this.pressedKeysActions &= ~gameActionCode;
    }

    private void showErrorForm(String errorText) {
        this.isRunning = false;
        Form form = new Form("Fatal error!");
        form.append(errorText);
        Command command = new Command("Exit", Command.EXIT, 1);
        form.addCommand(command);
        form.setCommandListener((CommandListener)this);
        this.appDisplay.setCurrent((Displayable)form);
    }

    public void handleAppDestroy() {
        this.isRunning = false;
    }

    // Called in the costructor by Thread(this).start();
    public void run() {
        try {
            settingsNames = new String[]{
                AppCanvas.getGameText(19),   // Music
                AppCanvas.getGameText(20),   // Sound
                AppCanvas.getGameText(18),   // Help
                AppCanvas.getGameText(17)};  // Fight Animation
            // TODO There are multiple reference to this class, reduce redundancy
            Class_I.appCanvas = this;
            AppCanvas.readAssetsPackage();
            this.loadSounds();
            Class_I.loadSettingsData();
            AppCanvas.fontSheets[FONT_ALPHANUMERIC] = new SpriteSheet("chars");     // 36 tiles, 7x7 [A-Z][+-][1-9]
            AppCanvas.fontSheets[FONT_NUMERIC] = new SpriteSheet("lchars");    // 10 tiles, 10x15 [0-9]
            this.spriteMask = new Sprite("mask.png");
            this.a_instance = new a((byte)0);
            this.isRunning = true;
            // Game loop
            while (this.isRunning) {
                if (!this.isShown()) continue;
                long startMillis = System.currentTimeMillis();
                this.a_instance.e();
                int elapsedMillis = (int)(System.currentTimeMillis() - startMillis);
                if (elapsedMillis >= 75) continue;
                try {
                    Thread.sleep(75 - elapsedMillis);
                }
                catch (Exception exception) {}
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            this.showErrorForm(exception.toString());
        }
    }

    private void loadSounds() {
        midiSounds = new Sound[4];
        for (int j = 0; j < 4; ++j) {
            try {
                AppCanvas.midiSounds[j] = new Sound(AppCanvas.getFileBytes("a" + j), Sound.FORMAT_TONE);
                continue;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void stopFirstSound() {
        AppCanvas.stopSound(0);
    }

    public static void playSound(int index, int loopTimes) {
        try {
            if (settings[SETTINGS_SOUND]) {
                // http://www.j2megame.org/j2meapi/Nokia_UI_API_1_1/com/nokia/mid/sound/Sound.html#play(int)
                midiSounds[index].play(loopTimes);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void stopSound(int index) {
        try {
            midiSounds[index].stop();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void releaseSoundResources(int index) {
        try {
            // Releases audio resources reserved by this object.
            // http://www.j2megame.org/j2meapi/Nokia_UI_API_1_1/com/nokia/mid/sound/Sound.html#release()
            midiSounds[index].release();
            AppCanvas.midiSounds[index] = null;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    // TODO figured put the output but the logic still needs to be deobfuscated
    /**
     * Splits a string into lines so that each line fits into a certain width.
     * The split is "word-wrap", meaning newlines can happen only where there is a space.
     * @param string Text to split.
     * @param maxPixelWidth If a line is longer than this value, text will continue on a new line.
     * @param font
     * @return An array containing each line of text.
     */
    public static String[] a(String string, int maxPixelWidth, Font font) {
        int n2;
        Vector<String> vector = new Vector<String>();
        int n3 = 0;
        final int originalTextLength = string.length();
        String string2 = null;
        do {
            n2 = n3;
            final int newlineCharIndex = string.indexOf(10, n2);
            block1: do {
                int n6 = n2;
                String string3 = string2;
                n2 = AppCanvas.a(string, n2);
                if (newlineCharIndex > -1 && newlineCharIndex < n2) {
                    n2 = newlineCharIndex;
                }
                string2 = string.substring(n3, n2).trim();
                if (font.stringWidth(string2) <= maxPixelWidth) continue;
                if (n6 == n3) {
                    for (int j = string2.length() - 1; j > 0; --j) {
                        String string4 = string2.substring(0, j);
                        if (font.stringWidth(string4) > maxPixelWidth) continue;
                        n2 = n6 + j;
                        string2 = string4;
                        break block1;
                    }
                    break;
                }
                n2 = n6;
                string2 = string3;
                break;
            } while (n2 != newlineCharIndex && n2 < originalTextLength);
            vector.addElement(string2);
            n3 = ++n2;
        } while (n3 < originalTextLength);

        String[] lines = new String[vector.size()];
        vector.copyInto(lines);
        return lines;
    }

    private static int a(String string, int charIndex) {
        char c2 = string.charAt(charIndex);
        if (AppCanvas.boolean_b(c2)) {
            return charIndex + 1;
        }
        int n2;
        while ((n2 = string.indexOf(32, charIndex)) == 0) {
            ++charIndex;
        }
        int n3 = 0;
        n3 = n2;
        n3 = n3 == -1 ? string.length() : ++n3;
        for (n2 = charIndex + 1; n2 < n3; ++n2) {
            if (!AppCanvas.boolean_b(string.charAt(n2))) continue;
            return n2;
        }
        return n3;
    }

    private static boolean boolean_b(int charCode) {
        return charCode >= 11904 && charCode < 44032 || charCode >= 63744 && charCode < 64256 || charCode >= 65280 && charCode < 65504;
    }

    private static void readAssetsPackage() throws Exception {
        if (assetsFileName == null) {
            InputStream inputStream = ((Object)((Object)App.instance)).getClass().getResourceAsStream("/1.pak");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            short s = dataInputStream.readShort();
            int filesCount = dataInputStream.readShort();

            assetsFileName = new String[filesCount];
            int[] unusedArr = new int[filesCount];
            int[] assetFilesSizes = new int[filesCount];

            for (int j = 0; j < filesCount; ++j) {
                AppCanvas.assetsFileName[j] = dataInputStream.readUTF();
                unusedArr[j] = dataInputStream.readInt() + s;
                assetFilesSizes[j] = dataInputStream.readShort();
            }
            assetsFileBytes = new byte[filesCount][];
            for (int j = 0; j < filesCount; ++j) {
                AppCanvas.assetsFileBytes[j] = new byte[assetFilesSizes[j]];
                dataInputStream.readFully(assetsFileBytes[j]);
            }
            dataInputStream.close();
        }
    }

    /**
     * Gets the data for a specific file.
     * @param filename name of the file
     * @return byte array containing the file's data, or null if the file wasn't found
     */
    public static byte[] getFileBytes(String filename) {
        for (int j = 0; j < assetsFileName.length; ++j) {
            if (filename.equals(assetsFileName[j])) {
                return assetsFileBytes[j];
            }
        }
        return null;
    }

    public static InputStream getFileBytesInputStream(String filename) throws Exception {
        return new ByteArrayInputStream(AppCanvas.getFileBytes(filename));
    }

    public void commandAction(Command command, Displayable displayable) {
        App.instance.notifyDestroyed();
    }

    private static int loadAppStrings(String filename, boolean skipFirstPart) throws Exception {
        InputStream inputStream = ((Object)((Object)App.instance)).getClass().getResourceAsStream(filename);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        if (!skipFirstPart) {
            stringsPartA = new String[63];
        }
        stringsPartB = new String[dataInputStream.readInt() - 63];
        int stringsCount = stringsPartA.length;
        for (int n = 0; n < stringsCount; ++n) {
            String currentString = dataInputStream.readUTF();
            if (skipFirstPart) continue;
            AppCanvas.stringsPartA[n] = currentString;
        }
        stringsCount = stringsPartB.length;
        for (int n = 0; n < stringsCount; ++n) {
            AppCanvas.stringsPartB[n] = dataInputStream.readUTF();
        }
        dataInputStream.close();
        return stringsPartA.length;
    }

    /**
     * Gets a string loaded from the file lang.dat, identified by its index
     * @param index index of the string to return
     * @return the app string with the specific index
     */
    public static String getGameText(int index) {
        if (index < 63) {
            return stringsPartA[index];
        }
        if (index - 63 < stringsPartB.length) {
            return stringsPartB[index - 63];
        }
        return "?: " + index;
    }

    static {
        fontSmallPlain = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        //fontMediumBold = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        // These next two contain the character with the min/max ASCIII value in the font spritesheets
        fontSheetMinASCIIValue = new short[]{43, 48};  // 43 = '+', 48 = '0' (ASCII)
        fontSheetMaxASCIIValue = new short[]{90, 57};    // 90 = 'Z', 57 = '9' (ASCII)
        fontSheetOffsetToSpriteIndexTable = new byte[][]{
            {26, -1, 25, -1, -1, 14, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, -1},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}};
        fontSheets = new SpriteSheet[2];
        randomGen = new Random();
        // Music, Sound, Help, Fight Animation
        settings = new boolean[]{true, true, true, true};
    }
}

