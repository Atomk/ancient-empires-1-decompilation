/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class a
extends i {
    public static final boolean[] var_boolean_arr_a;
    public h var_h_g;
    public h var_h_d;
    public h var_h_a;
    public e[][] var_e_arr_arr_c;
    public int I = -15;
    public int B;
    public int var_int_d = 0;
    public boolean var_boolean_g = false;
    public Vector var_java_util_Vector_d;
    public e[][] var_e_arr_arr_a;
    public h[][] var_h_arr_arr_a;
    public h[] b;
    public e[] var_e_arr_a = new e[0];
    public e var_e_a;
    public byte[][] var_byte_arr_arr_c;

    public a(byte by) throws Exception {
        super(by);
        this.var_h_g = new h("splashbg.png");
        this.var_h_d = new h("splashfg.png");
        this.var_h_a = new h("macrospace.png");
        this.B = d.var_int_d - this.var_h_d.c + 30;
    }

    public void m() throws Exception {
        this.var_h_g = null;
        this.var_h_d = null;
        this.var_h_a = null;
        super.m();
        this.var_e_arr_arr_a = new e[2][11];
        this.b = new h[i.var_java_lang_String_arr_a.length];
        this.var_h_arr_arr_a = new h[i.var_java_lang_String_arr_a.length][];
        this.var_e_arr_arr_c = new e[2][11];
        byte[] byArray = d.byte_arr_a("unit_icons_s.png");
        for (int j = 0; j < 2; ++j) {
            byte[] imageBytes = new byte[byArray.length];
            System.arraycopy(byArray, 0, imageBytes, 0, byArray.length);
            h h2 = h.h_a(imageBytes, j);
            for (int k = 0; k < 11; ++k) {
                this.var_e_arr_arr_c[j][k] = new e(new h(h2, k, 0, 10, 10), 10, 10);
            }
        }
        this.var_e_a = new e("b_smoke");
        this.var_h_h = new h("defencepanel.png");
        this.var_e_q = new e("soul");
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
                    d.a(0, 1);
                    this.var_h_c = null;
                    this.var_int_m = 0;
                    ++this.var_int_p;
                    break;
                }
                ++this.var_int_m;
                break;
            }
            case 2: {
                if (this.I < 0) {
                    ++this.I;
                    this.B -= 2;
                    ++this.var_int_d;
                    break;
                }
                this.var_int_m = 0;
                ++this.var_int_p;
                break;
            }
            case 3: {
                if (this.var_int_m < 15) {
                    ++this.var_int_m;
                    break;
                }
                boolean bl = this.var_boolean_p = !this.var_boolean_p;
                if (i.var_d_a.var_int_c == 0 || !this.var_boolean_c || this.var_int_g != 0) break;
                g g2 = new g(this, 0, 0);
                g2.a(this.var_java_lang_String_arr_e);
                g2.a((byte)1, d.h, 67, null, 16);
                i.var_d_a.var_int_c = 0;
            }
        }
    }

    public void b(Graphics graphics) {
        if (this.var_int_p == 0) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, i.var_d_a.e, i.var_d_a.g);
            i.a(graphics, 0, this.var_int_m, 15, 0, this.var_h_c, (i.var_d_a.e - this.var_h_c.d) / 2, (i.var_d_a.g - this.var_h_c.c) / 2, 0, 0);
        } else if (this.var_int_p == 1) {
            i.a(graphics, 0xFFFFFF, this.var_int_m, 15, 0, null, (i.var_d_a.e - this.var_h_c.d) / 2, (i.var_d_a.g - this.var_h_c.c) / 2, this.var_h_c.d, this.var_h_c.c);
        } else {
            graphics.setColor(108, 93, 72);
            graphics.fillRect(0, 0, i.var_d_a.e, i.var_d_a.g);
            this.var_h_g.draw(graphics, 0, this.I);
            this.var_h_d.draw(graphics, 0, this.B);
            if (this.var_int_p == 3) {
                if (this.var_int_m >= 15) {
                    this.var_h_e.draw(graphics, (i.var_d_a.e - this.var_h_e.d) / 2, 8);
                    graphics.setColor(0xFFFFFF);
                    if (this.var_boolean_p && this.var_int_g == 0) {
                        graphics.setFont(d.var_javax_microedition_lcdui_Font_a);
                        graphics.drawString(d.java_lang_String_a(25), d.h, d.var_int_d - d.var_javax_microedition_lcdui_Font_a.getHeight() - 10, 17);
                    }
                    this.var_h_a.draw(graphics, (i.var_d_a.e - this.var_h_a.d) / 2, d.var_int_d - this.var_h_a.c - 2);
                } else {
                    i.a(graphics, 0xFFFFFF, this.var_int_m, 15, 0, this.var_h_e, (i.var_d_a.e - this.var_h_e.d) / 2, 8, 0, 0);
                    graphics.setClip(0, 0, d.var_int_a, d.var_int_d);
                }
            }
        }
    }

    public void void_a(int n) throws Exception {
        int n2;
        super.void_a(n);
        this.var_byte_arr_arr_c = new byte[this.var_short_e][this.var_short_b];
        for (n2 = 0; n2 < this.var_short_e; ++n2) {
            for (int j = 0; j < this.var_short_b; ++j) {
                if (this.boolean_a(n2, j)) {
                    this.var_byte_arr_arr_c[n2][j] = 3;
                    if (n2 > 0 && !this.boolean_a(n2 - 1, j)) {
                        byte[] byArray = this.var_byte_arr_arr_c[n2];
                        int n3 = j;
                        byArray[n3] = (byte)(byArray[n3] + 1);
                    }
                    if (n2 < this.var_short_e - 1 && !this.boolean_a(n2 + 1, j)) {
                        byte[] byArray = this.var_byte_arr_arr_c[n2];
                        int n4 = j;
                        byArray[n4] = (byte)(byArray[n4] + 2);
                    }
                    if (j < this.var_short_b - 1 && !this.boolean_a(n2, j + 1)) {
                        byte[] byArray = this.var_byte_arr_arr_c[n2];
                        int n5 = j;
                        byArray[n5] = (byte)(byArray[n5] + 4);
                    }
                    if (j <= 0 || this.boolean_a(n2, j - 1)) continue;
                    byte[] byArray = this.var_byte_arr_arr_c[n2];
                    int n6 = j;
                    byArray[n6] = (byte)(byArray[n6] + 8);
                    continue;
                }
                this.var_byte_arr_arr_c[n2][j] = -1;
            }
        }
        this.var_e_arr_a = new e[this.var_byte_arr_arr_e.length];
        for (n2 = 0; n2 < this.var_byte_arr_arr_e.length; ++n2) {
            if (this.byte_a((int)this.var_byte_arr_arr_e[n2][0], (int)this.var_byte_arr_arr_e[n2][1]) != 7) continue;
            this.var_e_arr_a[n2] = e.a(this.var_e_a, 0, -1, 1, 250, (byte)0);
            this.var_e_arr_a[n2].var_boolean_d = false;
        }
    }

    public boolean boolean_a(int n, int n2) {
        return var_boolean_arr_a[this.byte_a(n, n2)];
    }

    public void e() throws Exception {
        if (this.var_byte_i == 0) {
            for (int j = 0; j < this.var_e_arr_a.length; ++j) {
                if (this.var_e_arr_a[j] == null || this.int_a((int)this.var_byte_arr_arr_e[j][0], (int)this.var_byte_arr_arr_e[j][1]) == -1 || this.var_e_arr_a[j].var_boolean_d || d.var_java_util_Random_a.nextInt() % 8 != 0) continue;
                this.var_e_arr_a[j].var_boolean_d = true;
                this.var_e_arr_a[j].var_int_c = 1;
                this.var_e_arr_a[j].void_b((this.var_byte_arr_arr_e[j][0] + 1) * 24 - this.var_e_a.short_a(), this.var_byte_arr_arr_e[j][1] * 24 - 2);
                this.var_java_util_Vector_f.addElement(this.var_e_arr_a[j]);
            }
        }
        super.e();
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
        short s3 = (short)(s + d.var_int_a / 24);
        short s4 = (short)(s2 + d.var_int_d / 24);
        if (d.var_int_a % 24 != 0) {
            s3 = (short)(s3 + 1);
        }
        if (d.var_int_d % 24 != 0) {
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
                byte by = this.var_byte_arr_arr_c[s6][s5];
                if (by > 0) {
                    this.var_h_arr_c[by].draw(graphics, n3, n2);
                }
                if (this.var_byte_arr_j[by = this.var_byte_arr_arr_a[s6][s5]] != 1) {
                    this.var_h_arr_c[by].draw(graphics, n3, n2);
                    if (this.var_byte_arr_j[by] == 8) {
                        this.var_h_arr_c[by + 1].draw(graphics, n3, n2 - 24);
                    }
                }
                if (this.var_boolean_h && this.var_byte_arr_arr_b[s6][s5] > 0) {
                    if (s6 > 0 && this.var_byte_arr_arr_b[s6 - 1][s5] <= 0) {
                        graphics.fillRect(n3, n2, 4, 24);
                    }
                    if (s6 < this.var_short_e - 1 && this.var_byte_arr_arr_b[s6 + 1][s5] <= 0) {
                        graphics.fillRect(n3 + 24 - 4, n2, 4, 24);
                    }
                    if (s5 > 0 && this.var_byte_arr_arr_b[s6][s5 - 1] <= 0) {
                        graphics.fillRect(n3, n2, 24, 4);
                    }
                    if (s5 < this.var_short_b - 1 && this.var_byte_arr_arr_b[s6][s5 + 1] <= 0) {
                        graphics.fillRect(n3, n2 + 24 - 4, 24, 4);
                    }
                }
                n3 += 24;
            }
            n2 += 24;
        }
    }

    public void a(c c2, c c3) throws Exception {
        this.var_byte_d = (byte)2;
        this.var_java_util_Vector_d = this.var_java_util_Vector_c;
        this.var_java_util_Vector_c = new Vector();
        this.var_boolean_e = true;
        this.var_int_m = 0;
        this.var_boolean_k = false;
        this.var_c_i = c2;
        this.var_c_b = c3;
        this.var_f_b = new f(this, c2, true);
        this.var_f_b.var_f_a = this.var_f_a = new f(this, c3, false);
        this.var_f_a.var_f_a = this.var_f_b;
        c2.a(c3);
        if (c3.a(c2, (int)c2.i, (int)c2.var_short_a)) {
            c3.a(c2);
            this.var_boolean_x = true;
        } else {
            this.var_boolean_x = false;
        }
        this.var_f_b.var_byte_c = (byte)c2.h;
        this.var_f_b.var_byte_d = (byte)c2.int_a();
        this.var_f_a.var_byte_c = (byte)c3.h;
        this.var_f_a.var_byte_d = (byte)c3.int_a();
    }

    public void b() throws Exception {
        if (this.var_boolean_e) {
            ++this.var_int_m;
            i.var_d_a.repaint();
            i.var_d_a.serviceRepaints();
            if (this.var_int_m >= 15) {
                this.var_boolean_e = false;
            }
            return;
        }
        if (this.var_boolean_q && this.var_long_n - this.var_long_b >= this.var_long_m) {
            this.var_boolean_q = false;
        }
        for (int j = this.var_java_util_Vector_c.size() - 1; j >= 0; --j) {
            e e2 = (e)this.var_java_util_Vector_c.elementAt(j);
            e2.void_a();
            if (e2.var_boolean_d) continue;
            this.var_java_util_Vector_c.removeElement(e2);
        }
        this.var_f_b.g();
        this.var_f_a.g();
        if (this.var_boolean_k) {
            if (this.var_long_n - this.var_long_f >= 500L) {
                this.var_f_a.e();
                this.var_f_b.e();
                this.var_f_a = null;
                this.var_f_b = null;
                this.var_java_util_Vector_c = this.var_java_util_Vector_d;
                this.var_java_util_Vector_d = null;
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
        i.var_d_a.repaint();
        i.var_d_a.serviceRepaints();
    }

    static {
        var_boolean_arr_a = new boolean[]{false, true, true, true, true, false, false, true, true};
    }
}

