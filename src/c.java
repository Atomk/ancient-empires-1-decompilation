/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class c
extends e {
    public static Class_I var_i_a;
    public String var_java_lang_String_a;
    public short var_short_d;
    public short var_short_b;
    public short l;
    public int[][] var_int_arr_arr_a;
    public short j;
    public short var_short_c;
    public Vector var_java_util_Vector_a = null;
    public short var_short_g;
    public long var_long_a;
    public byte var_byte_d;
    public byte var_byte_a;
    public short i;
    public short var_short_a;
    public short h;
    public byte var_byte_e = 0;
    public byte var_byte_b;
    public short k;
    public short var_short_f;
    public short var_short_e;
    public boolean var_boolean_e = false;
    public boolean var_boolean_b = true;
    public int var_int_g;
    public long var_long_c;
    public int var_int_b;
    public static final byte[] unitsDataMOV;
    public static final byte[] unitsDataATK;
    public static final byte[] unitsDataDEF;
    private static final byte[] unitsDataRangeMax;
    private static final byte[] unitsDataRangeMin;
    public static final int[][][] var_int_arr_arr_arr_a;
    public static final short[] unitsDataPrice;
    public static final short[] var_short_arr_a;

    private c(byte by, byte by2, int n, int n2) {
        super(var_i_a.a(by2, by));
        this.i = (short)n;
        this.var_short_a = (short)n2;
        this.var_short_b = (short)(n * 24);
        ((e)this).l = (short)(n2 * 24);
        this.void_b(n * 24, n2 * 24);
        c.var_i_a.var_java_util_Vector_a.addElement(this);
    }

    public void b(int n) {
        this.var_boolean_e = true;
        this.var_long_c = c.var_i_a.var_long_n;
        this.var_int_g = n;
    }

    public static c a(byte by, byte by2, int n, int n2) {
        c c2 = new c(by, by2, n, n2);
        c2.var_byte_d = by;
        c2.var_byte_a = by2;
        c2.h = (short)10;
        c2.l = var_short_arr_a[by];
        c2.var_int_arr_arr_a = var_int_arr_arr_arr_a[by];
        return c2;
    }

    public int a(c c2) {
        int n;
        int n2 = unitsDataATK[this.var_byte_d] + this.var_short_f;
        if (this.a((short)64) && c2.a((short)1)) {
            n2 += 2;
        }
        if (this.var_byte_d == 4 && c2.var_byte_d == 10) {
            n2 += 3;
        }
        if ((n = AppCanvas.randomGen.nextInt() % 20 + this.var_short_d) >= 19) {
            n2 += 2;
        } else if (n >= 16) {
            ++n2;
        } else if (n <= -19) {
            n2 -= 2;
        } else if (n <= -16) {
            --n2;
        }
        int n3 = unitsDataDEF[c2.var_byte_d] + c2.var_short_e;
        n = AppCanvas.randomGen.nextInt() % 20 + c2.var_short_d;
        if (n >= 19) {
            n3 += 2;
        } else if (n >= 16) {
            ++n3;
        } else if (n <= -19) {
            n3 -= 2;
        } else if (n <= -16) {
            --n3;
        }
        int n4 = var_i_a.a(var_i_a.byte_a(c2.i, (int)c2.var_short_a), c2);
        int n5 = (n2 - (n4 + n3) * 2 / 3) * this.h / 10;
        if (n5 > c2.h) {
            n5 = c2.h;
        }
        c2.h = (short)(c2.h - n5);
        this.var_short_b = (short)(this.var_short_b + (unitsDataATK[c2.var_byte_d] + unitsDataDEF[c2.var_byte_d]) * n5);
        return n5;
    }

    public boolean boolean_a() {
        if (this.var_short_d < 3 && this.var_short_b >= 75 << this.var_short_d) {
            this.var_short_b = 0;
            this.var_short_d = (short)(this.var_short_d + 1);
            return true;
        }
        return false;
    }

    public boolean a(c c2, int n, int n2) {
        return this.h > 0 && Math.abs(this.i - n) + Math.abs(this.var_short_a - n2) == 1 && unitsDataRangeMin[this.var_byte_d] == 1;
    }

    public void a(byte by) {
        this.var_byte_b = (byte)(this.var_byte_b | by);
        this.d();
    }

    public void b(byte by) {
        this.var_byte_b = (byte)(this.var_byte_b & ~by);
        this.d();
    }

    public void d() {
        this.k = 0;
        this.var_short_f = 0;
        this.var_short_e = 0;
        if ((this.var_byte_b & 1) != 0) {
            this.k = (short)(this.k - 1);
            this.var_short_f = (short)(this.var_short_f - 1);
            this.var_short_e = (short)(this.var_short_e - 1);
        }
        if ((this.var_byte_b & 2) != 0) {
            this.var_short_f = (short)(this.var_short_f + 1);
        }
    }

    public void c(int n, int n2) {
        this.i = (short)n;
        this.var_short_a = (short)n2;
        this.var_short_b = (short)(n * 24);
        ((e)this).l = (short)(n2 * 24);
    }

    public int int_a() {
        int n = 10 / this.var_int_arr_arr_a.length;
        int n2 = this.h / n;
        if (this.h != 10 && this.h % n > 0) {
            ++n2;
        }
        return n2;
    }

    public int int_a(int n, int n2) {
        return (this.var_short_d + unitsDataATK[this.var_byte_d] + unitsDataDEF[this.var_byte_d] + var_i_a.a(var_i_a.byte_a(n, n2), this)) * this.h;
    }

    public void a(byte[][] byArray, int n, int n2) {
        int n3;
        int n4;
        int n5;
        byte by = unitsDataRangeMin[this.var_byte_d];
        byte by2 = unitsDataRangeMax[this.var_byte_d];
        int n6 = n - by2;
        if (n6 < 0) {
            n6 = 0;
        }
        if ((n5 = n2 - by2) < 0) {
            n5 = 0;
        }
        if ((n4 = n + by2) >= c.var_i_a.var_short_e) {
            n4 = c.var_i_a.var_short_e - 1;
        }
        if ((n3 = n2 + by2) >= c.var_i_a.var_short_b) {
            n3 = c.var_i_a.var_short_b - 1;
        }
        for (int j = n6; j <= n4; ++j) {
            for (int k = n5; k <= n3; ++k) {
                int n7 = Math.abs(j - n) + Math.abs(k - n2);
                if (n7 < by || n7 > by2 || byArray[j][k] > 0) continue;
                byArray[j][k] = 127;
            }
        }
    }

    public void a(byte[][] byArray) {
        if (this.a((short)512)) {
            this.a(byArray, (int)this.i, (int)this.var_short_a);
            return;
        }
        this.b(byArray);
        for (int j = 0; j < c.var_i_a.var_short_e; ++j) {
            for (int k = 0; k < c.var_i_a.var_short_b; ++k) {
                if (byArray[j][k] <= 0 || byArray[j][k] == 127) continue;
                this.a(byArray, j, k);
            }
        }
    }

    public c[] a(int n, int n2, byte by) {
        return this.a(n, n2, (int)unitsDataRangeMin[this.var_byte_d], (int)unitsDataRangeMax[this.var_byte_d], by);
    }

    public c[] a(int n, int n2, int n3, int n4, byte by) {
        Vector<c> vector = new Vector<c>();
        int n5 = n - n4;
        int n6 = n2 - n4;
        int n7 = n + n4;
        int n8 = n2 + n4;
        for (int j = n5; j <= n7; ++j) {
            for (int k = n6; k <= n8; ++k) {
                c c2;
                int n9 = Math.abs(j - n) + Math.abs(k - n2);
                if (n9 < n3 || n9 > n4) continue;
                if (by == 0) {
                    c2 = var_i_a.c_a(j, k, (byte)0);
                    if (c2 == null || c2.var_byte_a == this.var_byte_a) continue;
                    vector.addElement(c2);
                    continue;
                }
                if (by == 1) {
                    c2 = var_i_a.c_a(j, k, (byte)1);
                    if (c2 == null) continue;
                    vector.addElement(c2);
                    continue;
                }
                if (by != 2 || (c2 = var_i_a.c_a(j, k, (byte)0)) == null || c2.var_byte_a != this.var_byte_a) continue;
                vector.addElement(c2);
            }
        }
        c[] objectArray = new c[vector.size()];
        vector.copyInto(objectArray);
        return objectArray;
    }

    public void void_a(int n, int n2) {
        this.j = (short)(n * 24);
        this.var_short_c = (short)(n2 * 24);
        this.var_java_util_Vector_a = this.a(this.i, this.var_short_a, n, n2);
        this.var_short_g = 0;
        this.var_byte_e = 1;
    }

    public Vector a(int n, int n2, int n3, int n4) {
        int n5;
        Vector vector = null;
        short[] sArray = new short[]{(short)n3, (short)n4};
        if (n == n3 && n2 == n4) {
            vector = new Vector();
            vector.addElement(sArray);
            return vector;
        }
        byte by = 0;
        byte by2 = 0;
        byte by3 = 0;
        int n6 = 0;
        if (n4 > 0) {
            by = c.var_i_a.var_byte_arr_arr_b[n3][n4 - 1];
        }
        if (n4 < c.var_i_a.var_short_b - 1) {
            by2 = c.var_i_a.var_byte_arr_arr_b[n3][n4 + 1];
        }
        if (n3 > 0) {
            by3 = c.var_i_a.var_byte_arr_arr_b[n3 - 1][n4];
        }
        if (n3 < c.var_i_a.var_short_e - 1) {
            n6 = c.var_i_a.var_byte_arr_arr_b[n3 + 1][n4];
        }
        if ((n5 = Math.max(Math.max(by, by2), Math.max(by3, n6))) == by) {
            vector = this.a(n, n2, n3, n4 - 1);
        } else if (n5 == by2) {
            vector = this.a(n, n2, n3, n4 + 1);
        } else if (n5 == by3) {
            vector = this.a(n, n2, n3 - 1, n4);
        } else if (n5 == n6) {
            vector = this.a(n, n2, n3 + 1, n4);
        }
        vector.addElement(sArray);
        return vector;
    }

    public void b(byte[][] byArray) {
        this.a(byArray, (int)this.i, (int)this.var_short_a, unitsDataMOV[this.var_byte_d] + this.k, -1);
    }

    public void a(byte[][] byArray, int n, int n2, int n3, int n4) {
        int n5;
        if (n3 <= byArray[n][n2]) {
            return;
        }
        byArray[n][n2] = (byte)n3;
        if (n4 != 1 && (n5 = n3 - this.int_b(n, n2 - 1)) >= 0) {
            this.a(byArray, n, n2 - 1, n5, 2);
        }
        if (n4 != 2 && (n5 = n3 - this.int_b(n, n2 + 1)) >= 0) {
            this.a(byArray, n, n2 + 1, n5, 1);
        }
        if (n4 != 4 && (n5 = n3 - this.int_b(n - 1, n2)) >= 0) {
            this.a(byArray, n - 1, n2, n5, 8);
        }
        if (n4 != 8 && (n5 = n3 - this.int_b(n + 1, n2)) >= 0) {
            this.a(byArray, n + 1, n2, n5, 4);
        }
    }

    private int int_b(int n, int n2) {
        if (n >= 0 && n2 >= 0 && n < c.var_i_a.var_short_e && n2 < c.var_i_a.var_short_b) {
            c c2 = var_i_a.c_a(n, n2, (byte)0);
            if (c2 != null && c2.var_byte_a != this.var_byte_a) {
                return 1000;
            }
            byte by = var_i_a.byte_a(n, n2);
            if (this.a((short)1)) {
                return 1;
            }
            if (this.a((short)2)) {
                if (by == 5) {
                    return 1;
                }
                return Class_I.terrainTypeMovementReduction_XXX[by] * 2;
            }
            return Class_I.terrainTypeMovementReduction_XXX[by];
        }
        return 10000;
    }

    public void void_a() {
        if (this.var_boolean_e) {
            if (c.var_i_a.var_long_n - this.var_long_c >= (long)this.var_int_g) {
                this.var_boolean_e = false;
            } else {
                this.var_boolean_b = !this.var_boolean_b;
            }
        }
        if (this.var_byte_e == 1) {
            if (this.var_short_g >= this.var_java_util_Vector_a.size()) {
                this.var_byte_e = 0;
                this.i = (short)(this.var_short_b / 24);
                this.var_short_a = (short)(((e)this).l / 24);
                this.var_java_util_Vector_a = null;
                this.var_short_g = 0;
                var_i_a.c(this);
            } else {
                short[] sArray = (short[])this.var_java_util_Vector_a.elementAt(this.var_short_g);
                int n = sArray[0] * 24;
                int n2 = sArray[1] * 24;
                if (n < this.var_short_b) {
                    this.var_short_b = (short)(this.var_short_b - 6);
                } else if (n > this.var_short_b) {
                    this.var_short_b = (short)(this.var_short_b + 6);
                } else if (n2 < ((e)this).l) {
                    ((e)this).l = (short)(((e)this).l - 6);
                } else if (n2 > ((e)this).l) {
                    ((e)this).l = (short)(((e)this).l + 6);
                }
                if (this.var_short_b % 24 == 0 && ((e)this).l % 24 == 0) {
                    this.var_short_g = (short)(this.var_short_g + 1);
                }
            }
            super.void_b(this.var_short_b, ((e)this).l);
        } else if (c.var_i_a.var_long_n - this.var_long_a >= 200L) {
            this.c();
            this.var_long_a = c.var_i_a.var_long_n;
        }
    }

    public boolean a(short s) {
        return (this.l & s) != 0;
    }

    public void void_b() {
        c c2;
        int n;
        this.var_byte_e = (byte)2;
        c c3 = var_i_a.c_a((int)this.i, (int)this.var_short_a, (byte)1);
        if (c3 != null) {
            c.var_i_a.var_java_util_Vector_a.removeElement(c3);
        }
        int n2 = c.var_i_a.var_java_util_Vector_a.size();
        for (n = 0; n < n2; ++n) {
            c2 = (c)c.var_i_a.var_java_util_Vector_a.elementAt(n);
            if (c2.var_byte_a != this.var_byte_a) continue;
            c2.b((byte)2);
        }
        n2 = c.var_i_a.var_java_util_Vector_a.size();
        for (n = 0; n < n2; ++n) {
            c2 = (c)c.var_i_a.var_java_util_Vector_a.elementAt(n);
            if (c2.var_byte_a != this.var_byte_a || !c2.a((short)256)) continue;
            c[] cArray = c2.a(c2.i, (int)c2.var_short_a, 1, 2, (byte)2);
            for (int j = 0; j < cArray.length; ++j) {
                cArray[j].a((byte)2);
                var_i_a.a(c.var_i_a.var_e_r, cArray[j].var_short_b, ((e)cArray[j]).l, 0, 0, 1, 50);
            }
        }
        c.var_i_a.var_c_f = this;
    }

    public static byte[] byte_arr_a() {
        byte[] byArray = new byte[11];
        int n = 0;
        for (int n2 = 0; n2 <= c.var_i_a.J; n2 = (int)((byte)(n2 + 1))) {
            if (unitsDataPrice[n2] <= 0) continue;
            byArray[n++] = (byte)n2;
        }
        byte[] byArray2 = new byte[n];
        System.arraycopy(byArray, 0, byArray2, 0, n);
        return byArray2;
    }

    public void a(Graphics graphics, int n, int n2) {
        if (this.var_boolean_e) {
            int n3 = this.var_boolean_b ? -2 : 2;
            int n4 = AppCanvas.randomGen.nextInt() % 1;
            super.a(graphics, n + n3, n2 + n4);
        } else {
            super.a(graphics, n, n2);
        }
    }

    public void b(Graphics graphics, int n, int n2) {
        int n3 = this.var_short_b + n;
        int n4 = ((e)this).l + n2;
        if (this.var_byte_e != 3) {
            if (this.var_byte_e == 2) {
                AppCanvas.drawBoldWhiteText(graphics, "E", n3 + this.short_a() - 7, n4 + this.short_b() - 5, 0);
            }
            if (this.h < 10) {
                AppCanvas.drawBoldWhiteText(graphics, "" + this.h, n3, n4 + this.short_b() - 5, 0);
            }
        }
    }

    static {
        //           0        1       2       3       4     5       6      7         8          9     10
        // 11 units (soldier, archer, lizard, wizard, wisp, spider, golem, catapult, wyvern) + (king, skeleton)
        unitsDataMOV = new byte[]{4, 4, 6, 4, 4, 5, 4, 3, 7, 4, 4};
        unitsDataATK = new byte[]{5, 5, 5, 4, 3, 6, 6, 7, 8, 5, 5};
        unitsDataDEF = new byte[]{1, 1, 2, 1, 2, 2, 4, 2, 3, 3, 1};
        unitsDataRangeMax = new byte[]{1, 2, 1, 1, 1, 1, 1, 4, 1, 1, 1};
        unitsDataRangeMin = new byte[]{1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1};
        var_int_arr_arr_arr_a = new int[][][]{new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}}, new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}}, new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}}, new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}}, new int[][]{{32, 70}, {10, 50}, {10, 90}}, new int[][]{{18, 70}, {3, 40}, {3, 100}}, new int[][]{{32, 65}, {10, 40}, {10, 85}}, new int[][]{{5, 40}, {5, 75}}, new int[][]{{22, 65}, {2, 40}, {2, 90}}, new int[][]{{5, 58}}, new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}}};
        // The last two cannot be bought (king and skeleton)
        unitsDataPrice = new short[]{150, 250, 300, 400, 500, 600, 600, 700, 1000, -1, -1};
        var_short_arr_a = new short[]{8, 64, 2, 32, 256, 128, 0, 512, 1, 28, 0};
    }
}

