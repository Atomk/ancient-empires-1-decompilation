/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public class f {
    public static final int[] var_int_arr_b;
    public static final String[] var_java_lang_String_arr_a;
    public a var_a_a;
    public c var_c_a;
    public boolean var_boolean_f = false;
    public boolean var_boolean_e = false;
    public byte var_byte_g;
    public byte var_byte_c;
    public byte var_byte_a;
    public byte var_byte_d;
    public byte var_byte_b;
    public byte var_byte_f;
    private static final byte[] var_byte_arr_a;
    private static final byte[][][][] var_byte_arr_arr_arr_arr_a;
    public byte[][] var_byte_arr_arr_a;
    private static final byte[][] var_byte_arr_arr_d;
    private static final byte[][] var_byte_arr_arr_e;
    private static final byte[][] var_byte_arr_arr_b;
    public int o = 0;
    public static final byte[] var_byte_arr_c;
    public static final byte[] var_byte_arr_b;
    public e var_e_c;
    public e var_e_a;
    public e var_e_b;
    public int m;
    public int var_int_a;
    public boolean var_boolean_b;
    public long var_long_a;
    public long var_long_c;
    public long var_long_b;
    public e[] var_e_arr_b;
    public Sprite[] var_h_arr_a;
    public Sprite var_h_a;
    public f var_f_a;
    public int var_int_f;
    public int var_int_b;
    public int var_int_d;
    public int j;
    public int n;
    public boolean var_boolean_d = false;
    public int h;
    public int l;
    public int[] var_int_arr_d;
    public int[] var_int_arr_c;
    public int[][] var_int_arr_arr_a;
    public boolean[] var_boolean_arr_a;
    public int var_int_c;
    public byte var_byte_e;
    public int k;
    public int var_int_e;
    public int[] var_int_arr_a;
    public byte[][] var_byte_arr_arr_c;
    public int var_int_g;
    public boolean var_boolean_c = false;
    public boolean var_boolean_a = false;
    public e[] var_e_arr_a;
    public int i;

    public f(i i2, c c2, boolean bl) throws Exception {
        int n;
        this.var_a_a = (a)i2;
        this.var_c_a = c2;
        this.var_byte_e = var_byte_arr_a[c2.var_byte_d];
        this.var_int_e = 200;
        this.var_int_g = var_int_arr_b[c2.var_byte_d];
        if (this.var_int_g != -1) {
            // empty if block
        }
        if (this.var_byte_e == 3) {
            this.var_int_e = 0;
        }
        this.var_byte_g = (byte)c2.h;
        this.h = this.var_byte_g;
        this.var_byte_b = this.var_byte_a = (byte)c2.int_a();
        this.var_boolean_b = bl;
        int n2 = 0;
        if (bl) {
            this.var_byte_f = 0;
            this.m = 0;
            this.b();
        } else {
            n2 = d.h;
            this.var_byte_f = 1;
            this.o = 6;
        }
        this.var_byte_arr_arr_a = var_byte_arr_arr_arr_arr_a[this.var_byte_e][this.var_byte_f];
        this.var_int_b = this.var_int_f = (int)i2.byte_a(c2.i, (int)c2.var_short_a);
        if (this.var_int_f == 2 || this.var_int_f == 3) {
            this.var_int_b = 1;
        }
        if (this.var_a_a.var_h_arr_arr_a[this.var_int_b] == null) {
            this.var_a_a.var_h_arr_arr_a[this.var_int_b] = new e((String)i.var_java_lang_String_arr_a[this.var_int_b]).var_h_arr_a;
        }
        this.var_h_arr_a = this.var_a_a.var_h_arr_arr_a[this.var_int_b];
        if (this.var_a_a.b[this.var_int_f] == null) {
            try {
                this.var_a_a.b[this.var_int_f] = this.var_int_f == 3 ? new Sprite("hill_bg.png") : new Sprite(i.var_java_lang_String_arr_a[this.var_int_f] + "_bg.png");
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.var_h_a = this.var_a_a.b[this.var_int_f];
        if (this.var_h_a != null) {
            this.var_int_d = this.var_h_a.height;
        }
        this.j = d.h / this.var_h_arr_a[0].width;
        if (d.h % this.var_h_arr_a[0].width != 0) {
            ++this.j;
        }
        this.n = (d.height2 - this.var_int_d) / this.var_h_arr_a[0].height;
        if ((d.height2 - this.var_int_d) % this.var_h_arr_a[0].height != 0) {
            ++this.n;
        }
        this.var_byte_arr_arr_c = new byte[this.j][this.n];
        for (n = 0; n < this.j; ++n) {
            for (int j = 0; j < this.n; ++j) {
                this.var_byte_arr_arr_c[n][j] = (byte)Math.abs(d.randomGen.nextInt() % 10);
                this.var_byte_arr_arr_c[n][j] = this.var_byte_arr_arr_c[n][j] >= 9 ? 2 : (this.var_byte_arr_arr_c[n][j] >= 8 ? 1 : 0);
            }
        }
        if (this.var_a_a.var_e_arr_arr_a[c2.var_byte_a][c2.var_byte_d] == null) {
            this.var_a_a.var_e_arr_arr_a[c2.var_byte_a][c2.var_byte_d] = new e(var_java_lang_String_arr_a[c2.var_byte_d], c2.var_byte_a);
        }
        this.var_e_c = this.var_a_a.var_e_arr_arr_a[c2.var_byte_a][c2.var_byte_d];
        if (this.var_byte_e == 2 || this.var_byte_e == 4) {
            if (i2.var_e_p == null) {
                i2.var_e_p = new e("slash");
            }
            this.var_e_b = new e(i2.var_e_p);
            this.var_e_b.a(var_byte_arr_arr_e[this.var_byte_f]);
        } else if (c2.var_byte_d == 9) {
            if (i2.var_e_i == null) {
                i2.var_e_i = new e("kingslash");
            }
            this.var_e_a = i2.var_e_i;
            this.var_e_b = new e("kingswing");
            this.var_e_b.a(var_byte_arr_arr_e[this.var_byte_f]);
        } else if (c2.var_byte_d == 5) {
            if (i2.var_e_o == null) {
                i2.var_e_o = new e("spiderspit");
            }
            this.var_e_a = i2.var_e_o;
        } else if (c2.var_byte_d == 7) {
            if (i2.var_e_c == null) {
                i2.var_e_c = new e("stone");
            }
            this.var_e_a = i2.var_e_c;
        } else if (c2.var_byte_d == 3) {
            this.var_e_a = i2.var_e_r;
        } else if (c2.var_byte_d == 8) {
            if (i2.var_e_n == null) {
                i2.var_e_n = new e("fireball");
            }
            this.var_e_a = i2.var_e_n;
        }
        this.var_int_arr_arr_a = new int[c2.var_int_arr_arr_a.length][2];
        for (n = 0; n < this.var_int_arr_arr_a.length; ++n) {
            this.var_int_arr_arr_a[n][0] = this.var_byte_f == 0 ? d.int_b(c2.var_int_arr_arr_a[n][0]) : (int)((short)(d.h - d.int_b(c2.var_int_arr_arr_a[n][0]) - this.var_e_c.short_a()));
            this.var_int_arr_arr_a[n][1] = d.int_a(c2.var_int_arr_arr_a[n][1]);
        }
        this.var_int_arr_c = new int[this.var_byte_a];
        this.var_int_arr_d = new int[this.var_byte_a];
        this.var_e_arr_b = new e[this.var_byte_a];
        this.var_int_arr_a = new int[this.var_byte_a];
        if (c2.var_byte_d == 4) {
            this.var_boolean_arr_a = new boolean[this.var_byte_a];
        }
        for (n = 0; n < this.var_byte_a; ++n) {
            this.var_int_arr_c[n] = 0;
            this.var_e_arr_b[n] = new e(this.var_e_c);
            this.var_e_arr_b[n].void_b(this.var_int_arr_arr_a[n][0] + n2, this.var_int_arr_arr_a[n][1]);
            this.a(n, this.var_byte_arr_arr_a[0]);
            if (c2.var_byte_d == 8) {
                this.var_int_arr_d[n] = -8 - Math.abs(d.randomGen.nextInt()) % 8;
            }
            if (c2.var_byte_d != 4) continue;
            this.var_int_arr_d[n] = d.randomGen.nextInt() % 5 - 8;
            this.var_int_arr_c[n] = d.randomGen.nextInt() % 5;
            this.var_boolean_arr_a[n] = this.var_int_arr_c[n] < 0;
        }
    }

    public int a(e e2, int n) {
        if (this.var_byte_f == 1) {
            return this.var_e_c.short_a() - n - e2.short_a();
        }
        return n;
    }

    public void a(int n, byte[] byArray) {
        this.var_e_arr_b[n].a(byArray);
        this.var_int_arr_a[n] = (int)this.var_a_a.var_long_n;
    }

    public void b() {
        this.var_boolean_e = true;
        this.o = 0;
        this.var_long_c = this.var_a_a.var_long_n;
    }

    public void f() {
        switch (this.o) {
            case 0: {
                if (this.var_int_c == 0 && this.var_a_a.var_long_n - this.var_long_c >= 200L) {
                    this.k = 600;
                    this.var_e_arr_a = new e[this.var_byte_b];
                    for (int j = 0; j < this.var_byte_b; ++j) {
                        if (this.var_byte_e == 5) {
                            this.k = 0;
                            continue;
                        }
                        if (this.var_c_a.var_byte_d != 3) continue;
                        this.a(j, this.var_byte_arr_arr_a[1]);
                        this.var_e_arr_a[j] = e.a(this.var_e_a, 0, 0, -1, 0, (byte)0);
                        this.var_e_arr_a[j].void_b(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].short_a() - this.var_e_arr_a[j].short_a()) / 2, this.var_e_arr_b[j].l - this.var_e_arr_a[j].short_b());
                        this.var_a_a.var_java_util_Vector_c.addElement(this.var_e_arr_a[j]);
                        this.var_e_arr_a[j].var_boolean_a = true;
                    }
                    this.var_int_c = 1;
                    this.var_long_c = this.var_a_a.var_long_n;
                }
                if (this.var_int_c != 1 || this.var_a_a.var_long_n - this.var_long_c < (long)this.k) break;
                this.o = 2;
                this.var_long_c = this.var_a_a.var_long_n;
                break;
            }
            case 2: {
                if (this.var_c_a.var_byte_d == 3) {
                    for (int j = 0; j < this.var_byte_b; ++j) {
                        this.var_e_arr_a[j].var_int_d = var_byte_arr_b[this.var_byte_f];
                    }
                } else {
                    if (this.var_c_a.var_byte_d == 7) {
                        this.c();
                    }
                    this.var_e_arr_a = new e[this.var_byte_b];
                    for (int j = 0; j < this.var_byte_b; ++j) {
                        if (this.var_byte_e == 5) {
                            this.a(j, this.var_byte_arr_arr_a[1]);
                            if (this.var_c_a.var_byte_d == 9) {
                                e e2 = e.a(this.var_e_b, 0, 0, 1, 200, (byte)0);
                                e2.void_b(this.var_e_arr_b[0].var_short_b + this.a(e2, 5), this.var_e_arr_b[0].l + 2);
                                this.var_a_a.var_java_util_Vector_c.addElement(e2);
                            }
                        } else {
                            this.a(j, this.var_byte_arr_arr_a[1]);
                            this.var_boolean_a = true;
                        }
                        if (this.var_c_a.var_byte_d == 1) {
                            this.var_e_arr_a[j] = e.a(null, var_byte_arr_b[this.var_byte_f] * 3, -4, -1, 0, (byte)3);
                            this.var_e_arr_a[j].void_b(this.var_e_arr_b[j].var_short_b + this.a(this.var_e_arr_a[j], this.var_e_arr_b[j].short_a()), this.var_e_arr_b[j].l);
                        } else if (this.var_c_a.var_byte_d == 7) {
                            this.var_e_arr_a[j] = e.a(this.var_e_a, var_byte_arr_b[this.var_byte_f] * 10, -8, -1, 0, (byte)0);
                            this.var_e_arr_a[j].void_b(this.var_e_arr_b[j].var_short_b + this.a(this.var_e_arr_a[j], 18), this.var_e_arr_b[j].l);
                        } else if (this.var_c_a.var_byte_d == 5) {
                            this.var_e_arr_a[j] = e.a(this.var_e_a, var_byte_arr_b[this.var_byte_f] * 2, 0, -1, 0, (byte)0);
                            this.var_e_arr_a[j].void_b(this.var_e_arr_b[j].var_short_b + this.a(this.var_e_arr_a[j], this.var_e_arr_b[j].short_a() - 10), this.var_e_arr_b[j].l + this.var_e_arr_b[j].short_b() / 3);
                        } else {
                            this.var_e_arr_a[j] = e.a(this.var_e_a, var_byte_arr_b[this.var_byte_f] * 2, 0, -1, 0, (byte)0);
                            this.var_e_arr_a[j].void_b(this.var_e_arr_b[j].var_short_b + this.a(this.var_e_arr_a[j], this.var_e_arr_b[j].short_a()), this.var_e_arr_b[j].l);
                        }
                        if (this.var_c_a.var_byte_d != 7) {
                            if (this.var_c_a.var_byte_d == 9) {
                                this.var_e_arr_a[j].a(var_byte_arr_arr_b[this.var_byte_f]);
                            } else if (this.var_c_a.var_byte_d == 8) {
                                this.var_e_arr_a[j].a(var_byte_arr_arr_d[this.var_byte_f]);
                            } else {
                                this.var_e_arr_a[j].a(var_byte_arr_arr_e[this.var_byte_f]);
                            }
                        }
                        this.var_a_a.var_java_util_Vector_c.addElement(this.var_e_arr_a[j]);
                        this.var_e_arr_a[j].var_boolean_a = true;
                    }
                    if (this.var_int_g != -1) {
                        d.a(this.var_int_g, 1);
                    }
                }
                this.o = 5;
                break;
            }
            case 5: {
                boolean bl = true;
                for (int j = 0; j < this.var_e_arr_a.length; ++j) {
                    if (this.var_c_a.var_byte_d == 8 && d.randomGen.nextInt() % 2 == 0) {
                        e e3 = e.a(this.var_a_a.var_e_a, 0, -1, 1, 200, (byte)0);
                        e3.void_b(this.var_e_arr_a[j].var_short_b + this.a(this.var_e_arr_a[j], 0), this.var_e_arr_a[j].l + 4);
                        this.var_a_a.var_java_util_Vector_c.addElement(e3);
                        e3.var_boolean_a = true;
                    }
                    if (this.var_byte_f == 0) {
                        if (this.var_e_arr_a[j].var_short_b > d.h) {
                            this.var_a_a.var_java_util_Vector_c.removeElement(this.var_e_arr_a[j]);
                            continue;
                        }
                        bl = false;
                        continue;
                    }
                    if (this.var_byte_f != 1) continue;
                    if (this.var_e_arr_a[j].var_short_b + this.var_e_arr_a[j].short_a() < d.h) {
                        this.var_a_a.var_java_util_Vector_c.removeElement(this.var_e_arr_a[j]);
                        continue;
                    }
                    bl = false;
                }
                if (!bl) break;
                this.o = 3;
                this.var_long_c = this.var_a_a.var_long_n;
                break;
            }
            case 3: {
                if (this.var_a_a.var_long_n - this.var_long_c < 400L) break;
                if (this.var_c_a.var_byte_d != 7 && this.var_c_a.var_byte_d != 1) {
                    for (int j = 0; j < this.var_byte_b; ++j) {
                        this.a(j, this.var_byte_arr_arr_a[0]);
                        this.var_boolean_a = false;
                        this.var_int_arr_c[j] = -2;
                    }
                }
                this.var_f_a.d();
                if (this.var_c_a.var_byte_d != 1) {
                    this.var_a_a.c(200);
                }
                this.o = 7;
                this.var_long_c = this.var_a_a.var_long_n;
                break;
            }
            case 7: {
                if (this.var_a_a.var_long_n - this.var_long_c < 500L) break;
                this.var_boolean_f = true;
            }
        }
    }

    public void c() {
        d.d(200);
        this.var_boolean_d = true;
        this.var_long_b = this.var_a_a.var_long_n;
    }

    public void a() {
        switch (this.o) {
            case 0: {
                if (this.var_int_c == 0) {
                    this.var_int_e = 100;
                    if (this.var_a_a.var_long_n - this.var_long_c < 200L) break;
                    this.var_int_c = 1;
                    this.var_long_c = this.var_a_a.var_long_n;
                    d.a(this.var_int_g, 1);
                    break;
                }
                if (this.var_int_c == 1) {
                    if (this.var_a_a.var_long_n - this.var_long_c < 200L) break;
                    this.var_int_e -= 20;
                    if (this.var_int_e <= 0) {
                        this.var_a_a.var_boolean_b = true;
                        this.o = 5;
                    }
                    this.var_long_c = this.var_a_a.var_long_n;
                    break;
                }
                if (this.i >= 200) {
                    this.var_a_a.var_boolean_b = true;
                    this.o = 5;
                    break;
                }
                this.i += 40;
                break;
            }
            case 5: {
                if (this.var_a_a.var_long_n - this.var_long_c < 500L) break;
                this.var_a_a.var_boolean_b = false;
                this.i = 0;
                this.var_f_a.d();
                this.o = 3;
                break;
            }
            case 3: {
                this.var_boolean_f = true;
                this.o = 6;
            }
        }
        boolean bl = false;
        if (this.var_a_a.var_long_n - this.var_long_a >= 300L) {
            bl = true;
            this.var_long_a = this.var_a_a.var_long_n;
        }
        for (int j = 0; j < this.var_e_arr_b.length; ++j) {
            if (bl) {
                e e2 = e.a(null, 0, 0, 1, 500, (byte)4);
                e2.void_b(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].short_a() >> 1), this.var_e_arr_b[j].l + (this.var_e_arr_b[j].short_b() >> 1) + this.var_int_a + this.var_int_arr_d[j]);
                this.var_a_a.var_java_util_Vector_c.addElement(e2);
            }
            if (this.var_boolean_arr_a[j] && this.var_int_arr_c[j] <= -4 || !this.var_boolean_arr_a[j] && this.var_int_arr_c[j] >= 4) {
                boolean bl2 = this.var_boolean_arr_a[j] = !this.var_boolean_arr_a[j];
            }
            if (this.var_boolean_arr_a[j]) {
                int n = j;
                this.var_int_arr_c[n] = this.var_int_arr_c[n] - 1;
            } else {
                int n = j;
                this.var_int_arr_c[n] = this.var_int_arr_c[n] + 1;
            }
            int n = j;
            this.var_int_arr_d[n] = this.var_int_arr_d[n] + this.var_int_arr_c[j] / 2;
        }
    }

    public void g() {
        int n;
        if (this.var_boolean_d) {
            if (this.var_a_a.var_long_n - this.var_long_b >= 300L) {
                this.var_boolean_d = false;
                this.m = 0;
                this.var_int_a = 0;
            } else {
                this.m = this.m > 0 ? -2 : 2;
                this.var_int_a = d.randomGen.nextInt() % 1;
            }
        }
        if (var_byte_arr_a[this.var_c_a.var_byte_d] == 1 || var_byte_arr_a[this.var_c_a.var_byte_d] == 5 || var_byte_arr_a[this.var_c_a.var_byte_d] == 0) {
            this.f();
        } else if (var_byte_arr_a[this.var_c_a.var_byte_d] == 6) {
            this.a();
        } else {
            n = 0;
            switch (this.o) {
                case 0: {
                    int n2;
                    if (this.var_a_a.var_long_n - this.var_long_c < 200L) break;
                    if (this.var_byte_e == 4) {
                        for (n2 = 0; n2 < this.var_byte_b; ++n2) {
                            this.a(n2, this.var_byte_arr_arr_a[1]);
                            this.var_int_arr_c[n2] = 0;
                        }
                    } else {
                        for (n2 = 0; n2 < this.var_int_arr_c.length; ++n2) {
                            this.var_int_arr_c[n2] = -6;
                        }
                    }
                    this.o = 2;
                    break;
                }
                case 2: {
                    int n2;
                    if (this.var_int_c < this.var_byte_b) {
                        ++this.var_int_c;
                    }
                    boolean bl = true;
                    for (n2 = 0; n2 < this.var_int_c; ++n2) {
                        e e2;
                        if (this.var_byte_e == 4) {
                            if (this.var_int_arr_c[n2] == -1) continue;
                            this.var_e_arr_b[n2].void_b(this.var_e_arr_b[n2].var_short_b + var_byte_arr_b[this.var_byte_f], this.var_e_arr_b[n2].l);
                            int n3 = n2;
                            this.var_int_arr_c[n3] = this.var_int_arr_c[n3] + 3;
                            if (this.var_int_arr_c[n2] >= 24) {
                                this.a(n2, this.var_byte_arr_arr_a[2]);
                                e2 = e.a(this.var_e_b, 0, 0, 1, 150, (byte)0);
                                e2.void_b(this.var_e_arr_b[n2].var_short_b + this.a(e2, 24), this.var_e_arr_b[n2].l + 3);
                                this.var_a_a.var_java_util_Vector_c.addElement(e2);
                                this.var_int_arr_c[n2] = -1;
                                continue;
                            }
                            bl = false;
                            continue;
                        }
                        if (this.var_int_arr_c[n2] <= 6) {
                            if (this.var_int_arr_c[n2] == -6) {
                                this.a(n2, this.var_byte_arr_arr_a[1]);
                            }
                            this.var_e_arr_b[n2].void_b(this.var_e_arr_b[n2].var_short_b + var_byte_arr_b[this.var_byte_f], this.var_e_arr_b[n2].l + this.var_int_arr_c[n2]);
                            int n4 = n2;
                            this.var_int_arr_c[n4] = this.var_int_arr_c[n4] + 1;
                            bl = false;
                            continue;
                        }
                        if (this.var_int_arr_c[n2] != 7) continue;
                        int n5 = n2;
                        this.var_int_arr_c[n5] = this.var_int_arr_c[n5] + 1;
                        if (this.var_byte_e != 2) continue;
                        this.a(n2, this.var_byte_arr_arr_a[0]);
                        e2 = e.a(this.var_e_b, 0, 0, 1, 150, (byte)0);
                        e2.void_b(this.var_e_arr_b[n2].var_short_b + this.a(e2, 14), this.var_e_arr_b[n2].l + 4);
                        this.var_a_a.var_java_util_Vector_c.addElement(e2);
                    }
                    if (!bl) break;
                    for (n2 = 0; n2 < this.var_int_arr_c.length; ++n2) {
                        this.var_int_arr_c[n2] = this.var_byte_e == 4 ? 0 : -6;
                    }
                    this.var_int_c = 0;
                    this.o = 5;
                    this.var_f_a.d();
                    this.var_a_a.c(200);
                    this.var_long_c = this.var_a_a.var_long_n;
                    break;
                }
                case 5: {
                    int n2;
                    if (this.var_byte_e == 4) {
                        if (this.var_a_a.var_long_n - this.var_long_c < 400L) break;
                        for (n2 = 0; n2 < this.var_byte_b; ++n2) {
                            this.var_e_arr_b[n2].void_b(this.var_e_arr_b[n2].var_short_b - var_byte_arr_c[this.var_byte_f], this.var_e_arr_b[n2].l);
                            this.a(n2, this.var_byte_arr_arr_a[3]);
                        }
                        this.o = 3;
                        break;
                    }
                    if (this.var_a_a.var_long_n - this.var_long_c < 50L) break;
                    this.o = 3;
                    break;
                }
                case 3: {
                    int n2;
                    if (this.var_int_c < this.var_byte_b) {
                        ++this.var_int_c;
                    }
                    boolean bl = true;
                    for (n2 = 0; n2 < this.var_int_c; ++n2) {
                        if (this.var_byte_e == 4) {
                            if (this.var_int_arr_c[n2] == -1) continue;
                            this.var_e_arr_b[n2].void_b(this.var_e_arr_b[n2].var_short_b - var_byte_arr_b[this.var_byte_f], this.var_e_arr_b[n2].l);
                            int n6 = n2;
                            this.var_int_arr_c[n6] = this.var_int_arr_c[n6] + 3;
                            if (this.var_int_arr_c[n2] >= 24) {
                                this.var_e_arr_b[n2].void_b(this.var_e_arr_b[n2].var_short_b + var_byte_arr_c[this.var_byte_f], this.var_e_arr_b[n2].l);
                                this.a(n2, this.var_byte_arr_arr_a[0]);
                                this.var_int_arr_c[n2] = -1;
                                continue;
                            }
                            bl = false;
                            continue;
                        }
                        if (this.var_int_arr_c[n2] <= 6) {
                            if (this.var_c_a.var_byte_d != 6 && this.var_int_arr_c[n2] == -6) {
                                this.a(n2, this.var_byte_arr_arr_a[2]);
                            }
                            this.var_e_arr_b[n2].void_b(this.var_e_arr_b[n2].var_short_b - var_byte_arr_b[this.var_byte_f], this.var_e_arr_b[n2].l + this.var_int_arr_c[n2]);
                            int n7 = n2;
                            this.var_int_arr_c[n7] = this.var_int_arr_c[n7] + 1;
                            bl = false;
                            continue;
                        }
                        if (this.var_int_arr_c[n2] != 7) continue;
                        int n8 = n2;
                        this.var_int_arr_c[n8] = this.var_int_arr_c[n8] + 1;
                        this.a(n2, this.var_byte_arr_arr_a[0]);
                    }
                    if (!bl) break;
                    this.var_boolean_f = true;
                    this.o = 6;
                    this.var_long_c = this.var_a_a.var_long_n;
                }
            }
        }
        if (this.var_c_a.var_byte_d == 8) {
            for (n = 0; n < this.var_e_arr_b.length; ++n) {
                if (this.var_boolean_a) continue;
                if (this.var_int_arr_d[n] >= -8) {
                    this.var_int_arr_c[n] = -6;
                    this.var_e_arr_b[n].c();
                    this.var_long_a = this.var_a_a.var_long_n;
                } else if (this.var_e_arr_b[n].var_short_d == 1 && this.var_a_a.var_long_n - this.var_long_a >= 200L) {
                    this.var_e_arr_b[n].c();
                }
                int n9 = n;
                this.var_int_arr_c[n9] = this.var_int_arr_c[n9] + 1;
                int n10 = n;
                this.var_int_arr_d[n10] = this.var_int_arr_d[n10] + (this.var_int_arr_c[n] >> 2);
            }
        } else {
            for (n = 0; n < this.var_e_arr_b.length; ++n) {
                if (this.var_a_a.var_long_n - (long)this.var_int_arr_a[n] < (long)this.var_int_e) continue;
                this.var_e_arr_b[n].c();
                this.var_int_arr_a[n] = (int)this.var_a_a.var_long_n;
            }
        }
    }

    public void d() {
        this.c();
        this.l = this.var_byte_a - this.var_byte_d;
        this.var_byte_b = this.var_byte_d;
        this.h = this.var_byte_c;
        for (int j = 0; j < this.l; ++j) {
            e e2 = e.a(this.var_a_a.var_e_q, 0, -2, 1, 150, (byte)0);
            e e3 = e.a(this.var_a_a.var_e_d, 0, 0, 1, 100, (byte)0);
            e2.void_b(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].short_a() - e2.short_a()) / 2, this.var_e_arr_b[j].l + this.var_e_arr_b[j].short_b() - e2.short_b() + 3);
            e3.void_b(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].short_a() - e3.short_a()) / 2, this.var_e_arr_b[j].l + this.var_e_arr_b[j].short_b() - e3.short_b() + 3);
            this.var_a_a.var_java_util_Vector_c.addElement(e2);
            this.var_a_a.var_java_util_Vector_c.addElement(e3);
            e2 = e.a(null, 0, 0, 1, 800, (byte)2);
            e2.void_b(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].short_a() >> 1), this.var_e_arr_b[j].l + (this.var_e_arr_b[j].short_b() >> 1));
            this.var_a_a.var_java_util_Vector_c.addElement(e2);
        }
        e[] eArray = new e[this.var_byte_b];
        System.arraycopy(this.var_e_arr_b, this.l, eArray, 0, this.var_byte_b);
        this.var_e_arr_b = eArray;
        d.a(1, 1);
    }

    public void a(Graphics graphics, int offsetX, int offsetY) {
        int n3;
        int n4;
        int n5;
        graphics.translate(offsetX, offsetY);
        int n6 = 0;
        int n7 = this.j;
        for (n5 = 0; n5 < n7; ++n5) {
            n4 = this.var_int_d + offsetY;
            int n8 = this.n;
            for (n3 = 0; n3 < n8; ++n3) {
                this.var_h_arr_a[this.var_byte_arr_arr_c[n5][n3]].draw(graphics, n6, n4);
                n4 += 24;
            }
            n6 += 24;
        }
        if (this.var_h_a != null) {
            n5 = this.var_h_a.width;
            n6 = 0;
            n3 = d.h / n5;
            for (n7 = 0; n7 < n3; ++n7) {
                this.var_h_a.draw(graphics, n6, 0);
                n6 += n5;
            }
        }
        String string = "" + this.h;
        n6 = (d.h - d.a((byte)1, string)) / 2;
        d.a(graphics, string, n6, 2, 1);
        string = null;
        n6 = (d.h - this.var_a_a.var_h_h.width) / 2;
        n4 = d.a((byte)1) + 4;
        this.var_a_a.var_h_h.draw(graphics, n6, n4);
        n7 = i.var_byte_arr_g[this.var_int_f];
        n3 = this.var_a_a.a((byte)this.var_int_f, this.var_c_a) - n7;
        StringBuffer stringBuffer = new StringBuffer().append(n7);
        if (n3 > 0) {
            stringBuffer.append("+" + n3);
        }
        d.a(graphics, stringBuffer.toString(), n6 + 28, n4 + 5, 0);
        graphics.translate(-offsetX, -offsetY);
    }

    public void a(Graphics graphics) {
        if (this.var_a_a.var_boolean_b) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, d.width2, d.height2);
        }
        for (int j = 0; j < this.var_byte_b; ++j) {
            e e2 = this.var_e_arr_b[j];
            if (this.var_c_a.var_byte_d == 4) {
                e2.a(graphics, this.m, this.var_int_a + this.var_int_arr_d[j]);
                continue;
            }
            e2.a(graphics, this.m, this.var_int_a + this.var_int_arr_d[j]);
        }
    }

    public void b(Graphics graphics) {
        graphics.setColor(0x404040);
        for (int j = 0; j < this.var_byte_b; ++j) {
            e e2 = this.var_e_arr_b[j];
            if (this.var_byte_e == 1 || this.var_byte_e == 2 || this.var_byte_e == 3) {
                graphics.fillArc((int)e2.var_short_b, this.var_int_arr_arr_a[j + this.l][1] + e2.short_b() * 4 / 5, (int)e2.short_a(), e2.short_b() / 4, 0, 360);
                continue;
            }
            if (this.var_byte_e != 6) continue;
            graphics.fillArc((int)e2.var_short_b, this.var_int_arr_arr_a[j + this.l][1] + e2.short_b() * 4 / 5, (int)e2.short_a(), e2.short_b() / 4, 0, 360);
        }
    }

    public void e() {
        if (this.var_int_g != -1) {
            // empty if block
        }
    }

    static {
        var_int_arr_b = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        var_java_lang_String_arr_a = new String[]{"soldier", "archer", "lizard", "wizard", "wisp", "spider", "golem", "catapult", "wyvern", "king", "skeleton"};
        var_byte_arr_a = new byte[]{2, 0, 2, 0, 6, 5, 3, 0, 1, 5, 4};
        var_byte_arr_arr_arr_arr_a = new byte[][][][]{new byte[][][]{new byte[][]{{2}, {3}}, new byte[][]{{0}, {1}}}, new byte[][][]{new byte[][]{{2, 3}, {2}}, new byte[][]{{0, 1}, {0}}}, new byte[][][]{new byte[][]{{2}, {3}, {2}}, new byte[][]{{0}, {1}, {0}}}, new byte[][][]{new byte[][]{{2}, {1, 3, 5, 4}}, new byte[][]{{0}, {1, 3, 5, 4}}}, new byte[][][]{new byte[][]{{4}, {3, 4}, {5}, {0, 1}}, new byte[][]{{1}, {0, 1}, {2}, {3, 4}}}, new byte[][][]{new byte[][]{{2}, {3}}, new byte[][]{{0}, {1}}}, new byte[][][]{new byte[][]{{0, 1, 2, 3}}, new byte[][]{{0, 1, 2, 3}}}};
        var_byte_arr_arr_d = new byte[][]{{2, 3}, {0, 1}};
        var_byte_arr_arr_e = new byte[][]{{1}, {0}};
        var_byte_arr_arr_b = new byte[][]{{3, 4, 5, 4}, {0, 1, 2, 1}};
        var_byte_arr_c = new byte[]{18, -18};
        var_byte_arr_b = new byte[]{3, -3};
    }
}

