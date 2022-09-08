/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.ChoiceGroup
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Displayable
 *  javax.microedition.lcdui.Form
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Item
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.Vector;
// "Choice" was not included in the original source
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Item;

public class Class_I
implements CommandListener {
    public static final byte BTN_ICON_CONFIRM = 0;
    public static final byte BTN_ICON_BACK = 1;
    public static final byte BTN_ICON_INFO = 2;

    public static final byte PORTRAIT_NONE = -1;
    public static final byte PORTRAIT_GALAMAR = 0;      // King Galamar
    public static final byte PORTRAIT_VALADORN = 1;     // Valadorn (the enemy, Galamar's brother)
    public static final byte PORTRAIT_CAPTAIN = 2;      // Galamar's troops captain

    public static final byte ARROW_UP = 0;
    public static final byte ARROW_DOWN = 1;

    public static final byte PLAYER_BLUE = 0;
    public static final byte PLAYER_RED = 1;

    private byte[] levelsData = new byte[1];
    public static AppCanvas appCanvas;
    private static final String[] skirmishMapNames;
    private byte var_byte_a;
    public String[] var_java_lang_String_arr_e = new String[]{
        AppCanvas.getGameText(1),  // NEW GAME
        AppCanvas.getGameText(2),  // SELECT LEVEL
        AppCanvas.getGameText(4),  // LOAD GAME
        AppCanvas.getGameText(5),  // SKIRMISH
        AppCanvas.getGameText(6),  // SETTINGS
        AppCanvas.getGameText(7),  // INSTRUCTIONS
        AppCanvas.getGameText(8),  // ABOUT
        AppCanvas.getGameText(9)}; // EXIT
    // Same as above, adds SAVE GAME as first element
    private String[] var_java_lang_String_arr_c = new String[]{
        AppCanvas.getGameText(3),       // SAVE GAME
        this.var_java_lang_String_arr_e[0],    // NEW GAME
        this.var_java_lang_String_arr_e[1],     // ...
        this.var_java_lang_String_arr_e[2],
        this.var_java_lang_String_arr_e[3],
        this.var_java_lang_String_arr_e[4],
        this.var_java_lang_String_arr_e[5],
        this.var_java_lang_String_arr_e[6],
        this.var_java_lang_String_arr_e[7]};
    private static final byte[][] var_byte_arr_arr_d;
    private static final byte[] var_byte_arr_f;
    private static final byte[] var_byte_arr_c;
    private long var_long_k = 0L;
    private long var_long_a;
    private boolean var_boolean_s = false;
    public static final int[] var_int_arr_a;
    public SpriteSheet[][] var_e_arr_arr_b;
    private static final byte[] var_byte_arr_a;
    public static final byte[] terrainTypeDefense;
    public static final byte[] terrainType_XXX;
    public static final String[] terrainTypeNames;
    public static final byte[] terrainTypeMovementReduction_XXX;
    public int var_int_t;
    public Sprite[] var_h_arr_d;
    public byte[] var_byte_arr_j;
    public short var_short_c;
    public short var_short_i;
    public short var_short_f;
    public short var_short_a;
    public short var_short_e;
    public short var_short_b;
    private Sprite spriteTombstone;
    public Sprite[] var_h_arr_c;
    public Sprite spriteMenuPointer;
    public Sprite spriteGold;
    public SpriteSheet var_e_h;
    public SpriteSheet var_e_k;
    public SpriteSheet uiArrowSheet;
    public SpriteSheet uiPanelFrameSheet;
    public SpriteSheet uiBtnIconSheet;
    public SpriteSheet var_e_l;
    public SpriteSheet var_e_r;
    public SpriteSheet var_e_d;
    public SpriteSheet var_e_j;
    public SpriteSheet uiPortraitSheet;
    public short var_short_h;
    public short var_short_g;
    public byte[][] var_byte_arr_arr_a;
    public byte var_byte_i;
    public byte var_byte_e;
    public long var_long_n;
    private int currentLevel;
    public int var_int_h;
    public int var_int_w;
    public Unit[] var_c_arr_b = null;
    public Unit var_c_h = null;
    public int var_int_c;
    public int var_int_v;
    public byte[][] var_byte_arr_arr_b;
    public boolean var_boolean_h = false;
    public boolean var_boolean_j = false;
    public boolean var_boolean_n = true;
    public Vector<Unit> var_java_util_Vector_a = new Vector<Unit>();
    public int var_int_g = 0;
    public boolean var_boolean_u = true;
    public boolean B = true;
    public Vector<short[]> var_java_util_Vector_b = null;
    public int H;
    public int var_int_k;
    public long var_long_c;
    public byte var_byte_h = (byte)2;
    public byte[] var_byte_arr_d = new byte[]{0, 1};
    public byte var_byte_g;
    public byte playerIndex_XX = 0;
    public short var_short_d;
    public Unit[] var_c_arr_a;
    public int[] var_int_arr_b;
    public byte[] var_byte_arr_b = new byte[]{1, 0};
    public Vector<g> var_java_util_Vector_e = new Vector<g>();
    public g var_g_c;
    public g var_g_h;
    public g var_g_g;
    public Vector<SpriteSheet> var_java_util_Vector_c = new Vector<SpriteSheet>();
    public Vector<SpriteSheet> var_java_util_Vector_f = new Vector<SpriteSheet>();
    public Unit var_c_c;
    public long var_long_g;
    public Unit var_c_e;
    public byte var_byte_f;
    public long var_long_i;
    public boolean var_boolean_r = false;
    public boolean var_boolean_t = false;
    public boolean var_boolean_f = false;
    public int var_int_q = 0;
    public int M = 0;
    public boolean var_boolean_m = false;
    public byte var_byte_d;
    public boolean var_boolean_c = false;
    public Sprite spriteMacrospaceLogo;
    public Sprite spriteGameTitle;
    public int var_int_p;
    private Command commandBack = new Command(AppCanvas.getGameText(22), Command.BACK, 1);   // "Back"
    private Command commandNext = new Command(AppCanvas.getGameText(12), 1, 1);  // "Next"
    private Command commandOK = new Command(AppCanvas.getGameText(10), Command.BACK, 1);      // "OK"
    private ChoiceGroup choiceGroupSettings;
    public boolean var_boolean_y = false;
    public int var_int_m;
    public int var_int_e;
    public int N;
    public int var_int_n;
    public long var_long_d;
    public Unit var_c_i;
    public Unit var_c_b;
    public boolean var_boolean_a = true;
    public long var_long_e;
    public boolean var_boolean_v = false;
    public long var_long_l;
    public int var_int_y;
    public int var_int_l;
    public Sprite[] var_h_arr_a;
    public boolean var_boolean_l = false;
    public boolean var_boolean_p;
    public int var_int_s = -1;
    public g var_g_i;
    public g var_g_e;
    public int J = 8;
    public g var_g_a;
    public g var_g_d;
    public g var_g_f;
    public boolean var_boolean_d = false;
    public int var_int_r;
    public byte[][] var_byte_arr_arr_e;
    public static final String[] var_java_lang_String_arr_d;
    public StringBuffer var_java_lang_StringBuffer_a = new StringBuffer();
    public int D;
    public Unit var_c_d = null;
    public int L = 0;
    public int E = -15790321;
    public int C = 6;
    public int G = this.C >> 1;
    public int K;
    public int var_int_u;
    public byte var_byte_b = 0;
    public int var_int_f;
    public int var_int_x;
    public Unit var_c_g;
    public Unit var_c_a;
    public int var_int_A = 0;
    public long var_long_j;
    public Unit[] var_c_arr_c;
    public int var_int_z;
    public int var_int_o;
    public Unit var_c_f = null;
    private short currentLevelStep = 0;
    public long var_long_h;
    public int var_int_i;
    public g var_g_b;
    public boolean var_boolean_z = false;
    public boolean var_boolean_o = false;
    public boolean var_boolean_w = false;
    public int var_int_j = -1;
    public int var_int_b = -1;
    public boolean var_boolean_A = true;
    public boolean var_boolean_i = true;
    public f var_f_b;
    public f var_f_a;
    public long var_long_f;
    public boolean var_boolean_k;
    public SpriteSheet spriteSheetSoul;
    public Sprite spritePanelDefense;
    public SpriteSheet var_e_n;
    public SpriteSheet var_e_i;
    public SpriteSheet var_e_c;
    public SpriteSheet var_e_p;
    public SpriteSheet var_e_f;
    public SpriteSheet var_e_o;
    public boolean var_boolean_q = false;
    public long var_long_m;
    public long var_long_b;
    public boolean var_boolean_x;
    public boolean var_boolean_e;
    public boolean var_boolean_b = false;

    public Class_I(byte by) throws Exception {
        this.var_byte_d = by;
        if (by == 0) {
            // Arrows shown when you can scroll text
            this.uiArrowSheet = new SpriteSheet("arrow");
            // Icons showing what the phone soft keys button will do (confirm, back)
            this.uiBtnIconSheet = new SpriteSheet("buttons");
            // Contains the elements used to make ui panels/menu frames
            this.uiPanelFrameSheet = new SpriteSheet("menu");
            this.spriteMacrospaceLogo = new Sprite("ms_logo.png");
            this.spriteMenuPointer = new Sprite("pointer.png");
            this.spriteGameTitle = new Sprite("splash.png");
            this.var_int_p = 0;
            try {
                this.levelsData = appCanvas.loadPersistentData("levels");
            }
            catch (Exception exception) {
                // empty catch block
            }
            return;
        }
    }

    public void m() throws Exception {
        int n;
        Object object;
        Object imageBytesObj;
        short s;
        AppCanvas.stopFirstSound();
        AppCanvas.releaseSoundResources(0);
        if (this.var_byte_d == 1) {
            return;
        }
        this.var_byte_d = 1;
        Unit.iClassRef = this;
        //AppCanvas.readAssetsPackage("/1.pak");
        this.var_e_h = new SpriteSheet("cursor");
        this.spriteGold = new Sprite("gold.png");
        // Characters heads, shown when there is story dialogue
        this.uiPortraitSheet = new SpriteSheet("portrait");
        this.var_e_d = new SpriteSheet("redspark");
        this.var_e_l = new SpriteSheet("smoke");
        this.var_e_r = new SpriteSheet("spark");
        this.var_e_j = new SpriteSheet("status");
        this.spriteTombstone = new Sprite("tombstone.png");
        this.var_e_h.a(var_byte_arr_arr_d[0]);
        this.var_e_k = new SpriteSheet(this.var_e_h);
        this.var_e_k.a(var_byte_arr_arr_d[3]);
        this.var_e_arr_arr_b = new SpriteSheet[2][11];
        byte[] byArray = AppCanvas.getFileBytes("unit_icons.png");
        for (short playerIndex = 0; playerIndex < 2; playerIndex = (short)((byte)(playerIndex + 1))) {
            imageBytesObj = new byte[byArray.length];
            System.arraycopy(byArray, 0, imageBytesObj, 0, byArray.length);
            object = Sprite.fromByteArray((byte[])imageBytesObj, (int)playerIndex);
            for (n = 0; n < 11; n = (int)((byte)(n + 1))) {
                this.var_e_arr_arr_b[playerIndex][n] = new SpriteSheet(new Sprite((Sprite)object, n, 0, 24, ((Sprite)object).height), 24, 24);
            }
        }
        imageBytesObj = AppCanvas.getFileBytesInputStream("tiles0.prop");
        object = new DataInputStream((InputStream)imageBytesObj);
        short s2 = ((DataInputStream)object).readShort();
        /*short s3 = */((DataInputStream)object).readShort();   // Unused variable
        this.var_byte_arr_j = new byte[s2];
        for (s = 0; s < s2; s = (short)((byte)(s + 1))) {
            this.var_byte_arr_j[s] = ((DataInputStream)object).readByte();
        }
        SpriteSheet e2 = new SpriteSheet("tiles0");
        Sprite[] hArray = e2.sprites;
        this.var_int_t = hArray.length;
        e2 = null;
        byte[] imageBytes = AppCanvas.getFileBytes("buildings.png");
        Sprite[] hArray2 = new Sprite[9];
        for (s = 0; s <= 2; s = (short)((byte)(s + 1))) {
            byte[] byArray3 = new byte[imageBytes.length];
            System.arraycopy(imageBytes, 0, byArray3, 0, imageBytes.length);
            Sprite h2 = Sprite.fromByteArray(byArray3, (int)s);
            for (n = 0; n < 3; n = (int)((byte)(n + 1))) {
                hArray2[s * 3 + n] = new Sprite(h2, n, 0, 24, 24);
            }
        }
        this.var_h_arr_c = new Sprite[hArray.length + hArray2.length];
        System.arraycopy(hArray, 0, this.var_h_arr_c, 0, hArray.length);
        System.arraycopy(hArray2, hArray2.length - 3, this.var_h_arr_c, hArray.length, 3);
        System.arraycopy(hArray2, 0, this.var_h_arr_c, hArray.length + 3, hArray2.length - 3);
        e2 = new SpriteSheet("stiles0");
        this.var_h_arr_d = e2.sprites;
        e2 = null;
        this.var_h_arr_a = new Sprite[2];
        this.var_int_l = var_byte_arr_a[0];
        this.var_h_arr_a[0] = this.var_h_arr_c[var_byte_arr_a[0]];
        this.var_h_arr_a[1] = this.var_h_arr_c[var_byte_arr_a[1]];
        //AppCanvas.e();
        this.var_g_c = new g(this, (byte)3, 9);
        this.var_g_g = new g(this, (byte)5, 10);
    }

    public void d(int gameActionCode) {
        this.var_boolean_s = true;
        if (this.var_byte_a == 0 && this.var_byte_d == 1 && this.var_byte_i == 0) {
            boolean bl = false;
            this.var_java_lang_StringBuffer_a.append(gameActionCode);
            String string = this.var_java_lang_StringBuffer_a.toString();
            for (int j = 0; j < var_java_lang_String_arr_d.length; ++j) {
                if (string.equals(var_java_lang_String_arr_d[j])) {
                    if (j == 0) {
                        this.g();
                        continue;
                    }
                    if (j != 1) continue;
                    byte by = this.playerIndex_XX;
                    this.var_int_arr_b[by] = this.var_int_arr_b[by] + 1000;
                    continue;
                }
                if (!var_java_lang_String_arr_d[j].startsWith(string)) continue;
                bl = true;
            }
            if (!bl) {
                this.var_java_lang_StringBuffer_a = new StringBuffer();
            }
        }
    }

    public void q() {
        this.var_boolean_m = true;
    }

    public void n() {
    }

    public byte[] getGameSaveData() throws Exception {
        int n;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeByte(this.var_byte_a);
        dataOutputStream.writeByte(this.currentLevel);
        dataOutputStream.writeByte(this.var_byte_h);
        dataOutputStream.writeByte(this.var_byte_g);
        dataOutputStream.writeShort(this.var_short_d);
        dataOutputStream.writeByte(this.J);
        for (n = 0; n < this.var_byte_h; ++n) {
            dataOutputStream.writeByte(this.var_byte_arr_b[n]);
            dataOutputStream.writeShort(this.var_int_arr_b[n]);
        }
        for (n = 0; n < this.var_byte_arr_arr_e.length; ++n) {
            if (this.var_byte_arr_arr_a[this.var_byte_arr_arr_e[n][0]][this.var_byte_arr_arr_e[n][1]] < this.var_int_t) continue;
            dataOutputStream.writeByte(this.var_byte_arr_arr_a[this.var_byte_arr_arr_e[n][0]][this.var_byte_arr_arr_e[n][1]]);
        }
        dataOutputStream.writeByte(this.var_java_util_Vector_a.size());
        int n2 = this.var_java_util_Vector_a.size();
        for (n = 0; n < n2; ++n) {
            Unit c2 = this.var_java_util_Vector_a.elementAt(n);
            dataOutputStream.writeByte(c2.unitType);
            dataOutputStream.writeByte(c2.owner);
            dataOutputStream.writeByte(c2.var_byte_e);
            dataOutputStream.writeByte(c2.var_byte_b);
            dataOutputStream.writeByte(c2.quantity);
            dataOutputStream.writeByte(c2.var_short_d);
            dataOutputStream.writeShort(c2.var_short_b);
            dataOutputStream.writeShort(c2.mapX);
            dataOutputStream.writeShort(c2.mapY);
            dataOutputStream.writeShort(c2.var_int_b);
        }
        dataOutputStream.writeShort(this.currentLevelStep);
        dataOutputStream.writeInt((short)this.var_long_h);
        dataOutputStream.writeInt(this.var_int_i);
        dataOutputStream.writeByte(this.var_boolean_z ? 0 : 1);
        byte[] byArray = byteArrayOutputStream.toByteArray();
        dataOutputStream.close();
        return byArray;
    }

    private void loadSavedGameData(byte[] savedGameData) throws Exception {
        int n;
        int n2;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(savedGameData);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        this.var_byte_a = dataInputStream.readByte();
        this.currentLevel = dataInputStream.readByte();
        this.var_byte_h = dataInputStream.readByte();
        this.void_a(this.currentLevel);
        this.var_byte_g = dataInputStream.readByte();
        this.playerIndex_XX = this.var_byte_arr_d[this.var_byte_g];
        this.var_short_d = dataInputStream.readShort();
        this.J = dataInputStream.readByte();
        for (n2 = 0; n2 < this.var_byte_h; ++n2) {
            this.var_byte_arr_b[n2] = dataInputStream.readByte();
            this.var_int_arr_b[n2] = dataInputStream.readShort();
        }
        for (n2 = 0; n2 < this.var_byte_arr_arr_e.length; ++n2) {
            if (this.var_byte_arr_arr_a[this.var_byte_arr_arr_e[n2][0]][this.var_byte_arr_arr_e[n2][1]] < this.var_int_t) continue;
            this.var_byte_arr_arr_a[this.var_byte_arr_arr_e[n2][0]][this.var_byte_arr_arr_e[n2][1]] = dataInputStream.readByte();
        }
        this.var_java_util_Vector_a = new Vector<Unit>();
        n2 = dataInputStream.readByte();
        int n3 = n2;
        for (n = 0; n < n3; ++n) {
            byte unitType = dataInputStream.readByte();
            byte unitOwner = dataInputStream.readByte();
            byte by3 = dataInputStream.readByte();
            byte by4 = dataInputStream.readByte();
            byte unitQuantity = dataInputStream.readByte();
            byte by6 = dataInputStream.readByte();
            short s = dataInputStream.readShort();
            short unitPosX = dataInputStream.readShort();
            short unitPosY = dataInputStream.readShort();
            short s4 = dataInputStream.readShort();
            Unit unit = Unit.spawn(unitType, unitOwner, unitPosX, unitPosY);
            unit.var_byte_e = by3;
            unit.var_short_b = s;
            unit.var_short_d = by6;
            unit.var_byte_b = by4;
            unit.d();
            unit.quantity = unitQuantity;
            unit.var_int_b = s4;
            if (unitType == Unit.KING) {
                this.var_c_arr_a[unitOwner] = unit;
            }
            if (this.var_byte_a != 0) continue;
            if (unitType == Unit.KING) {
                if (unitOwner == PLAYER_BLUE) {
                    unit.var_java_lang_String_a = AppCanvas.getGameText(43);  // GALAMAR
                    continue;
                }
                if (by6 == 4) continue;
                unit.var_java_lang_String_a = AppCanvas.getGameText(44);  // VALADORN
                continue;
            }
            if (this.currentLevel != 2 || unitOwner != PLAYER_BLUE || unitType != Unit.LIZARD) continue;
            unit.var_java_lang_String_a = AppCanvas.getGameText(45);  // LIZARD CHIEF
        }
        if (this.currentLevel == 2) {
            for (n = 5; n < 10; ++n) {
                this.var_byte_arr_arr_a[n][12] = var_byte_arr_a[0];
            }
        }
        this.currentLevelStep = dataInputStream.readShort();
        this.var_long_h = dataInputStream.readInt();
        this.var_int_i = dataInputStream.readInt();
        this.var_boolean_z = dataInputStream.readByte() != 0;
        dataInputStream.close();
        this.void_c(this.var_c_arr_a[this.playerIndex_XX].mapX, this.var_c_arr_a[this.playerIndex_XX].mapY);
        this.void_a(this.var_c_arr_a[this.playerIndex_XX].var_short_b, (int)((SpriteSheet)this.var_c_arr_a[this.playerIndex_XX]).l);
    }

    public static void loadSettingsData() {
        try {
            byte[] settingsData = appCanvas.loadPersistentData("settings");
            for (int j = 0; j < AppCanvas.settings.length; ++j) {
                AppCanvas.settings[j] = (settingsData[0] & 1 << j) != 0;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void updateSettings() {
        try {
            boolean valuesChanged = false;
            for (int j = 0; j < AppCanvas.settings.length; ++j) {
                boolean isChoiceSelected = this.choiceGroupSettings.isSelected(j);
                if (isChoiceSelected == AppCanvas.settings[j]) continue;
                AppCanvas.settings[j] = isChoiceSelected;
                valuesChanged = true;
            }
            if (valuesChanged) {
                // It's a single byte but has to be an array to save it in a record
                byte[] settingsDataBytes = new byte[1];
                for (int n = 0; n < AppCanvas.settings.length; ++n) {
                    if (!AppCanvas.settings[n]) continue;
                    // Sets a single bit to 1
                    settingsDataBytes[0] = (byte)(settingsDataBytes[0] | 1 << n);
                }
                appCanvas.savePersistentData("settings", settingsDataBytes);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void b(Unit c2, Unit c3) {
        // TODO hint to rename other stuff
        if (AppCanvas.settings[AppCanvas.SETTINGS_FIGHT_ANIMATIONS]) {
            this.var_boolean_y = true;
            this.var_int_m = 0;
        } else {
            this.var_byte_i = (byte)13;
            this.var_int_n = 0;
            this.var_e_h.a(var_byte_arr_arr_d[0]);
        }
        this.var_c_i = c2;
        this.var_c_b = c3;
    }

    public void o() {
        SpriteSheet e2;
        if (this.var_c_i.quantity <= 0) {
            this.var_c_c = this.var_c_i;
            AppCanvas.playSound(3, 1);
        } else if (this.var_c_i.boolean_a()) {
            e2 = this.a(this.var_e_j, this.var_c_i.var_short_b + 3, ((SpriteSheet)this.var_c_i).l + 3, 0, 0, 1, 800);
            e2.a(var_byte_arr_c);
            this.a(this.var_e_r, this.var_c_i.var_short_b, ((SpriteSheet)this.var_c_i).l, 0, 0, 1, 50);
        }
        if (this.var_c_b.quantity <= 0) {
            this.var_c_c = this.var_c_b;
            AppCanvas.playSound(3, 1);
        } else if (this.var_c_i.a((short)128)) {
            e2 = this.a(this.var_e_j, this.var_c_b.var_short_b + 4, ((SpriteSheet)this.var_c_b).l + 3, 0, 0, 1, 800);
            e2.a(var_byte_arr_f);
            this.a(this.var_e_r, this.var_c_b.var_short_b, ((SpriteSheet)this.var_c_b).l, 0, 0, 1, 50);
            this.var_c_b.a((byte)1);
        } else if (this.var_c_b.boolean_a()) {
            e2 = this.a(this.var_e_j, this.var_c_b.var_short_b + 3, ((SpriteSheet)this.var_c_b).l + 3, 0, 0, 1, 800);
            e2.a(var_byte_arr_c);
            this.a(this.var_e_r, this.var_c_b.var_short_b, ((SpriteSheet)this.var_c_b).l, 0, 0, 1, 50);
            AppCanvas.playSound(-1, 1);
        }
        if (this.var_c_c != null) {
            this.a(this.var_e_r, this.var_c_c.var_short_b, ((SpriteSheet)this.var_c_c).l, 0, 0, 1, 50);
        }
        this.var_long_g = this.var_long_n;
        if (this.var_byte_arr_b[this.var_byte_g] == 0) {
            this.var_long_j = this.var_long_n;
            this.var_byte_b = (byte)6;
        }
        this.var_e_h.a(var_byte_arr_arr_d[0]);
        this.var_byte_i = 0;
        this.var_c_i.void_b();
        this.var_c_b = null;
        this.var_c_i = null;
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == this.commandBack) {
            Class_I.appCanvas.appDisplay.setCurrent((Displayable)appCanvas);
        } else if (command == this.commandNext) {
            ++this.D;
            // TODO what are these?
            Form form = new Form(AppCanvas.getGameText(7) + " - " + this.D);    // INSTRUCTIONS
            form.append(AppCanvas.getGameText(85 + this.D));
            if (this.D < 17) {
                form.addCommand(this.commandNext);
            }
            form.addCommand(this.commandBack);
            form.setCommandListener((CommandListener)this);
            Class_I.appCanvas.appDisplay.setCurrent((Displayable)form);
        } else if (command == this.commandOK) {
            this.updateSettings();
            if (!AppCanvas.settings[AppCanvas.SETTINGS_MUSIC]) {
                AppCanvas.stopFirstSound();
            }
            Class_I.appCanvas.appDisplay.setCurrent((Displayable)appCanvas);
        }
    }

    public SpriteSheet a(SpriteSheet e2, int n, int n2, int n3, int n4, int n5, int n6) {
        SpriteSheet e3 = SpriteSheet.a(e2, n3, n4, n5, n6, (byte)0);
        e3.void_b(n, n2);
        this.var_java_util_Vector_f.addElement(e3);
        return e3;
    }

    public void a(Unit unit) {
        this.var_boolean_t = true;
        this.var_boolean_r = !this.var_boolean_d;
        this.L = 0xFFFFFFF;
        this.var_byte_i = 1;
        this.var_boolean_v = true;
        this.a(this.var_byte_arr_arr_b, 0);
        unit.b(this.var_byte_arr_arr_b);
        this.var_boolean_h = true;
        this.var_boolean_j = false;
        this.var_e_h.a(var_byte_arr_arr_d[2]);
    }

    public void c() {
        if (this.var_byte_i == 3) {
            this.var_c_h.c(this.var_int_c, this.var_int_v);
            this.var_c_h.b(this.var_byte_arr_arr_b);
            this.a(this.var_c_h);
            this.var_boolean_v = true;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void a(int n, String string, g g2) throws Exception {
        boolean bl = true;
        // NEW GAME
        if (string.equals(AppCanvas.getGameText(1)) || g2 == this.var_g_f) {
            if (g2 == this.var_g_f) {
                if (n > this.levelsData[0]) return;
                this.currentLevel = n;
                this.var_g_f = null;
            } else {
                this.currentLevel = 0;
            }
            this.var_byte_a = 0;
            this.var_byte_arr_b[1] = 0;
            this.spriteGameTitle = null;
            this.spriteMacrospaceLogo = null;
            this.var_boolean_l = true;
            appCanvas.repaint();
            appCanvas.serviceRepaints();
            this.m();
            this.void_a(this.currentLevel);
            this.var_boolean_l = false;
            this.var_byte_i = 0;
        } else if (string.equals(AppCanvas.getGameText(2))) {   // SELECT LEVEL
            this.var_g_f = new g(this, (byte)0, 0);
            // TODO there are 7 levels, dehardcode the magic number
            String[] levelNames = new String[7];
            int levelsCount = levelNames.length;
            for (int j = 0; j < levelsCount; ++j) {
                // TODO this loads the name only for already completed levels, find the load/save code for the value
                levelNames[j] = j <= this.levelsData[0] ? AppCanvas.getGameText(48 + j) : "???";
            }
            this.var_g_f.showMenuOptions(levelNames);
            this.var_g_f.a((byte)1, AppCanvas.h, AppCanvas.f, g2, 48);
        } else if (string.equals(AppCanvas.getGameText(3))) {   // SAVE GAME
            appCanvas.savePersistentData("save", this.getGameSaveData());
            g.a(this, null, AppCanvas.getGameText(40), 1000, true);
            this.var_byte_i = 0;
        } else if (string.equals(AppCanvas.getGameText(4))) {   // LOAD GAME
            try {
                byte[] savedGameData = appCanvas.loadPersistentData("save");
                this.var_boolean_l = true;
                appCanvas.repaint();
                appCanvas.serviceRepaints();
                this.m();
                this.loadSavedGameData(savedGameData);
                if (this.var_byte_a == 0) {
                    this.a(true);
                }
                g.a(this, null, AppCanvas.getGameText(41), 1000, true); // GAME LOADED
                this.var_boolean_l = false;
            }
            catch (Exception exception) {
                g g3 = g.a(this, null, AppCanvas.getGameText(42), 1000, true);  // NO SAVED GAMES
                g3.var_g_a = g2;
            }
            this.var_byte_i = 0;
        } else if (string.equals(AppCanvas.getGameText(5))) {   // SKIRMISH
            this.var_g_a = new g(this, (byte)0, 0);
            this.var_g_a.showMenuOptions(skirmishMapNames);
            this.var_g_a.a((byte)1, AppCanvas.h, AppCanvas.f, g2, 48);
        } else if (g2 == this.var_g_a) {
            this.var_int_h = n;
            this.var_g_d = new g(this, (byte)0, 0);

            // TODO find all occurrences of stringArray in this file to fix the remaining errors
            // These menu options are displayed after you choose one of the skirmish maps
            // 1 PLAYER
            // 2 PLAYER
            // TODO in the game if you select the first option (1 player) you play against AI, useful info to find the check for this
            String[] skirmishNumPlayersMenuOptions = new String[2];
            for (int j = 0; j < 2; ++j) {
                // TODO is a loop really necessary for just 2 elements?
                skirmishNumPlayersMenuOptions[j] = j + 1 + " " + AppCanvas.getGameText(16);   // PLAYER
            }
            this.var_g_d.showMenuOptions(skirmishNumPlayersMenuOptions);
            this.var_g_d.a((byte)1, AppCanvas.h, AppCanvas.f, g2, 48);
        } else if (g2 == this.var_g_d) {
            this.var_byte_arr_b[1] = n == 0 ? (byte)0 : 1;
            this.var_byte_a = 1;
            this.J = 8;
            this.var_boolean_l = true;
            appCanvas.repaint();
            appCanvas.serviceRepaints();
            this.m();
            this.void_a(this.var_int_h);
            this.currentLevel = this.var_int_h;
            this.var_g_e.a((byte)0, 0, 0, null, 0);
            this.var_boolean_l = false;
        } else {
            if (string.equals(AppCanvas.getGameText(6))) {  // SETTINGS
                this.choiceGroupSettings = new ChoiceGroup("", Choice.MULTIPLE, AppCanvas.settingsNames, null);
                for (int j = 0; j < AppCanvas.settings.length; ++j) {
                    this.choiceGroupSettings.setSelectedIndex(j, AppCanvas.settings[j]);
                }
                Form settingsForm = new Form(AppCanvas.getGameText(6)); // SETTINGS
                settingsForm.append((Item)this.choiceGroupSettings);
                settingsForm.addCommand(this.commandOK);
                settingsForm.setCommandListener((CommandListener)this);
                Class_I.appCanvas.appDisplay.setCurrent((Displayable)settingsForm);
                return;
            }
            if (string.equals(AppCanvas.getGameText(7))) {  // INSTRUCTIONS
                this.D = 0;
                Form form = new Form(AppCanvas.getGameText(7));
                form.append(AppCanvas.getGameText(13));     // "Ancient Empires is a thrilling strategy game [...]""
                form.addCommand(this.commandBack);
                form.addCommand(this.commandNext);
                form.setCommandListener((CommandListener)this);
                Class_I.appCanvas.appDisplay.setCurrent((Displayable)form);
                return;
            }
            if (string.equals(AppCanvas.getGameText(8))) {  // ABOUT
                Form form = new Form(AppCanvas.getGameText(8));
                // "Ancient Empires" + "is a product of Macrospace [...]"
                form.append(AppCanvas.getGameText(0) + AppCanvas.getGameText(14));  
                form.addCommand(this.commandBack);
                form.setCommandListener((CommandListener)this);
                Class_I.appCanvas.appDisplay.setCurrent((Displayable)form);
                return;
            }
            if (string.equals(AppCanvas.getGameText(9))) {  // EXIT
                App.instance.notifyDestroyed();
            } else if (string.equals(AppCanvas.getGameText(26))) {  // MAIN MENU
                g g4 = new g(this, (byte)0, 0);
                g4.showMenuOptions(this.var_java_lang_String_arr_c);
                g4.a((byte)1, AppCanvas.h, AppCanvas.f, g2, 48);
            } else if (string.equals(AppCanvas.getGameText(27))) {  // MOVE
                this.var_boolean_d = false;
                this.a(this.var_c_h);
            } else if (string.equals(AppCanvas.getGameText(28))) {  // ATTACK
                this.a(this.var_byte_arr_arr_b, 0);
                this.var_byte_e = this.var_byte_i;
                this.var_byte_i = (byte)6;
                this.var_boolean_v = true;
                this.var_c_arr_b = this.var_c_h.a(this.var_c_h.mapX, (int)this.var_c_h.mapY, (byte)0);
                this.var_int_w = 0;
                this.var_boolean_h = true;
                this.var_boolean_j = true;
                this.var_c_h.a(this.var_byte_arr_arr_b, (int)this.var_c_h.mapX, (int)this.var_c_h.mapY);
                this.var_e_h.a(var_byte_arr_arr_d[1]);
                this.var_boolean_r = true;
                this.var_boolean_t = true;
            } else if (string.equals(AppCanvas.getGameText(29))) {  // BUY
                g g5 = new g(this, (byte)2, 8);
                g5.a((byte)8, 0, this.var_g_c.var_int_g, g2, 0);
            } else if (string.equals(AppCanvas.getGameText(30))) {  // END MOVE
                this.var_c_h.void_b();
                this.h();
                this.var_byte_i = 0;
            } else if (string.equals(AppCanvas.getGameText(32))) {  // END TURN
                g.a(this, null, AppCanvas.getGameText(39), 1000, true);
                this.var_byte_i = (byte)8;
                this.var_long_c = this.var_long_n;
            } else if (string.equals(AppCanvas.getGameText(33))) {
                if (this.a((int)this.var_c_h.mapX, (int)this.var_c_h.mapY, this.var_c_h)) {
                    this.void_a((int)this.var_c_h.mapX, (int)this.var_c_h.mapY, (int)this.var_c_h.owner);
                    g.a(this, null, AppCanvas.getGameText(38), 1000, true);
                    this.var_byte_i = (byte)9;
                    AppCanvas.playSound(-1, 1);
                    this.var_long_c = this.var_long_n;
                }
                this.var_c_h.void_b();
            } else if (string.equals(AppCanvas.getGameText(34))) {
                this.var_byte_i = (byte)7;
                this.var_c_arr_b = this.var_c_h.a(this.var_c_h.mapX, (int)this.var_c_h.mapY, (byte)1);
                this.var_boolean_h = true;
                this.var_boolean_j = true;
                this.var_c_h.a(this.var_byte_arr_arr_b, (int)this.var_c_h.mapX, (int)this.var_c_h.mapY);
                this.var_boolean_r = true;
            } else if (string.equals(AppCanvas.getGameText(35))) {
                g g6 = new g(this, (byte)7, 0);
                g6.a((byte)0, 0, 0, g2, 0);
            } else if (string.equals(AppCanvas.getGameText(36))) {
                this.var_g_e.a((byte)0, 0, 0, g2, 0);
            } else {
                this.var_c_h = this.c_a((int)((byte)n), (int)this.var_c_h.mapX, this.var_c_h.mapY);
                this.var_boolean_d = true;
                this.a(this.var_c_h);
            }
        }
        if (!bl) return;
        g2.a(true);
    }

    public Unit c_a(int unitType, int mapX, int mapY) {
        byte by = this.var_byte_g;
        this.var_int_arr_b[by] = this.var_int_arr_b[by] - Unit.unitsDataPrice[unitType];
        return Unit.spawn((byte)unitType, this.playerIndex_XX, mapX, mapY);
    }

    public SpriteSheet a(byte playerIndex, byte unitType) {
        return this.var_e_arr_arr_b[playerIndex][unitType];
    }

    // TODO write a tool to read level data
    public void void_a(int levelIndex) throws Exception {
        short s;
        String filename;
        this.var_java_util_Vector_c = new Vector<SpriteSheet>();
        this.var_short_d = 0;
        this.playerIndex_XX = PLAYER_BLUE;
        this.var_byte_g = 0;
        this.currentLevelStep = 0;
        this.var_c_arr_a = null;
        this.var_java_util_Vector_a = new Vector<Unit>();
        this.var_c_h = null;
        this.var_c_arr_b = null;
        this.var_c_d = null;
        this.var_g_g.var_c_a = null;
        this.var_byte_arr_arr_a = null;
        this.var_byte_arr_arr_b = null;
        this.var_int_arr_b = new int[this.var_byte_h];
        if (this.var_byte_a == 1) {
            this.var_int_arr_b[0] = 1000;
            this.var_int_arr_b[1] = 1000;
        } else {
            this.var_int_arr_b[0] = 300;
            this.var_int_arr_b[1] = 300;
        }
        this.var_boolean_v = true;
        //AppCanvas.readAssetsPackage("/1.pak");
        int n2 = levelIndex;
        if (this.var_byte_a == 0) {
            if (levelIndex == 6) {
                n2 = 5;
            }
            filename = "m" + n2;
        } else {
            filename = "s" + n2;
        }
        DataInputStream dataInputStream = new DataInputStream(AppCanvas.getFileBytesInputStream(filename));
        this.var_short_e = (short)dataInputStream.readInt();
        this.var_short_b = (short)dataInputStream.readInt();
        this.var_byte_arr_arr_a = new byte[this.var_short_e][this.var_short_b];
        this.var_byte_arr_arr_b = new byte[this.var_short_e][this.var_short_b];
        int n3 = 0;
        byte[][] byArray = new byte[30][3];
        for (s = 0; s < this.var_short_e; s = (short)(s + 1)) {
            for (short s2 = 0; s2 < this.var_short_b; s2 = (short)(s2 + 1)) {
                this.var_byte_arr_arr_a[s][s2] = dataInputStream.readByte();
                this.var_byte_arr_arr_b[s][s2] = 0;
                if (this.var_byte_arr_arr_a[s][s2] < this.var_int_t) continue;
                byArray[n3][0] = (byte)s;
                byArray[n3][1] = (byte)s2;
                byArray[n3][2] = (byte)this.int_a(s, (int)s2);
                ++n3;
            }
        }
        this.var_byte_arr_arr_e = new byte[n3][3];
        System.arraycopy(byArray, 0, this.var_byte_arr_arr_e, 0, n3);
        byArray = null;
        this.var_short_c = (short)(this.var_short_e * 24);
        this.var_short_i = (short)(this.var_short_b * 24);
        if (levelIndex == 6) {
            dataInputStream.close();
            dataInputStream = new DataInputStream(AppCanvas.getFileBytesInputStream("m" + levelIndex));
            dataInputStream.readInt();
            dataInputStream.readInt();
        }
        int n4 = dataInputStream.readInt();
        dataInputStream.skip(n4 * 4);
        int n5 = dataInputStream.readInt();
        this.var_c_arr_a = new Unit[this.var_byte_h];
        for (s = 0; s < n5; s = (short)(s + 1)) {
            byte by = dataInputStream.readByte();
            int n6 = dataInputStream.readShort() * 24 / 16;
            int n7 = dataInputStream.readShort() * 24 / 16;
            byte unitType = (byte)(by % 11);
            byte unitOwner = (byte)(by / 11);
            Unit c2 = Unit.spawn(unitType, unitOwner, n6 / 24, n7 / 24);
            if (unitType != Unit.KING) continue;
            this.var_c_arr_a[unitOwner] = c2;
        }
        dataInputStream.close();
        //AppCanvas.e();
        if (this.var_byte_a == 0) {
            // Level name (e.g. REGROUP) and its objective, shown at level start
            this.var_g_e = g.a(this, AppCanvas.getGameText(48 + this.currentLevel), AppCanvas.getGameText(55 + this.currentLevel), -1, false);
            // 'The Kingdom of Thorin is divided. Betrayed by his own twin brother Valadorn [...]''
            this.var_g_b = g.a(this, null, AppCanvas.getGameText(103 + this.currentLevel), -1, false);
            this.a(false);
            this.void_b(500);
            AppCanvas.playSound(-1, 1);
        } else {
            this.var_g_e = g.a(this, AppCanvas.getGameText(36), AppCanvas.getGameText(62), -1, false);
        }
        this.var_boolean_w = false;
        if (this.var_c_arr_a[1] != null) {
            this.K = this.var_c_arr_a[1].mapX;
            this.var_int_u = this.var_c_arr_a[1].mapY;
        } else {
            this.K = 0;
            this.var_int_u = 0;
        }
        this.void_c(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
        this.void_a(this.var_c_arr_a[0].var_short_b, (int)((SpriteSheet)this.var_c_arr_a[0]).l);
    }

    public void h() {
        this.var_int_w = 0;
        this.var_c_h = null;
        this.var_c_arr_b = new Unit[0];
        this.a(this.var_byte_arr_arr_b, 0);
        this.var_boolean_h = false;
        this.var_boolean_j = false;
    }

    public void a(byte[][] byArray, int n) {
        for (int j = 0; j < this.var_short_e; ++j) {
            for (int k = 0; k < this.var_short_b; ++k) {
                byArray[j][k] = (byte)n;
            }
        }
    }

    public void c(Unit unit) {
        this.b((Unit)null);
        this.a(this.var_byte_arr_arr_b, 0);
        this.var_boolean_h = false;
        if (this.var_boolean_o) {
            return;
        }
        if (this.var_byte_arr_b[this.var_byte_g] == 1) {
            this.var_boolean_n = true;
            this.var_e_h.a(var_byte_arr_arr_d[0]);
            this.var_byte_i = (byte)3;
            this.var_g_h = new g(this, (byte)0, 8);
            this.var_g_h.showMenuOptions(this.getUnitPossibleActions(unit, (byte)0));
            this.var_g_h.a((byte)8, 0, this.var_g_c.var_int_g, null, 0);
            AppCanvas.playSound(-1, 1);
        } else if (this.var_byte_arr_b[this.var_byte_g] == 0) {
            this.var_byte_b = (byte)4;
        }
    }

    private String[] getUnitPossibleActions(Unit unit, byte by) {
        Vector<String> vector = new Vector<String>();
        if (by == 1 && this.var_c_h.a((short)4) && this.getTerrainType_ZZ(this.var_c_h.mapX, (int)this.var_c_h.mapY) == f.TERRAIN_CASTLE) {
            vector.addElement(AppCanvas.getGameText(29));   // BUY
        }
        if (this.a((int)unit.mapX, (int)unit.mapY, unit)) {
            vector.addElement(AppCanvas.getGameText(33));   // OCCUPY
        }
        if ((by == 1 || unit.unitType != Unit.CATAPULT) && unit.a(unit.mapX, (int)unit.mapY, (byte)0).length > 0) {
            vector.addElement(AppCanvas.getGameText(28));   // ATTACK
        }
        if (unit.a((short)32) && unit.a(unit.mapX, (int)unit.mapY, (byte)1).length > 0) {
            vector.addElement(AppCanvas.getGameText(34));   // RAISE
        }
        if (by == 1) {
            vector.addElement(AppCanvas.getGameText(27));   // MOVE
        } else {
            vector.addElement(AppCanvas.getGameText(30));   // END MOVE
        }
        
        String[] stringArray = new String[vector.size()];
        vector.copyInto(stringArray);
        return stringArray;
    }

    public void j() {
        switch (this.var_int_p) {
            case 0: {
                if (this.var_int_m < 15) {
                    ++this.var_int_m;
                }
                if (this.var_long_n < 1500L) break;
                this.var_int_p = 1;
                this.var_boolean_c = true;
                this.var_int_m = 0;
                break;
            }
            case 1: {
                if (this.var_int_m >= 15) {
                    AppCanvas.playSound(0, 1);
                    this.spriteMacrospaceLogo = null;
                    this.var_int_m = 0;
                    ++this.var_int_p;
                    break;
                }
                ++this.var_int_m;
                break;
            }
            case 2: {
                if (this.var_int_m < 15) {
                    ++this.var_int_m;
                    ++this.N;
                    break;
                }
                if (this.var_long_n % 100L == 0L) {
                    this.var_boolean_p = !this.var_boolean_p;
                }
                if (Class_I.appCanvas.pressedKeysActions == 0 || !this.var_boolean_c || this.var_int_g != 0) break;
                g g2 = new g(this, (byte)0, 0);
                g2.showMenuOptions(this.var_java_lang_String_arr_e);
                g2.a((byte)1, AppCanvas.h, AppCanvas.f, null, 48);
                Class_I.appCanvas.pressedKeysActions = 0;
            }
        }
    }

    public void e() throws Exception {
        int n;
        this.var_long_n += 50L;
        if (this.var_byte_d == 2) {
            this.b();
            return;
        }
        this.void_a();
        if (this.var_int_s != -1) {
            if (AppCanvas.settings[AppCanvas.SETTINGS_HELP]) {
                // This is the first tutorial string - "Select and move units by pressing 5 [...]""
                this.var_g_b = g.a(this, AppCanvas.getGameText(85 + this.var_int_s), PORTRAIT_NONE, (byte)2);
            }
            this.var_int_s = -1;
        }
        if (this.var_int_q == 0) {
            int n2;
            if (this.var_byte_d == 0) {
                this.j();
            } else {
                if (this.var_long_n - this.var_long_e >= 300L) {
                    this.var_boolean_a = !this.var_boolean_a;
                    this.var_long_e = this.var_long_n;
                }
                if (this.var_boolean_y) {
                    ++this.var_int_m;
                    if (this.var_int_m > 15) {
                        if (this.var_byte_i == 10) {
                            ++this.currentLevel;
                            if (this.currentLevel > this.levelsData[0]) {
                                this.levelsData[0] = (byte)this.currentLevel;
                                appCanvas.savePersistentData("levels", this.levelsData);
                            }
                            this.void_a(this.currentLevel);
                            this.var_byte_i = 0;
                        } else if (this.var_byte_i == 11) {
                            this.var_int_r = 0;
                            this.var_long_c = this.var_long_n;
                        } else {
                            this.a(this.var_c_i, this.var_c_b);
                            this.var_c_g = null;
                            this.h();
                        }
                        this.var_boolean_y = false;
                    } else {
                        appCanvas.repaint();
                        appCanvas.serviceRepaints();
                    }
                    return;
                }
                if (this.var_java_util_Vector_b != null) {
                    this.H = (this.H + 1) % 12;
                }
                if (this.var_boolean_h) {
                    this.L += this.E;
                    if (this.L >= 0xFFFFFF) {
                        this.L = 0xFFFFFF;
                        this.E = -986895;
                    } else if (this.L <= 0x9F9F9F) {
                        this.L = 0x9F9F9F;
                        this.E = 986895;
                    }
                }
                if (this.var_boolean_n && this.var_long_n - this.var_long_k >= 200L) {
                    this.var_e_h.c();
                    this.var_long_k = this.var_long_n;
                }
                int n3 = this.var_short_h * 24;
                int n4 = this.var_short_g * 24;
                int n5 = this.var_e_h.var_short_b;
                int n6 = this.var_e_h.l;
                if (n3 > n5) {
                    n5 += 8;
                } else if (n3 < n5) {
                    n5 -= 8;
                }
                if (n4 > n6) {
                    n6 += 8;
                } else if (n4 < n6) {
                    n6 -= 8;
                }
                this.var_e_h.void_b(n5, n6);
                if (this.var_byte_i == 8) {
                    if (this.var_int_k == 0 && this.var_g_i == null) {
                        this.l();
                        this.var_byte_i = 0;
                    }
                } else if (this.var_byte_i == 9) {
                    if (this.var_g_i == null) {
                        this.var_byte_i = 0;
                    }
                } else if (this.var_byte_i == 11) {
                    if (!this.var_boolean_y && this.var_int_r == 0 && this.var_long_n - this.var_long_c >= 1000L) {
                        g temmp_g_XXX = new g(this, (byte)0, 0);
                        temmp_g_XXX.showMenuOptions(this.var_java_lang_String_arr_e);
                        temmp_g_XXX.a((byte)1, AppCanvas.h, AppCanvas.f, null, 48);
                        temmp_g_XXX.var_boolean_g = false;
                        this.var_int_r = 1;
                    }
                } else if (this.var_byte_i == 10) {
                    if (this.var_g_i == null) {
                        this.var_boolean_y = true;
                        this.var_int_m = 0;
                    }
                } else if (this.var_byte_i == 13) {
                    if (this.var_int_n == 0) {
                        this.var_c_i.a(this.var_c_b);
                        this.var_c_b.b(400);
                        this.a(this.var_e_d, this.var_c_b.var_short_b, ((SpriteSheet)this.var_c_b).l, 0, 0, 2, 50);
                        this.var_long_d = this.var_long_n;
                        ++this.var_int_n;
                    } else if (this.var_int_n == 1) {
                        if (this.var_long_n - this.var_long_d >= 800L) {
                            this.void_c(this.var_c_i.mapX, this.var_c_i.mapY);
                            if (this.var_c_b.a(this.var_c_i, (int)this.var_c_i.mapX, (int)this.var_c_i.mapY)) {
                                this.var_c_b.a(this.var_c_i);
                                this.var_c_i.b(400);
                                this.a(this.var_e_d, this.var_c_i.var_short_b, ((SpriteSheet)this.var_c_i).l, 0, 0, 2, 50);
                                this.var_long_d = this.var_long_n;
                                ++this.var_int_n;
                            } else {
                                this.o();
                            }
                        }
                    } else if (this.var_long_n - this.var_long_d >= 800L) {
                        this.o();
                    }
                } else if (this.var_c_c != null) {
                    if (this.var_long_n - this.var_long_g >= 300L) {
                        this.a(this.var_e_l, this.var_c_c.var_short_b, ((SpriteSheet)this.var_c_c).l, 0, -3, 1, 100);
                        if (this.var_byte_a == 0 && this.var_c_arr_a[1] != null && this.var_c_c == this.var_c_arr_a[1] && this.currentLevel != 4) {
                            if (this.currentLevel != 6) {
                                this.var_java_util_Vector_a.removeElement(this.var_c_c);
                                this.var_c_arr_a[1] = null;
                            }
                        } else if (this.var_c_c.unitType == Unit.SKELETON) {
                            this.var_java_util_Vector_a.removeElement(this.var_c_c);
                        } else {
                            this.var_c_c.var_byte_e = (byte)3;
                            this.var_c_c.var_int_b = this.var_short_d;
                        }
                        this.var_c_c = null;
                        this.var_g_g.b();
                    }
                } else if (this.var_c_e != null) {
                    if (this.var_long_n - this.var_long_i >= 400L) {
                        this.var_java_util_Vector_a.removeElement(this.var_c_e);
                        // TODO rename second parameter to playerIndex_ZZ
                        Unit unitSkeleton = Unit.spawn(Unit.SKELETON, this.var_byte_f, this.var_c_e.mapX, this.var_c_e.mapY);
                        unitSkeleton.void_b();
                        this.var_c_e = null;
                    }
                } else if (this.var_byte_arr_b[this.var_byte_g] == 0) {
                    this.p();
                } else if (Class_I.appCanvas.var_a_a == this && this.var_int_g == 0) {
                    if (this.var_boolean_t && appCanvas.boolean_c(1024)) {
                        appCanvas.handleKeyPressedAction(16);
                        appCanvas.handleKeyReleasedAction(1024);
                    }
                    if (this.var_byte_i == 6 || this.var_byte_i == 7) {
                        if (appCanvas.boolean_c(4)) {
                            --this.var_int_w;
                            if (this.var_int_w < 0) {
                                this.var_int_w = this.var_c_arr_b.length - 1;
                            }
                            appCanvas.handleKeyReleasedAction(4);
                            this.var_boolean_v = true;
                        } else if (appCanvas.boolean_c(8)) {
                            ++this.var_int_w;
                            if (this.var_int_w >= this.var_c_arr_b.length) {
                                this.var_int_w = 0;
                            }
                            appCanvas.handleKeyReleasedAction(8);
                            this.var_boolean_v = true;
                        } else if (appCanvas.boolean_c(1)) {
                            --this.var_int_w;
                            if (this.var_int_w < 0) {
                                this.var_int_w = this.var_c_arr_b.length - 1;
                            }
                            appCanvas.handleKeyReleasedAction(1);
                            this.var_boolean_v = true;
                        } else if (appCanvas.boolean_c(2)) {
                            ++this.var_int_w;
                            if (this.var_int_w >= this.var_c_arr_b.length) {
                                this.var_int_w = 0;
                            }
                            appCanvas.handleKeyReleasedAction(2);
                            this.var_boolean_v = true;
                        }
                        this.void_c(this.var_c_arr_b[this.var_int_w].mapX, this.var_c_arr_b[this.var_int_w].mapY);
                        if (this.var_boolean_v) {
                            this.var_g_g.b();
                        }
                        if (appCanvas.boolean_c(16)) {
                            if (this.var_byte_i == 6) {
                                this.b(this.var_c_h, this.var_c_arr_b[this.var_int_w]);
                            } else if (this.var_byte_i == 7) {
                                this.void_a(this.var_c_arr_b[this.var_int_w], this.playerIndex_XX);
                                this.var_c_h.void_b();
                                this.var_byte_i = 0;
                            }
                            this.h();
                            this.var_boolean_r = false;
                            this.var_boolean_t = false;
                        }
                        this.var_boolean_v = false;
                    } else {
                        if (this.var_long_n - this.var_long_a >= 150L && this.var_e_h.var_short_b % 24 == 0 && this.var_e_h.l % 24 == 0) {
                            if (appCanvas.boolean_c(4)) {
                                if (this.var_boolean_s || appCanvas.boolean_a(4)) {
                                    if (this.var_short_h > 0) {
                                        this.var_short_h = (short)(this.var_short_h - 1);
                                    }
                                    this.var_boolean_s = false;
                                    this.var_boolean_v = true;
                                    this.var_long_a = this.var_long_n;
                                }
                            } else if (appCanvas.boolean_c(8) && (this.var_boolean_s || appCanvas.boolean_a(8))) {
                                if (this.var_short_h < this.var_short_e - 1) {
                                    this.var_short_h = (short)(this.var_short_h + 1);
                                }
                                this.var_boolean_s = false;
                                this.var_boolean_v = true;
                                this.var_long_a = this.var_long_n;
                            }
                            if (appCanvas.boolean_c(1)) {
                                if (this.var_boolean_s || appCanvas.boolean_a(1)) {
                                    if (this.var_short_g > 0) {
                                        this.var_short_g = (short)(this.var_short_g - 1);
                                    }
                                    this.var_boolean_s = false;
                                    this.var_boolean_v = true;
                                    this.var_long_a = this.var_long_n;
                                }
                            } else if (appCanvas.boolean_c(2) && (this.var_boolean_s || appCanvas.boolean_a(2))) {
                                if (this.var_short_g < this.var_short_b - 1) {
                                    this.var_short_g = (short)(this.var_short_g + 1);
                                }
                                this.var_boolean_s = false;
                                this.var_boolean_v = true;
                                this.var_long_a = this.var_long_n;
                            }
                            if (this.var_boolean_v) {
                                if (this.var_byte_i == 1) {
                                    if (this.var_byte_arr_arr_b[this.var_short_h][this.var_short_g] > 0) {
                                        this.var_java_util_Vector_b = this.var_c_h.a(this.var_c_h.mapX, this.var_c_h.mapY, this.var_short_h, this.var_short_g);
                                    }
                                } else {
                                    this.var_g_g.b();
                                }
                            }
                            this.var_boolean_v = false;
                        }
                        if (this.var_byte_i == 1) {
                            if ((Class_I.appCanvas.pressedKeysActions & 0x10) != 0 && this.var_c_h != null) {
                                Unit unit_c_a = this.c_a((int)this.var_short_h, (int)this.var_short_g, (byte)0);
                                if (this.var_byte_arr_arr_b[this.var_short_h][this.var_short_g] > 0 && (unit_c_a == null || unit_c_a == this.var_c_h)) {
                                    this.var_int_c = this.var_c_h.mapX;
                                    this.var_int_v = this.var_c_h.mapY;
                                    this.var_c_h.void_a(this.var_short_h, this.var_short_g);
                                    this.b(this.var_c_h);
                                    this.var_boolean_n = false;
                                    this.var_boolean_h = false;
                                    this.var_java_util_Vector_b = null;
                                    this.var_boolean_r = false;
                                    this.var_boolean_t = false;
                                    this.var_byte_i = (byte)2;
                                    // TODO this -1 should be an error...
                                    AppCanvas.playSound(-1, 1);
                                }
                                appCanvas.handleKeyReleasedAction(16);
                            }
                        } else if (this.var_byte_i == 0) {
                            if ((Class_I.appCanvas.pressedKeysActions & 0x100) != 0) {
                                this.var_c_h = this.c_a((int)this.var_short_h, (int)this.var_short_g, (byte)0);
                                if (this.var_c_h != null) {
                                    g temp_g_YYY = new g(this, (byte)4, 0);
                                    temp_g_YYY.a((byte)0, 0, 0, null, 0);
                                }
                                appCanvas.handleKeyReleasedAction(256);
                            } else if (appCanvas.boolean_c(512)) {
                                if (this.var_c_arr_a[this.playerIndex_XX] != null) {
                                    this.void_c(this.var_c_arr_a[this.playerIndex_XX].mapX, this.var_c_arr_a[this.playerIndex_XX].mapY);
                                    this.void_a(this.var_c_arr_a[this.playerIndex_XX].var_short_b + 12, ((SpriteSheet)this.var_c_arr_a[this.playerIndex_XX]).l + 12);
                                }
                            } else if ((Class_I.appCanvas.pressedKeysActions & 0x20) != 0) {
                                this.var_c_h = this.c_a((int)this.var_short_h, (int)this.var_short_g, (byte)0);
                                if (this.var_c_h != null) {
                                    this.a(this.var_byte_arr_arr_b, 0);
                                    this.var_c_h.a(this.var_byte_arr_arr_b);
                                    this.var_boolean_j = true;
                                    this.var_boolean_h = true;
                                }
                                appCanvas.handleKeyReleasedAction(32);
                            } else if (!appCanvas.boolean_c(128) && !appCanvas.boolean_c(64) && (appCanvas.boolean_c(16) || appCanvas.boolean_c(1024))) {
                                this.var_c_h = this.c_a((int)this.var_short_h, (int)this.var_short_g, (byte)0);
                                if (this.var_c_h != null && this.var_c_h.var_byte_e == 0 && this.var_c_h.owner == this.playerIndex_XX) {
                                    String[] unitActionsMenuOptions = this.getUnitPossibleActions(this.var_c_h, (byte)1);
                                    if (unitActionsMenuOptions.length > 1) {
                                        this.var_g_h = new g(this, (byte)0, 8);
                                        this.var_g_h.showMenuOptions(unitActionsMenuOptions);
                                        this.var_g_h.a((byte)8, 0, 40, null, 0);
                                        AppCanvas.playSound(-1, 1);
                                    } else {
                                        this.var_boolean_d = false;
                                        this.a(this.var_c_h);
                                    }
                                } else {
                                    this.var_c_h = null;
                                    // These are shown when you open the menu in-game on a non-unit tile
                                    // TODO document the conditions to open this menu
                                    String [] menuOptions = new String[]{
                                        AppCanvas.getGameText(32),  // END TURN
                                        AppCanvas.getGameText(35),  // MAP
                                        AppCanvas.getGameText(36),  // OBJECTIVE
                                        AppCanvas.getGameText(26)}; // MAIN MENU
                                    this.var_g_h = new g(this, (byte)0, 8);
                                    this.var_g_h.showMenuOptions(menuOptions);
                                    this.var_g_h.a((byte)8, 0, 40, null, 0);
                                    AppCanvas.playSound(-1, 1);
                                }
                                appCanvas.handleKeyReleasedAction(16);
                                appCanvas.handleKeyReleasedAction(1024);
                            }
                        }
                    }
                }
                if (this.var_byte_i == 0 && this.M == 0) {
                    if (this.var_g_c.var_byte_e == 3) {
                        this.var_g_c.a(this.var_g_c.var_byte_d, 0, 0, null, 0);
                    }
                    if (this.var_g_g.var_byte_e == 3) {
                        this.var_g_g.a(this.var_g_g.var_byte_d, 0, 0, null, 0);
                    }
                } else if (this.M > 0 || this.var_byte_i == 11) {
                    this.var_g_c.a(true);
                    this.var_g_g.a(true);
                } else {
                    if (this.var_g_c.var_byte_e == 2) {
                        this.var_g_c.a(false);
                    }
                    if (this.var_byte_i == 6) {
                        if (this.var_g_g.var_byte_e == 3) {
                            this.var_g_g.a(this.var_g_g.var_byte_d, 0, 0, null, 0);
                        }
                    } else if (this.var_g_g.var_byte_e == 2) {
                        this.var_g_g.a(false);
                    }
                }
                n2 = this.var_java_util_Vector_a.size();
                for (n = 0; n < n2; ++n) {
                    this.var_java_util_Vector_a.elementAt(n).void_a();
                }
                if (this.var_long_n - this.var_long_l >= 300L) {
                    this.var_int_y = (this.var_int_y + 1) % this.var_h_arr_a.length;
                    this.var_h_arr_c[this.var_int_l] = this.var_h_arr_a[this.var_int_y];
                    this.var_long_l = this.var_long_n;
                }
                this.d();
            }
            if (this.var_boolean_r && appCanvas.boolean_c(2048)) {
                if (this.var_byte_i == 1) {
                    this.var_byte_i = 0;
                    this.a(this.var_byte_arr_arr_b, 0);
                    this.var_boolean_h = false;
                    this.var_boolean_j = false;
                    this.var_java_util_Vector_b = null;
                    this.var_e_h.a(var_byte_arr_arr_d[0]);
                    this.void_c(this.var_c_h.mapX, this.var_c_h.mapY);
                    this.var_c_h = null;
                } else if (this.var_byte_i == 6) {
                    this.var_byte_i = this.var_byte_e;
                    this.var_boolean_h = false;
                    this.var_g_h.a((byte)8, 0, 40, null, 0);
                    this.var_e_h.a(var_byte_arr_arr_d[0]);
                    this.void_c(this.var_c_h.mapX, this.var_c_h.mapY);
                }
                appCanvas.handleKeyReleasedAction(2048);
                this.var_boolean_r = false;
                this.var_boolean_t = false;
            }
            for (n = this.var_java_util_Vector_c.size() - 1; n >= 0; --n) {
                SpriteSheet e2 = this.var_java_util_Vector_c.elementAt(n);
                e2.void_a();
                if (e2.var_boolean_d) continue;
                this.var_java_util_Vector_c.removeElement(e2);
            }
            n2 = this.var_java_util_Vector_f.size();
            for (n = 0; n < n2; ++n) {
                this.var_java_util_Vector_c.addElement(this.var_java_util_Vector_f.elementAt(n));
            }
            this.var_java_util_Vector_f.removeAllElements();
        }
        for (n = this.var_java_util_Vector_e.size() - 1; n >= 0; --n) {
            this.var_java_util_Vector_e.elementAt(n).d();
        }
        appCanvas.repaint();
        appCanvas.serviceRepaints();
    }

    public void void_a(Unit c2, byte by) {
        this.var_c_e = c2;
        this.var_byte_f = by;
        this.a(this.var_e_r, c2.var_short_b - 8, ((SpriteSheet)c2).l - 8, 1, 1, 3, 50);
        this.a(this.var_e_r, c2.var_short_b + 8, ((SpriteSheet)c2).l - 8, -1, 1, 3, 50);
        this.a(this.var_e_r, c2.var_short_b - 8, ((SpriteSheet)c2).l + 8, 1, -1, 3, 50);
        this.a(this.var_e_r, c2.var_short_b + 8, ((SpriteSheet)c2).l + 8, -1, -1, 3, 50);
        this.var_long_i = this.var_long_n;
    }

    private void d() {
        if (this.var_c_d == null) {
            this.d(this.var_e_h.var_short_b + 12, this.var_e_h.l + 12);
        } else {
            this.d(this.var_c_d.var_short_b + 12, ((SpriteSheet)this.var_c_d).l + 12);
        }
    }

    public void b(Unit c2) {
        this.var_c_d = c2;
        this.var_int_g = c2 == null ? --this.var_int_g : ++this.var_int_g;
    }

    public boolean boolean_c(int n, int n2) {
        return this.var_short_f == this.int_b(n) && this.var_short_a == this.int_a(n2);
    }

    public boolean boolean_b(int n, int n2) {
        return this.boolean_c(n * 24 + 12, n2 * 24 + 12);
    }

    public int int_b(int n) {
        int n2;
        if (this.var_short_c > AppCanvas.width2) {
            n2 = AppCanvas.h - n;
            if (n2 > 0) {
                n2 = 0;
            } else if (n2 < AppCanvas.width2 - this.var_short_c) {
                n2 = (short)(AppCanvas.width2 - this.var_short_c);
            }
        } else {
            n2 = (AppCanvas.width2 - this.var_short_c) / 2;
        }
        return n2;
    }

    public int int_a(int n) {
        int n2;
        if (this.var_short_i > AppCanvas.height2) {
            n2 = AppCanvas.f - n;
            if (n2 > 0) {
                n2 = 0;
            } else if (n2 < AppCanvas.height2 - this.var_short_i) {
                n2 = (short)(AppCanvas.height2 - this.var_short_i);
            }
        } else {
            n2 = (AppCanvas.height2 - this.var_short_i) / 2;
        }
        return n2;
    }

    public void void_a(int n, int n2) {
        this.var_short_f = (short)this.int_b(n);
        this.var_short_a = (short)this.int_a(n2);
    }

    public void d(int n, int n2) {
        int n3;
        int n4 = this.int_b(n);
        int n5 = this.int_a(n2);
        int n6 = n4 - this.var_short_f;
        int n7 = n5 - this.var_short_a;
        if (n6 != 0) {
            n3 = n6 / 4;
            if (n6 < 0) {
                if (n3 > -1) {
                    n3 = -1;
                } else if (n3 < -8) {
                    n3 = -8;
                }
            } else if (n3 < 1) {
                n3 = 1;
            } else if (n3 > 8) {
                n3 = 8;
            }
            this.var_short_f = (short)(this.var_short_f + n3);
        }
        if (n7 != 0) {
            n3 = n7 / 4;
            if (n7 < 0) {
                if (n3 > -1) {
                    n3 = -1;
                } else if (n3 < -8) {
                    n3 = -8;
                }
            } else if (n3 < 1) {
                n3 = 1;
            } else if (n3 > 8) {
                n3 = 8;
            }
            this.var_short_a = (short)(this.var_short_a + n3);
        }
    }

    public void void_c(int n, int n2) {
        this.var_short_h = (short)n;
        this.var_short_g = (short)n2;
        this.var_e_h.void_b(n * 24, n2 * 24);
        this.var_g_g.b();
    }

    public void d(Graphics graphics) {
        short s = (short)(-this.var_short_f / 24);
        short s2 = (short)(-this.var_short_a / 24);
        if (s < 0) {
            s = 0;
        }
        if (s2 < 0) {
            s2 = 0;
        }
        short s3 = (short)(s + AppCanvas.width2 / 24);
        short s4 = (short)(s2 + AppCanvas.height2 / 24);
        if (AppCanvas.width2 % 24 != 0) {
            s3 = (short)(s3 + 1);
        }
        if (AppCanvas.height2 % 24 != 0) {
            s4 = (short)(s4 + 1);
        }
        if (s3 >= this.var_short_e) {
            s3 = (short)(this.var_short_e - 1);
        }
        if (s4 >= this.var_short_b) {
            s4 = (short)(this.var_short_b - 1);
        }
        int n = this.var_short_f < 0 ? this.var_short_f % 24 : this.var_short_f;
        int n2 = this.var_short_a < 0 ? this.var_short_a % 24 : this.var_short_a;
        if (this.var_boolean_j) {
            graphics.setColor(this.L & 0xFF0000);
        } else {
            graphics.setColor(this.L);
        }
        for (short s5 = s2; s5 <= s4; s5 = (short)(s5 + 1)) {
            int n3 = n;
            for (short s6 = s; s6 <= s3; s6 = (short)(s6 + 1)) {
                byte by = this.var_byte_arr_arr_a[s6][s5];
                this.var_h_arr_c[by].draw(graphics, n3, n2);
                if (this.var_byte_arr_j[by] == 8) {
                    this.var_h_arr_c[by + 1].draw(graphics, n3, n2 - 24);
                }
                if (this.var_boolean_h && this.var_byte_arr_arr_b[s6][s5] > 0) {
                    if (s6 > 0 && this.var_byte_arr_arr_b[s6 - 1][s5] <= 0) {
                        graphics.fillRect(n3, n2, 2, 24);
                    }
                    if (s6 < this.var_short_e - 1 && this.var_byte_arr_arr_b[s6 + 1][s5] <= 0) {
                        graphics.fillRect(n3 + 24 - 2, n2, 2, 24);
                    }
                    if (s5 > 0 && this.var_byte_arr_arr_b[s6][s5 - 1] <= 0) {
                        graphics.fillRect(n3, n2, 24, 2);
                    }
                    if (s5 < this.var_short_b - 1 && this.var_byte_arr_arr_b[s6][s5 + 1] <= 0) {
                        graphics.fillRect(n3, n2 + 24 - 2, 24, 2);
                    }
                }
                n3 += 24;
            }
            n2 += 24;
        }
    }

    public void c(Graphics graphics) {
        graphics.setFont(AppCanvas.fontSmallPlain);
        graphics.setColor(0);
        graphics.fillRect(0, 0, AppCanvas.width2, AppCanvas.height2);
        graphics.setColor(0xFFFFFF);
        // LOADING...
        graphics.drawString(AppCanvas.getGameText(24), AppCanvas.width2 / 2, (AppCanvas.height2 - AppCanvas.fontSmallPlain.getHeight()) / 2, 17);
    }

    public void b(Graphics graphics) {
        if (this.var_int_p == 0) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, Class_I.appCanvas.width, Class_I.appCanvas.height);
            Class_I.a(graphics, 0, this.var_int_m, 15, 0, this.spriteMacrospaceLogo, (Class_I.appCanvas.width - this.spriteMacrospaceLogo.width) / 2, (Class_I.appCanvas.height - this.spriteMacrospaceLogo.height) / 2, 0, 0);
        } else if (this.var_int_p == 1) {
            Class_I.a(graphics, 0xFFFFFF, this.var_int_m, 15, 0, null, (Class_I.appCanvas.width - this.spriteMacrospaceLogo.width) / 2, (Class_I.appCanvas.height - this.spriteMacrospaceLogo.height) / 2, this.spriteMacrospaceLogo.width, this.spriteMacrospaceLogo.height);
        } else if (this.var_int_p == 2) {
            graphics.setColor(-16777216);
            graphics.fillRect(0, 0, Class_I.appCanvas.width, Class_I.appCanvas.height);
            if (this.var_int_m >= 15) {
                this.spriteGameTitle.draw(graphics, Class_I.appCanvas.width - this.spriteGameTitle.width >> 1, (Class_I.appCanvas.height - this.spriteGameTitle.height) / 3);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(AppCanvas.fontSmallPlain);
                // TODO there's a pretty similar method in 'a' class
                if (this.var_boolean_p && this.var_int_g == 0) {
                    // PRESS ANY KEY
                    graphics.drawString(AppCanvas.getGameText(25), AppCanvas.h, AppCanvas.height2 * 3 / 4, 17);
                }
                // ©MACROSPACE LTD.
                graphics.drawString(AppCanvas.getGameText(15), AppCanvas.h, AppCanvas.height2 - 1, 33);
            } else {
                if (32 * this.N <= 255) {
                    graphics.setColor(0xFFFFFF - 0x202020 * this.N);
                    graphics.fillRect(0, 0, AppCanvas.width2, AppCanvas.height2);
                } else {
                    graphics.setColor(0xFFFFFF);
                }
                Class_I.a(graphics, 0xFFFFFF, this.var_int_m, 15, 0, this.spriteGameTitle, Class_I.appCanvas.width - this.spriteGameTitle.width >> 1, (Class_I.appCanvas.height - this.spriteGameTitle.height) / 3, 0, 0);
                graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
            }
        }
    }

    // This is the game's global draw method
    public void gameDraw(Graphics graphics) {
        int n;
        if (this.var_byte_d == 2) {
            this.e(graphics);
            return;
        }
        if (this.var_boolean_l) {
            this.c(graphics);
            return;
        }
        if (this.var_int_q == 0 || this.var_boolean_m) {
            this.var_boolean_m = false;
            if (this.var_boolean_y) {
                if (this.var_int_m >= 15 && this.var_byte_i != 11) {
                    this.c(graphics);
                } else {
                    Class_I.a(graphics, 0, this.var_int_m, 15, this.var_int_e, null, 0, 0, AppCanvas.width2, AppCanvas.height2);
                }
                return;
            }
            if (this.var_byte_d == 0) {
                this.b(graphics);
            } else {
                int n2;
                graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                if (this.var_short_c < AppCanvas.width2 || this.var_short_i < AppCanvas.height2) {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, AppCanvas.width2, AppCanvas.height2);
                }
                this.d(graphics);
                int n3 = this.var_java_util_Vector_a.size();
                for (n2 = 0; n2 < n3; ++n2) {
                    Unit c2 = this.var_java_util_Vector_a.elementAt(n2);
                    if (c2.var_byte_e == 3) {
                        this.spriteTombstone.draw(graphics, this.var_short_f + c2.var_short_b, this.var_short_a + ((SpriteSheet)c2).l);
                        continue;
                    }
                    if (c2 == this.var_c_h) continue;
                    c2.a(graphics, (int)this.var_short_f, (int)this.var_short_a);
                }
                n3 = this.var_java_util_Vector_a.size();
                for (n2 = 0; n2 < n3; ++n2) {
                    this.var_java_util_Vector_a.elementAt(n2).b(graphics, this.var_short_f, this.var_short_a);
                }
                if (this.var_java_util_Vector_b != null) {
                    graphics.setColor(0xFFFFFF);
                    short[][] sArrayArray = new short[this.var_java_util_Vector_b.size()][];
                    this.var_java_util_Vector_b.copyInto((Object[])sArrayArray);
                    int n4 = sArrayArray.length;
                    for (n3 = 0; n3 < n4; ++n3) {
                        short[] sArray;
                        int n5 = sArrayArray[n3][0] * 24 + this.var_short_f;
                        int n6 = sArrayArray[n3][1] * 24 + this.var_short_a;
                        int n7 = n5 + 12;
                        int n8 = n6 + 12;
                        short[] sArray2 = sArrayArray[n3];
                        if (n3 != 0) {
                            sArray = sArrayArray[n3 - 1];
                            if (sArray[0] == sArray2[0] + 1) {
                                this.a(graphics, n7, n8 - this.G, 12, 0, true);
                            } else if (sArray[0] == sArray2[0] - 1) {
                                this.a(graphics, n5, n8 - this.G, 12, 0, false);
                            } else if (sArray[1] == sArray2[1] + 1) {
                                this.a(graphics, n7 - this.G, n8, 0, 12, true);
                            } else if (sArray[1] == sArray2[1] - 1) {
                                this.a(graphics, n7 - this.G, n6, 0, 12, false);
                            }
                        }
                        if (n3 == n4 - 1) {
                            graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                            this.var_e_k.a(graphics, n5 - 1, n6 - 4);
                            continue;
                        }
                        sArray = sArrayArray[n3 + 1];
                        if (sArray[0] == sArray2[0] + 1) {
                            this.a(graphics, n7, n8 - this.G, 12, 0, false);
                            continue;
                        }
                        if (sArray[0] == sArray2[0] - 1) {
                            this.a(graphics, n5, n8 - this.G, 12, 0, true);
                            continue;
                        }
                        if (sArray[1] == sArray2[1] + 1) {
                            this.a(graphics, n7 - this.G, n8, 0, 12, false);
                            continue;
                        }
                        if (sArray[1] != sArray2[1] - 1) continue;
                        this.a(graphics, n7 - this.G, n6, 0, 12, true);
                    }
                }
                graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                if (this.var_c_h != null) {
                    this.var_c_h.a(graphics, (int)this.var_short_f, (int)this.var_short_a);
                    this.var_c_h.b(graphics, this.var_short_f, this.var_short_a);
                }
                if (this.var_boolean_n) {
                    this.var_e_h.a(graphics, this.var_short_f - 1, this.var_short_a - 1);
                }
                if (this.var_byte_i == 11 && !this.var_boolean_y) {
                    String string = AppCanvas.getGameText(23);
                    graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                    graphics.setFont(AppCanvas.fontSmallPlain);
                    graphics.setColor(-16777216);
                    graphics.fillRect(0, 0, AppCanvas.width2, AppCanvas.height2);
                    n3 = AppCanvas.f - AppCanvas.fontSmallPlain.getHeight() / 2;
                    graphics.setColor(-1);
                    graphics.drawString(string, AppCanvas.h, n3, 17);
                }
                for (n = 0; n < this.var_java_util_Vector_c.size(); ++n) {
                    this.var_java_util_Vector_c.elementAt(n).a(graphics, this.var_short_f, this.var_short_a);
                }
            }
        }
        for (n = 0; n < this.var_java_util_Vector_e.size(); ++n) {
            this.var_java_util_Vector_e.elementAt(n).a(graphics);
        }
        if (this.var_int_g == 0) {
            if (this.var_boolean_r) {
                this.uiBtnIconSheet.a(BTN_ICON_BACK);
                this.uiBtnIconSheet.a(graphics, AppCanvas.width2 - this.uiBtnIconSheet.getSpritesWidth(), AppCanvas.height2 - this.uiBtnIconSheet.getSpritesHeight());
            }
            if (this.var_boolean_t) {
                this.uiBtnIconSheet.a(BTN_ICON_CONFIRM);
                this.uiBtnIconSheet.a(graphics, 0, AppCanvas.height2 - this.uiBtnIconSheet.getSpritesHeight());
            }
        }
    }

    public void a(Graphics graphics, int n, int n2, int n3, int n4, boolean bl) {
        int n5 = 2;
        int n6 = 12 - n5;
        int n7 = this.H;
        if (bl) {
            n7 = 12 - n7;
        }
        int n8 = 1;
        if (n3 != 0) {
            n8 += n3 / (n6 + n5);
            graphics.setClip(n, n2, n3, this.C);
        } else if (n4 != 0) {
            n8 += n4 / (n6 + n5);
            graphics.setClip(n, n2, this.C, n4);
        }
        int n9 = n7 - n6 - n5;
        for (int j = 0; j < n8; ++j) {
            if (n3 != 0) {
                graphics.fillRect(n + n9, n2, n6, this.C);
            } else if (n4 != 0) {
                graphics.fillRect(n, n2 + n9, this.C, n6);
            }
            if (n3 != 0 ? n9 >= n3 : n4 != 0 && (n9 += n6 + n5) >= n4) break;
        }
    }

    public Unit c_a(int n, int n2, byte by) {
        int n3 = this.var_java_util_Vector_a.size();
        for (int j = 0; j < n3; ++j) {
            Unit c2 = this.var_java_util_Vector_a.elementAt(j);
            if (n != c2.mapX || n2 != c2.mapY || !(by == 0 ? c2.var_byte_e != 3 : by == 1 && c2.var_byte_e == 3)) continue;
            return c2;
        }
        return null;
    }

    public byte getTerrainType_ZZ(int n, int n2) {
        return this.var_byte_arr_j[this.var_byte_arr_arr_a[n][n2]];
    }

    public int getTerrainDefence_XX(byte terrainType, Unit unit) {
        int terrainDefence = terrainTypeDefense[terrainType];
        // TODO probably 2 is Unit.LIZARD, they get +2 defence in water
        if (unit.a((short)2) && terrainType == f.TERRAIN_WATER) {
            terrainDefence += 2;
        }
        return terrainDefence;
    }

    public void l() {
        int n;
        this.var_short_d = (short)(this.var_short_d + 1);
        this.var_byte_g = (byte)((this.var_byte_g + 1) % this.var_byte_arr_d.length);
        this.playerIndex_XX = this.var_byte_arr_d[this.var_byte_g];
        for (n = this.var_java_util_Vector_a.size() - 1; n >= 0; --n) {
            Unit c2 = this.var_java_util_Vector_a.elementAt(n);
            if (c2.var_byte_e == 3) {
                if (this.var_short_d - c2.var_int_b < 3) continue;
                this.var_java_util_Vector_a.removeElement(c2);
                continue;
            }
            c2.var_byte_e = 0;
            if (this.playerIndex_XX == c2.owner && this.boolean_a((int)c2.mapX, (int)c2.mapY, (int)c2.owner)) {
                c2.quantity = (short)(c2.quantity + 2);
                if (c2.quantity > 10) {
                    c2.quantity = (short)10;
                }
            }
            if (this.playerIndex_XX == c2.owner) continue;
            c2.b((byte)1);
        }
        for (n = 0; n < this.var_byte_arr_arr_a.length; ++n) {
            for (int j = 0; j < this.var_byte_arr_arr_a[n].length; ++j) {
                if (!this.boolean_a(n, j, (int)this.playerIndex_XX)) continue;
                if (this.getTerrainType_ZZ(n, j) == f.TERRAIN_TOWN) {
                    byte by = this.var_byte_g;
                    this.var_int_arr_b[by] = this.var_int_arr_b[by] + 30;
                    continue;
                }
                if (this.getTerrainType_ZZ(n, j) != f.TERRAIN_CASTLE) continue;
                byte by = this.var_byte_g;
                this.var_int_arr_b[by] = this.var_int_arr_b[by] + 50;
            }
        }
        n = this.var_short_h;
        short s = this.var_short_g;
        this.void_c(this.K, this.var_int_u);
        this.K = n;
        this.var_int_u = s;
        this.var_boolean_v = true;
        if (this.int_a(-1, 0, this.playerIndex_XX) <= 0) {
            this.l();
        }
    }

    public boolean a(int n, int n2, Unit c2) {
        if (c2.a((short)8) && this.getTerrainType_ZZ(c2.mapX, (int)c2.mapY) == f.TERRAIN_TOWN && !this.boolean_a((int)c2.mapX, (int)c2.mapY, (int)c2.owner)) {
            return true;
        }
        return c2.a((short)16) && this.getTerrainType_ZZ(c2.mapX, (int)c2.mapY) == f.TERRAIN_CASTLE && !this.boolean_a((int)c2.mapX, (int)c2.mapY, (int)c2.owner);
    }

    public void void_a(int n, int n2, int n3) {
        if (this.var_byte_arr_arr_a[n][n2] >= this.var_int_t) {
            this.var_byte_arr_arr_a[n][n2] = (byte)(this.var_int_t + (n3 + 1) * 3 + (this.var_byte_arr_arr_a[n][n2] - this.var_int_t) % 3);
        }
    }

    public int int_a(int n, int n2) {
        if (this.var_byte_arr_arr_a[n][n2] >= this.var_int_t) {
            return (this.var_byte_arr_arr_a[n][n2] - this.var_int_t) / 3 - 1;
        }
        return -1;
    }

    public boolean boolean_a(int n, int n2, int n3) {
        return this.int_a(n, n2) == n3;
    }

    private int int_a(int unitType, int n2, byte by) {
        return this.c_arr_a(unitType, n2, by).length;
    }

    private Unit[] c_arr_a(int unitType, int n2, byte by) {
        Vector<Unit> vector = new Vector<Unit>();
        int n3 = this.var_java_util_Vector_a.size();
        for (int j = 0; j < n3; ++j) {
            Unit c2 = this.var_java_util_Vector_a.elementAt(j);
            if (unitType != -1 && c2.unitType != unitType || (n2 != -1 || n2 == 3) && n2 != c2.var_byte_e || c2.owner != by) continue;
            vector.addElement(c2);
        }
        Unit[] unitsArray = new Unit[vector.size()];
        vector.copyInto(unitsArray);
        return unitsArray;
    }

    public static void a(Graphics graphics, int n, int n2, int n3, int n4, Sprite sprite, int n5, int n6, int n7, int n8) {
        int n9;
        int n10;
        if (sprite != null) {
            n10 = sprite.width / 4 + 1;
            n9 = sprite.height / 2 + 1;
        } else {
            n10 = n7 / 4 + 1;
            n9 = n8 / 4 + 1;
        }
        int n11 = n3 - 6;
        graphics.setColor(n);
        for (int j = 0; j < 4; ++j) {
            int n12;
            int n13;
            int n14 = n2 - j * 2;
            if (n14 < 0) {
                n14 = 0;
            }
            if (n14 >= n11) {
                n13 = n9;
                n12 = n10;
            } else {
                n12 = n10 * n14 / n11;
                n13 = n9 * n14 / n11;
            }
            if (n4 == 1) {
                n12 = n10 - n12;
                n13 = n9 - n13;
            }
            int n15 = (n10 - n12) / 2;
            int n16 = (n9 - n13) / 2;
            int n17 = j * n10 + n15 + n5;
            for (int k = 0; k < 4; ++k) {
                int n18 = k * n9 + n16 + n6;
                if (sprite == null) {
                    graphics.fillRect(n17, n18, n12, n13);
                    continue;
                }
                graphics.setClip(n17, n18, n12, n13);
                sprite.draw(graphics, n5, n6);
            }
        }
    }

    public boolean boolean_a(int n) {
        short s = this.var_c_arr_a[this.playerIndex_XX].mapX;
        short s2 = this.var_c_arr_a[this.playerIndex_XX].mapY;
        return Unit.unitsDataPrice[n] <= this.var_int_arr_b[this.var_byte_g] && Unit.unitsDataPrice[n] > 0 && (s > 0 && this.c_a(s - 1, (int)s2, (byte)0) == null || s < this.var_short_e - 1 && this.c_a(s + 1, (int)s2, (byte)0) == null || s2 > 0 && this.c_a((int)s, s2 - 1, (byte)0) == null || s2 < this.var_short_b - 1 && this.c_a((int)s, s2 + 1, (byte)0) == null);
    }

    public void p() throws Exception {
        if (this.var_byte_b == 4) {
            if (this.var_c_g != null || this.var_c_a != null) {
                this.var_byte_b = (byte)5;
                this.var_c_h.a(this.var_byte_arr_arr_b, (int)this.var_c_h.mapX, (int)this.var_c_h.mapY);
                this.var_boolean_j = true;
                this.var_boolean_h = true;
                this.var_long_j = this.var_long_n;
                if (this.var_c_g != null) {
                    this.var_e_h.a(var_byte_arr_arr_d[1]);
                    this.void_c(this.var_c_g.mapX, this.var_c_g.mapY);
                } else if (this.var_c_a != null) {
                    this.void_c(this.var_c_a.mapX, this.var_c_a.mapY);
                }
            } else {
                if (this.a((int)this.var_c_h.mapX, (int)this.var_c_h.mapY, this.var_c_h)) {
                    this.void_a((int)this.var_c_h.mapX, (int)this.var_c_h.mapY, (int)this.var_c_h.owner);
                    g.a(this, null, AppCanvas.getGameText(38), 1000, true);
                    AppCanvas.playSound(-1, 1);
                    this.var_byte_i = (byte)9;
                    this.var_long_c = this.var_long_n;
                } else {
                    this.var_byte_i = 0;
                }
                this.var_c_h.void_b();
                this.var_c_h = null;
                this.var_byte_b = 0;
            }
            this.var_boolean_n = true;
        } else if (this.var_byte_b == 5) {
            if (this.var_long_n - this.var_long_j >= 500L) {
                if (this.var_c_g != null) {
                    this.b(this.var_c_h, this.var_c_g);
                } else if (this.var_c_a != null) {
                    this.void_a(this.var_c_a, this.playerIndex_XX);
                    this.var_c_a = null;
                    this.var_byte_b = (byte)7;
                    this.var_c_h.void_b();
                }
                this.var_boolean_h = false;
                this.var_boolean_j = false;
            }
        } else if (this.var_byte_b == 7) {
            if (this.var_c_e == null) {
                this.var_byte_b = 0;
                this.var_byte_i = 0;
            }
        } else if (this.var_byte_b == 6) {
            if (this.var_long_n - this.var_long_j >= 1000L) {
                this.var_c_g = null;
                this.var_byte_b = 0;
                this.var_byte_i = 0;
            }
        } else {
            if (this.var_byte_b == 2) {
                return;
            }
            if (this.var_byte_b == 3) {
                if (this.var_int_A == 0) {
                    if (this.boolean_c(this.var_c_h.var_short_b + 12, ((SpriteSheet)this.var_c_h).l + 12)) {
                        this.var_int_A = 1;
                        this.var_long_j = this.var_long_n;
                    }
                } else if (this.var_int_A == 1) {
                    if (this.var_long_n - this.var_long_j >= 400L) {
                        this.var_boolean_h = true;
                        this.var_int_A = 2;
                        this.var_byte_i = 1;
                        this.var_long_j = this.var_long_n;
                    }
                } else if (this.var_int_A == 2) {
                    if (this.var_long_n - this.var_long_j >= 300L) {
                        this.var_short_h = (short)this.var_int_f;
                        this.var_short_g = (short)this.var_int_x;
                        this.var_e_h.void_b(this.var_int_f * 24, this.var_int_x * 24);
                        this.var_java_util_Vector_b = this.var_c_h.a(this.var_c_h.mapX, this.var_c_h.mapY, this.var_short_h, this.var_short_g);
                        this.var_int_A = 3;
                        this.var_long_j = this.var_long_n;
                    }
                } else if (this.var_int_A == 3 && this.var_long_n - this.var_long_j >= 300L) {
                    this.var_java_util_Vector_b = null;
                    this.var_c_h.void_a(this.var_int_f, this.var_int_x);
                    this.var_byte_b = (byte)2;
                    this.var_int_A = 0;
                    this.var_byte_i = (byte)2;
                }
                return;
            }
            int n = this.var_java_util_Vector_a.size();
            for (int j = 0; j < n; ++j) {
                int n2;
                int n3;
                int n4;
                int n5;
                int n6;
                int n7;
                Unit c2 = this.var_java_util_Vector_a.elementAt(j);
                if (c2.owner != this.playerIndex_XX || c2.var_byte_e == 2 || c2.var_byte_e == 3) continue;
                if (c2.unitType == Unit.KING) {
                    if (this.int_a(-1, 0, this.playerIndex_XX) != 1) continue;
                    if (this.getTerrainType_ZZ(c2.mapX, (int)c2.mapY) == f.TERRAIN_CASTLE && this.boolean_a((int)c2.mapX, (int)c2.mapY, (int)this.playerIndex_XX)) {
                        if (this.int_a(0, -1, this.playerIndex_XX) < 2 && this.boolean_a(0)) {
                            c2 = this.c_a(0, (int)c2.mapX, c2.mapY);
                        } else {
                            n7 = 0;
                            byte[] byArray = new byte[11];
                            for (n6 = 1; n6 < 11; n6 = (int)((byte)(n6 + 1))) {
                                if (this.int_a(n6, -1, this.playerIndex_XX) >= 1 && Unit.unitsDataPrice[n6] < 600 || !this.boolean_a(n6)) continue;
                                byArray[n7] = (byte)n6;
                                ++n7;
                            }
                            if (n7 > 0) {
                                n6 = byArray[Math.abs(AppCanvas.randomGen.nextInt()) % n7];
                                c2 = this.c_a((int)((byte)n6), (int)c2.mapX, c2.mapY);
                            }
                        }
                    }
                }
                this.void_c(c2.mapX, c2.mapY);
                this.b(c2);
                this.var_c_h = c2;
                this.var_boolean_n = true;
                this.a(this.var_byte_arr_arr_b, 0);
                this.var_c_h.b(this.var_byte_arr_arr_b);
                this.var_boolean_h = false;
                this.var_c_arr_c = this.c_arr_a(0, -1, this.playerIndex_XX);
                n7 = 666;
                this.var_int_z = -1;
                for (n5 = 0; n5 < this.var_byte_arr_arr_e.length; ++n5) {
                    n6 = this.var_byte_arr_arr_e[n5][0];
                    n4 = this.var_byte_arr_arr_e[n5][1];
                    if (this.getTerrainType_ZZ(n6, n4) != f.TERRAIN_TOWN) continue;
                    n3 = this.boolean_a(n6, n4, (int)c2.owner) ? 1 : 0;
                    if (this.currentLevel != 2 && (c2.unitType != Unit.SOLDIER || n3 != 0) && (c2.unitType == Unit.SOLDIER || n3 == 0) || (n2 = Math.abs(n6 - c2.mapX) + Math.abs(n4 - c2.mapY)) >= n7) continue;
                    n7 = n2;
                    this.var_int_z = n6;
                    this.var_int_o = n4;
                }
                this.var_byte_b = (byte)3;
                n5 = 0;
                n4 = this.var_byte_arr_arr_b.length;
                for (n6 = 0; n6 < n4; ++n6) {
                    n2 = this.var_byte_arr_arr_b[n6].length;
                    for (n3 = 0; n3 < n2; ++n3) {
                        int n8;
                        Unit c3 = this.c_a(n6, n3, (byte)0);
                        if (this.var_byte_arr_arr_b[n6][n3] <= 0 || c3 != null && c3 != c2) continue;
                        if (!c2.a((short)512) || c3 == c2) {
                            Unit[] cArray = c2.a(n6, n3, (byte)0);
                            for (int k = 0; k < cArray.length; ++k) {
                                n8 = this.a(c2, n6, n3, cArray[k], null);
                                if (n8 <= n5) continue;
                                this.var_c_g = cArray[k];
                                n5 = n8;
                                this.var_int_f = n6;
                                this.var_int_x = n3;
                            }
                        }
                        if (c2.a((short)32)) {
                            this.var_c_arr_b = c2.a(n6, n3, (byte)1);
                            for (int k = 0; k < this.var_c_arr_b.length; ++k) {
                                n8 = this.a(c2, n6, n3, null, this.var_c_arr_b[k]);
                                if (n8 <= n5) continue;
                                this.var_c_a = this.var_c_arr_b[k];
                                n5 = n8;
                                this.var_int_f = n6;
                                this.var_int_x = n3;
                            }
                        }
                        if ((n8 = this.a(c2, n6, n3, null, null)) <= n5) continue;
                        this.var_c_g = null;
                        this.var_c_a = null;
                        n5 = n8;
                        this.var_int_f = n6;
                        this.var_int_x = n3;
                    }
                }
                return;
            }
            this.var_c_arr_c = null;
            g.a(this, null, AppCanvas.getGameText(39), 1000, true);
            this.var_byte_i = (byte)8;
            this.var_long_c = this.var_long_n;
        }
    }

    public int a(Unit c2, int n, int n2, Unit c3, Unit c4) {
        int n3;
        int n4;
        int n5 = 0;
        switch (c2.unitType) {
            case Unit.SOLDIER: {
                if (this.var_c_arr_a[c2.owner] != null && this.var_int_z != -1) {
                    n4 = this.var_short_e - Math.abs(this.var_int_z - n) + this.var_short_b - Math.abs(this.var_int_o - n2);
                    n5 += n4 * n4;
                }
                if (terrainTypeMovementReduction_XXX[this.getTerrainType_ZZ(n, n2)] <= 1) {
                    n5 += 5;
                }
                for (n4 = 0; n4 < this.var_c_arr_c.length; ++n4) {
                    if (this.var_c_arr_c[n4] == c2) continue;
                    n3 = this.var_c_arr_c[n4].mapX - c2.mapX + (this.var_c_arr_c[n4].mapY - c2.mapY);
                    n5 += n3 * n3;
                }
                if (this.getTerrainType_ZZ(n, n2) != f.TERRAIN_TOWN || this.boolean_a(n, n2, (int)c2.owner) || c3 != null) break;
                n5 += 200;
                break;
            }
            case Unit.WIZARD: {
                if (c4 == null) break;
                n5 += 100;
                break;
            }
            case Unit.KING: {
                if (n != c2.mapX || n2 != c2.mapY) break;
                n5 += 200;
                break;
            }
        }
        if (c3 != null) {
            n5 = !c3.a(c2, n, n2) ? (n5 += c2.int_a(n, n2) * 2) : (n5 += c2.int_a(n, n2) - c3.int_a(n, n2) + 10 - c3.quantity);
            if (c3.unitType == Unit.KING) {
                n5 += 10;
            }
        }
        n5 += this.getTerrainDefence_XX(this.getTerrainType_ZZ(n, n2), c2) * 2;
        for (n4 = 0; n4 < this.var_c_arr_a.length; ++n4) {
            if (n4 == this.var_byte_g || this.var_c_arr_a[n4] == null) continue;
            n5 += (this.var_short_e - Math.abs(n - this.var_c_arr_a[n4].mapX) + this.var_short_b - Math.abs(n2 - this.var_c_arr_a[n4].mapY)) * 2;
            break;
        }
        if (this.getTerrainType_ZZ(n, n2) == f.TERRAIN_TOWN && this.boolean_a(n, n2, (int)c2.owner)) {
            n5 += (10 - c2.quantity) * 2;
        }
        if (c2.quantity < 5 && c2.unitType != Unit.SOLDIER && this.var_int_z != -1) {
            n4 = this.var_short_e - Math.abs(this.var_int_z - n) + this.var_short_b - Math.abs(this.var_int_o - n2);
            n5 += n4 * n4;
        }
        if (this.currentLevel == 2 && this.var_int_z != -1) {
            n4 = Math.abs(this.var_int_z - n) - 1;
            n3 = Math.abs(this.var_int_o - n2) - 3;
            if (n4 < 0) {
                n4 = 0;
            }
            if (n3 < 0) {
                n3 = 0;
            }
            int n6 = this.var_short_e - n4 * 2 + this.var_short_b - n3 * 2;
            n5 += n6 * n6;
        }
        return n5 += 10 * (Math.abs(n - c2.mapX) + Math.abs(n2 - c2.mapY)) / (Unit.unitsDataMOV[c2.unitType] - 1);
    }

    public void void_b(int n) {
        this.var_boolean_z = true;
        this.var_int_i = n;
        this.var_long_h = this.var_long_n;
    }

    public void a(boolean bl) {
        if (bl) {
            this.var_boolean_o = false;
            --this.M;
            --this.var_int_g;
        } else {
            this.var_boolean_o = true;
            ++this.M;
            ++this.var_int_g;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void void_a() {
        if (this.var_boolean_z) {
            if (this.var_long_n - this.var_long_h < (long)this.var_int_i) return;
            this.var_boolean_z = false;
        }
        if (this.var_boolean_w) {
            if (this.currentLevelStep != 0) return;
            this.var_boolean_y = true;
            this.var_int_m = 0;
            this.var_byte_i = (byte)11;
            this.currentLevelStep = 1;
            return;
        }
        if (this.var_byte_a == 1) {
            if (this.var_c_arr_a[0].var_byte_e != 3 && this.var_c_arr_a[1].var_byte_e != 3) return;
            this.i();
            return;
        }
        if (this.var_byte_d != 1 || this.var_byte_a != 0) {
            return;
        }
        if (this.currentLevelStep == 0) {
            this.var_c_arr_a[0].var_java_lang_String_a = AppCanvas.getGameText(43); // "GALAMAR"
            this.var_g_b.a((byte)0, 0, 0, null, 0);
            ++this.currentLevelStep;
        }
        if (this.var_g_b != null) {
            if (this.var_g_b.var_byte_e != 3) return;
            this.var_g_b = null;
            AppCanvas.stopFirstSound();
        } else if (this.var_int_j != -1) {
            if (!this.boolean_b(this.var_int_j, this.var_int_b)) return;
            this.var_int_j = -1;
            this.var_int_b = -1;
        }
        if (this.var_byte_i != 11 && this.var_c_arr_a[0].var_byte_e == 3) {
            this.i();
            return;
        }
        if (this.currentLevel == 0) {
            switch (this.currentLevelStep) {
                // TODO the step 0 is executed somewhere else...look for string 103 and this.currentLevelStep
                case 1: {
                    this.var_int_arr_b[0] = 0;
                    // TODO I think this animates the camera/map to show another area
                    this.void_b(8, 9);
                    ++this.currentLevelStep;
                    break;
                }
                case 2: {
                    // TODO pretty sure this is a 'wait'
                    this.void_b(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    // "Sire, your troops are weary after last night's battle. [...]"
                    this.var_g_b = g.a(this, AppCanvas.getGameText(111), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 5: {
                    this.void_b(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    // "Sound advice, captain. Ready the troops [...]"
                    this.var_g_b = g.a(this, AppCanvas.getGameText(112), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(113), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.var_g_e.a((byte)0, 0, 0, null, 0);
                    ++this.currentLevelStep;
                    break;
                }
                case 9: {
                    if (this.var_g_i != null) break;
                    this.a(true);
                    this.var_int_s = 0;
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    if (this.var_byte_i != 1) break;
                    this.var_int_s = 1;
                    ++this.currentLevelStep;
                    break;
                }
                case 11: {
                    if (this.var_c_f == null) break;
                    this.var_int_s = 2;
                    ++this.currentLevelStep;
                    break;
                }
                case 12: {
                    if (this.var_c_f == null) break;
                    this.var_int_s = 3;
                    ++this.currentLevelStep;
                    break;
                }
                case 13: {
                    if (this.int_a(-1, 2, (byte)0) >= 3) {
                        this.var_int_s = 4;
                        ++this.currentLevelStep;
                        break;
                    }
                    if (this.var_short_d < 1) break;
                    ++this.currentLevelStep;
                    break;
                }
                case 14: {
                    if (this.var_short_d < 2) break;
                    this.var_int_s = 5;
                    ++this.currentLevelStep;
                    break;
                }
                case 15: {
                    if (this.var_c_f == null || this.var_c_f.mapX < 4 || this.var_c_f.mapY < 7) break;
                    Unit.spawn(Unit.SOLDIER, PLAYER_RED, 5, 8);
                    this.a(this.var_e_r, 120, 192, 0, 0, 2, 50);
                    this.a(false);
                    this.void_b(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 16: {
                    // "Spies!! Valadorn and his Red legion [...]"
                    this.var_g_b = g.a(this, AppCanvas.getGameText(114), PORTRAIT_CAPTAIN, (byte)4);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 17: {
                    this.var_int_s = 6;
                    ++this.currentLevelStep;
                    break;
                }
                case 18: {
                    if (this.var_byte_i != 1 || this.playerIndex_XX != PLAYER_BLUE) break;
                    this.var_int_s = 7;
                    ++this.currentLevelStep;
                    break;
                }
                case 19: {
                    if (!this.boolean_a(8, 9, 0) || this.var_byte_i != 0) break;
                    this.void_b(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 20: {
                    this.g();
                }
            }
            if (this.int_a(-1, 3, (byte)0) == 1) {
                this.i();
            }
        } else if (this.currentLevel == 1) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.var_int_arr_b[0] = 0;
                    this.J = 2;
                    this.void_b(9, 12);
                    break;
                }
                case 2: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(115), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 4: {
                    this.var_g_e.a((byte)0, 0, 0, null, 0);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 5: {
                    if (this.var_g_i != null) break;
                    this.var_int_s = 9;
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    if (!this.boolean_a(9, 12, 0) && !this.boolean_a(11, 13, 0)) break;
                    this.var_int_s = 10;
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    if (!this.boolean_a(9, 12, 0) || !this.boolean_a(11, 13, 0)) break;
                    this.var_int_s = 11;
                    this.a(false);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.void_b(12, 1);
                    break;
                }
                case 9: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(116), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 11: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(117), PORTRAIT_GALAMAR, (byte)4);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 12: {
                    if (this.var_c_arr_a[0].mapX < 11 || this.var_c_arr_a[0].mapY > 4 || this.var_c_arr_a[0].var_byte_e != 2) break;
                    this.void_b(12, 1);
                    this.a(false);
                    break;
                }
                case 13: {
                    Unit.spawn(Unit.SPIDER, PLAYER_RED, 4, 0);
                    Unit.spawn(Unit.SPIDER, PLAYER_RED, 1, 1);
                    Unit.spawn(Unit.SPIDER, PLAYER_RED, 1, 5);
                    Unit unitLizard_1 = Unit.spawn(Unit.LIZARD, PLAYER_BLUE, 12, 1);
                    this.b(unitLizard_1);
                    unitLizard_1.b(this.var_byte_arr_arr_b);
                    // TODO I think this makes the lizard walk to the location
                    // Ref: https://youtu.be/6MTmxnNygSw?t=371
                    unitLizard_1.void_a(9, 2);
                    this.void_b(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 14: {
                    if (this.var_byte_i == 1) break;
                    Unit unitLizard_2 = Unit.spawn(Unit.LIZARD, PLAYER_BLUE, 12, 1);
                    this.b(unitLizard_2);
                    unitLizard_2.b(this.var_byte_arr_arr_b);
                    unitLizard_2.void_a(10, 1);
                    this.void_b(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 15: {
                    if (this.var_byte_i == 1) break;
                    this.var_g_b = g.a(this, AppCanvas.getGameText(118), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 16: {
                    this.void_b(1, 1);
                    break;
                }
                case 17: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 18: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(119), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 19: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(120), PORTRAIT_CAPTAIN, (byte)4);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 20: {
                    this.var_int_s = 12;
                    ++this.currentLevelStep;
                    break;
                }
                case 21: {
                    this.var_int_s = 13;
                    ++this.currentLevelStep;
                    break;
                }
                case 22: {
                    if (this.int_a(-1, -1, (byte)1) != this.int_a(-1, 3, (byte)1)) break;
                    this.void_b(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 23: {
                    this.g();
                }
            }
        } else if (this.currentLevel == 2) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.var_int_arr_b[0] = 0;
                    this.c_a((int)14, (int)12, (byte)0).var_java_lang_String_a = AppCanvas.getGameText(45);
                    this.void_b(7, 12);
                    break;
                }
                case 2: {
                    for (int j = 5; j < 10; ++j) {
                        this.a(this.var_e_d, j * 24, 288, 0, 0, 4, 50);
                    }
                    AppCanvas.playSound(1, 3);
                    this.void_b(200);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    for (int j = 5; j < 10; ++j) {
                        this.var_byte_arr_arr_a[j][12] = var_byte_arr_a[0];
                    }
                    this.void_b(300);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    // 'Sire, the bridge has been destroyed!'
                    this.var_g_b = g.a(this, AppCanvas.getGameText(121), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 5: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 6: {
                    // 'Valadorn must be expecting us - we must find another way across. This could be a trap.'
                    this.var_g_b = g.a(this, AppCanvas.getGameText(122), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    // 'Troops! Keep your eyes open and protect the Lizard Chief at all cost.'
                    this.var_g_b = g.a(this, AppCanvas.getGameText(123), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.a(true);
                    this.var_g_e.a((byte)0, 0, 0, null, 0);
                    ++this.currentLevelStep;
                }
            }
            if (this.currentLevelStep != -1) {
                if (this.var_c_arr_a[0].mapX == 1 && this.var_c_arr_a[0].mapY == 13 && this.var_c_arr_a[0].var_byte_e == 2) {
                    this.g();
                }
                if (this.int_a(2, 3, (byte)0) == 1) {
                    this.i();
                }
            }
        } else if (this.currentLevel == 3) {
            if (this.var_byte_i == 1 && this.playerIndex_XX == PLAYER_BLUE) {
                if (this.var_boolean_A && this.var_c_h.unitType == Unit.WIZARD) {
                    this.var_int_s = 15;
                    this.var_boolean_A = false;
                }
                if (this.var_boolean_i && this.var_c_h.unitType == Unit.WISP) {
                    this.var_int_s = 16;
                    this.var_boolean_i = false;
                }
            }
            switch (this.currentLevelStep) {
                case 1: {
                    this.var_boolean_A = true;
                    this.var_boolean_i = true;
                    this.J = 5;
                    this.void_b(2, 0);
                    break;
                }
                case 2: {
                    this.void_b(2, 12);
                    break;
                }
                case 3: {
                    // 'Your majesty, the Red legion! Watch out for their long range catapult!'
                    this.var_g_b = g.a(this, AppCanvas.getGameText(124), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 5: {
                    this.var_g_e.a((byte)0, 0, 0, null, 0);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    if (this.var_short_d != 10) break;
                    this.void_b(2, 0);
                    this.a(false);
                    break;
                }
                case 7: {
                    this.var_c_arr_a[1] = Unit.spawn(Unit.KING, PLAYER_RED, 2, 0);
                    // TODO 44: VALADORN -- maybe this is the unit's name?
                    this.var_c_arr_a[1].var_java_lang_String_a = AppCanvas.getGameText(44);
                    Unit.spawn(Unit.SPIDER, PLAYER_RED, 0, 0);
                    this.a(this.var_e_r, 48, 0, 0, 0, 4, 50);
                    this.a(this.var_e_r, 0, 0, 0, 0, 4, 50);
                    this.void_a(2, 0, 1);
                    this.void_a(0, 0, 1);
                    this.void_b(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    // 'What is this treachery! The city has turned against us!'
                    this.var_g_b = g.a(this, AppCanvas.getGameText(125), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 9: {
                    // 'As predictable as ever, brother! I have you now!'
                    this.var_g_b = g.a(this, AppCanvas.getGameText(126), PORTRAIT_VALADORN, (byte)8);
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 11: {
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 12: {
                    if (this.var_c_arr_a[1] != null) break;
                    // 'Retreat!! Curse you Galamar! You won't be so lucky next time!'
                    this.var_g_b = g.a(this, AppCanvas.getGameText(127), PORTRAIT_VALADORN, (byte)8);
                    this.a(false);
                    ++this.currentLevelStep;
                    break;
                }
                case 13: {
                    this.void_b(600);
                    ++this.currentLevelStep;
                    break;
                }
                case 14: {
                    this.a(true);
                    this.g();
                }
            }
        } else if (this.currentLevel == 4) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.J = 7;
                    this.void_b(2, 2);
                    break;
                }
                case 2: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(128), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(129), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 5: {
                    this.var_g_e.a((byte)0, 0, 0, null, 0);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    if (this.var_c_arr_a[1].var_byte_e != 3) break;
                    this.void_b(2, 2);
                    this.a(false);
                    break;
                }
                case 7: {
                    //Unit c4 = Unit.a((byte)8, (byte)0, 2, 2); // unused
                    this.void_b(2, 2);
                    this.void_b(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.void_b(2, 2);
                    this.void_b(750);
                    break;
                }
                case 9: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(130), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    this.void_b(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 11: {
                    this.a(true);
                    this.g();
                }
            }
        } else if (this.currentLevel == 5) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.var_c_arr_a[1].var_java_lang_String_a = AppCanvas.getGameText(44);
                    this.J = 8;
                    this.void_b(this.var_c_arr_a[1].mapX, this.var_c_arr_a[1].mapY);
                    break;
                }
                case 2: {
                    this.void_b(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(131), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 5: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(132), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    this.var_g_e.a((byte)0, 0, 0, null, 0);
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    if (this.var_g_i != null) break;
                    this.var_int_s = 17;
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    if (this.var_c_arr_a[1] != null) break;
                    this.a(false);
                    ++this.currentLevelStep;
                    break;
                }
                case 9: {
                    this.void_b(800);
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    this.a(true);
                    this.g();
                }
            }
        } else if (this.currentLevel == 6) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.J = 8;
                    this.void_b(13, 0);
                    break;
                }
                case 2: {
                    Unit.spawn(Unit.CATAPULT, PLAYER_RED, 12, 0);
                    Unit.spawn(Unit.SOLDIER, PLAYER_RED, 13, 0);
                    this.a(this.var_e_r, 312, 0, 0, 0, 4, 50);
                    this.a(this.var_e_r, 288, 0, 0, 0, 4, 50);
                    this.void_a(13, 0, 1);
                    this.void_b(800);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    this.void_b(1, 12);
                    break;
                }
                case 4: {
                    Unit.spawn(Unit.GOLEM, PLAYER_RED, 1, 11);
                    Unit.spawn(Unit.SOLDIER, PLAYER_RED, 1, 12);
                    this.a(this.var_e_r, 24, 288, 0, 0, 4, 50);
                    this.a(this.var_e_r, 24, 264, 0, 0, 4, 50);
                    this.void_a(1, 12, 1);
                    this.void_b(800);
                    ++this.currentLevelStep;
                    break;
                }
                case 5: {
                    this.void_b(1, 1);
                    break;
                }
                case 6: {
                    this.var_c_arr_a[1] = Unit.spawn(Unit.KING, PLAYER_RED, 1, 1);
                    this.var_c_arr_a[1].var_java_lang_String_a = AppCanvas.getGameText(44);
                    Unit.spawn(Unit.WYVERN, PLAYER_RED, 0, 1);
                    Unit.spawn(Unit.SOLDIER, PLAYER_RED, 1, 2);
                    this.a(this.var_e_r, 24, 24, 0, 0, 4, 50);
                    this.a(this.var_e_r, 0, 24, 0, 0, 4, 50);
                    this.a(this.var_e_r, 24, 48, 0, 0, 4, 50);
                    this.void_a(1, 1, 1);
                    this.void_a(1, 2, 1);
                    this.void_b(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(133), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(134), PORTRAIT_VALADORN, (byte)8);
                    ++this.currentLevelStep;
                    break;
                }
                case 9: {
                    this.void_b(this.var_c_arr_a[0].mapX, this.var_c_arr_a[0].mapY);
                    break;
                }
                case 10: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(135), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 11: {
                    this.var_g_e.a((byte)0, 0, 0, null, 0);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 12: {
                    if (this.var_c_arr_a[1].quantity > 0) break;
                    this.a(false);
                    ++this.currentLevelStep;
                    break;
                }
                case 13: {
                    this.void_b(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 14: {
                    this.void_b(this.var_c_arr_a[1].mapX, this.var_c_arr_a[1].mapY);
                    break;
                }
                case 15: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(136), PORTRAIT_VALADORN, (byte)8);
                    ++this.currentLevelStep;
                    break;
                }
                case 16: {
                    this.var_g_b = g.a(this, AppCanvas.getGameText(137), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 17: {
                    this.void_b(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 18: {
                    this.var_g_b = g.a(this, null, AppCanvas.getGameText(110), -1, true);
                    ++this.currentLevelStep;
                    break;
                }
                case 19: {
                    this.a(true);
                    this.i();
                }
            }
        }
        this.var_c_f = null;
    }

    public void void_b(int n, int n2) {
        this.var_int_j = n;
        this.var_int_b = n2;
        this.void_c(n, n2);
        ++this.currentLevelStep;
    }

    public void g() {
        AppCanvas.playSound(2, 1);
        this.var_byte_i = (byte)10;
        g.a(this, null, AppCanvas.getGameText(37), 1000, true);
        this.var_long_c = this.var_long_n;
        this.currentLevelStep = -1;
    }

    public void i() {
        this.var_boolean_w = true;
        this.currentLevelStep = 0;
        this.void_b(800);
    }

    public void a(Unit c2, Unit c3) throws Exception {
        this.var_byte_d = (byte)2;
        this.var_boolean_e = true;
        this.var_int_m = 0;
        this.var_boolean_k = false;
        this.var_c_i = c2;
        this.var_c_b = c3;
        //AppCanvas.readAssetsPackage("/2.pak");
        // TODO this image does not exist. A child class assign "defencepanel" to this field, which exists
        // Since the image does not exist, this is the same as assigning null
        this.spritePanelDefense = null;//new Sprite("defpanel.png");
        this.spriteSheetSoul = new SpriteSheet("soul");
        this.var_f_b = new f(this, c2, true);
        this.var_f_a = new f(this, c3, false);
        //AppCanvas.e();
        this.var_f_b.var_f_a = this.var_f_a;
        this.var_f_a.var_f_a = this.var_f_b;
        c2.a(c3);
        if (c3.a(c2, (int)c2.mapX, (int)c2.mapY)) {
            c3.a(c2);
            this.var_boolean_x = true;
        } else {
            this.var_boolean_x = false;
        }
        this.var_f_b.var_byte_c = (byte)c2.quantity;
        this.var_f_b.var_byte_d = (byte)c2.int_a();
        this.var_f_a.var_byte_c = (byte)c3.quantity;
        this.var_f_a.var_byte_d = (byte)c3.int_a();
    }

    public void b() throws Exception {
        if (this.var_boolean_e) {
            ++this.var_int_m;
            appCanvas.repaint();
            appCanvas.serviceRepaints();
            if (this.var_int_m >= 15) {
                this.var_boolean_e = false;
            }
            return;
        }
        if (this.var_boolean_q && this.var_long_n - this.var_long_b >= this.var_long_m) {
            this.var_boolean_q = false;
        }
        for (int j = this.var_java_util_Vector_c.size() - 1; j >= 0; --j) {
            SpriteSheet e2 = this.var_java_util_Vector_c.elementAt(j);
            e2.void_a();
            if (e2.var_boolean_d) continue;
            this.var_java_util_Vector_c.removeElement(e2);
        }
        this.var_f_b.g();
        this.var_f_a.g();
        if (this.var_boolean_k) {
            if (this.var_long_n - this.var_long_f >= 300L) {
                this.var_f_a = null;
                this.var_f_b = null;
                this.spriteSheetSoul = null;
                this.spritePanelDefense = null;
                this.var_e_n = null;
                this.var_e_i = null;
                this.var_e_c = null;
                this.var_e_p = null;
                this.var_e_f = null;
                this.var_e_o = null;
                this.o();
                this.var_byte_d = 1;
                return;
            }
        } else if (this.var_f_b.var_boolean_f) {
            if (this.var_boolean_x && this.var_f_a.var_byte_c > 0) {
                if (!this.var_f_a.var_boolean_e) {
                    this.var_f_a.b();
                }
                if (this.var_f_a.var_boolean_f) {
                    this.var_boolean_k = true;
                    this.var_long_f = this.var_long_n;
                }
            } else {
                this.var_boolean_k = true;
                this.var_long_f = this.var_long_n;
            }
        }
        appCanvas.repaint();
        appCanvas.serviceRepaints();
    }

    public void e(Graphics graphics) {
        graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
        int n = 0;
        int n2 = 0;
        if (this.var_boolean_q) {
            n = AppCanvas.randomGen.nextInt() % 5;
            n2 = AppCanvas.randomGen.nextInt() % 3;
        }
        this.var_f_b.a(graphics, n, n2);
        this.var_f_a.a(graphics, n + AppCanvas.h, n2);
        graphics.setColor(0);
        graphics.fillRect(AppCanvas.h - 1 + n, n2, 2, AppCanvas.height2);
        this.var_f_b.b(graphics);
        this.var_f_a.b(graphics);
        if (this.var_f_b.var_boolean_f) {
            this.var_f_b.a(graphics);
            this.var_f_a.a(graphics);
        } else {
            this.var_f_a.a(graphics);
            this.var_f_b.a(graphics);
        }
        for (int j = 0; j < this.var_java_util_Vector_c.size(); ++j) {
            SpriteSheet e2 = this.var_java_util_Vector_c.elementAt(j);
            if (e2.var_boolean_a) {
                if (this.var_f_b.var_boolean_f) {
                    graphics.setClip(AppCanvas.h, 0, AppCanvas.h, AppCanvas.height2);
                } else {
                    graphics.setClip(0, 0, AppCanvas.h, AppCanvas.height2);
                }
            } else {
                graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
            }
            e2.a(graphics, 0, 0);
        }
        if (this.var_boolean_e) {
            Class_I.a(graphics, 0, this.var_int_m, 15, 1, null, 0, 0, AppCanvas.width2, AppCanvas.height2);
        }
    }

    public void c(int n) {
        this.var_boolean_q = true;
        this.var_long_m = n;
        this.var_long_b = this.var_long_n;
    }

    static {
        skirmishMapNames = new String[]{"Island Cross", "Rocky Bay"};
        var_byte_arr_arr_d = new byte[][]{{0, 1}, {2, 3}, {0, 1}, {4}};
        var_byte_arr_f = new byte[]{0};
        var_byte_arr_c = new byte[]{2};
        var_int_arr_a = new int[]{-16776961, -65536, -16711936};
        var_byte_arr_a = new byte[]{21, 22};

        // Terrain type variables
        // TODO these have 9 elements each, check for magic numbers
        // TODO any variabe used to access these arrays are terrainType, like I did to rename all the unitType
        // TODO Rename defense -> defence in all code (it's called like this in lang.dat...devs are British)
        terrainTypeDefense = new byte[]{0, 1, 2, 2, 3, 0, 0, 3, 3};
        terrainType_XXX = new byte[]{18, 3, 1, 2, 0, 21, 20, 23, 24};   // TODO used only once in the app
        // TODO The first "mountain" should be "hill", one of the last two towns is probably the castle
        terrainTypeNames = new String[]{"road", "grass", "woods", "mountain", "mountain", "water", "bridge", "town", "town"};
        // TODO after a couple tests I'm pretty sure this is correct but I'm not sure yet
        terrainTypeMovementReduction_XXX = new byte[]{1, 1, 2, 2, 3, 3, 1, 1, 1};
        var_java_lang_String_arr_d = new String[] {"14281428", "18241824"};
    }
}

