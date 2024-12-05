/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import javax.microedition.lcdui.Graphics;

public class f {
    // TODO only a fraction of these are actually used, maybe clean up?
    public static final byte TERRAIN_ROAD = 0;
    public static final byte TERRAIN_GRASS = 1;
    public static final byte TERRAIN_WOODS = 2;
    public static final byte TERRAIN_HILL = 3;
    public static final byte TERRAIN_MOUNTAIN = 4;
    public static final byte TERRAIN_WATER = 5;
    public static final byte TERRAIN_BRIDGE = 6;
    public static final byte TERRAIN_TOWN = 7;
    public static final byte TERRAIN_CASTLE = 8;

    private static final int[] unitsSoundIndex;
    private static final String[] unitsNames;
    private a var_a_a;
    private Unit unit;
    public boolean var_boolean_f = false;
    public boolean var_boolean_e = false;
    public byte unitQuantityAfterBattle;
    private byte var_byte_a;
    public byte var_byte_d;
    private byte var_byte_b;
    private byte var_byte_f;
    private static final byte[] var_byte_arr_a;
    private static final byte[][][][] var_byte_arr_arr_arr_arr_a;
    private byte[][] var_byte_arr_arr_a;
    private static final byte[][] attackAnimTable_1;
    private static final byte[][] attackAnimTable_2;
    private static final byte[][] attackAnimTableKingslash;
    private int o = 0;
    public static final byte[] var_byte_arr_c;
    public static final byte[] var_byte_arr_b;
    private SpriteSheet var_e_c;
    private SpriteSheet _attackProjectile;
    private SpriteSheet var_e_b;
    private int m;
    private int var_int_a;
    //private boolean var_boolean_b;    // Unused
    private long var_long_a;
    private long var_long_c;
    private long var_long_b;
    private SpriteSheet[] var_e_arr_b;
    private Sprite[] var_h_arr_a;
    private Sprite var_h_a;
    public f var_f_a;
    private int _terrainTypeBG;
    private int _terrainTypeFloor;
    private int var_int_d;
    private int j;
    private int n;
    private boolean var_boolean_d = false;
    private int unitQuantity;
    private int l;
    private int[] var_int_arr_d;
    private int[] var_int_arr_c;
    private int[][] _unitsCoords;
    private boolean[] var_boolean_arr_a;
    private int var_int_c;
    private byte var_byte_e;
    private int k;
    private int var_int_e;
    private int[] var_int_arr_a;
    private byte[][] var_byte_arr_arr_c;
    private int soundIndex;
    //private boolean var_boolean_c = false;    // Unused
    private boolean var_boolean_a = false;
    private SpriteSheet[] var_e_arr_a;
    private int i;

