/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  javax.microedition.lcdui.Font
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class g {
    // TODO dehardcode remaining colors
    private static final int COLOR_WHITE = 0xFFFFFF;
    private static final int COLOR_BLACK = 0;
    private static final int COLOR_RED = 0xFF0000;  // TODO what is this used for? it's in other places in the app
    private static final int COLOR_UI_BACKGROUND = 13549221;    // 0xCEBEA5 - sand
    private static final int COLOR_UI_LINE_SEPARATOR = 10391157;  // 0x9e8e75 - brownish mid-dark gray

    private static final int FRAME_TOP_LEFT = 0;
    private static final int FRAME_TOP_RIGHT = 1;
    private static final int FRAME_BOTTOM_LEFT = 2;
    private static final int FRAME_BOTTOM_RIGHT = 3;

    private static final int FRAME_TOP = 4;
    private static final int FRAME_BOTTOM = 5;
    private static final int FRAME_LEFT = 6;
    private static final int FRAME_RIGHT = 7;

    public int j;
    public byte var_byte_e = (byte)3;
    private short var_short_a = (short)150;
    private a var_a_a;
    private String[] _descriptionLines;
    private int A;
    private int y;
    private int var_int_b;  // TODO has something to do with UI width
    public int var_int_g;
    private int m;
    private Font appFont = AppCanvas.fontSmallPlain;
    private int unitType_XX;
    private int buyableUnitsCount_XX;
    private int B;
    private int p;
    private long var_long_a;
    private int var_int_c;
    private int s;
    private int o;
    private int l;
    private int k;
    private int var_int_e;
    private int r;
    private int C;
    public byte var_byte_d = (byte)8;
    private byte _type;
    private int t;
    private boolean var_boolean_c = false;
    private g var_g_b;
    private byte[] buyableUnits;
    private int D;
    private int var_int_h;
    private int w = 1;
    private int var_int_f;
    private int v;
    private int var_int_a;
    private byte var_byte_c;
    private byte portraitSpriteIndex;
    private short var_short_b;
    private int u;
    private int var_int_d;
    private int var_int_i;
    private String[] _titleLines;
    private int _timeLeft;
    private boolean var_boolean_i = true;
    private boolean var_boolean_e = true;
    public boolean var_boolean_g = false;
    private boolean var_boolean_a;
    private boolean var_boolean_f = true;
    private int q;
    public Unit var_c_a;
    private boolean var_boolean_h = true;
    private boolean var_boolean_d = true;
    public g var_g_a;

    public static final byte TYPE_UNIT_INFO = 4;
    public static final byte TYPE_DIALOGUE = 6;
    public static final byte TYPE_MINIMAP = 7;
    /** Used for alerts like "OCCUPIED", "GAME SAVED" and "TURN END",
     * or campaign backstory at level start. */
    public static final byte TYPE_TIMED_INFOBOX = 9;

    public g(Class_I i2, byte type, int n) {
        this.var_a_a = (a)i2;
        this._type = type;
        this.t = n;
        this.B = i2.spriteMenuPointer.width;
        if (type == 0) {
            this.var_boolean_g = true;
            this.var_boolean_a = true;
        } else if (type == 2) {
            this.var_boolean_g = true;
            this.var_boolean_a = true;
            this.buyableUnits = Unit.getCurrentGameAllowedUnitTypes();
            this.buyableUnitsCount_XX = this.buyableUnits.length;
            this.var_int_b = 64;
            this.var_int_g = AppCanvas.height2 - 40;
            this.w = (this.var_int_b - 6 - 2) / 26;
            this.var_int_f = (this.var_int_g - 12 - 2) / 26;
            this.D = (this.var_int_b - 6 - 24 * this.w) / (this.w + 1);
            this.var_int_h = (this.var_int_f + this.D) * 24 + this.D > this.var_int_g - 6 ? (this.var_int_g - 12 - 24 * this.var_int_f) / (this.var_int_f + 1) : this.D;
            this.var_g_b = new g(i2, (byte)1, 4);
        } else if (type == 1 || type == TYPE_UNIT_INFO) {
            this.var_int_g = AppCanvas.height2;
            if (type == TYPE_UNIT_INFO) {
                this.var_boolean_a = true;
                this.j = 65;
                this.var_c_a = i2.tryGetUnit(i2.mapCursorX, i2.mapCursorY, Class_I.SEARCH_ALIVE);
                this.unitType_XX = this.var_c_a.unitType;
                this.var_byte_c = this.var_c_a.owner;
                this.var_int_b = AppCanvas.width2;
                this.var_int_g = AppCanvas.height2 * 2 / 3;
            } else {
                this.j = 65;
                this.var_byte_c = i2.currentPlayer;
                this.var_int_b = AppCanvas.width2 - 64;
                this.var_int_a = this.var_int_g - this.j - 12 - 4 - i2.uiArrowSheet.getSpritesHeight();
            }
            this.v = this.var_int_b - 16;
            this.var_int_a = this.var_int_g - this.j - 12 - 4 - i2.uiArrowSheet.getSpritesHeight();
            this.u = (this.var_int_a - 2) / (this.appFont.getBaselinePosition() + 2);
            this.D = (this.var_int_a - this.u * this.appFont.getBaselinePosition()) / (this.u + 1);
            // Unit description (74 is the description for the soldier - "Hardworking and brave, soldiers [...]")
            // Same thing at line 497
            this._descriptionLines = AppCanvas.a(AppCanvas.getGameText(74 + this.unitType_XX), this.v, this.appFont);
        } else if (type == 3) {
            this.var_boolean_f = false;
            this.var_boolean_h = false;
            this.var_boolean_d = false;
            this.var_int_b = 64;
            this.var_int_g = 40;
        } else if (type == 5) {
            this.var_boolean_f = false;
            this.var_boolean_h = false;
            this.var_boolean_d = false;
            this.b();
        } else if (type == TYPE_DIALOGUE) {
            this.var_int_g = i2.uiPortraitSheet.getSpritesHeight() + -5;
            this.var_int_b = AppCanvas.width2;
            int n2 = this.var_int_g - 6;
            n2 = n == 2 ? (n2 -= 2) : (n2 -= 6);
            g.a(this, this.appFont.getBaselinePosition(), n2, 1);
        } else if (type == TYPE_MINIMAP) {
            this.var_short_a = (short)400;
            this.var_boolean_a = true;
            this.var_int_b = i2.mapTilesWidth * i2.miniMapTerrainTiles[0].width + 12;
            this.var_int_g = i2.mapTilesHeight * i2.miniMapTerrainTiles[0].height + 12;
        }
        this.var_boolean_c = true;
    }

    /**
     *
     * @param i2
     * @param title Optional title text, shown at the top and horizontally centered.
     * @param description The main text content of the panel.
     * @param duration Milliseconds after which the panel will disappear automatically.
     *                 If equals -1, duration will be infinite and panel has to be closed manually.
     * @param bl
     * @return
     */
    public static g a(Class_I i2, String title, String description, int duration, boolean bl) {
        g panel = new g(i2, TYPE_TIMED_INFOBOX, 0);
        panel.D = 4;
        panel._timeLeft = duration;
        if (duration == -1) {
            panel.var_boolean_a = true;
        }
        Font font = panel.appFont;
        int descriptionWidth = font.stringWidth(description);
        int titleWidth = font.stringWidth(title);
        if (title != null && titleWidth > descriptionWidth) {
            descriptionWidth = titleWidth;
        }
        int txtMaxAllowedWidth = 0;
        if (descriptionWidth > txtMaxAllowedWidth) {
            txtMaxAllowedWidth = descriptionWidth;
        }
        int n3 = AppCanvas.width2 - panel.D * 4 - 12;
        if (txtMaxAllowedWidth > n3) {
            txtMaxAllowedWidth = n3;
        }
        if (title != null) {
            panel._titleLines = AppCanvas.a(title, txtMaxAllowedWidth, panel.appFont);
        }
        panel._descriptionLines = AppCanvas.a(description, txtMaxAllowedWidth, panel.appFont);
        panel.u = panel._descriptionLines.length;
        panel.m = panel.appFont.getBaselinePosition() + panel.D;
        int n6 = panel.u * panel.m + panel.D;
        if (title != null) {
            n6 += panel._titleLines.length * panel.m + panel.D;
        }
        panel.var_int_b = txtMaxAllowedWidth + panel.D * 4 + 12;
        panel.var_int_g = n6 + 12;
        if (panel.var_int_g > AppCanvas.height2) {
            n6 = AppCanvas.height2 - 12;
            if (title != null) {
                n6 -= panel.D;
            }
            g.a(panel, panel.appFont.getBaselinePosition(), n6, 4);
            panel.var_int_g = 12 + panel.m * panel.u + panel.D;
            if (title != null) {
                panel.var_int_g += panel.D;
                panel.u -= panel._titleLines.length;
            }
        }
        if (bl) {
            panel.a((byte)0, 0, 0, null, 0);
        }
        // TODO this feels useless since you return the panel
        i2.storyPanel = panel;
        return panel;
    }

    public static g a(Class_I i2, String text, byte portraitIndex, byte by2) {
        g panel = new g(i2, TYPE_DIALOGUE, by2);
        panel.portraitSpriteIndex = portraitIndex;
        if (portraitIndex == Class_I.PORTRAIT_NONE) {
            panel.var_int_d = panel.var_int_b - 24;
            panel._descriptionLines = AppCanvas.a(text, panel.var_int_d, panel.appFont);
        } else {
            panel.var_int_d = panel.var_int_b - i2.uiPortraitSheet.getSpritesWidth() - 6 - 12;
            panel._descriptionLines = AppCanvas.a(text, panel.var_int_d, panel.appFont);
        }
        panel.a(by2, 0, 0, null, 0);
        return panel;
    }

    public void b() {
        if (this._type == 5) {
            this.var_int_b = 40;
            this.var_int_g = 52;
            this.var_c_a = this.var_a_a.tryGetUnit(this.var_a_a.mapCursorX, this.var_a_a.mapCursorY, Class_I.SEARCH_ALIVE);
            if (this.var_c_a != null) {
                this.var_int_b = 68;
            }
            if (this.var_byte_d == 4) {
                this.var_int_c = 0;
                this.A = 0;
            } else {
                this.var_int_c = this.A = AppCanvas.width2 - this.var_int_b;
            }
        }
    }

    // Creates a menu with all the options passsed as argument
    // TODO this just creates the menu, it doesn't show it, rename accordingly
    public void showMenuOptions(String[] menuOptions) {
        this._descriptionLines = menuOptions;
        this.u = this.buyableUnitsCount_XX = menuOptions.length;
        this.var_int_b = 0;
        this.var_int_g = 0;
        this.D = 4;
        this.m = this.appFont.getBaselinePosition() + this.D;
        int n = 0;
        for (int j = 0; j < menuOptions.length; ++j) {
            int n2 = this.appFont.stringWidth(menuOptions[j]);
            if (n2 <= n) continue;
            n = n2;
        }
        this.var_int_b = n + this.D * 5 + this.B;
        if ((this.t & 8) == 0) {
            this.var_int_b += 6 + this.B;
        }
        if ((this.t & 4) == 0) {
            this.var_int_b += 6;
        }
        if (this.var_int_b > AppCanvas.width2) {
            this.var_int_b = AppCanvas.width2;
        }
        this.var_int_g = this.m * menuOptions.length + this.D + 12;
        if (this.var_int_g > AppCanvas.height2) {
            this.var_int_g = AppCanvas.height2;
            g.a(this, this.appFont.getBaselinePosition(), this.var_int_g - 12, this.D);
        }
    }

    private static void a(g g2, int n, int n2, int n3) {
        g2.D = n3;
        g2.m = n + n3;
        g2.u = (n2 - n3) / g2.m;
        g2.D = (n2 - g2.u * n) / (g2.u + 1);
        g2.m = n + g2.D;
    }

    private void c() {
        if (this.var_boolean_h) {
            ++this.var_a_a.var_int_g;
        }
        if (this.var_boolean_d) {
            ++this.var_a_a.M;
        }
        if (this._type == 2) {
            this.var_g_b.a((byte)4, 0, 0, null, 0);
            this.var_a_a.var_g_c.var_boolean_e = false;
            if (this.var_a_a.var_g_c.var_byte_d != 8) {
                this.var_a_a.var_g_c.void_a();
            }
            this.var_a_a.var_g_c.a((byte)8, 0, 0, null, 0);
        }
        if (this._type == TYPE_DIALOGUE || this._type == TYPE_UNIT_INFO || this._type == 5) {
            this.y = AppCanvas.height2 - this.var_int_g;
        }
        if ((this._type == 3 || this._type == 5 || this._type == 0) && this.var_boolean_e && this.boolean_a()) {
            this.void_a();
        }
        if (this._type == TYPE_MINIMAP || this._type == TYPE_TIMED_INFOBOX) {
            this.A = (AppCanvas.width2 - this.var_int_b) / 2;
            this.y = (AppCanvas.height2 - this.var_int_g) / 2;
        }
        if (this._type == TYPE_TIMED_INFOBOX) {
            this.var_a_a.timedInfobox = this;
        }
        this.s = this.y;
        this.var_int_c = this.A;
        this.var_byte_e = 0;
        switch (this.var_byte_d) {
            case 4: {
                this.var_int_c = -this.var_int_b;
                this.A = 0;
                break;
            }
            case 8: {
                this.A = AppCanvas.width2 - this.var_int_b;
                this.var_int_c = AppCanvas.width2;
                break;
            }
            case 1: {
                this.s = -this.var_int_g;
                break;
            }
            case 2: {
                this.s = AppCanvas.height2;
            }
        }
        int n = 9;
        this.k = ((this.A - this.var_int_c) * 2 << 10) / n;
        this.var_int_e = ((this.y - this.s) * 2 << 10) / n;
        this.o = this.k * 3;
        this.l = this.var_int_e * 3;
        this.r = this.var_int_c << 10;
        this.C = this.s << 10;
        this.q = 0;
        if (!this.var_a_a.var_java_util_Vector_e.contains(this)) {
            this.var_a_a.var_java_util_Vector_e.addElement(this);
        }
    }

    public void a(byte by, int n, int n2, g g2, int n3) {
        this.A = (n3 & 0x10) != 0 ? n - this.var_int_b / 2 : n;
        this.y = (n3 & 0x20) != 0 ? n2 - this.var_int_g / 2 : n2;
        this.var_g_a = g2;
        this.var_byte_d = by;
        this.c();
    }

    // TODO this sets the timed infobox as closed, have to dig more for the other panel types
    public void a(boolean bl) throws Exception {
        if (!this.var_boolean_e) {
            return;
        }
        if (this.var_boolean_h) {
            --this.var_a_a.var_int_g;
            --this.var_a_a.var_int_q;
        }
        if (this.var_boolean_d) {
            --this.var_a_a.M;
        }
        if (this._type == TYPE_TIMED_INFOBOX) {
            // Tells the game the infobox was closed (manually, or automatically if timed)
            this.var_a_a.timedInfobox = null;
        }
        if (this._type == 2) {
            this.var_g_b.a(false);
        }
        if (this._type == TYPE_MINIMAP || this._type == TYPE_UNIT_INFO || this._type == TYPE_TIMED_INFOBOX || bl) {
            this.var_byte_e = (byte)3;
            this.var_a_a.var_boolean_m = true;
            this.var_a_a.var_java_util_Vector_e.removeElement(this);
            if (this._type == 2) {
                this.var_a_a.var_g_c.var_boolean_e = true;
            }
            return;
        }
        this.var_byte_e = 1;
        this.s = this.y;
        this.var_int_c = this.A;
        this.o = 0;
        this.l = 0;
    }

    private boolean boolean_a() {
        return this.var_byte_d == 8 && this.var_a_a.mapCursorSheet.var_short_b + this.var_a_a.mapCursorSheet.getSpritesWidth() + this.var_a_a.var_short_f >= AppCanvas.cenX + 24 + 12 || this.var_byte_d == 4 && this.var_a_a.mapCursorSheet.var_short_b + this.var_a_a.var_short_f <= AppCanvas.cenX - 24 - 12;
    }

    private void void_a() {
        if (this.var_byte_d == 8) {
            this.t &= 0xFFFFFFF7;
            this.t |= 4;
            this.var_byte_d = (byte)4;
        } else if (this.var_byte_d == 4) {
            this.t &= 0xFFFFFFFB;
            this.t |= 8;
            this.var_byte_d = (byte)8;
        }
    }

    public void d() throws Exception {
        block112: {
            boolean bl;
            block113: {
                block98: {
                    block97: {
                        block102: {
                            block109: {
                                block111: {
                                    block110: {
                                        block106: {
                                            block108: {
                                                block107: {
                                                    block103: {
                                                        block105: {
                                                            block104: {
                                                                block99: {
                                                                    block101: {
                                                                        block100: {
                                                                            block95: {
                                                                                block96: {
                                                                                    if (this.var_byte_e == 3) {
                                                                                        return;
                                                                                    }
                                                                                    bl = false;
                                                                                    if (!this.var_boolean_a) break block95;
                                                                                    if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_UI_CONFIRM)) break block96;
                                                                                    if (!Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_CONFIRM)) break block95;
                                                                                }
                                                                                bl = true;
                                                                                Class_I.appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_UI_CONFIRM);
                                                                                Class_I.appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_CONFIRM);
                                                                            }
                                                                            if (this._type != 2 && this._type != 0) break block97;
                                                                            if (this.var_byte_e != 2) break block98;
                                                                            if (!Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_DOWN)) break block99;
                                                                            if (this.var_boolean_i) break block100;
                                                                            if (!Class_I.appCanvas.stoppedRequestingAction(AppCanvas.ACTION_DOWN)) break block101;
                                                                        }
                                                                        this.unitType_XX += this.w;
                                                                        if (this.unitType_XX >= this.buyableUnitsCount_XX) {
                                                                            this.unitType_XX -= this.buyableUnitsCount_XX;
                                                                            this.var_short_b = 0;
                                                                        }
                                                                        if (this.unitType_XX - this.var_short_b >= this.u) {
                                                                            this.var_short_b++;
                                                                        }
                                                                        this.var_boolean_c = true;
                                                                    }
                                                                    this.var_boolean_i = false;
                                                                    break block102;
                                                                }
                                                                if (!Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_UP)) break block103;
                                                                if (this.var_boolean_i) break block104;
                                                                if (!Class_I.appCanvas.stoppedRequestingAction(AppCanvas.ACTION_UP)) break block105;
                                                            }
                                                            this.unitType_XX -= this.w;
                                                            if (this.unitType_XX < 0) {
                                                                this.unitType_XX = this.buyableUnitsCount_XX + this.unitType_XX;
                                                                this.var_short_b = (short)(this.buyableUnitsCount_XX - this.u);
                                                            }
                                                            if (this.unitType_XX < this.var_short_b) {
                                                                this.var_short_b--;
                                                            }
                                                            this.var_boolean_c = true;
                                                        }
                                                        this.var_boolean_i = false;
                                                        break block102;
                                                    }
                                                    if (!Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_LEFT)) break block106;
                                                    if (this.var_boolean_i) break block107;
                                                    if (!Class_I.appCanvas.stoppedRequestingAction(AppCanvas.ACTION_LEFT)) break block108;
                                                }
                                                if (this._type == 2) {
                                                    --this.unitType_XX;
                                                    if (this.unitType_XX < 0) {
                                                        this.unitType_XX = this.buyableUnitsCount_XX - 1;
                                                    }
                                                    this.var_boolean_c = true;
                                                }
                                            }
                                            this.var_boolean_i = false;
                                            break block102;
                                        }
                                        if (!Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_RIGHT)) break block109;
                                        if (this.var_boolean_i) break block110;
                                        if (!Class_I.appCanvas.stoppedRequestingAction(AppCanvas.ACTION_RIGHT)) break block111;
                                    }
                                    if (this._type == 2) {
                                        ++this.unitType_XX;
                                        if (this.unitType_XX >= this.buyableUnitsCount_XX) {
                                            this.unitType_XX = 0;
                                        }
                                        this.var_boolean_c = true;
                                    }
                                }
                                this.var_boolean_i = false;
                                break block102;
                            }
                            this.var_boolean_i = true;
                        }
                        if (bl) {
                            bl = false;
                            if (this._type != 2 || this.var_a_a.playersMoney[this.var_a_a.currentPlayer] >= Unit.unitsDataPrice[this.buyableUnits[this.unitType_XX]]) {
                                if (this._type == 2) {
                                    this.var_a_a.a((int)this.buyableUnits[this.unitType_XX], "", this);
                                } else {
                                    this.var_a_a.a(this.unitType_XX, this._descriptionLines[this.unitType_XX], this);
                                }
                                return;
                            }
                        }
                        if (this._type == 2 && this.var_boolean_c) {
                            this.var_g_b.var_boolean_c = true;
                            this.var_g_b.unitType_XX = this.unitType_XX;
                            // Unit description (74 is the description for the soldier - "Hardworking and brave, soldiers [...]")
                            // Same thing at line 109
                            this.var_g_b._descriptionLines = AppCanvas.a(AppCanvas.getGameText(74 + this.buyableUnits[this.unitType_XX]), this.var_g_b.v, this.appFont);
                            this.var_g_b.var_short_b = 0;
                        }
                        break block98;
                    }
                    if (this._type == TYPE_DIALOGUE) {
                        if (this.var_byte_e == 2) {
                            if (Class_I.appCanvas.pressedKeysActions != 0) {
                                if (this.var_short_b + this.u >= this._descriptionLines.length) {
                                    this.a(false);
                                } else {
                                    this.var_short_b = (short)(this.var_short_b + this.u);
                                }
                                Class_I.appCanvas.pressedKeysActions = 0;
                                this.var_boolean_c = true;
                            }
                        }
                    } else if (this._type == TYPE_TIMED_INFOBOX) {
                        if (this._timeLeft != -1) {
                            if (this._timeLeft > 0) {
                                this._timeLeft -= 50;
                            }
                            // If duration time was exceeded
                            if (this._timeLeft <= 0) {
                                this.a(true);
                                if (this.var_g_a != null) {
                                    this.var_g_a.c();
                                }
                            }
                        }
                        if (this.var_int_i > 0) {
                            this.var_int_i -= 2;
                            if (this.var_int_i < 0) {
                                this.var_int_i = 0;
                            }
                            this.var_boolean_c = true;
                        } else if (this.var_int_i < 0) {
                            this.var_int_i += 2;
                            if (this.var_int_i > 0) {
                                this.var_int_i = 0;
                            }
                            this.var_boolean_c = true;
                        }
                        if (this.var_int_i == 0) {
                            if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_UP) && this.var_short_b > 0) {
                                this.var_int_i = -this.m;
                                this.var_short_b--;
                                this.var_boolean_c = true;
                            }
                            if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_DOWN) && this.var_short_b + this.u < this._descriptionLines.length) {
                                this.var_int_i = this.m;
                                this.var_short_b++;
                                this.var_boolean_c = true;
                            }
                        }
                    } else if (this._type == 1 || this._type == TYPE_UNIT_INFO) {
                        if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_SCROLL_UP)) {
                            if (this.var_short_b > 0) {
                                this.var_short_b--;
                                this.var_boolean_c = true;
                            }
                            Class_I.appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_SCROLL_UP);
                        }
                        if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_UNIT_INFO_OR_SCROLL_DOWN)) {
                            if (this.var_short_b + this.u < this._descriptionLines.length) {
                                this.var_short_b++;
                                this.var_boolean_c = true;
                            }
                            Class_I.appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_UNIT_INFO_OR_SCROLL_DOWN);
                        }
                    } else if (this._type == 3 || this._type == 5) {
                        if (this.var_byte_e != 1 && this.boolean_a()) {
                            this.a(false);
                        }
                        this.var_boolean_c = true;
                    } else if (this._type == TYPE_MINIMAP) {
                        if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_UP)) {
                            if (this.s < 0) {
                                ++this.s;
                                ++this.y;
                                this.var_boolean_c = true;
                            }
                        } else if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_DOWN) && this.s + this.var_int_g > AppCanvas.height2) {
                            --this.s;
                            --this.y;
                            this.var_boolean_c = true;
                        }
                        if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_LEFT)) {
                            if (this.var_int_c < 0) {
                                ++this.var_int_c;
                                ++this.A;
                                this.var_boolean_c = true;
                            }
                        } else if (Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_RIGHT) && this.var_int_c + this.var_int_b > AppCanvas.width2) {
                            --this.var_int_c;
                            --this.A;
                            this.var_boolean_c = true;
                        }
                    }
                }
                if (this.var_boolean_f && this.var_a_a.time - this.var_long_a >= (long)this.var_short_a) {
                    this.p = this.p == 0 ? 2 : 0;
                    this.var_long_a = this.var_a_a.time;
                    this.var_boolean_c = true;
                }
                if (this.var_byte_e != 2) break block112;
                if (bl) break block113;
                if (!this.var_boolean_g) break block112;
                if (!Class_I.appCanvas.isRequestingAction(AppCanvas.ACTION_CANCEL)) break block112;
            }
            Class_I.appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_CANCEL);
            this.a(true);
            this.var_a_a.c();
            if (!bl && this.var_g_a != null) {
                this.var_g_a.c();
            }
            return;
        }
        switch (this.var_byte_e) {
            case 0: {
                if (this.var_byte_d == 0) {
                    ++this.q;
                    if (this.q >= 3) {
                        this.var_byte_e = (byte)2;
                        if (this.var_boolean_h) {
                            ++this.var_a_a.var_int_q;
                        }
                        Class_I.appCanvas.keyReleased(-1);
                    }
                } else {
                    this.o -= this.k;
                    this.l -= this.var_int_e;
                    this.r += this.o;
                    this.C += this.l;
                    this.var_int_c = this.r >> 10;
                    this.s = this.C >> 10;
                    if (this.var_byte_d == 4 && this.o == 0 || this.var_byte_d == 8 && this.o == 0 || this.var_byte_d == 1 && this.l == 0 || this.var_byte_d == 2 && this.l == 0) {
                        this.var_int_c = this.A;
                        this.s = this.y;
                        this.var_byte_e = (byte)2;
                        if (this.var_boolean_h) {
                            ++this.var_a_a.var_int_q;
                        }
                        Class_I.appCanvas.keyReleased(-1);
                    }
                }
                this.var_boolean_c = true;
                this.var_a_a.var_boolean_m = true;
                break;
            }
            case 1: {
                this.o -= this.k;
                this.l -= this.var_int_e;
                this.var_int_c += this.o;
                this.s += this.l;
                if (this.var_int_c <= -this.var_int_b || this.var_int_c >= AppCanvas.width2 || this.s <= -this.var_int_g || this.s >= AppCanvas.height2) {
                    this.var_byte_e = (byte)3;
                    this.var_a_a.var_boolean_m = true;
                    this.var_a_a.var_java_util_Vector_e.removeElement(this);
                    return;
                }
                this.var_boolean_c = true;
            }
        }
    }

    public void a(Graphics graphics) {
        if (this.var_byte_e == 3) {
            return;
        }
        if (!this.var_boolean_c) {
            return;
        }
        this.var_boolean_c = false;
        int translateY = 0;
        int translateX = 0;
        int n3 = 0;
        int n4 = 0;
        if (this.var_byte_d == 0 && (this.var_byte_e == 0 || this.var_byte_e == 1)) {
            graphics.setColor(COLOR_WHITE);
            int n5 = this.var_int_b * this.q / 3;
            int n6 = this.var_int_g * this.q / 3;
            graphics.drawRect(this.A + (this.var_int_b - n5) / 2, this.y + (this.var_int_g - n6) / 2, n5, n6);
            return;
        }
        this.a(graphics, this.var_int_c, this.s, this.var_int_b, this.var_int_g, this.t);
        n3 = this.var_int_b;
        n4 = this.var_int_g;
        translateY = this.s;
        translateX = this.var_int_c;
        if ((this.t & 1) == 0) {
            n4 -= 6;
            translateY += 6;
        }
        if ((this.t & 2) == 0) {
            n4 -= 6;
        }
        if ((this.t & 4) == 0) {
            translateX += 6;
            n3 -= 6;
        }
        if ((this.t & 8) == 0) {
            n3 -= 6;
        }
        graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
        graphics.translate(translateX, translateY);
        graphics.setFont(this.appFont);
        if (this.var_byte_e == 2 || this._type == 3 || this._type == 5) {
            block0 : switch (this._type) {
                case 0: {
                    if (this.var_byte_e != 2) break;
                    graphics.setColor(-16777216);   // 0xFF000000 - The FF should be ignored, so this is black
                    int n7 = this.B + this.D * 3;
                    int n8 = this.D;
                    for (int j = this.var_short_b; j < this.var_short_b + this.u; ++j) {
                        graphics.drawString(this._descriptionLines[j], n7, n8, 20);
                        n8 += this.m;
                    }
                    this.var_a_a.spriteMenuPointer.draw(graphics, this.p + this.D, (this.unitType_XX - this.var_short_b) * this.m + this.D);
                    break;
                }
                case 1:
                case TYPE_UNIT_INFO: {
                    int n9;
                    int n10;
                    int n11;
                    int n12;
                    int n13 = 2;
                    int n14 = 2;
                    this.var_a_a.a(this.var_byte_c, (byte)this.unitType_XX).a(graphics, n13, n14);
                    n13 += 26;
                    graphics.setColor(COLOR_BLACK);
                    // TODO first check is useless because always true in this switch branch
                    if (this._type == TYPE_UNIT_INFO && this.var_c_a.customName != null) {
                        // This is used only when the unit is a King and is a specific character in the story
                        graphics.drawString(this.var_c_a.customName, n13, n14, 20);
                    } else {
                        // SOLDIER + unitType (shows name of the unit)
                        graphics.drawString(AppCanvas.getGameText(63 + this.unitType_XX), n13, n14, 20);
                    }
                    graphics.setColor(COLOR_BLACK);
                    //String string = "";
                    StringBuffer stringBuffer = new StringBuffer();
                    //int n15 = 4;
                    if (this._type == 1) {
                        this.var_a_a.spriteGold.draw(graphics, n13, n14 += this.appFont.getBaselinePosition() + 2);
                        AppCanvas.drawBoldWhiteText(graphics, "" + Unit.unitsDataPrice[this.unitType_XX], n13 += this.var_a_a.spriteGold.width + 2, n14 + 3, AppCanvas.FONT_ALPHANUMERIC);
                    } else {
                        graphics.setColor(COLOR_UI_LINE_SEPARATOR);
                        graphics.drawLine(0, n13, n3 - 1, n14 += 26);
                        n12 = n3 / 2;
                        graphics.drawLine(n12, n14, n12, this.j);
                        graphics.setColor(COLOR_BLACK);
                        AppCanvas.drawBoldWhiteText(graphics, "STATUS", (n12 += 3) + 6, n14 += 3, AppCanvas.FONT_ALPHANUMERIC);
                        n14 += AppCanvas.getSpriteFontCharHeight(AppCanvas.FONT_ALPHANUMERIC) + 2;
                        // TODO it would be much more readable to have a hasStatus method, but maybe this inlining is an optimization?
                        if (this.var_c_a.hasStatus(Unit.STATUS_POISON)) {
                            this.var_a_a.uiStatusSheet.setCurrentIndex(Class_I.STATUS_SHEET_POISON);
                            this.var_a_a.uiStatusSheet.a(graphics, n12, n14 - 2);
                            // POISON
                            graphics.drawString(AppCanvas.getGameText(46), n12 + this.var_a_a.uiStatusSheet.getSpritesWidth() + 2, n14, 20);
                            n14 += this.var_a_a.uiStatusSheet.getSpritesHeight() - 4;
                        }
                        if (this.var_c_a.hasStatus(Unit.STATUS_AURA)) {
                            this.var_a_a.uiStatusSheet.setCurrentIndex(Class_I.STATUS_SHEET_AURA);
                            this.var_a_a.uiStatusSheet.a(graphics, n12, n14);
                            // AURA
                            graphics.drawString(AppCanvas.getGameText(47), n12 + this.var_a_a.uiStatusSheet.getSpritesWidth() + 2, n14 + 2, 20);
                        }
                        AppCanvas.drawBoldWhiteText_XX(graphics, "" + this.var_c_a.quantity, n3 - 4, 6, AppCanvas.FONT_NUMERIC, 8);
                        graphics.setFont(this.appFont);
                        this.var_a_a.uiStatusSheet.setCurrentIndex(Class_I.STATUS_SHEET_STAR);
                        for (n11 = 0; n11 < this.var_c_a.stars; ++n11) {
                            this.var_a_a.uiStatusSheet.a(graphics, n13, this.appFont.getBaselinePosition() + 2);
                            n13 += this.var_a_a.uiStatusSheet.getSpritesWidth();
                        }
                    }


                    graphics.setColor(COLOR_RED);
                    AppCanvas.drawBoldWhiteText(graphics, "ATK", 2, 33, AppCanvas.FONT_ALPHANUMERIC);
                    stringBuffer.append(Unit.unitsDataATK[this.unitType_XX]);
                    if (this._type == TYPE_UNIT_INFO) {
                        if (this.var_c_a.statusModAtk > 0) {
                            stringBuffer.append("+");
                        }
                        if (this.var_c_a.statusModAtk != 0) {
                            stringBuffer.append(this.var_c_a.statusModAtk);
                        }
                        n13 = n3 / 2 - 2;
                    } else {
                        n13 = n3 - 2 - 2;
                    }
                    String string2 = stringBuffer.toString();
                    AppCanvas.drawBoldWhiteText_XX(graphics, string2, n13, 33, AppCanvas.FONT_ALPHANUMERIC, 8);


                    AppCanvas.drawBoldWhiteText(graphics, "DEF", 2, 43, AppCanvas.FONT_ALPHANUMERIC);
                    stringBuffer = new StringBuffer();
                    stringBuffer.append(Unit.unitsDataDEF[this.unitType_XX]);
                    if (this._type == 4) {
                        if (this.var_c_a.statusModDef > 0) {
                            stringBuffer.append("+");
                        }
                        if (this.var_c_a.statusModDef != 0) {
                            stringBuffer.append(this.var_c_a.statusModDef);
                        }
                    }
                    string2 = stringBuffer.toString();
                    AppCanvas.drawBoldWhiteText_XX(graphics, string2, n13, 43, AppCanvas.FONT_ALPHANUMERIC, 8);


                    AppCanvas.drawBoldWhiteText(graphics, "MOV", 2, 53, AppCanvas.FONT_ALPHANUMERIC);
                    stringBuffer = new StringBuffer();
                    stringBuffer.append(Unit.unitsDataMOV[this.unitType_XX]);
                    if (this._type == TYPE_UNIT_INFO) {
                        if (this.var_c_a.statusModMov > 0) {
                            stringBuffer.append("+");
                        }
                        if (this.var_c_a.statusModMov != 0) {
                            stringBuffer.append(this.var_c_a.statusModMov);
                        }
                    }
                    string2 = stringBuffer.toString();
                    AppCanvas.drawBoldWhiteText_XX(graphics, string2, n13, 53, AppCanvas.FONT_ALPHANUMERIC, 8);

                    n12 = this.j;
                    n11 = n3 / 2;
                    graphics.setColor(-6386059);
                    graphics.drawLine(0, n12, n3 - 1, n12);
                    graphics.drawLine(0, n12 + this.var_int_a, n3 - 1, n12 + this.var_int_a);
                    graphics.setColor(COLOR_BLACK);
                    n12 += this.D;
                    for (n10 = this.var_short_b; n10 < this.var_short_b + this.u && n10 < this._descriptionLines.length; ++n10) {
                        graphics.drawString(this._descriptionLines[n10], n11, n12, 17);
                        n12 += this.appFont.getBaselinePosition() + this.D;
                    }
                    if (this.var_short_b != 0) {
                        this.var_a_a.uiArrowSheet.setCurrentIndex(Class_I.ARROW_UP);
                        n10 = (n3 - this.var_a_a.uiArrowSheet.getSpritesWidth()) / 2;
                        n9 = this.j - this.var_a_a.uiArrowSheet.getSpritesHeight() / 2;
                        this.var_a_a.uiArrowSheet.a(graphics, n10, n9 - this.p);
                        AppCanvas.drawBoldWhiteText(graphics, "1", n10 + this.var_a_a.uiArrowSheet.getSpritesWidth(), n9, AppCanvas.FONT_ALPHANUMERIC);
                    }
                    if (this.var_short_b + this.u >= this._descriptionLines.length) break;
                    this.var_a_a.uiArrowSheet.setCurrentIndex(Class_I.ARROW_DOWN);
                    n10 = (n3 - this.var_a_a.uiArrowSheet.getSpritesWidth()) / 2;
                    n9 = this.j + this.var_int_a - this.var_a_a.uiArrowSheet.getSpritesHeight() / 2;
                    this.var_a_a.uiArrowSheet.a(graphics, n10, n9 + this.p);
                    AppCanvas.drawBoldWhiteText(graphics, "7", n10 + this.var_a_a.uiArrowSheet.getSpritesWidth(), n9, AppCanvas.FONT_ALPHANUMERIC);
                    break;
                }
                case 2: {
                    int n16 = 0;
                    int n17 = this.D;
                    int n18 = this.var_int_h;
                    for (int j = 0; j < this.var_int_f; ++j) {
                        for (int k = 0; k < this.w; ++k) {
                            SpriteSheet e2 = this.var_a_a.var_e_arr_arr_b[this.var_a_a.currentPlayer][this.buyableUnits[n16]];
                            e2.a(graphics, n17, n18);
                            if (this.var_a_a.playersMoney[this.var_a_a.currentPlayer] < Unit.unitsDataPrice[this.buyableUnits[n16]]) {
                                Class_I.appCanvas.drawSpriteMask(graphics, n17, n18, e2.getSpritesWidth(), e2.getSpritesHeight(), -1328628059);
                            }
                            if (n16 == this.unitType_XX) {
                                this.var_a_a.spriteMenuPointer.draw(graphics, n17 - this.var_a_a.spriteMenuPointer.width + this.p, n18);
                            }
                            if (++n16 >= this.buyableUnits.length) break;
                            n17 += this.D + 24;
                        }
                        if (n16 >= this.buyableUnits.length) break block0;
                        n17 = this.D;
                        n18 += this.var_int_h + 24;
                    }
                    break;
                }
                case 3: {
                    this.var_a_a.spriteGold.draw(graphics, 2, 2);
                    if (this.var_a_a.var_byte_arr_b[this.var_a_a.currentPlayer] == 0) {
                        this.var_a_a.uiBtnIconSheet.setCurrentIndex(Class_I.BTN_ICON_INFO);
                        this.var_a_a.uiBtnIconSheet.a(graphics, n3 - 2 - this.var_a_a.uiBtnIconSheet.getSpritesWidth(), 2);
                    } else {
                        AppCanvas.drawBoldWhiteText_XX(graphics, "" + this.var_a_a.playersMoney[this.var_a_a.currentPlayerIndex_XX], n3 - 2, 5, AppCanvas.FONT_ALPHANUMERIC, 8);
                    }
                    int n19 = 16;
                    int n20 = this.var_int_g - n19 - 6;
                    graphics.setClip(0, n19, n3, n20);
                    graphics.setColor(Class_I.playerColors[this.var_a_a.currentPlayer]);
                    graphics.fillRect(0, n19 + 1, n3, n20);
                    int n21 = 0;
                    if (this.var_a_a.currentPlayer == Class_I.PLAYER_RED) {
                        n21 = n3 - this.var_a_a.uiPortraitSheet.getSpritesWidth();
                    }
                    this.var_a_a.uiPortraitSheet.setCurrentIndex(this.var_a_a.currentPlayer);
                    this.var_a_a.uiPortraitSheet.a(graphics, n21, n19 - 10);
                    break;
                }
                case TYPE_DIALOGUE: {
                    graphics.setFont(this.appFont);
                    int n22 = this.D;
                    int n23 = 4;
                    n23 = this.t == 4 ? (n23 += this.var_a_a.uiPortraitSheet.getSpritesWidth()) : (n23 += 4);
                    graphics.setColor(COLOR_BLACK);
                    for (int j = this.var_short_b; j < this.var_short_b + this.u && j < this._descriptionLines.length; ++j) {
                        graphics.drawString(this._descriptionLines[j], n23, n22, 20);
                        n22 += this.m;
                    }
                    if (this.portraitSpriteIndex != Class_I.PORTRAIT_NONE) {
                        this.var_a_a.uiPortraitSheet.setCurrentIndex(this.portraitSpriteIndex);
                        if (this.t == 4) {
                            this.var_a_a.uiPortraitSheet.a(graphics, 0, -11);
                        } else if (this.t == 8) {
                            this.var_a_a.uiPortraitSheet.a(graphics, this.var_int_b - 6 - this.var_a_a.uiPortraitSheet.getSpritesWidth(), -11);
                        }
                    }
                    this.var_a_a.uiArrowSheet.setCurrentIndex(Class_I.ARROW_DOWN);
                    this.var_a_a.uiArrowSheet.a(graphics, n23 + this.var_int_d - this.var_a_a.uiArrowSheet.getSpritesWidth(), this.var_int_g - 6 - this.var_a_a.uiArrowSheet.getSpritesHeight() + this.p);
                    break;
                }
                case 5: {
                    byte terrainType = this.var_a_a.getTerrainType(this.var_a_a.mapCursorX, this.var_a_a.mapCursorY);
                    byte by2 = this.var_a_a.mapTerrain[this.var_a_a.mapCursorX][this.var_a_a.mapCursorY] >= this.var_a_a.var_int_t ? this.var_a_a.mapTerrain[this.var_a_a.mapCursorX][this.var_a_a.mapCursorY] : Class_I.terrainType_XXX[terrainType];
                    int n24 = 6;
                    this.var_a_a.var_h_arr_c[by2].draw(graphics, n24, 2);
                    graphics.setColor(COLOR_BLACK);
                    graphics.drawRect(n24, 2, 23, 23);
                    int n25 = 28;
                    AppCanvas.drawBoldWhiteText(graphics, "DEF", 7, n25, AppCanvas.FONT_ALPHANUMERIC);
                    AppCanvas.drawBoldWhiteText(graphics, "" + Class_I.terrainTypeDefense[terrainType], 14, n25 += 9, AppCanvas.FONT_ALPHANUMERIC);
                    if (this.var_c_a == null) break;
                    this.var_a_a.a(this.var_c_a.owner, this.var_c_a.unitType).a(graphics, 35, 2);
                    n25 = 22;
                    int n26 = 31;
                    this.var_a_a.uiStatusSheet.setCurrentIndex(Class_I.STATUS_SHEET_STAR);
                    for (int j = 0; j < this.var_c_a.stars; ++j) {
                        this.var_a_a.uiStatusSheet.a(graphics, n26, n25);
                        n26 += this.var_a_a.uiStatusSheet.getSpritesWidth() - (this.var_a_a.uiStatusSheet.getSpritesWidth() >> 1);
                    }
                    n25 += this.var_a_a.uiStatusSheet.getSpritesHeight() - 5;
                    n26 = 31;
                    if (this.var_c_a.hasStatus(Unit.STATUS_POISON)) {
                        this.var_a_a.uiStatusSheet.setCurrentIndex(Class_I.STATUS_SHEET_POISON);
                        this.var_a_a.uiStatusSheet.a(graphics, n26, n25);
                        n26 += this.var_a_a.uiStatusSheet.getSpritesWidth();
                    }
                    if (this.var_c_a.hasStatus(Unit.STATUS_AURA)) {
                        this.var_a_a.uiStatusSheet.setCurrentIndex(Class_I.STATUS_SHEET_AURA);
                        this.var_a_a.uiStatusSheet.a(graphics, n26, n25);
                    }
                    break;
                }
                case TYPE_MINIMAP: {
                    short miniMapTileWidth = this.var_a_a.miniMapTerrainTiles[0].width;
                    short miniMapTileHeight = this.var_a_a.miniMapTerrainTiles[0].height;

                    int y = 0;
                    for (int mapY = 0; mapY < this.var_a_a.mapTilesHeight; ++mapY) {
                        int x = 0;
                        for (int mapX = 0; mapX < this.var_a_a.mapTilesWidth; ++mapX) {
                            int terrainType = this.var_a_a.getTerrainType(mapX, mapY);
                            int n32 = this.var_a_a.int_a(mapX, mapY);
                            if (n32 >= 0) {
                                terrainType = 2 * (n32 + 1) + 7 + terrainType - 7;
                            }
                            this.var_a_a.miniMapTerrainTiles[terrainType].draw(graphics, x, y);
                            x += miniMapTileWidth;
                        }
                        y += miniMapTileHeight;
                    }

                    if (this.p != 0)
                        break;

                    int unitsCount = this.var_a_a.mapUnitsList.size();
                    for (int i = 0; i < unitsCount; ++i) {
                        Unit unit = this.var_a_a.mapUnitsList.elementAt(i);
                        this.var_a_a.miniMapUnitsSheets[unit.owner][unit.unitType].a(graphics, unit.mapX * miniMapTileWidth, unit.mapY * miniMapTileHeight);
                    }
                    break;
                }
                case TYPE_TIMED_INFOBOX: {
                    graphics.setClip(0, 0, n3, this.var_int_g - 12);
                    int n35 = 0;
                    int n36 = this.D;
                    if (this._titleLines != null) {
                        graphics.setColor(COLOR_BLACK);
                        for (byte i = 0; i < this._titleLines.length; ++i) {
                            graphics.drawString(this._titleLines[i], n3 / 2, n36, 17);
                            n36 += this.m;  // TODO rename _lineSpacing or _textLineSpacing
                        }
                        graphics.setColor(COLOR_UI_LINE_SEPARATOR);
                        graphics.drawLine(0, n36, n3 - 1, n36);
                        n35 = n36;
                        n36 += this.D;
                    }
                    graphics.setColor(COLOR_BLACK);
                    int n34 = this.var_short_b;
                    int n37 = this.var_short_b + this.u;
                    if (this.var_int_i > 0) {
                        --n34;
                        n36 -= this.m;
                    } else if (this.var_int_i < 0) {
                        ++n37;
                    }
                    n36 += this.var_int_i;
                    graphics.setClip(0, n35, n3, n4 - n35);
                    for (int i = n34; i < n37; ++i) {
                        graphics.drawString(this._descriptionLines[i], n3 / 2, n36, 17);
                        n36 += this.m;
                    }
                    graphics.setClip(-6, -6, this.var_int_b, this.var_int_g);
                    if (this.var_short_b > 0) {
                        this.var_a_a.uiArrowSheet.setCurrentIndex(Class_I.ARROW_UP);
                        int n33 = (n3 - this.var_a_a.uiArrowSheet.getSpritesWidth()) / 2;
                        this.var_a_a.uiArrowSheet.a(graphics, n33, n35 + this.p - this.var_a_a.uiArrowSheet.getSpritesHeight());
                    }
                    if (this.var_short_b + this.u >= this._descriptionLines.length) break;
                    this.var_a_a.uiArrowSheet.setCurrentIndex(Class_I.ARROW_DOWN);
                    int n33 = (n3 - this.var_a_a.uiArrowSheet.getSpritesWidth()) / 2;
                    int n38 = this.var_int_g - 12;
                    this.var_a_a.uiArrowSheet.a(graphics, n33, n38 - this.p);
                }
            }
        }
        graphics.translate(-translateX, -translateY);
        graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
        if (this.var_byte_e == 2) {
            if (this.var_boolean_a) {
                this.var_a_a.uiBtnIconSheet.setCurrentIndex(Class_I.BTN_ICON_CONFIRM);
                this.var_a_a.uiBtnIconSheet.a(graphics, 0, AppCanvas.height2 - this.var_a_a.uiBtnIconSheet.getSpritesHeight());
            }
            if (this.var_boolean_g) {
                this.var_a_a.uiBtnIconSheet.setCurrentIndex(Class_I.BTN_ICON_BACK);
                this.var_a_a.uiBtnIconSheet.a(graphics, AppCanvas.width2 - this.var_a_a.uiBtnIconSheet.getSpritesWidth(), AppCanvas.height2 - this.var_a_a.uiBtnIconSheet.getSpritesHeight());
            }
        }
    }

    // TODO this draws UI panels!
    private void a(Graphics graphics, int n, int n2, int n3, int n4, int n5) {
        int n6;
        SpriteSheet frameSheet = this.var_a_a.uiPanelFrameSheet;
        graphics.setClip(n, n2, n3, n4);
        graphics.setColor(COLOR_UI_BACKGROUND);
        int n7 = n3;
        int n8 = n4;
        int n9 = n;
        int n10 = n2;
        int n11 = n3;
        if ((n5 & 4) == 0) {
            n11 -= frameSheet.getSpritesWidth();
            n7 -= 6;
            n9 += 6;
        }
        if ((n5 & 8) == 0) {
            n11 -= frameSheet.getSpritesWidth();
            n7 -= 6;
        }
        int n12 = n4;
        if ((n5 & 1) == 0) {
            n12 -= frameSheet.getSpritesHeight();
            n8 -= 6;
            n10 += 6;
        }
        if ((n5 & 2) == 0) {
            n12 -= frameSheet.getSpritesHeight();
            n8 -= 6;
        }
        graphics.fillRect(n9, n10, n7, n8);
        int n13 = n11 / frameSheet.getSpritesWidth();
        int n14 = n12 / frameSheet.getSpritesHeight();
        if (n11 % frameSheet.getSpritesWidth() > 0) {
            ++n13;
        }
        if (n12 % frameSheet.getSpritesHeight() > 0) {
            ++n14;
        }
        int n15 = n + frameSheet.getSpritesWidth();
        int n16 = n2 + n4 - frameSheet.getSpritesHeight();
        for (n6 = 0; n6 < n13; ++n6) {
            if ((n5 & 1) == 0) {
                frameSheet.setCurrentIndex(FRAME_TOP);
                frameSheet.a(graphics, n15, n2);
            }
            if ((n5 & 2) == 0) {
                frameSheet.setCurrentIndex(FRAME_BOTTOM);
                frameSheet.a(graphics, n15, n16);
            }
            n15 += frameSheet.getSpritesWidth();
        }
        n15 = n + n3 - frameSheet.getSpritesWidth();
        n16 = n2 + frameSheet.getSpritesHeight();
        for (n6 = 0; n6 < n14; ++n6) {
            if ((n5 & 4) == 0) {
                frameSheet.setCurrentIndex(FRAME_LEFT);
                frameSheet.a(graphics, n, n16);
            }
            if ((n5 & 8) == 0) {
                frameSheet.setCurrentIndex(FRAME_RIGHT);
                frameSheet.a(graphics, n15, n16);
            }
            n16 += frameSheet.getSpritesHeight();
        }
        if ((n5 & 1) == 0) {
            if ((n5 & 4) == 0) {
                frameSheet.setCurrentIndex(FRAME_TOP_LEFT);
                frameSheet.a(graphics, n, n2);
            } else {
                frameSheet.setCurrentIndex(FRAME_TOP);
                frameSheet.a(graphics, n, n2);
            }
            if ((n5 & 8) == 0) {
                frameSheet.setCurrentIndex(FRAME_TOP_RIGHT);
                frameSheet.a(graphics, n + n3 - 24, n2);
            } else {
                frameSheet.setCurrentIndex(FRAME_TOP);
                frameSheet.a(graphics, n + n3 - 24, n2);
            }
        } else {
            if ((n5 & 4) == 0) {
                frameSheet.setCurrentIndex(FRAME_LEFT);
                frameSheet.a(graphics, n, n2);
            }
            if ((n5 & 8) == 0) {
                frameSheet.setCurrentIndex(FRAME_RIGHT);
                frameSheet.a(graphics, n + n3 - 24, n2);
            }
        }
        n16 = n2 + n4 - 24;
        if ((n5 & 2) == 0) {
            if ((n5 & 4) == 0) {
                frameSheet.setCurrentIndex(FRAME_BOTTOM_LEFT);
                frameSheet.a(graphics, n, n16);
            } else {
                frameSheet.setCurrentIndex(FRAME_BOTTOM);
                frameSheet.a(graphics, n, n16);
            }
            if ((n5 & 8) == 0) {
                frameSheet.setCurrentIndex(FRAME_BOTTOM_RIGHT);
                frameSheet.a(graphics, n + n3 - 24, n16);
            } else {
                frameSheet.setCurrentIndex(FRAME_BOTTOM);
                frameSheet.a(graphics, n + n3 - 24, n16);
            }
        } else {
            if ((n5 & 4) == 0) {
                frameSheet.setCurrentIndex(FRAME_LEFT);
                frameSheet.a(graphics, n, n16);
            }
            if ((n5 & 8) == 0) {
                frameSheet.setCurrentIndex(FRAME_RIGHT);
                frameSheet.a(graphics, n + n3 - 24, n16);
            }
        }
    }
}

