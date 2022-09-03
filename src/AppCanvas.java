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

public class AppCanvas
extends FullCanvas
implements Runnable,
CommandListener {
    public static final Font fontSmallPlain;
    public static final Font fontMediumBold;
    public static int width2;
    public static int height2;
    public static int h;
    public static int f;
    private static final short[] asciiRefStart;
    private static final short[] asciiRefEnd;
    private static final byte[][] var_byte_arr_arr_a;
    public Display appDisplay;
    private boolean isRunning = false;
    public a var_a_a;
    public int width;
    public int height;
    public int var_int_c = 0;
    public int var_int_b = 0;
    private long var_long_a;
    private static e[] var_e_arr_a;
    public static Random randomGen;
    public static boolean[] settings;
    public static String[] var_java_lang_String_arr_d;
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
            h = width2 >> 1;
            f = height2 >> 1;
            this.appDisplay = Display.getDisplay((MIDlet)mIDlet);
            this.appDisplay.setCurrent((Displayable)this);
            new Thread(this).start();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            this.showErrorForm(exception.toString());
        }
    }

    public static int int_a(int n) {
        return n * 208 / 128;
    }

    public static int int_b(int n) {
        return n * 176 / 128;
    }

    public void a(Graphics graphics, int x, int y, int n3, int n4, int n5) {
        this.spriteMask.draw(graphics, x, y);
    }

    public void savePersistentData(String recordName, byte[] data) throws Exception {
        try {
            RecordStore.deleteRecordStore((String)recordName);
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

    public static int a(byte by, String string) {
        return var_e_arr_a[by].short_a() * string.length();
    }

    public static int a(byte by) {
        return var_e_arr_a[by].short_b();
    }

    public void showNotify() {
        this.var_int_c = 0;
        if (this.var_a_a != null) {
            this.var_a_a.q();
        }
    }

    public void hideNotify() {
        this.var_int_c = 0;
        if (this.var_a_a != null) {
            this.var_a_a.n();
        }
    }

    public static void a(Graphics graphics, String text, int n, int n2, int n3, int n4) {
        if ((n4 & 8) != 0) {
            n -= AppCanvas.a((byte)n3, text);
        }
        AppCanvas.drawBoldWhiteText(graphics, text, n, n2, n3);
    }

    public static void drawBoldWhiteText(Graphics graphics, String text, int x, int y, int n3) {
        int stringLength = text.length();
        for (int j = 0; j < stringLength; ++j) {
            char letter = text.charAt(j);
            if (letter < asciiRefStart[n3] || letter > asciiRefEnd[n3]) continue;
            byte by = var_byte_arr_arr_a[n3][letter - asciiRefStart[n3]];
            if (by != -1) {
                var_e_arr_a[n3].a(by);
                var_e_arr_a[n3].a(graphics, x, y);
                x += var_e_arr_a[n3].short_a();
                continue;
            }
            byte[] letterByte = new byte[]{(byte)letter};
            String letterString = new String(letterByte);
            graphics.drawString(letterString, x, y, 20);
            x += graphics.getFont().stringWidth(letterString);
        }
    }

    // graphics code can only be executed inside this method. More info:
    // https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/javax/microedition/lcdui/Canvas.html#paint(javax.microedition.lcdui.Graphics)
    public void paint(Graphics graphics) {
        if (this.isRunning) {
            this.var_a_a.gameDraw(graphics);
        } else {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setFont(fontSmallPlain);
            graphics.setColor(0);
            graphics.drawString(AppCanvas.getGameText(24), this.width / 2, (this.height - fontSmallPlain.getHeight()) / 2, 17);
        }
    }

    public int getGameAction(int keyCode) {
        // Constants from FullCanvas (which this class extends) and Canvas (parent of FullCanvas)
        switch (keyCode) {
            case KEY_SOFTKEY1: {
                return 1024;
            }
            case KEY_SOFTKEY2: {
                return 2048;
            }
            case KEY_NUM0: {
                return 32;
            }
            case KEY_NUM5: {
                return 16;
            }
            case KEY_NUM1: {
                return 64;
            }
            case KEY_NUM3: {
                return 128;
            }
            case KEY_NUM7: {
                return 256;
            }
            case KEY_NUM9: {
                return 512;
            }
            case KEY_NUM2: {
                return 1;
            }
            case KEY_NUM8: {
                return 2;
            }
            case KEY_NUM4: {
                return 4;
            }
            case KEY_NUM6: {
                return 8;
            }
        }
        switch (super.getGameAction(keyCode)) {
            case UP: {
                return 1;
            }
            case DOWN: {
                return 2;
            }
            case LEFT: {
                return 4;
            }
            case RIGHT: {
                return 8;
            }
            case FIRE: {
                return 16;
            }
        }
        return 0;
    }

    public void keyPressed(int keyCode) {
        this.handleKeyPressedAction(this.getGameAction(keyCode));
    }

    public boolean boolean_c(int n) {
        return (this.var_int_c & n) != 0;
    }

    public void keyReleased(int keyCode) {
        this.void_c(this.getGameAction(keyCode));
    }

    public boolean boolean_a(int n) {
        return this.var_int_b == n && System.currentTimeMillis() - this.var_long_a >= 300L;
    }

    public void handleKeyPressedAction(int gameActionCode) {
        this.var_int_b = gameActionCode;
        this.var_long_a = System.currentTimeMillis();
        this.var_int_c |= gameActionCode;
        if (this.var_a_a != null) {
            this.var_a_a.d(gameActionCode);
        }
    }

    public void void_c(int gameActionCode) {
        if (gameActionCode == this.var_int_b) {
            this.var_int_b = 0;
        }
        this.var_int_c &= ~gameActionCode;
    }

    // TODO make private, this is not called outside the class
    public void showErrorForm(String errorText) {
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

    public void run() {
        try {
            String[] stringArray = new String[]{
                AppCanvas.getGameText(19),
                AppCanvas.getGameText(20),
                AppCanvas.getGameText(18),
                AppCanvas.getGameText(17)};
            var_java_lang_String_arr_d = stringArray;
            Class_I.appCanvas = this;
            AppCanvas.readAssetsPackage();
            this.loadSounds();
            Class_I.loadSettingsData();
            AppCanvas.var_e_arr_a[0] = new e("chars");
            AppCanvas.var_e_arr_a[1] = new e("lchars");
            this.spriteMask = new Sprite("mask.png");
            this.var_a_a = new a((byte)0);
            this.isRunning = true;
            while (this.isRunning) {
                if (!this.isShown()) continue;
                long startMillis = System.currentTimeMillis();
                this.var_a_a.e();
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
            if (settings[1]) {
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

    public static String[] a(String string, int n, Font font) {
        int n2;
        Vector<String> vector = new Vector<String>();
        int n3 = 0;
        int n4 = string.length();
        String string2 = null;
        do {
            n2 = n3;
            int n5 = string.indexOf(10, n2);
            block1: do {
                int n6 = n2;
                String string3 = string2;
                n2 = AppCanvas.a(string, n2);
                if (n5 > -1 && n5 < n2) {
                    n2 = n5;
                }
                if (font.stringWidth(string2 = string.substring(n3, n2).trim()) <= n) continue;
                if (n6 == n3) {
                    for (int j = string2.length() - 1; j > 0; --j) {
                        String string4 = string2.substring(0, j);
                        if (font.stringWidth(string4) > n) continue;
                        n2 = n6 + j;
                        string2 = string4;
                        break block1;
                    }
                    break;
                }
                n2 = n6;
                string2 = string3;
                break;
            } while (n2 != n5 && n2 < n4);
            vector.addElement(string2);
        } while ((n3 = ++n2) < n4);
        String[] stringsArray = new String[vector.size()];
        vector.copyInto(stringsArray);
        return stringsArray;
    }

    private static int a(String string, int n) {
        int n2;
        char c2 = string.charAt(n);
        if (AppCanvas.boolean_b(c2)) {
            return n + 1;
        }
        int n3 = 0;
        while ((n2 = string.indexOf(32, n)) == 0) {
            ++n;
        }
        n3 = n2;
        n3 = n3 == -1 ? string.length() : ++n3;
        for (n2 = n + 1; n2 < n3; ++n2) {
            if (!AppCanvas.boolean_b(string.charAt(n2))) continue;
            return n2;
        }
        return n3;
    }

    private static boolean boolean_b(int n) {
        return n >= 11904 && n < 44032 || n >= 63744 && n < 64256 || n >= 65280 && n < 65504;
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

    public static int loadAppStrings(String filename, boolean skipFirstPart) throws Exception {
        int n;
        InputStream inputStream = ((Object)((Object)App.instance)).getClass().getResourceAsStream(filename);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        if (!skipFirstPart) {
            stringsPartA = new String[63];
        }
        stringsPartB = new String[dataInputStream.readInt() - 63];
        int stringsCount = stringsPartA.length;
        for (n = 0; n < stringsCount; ++n) {
            String currentString = dataInputStream.readUTF();
            if (skipFirstPart) continue;
            AppCanvas.stringsPartA[n] = currentString;
        }
        stringsCount = stringsPartB.length;
        for (n = 0; n < stringsCount; ++n) {
            AppCanvas.stringsPartB[n] = dataInputStream.readUTF();
        }
        dataInputStream.close();
        return stringsPartA.length;
    }

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
        // TODO this is never used
        fontMediumBold = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        asciiRefStart = new short[]{43, 48};  // 43 = '+', 48 = '0' (ASCII)
        asciiRefEnd = new short[]{90, 57};    // 90 = 'Z', 57 = '9' (ASCII)
        var_byte_arr_arr_a = new byte[][]{
            {26, -1, 25, -1, -1, 14, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, -1},
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}};
        var_e_arr_a = new e[2];
        randomGen = new Random();
        // 0/1 - music/sound (referenced in stopSound) // 2 - tutorial
        settings = new boolean[]{true, true, true, true};
    }
}