    public f(Class_I i2, Unit unit, boolean bl) throws Exception {
        int n;
        this.var_a_a = (a)i2;
        this.unit = unit;
        this.var_byte_e = var_byte_arr_a[unit.unitType];
        this.var_int_e = 200;
        this.soundIndex = unitsSoundIndex[unit.unitType];
        if (this.soundIndex != -1) {
            // empty if block
        }
        if (this.var_byte_e == 3) {
            this.var_int_e = 0;
        }
        this.unitQuantity = (byte)unit.quantity;
        this.var_byte_b = this.var_byte_a = (byte)unit.int_a();
        //this.var_boolean_b = bl;
        int n2 = 0;
        if (bl) {
            this.var_byte_f = 0;
            this.m = 0;
            this.b();
        } else {
            n2 = AppCanvas.cenX;
            this.var_byte_f = 1;
            this.o = 6;
        }
        this.var_byte_arr_arr_a = var_byte_arr_arr_arr_arr_a[this.var_byte_e][this.var_byte_f];
        this._terrainTypeFloor = this._terrainTypeBG = (int)i2.getTerrainType(unit.mapX, unit.mapY);
        if (this._terrainTypeBG == TERRAIN_WOODS || this._terrainTypeBG == TERRAIN_HILL) {
            this._terrainTypeFloor = TERRAIN_GRASS;
        }
        if (this.var_a_a.var_h_arr_arr_a[this._terrainTypeFloor] == null) {
            // TODO rename to terrainBattleSprites
            this.var_a_a.var_h_arr_arr_a[this._terrainTypeFloor] = new SpriteSheet(Class_I.terrainTypeNames[this._terrainTypeFloor]).sprites;
        }
        this.var_h_arr_a = this.var_a_a.var_h_arr_arr_a[this._terrainTypeFloor];
        if (this.var_a_a.b[this._terrainTypeBG] == null) {
            try {
                // TODO split and see what sprites are these
                this.var_a_a.b[this._terrainTypeBG] = this._terrainTypeBG == TERRAIN_HILL ? new Sprite("hill_bg.png") : new Sprite(Class_I.terrainTypeNames[this._terrainTypeBG] + "_bg.png");
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.var_h_a = this.var_a_a.b[this._terrainTypeBG];
        if (this.var_h_a != null) {
            this.var_int_d = this.var_h_a.height;
        }
        this.j = AppCanvas.cenX / this.var_h_arr_a[0].width;
        if (AppCanvas.cenX % this.var_h_arr_a[0].width != 0) {
            ++this.j;
        }
        this.n = (AppCanvas.height2 - this.var_int_d) / this.var_h_arr_a[0].height;
        if ((AppCanvas.height2 - this.var_int_d) % this.var_h_arr_a[0].height != 0) {
            ++this.n;
        }
        this.var_byte_arr_arr_c = new byte[this.j][this.n];
        for (n = 0; n < this.j; ++n) {
            for (int j = 0; j < this.n; ++j) {
                this.var_byte_arr_arr_c[n][j] = (byte)Math.abs(AppCanvas.randomInt() % 10);
                this.var_byte_arr_arr_c[n][j] = this.var_byte_arr_arr_c[n][j] >= 9 ? 2 : (this.var_byte_arr_arr_c[n][j] >= 8 ? (byte)1 : 0);
            }
        }
        if (this.var_a_a.ssUnitsBattleAnimations[unit.owner][unit.unitType] == null) {
            this.var_a_a.ssUnitsBattleAnimations[unit.owner][unit.unitType] = new SpriteSheet(unitsNames[unit.unitType], unit.owner);
        }
        this.var_e_c = this.var_a_a.ssUnitsBattleAnimations[unit.owner][unit.unitType];
        if (this.var_byte_e == 2 || this.var_byte_e == 4) {
            if (i2.ssBattleFxSwordSlash == null) {
                // Soldiers and lizards have this attack animation
                i2.ssBattleFxSwordSlash = new SpriteSheet("slash");
            }
            this.var_e_b = new SpriteSheet(i2.ssBattleFxSwordSlash);
            this.var_e_b.setReorderTable(attackAnimTable_2[this.var_byte_f]);
        } else if (unit.unitType == Unit.KING) {
            if (i2.ssBattleFxKingWave == null) {
                i2.ssBattleFxKingWave = new SpriteSheet("kingslash");
            }
            this._attackProjectile = i2.ssBattleFxKingWave;
            // Shown while the king attacks, only 1 frame: https://youtu.be/6MTmxnNygSw?t=123
            this.var_e_b = new SpriteSheet("kingswing");
            // TODO I believe the parameter is the side of the screen, sheets have frames for each side
            this.var_e_b.setReorderTable(attackAnimTable_2[this.var_byte_f]);
        } else if (unit.unitType == Unit.SPIDER) {
            if (i2.ssBattleFxSpiderSpit == null) {
                i2.ssBattleFxSpiderSpit = new SpriteSheet("spiderspit");
            }
            this._attackProjectile = i2.ssBattleFxSpiderSpit;
        } else if (unit.unitType == Unit.CATAPULT) {
            if (i2.ssBattleFxCatapultStone == null) {
                i2.ssBattleFxCatapultStone = new SpriteSheet("stone");
            }
            this._attackProjectile = i2.ssBattleFxCatapultStone;
        } else if (unit.unitType == Unit.WIZARD) {
            this._attackProjectile = i2.blueSparkSheet;
        } else if (unit.unitType == Unit.WYVERN) {
            if (i2.ssBattleFxWyvernFireball == null) {
                i2.ssBattleFxWyvernFireball = new SpriteSheet("fireball");
            }
            this._attackProjectile = i2.ssBattleFxWyvernFireball;
        }
        this._unitsCoords = new int[unit.battleScreenCoords.length][2];
        for (n = 0; n < this._unitsCoords.length; ++n) {
            this._unitsCoords[n][0] = this.var_byte_f == 0 ? AppCanvas.int_b(unit.battleScreenCoords[n][0]) : (int)((short)(AppCanvas.cenX - AppCanvas.int_b(unit.battleScreenCoords[n][0]) - this.var_e_c.getSpritesWidth()));
            this._unitsCoords[n][1] = AppCanvas.int_a(unit.battleScreenCoords[n][1]);
        }
        this.var_int_arr_c = new int[this.var_byte_a];
        this.var_int_arr_d = new int[this.var_byte_a];
        this.var_e_arr_b = new SpriteSheet[this.var_byte_a];
        this.var_int_arr_a = new int[this.var_byte_a];
        if (unit.unitType == Unit.WISP) {
            this.var_boolean_arr_a = new boolean[this.var_byte_a];
        }
        for (n = 0; n < this.var_byte_a; ++n) {
            this.var_int_arr_c[n] = 0;
            this.var_e_arr_b[n] = new SpriteSheet(this.var_e_c);
            this.var_e_arr_b[n].setMapPixelCoords(this._unitsCoords[n][0] + n2, this._unitsCoords[n][1]);
            this.a(n, this.var_byte_arr_arr_a[0]);
            if (unit.unitType == Unit.WYVERN) {
                this.var_int_arr_d[n] = -8 - Math.abs(AppCanvas.randomInt()) % 8;
            }
            if (unit.unitType != Unit.WISP) continue;
            this.var_int_arr_d[n] = AppCanvas.randomInt() % 5 - 8;
            this.var_int_arr_c[n] = AppCanvas.randomInt() % 5;
            this.var_boolean_arr_a[n] = this.var_int_arr_c[n] < 0;
        }
    }

    private int a(SpriteSheet e2, int n) {
        if (this.var_byte_f == 1) {
            return this.var_e_c.getSpritesWidth() - n - e2.getSpritesWidth();
        }
        return n;
    }

    private void a(int n, byte[] byArray) {
        this.var_e_arr_b[n].setReorderTable(byArray);
        this.var_int_arr_a[n] = (int)this.var_a_a.time;
    }

    public void b() {
        this.var_boolean_e = true;
        this.o = 0;
        this.var_long_c = this.var_a_a.time;
    }

    private void f() {
        switch (this.o) {
            case 0: {
                if (this.var_int_c == 0 && this.var_a_a.time - this.var_long_c >= 200L) {
                    this.k = 600;
                    this.var_e_arr_a = new SpriteSheet[this.var_byte_b];
                    for (int j = 0; j < this.var_byte_b; ++j) {
                        if (this.var_byte_e == 5) {
                            this.k = 0;
                            continue;
                        }
                        if (this.unit.unitType != Unit.WIZARD) continue;
                        this.a(j, this.var_byte_arr_arr_a[1]);
                        this.var_e_arr_a[j] = SpriteSheet.a(this._attackProjectile, 0, 0, -1, 0, (byte)0);
                        this.var_e_arr_a[j].setMapPixelCoords(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].getSpritesWidth() - this.var_e_arr_a[j].getSpritesWidth()) / 2, this.var_e_arr_b[j].l - this.var_e_arr_a[j].getSpritesHeight());
                        this.var_a_a.var_java_util_Vector_c.addElement(this.var_e_arr_a[j]);
                        this.var_e_arr_a[j].var_boolean_a = true;
                    }
                    this.var_int_c = 1;
                    this.var_long_c = this.var_a_a.time;
                }
                if (this.var_int_c != 1 || this.var_a_a.time - this.var_long_c < (long)this.k) break;
                this.o = 2;
                this.var_long_c = this.var_a_a.time;
                break;
            }
            case 2: {
                if (this.unit.unitType == Unit.WIZARD) {
                    for (int j = 0; j < this.var_byte_b; ++j) {
                        this.var_e_arr_a[j].var_int_d = var_byte_arr_b[this.var_byte_f];
                    }
                } else {
                    if (this.unit.unitType == Unit.CATAPULT) {
                        this.c();
                    }
                    this.var_e_arr_a = new SpriteSheet[this.var_byte_b];
                    for (int j = 0; j < this.var_byte_b; ++j) {
                        if (this.var_byte_e == 5) {
                            this.a(j, this.var_byte_arr_arr_a[1]);
                            if (this.unit.unitType == Unit.KING) {
                                SpriteSheet e2 = SpriteSheet.a(this.var_e_b, 0, 0, 1, 200, (byte)0);
                                e2.setMapPixelCoords(this.var_e_arr_b[0].var_short_b + this.a(e2, 5), this.var_e_arr_b[0].l + 2);
                                this.var_a_a.var_java_util_Vector_c.addElement(e2);
                            }
                        } else {
                            this.a(j, this.var_byte_arr_arr_a[1]);
                            this.var_boolean_a = true;
                        }
                        if (this.unit.unitType == Unit.ARCHER) {
                            this.var_e_arr_a[j] = SpriteSheet.a(null, var_byte_arr_b[this.var_byte_f] * 3, -4, -1, 0, (byte)3);
                            this.var_e_arr_a[j].setMapPixelCoords(this.var_e_arr_b[j].var_short_b + this.a(this.var_e_arr_a[j], this.var_e_arr_b[j].getSpritesWidth()), this.var_e_arr_b[j].l);
                        } else if (this.unit.unitType == Unit.CATAPULT) {
                            this.var_e_arr_a[j] = SpriteSheet.a(this._attackProjectile, var_byte_arr_b[this.var_byte_f] * 10, -8, -1, 0, (byte)0);
                            this.var_e_arr_a[j].setMapPixelCoords(this.var_e_arr_b[j].var_short_b + this.a(this.var_e_arr_a[j], 18), this.var_e_arr_b[j].l);
                        } else if (this.unit.unitType == Unit.SPIDER) {
                            this.var_e_arr_a[j] = SpriteSheet.a(this._attackProjectile, var_byte_arr_b[this.var_byte_f] * 2, 0, -1, 0, (byte)0);
                            this.var_e_arr_a[j].setMapPixelCoords(this.var_e_arr_b[j].var_short_b + this.a(this.var_e_arr_a[j], this.var_e_arr_b[j].getSpritesWidth() - 10), this.var_e_arr_b[j].l + this.var_e_arr_b[j].getSpritesHeight() / 3);
                        } else {
                            this.var_e_arr_a[j] = SpriteSheet.a(this._attackProjectile, var_byte_arr_b[this.var_byte_f] * 2, 0, -1, 0, (byte)0);
                            this.var_e_arr_a[j].setMapPixelCoords(this.var_e_arr_b[j].var_short_b + this.a(this.var_e_arr_a[j], this.var_e_arr_b[j].getSpritesWidth()), this.var_e_arr_b[j].l);
                        }
                        if (this.unit.unitType != Unit.CATAPULT) {
                            if (this.unit.unitType == Unit.KING) {
                                this.var_e_arr_a[j].setReorderTable(attackAnimTableKingslash[this.var_byte_f]);
                            } else if (this.unit.unitType == Unit.WYVERN) {
                                this.var_e_arr_a[j].setReorderTable(attackAnimTable_1[this.var_byte_f]);
                            } else {
                                this.var_e_arr_a[j].setReorderTable(attackAnimTable_2[this.var_byte_f]);
                            }
                        }
                        this.var_a_a.var_java_util_Vector_c.addElement(this.var_e_arr_a[j]);
                        this.var_e_arr_a[j].var_boolean_a = true;
                    }
                    if (this.soundIndex != -1) {
                        AppCanvas.playSound(this.soundIndex, 1);
                    }
                }
                this.o = 5;
                break;
            }
            case 5: {
                boolean bl = true;
                for (int j = 0; j < this.var_e_arr_a.length; ++j) {
                    if (this.unit.unitType == Unit.WYVERN && AppCanvas.randomInt() % 2 == 0) {
                        SpriteSheet e3 = SpriteSheet.a(this.var_a_a.spriteSheetChimneySmoke, 0, -1, 1, 200, (byte)0);
                        e3.setMapPixelCoords(this.var_e_arr_a[j].var_short_b + this.a(this.var_e_arr_a[j], 0), this.var_e_arr_a[j].l + 4);
                        this.var_a_a.var_java_util_Vector_c.addElement(e3);
                        e3.var_boolean_a = true;
                    }
                    if (this.var_byte_f == 0) {
                        if (this.var_e_arr_a[j].var_short_b > AppCanvas.cenX) {
                            this.var_a_a.var_java_util_Vector_c.removeElement(this.var_e_arr_a[j]);
                            continue;
                        }
                        bl = false;
                        continue;
                    }
                    if (this.var_byte_f != 1) continue;
                    if (this.var_e_arr_a[j].var_short_b + this.var_e_arr_a[j].getSpritesWidth() < AppCanvas.cenX) {
                        this.var_a_a.var_java_util_Vector_c.removeElement(this.var_e_arr_a[j]);
                        continue;
                    }
                    bl = false;
                }
                if (!bl) break;
                this.o = 3;
                this.var_long_c = this.var_a_a.time;
                break;
            }
            case 3: {
                if (this.var_a_a.time - this.var_long_c < 400L) break;
                if (this.unit.unitType != Unit.CATAPULT && this.unit.unitType != Unit.ARCHER) {
                    for (int j = 0; j < this.var_byte_b; ++j) {
                        this.a(j, this.var_byte_arr_arr_a[0]);
                        this.var_boolean_a = false;
                        this.var_int_arr_c[j] = -2;
                    }
                }
                this.var_f_a.d();
                if (this.unit.unitType != Unit.ARCHER) {
                    this.var_a_a.c(200);
                }
                this.o = 7;
                this.var_long_c = this.var_a_a.time;
                break;
            }
            case 7: {
                if (this.var_a_a.time - this.var_long_c < 500L) break;
                this.var_boolean_f = true;
            }
        }
    }

