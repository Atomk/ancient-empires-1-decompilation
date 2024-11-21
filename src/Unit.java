/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class Unit
extends SpriteSheet {
    public static final byte SOLDIER = 0;
    public static final byte ARCHER = 1;
    public static final byte LIZARD = 2;
    public static final byte WIZARD = 3;
    public static final byte WISP = 4;
    public static final byte SPIDER = 5;
    public static final byte GOLEM = 6;
    public static final byte CATAPULT = 7;
    public static final byte WYVERN = 8;
    public static final byte KING = 9;
    public static final byte SKELETON = 10;

    // These values are used in the isType method.
    // The golem and skeleton units have bitflag 0, means isType(n)
    // will always return false if used on these units. The King has
    // bitflag 28 (00011100), which means isType(n) will return true
    // when the argument is either 4, 8 (SOLDIER_FLAG) or 16.
    // See the isType method and its calls for more context.
    public static final short SOLDIER_FLAG = 8;
    public static final short ARCHER_FLAG = 64;
    public static final short LIZARD_FLAG = 2;
    public static final short WIZARD_FLAG = 32;
    public static final short WISP_FLAG = 256;
    public static final short SPIDER_FLAG = 128;
    public static final short GOLEM_FLAG = 0;
    public static final short CATAPULT_FLAG = 512;
    public static final short WYVERN_FLAG = 1;
    public static final short KING_FLAG = 28;   // 16 + 8 + 4 => 28 = 00011100
    public static final short SKELETON_FLAG = 0;

    public static final byte STATUS_POISON = 1;
    public static final byte STATUS_AURA = 2;

    // Units sprites are 24x24 (units_icons.png)
    private static final byte TILE_SIZE = 24;

    // TODO this a static reference to the 'i' class, there must be a better solution...
    // TODO since it's only set once, encapsulate within a setStaticIReference() and maki it private
    public static Class_I iClassRef;
    public String customName;
    public short var_short_d;
    public short mapPixelX;
    private short bitflag;
    public int[][] var_int_arr_arr_a;
    //private short j;
    //private short var_short_c;
    private Vector<short[]> var_java_util_Vector_a = null;
    private short var_short_g;
    private long var_long_a;
    public byte unitType;   // TODO could be called just "type"
    public byte owner;
    public short mapX;
    public short mapY;
    public short quantity;
    public byte var_byte_e = 0;
    public byte statusFlags;
    public short k;
    public short var_short_f;
    public short var_short_e;
    private boolean var_boolean_e = false;
    private boolean var_boolean_b = true;
    private int var_int_g;
    private long var_long_c;
    public int var_int_b;
    public static final byte[] unitsDataMOV;
    public static final byte[] unitsDataATK;
    public static final byte[] unitsDataDEF;
    private static final byte[] unitsDataRangeMax;
    private static final byte[] unitsDataRangeMin;
    private static final int[][][] unitsDataArrayOfPairs_XXX;
    public static final short[] unitsDataPrice;
    private static final short[] unitsDataBitflag;

    private Unit(byte unitType, byte owner, int mapX, int mapY) {
        super(iClassRef.a(owner, unitType));
        this.mapX = (short)mapX;
        this.mapY = (short)mapY;
        this.mapPixelX = (short)(mapX * TILE_SIZE);
        ((SpriteSheet)this).l = (short)(mapY * TILE_SIZE);
        this.void_b(mapX * TILE_SIZE, mapY * TILE_SIZE);
        Unit.iClassRef.mapUnitsList.addElement(this);
    }

    public void b(int n) {
        this.var_boolean_e = true;
        this.var_long_c = Unit.iClassRef.var_long_n;
        this.var_int_g = n;
    }

    public static Unit spawn(byte unitType, byte owner, int mapX, int mapY) {
        Unit newUnit = new Unit(unitType, owner, mapX, mapY);
        // TODO isn't this statement redundant? The constructor above should set it
        newUnit.unitType = unitType;
        newUnit.owner = owner;
        newUnit.quantity = (short)10;
        newUnit.bitflag = unitsDataBitflag[unitType];
        newUnit.var_int_arr_arr_a = unitsDataArrayOfPairs_XXX[unitType];
        return newUnit;
    }

    public int a(Unit unit) {
        int n;
        int n2 = unitsDataATK[this.unitType] + this.var_short_f;
        if (this.isType(ARCHER_FLAG) && unit.isType(WYVERN_FLAG)) {
            n2 += 2;
        }
        if (this.unitType == Unit.WISP && unit.unitType == Unit.SKELETON) {
            n2 += 3;
        }
        if ((n = AppCanvas.randomInt() % 20 + this.var_short_d) >= 19) {
            n2 += 2;
        } else if (n >= 16) {
            ++n2;
        } else if (n <= -19) {
            n2 -= 2;
        } else if (n <= -16) {
            --n2;
        }
        int n3 = unitsDataDEF[unit.unitType] + unit.var_short_e;
        n = AppCanvas.randomInt() % 20 + unit.var_short_d;
        if (n >= 19) {
            n3 += 2;
        } else if (n >= 16) {
            ++n3;
        } else if (n <= -19) {
            n3 -= 2;
        } else if (n <= -16) {
            --n3;
        }
        int terrainDEF_XX = iClassRef.getTerrainDefence_XX(iClassRef.getTerrainType_ZZ(unit.mapX, (int)unit.mapY), unit);
        int n5 = (n2 - (terrainDEF_XX + n3) * 2 / 3) * this.quantity / 10;
        if (n5 > unit.quantity) {
            n5 = unit.quantity;
        }
        unit.quantity = (short)(unit.quantity - n5);
        this.mapPixelX = (short)(this.mapPixelX + (unitsDataATK[unit.unitType] + unitsDataDEF[unit.unitType]) * n5);
        return n5;
    }

    public boolean boolean_a() {
        if (this.var_short_d < 3 && this.mapPixelX >= 75 << this.var_short_d) {
            this.mapPixelX = 0;
            this.var_short_d = (short)(this.var_short_d + 1);
            return true;
        }
        return false;
    }

    // TODO the first parameter is unused
    public boolean a(Unit unit, int mapX, int mapY) {
        return this.quantity > 0 && Math.abs(this.mapX - mapX) + Math.abs(this.mapY - mapY) == 1 && unitsDataRangeMin[this.unitType] == 1;
    }

    public void addStatus(byte statusFlag) {
        this.statusFlags = (byte)(this.statusFlags | statusFlag);
        this.d();
    }

    public void removeStatus(byte statusFlag) {
        this.statusFlags = (byte)(this.statusFlags & ~statusFlag);
        this.d();
    }

    public void d() {
        this.k = 0;
        this.var_short_f = 0;
        this.var_short_e = 0;
        if ((this.statusFlags & STATUS_POISON) != 0) {
            this.k--;
            this.var_short_f--;
            this.var_short_e--;
        }
        if ((this.statusFlags & STATUS_AURA) != 0) {
            this.var_short_f++;
        }
    }

    public void setPosition(int mapX, int mapY) {
        this.mapX = (short)mapX;
        this.mapY = (short)mapY;
        this.mapPixelX = (short)(mapX * TILE_SIZE);
        ((SpriteSheet)this).l = (short)(mapY * TILE_SIZE);
    }

    public int int_a() {
        int n = 10 / this.var_int_arr_arr_a.length;
        int n2 = this.quantity / n;
        if (this.quantity != 10 && this.quantity % n > 0) {
            ++n2;
        }
        return n2;
    }

    public int int_a(int n, int n2) {
        return (this.var_short_d + unitsDataATK[this.unitType] + unitsDataDEF[this.unitType] + iClassRef.getTerrainDefence_XX(iClassRef.getTerrainType_ZZ(n, n2), this)) * this.quantity;
    }

    public void a(byte[][] byArray, int n, int n2) {
        int n3;
        int n4;
        int n5;
        byte attackRangeMin = unitsDataRangeMin[this.unitType];
        byte attackRangeMax = unitsDataRangeMax[this.unitType];
        int n6 = n - attackRangeMax;
        if (n6 < 0) {
            n6 = 0;
        }
        if ((n5 = n2 - attackRangeMax) < 0) {
            n5 = 0;
        }
        if ((n4 = n + attackRangeMax) >= Unit.iClassRef.mapTilesWidth) {
            n4 = Unit.iClassRef.mapTilesWidth - 1;
        }
        if ((n3 = n2 + attackRangeMax) >= Unit.iClassRef.mapTilesHeight) {
            n3 = Unit.iClassRef.mapTilesHeight - 1;
        }
        for (int j = n6; j <= n4; ++j) {
            for (int k = n5; k <= n3; ++k) {
                int n7 = Math.abs(j - n) + Math.abs(k - n2);
                if (n7 < attackRangeMin || n7 > attackRangeMax || byArray[j][k] > 0) continue;
                byArray[j][k] = 127;
            }
        }
    }

    public void a(byte[][] byArray) {
        if (this.isType(CATAPULT_FLAG)) {
            this.a(byArray, (int)this.mapX, (int)this.mapY);
            return;
        }
        this.b(byArray);
        for (int j = 0; j < Unit.iClassRef.mapTilesWidth; ++j) {
            for (int k = 0; k < Unit.iClassRef.mapTilesHeight; ++k) {
                if (byArray[j][k] <= 0 || byArray[j][k] == 127) continue;
                this.a(byArray, j, k);
            }
        }
    }

    public Unit[] a(int n, int n2, byte by) {
        return this.a(n, n2, (int)unitsDataRangeMin[this.unitType], (int)unitsDataRangeMax[this.unitType], by);
    }

    private Unit[] a(int areaOriginX, int areaOriginY, int unitRangeMin, int unitRangeMax, byte by) {
        Vector<Unit> vector = new Vector<Unit>();
        final int minX = areaOriginX - unitRangeMax;
        final int minY = areaOriginY - unitRangeMax;
        final int maxX = areaOriginX + unitRangeMax;
        final int maxY = areaOriginY + unitRangeMax;
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                Unit c2;

                // Manhattan distance from unit position (area center) to the current square (x; y)
                final int manhattanDist = Math.abs(x - areaOriginX) + Math.abs(y - areaOriginY);
                if (manhattanDist < unitRangeMin || manhattanDist > unitRangeMax) continue;

                if (by == 0) {
                    c2 = iClassRef.c_a(x, y, (byte)0);
                    if (c2 == null || c2.owner == this.owner) continue;
                    vector.addElement(c2);
                    continue;
                }
                if (by == 1) {
                    c2 = iClassRef.c_a(x, y, (byte)1);
                    if (c2 == null) continue;
                    vector.addElement(c2);
                    continue;
                }
                if (by != 2 || (c2 = iClassRef.c_a(x, y, (byte)0)) == null || c2.owner != this.owner) continue;
                vector.addElement(c2);
            }
        }
        Unit[] unitsArray = new Unit[vector.size()];
        vector.copyInto(unitsArray);
        return unitsArray;
    }

    public void void_a(int n, int n2) {
        //this.j = (short)(n * TILE_SIZE);
        //this.var_short_c = (short)(n2 * TILE_SIZE);
        this.var_java_util_Vector_a = this.a(this.mapX, this.mapY, n, n2);
        this.var_short_g = 0;
        this.var_byte_e = 1;
    }

    public Vector<short[]> a(int mapX, int mapY, int n3, int n4) {
        int n5;
        Vector<short[]> vector = null;
        short[] sArray = new short[]{(short)n3, (short)n4};
        if (mapX == n3 && mapY == n4) {
            vector = new Vector<short[]>();
            vector.addElement(sArray);
            return vector;
        }
        byte by = 0;
        byte by2 = 0;
        byte by3 = 0;
        int n6 = 0;
        if (n4 > 0) {
            by = Unit.iClassRef.mapValues_XX[n3][n4 - 1];
        }
        if (n4 < Unit.iClassRef.mapTilesHeight - 1) {
            by2 = Unit.iClassRef.mapValues_XX[n3][n4 + 1];
        }
        if (n3 > 0) {
            by3 = Unit.iClassRef.mapValues_XX[n3 - 1][n4];
        }
        if (n3 < Unit.iClassRef.mapTilesWidth - 1) {
            n6 = Unit.iClassRef.mapValues_XX[n3 + 1][n4];
        }
        if ((n5 = Math.max(Math.max(by, by2), Math.max(by3, n6))) == by) {
            vector = this.a(mapX, mapY, n3, n4 - 1);
        } else if (n5 == by2) {
            vector = this.a(mapX, mapY, n3, n4 + 1);
        } else if (n5 == by3) {
            vector = this.a(mapX, mapY, n3 - 1, n4);
        } else if (n5 == n6) {
            vector = this.a(mapX, mapY, n3 + 1, n4);
        }
        vector.addElement(sArray);
        return vector;
    }

    public void b(byte[][] byArray) {
        this.a(byArray, (int)this.mapX, (int)this.mapY, unitsDataMOV[this.unitType] + this.k, -1);
    }

    private void a(byte[][] byArray, int mapX, int mapY, int n3, int n4) {
        int n5;
        if (n3 <= byArray[mapX][mapY]) {
            return;
        }
        byArray[mapX][mapY] = (byte)n3;
        if (n4 != 1 && (n5 = n3 - this.int_b(mapX, mapY - 1)) >= 0) {
            this.a(byArray, mapX, mapY - 1, n5, 2);
        }
        if (n4 != 2 && (n5 = n3 - this.int_b(mapX, mapY + 1)) >= 0) {
            this.a(byArray, mapX, mapY + 1, n5, 1);
        }
        if (n4 != 4 && (n5 = n3 - this.int_b(mapX - 1, mapY)) >= 0) {
            this.a(byArray, mapX - 1, mapY, n5, 8);
        }
        if (n4 != 8 && (n5 = n3 - this.int_b(mapX + 1, mapY)) >= 0) {
            this.a(byArray, mapX + 1, mapY, n5, 4);
        }
    }

    private int int_b(int mapX, int mapY) {
        // If coordinates are inside the map
        if (mapX >= 0 && mapY >= 0 && mapX < Unit.iClassRef.mapTilesWidth && mapY < Unit.iClassRef.mapTilesHeight) {
            Unit unit = iClassRef.c_a(mapX, mapY, (byte)0);
            if (unit != null && unit.owner != this.owner) {
                return 1000;
            }
            byte terrainType = iClassRef.getTerrainType_ZZ(mapX, mapY);
            if (this.isType(WYVERN_FLAG)) {
                // Wyvern flies, so terrain type does not affect movement
                return 1;
            }
            if (this.isType(LIZARD_FLAG)) {
                if (terrainType == f.TERRAIN_WATER) {
                    // Lizards are not slowed down by water
                    return 1;
                }
                return Class_I.terrainTypeMovementReduction_XXX[terrainType] * 2;
            }
            return Class_I.terrainTypeMovementReduction_XXX[terrainType];
        }
        return 10000;
    }

    public void void_a() {
        if (this.var_boolean_e) {
            if (Unit.iClassRef.var_long_n - this.var_long_c >= (long)this.var_int_g) {
                this.var_boolean_e = false;
            } else {
                this.var_boolean_b = !this.var_boolean_b;
            }
        }
        if (this.var_byte_e == 1) {
            if (this.var_short_g >= this.var_java_util_Vector_a.size()) {
                this.var_byte_e = 0;
                this.mapX = (short)(this.mapPixelX / TILE_SIZE);
                this.mapY = (short)(((SpriteSheet)this).l / TILE_SIZE);
                this.var_java_util_Vector_a = null;
                this.var_short_g = 0;
                iClassRef.c(this);
            } else {
                short[] sArray = (short[])this.var_java_util_Vector_a.elementAt(this.var_short_g);
                int n = sArray[0] * TILE_SIZE;
                int n2 = sArray[1] * TILE_SIZE;
                if (n < this.mapPixelX) {
                    this.mapPixelX = (short)(this.mapPixelX - 6);
                } else if (n > this.mapPixelX) {
                    this.mapPixelX = (short)(this.mapPixelX + 6);
                } else if (n2 < ((SpriteSheet)this).l) {
                    ((SpriteSheet)this).l = (short)(((SpriteSheet)this).l - 6);
                } else if (n2 > ((SpriteSheet)this).l) {
                    ((SpriteSheet)this).l = (short)(((SpriteSheet)this).l + 6);
                }
                if (this.mapPixelX % TILE_SIZE == 0 && ((SpriteSheet)this).l % TILE_SIZE == 0) {
                    this.var_short_g = (short)(this.var_short_g + 1);
                }
            }
            super.void_b(this.mapPixelX, ((SpriteSheet)this).l);
        } else if (Unit.iClassRef.var_long_n - this.var_long_a >= 200L) {
            this.nextFrame();
            this.var_long_a = Unit.iClassRef.var_long_n;
        }
    }

    public boolean isType(short unitBitflag) {
        // Example: 0001 & 0100 => 0000 
        // This is the clever but less readable alternative to:
        //      return this.unitType == unitTypeParam;
        // TODO unless I missed something, bitflags are unnecessary and can be deleted in favor of unitType
        // Could have been more interesting if it checked both the unit type and owner
        return (this.bitflag & unitBitflag) != 0;
    }

    public void void_b() {
        Unit c2;
        int n;
        this.var_byte_e = (byte)2;
        Unit c3 = iClassRef.c_a((int)this.mapX, (int)this.mapY, (byte)1);
        if (c3 != null) {
            Unit.iClassRef.mapUnitsList.removeElement(c3);
        }
        int unitsCount = Unit.iClassRef.mapUnitsList.size();
        for (n = 0; n < unitsCount; ++n) {
            c2 = Unit.iClassRef.mapUnitsList.elementAt(n);
            if (c2.owner != this.owner) continue;
            c2.removeStatus(Unit.STATUS_AURA);
        }
        unitsCount = Unit.iClassRef.mapUnitsList.size();
        for (n = 0; n < unitsCount; ++n) {
            c2 = Unit.iClassRef.mapUnitsList.elementAt(n);
            if (c2.owner != this.owner || !c2.isType(WISP_FLAG)) continue;
            Unit[] cArray = c2.a(c2.mapX, (int)c2.mapY, 1, 2, (byte)2);
            for (int j = 0; j < cArray.length; ++j) {
                cArray[j].addStatus(Unit.STATUS_AURA);
                iClassRef.a(Unit.iClassRef.sparkSheet, cArray[j].mapPixelX, ((SpriteSheet)cArray[j]).l, 0, 0, 1, 50);
            }
        }
        Unit.iClassRef.var_c_f = this;
    }

    // A bit unnecessary, the value of strongestBuyableUnit is enough
    // Since the array wil always contain numbers between 0 and strongestBuyableUnit
    public static byte[] getBuyableUnitsIndex() {
        byte[] array = new byte[11];
        int buyableUnitsCount = 0;
        for (byte unitType = 0; unitType <= Unit.iClassRef.strongestBuyableUnit; unitType++) {
            // Skips the units you cannot buy (king, skeleton)
            if (unitsDataPrice[unitType] <= 0)
                continue;
            array[buyableUnitsCount++] = unitType;
        }
        // This "trims" the array so that it is the correct length
        // Probably a trick to avoid using a dynamic structure (vector)
        byte[] buyableUnits = new byte[buyableUnitsCount];
        System.arraycopy(array, 0, buyableUnits, 0, buyableUnitsCount);
        return buyableUnits;
    }

    public void a(Graphics graphics, int x, int y) {
        if (this.var_boolean_e) {
            int n3 = this.var_boolean_b ? -2 : 2;
            int n4 = AppCanvas.randomInt() % 1;
            super.a(graphics, x + n3, y + n4);
        } else {
            super.a(graphics, x, y);
        }
    }

    public void b(Graphics graphics, int n, int n2) {
        int screenX = this.mapPixelX + n;
        int screenY = ((SpriteSheet)this).l + n2;
        if (this.var_byte_e != 3) {
            if (this.var_byte_e == 2) {
                // The "E" is shown on units that already moved and cannot perform any more actions in the turn
                AppCanvas.drawBoldWhiteText(graphics, "E", screenX + this.getSpritesWidth() - 7, screenY + this.getSpritesHeight() - 5, AppCanvas.FONT_ALPHANUMERIC);
            }
            // TODO maybe look for "10" and deharcode to STACK_MAX or MAX_QUANTITY
            if (this.quantity < 10) {
                // Shows the unit stack number in its bottom left corner only if there's less than 10 (the max amount)
                AppCanvas.drawBoldWhiteText(graphics, "" + this.quantity, screenX, screenY + this.getSpritesHeight() - 5, AppCanvas.FONT_ALPHANUMERIC);
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
        // Array of bidimensional arrays
        unitsDataArrayOfPairs_XXX = new int[][][]{
            new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}}, 
            new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}},
            new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}},
            new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}},
            new int[][]{{32, 70}, {10, 50}, {10, 90}},  // in Motorola version: {32, 75}, {10, 55}, {10, 95}
            new int[][]{{18, 70}, {3, 40}, {3, 100}},
            new int[][]{{32, 65}, {10, 40}, {10, 85}},
            new int[][]{{5, 40}, {5, 75}},
            new int[][]{{22, 65}, {2, 40}, {2, 90}},
            new int[][]{{5, 58}},
            new int[][]{{32, 55}, {32, 83}, {10, 67}, {10, 98}, {10, 38}}};
        // The last two cannot be bought (king and skeleton)
        unitsDataPrice = new short[]{150, 250, 300, 400, 500, 600, 600, 700, 1000, -1, -1};
        unitsDataBitflag = new short[]{8, 64, 2, 32, 256, 128, 0, 512, 1, 28, 0};
    }
}

