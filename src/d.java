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

public class d
extends FullCanvas
implements Runnable,
CommandListener {
    public static final Font var_javax_microedition_lcdui_Font_a;
    public static final Font var_javax_microedition_lcdui_Font_b;
    public static int var_int_a;
    public static int var_int_d;
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
    public Sprite var_h_a;
    public static Sound[] var_com_nokia_mid_sound_Sound_arr_a;
    public static byte[][] var_byte_arr_arr_b;
    public static String[] var_java_lang_String_arr_a;
    public static String[] stringsPartA;
    public static String[] stringsPartB;

    public d(MIDlet mIDlet) {
        try {
            d.loadAppStrings("/lang.dat", false);
            this.width = this.getWidth();
            this.height = this.getHeight();
            var_int_a = this.width;
            var_int_d = this.height;
            h = var_int_a >> 1;
            f = var_int_d >> 1;
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

    public void a(Graphics graphics, int n, int n2, int n3, int n4, int n5) {
        this.var_h_a.draw(graphics, n, n2);
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
            n -= d.a((byte)n3, string);
        }
        d.a(graphics, string, n, n2, n3);
    }

    public static void a(Graphics graphics, String string, int n, int n2, int n3) {
        boolean bl = false;
        int n4 = string.length();
        for (int j = 0; j < n4; ++j) {
            char c2 = string.charAt(j);
            if (c2 < var_short_arr_b[n3] || c2 > var_short_arr_a[n3]) continue;
            byte by = var_byte_arr_arr_a[n3][c2 - var_short_arr_b[n3]];
            if (by != -1) {
                var_e_arr_a[n3].a(by);
                var_e_arr_a[n3].a(graphics, n, n2);
                n += var_e_arr_a[n3].short_a();
                continue;
            }
            byte[] byArray = new byte[]{(byte)c2};
            String string2 = new String(byArray);
            graphics.drawString(string2, n, n2, 20);
            n += graphics.getFont().stringWidth(string2);
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
            graphics.drawString(d.getGameText(24), this.width / 2, (this.height - var_javax_microedition_lcdui_Font_a.getHeight()) / 2, 17);
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

    public void e(int n) {
        this.var_int_b = n;
        this.var_long_a = System.currentTimeMillis();
        this.var_int_c |= n;
        if (this.var_a_a != null) {
            this.var_a_a.d(n);
        }
    }

    public void void_c(int n) {
        if (n == this.var_int_b) {
            this.var_int_b = 0;
        }
        this.var_int_c &= ~n;
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
            String[] stringArray = new String[]{d.getGameText(19), d.getGameText(20), d.getGameText(18), d.getGameText(17)};
            var_java_lang_String_arr_d = stringArray;
            i.var_d_a = this;
            d.void_a("");
            this.b();
            this.c();
            i.f();
            d.var_e_arr_a[0] = new e("chars");
            d.var_e_arr_a[1] = new e("lchars");
            this.var_h_a = new Sprite("mask.png");
            this.var_a_a = new a(0);
            this.var_boolean_a = true;
            while (this.var_boolean_a) {
                if (!this.isShown()) continue;
                long l = System.currentTimeMillis();
                this.var_a_a.e();
                int n = (int)(System.currentTimeMillis() - l);
                if (n >= 75) continue;
                try {
                    Thread.sleep(75 - n);
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
        var_com_nokia_mid_sound_Sound_arr_a = new Sound[4];
        for (int j = 0; j < 4; ++j) {
            try {
                d.var_com_nokia_mid_sound_Sound_arr_a[j] = new Sound(d.byte_arr_a("a" + j), 1);
                continue;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void a() {
        d.void_b(0);
    }

    public static void a(int n, int n2) {
        try {
            if (var_boolean_arr_a[1]) {
                var_com_nokia_mid_sound_Sound_arr_a[n].play(n2);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void void_b(int n) {
        try {
            var_com_nokia_mid_sound_Sound_arr_a[n].stop();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void void_a(int n) {
        try {
            var_com_nokia_mid_sound_Sound_arr_a[n].release();
            d.var_com_nokia_mid_sound_Sound_arr_a[n] = null;
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static String[] a(String string, int n, Font font) {
        int n2;
        Vector<String> vector = new Vector<String>();
        int n3 = 0;
        boolean bl = false;
        int n4 = string.length();
        String string2 = null;
        do {
            n2 = n3;
            int n5 = string.indexOf(10, n2);
            block1: do {
                int n6 = n2;
                String string3 = string2;
                n2 = d.a(string, n2);
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
        Object[] objectArray = new String[vector.size()];
        vector.copyInto(objectArray);
        return objectArray;
    }

    private static int a(String string, int n) {
        int n2;
        char c2 = string.charAt(n);
        if (d.boolean_b(c2)) {
            return n + 1;
        }
        int n3 = 0;
        while ((n2 = string.indexOf(32, n)) == 0) {
            ++n;
        }
        n3 = n2;
        n3 = n3 == -1 ? string.length() : ++n3;
        for (n2 = n + 1; n2 < n3; ++n2) {
            if (!d.boolean_b(string.charAt(n2))) continue;
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
                d.var_java_lang_String_arr_a[j] = dataInputStream.readUTF();
                nArray[j] = dataInputStream.readInt() + s;
                nArray2[j] = dataInputStream.readShort();
            }
            var_byte_arr_arr_b = new byte[var_java_lang_String_arr_a.length][];
            for (int j = 0; j < var_java_lang_String_arr_a.length; ++j) {
                d.var_byte_arr_arr_b[j] = new byte[nArray2[j]];
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
        return new ByteArrayInputStream(d.byte_arr_a(filename));
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
            d.stringsPartA[n] = currentString;
        }
        stringsCount = stringsPartB.length;
        for (n = 0; n < stringsCount; ++n) {
            d.stringsPartB[n] = dataInputStream.readUTF();
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