    private void c() {
        //AppCanvas.d(200);
        this.var_boolean_d = true;
        this.var_long_b = this.var_a_a.time;
    }

    private void a() {
        switch (this.o) {
            case 0: {
                if (this.var_int_c == 0) {
                    this.var_int_e = 100;
                    if (this.var_a_a.time - this.var_long_c < 200L) break;
                    this.var_int_c = 1;
                    this.var_long_c = this.var_a_a.time;
                    AppCanvas.playSound(this.soundIndex, 1);
                    break;
                }
                if (this.var_int_c == 1) {
                    if (this.var_a_a.time - this.var_long_c < 200L) break;
                    this.var_int_e -= 20;
                    if (this.var_int_e <= 0) {
                        this.var_a_a.var_boolean_b = true;
                        this.o = 5;
                    }
                    this.var_long_c = this.var_a_a.time;
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
                if (this.var_a_a.time - this.var_long_c < 500L) break;
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
        if (this.var_a_a.time - this.var_long_a >= 300L) {
            bl = true;
            this.var_long_a = this.var_a_a.time;
        }
        for (int j = 0; j < this.var_e_arr_b.length; ++j) {
            if (bl) {
                SpriteSheet e2 = SpriteSheet.a(null, 0, 0, 1, 500, (byte)4);
                e2.setMapPixelCoords(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].getSpritesWidth() >> 1), this.var_e_arr_b[j].l + (this.var_e_arr_b[j].getSpritesHeight() >> 1) + this.var_int_a + this.var_int_arr_d[j]);
                this.var_a_a.var_java_util_Vector_c.addElement(e2);
            }
            if (this.var_boolean_arr_a[j] && this.var_int_arr_c[j] <= -4 || !this.var_boolean_arr_a[j] && this.var_int_arr_c[j] >= 4) {
                this.var_boolean_arr_a[j] = !this.var_boolean_arr_a[j];
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
            if (this.var_a_a.time - this.var_long_b >= 300L) {
                this.var_boolean_d = false;
                this.m = 0;
                this.var_int_a = 0;
            } else {
                this.m = this.m > 0 ? -2 : 2;
                this.var_int_a = AppCanvas.randomInt() % 1;
            }
        }
        if (var_byte_arr_a[this.unit.unitType] == 1 || var_byte_arr_a[this.unit.unitType] == 5 || var_byte_arr_a[this.unit.unitType] == 0) {
            this.f();
        } else if (var_byte_arr_a[this.unit.unitType] == 6) {
            this.a();
        } else {
            n = 0;
            switch (this.o) {
                case 0: {
                    int n2;
                    if (this.var_a_a.time - this.var_long_c < 200L) break;
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
                        SpriteSheet e2;
                        if (this.var_byte_e == 4) {
                            if (this.var_int_arr_c[n2] == -1) continue;
                            this.var_e_arr_b[n2].setMapPixelCoords(this.var_e_arr_b[n2].var_short_b + var_byte_arr_b[this.var_byte_f], this.var_e_arr_b[n2].l);
                            int n3 = n2;
                            this.var_int_arr_c[n3] = this.var_int_arr_c[n3] + 3;
                            if (this.var_int_arr_c[n2] >= 24) {
                                this.a(n2, this.var_byte_arr_arr_a[2]);
                                e2 = SpriteSheet.a(this.var_e_b, 0, 0, 1, 150, (byte)0);
                                e2.setMapPixelCoords(this.var_e_arr_b[n2].var_short_b + this.a(e2, 24), this.var_e_arr_b[n2].l + 3);
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
                            this.var_e_arr_b[n2].setMapPixelCoords(this.var_e_arr_b[n2].var_short_b + var_byte_arr_b[this.var_byte_f], this.var_e_arr_b[n2].l + this.var_int_arr_c[n2]);
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
                        e2 = SpriteSheet.a(this.var_e_b, 0, 0, 1, 150, (byte)0);
                        e2.setMapPixelCoords(this.var_e_arr_b[n2].var_short_b + this.a(e2, 14), this.var_e_arr_b[n2].l + 4);
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
                    this.var_long_c = this.var_a_a.time;
                    break;
                }
                case 5: {
                    int n2;
                    if (this.var_byte_e == 4) {
                        if (this.var_a_a.time - this.var_long_c < 400L) break;
                        for (n2 = 0; n2 < this.var_byte_b; ++n2) {
                            this.var_e_arr_b[n2].setMapPixelCoords(this.var_e_arr_b[n2].var_short_b - var_byte_arr_c[this.var_byte_f], this.var_e_arr_b[n2].l);
                            this.a(n2, this.var_byte_arr_arr_a[3]);
                        }
                        this.o = 3;
                        break;
                    }
                    if (this.var_a_a.time - this.var_long_c < 50L) break;
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
                            this.var_e_arr_b[n2].setMapPixelCoords(this.var_e_arr_b[n2].var_short_b - var_byte_arr_b[this.var_byte_f], this.var_e_arr_b[n2].l);
                            int n6 = n2;
                            this.var_int_arr_c[n6] = this.var_int_arr_c[n6] + 3;
                            if (this.var_int_arr_c[n2] >= 24) {
                                this.var_e_arr_b[n2].setMapPixelCoords(this.var_e_arr_b[n2].var_short_b + var_byte_arr_c[this.var_byte_f], this.var_e_arr_b[n2].l);
                                this.a(n2, this.var_byte_arr_arr_a[0]);
                                this.var_int_arr_c[n2] = -1;
                                continue;
                            }
                            bl = false;
                            continue;
                        }
                        if (this.var_int_arr_c[n2] <= 6) {
                            if (this.unit.unitType != Unit.GOLEM && this.var_int_arr_c[n2] == -6) {
                                this.a(n2, this.var_byte_arr_arr_a[2]);
                            }
                            this.var_e_arr_b[n2].setMapPixelCoords(this.var_e_arr_b[n2].var_short_b - var_byte_arr_b[this.var_byte_f], this.var_e_arr_b[n2].l + this.var_int_arr_c[n2]);
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
                    this.var_long_c = this.var_a_a.time;
                }
            }
        }
        if (this.unit.unitType == Unit.WYVERN) {
            for (n = 0; n < this.var_e_arr_b.length; ++n) {
                if (this.var_boolean_a) continue;
                if (this.var_int_arr_d[n] >= -8) {
                    this.var_int_arr_c[n] = -6;
                    this.var_e_arr_b[n].nextFrame();
                    this.var_long_a = this.var_a_a.time;
                } else if (this.var_e_arr_b[n].currentIndex == 1 && this.var_a_a.time - this.var_long_a >= 200L) {
                    this.var_e_arr_b[n].nextFrame();
                }
                int n9 = n;
                this.var_int_arr_c[n9] = this.var_int_arr_c[n9] + 1;
                int n10 = n;
                this.var_int_arr_d[n10] = this.var_int_arr_d[n10] + (this.var_int_arr_c[n] >> 2);
            }
        } else {
            for (n = 0; n < this.var_e_arr_b.length; ++n) {
                if (this.var_a_a.time - (long)this.var_int_arr_a[n] < (long)this.var_int_e) continue;
                this.var_e_arr_b[n].nextFrame();
                this.var_int_arr_a[n] = (int)this.var_a_a.time;
            }
        }
    }

    private void d() {
        this.c();
        this.l = this.var_byte_a - this.var_byte_d;
        this.var_byte_b = this.var_byte_d;
        this.unitQuantity = this.unitQuantityAfterBattle;
        for (int j = 0; j < this.l; ++j) {
            SpriteSheet e2 = SpriteSheet.a(this.var_a_a.spriteSheetSoul, 0, -2, 1, 150, (byte)0);
            SpriteSheet e3 = SpriteSheet.a(this.var_a_a.redsparkSheet, 0, 0, 1, 100, (byte)0);
            e2.setMapPixelCoords(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].getSpritesWidth() - e2.getSpritesWidth()) / 2, this.var_e_arr_b[j].l + this.var_e_arr_b[j].getSpritesHeight() - e2.getSpritesHeight() + 3);
            e3.setMapPixelCoords(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].getSpritesWidth() - e3.getSpritesWidth()) / 2, this.var_e_arr_b[j].l + this.var_e_arr_b[j].getSpritesHeight() - e3.getSpritesHeight() + 3);
            this.var_a_a.var_java_util_Vector_c.addElement(e2);
            this.var_a_a.var_java_util_Vector_c.addElement(e3);
            e2 = SpriteSheet.a(null, 0, 0, 1, 800, (byte)2);
            e2.setMapPixelCoords(this.var_e_arr_b[j].var_short_b + (this.var_e_arr_b[j].getSpritesWidth() >> 1), this.var_e_arr_b[j].l + (this.var_e_arr_b[j].getSpritesHeight() >> 1));
            this.var_a_a.var_java_util_Vector_c.addElement(e2);
        }
        SpriteSheet[] eArray = new SpriteSheet[this.var_byte_b];
        System.arraycopy(this.var_e_arr_b, this.l, eArray, 0, this.var_byte_b);
        this.var_e_arr_b = eArray;
        AppCanvas.playSound(1, 1);
    }

