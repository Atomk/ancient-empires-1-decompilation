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
    public int j;
    public byte var_byte_e = (byte)3;
    private short var_short_a = (short)150;
    private a var_a_a;
    private String[] var_java_lang_String_arr_b;
    private int A;
    private int y;
    private int var_int_b;
    public int var_int_g;
    private int m;
    private Font appFont = AppCanvas.fontSmallPlain;
    private int unitType_XX;
    private int z;
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
    private byte var_byte_a;
    private int t;
    private boolean var_boolean_c = false;
    private g var_g_b;
    private byte[] var_byte_arr_a;
    private int D;
    private int var_int_h;
    private int w = 1;
    private int var_int_f;
    private int v;
    private int var_int_a;
    private byte var_byte_c;
    private byte var_byte_b;
    private short var_short_b;
    private int u;
    private int var_int_d;
    private int var_int_i;
    private String[] var_java_lang_String_arr_a;
    private int n;
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

    public g(Class_I i2, byte by, int n) {
        this.var_a_a = (a)i2;
        this.var_byte_a = by;
        this.t = n;
        this.B = i2.spriteMenuPointer.width;
        if (by == 0) {
            this.var_boolean_g = true;
            this.var_boolean_a = true;
        } else if (by == 2) {
            this.var_boolean_g = true;
            this.var_boolean_a = true;
            this.var_byte_arr_a = Unit.byte_arr_a();
            this.z = this.var_byte_arr_a.length;
            this.var_int_b = 64;
            this.var_int_g = AppCanvas.height2 - 40;
            this.w = (this.var_int_b - 6 - 2) / 26;
            this.var_int_f = (this.var_int_g - 12 - 2) / 26;
            this.D = (this.var_int_b - 6 - 24 * this.w) / (this.w + 1);
            this.var_int_h = (this.var_int_f + this.D) * 24 + this.D > this.var_int_g - 6 ? (this.var_int_g - 12 - 24 * this.var_int_f) / (this.var_int_f + 1) : this.D;
            this.var_g_b = new g(i2, (byte)1, 4);
        } else if (by == 1 || by == 4) {
            this.var_int_g = AppCanvas.height2;
            if (by == 4) {
                this.var_boolean_a = true;
                this.j = 65;
                this.var_c_a = i2.c_a((int)i2.var_short_h, (int)i2.var_short_g, (byte)0);
                this.unitType_XX = this.var_c_a.unitType;
                this.var_byte_c = this.var_c_a.var_byte_a;
                this.var_int_b = AppCanvas.width2;
                this.var_int_g = AppCanvas.height2 * 2 / 3;
            } else {
                this.j = 65;
                this.var_byte_c = i2.var_byte_c;
                this.var_int_b = AppCanvas.width2 - 64;
                this.var_int_a = this.var_int_g - this.j - 12 - 4 - i2.var_e_g.getSpritesHeight();
            }
            this.v = this.var_int_b - 16;
            this.var_int_a = this.var_int_g - this.j - 12 - 4 - i2.var_e_g.getSpritesHeight();
            this.u = (this.var_int_a - 2) / (this.appFont.getBaselinePosition() + 2);
            this.D = (this.var_int_a - this.u * this.appFont.getBaselinePosition()) / (this.u + 1);
            // Unit description (74 is the description for the soldier - "Hardworking and brave, soldiers [...]")
            // Same thing at line 497
            this.var_java_lang_String_arr_b = AppCanvas.a(AppCanvas.getGameText(74 + this.unitType_XX), this.v, this.appFont);
        } else if (by == 3) {
            this.var_boolean_f = false;
            this.var_boolean_h = false;
            this.var_boolean_d = false;
            this.var_int_b = 64;
            this.var_int_g = 40;
        } else if (by == 5) {
            this.var_boolean_f = false;
            this.var_boolean_h = false;
            this.var_boolean_d = false;
            this.b();
        } else if (by == 6) {
            this.var_int_g = i2.var_e_m.getSpritesHeight() + -5;
            this.var_int_b = AppCanvas.width2;
            int n2 = this.var_int_g - 6;
            n2 = n == 2 ? (n2 -= 2) : (n2 -= 6);
            g.a(this, this.appFont.getBaselinePosition(), n2, 1);
        } else if (by == 7) {
            this.var_short_a = (short)400;
            this.var_boolean_a = true;
            this.var_int_b = i2.var_short_e * i2.var_h_arr_d[0].width + 12;
            this.var_int_g = i2.var_short_b * i2.var_h_arr_d[0].height + 12;
        }
        this.var_boolean_c = true;
    }

    public static g a(Class_I i2, String string, String string2, int n, boolean bl) {
        int n2;
        g g2 = new g(i2, (byte)9, 0);
        g2.D = 4;
        g2.n = n;
        if (n == -1) {
            g2.var_boolean_a = true;
        }
        Font font = g2.appFont;
        int n3 = AppCanvas.width2 - g2.D * 4 - 12;
        int n4 = font.stringWidth(string2);
        int n5 = 0;
        if (string != null && (n2 = font.stringWidth(string)) > n4) {
            n4 = n2;
        }
        if (n4 > n5) {
            n5 = n4;
        }
        if (n5 > n3) {
            n5 = n3;
        }
        if (string != null) {
            g2.var_java_lang_String_arr_a = AppCanvas.a(string, n5, g2.appFont);
        }
        g2.var_java_lang_String_arr_b = AppCanvas.a(string2, n5, g2.appFont);
        g2.u = g2.var_java_lang_String_arr_b.length;
        g2.m = g2.appFont.getBaselinePosition() + g2.D;
        int n6 = g2.u * g2.m + g2.D;
        if (string != null) {
            n6 += g2.var_java_lang_String_arr_a.length * g2.m + g2.D;
        }
        g2.var_int_b = n5 + g2.D * 4 + 12;
        g2.var_int_g = n6 + 12;
        if (g2.var_int_g > AppCanvas.height2) {
            n6 = AppCanvas.height2 - 12;
            if (string != null) {
                n6 -= g2.D;
            }
            g.a(g2, g2.appFont.getBaselinePosition(), n6, 4);
            g2.var_int_g = 12 + g2.m * g2.u + g2.D;
            if (string != null) {
                g2.var_int_g += g2.D;
                g2.u -= g2.var_java_lang_String_arr_a.length;
            }
        }
        if (bl) {
            g2.a((byte)0, 0, 0, null, 0);
        }
        i2.var_g_b = g2;
        return g2;
    }

    public static g a(Class_I i2, String string, byte by, byte by2) {
        byte by3 = by2;
        g g2 = new g(i2, (byte)6, by3);
        g2.var_byte_b = by;
        if (by == -1) {
            by3 = 0;
            g2.var_int_d = g2.var_int_b - 24;
            g2.var_java_lang_String_arr_b = AppCanvas.a(string, g2.var_int_d, g2.appFont);
        } else {
            g2.var_int_d = g2.var_int_b - i2.var_e_m.getSpritesWidth() - 6 - 12;
            g2.var_java_lang_String_arr_b = AppCanvas.a(string, g2.var_int_d, g2.appFont);
        }
        g2.a(by2, 0, 0, null, 0);
        return g2;
    }

    public void b() {
        if (this.var_byte_a == 5) {
            this.var_int_b = 40;
            this.var_int_g = 52;
            this.var_c_a = this.var_a_a.c_a((int)this.var_a_a.var_short_h, (int)this.var_a_a.var_short_g, (byte)0);
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
        this.var_java_lang_String_arr_b = menuOptions;
        this.u = this.z = menuOptions.length;
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
        if (this.var_byte_a == 2) {
            this.var_g_b.a((byte)4, 0, 0, null, 0);
            this.var_a_a.var_g_c.var_boolean_e = false;
            if (this.var_a_a.var_g_c.var_byte_d != 8) {
                this.var_a_a.var_g_c.void_a();
            }
            this.var_a_a.var_g_c.a((byte)8, 0, 0, null, 0);
        }
        if (this.var_byte_a == 6 || this.var_byte_a == 4 || this.var_byte_a == 5) {
            this.y = AppCanvas.height2 - this.var_int_g;
        }
        if ((this.var_byte_a == 3 || this.var_byte_a == 5 || this.var_byte_a == 0) && this.var_boolean_e && this.boolean_a()) {
            this.void_a();
        }
        if (this.var_byte_a == 7 || this.var_byte_a == 9) {
            this.A = (AppCanvas.width2 - this.var_int_b) / 2;
            this.y = (AppCanvas.height2 - this.var_int_g) / 2;
        }
        if (this.var_byte_a == 9) {
            this.var_a_a.var_g_i = this;
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
        if (this.var_byte_a == 9) {
            this.var_a_a.var_g_i = null;
        }
        if (this.var_byte_a == 2) {
            this.var_g_b.a(false);
        }
        if (this.var_byte_a == 7 || this.var_byte_a == 4 || this.var_byte_a == 9 || bl) {
            this.var_byte_e = (byte)3;
            this.var_a_a.var_boolean_m = true;
            this.var_a_a.var_java_util_Vector_e.removeElement(this);
            if (this.var_byte_a == 2) {
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
        return this.var_byte_d == 8 && this.var_a_a.var_e_h.var_short_b + this.var_a_a.var_e_h.getSpritesWidth() + this.var_a_a.var_short_f >= AppCanvas.h + 24 + 12 || this.var_byte_d == 4 && this.var_a_a.var_e_h.var_short_b + this.var_a_a.var_short_f <= AppCanvas.h - 24 - 12;
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
                                                                                    if (Class_I.appCanvas.boolean_c(1024)) break block96;
                                                                                    if (!Class_I.appCanvas.boolean_c(16)) break block95;
                                                                                }
                                                                                bl = true;
                                                                                Class_I.appCanvas.handleKeyReleasedAction(1024);
                                                                                Class_I.appCanvas.handleKeyReleasedAction(16);
                                                                            }
                                                                            if (this.var_byte_a != 2 && this.var_byte_a != 0) break block97;
                                                                            if (this.var_byte_e != 2) break block98;
                                                                            if (!Class_I.appCanvas.boolean_c(2)) break block99;
                                                                            if (this.var_boolean_i) break block100;
                                                                            if (!Class_I.appCanvas.boolean_a(2)) break block101;
                                                                        }
                                                                        this.unitType_XX += this.w;
                                                                        if (this.unitType_XX >= this.z) {
                                                                            this.unitType_XX -= this.z;
                                                                            this.var_short_b = 0;
                                                                        }
                                                                        if (this.unitType_XX - this.var_short_b >= this.u) {
                                                                            this.var_short_b = (short)(this.var_short_b + 1);
                                                                        }
                                                                        this.var_boolean_c = true;
                                                                    }
                                                                    this.var_boolean_i = false;
                                                                    break block102;
                                                                }
                                                                if (!Class_I.appCanvas.boolean_c(1)) break block103;
                                                                if (this.var_boolean_i) break block104;
                                                                if (!Class_I.appCanvas.boolean_a(1)) break block105;
                                                            }
                                                            this.unitType_XX -= this.w;
                                                            if (this.unitType_XX < 0) {
                                                                this.unitType_XX = this.z + this.unitType_XX;
                                                                this.var_short_b = (short)(this.z - this.u);
                                                            }
                                                            if (this.unitType_XX < this.var_short_b) {
                                                                this.var_short_b = (short)(this.var_short_b - 1);
                                                            }
                                                            this.var_boolean_c = true;
                                                        }
                                                        this.var_boolean_i = false;
                                                        break block102;
                                                    }
                                                    if (!Class_I.appCanvas.boolean_c(4)) break block106;
                                                    if (this.var_boolean_i) break block107;
                                                    if (!Class_I.appCanvas.boolean_a(4)) break block108;
                                                }
                                                if (this.var_byte_a == 2) {
                                                    --this.unitType_XX;
                                                    if (this.unitType_XX < 0) {
                                                        this.unitType_XX = this.z - 1;
                                                    }
                                                    this.var_boolean_c = true;
                                                }
                                            }
                                            this.var_boolean_i = false;
                                            break block102;
                                        }
                                        if (!Class_I.appCanvas.boolean_c(8)) break block109;
                                        if (this.var_boolean_i) break block110;
                                        if (!Class_I.appCanvas.boolean_a(8)) break block111;
                                    }
                                    if (this.var_byte_a == 2) {
                                        ++this.unitType_XX;
                                        if (this.unitType_XX >= this.z) {
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
                            if (this.var_byte_a != 2 || this.var_a_a.var_int_arr_b[this.var_a_a.var_byte_c] >= Unit.unitsDataPrice[this.var_byte_arr_a[this.unitType_XX]]) {
                                if (this.var_byte_a == 2) {
                                    this.var_a_a.a((int)this.var_byte_arr_a[this.unitType_XX], "", this);
                                } else {
                                    this.var_a_a.a(this.unitType_XX, this.var_java_lang_String_arr_b[this.unitType_XX], this);
                                }
                                return;
                            }
                        }
                        if (this.var_byte_a == 2 && this.var_boolean_c) {
                            this.var_g_b.var_boolean_c = true;
                            this.var_g_b.unitType_XX = this.unitType_XX;
                            // Unit description (74 is the description for the soldier - "Hardworking and brave, soldiers [...]")
                            // Same thing at line 109
                            this.var_g_b.var_java_lang_String_arr_b = AppCanvas.a(AppCanvas.getGameText(74 + this.var_byte_arr_a[this.unitType_XX]), this.var_g_b.v, this.appFont);
                            this.var_g_b.var_short_b = 0;
                        }
                        break block98;
                    }
                    if (this.var_byte_a == 6) {
                        if (this.var_byte_e == 2) {
                            if (Class_I.appCanvas.pressedKeysActions != 0) {
                                if (this.var_short_b + this.u >= this.var_java_lang_String_arr_b.length) {
                                    this.a(false);
                                } else {
                                    this.var_short_b = (short)(this.var_short_b + this.u);
                                }
                                Class_I.appCanvas.pressedKeysActions = 0;
                                this.var_boolean_c = true;
                            }
                        }
                    } else if (this.var_byte_a == 9) {
                        if (this.n != -1) {
                            if (this.n > 0) {
                                this.n -= 50;
                            }
                            if (this.n <= 0) {
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
                            if (Class_I.appCanvas.boolean_c(1) && this.var_short_b > 0) {
                                this.var_int_i = -this.m;
                                this.var_short_b = (short)(this.var_short_b - 1);
                                this.var_boolean_c = true;
                            }
                            if (Class_I.appCanvas.boolean_c(2) && this.var_short_b + this.u < this.var_java_lang_String_arr_b.length) {
                                this.var_int_i = this.m;
                                this.var_short_b = (short)(this.var_short_b + 1);
                                this.var_boolean_c = true;
                            }
                        }
                    } else if (this.var_byte_a == 1 || this.var_byte_a == 4) {
                        if (Class_I.appCanvas.boolean_c(64)) {
                            if (this.var_short_b > 0) {
                                this.var_short_b = (short)(this.var_short_b - 1);
                                this.var_boolean_c = true;
                            }
                            Class_I.appCanvas.handleKeyReleasedAction(64);
                        }
                        if (Class_I.appCanvas.boolean_c(256)) {
                            if (this.var_short_b + this.u < this.var_java_lang_String_arr_b.length) {
                                this.var_short_b = (short)(this.var_short_b + 1);
                                this.var_boolean_c = true;
                            }
                            Class_I.appCanvas.handleKeyReleasedAction(256);
                        }
                    } else if (this.var_byte_a == 3 || this.var_byte_a == 5) {
                        if (this.var_byte_e != 1 && this.boolean_a()) {
                            this.a(false);
                        }
                        this.var_boolean_c = true;
                    } else if (this.var_byte_a == 7) {
                        if (Class_I.appCanvas.boolean_c(1)) {
                            if (this.s < 0) {
                                ++this.s;
                                ++this.y;
                                this.var_boolean_c = true;
                            }
                        } else if (Class_I.appCanvas.boolean_c(2) && this.s + this.var_int_g > AppCanvas.height2) {
                            --this.s;
                            --this.y;
                            this.var_boolean_c = true;
                        }
                        if (Class_I.appCanvas.boolean_c(4)) {
                            if (this.var_int_c < 0) {
                                ++this.var_int_c;
                                ++this.A;
                                this.var_boolean_c = true;
                            }
                        } else if (Class_I.appCanvas.boolean_c(8) && this.var_int_c + this.var_int_b > AppCanvas.width2) {
                            --this.var_int_c;
                            --this.A;
                            this.var_boolean_c = true;
                        }
                    }
                }
                if (this.var_boolean_f && this.var_a_a.var_long_n - this.var_long_a >= (long)this.var_short_a) {
                    this.p = this.p == 0 ? 2 : 0;
                    this.var_long_a = this.var_a_a.var_long_n;
                    this.var_boolean_c = true;
                }
                if (this.var_byte_e != 2) break block112;
                if (bl) break block113;
                if (!this.var_boolean_g) break block112;
                if (!Class_I.appCanvas.boolean_c(2048)) break block112;
            }
            Class_I.appCanvas.handleKeyReleasedAction(2048);
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
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        if (this.var_byte_d == 0 && (this.var_byte_e == 0 || this.var_byte_e == 1)) {
            graphics.setColor(0xFFFFFF);
            int n5 = this.var_int_b * this.q / 3;
            int n6 = this.var_int_g * this.q / 3;
            graphics.drawRect(this.A + (this.var_int_b - n5) / 2, this.y + (this.var_int_g - n6) / 2, n5, n6);
            return;
        }
        this.a(graphics, this.var_int_c, this.s, this.var_int_b, this.var_int_g, this.t);
        n3 = this.var_int_b;
        n4 = this.var_int_g;
        n = this.s;
        n2 = this.var_int_c;
        if ((this.t & 1) == 0) {
            n4 -= 6;
            n += 6;
        }
        if ((this.t & 2) == 0) {
            n4 -= 6;
        }
        if ((this.t & 4) == 0) {
            n2 += 6;
            n3 -= 6;
        }
        if ((this.t & 8) == 0) {
            n3 -= 6;
        }
        graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
        graphics.translate(n2, n);
        graphics.setFont(this.appFont);
        if (this.var_byte_e == 2 || this.var_byte_a == 3 || this.var_byte_a == 5) {
            block0 : switch (this.var_byte_a) {
                case 0: {
                    if (this.var_byte_e != 2) break;
                    graphics.setColor(-16777216);
                    int n7 = this.B + this.D * 3;
                    int n8 = this.D;
                    for (int j = this.var_short_b; j < this.var_short_b + this.u; ++j) {
                        graphics.drawString(this.var_java_lang_String_arr_b[j], n7, n8, 20);
                        n8 += this.m;
                    }
                    this.var_a_a.spriteMenuPointer.draw(graphics, this.p + this.D, (this.unitType_XX - this.var_short_b) * this.m + this.D);
                    break;
                }
                case 1: 
                case 4: {
                    int n9;
                    int n10;
                    int n11;
                    int n12;
                    int n13 = 2;
                    int n14 = 2;
                    this.var_a_a.a(this.var_byte_c, (byte)this.unitType_XX).a(graphics, n13, n14);
                    n13 += 26;
                    graphics.setColor(0);
                    if (this.var_byte_a == 4 && this.var_c_a.var_java_lang_String_a != null) {
                        graphics.drawString(this.var_c_a.var_java_lang_String_a, n13, n14, 20);
                    } else {
                        // SOLDIER
                        graphics.drawString(AppCanvas.getGameText(63 + this.unitType_XX), n13, n14, 20);
                    }
                    graphics.setColor(0);
                    //String string = "";
                    StringBuffer stringBuffer = new StringBuffer();
                    //int n15 = 4;
                    if (this.var_byte_a == 1) {
                        this.var_a_a.spriteGold.draw(graphics, n13, n14 += this.appFont.getBaselinePosition() + 2);
                        AppCanvas.drawBoldWhiteText(graphics, "" + Unit.unitsDataPrice[this.unitType_XX], n13 += this.var_a_a.spriteGold.width + 2, n14 + 3, 0);
                    } else {
                        graphics.setColor(10391157);
                        graphics.drawLine(0, n13, n3 - 1, n14 += 26);
                        n12 = n3 / 2;
                        graphics.drawLine(n12, n14, n12, this.j);
                        graphics.setColor(0);
                        AppCanvas.drawBoldWhiteText(graphics, "STATUS", (n12 += 3) + 6, n14 += 3, 0);
                        n14 += AppCanvas.a((byte)0) + 2;
                        if ((this.var_c_a.var_byte_b & 1) != 0) {
                            this.var_a_a.var_e_j.a(0);
                            this.var_a_a.var_e_j.a(graphics, n12, n14 - 2);
                            // POISON
                            graphics.drawString(AppCanvas.getGameText(46), n12 + this.var_a_a.var_e_j.getSpritesWidth() + 2, n14, 20);
                            n14 += this.var_a_a.var_e_j.getSpritesHeight() - 4;
                        }
                        if ((this.var_c_a.var_byte_b & 2) != 0) {
                            this.var_a_a.var_e_j.a(1);
                            this.var_a_a.var_e_j.a(graphics, n12, n14);
                            // AURA
                            graphics.drawString(AppCanvas.getGameText(47), n12 + this.var_a_a.var_e_j.getSpritesWidth() + 2, n14 + 2, 20);
                        }
                        AppCanvas.a(graphics, "" + this.var_c_a.h, n3 - 4, 6, 1, 8);
                        graphics.setFont(this.appFont);
                        this.var_a_a.var_e_j.a(2);
                        for (n11 = 0; n11 < this.var_c_a.var_short_d; ++n11) {
                            this.var_a_a.var_e_j.a(graphics, n13, this.appFont.getBaselinePosition() + 2);
                            n13 += this.var_a_a.var_e_j.getSpritesWidth();
                        }
                    }
                    graphics.setColor(0xFF0000);
                    AppCanvas.drawBoldWhiteText(graphics, "ATK", 2, 33, 0);
                    stringBuffer.append(Unit.unitsDataATK[this.unitType_XX]);
                    if (this.var_byte_a == 4) {
                        if (this.var_c_a.var_short_f > 0) {
                            stringBuffer.append("+");
                        }
                        if (this.var_c_a.var_short_f != 0) {
                            stringBuffer.append(this.var_c_a.var_short_f);
                        }
                        n13 = n3 / 2 - 2;
                    } else {
                        n13 = n3 - 2 - 2;
                    }
                    String string2 = stringBuffer.toString();
                    AppCanvas.a(graphics, string2, n13, 33, 0, 8);
                    AppCanvas.drawBoldWhiteText(graphics, "DEF", 2, 43, 0);
                    stringBuffer = new StringBuffer();
                    stringBuffer.append(Unit.unitsDataDEF[this.unitType_XX]);
                    if (this.var_byte_a == 4) {
                        if (this.var_c_a.var_short_e > 0) {
                            stringBuffer.append("+");
                        }
                        if (this.var_c_a.var_short_e != 0) {
                            stringBuffer.append(this.var_c_a.var_short_e);
                        }
                    }
                    string2 = stringBuffer.toString();
                    AppCanvas.a(graphics, string2, n13, 43, 0, 8);
                    AppCanvas.drawBoldWhiteText(graphics, "MOV", 2, 53, 0);
                    stringBuffer = new StringBuffer();
                    stringBuffer.append(Unit.unitsDataMOV[this.unitType_XX]);
                    if (this.var_byte_a == 4) {
                        if (this.var_c_a.k > 0) {
                            stringBuffer.append("+");
                        }
                        if (this.var_c_a.k != 0) {
                            stringBuffer.append(this.var_c_a.k);
                        }
                    }
                    string2 = stringBuffer.toString();
                    AppCanvas.a(graphics, string2, n13, 53, 0, 8);
                    n12 = this.j;
                    n11 = n3 / 2;
                    graphics.setColor(-6386059);
                    graphics.drawLine(0, n12, n3 - 1, n12);
                    graphics.drawLine(0, n12 + this.var_int_a, n3 - 1, n12 + this.var_int_a);
                    graphics.setColor(0);
                    n12 += this.D;
                    for (n10 = this.var_short_b; n10 < this.var_short_b + this.u && n10 < this.var_java_lang_String_arr_b.length; ++n10) {
                        graphics.drawString(this.var_java_lang_String_arr_b[n10], n11, n12, 17);
                        n12 += this.appFont.getBaselinePosition() + this.D;
                    }
                    if (this.var_short_b != 0) {
                        this.var_a_a.var_e_g.a(0);
                        n10 = (n3 - this.var_a_a.var_e_g.getSpritesWidth()) / 2;
                        n9 = this.j - this.var_a_a.var_e_g.getSpritesHeight() / 2;
                        this.var_a_a.var_e_g.a(graphics, n10, n9 - this.p);
                        AppCanvas.drawBoldWhiteText(graphics, "1", n10 + this.var_a_a.var_e_g.getSpritesWidth(), n9, 0);
                    }
                    if (this.var_short_b + this.u >= this.var_java_lang_String_arr_b.length) break;
                    this.var_a_a.var_e_g.a(1);
                    n10 = (n3 - this.var_a_a.var_e_g.getSpritesWidth()) / 2;
                    n9 = this.j + this.var_int_a - this.var_a_a.var_e_g.getSpritesHeight() / 2;
                    this.var_a_a.var_e_g.a(graphics, n10, n9 + this.p);
                    AppCanvas.drawBoldWhiteText(graphics, "7", n10 + this.var_a_a.var_e_g.getSpritesWidth(), n9, 0);
                    break;
                }
                case 2: {
                    int n16 = 0;
                    int n17 = this.D;
                    int n18 = this.var_int_h;
                    for (int j = 0; j < this.var_int_f; ++j) {
                        for (int k = 0; k < this.w; ++k) {
                            SpriteSheet e2 = this.var_a_a.var_e_arr_arr_b[this.var_a_a.var_byte_c][this.var_byte_arr_a[n16]];
                            e2.a(graphics, n17, n18);
                            if (this.var_a_a.var_int_arr_b[this.var_a_a.var_byte_c] < Unit.unitsDataPrice[this.var_byte_arr_a[n16]]) {
                                Class_I.appCanvas.a(graphics, n17, n18, e2.getSpritesWidth(), e2.getSpritesHeight(), -1328628059);
                            }
                            if (n16 == this.unitType_XX) {
                                this.var_a_a.spriteMenuPointer.draw(graphics, n17 - this.var_a_a.spriteMenuPointer.width + this.p, n18);
                            }
                            if (++n16 >= this.var_byte_arr_a.length) break;
                            n17 += this.D + 24;
                        }
                        if (n16 >= this.var_byte_arr_a.length) break block0;
                        n17 = this.D;
                        n18 += this.var_int_h + 24;
                    }
                    break;
                }
                case 3: {
                    this.var_a_a.spriteGold.draw(graphics, 2, 2);
                    if (this.var_a_a.var_byte_arr_b[this.var_a_a.var_byte_c] == 0) {
                        this.var_a_a.var_e_e.a(2);
                        this.var_a_a.var_e_e.a(graphics, n3 - 2 - this.var_a_a.var_e_e.getSpritesWidth(), 2);
                    } else {
                        AppCanvas.a(graphics, "" + this.var_a_a.var_int_arr_b[this.var_a_a.var_byte_g], n3 - 2, 5, 0, 8);
                    }
                    int n19 = 16;
                    int n20 = this.var_int_g - n19 - 6;
                    graphics.setClip(0, n19, n3, n20);
                    graphics.setColor(Class_I.var_int_arr_a[this.var_a_a.var_byte_c]);
                    graphics.fillRect(0, n19 + 1, n3, n20);
                    int n21 = 0;
                    if (this.var_a_a.var_byte_c == 1) {
                        n21 = n3 - this.var_a_a.var_e_m.getSpritesWidth();
                    }
                    this.var_a_a.var_e_m.a(this.var_a_a.var_byte_c);
                    this.var_a_a.var_e_m.a(graphics, n21, n19 - 10);
                    break;
                }
                case 6: {
                    graphics.setFont(this.appFont);
                    int n22 = this.D;
                    int n23 = 4;
                    n23 = this.t == 4 ? (n23 += this.var_a_a.var_e_m.getSpritesWidth()) : (n23 += 4);
                    graphics.setColor(0);
                    for (int j = this.var_short_b; j < this.var_short_b + this.u && j < this.var_java_lang_String_arr_b.length; ++j) {
                        graphics.drawString(this.var_java_lang_String_arr_b[j], n23, n22, 20);
                        n22 += this.m;
                    }
                    if (this.var_byte_b != -1) {
                        this.var_a_a.var_e_m.a(this.var_byte_b);
                        if (this.t == 4) {
                            this.var_a_a.var_e_m.a(graphics, 0, -11);
                        } else if (this.t == 8) {
                            this.var_a_a.var_e_m.a(graphics, this.var_int_b - 6 - this.var_a_a.var_e_m.getSpritesWidth(), -11);
                        }
                    }
                    this.var_a_a.var_e_g.a(1);
                    this.var_a_a.var_e_g.a(graphics, n23 + this.var_int_d - this.var_a_a.var_e_g.getSpritesWidth(), this.var_int_g - 6 - this.var_a_a.var_e_g.getSpritesHeight() + this.p);
                    break;
                }
                case 5: {
                    byte terrainType = this.var_a_a.getTerrainType_ZZ(this.var_a_a.var_short_h, (int)this.var_a_a.var_short_g);
                    byte by2 = this.var_a_a.var_byte_arr_arr_a[this.var_a_a.var_short_h][this.var_a_a.var_short_g] >= this.var_a_a.var_int_t ? this.var_a_a.var_byte_arr_arr_a[this.var_a_a.var_short_h][this.var_a_a.var_short_g] : Class_I.terrainType_XXX[terrainType];
                    int n24 = 6;
                    this.var_a_a.var_h_arr_c[by2].draw(graphics, n24, 2);
                    graphics.setColor(0);
                    graphics.drawRect(n24, 2, 23, 23);
                    int n25 = 28;
                    AppCanvas.drawBoldWhiteText(graphics, "DEF", 7, n25, 0);
                    AppCanvas.drawBoldWhiteText(graphics, "" + Class_I.terrainTypeDefense[terrainType], 14, n25 += 9, 0);
                    if (this.var_c_a == null) break;
                    this.var_a_a.a(this.var_c_a.var_byte_a, this.var_c_a.unitType).a(graphics, 35, 2);
                    n25 = 22;
                    int n26 = 31;
                    this.var_a_a.var_e_j.a(2);
                    for (int j = 0; j < this.var_c_a.var_short_d; ++j) {
                        this.var_a_a.var_e_j.a(graphics, n26, n25);
                        n26 += this.var_a_a.var_e_j.getSpritesWidth() - (this.var_a_a.var_e_j.getSpritesWidth() >> 1);
                    }
                    n25 += this.var_a_a.var_e_j.getSpritesHeight() - 5;
                    n26 = 31;
                    if ((this.var_c_a.var_byte_b & 1) != 0) {
                        this.var_a_a.var_e_j.a(0);
                        this.var_a_a.var_e_j.a(graphics, n26, n25);
                        n26 += this.var_a_a.var_e_j.getSpritesWidth();
                    }
                    if ((this.var_c_a.var_byte_b & 2) == 0) break;
                    this.var_a_a.var_e_j.a(1);
                    this.var_a_a.var_e_j.a(graphics, n26, n25);
                    break;
                }
                case 7: {
                    int n27;
                    int n28;
                    short s = this.var_a_a.var_h_arr_d[0].width;
                    short s2 = this.var_a_a.var_h_arr_d[0].height;
                    int n29 = 0;
                    for (n28 = 0; n28 < this.var_a_a.var_short_b; ++n28) {
                        int n30 = 0;
                        for (n27 = 0; n27 < this.var_a_a.var_short_e; ++n27) {
                            int n31 = this.var_a_a.getTerrainType_ZZ(n27, n28);
                            int n32 = this.var_a_a.int_a(n27, n28);
                            if (n32 >= 0) {
                                n31 = 2 * (n32 + 1) + 7 + n31 - 7;
                            }
                            this.var_a_a.var_h_arr_d[n31].draw(graphics, n30, n29);
                            n30 += s;
                        }
                        n29 += s2;
                    }
                    if (this.p != 0) break;
                    n27 = this.var_a_a.var_java_util_Vector_a.size();
                    for (n28 = 0; n28 < n27; ++n28) {
                        Unit c2 = (Unit)this.var_a_a.var_java_util_Vector_a.elementAt(n28);
                        this.var_a_a.var_e_arr_arr_c[c2.var_byte_a][c2.unitType].a(graphics, c2.i * s, c2.var_short_a * s2);
                    }
                    break;
                }
                case 9: {
                    int n33;
                    int n34;
                    graphics.setClip(0, 0, n3, this.var_int_g - 12);
                    int n35 = 0;
                    int n36 = this.D;
                    if (this.var_java_lang_String_arr_a != null) {
                        graphics.setColor(0);
                        for (n34 = 0; n34 < this.var_java_lang_String_arr_a.length; ++n34) {
                            graphics.drawString(this.var_java_lang_String_arr_a[n34], n3 / 2, n36, 17);
                            n36 += this.m;
                        }
                        // TODO convert to hex and name it
                        graphics.setColor(10391157);
                        graphics.drawLine(0, n36, n3 - 1, n36);
                        n35 = n36;
                        n36 += this.D;
                    }
                    graphics.setColor(0);
                    n34 = this.var_short_b;
                    int n37 = this.var_short_b + this.u;
                    if (this.var_int_i > 0) {
                        --n34;
                        n36 -= this.m;
                    } else if (this.var_int_i < 0) {
                        ++n37;
                    }
                    n36 += this.var_int_i;
                    graphics.setClip(0, n35, n3, n4 - n35);
                    for (n33 = n34; n33 < n37; ++n33) {
                        graphics.drawString(this.var_java_lang_String_arr_b[n33], n3 / 2, n36, 17);
                        n36 += this.m;
                    }
                    graphics.setClip(-6, -6, this.var_int_b, this.var_int_g);
                    if (this.var_short_b > 0) {
                        this.var_a_a.var_e_g.a(0);
                        n33 = (n3 - this.var_a_a.var_e_g.getSpritesWidth()) / 2;
                        this.var_a_a.var_e_g.a(graphics, n33, n35 + this.p - this.var_a_a.var_e_g.getSpritesHeight());
                    }
                    if (this.var_short_b + this.u >= this.var_java_lang_String_arr_b.length) break;
                    this.var_a_a.var_e_g.a(1);
                    n33 = (n3 - this.var_a_a.var_e_g.getSpritesWidth()) / 2;
                    int n38 = this.var_int_g - 12;
                    this.var_a_a.var_e_g.a(graphics, n33, n38 - this.p);
                }
            }
        }
        graphics.translate(-n2, -n);
        graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
        if (this.var_byte_e == 2) {
            if (this.var_boolean_a) {
                this.var_a_a.var_e_e.a(0);
                this.var_a_a.var_e_e.a(graphics, 0, AppCanvas.height2 - this.var_a_a.var_e_e.getSpritesHeight());
            }
            if (this.var_boolean_g) {
                this.var_a_a.var_e_e.a(1);
                this.var_a_a.var_e_e.a(graphics, AppCanvas.width2 - this.var_a_a.var_e_e.getSpritesWidth(), AppCanvas.height2 - this.var_a_a.var_e_e.getSpritesHeight());
            }
        }
    }

    private void a(Graphics graphics, int n, int n2, int n3, int n4, int n5) {
        int n6;
        SpriteSheet e2 = this.var_a_a.var_e_b;
        graphics.setClip(n, n2, n3, n4);
        graphics.setColor(13549221);
        int n7 = n3;
        int n8 = n4;
        int n9 = n;
        int n10 = n2;
        int n11 = n3;
        if ((n5 & 4) == 0) {
            n11 -= e2.getSpritesWidth();
            n7 -= 6;
            n9 += 6;
        }
        if ((n5 & 8) == 0) {
            n11 -= e2.getSpritesWidth();
            n7 -= 6;
        }
        int n12 = n4;
        if ((n5 & 1) == 0) {
            n12 -= e2.getSpritesHeight();
            n8 -= 6;
            n10 += 6;
        }
        if ((n5 & 2) == 0) {
            n12 -= e2.getSpritesHeight();
            n8 -= 6;
        }
        graphics.fillRect(n9, n10, n7, n8);
        int n13 = n11 / e2.getSpritesWidth();
        int n14 = n12 / e2.getSpritesHeight();
        if (n11 % e2.getSpritesWidth() > 0) {
            ++n13;
        }
        if (n12 % e2.getSpritesHeight() > 0) {
            ++n14;
        }
        int n15 = n + e2.getSpritesWidth();
        int n16 = n2 + n4 - e2.getSpritesHeight();
        for (n6 = 0; n6 < n13; ++n6) {
            if ((n5 & 1) == 0) {
                e2.a(4);
                e2.a(graphics, n15, n2);
            }
            if ((n5 & 2) == 0) {
                e2.a(5);
                e2.a(graphics, n15, n16);
            }
            n15 += e2.getSpritesWidth();
        }
        n15 = n + n3 - e2.getSpritesWidth();
        n16 = n2 + e2.getSpritesHeight();
        for (n6 = 0; n6 < n14; ++n6) {
            if ((n5 & 4) == 0) {
                e2.a(6);
                e2.a(graphics, n, n16);
            }
            if ((n5 & 8) == 0) {
                e2.a(7);
                e2.a(graphics, n15, n16);
            }
            n16 += e2.getSpritesHeight();
        }
        if ((n5 & 1) == 0) {
            if ((n5 & 4) == 0) {
                e2.a(0);
                e2.a(graphics, n, n2);
            } else {
                e2.a(4);
                e2.a(graphics, n, n2);
            }
            if ((n5 & 8) == 0) {
                e2.a(1);
                e2.a(graphics, n + n3 - 24, n2);
            } else {
                e2.a(4);
                e2.a(graphics, n + n3 - 24, n2);
            }
        } else {
            if ((n5 & 4) == 0) {
                e2.a(6);
                e2.a(graphics, n, n2);
            }
            if ((n5 & 8) == 0) {
                e2.a(7);
                e2.a(graphics, n + n3 - 24, n2);
            }
        }
        n16 = n2 + n4 - 24;
        if ((n5 & 2) == 0) {
            if ((n5 & 4) == 0) {
                e2.a(2);
                e2.a(graphics, n, n16);
            } else {
                e2.a(5);
                e2.a(graphics, n, n16);
            }
            if ((n5 & 8) == 0) {
                e2.a(3);
                e2.a(graphics, n + n3 - 24, n16);
            } else {
                e2.a(5);
                e2.a(graphics, n + n3 - 24, n16);
            }
        } else {
            if ((n5 & 4) == 0) {
                e2.a(6);
                e2.a(graphics, n, n16);
            }
            if ((n5 & 8) == 0) {
                e2.a(7);
                e2.a(graphics, n + n3 - 24, n16);
            }
        }
    }
}

