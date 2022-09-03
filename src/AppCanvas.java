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
    public static final Font var_javax_microedition_lcdui_Font_a;
    public static final Font var_javax_microedition_lcdui_Font_b;
    public static int width2;
    public static int height2;
    public static int h;
    public static int f;
    public static final short[] var_short_arr_b;
    public static final short[] var_short_arr_a;
    public static final byte[][] var_byte_arr_arr_a;
    public Display appDisplay;
    private boolean var_boolean_a = false;
    public a var_a_a;
    public int width;
    public int height;
    public int var_int_c = 0;
    public int var_int_b = 0;
    public long var_long_a;
    private static e[] var_e_arr_a;
    public static Random randomGen;
    public static boolean[] var_boolean_arr_a;
    public static String[] var_java_lang_String_arr_d;
    public static boolean var_boolean_b;
    public Sprite spriteMask;
    public static Sound[] midiSounds;
    public static byte[][] var_byte_arr_arr_b;
    public static String[] var_java_lang_String_arr_a;
    public static String[] stringsPartA;
    public static String[] stringsPartB;

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

    public void a(String recordName, byte[] byArray) throws Exception {
        try {
            RecordStore.deleteRecordStore((String)recordName);
        }
        catch (Exception exception) {
            // empty catch block
        }
        RecordStore recordStore = RecordStore.openRecordStore((String)recordName, (boolean)true);
        if (recordStore.getNumRecords() == 0) {
            recordStore.addRecord(byArray, 0, byArray.length);
        } else {
            recordStore.setRecord(1, byArray, 0, byArray.length);
        }
        recordStore.closeRecordStore();
    }

    public byte[] byte_arr_b(String recordName) throws Exception {
        RecordStore recordStore = RecordStore.openRecordStore((String)recordName, (boolean)false);
        byte[] byArray = recordStore.getRecord(1);
        recordStore.closeRecordStore();
        return byArray;
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

    public static void a(Graphics graphics, String string, int n, int n2, int n3, int n4) {
        if ((n4 & 8) != 0) {
            n -= AppCanvas.a((byte)n3, string);
        }
        AppCanvas.a(graphics, string, n, n2, n3);
    }

    public static void a(Graphics graphics, String string, int x, int y, int n3) {
        int n4 = string.length();
        for (int j = 0; j < n4; ++j) {
            char c2 = string.charAt(j);
            if (c2 < var_short_arr_b[n3] || c2 > var_short_arr_a[n3]) continue;
            byte by = var_byte_arr_arr_a[n3][c2 - var_short_arr_b[n3]];
            if (by != -1) {
                var_e_arr_a[n3].a(by);
                var_e_arr_a[n3].a(graphics, x, y);
                x += var_e_arr_a[n3].short_a();
                continue;
            }
            byte[] byArray = new byte[]{(byte)c2};
            String text = new String(byArray);
            graphics.drawString(text, x, y, 20);
            x += graphics.getFont().stringWidth(text);
        }
    }

    public void paint(Graphics graphics) {
        if (this.var_boolean_a) {
            this.var_a_a.a(graphics);
        } else {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setFont(var_javax_microedition_lcdui_Font_a);
            graphics.setColor(0);
            graphics.drawString(AppCanvas.getGameText(24), this.width / 2, (this.height - var_javax_microedition_lcdui_Font_a.getHeight()) / 2, 17);
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
        this.e(this.getGameAction(keyCode));
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

    public void e(int gameActionCode) {
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
        this.var_boolean_a = false;
        Form form = new Form("Fatal error!");
        form.append(errorText);
        Command command = new Command("Exit", Command.EXIT, 1);
        form.addCommand(command);
        form.setCommandListener((CommandListener)this);
        this.appDisplay.setCurrent((Displayable)form);
    }

    public void d() {
        this.var_boolean_a = false;
    }

    public void run() {
        try {
            String[] stringArray = new String[]{
                AppCanvas.getGameText(19),
                AppCanvas.getGameText(20),
                AppCanvas.getGameText(18),
                AppCanvas.getGameText(17)};
            var_java_lang_String_arr_d = stringArray;
            i.var_d_a = this;
            AppCanvas.void_a("");
            this.b();
            this.c();
            i.f();
            AppCanvas.var_e_arr_a[0] = new e("chars");
            AppCanvas.var_e_arr_a[1] = new e("lchars");
            this.spriteMask = new Sprite("mask.png");
            this.var_a_a = new a((byte)0);
            this.var_boolean_a = true;
            while (this.var_boolean_a) {
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

    public static void d(int n) {
    }

    public void c() {
    }

    public void b() {
        midiSounds = new Sound[4];
        for (int j = 0; j < 4; ++j) {
            try {
                AppCanvas.midiSounds[j] = new Sound(AppCanvas.byte_arr_a("a" + j), 1);
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
            if (var_boolean_arr_a[1]) {
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

    public static void void_a(String string) throws Exception {
        if (var_java_lang_String_arr_a == null) {
            var_java_lang_String_arr_a = null;
            int[] nArray = null;
            int[] nArray2 = null;
            InputStream inputStream = ((Object)((Object)App.instance)).getClass().getResourceAsStream("/1.pak");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            short s = dataInputStream.readShort();
            int n = dataInputStream.readShort();
            var_java_lang_String_arr_a = new String[n];
            nArray = new int[n];
            nArray2 = new int[n];
            for (int j = 0; j < n; ++j) {
                AppCanvas.var_java_lang_String_arr_a[j] = dataInputStream.readUTF();
                nArray[j] = dataInputStream.readInt() + s;
                nArray2[j] = dataInputStream.readShort();
            }
            var_byte_arr_arr_b = new byte[var_java_lang_String_arr_a.length][];
            for (int j = 0; j < var_java_lang_String_arr_a.length; ++j) {
                AppCanvas.var_byte_arr_arr_b[j] = new byte[nArray2[j]];
                dataInputStream.readFully(var_byte_arr_arr_b[j]);
            }
            dataInputStream.close();
        }
    }

    public static byte[] byte_arr_a(String filename) {
        for (int j = 0; j < var_java_lang_String_arr_a.length; ++j) {
            if (!filename.equals(var_java_lang_String_arr_a[j])) continue;
            return var_byte_arr_arr_b[j];
        }
        return null;
    }

    public static InputStream java_io_InputStream_a(String filename) throws Exception {
        return new ByteArrayInputStream(AppCanvas.byte_arr_a(filename));
    }

    public static void e() {
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
        var_javax_microedition_lcdui_Font_a = Font.getFont((int)0, (int)0, (int)8);
        var_javax_microedition_lcdui_Font_b = Font.getFont((int)0, (int)1, (int)0);
        var_short_arr_b = new short[]{43, 48};
        var_short_arr_a = new short[]{90, 57};
        var_byte_arr_arr_a = new byte[][]{{26, -1, 25, -1, -1, 14, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, -1}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}};
        var_e_arr_a = new e[2];
        randomGen = new Random();
        var_boolean_arr_a = new boolean[]{true, true, true, true};
        var_boolean_b = true;
    }
}