    public void a(Graphics graphics, int offsetX, int offsetY) {
        graphics.translate(offsetX, offsetY);
        int n6 = 0;
        for (int n5 = 0; n5 < this.j; ++n5) {
            int n4 = this.var_int_d + offsetY;
            int n8 = this.n;
            for (int n3 = 0; n3 < n8; ++n3) {
                this.var_h_arr_a[this.var_byte_arr_arr_c[n5][n3]].draw(graphics, n6, n4);
                n4 += 24;
            }
            n6 += 24;
        }
        if (this.var_h_a != null) {
            int n5 = this.var_h_a.width;
            int x = 0;
            int n3 = AppCanvas.cenX / n5;
            for (int n7 = 0; n7 < n3; ++n7) {
                this.var_h_a.draw(graphics, x, 0);
                x += n5;
            }
        }

        String txtUnitQuantity = "" + this.unitQuantity;
        int txtUnitQuantityX = (AppCanvas.cenX - AppCanvas.getSpriteFontTextWidth(AppCanvas.FONT_NUMERIC, txtUnitQuantity)) / 2;
        AppCanvas.drawBoldWhiteText(graphics, txtUnitQuantity, txtUnitQuantityX, 2, AppCanvas.FONT_NUMERIC);
        txtUnitQuantity = null;

        n6 = (AppCanvas.cenX - this.var_a_a.spritePanelDefense.width) / 2;
        int n4 = AppCanvas.getSpriteFontCharHeight(AppCanvas.FONT_NUMERIC) + 4;
        this.var_a_a.spritePanelDefense.draw(graphics, n6, n4);

        byte terrainDEF = Class_I.terrainTypeDefense[this._terrainTypeBG];
        int terrainBonusDEF = this.var_a_a.getTerrainDefenceForUnit((byte)this._terrainTypeBG, this.unit) - terrainDEF;
        StringBuffer stringBuffer = new StringBuffer().append(terrainDEF);
        if (terrainBonusDEF > 0) {
            stringBuffer.append("+" + terrainBonusDEF);
        }
        AppCanvas.drawBoldWhiteText(graphics, stringBuffer.toString(), n6 + 28, n4 + 5, AppCanvas.FONT_ALPHANUMERIC);
        graphics.translate(-offsetX, -offsetY);
    }

    public void a(Graphics graphics) {
        if (this.var_a_a.var_boolean_b) {
            graphics.setColor(0xFFFFFF);    // White
            graphics.fillRect(0, 0, AppCanvas.width2, AppCanvas.height2);
        }
        for (int j = 0; j < this.var_byte_b; ++j) {
            SpriteSheet e2 = this.var_e_arr_b[j];
            if (this.unit.unitType == Unit.WISP) {
                e2.a(graphics, this.m, this.var_int_a + this.var_int_arr_d[j]);
                continue;
            }
            e2.a(graphics, this.m, this.var_int_a + this.var_int_arr_d[j]);
        }
    }

    public void b(Graphics graphics) {
        graphics.setColor(0x404040);    // Dark gray
        for (int j = 0; j < this.var_byte_b; ++j) {
            SpriteSheet e2 = this.var_e_arr_b[j];
            if (this.var_byte_e == 1 || this.var_byte_e == 2 || this.var_byte_e == 3) {
                graphics.fillArc((int)e2.var_short_b, this._unitsCoords[j + this.l][1] + e2.getSpritesHeight() * 4 / 5, (int)e2.getSpritesWidth(), e2.getSpritesHeight() / 4, 0, 360);
                continue;
            }
            if (this.var_byte_e != 6) continue;
            graphics.fillArc((int)e2.var_short_b, this._unitsCoords[j + this.l][1] + e2.getSpritesHeight() * 4 / 5, (int)e2.getSpritesWidth(), e2.getSpritesHeight() / 4, 0, 360);
        }
    }

    // Does nothing
    // public void soundMethod() {
    //     if (this.soundIndex != -1) {
    //         // empty if block
    //     }
    // }

    static {
        // TODO sounds were purposefully disabled, maybe there's a way to restore them using available assets
        unitsSoundIndex = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        // TODO it would make more sense to have this in the Unit class
        unitsNames = new String[]{
            "soldier", "archer", "lizard", "wizard", "wisp",
            "spider", "golem", "catapult", "wyvern", "king", "skeleton"};
        // TODO rename to unitsValues_XX
        var_byte_arr_a = new byte[]{2, 0, 2, 0, 6, 5, 3, 0, 1, 5, 4};

        var_byte_arr_arr_arr_arr_a = new byte[][][][]{
            new byte[][][]{new byte[][]{{2}, {3}}, new byte[][]{{0}, {1}}},
            new byte[][][]{new byte[][]{{2, 3}, {2}}, new byte[][]{{0, 1}, {0}}},
            new byte[][][]{new byte[][]{{2}, {3}, {2}}, new byte[][]{{0}, {1}, {0}}},
            new byte[][][]{new byte[][]{{2}, {1, 3, 5, 4}}, new byte[][]{{0}, {1, 3, 5, 4}}},
            new byte[][][]{new byte[][]{{4}, {3, 4}, {5}, {0, 1}}, new byte[][]{{1}, {0, 1}, {2}, {3, 4}}},
            new byte[][][]{new byte[][]{{2}, {3}}, new byte[][]{{0}, {1}}},
            new byte[][][]{new byte[][]{{0, 1, 2, 3}}, new byte[][]{{0, 1, 2, 3}}}};
        attackAnimTable_1 = new byte[][]{{2, 3}, {0, 1}};
        attackAnimTable_2 = new byte[][]{{1}, {0}};
        attackAnimTableKingslash = new byte[][]{{3, 4, 5, 4}, {0, 1, 2, 1}};
        var_byte_arr_c = new byte[]{18, -18};
        var_byte_arr_b = new byte[]{3, -3};
    }
}

