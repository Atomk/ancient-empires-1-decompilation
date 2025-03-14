/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public class a
extends Class_I {
    private static final boolean[] var_boolean_arr_a;
    private Sprite spriteSplashBack;
    private Sprite spriteSplashFront;
    private Sprite spriteCoyright;
    public SpriteSheet[][] miniMapUnitsSheets;
    private int _backY = -15;
    private int _frontY;
    //private int var_int_d = 0;    // Unused
    public boolean var_boolean_g = false;
    private Vector<SpriteSheet> var_java_util_Vector_d;
    public SpriteSheet[][] ssUnitsBattleAnimations;
    public Sprite[][] var_h_arr_arr_a;
    public Sprite[] terrainBackgroundSprites;
    private SpriteSheet[] var_e_arr_a = new SpriteSheet[0];
    public SpriteSheet spriteSheetChimneySmoke;
    private byte[][] _map_XX;

    public a(byte by) throws Exception {
        super(by);
        this.spriteSplashBack = new Sprite("splashbg.png");
        this.spriteSplashFront = new Sprite("splashfg.png");
        this.spriteCoyright = new Sprite("macrospace.png");
        // Start displaying slightly below intended height, to allow "slide from bottom" animation
        this._frontY = AppCanvas.height2 - this.spriteSplashFront.height + 30;
    }

    public void m() throws Exception {
        this.spriteSplashBack = null;
        this.spriteSplashFront = null;
        this.spriteCoyright = null;
        super.m();
        // First index is owner (player index), second index is unit type
        // This probably contains the colored spritesheet for each possible unit
        this.ssUnitsBattleAnimations = new SpriteSheet[2][11];
        this.terrainBackgroundSprites = new Sprite[Class_I.terrainTypeNames.length];
        this.var_h_arr_arr_a = new Sprite[Class_I.terrainTypeNames.length][];

        // This contains the unit sprites used only in the minimap
        // TODO move in the same class as miniMapTerrainTiles
        this.miniMapUnitsSheets = new SpriteSheet[2][11];
        // Spritesheet containing 11 (one for every unit type) 10x10 pixel images in the same row
        byte[] sheetImgBytes = AppCanvas.getFileBytes("unit_icons_s.png");
        for (byte playerIndex = 0; playerIndex < 2; ++playerIndex) {
            byte[] sheetImgBytesCopy = new byte[sheetImgBytes.length];
            System.arraycopy(sheetImgBytes, 0, sheetImgBytesCopy, 0, sheetImgBytes.length);
            // TODO maybe it's not necessary to copy the image bytes since fromByteArray already copies them
            // This line creates a spritesheet with colors matching the player (index 0 = blue, 1 = red)
            Sprite spriteSheetImage = Sprite.fromByteArray(sheetImgBytesCopy, playerIndex);
            for (int unitIndex = 0; unitIndex < 11; ++unitIndex) {
                Sprite sprite = new Sprite(spriteSheetImage, unitIndex, 0, 10, 10);
                // TODO this looks unnecessarily complicates, could probably be an array of sprites
                this.miniMapUnitsSheets[playerIndex][unitIndex] = new SpriteSheet(sprite, 10, 10);
            }
        }

        this.spriteSheetChimneySmoke = new SpriteSheet("b_smoke");
        this.spritePanelDefense = new Sprite("defencepanel.png");
        // Shoun in the battle screen when one of the units on screen dies
        this.spriteSheetSoul = new SpriteSheet("soul");
    }

    public void j() {
        switch (this.splashPhase) {
            case 0: {
                if (this.var_int_m < 15) {
                    ++this.var_int_m;
                }
                if (this.time < 1500L) break;
                this.splashPhase = 1;
                this.var_boolean_c = true;
                this.var_int_m = 0;
                break;
            }
            case 1: {
                if (this.var_int_m >= 15) {
                    AppCanvas.playSound(0, 1);
                    this.spriteMacrospaceLogo = null;
                    this.var_int_m = 0;
                    ++this.splashPhase;
                    break;
                }
                ++this.var_int_m;
                break;
            }
            case 2: {
                if (this._backY < 0) {
                    ++this._backY;
                    this._frontY -= 2;
                    //++this.var_int_d;
                    break;
                }
                this.var_int_m = 0;
                ++this.splashPhase;
                break;
            }
            case 3: {
                if (this.var_int_m < 15) {
                    ++this.var_int_m;
                    break;
                }
                this.var_boolean_p = !this.var_boolean_p;
                if (Class_I.appCanvas.pressedKeysActions == 0 || !this.var_boolean_c || this.var_int_g != 0) break;
                g g2 = new g(this, (byte)0, 0);
                g2.showMenuOptions(this.mainMenuStringsNoSave);
                g2.a((byte)1, AppCanvas.cenX, 67, null, 16);
                Class_I.appCanvas.pressedKeysActions = 0;
            }
        }
    }

    public void b(Graphics graphics) {
        if (this.splashPhase == 0) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, Class_I.appCanvas.width, Class_I.appCanvas.height);
            Class_I.a(graphics, 0, this.var_int_m, 15, 0, this.spriteMacrospaceLogo, (Class_I.appCanvas.width - this.spriteMacrospaceLogo.width) / 2, (Class_I.appCanvas.height - this.spriteMacrospaceLogo.height) / 2, 0, 0);
        } else if (this.splashPhase == 1) {
            Class_I.a(graphics, 0xFFFFFF, this.var_int_m, 15, 0, null, (Class_I.appCanvas.width - this.spriteMacrospaceLogo.width) / 2, (Class_I.appCanvas.height - this.spriteMacrospaceLogo.height) / 2, this.spriteMacrospaceLogo.width, this.spriteMacrospaceLogo.height);
        } else {
            graphics.setColor(108, 93, 72);
            graphics.fillRect(0, 0, Class_I.appCanvas.width, Class_I.appCanvas.height);
            this.spriteSplashBack.draw(graphics, 0, this._backY);
            this.spriteSplashFront.draw(graphics, 0, this._frontY);
            if (this.splashPhase == 3) {
                if (this.var_int_m >= 15) {
                    this.spriteGameTitle.draw(graphics, (Class_I.appCanvas.width - this.spriteGameTitle.width) / 2, 8);
                    graphics.setColor(0xFFFFFF);
                    // TODO I'm pretty sure these two variables control the "press any key" blinking but it's also found in theparent class...
                    if (this.var_boolean_p && this.var_int_g == 0) {
                        graphics.setFont(AppCanvas.fontSmallPlain);
                        // "PRESS ANY KEY"
                        graphics.drawString(AppCanvas.getGameText(25), AppCanvas.cenX, AppCanvas.height2 - AppCanvas.fontSmallPlain.getHeight() - 10, 17);
                    }
                    this.spriteCoyright.draw(graphics, (Class_I.appCanvas.width - this.spriteCoyright.width) / 2, AppCanvas.height2 - this.spriteCoyright.height - 2);
                } else {
                    Class_I.a(graphics, 0xFFFFFF, this.var_int_m, 15, 0, this.spriteGameTitle, (Class_I.appCanvas.width - this.spriteGameTitle.width) / 2, 8, 0, 0);
                    graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                }
            }
        }
    }

    public void loadLevelData(int levelIndex) throws Exception {
        super.loadLevelData(levelIndex);
        this._map_XX = new byte[this.mapTilesWidth][this.mapTilesHeight];
        for (int mapX = 0; mapX < this.mapTilesWidth; ++mapX) {
            for (int mapY = 0; mapY < this.mapTilesHeight; ++mapY) {
                if (this.boolean_a(mapX, mapY)) {
                    this._map_XX[mapX][mapY] = 3;
                    if (mapX > 0 && !this.boolean_a(mapX - 1, mapY)) {
                        this._map_XX[mapX][mapY] += 1;
                    }
                    if (mapX < this.mapTilesWidth - 1 && !this.boolean_a(mapX + 1, mapY)) {
                        this._map_XX[mapX][mapY] += 2;
                    }
                    if (mapY < this.mapTilesHeight - 1 && !this.boolean_a(mapX, mapY + 1)) {
                        this._map_XX[mapX][mapY] += 4;
                    }
                    if (mapY <= 0 || this.boolean_a(mapX, mapY - 1)) continue;

                    this._map_XX[mapX][mapY] += 8;
                    continue;
                }
                this._map_XX[mapX][mapY] = -1;
            }
        }
        this.var_e_arr_a = new SpriteSheet[this.var_byte_arr_arr_e.length];
        for (int n2 = 0; n2 < this.var_byte_arr_arr_e.length; ++n2) {
            if (this.getTerrainType(this.var_byte_arr_arr_e[n2][0], this.var_byte_arr_arr_e[n2][1]) != f.TERRAIN_TOWN) continue;
            this.var_e_arr_a[n2] = SpriteSheet.a(this.spriteSheetChimneySmoke, 0, -1, 1, 250, (byte)0);
            this.var_e_arr_a[n2].var_boolean_d = false;
        }
    }

    private boolean boolean_a(int mapX, int mapY) {
        return var_boolean_arr_a[this.getTerrainType(mapX, mapY)];
    }

    public void e() throws Exception {
        if (this.var_byte_i == 0) {
            for (int j = 0; j < this.var_e_arr_a.length; ++j) {
                if (this.var_e_arr_a[j] == null || this.int_a((int)this.var_byte_arr_arr_e[j][0], (int)this.var_byte_arr_arr_e[j][1]) == -1 || this.var_e_arr_a[j].var_boolean_d || AppCanvas.randomInt() % 8 != 0) continue;
                this.var_e_arr_a[j].var_boolean_d = true;
                this.var_e_arr_a[j].var_int_c = 1;
                this.var_e_arr_a[j].setMapPixelCoords((this.var_byte_arr_arr_e[j][0] + 1) * 24 - this.spriteSheetChimneySmoke.getSpritesWidth(), this.var_byte_arr_arr_e[j][1] * 24 - 2);
                this.var_java_util_Vector_f.addElement(this.var_e_arr_a[j]);
            }
        }
        super.e();
    }

    // TODO Probably overrides the parent class method (that is pretty much the sam)
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
            s3++;
        }
        if (AppCanvas.height2 % 24 != 0) {
            s4++;
        }
        if (s3 >= this.mapTilesWidth) {
            s3 = (short)(this.mapTilesWidth - 1);
        }
        if (s4 >= this.mapTilesHeight) {
            s4 = (short)(this.mapTilesHeight - 1);
        }
        int n = this.var_short_f < 0 ? this.var_short_f % 24 : this.var_short_f;
        int y = this.var_short_a < 0 ? this.var_short_a % 24 : this.var_short_a;
        if (this.useRedAreaBorder) {
            graphics.setColor(this.colorRangeBorder & 0xFF0000);
        } else {
            graphics.setColor(this.colorRangeBorder);
        }
        for (short mapY = s2; mapY <= s4; mapY++) {
            int x = n;
            for (short mapX = s; mapX <= s3; mapX++) {
                byte by = this._map_XX[mapX][mapY];
                if (by > 0) {
                    this.var_h_arr_c[by].draw(graphics, x, y);
                }
                byte tileIndex = this.mapTerrain[mapX][mapY];
                if (this.tileIdToTerrainType[tileIndex] != f.TERRAIN_GRASS) {
                    this.var_h_arr_c[tileIndex].draw(graphics, x, y);
                    if (this.tileIdToTerrainType[tileIndex] == f.TERRAIN_CASTLE) {
                        this.var_h_arr_c[tileIndex + 1].draw(graphics, x, y - 24);
                    }
                }
                if (this.drawAreaBorder && this.unitActionsMatrix[mapX][mapY] > 0) {
                    if (mapX > 0 && this.unitActionsMatrix[mapX - 1][mapY] <= 0) {
                        graphics.fillRect(x, y, 4, 24);
                    }
                    if (mapX < this.mapTilesWidth - 1 && this.unitActionsMatrix[mapX + 1][mapY] <= 0) {
                        graphics.fillRect(x + 24 - 4, y, 4, 24);
                    }
                    if (mapY > 0 && this.unitActionsMatrix[mapX][mapY - 1] <= 0) {
                        graphics.fillRect(x, y, 24, 4);
                    }
                    if (mapY < this.mapTilesHeight - 1 && this.unitActionsMatrix[mapX][mapY + 1] <= 0) {
                        graphics.fillRect(x, y + 24 - 4, 24, 4);
                    }
                }
                x += 24;
            }
            y += 24;
        }
    }

    public void a(Unit attacker, Unit defender) throws Exception {
        this.var_byte_d = (byte)2;
        this.var_java_util_Vector_d = this.var_java_util_Vector_c;
        this.var_java_util_Vector_c = new Vector<SpriteSheet>();
        this.var_boolean_e = true;
        this.var_int_m = 0;
        this.var_boolean_k = false;
        this.var_c_i = attacker;
        this.var_c_b = defender;
        this.var_f_b = new f(this, attacker, true);
        this.var_f_b.var_f_a = this.var_f_a = new f(this, defender, false);
        this.var_f_a.var_f_a = this.var_f_b;
        attacker.attack(defender);
        if (defender.canCounterattackMelee(attacker, (int)attacker.mapX, (int)attacker.mapY)) {
            defender.attack(attacker);
            this.defenderDidCounterattack = true;
        } else {
            this.defenderDidCounterattack = false;
        }
        this.var_f_b.unitQuantityAfterBattle = (byte)attacker.quantity;
        this.var_f_b.var_byte_d = (byte)attacker.int_a();
        this.var_f_a.unitQuantityAfterBattle = (byte)defender.quantity;
        this.var_f_a.var_byte_d = (byte)defender.int_a();
    }

    public void b() throws Exception {
        if (this.var_boolean_e) {
            ++this.var_int_m;
            Class_I.appCanvas.repaint();
            Class_I.appCanvas.serviceRepaints();
            if (this.var_int_m >= 15) {
                this.var_boolean_e = false;
            }
            return;
        }
        if (this.var_boolean_q && this.time - this.var_long_b >= this.var_long_m) {
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
            if (this.time - this.var_long_f >= 500L) {
                // Calls to empty method
                // this.var_f_a.soundMethod();
                // this.var_f_b.soundMethod();
                this.var_f_a = null;
                this.var_f_b = null;
                this.var_java_util_Vector_c = this.var_java_util_Vector_d;
                this.var_java_util_Vector_d = null;
                this.o();
                this.var_byte_d = 1;
                return;
            }
        } else if (this.var_f_b.var_boolean_f) {
            if (this.defenderDidCounterattack && this.var_f_a.unitQuantityAfterBattle > 0) {
                if (!this.var_f_a.var_boolean_e) {
                    this.var_f_a.b();
                }
                if (this.var_f_a.var_boolean_f) {
                    this.var_boolean_k = true;
                    this.var_long_f = this.time;
                }
            } else {
                this.var_boolean_k = true;
                this.var_long_f = this.time;
            }
        }
        Class_I.appCanvas.repaint();
        Class_I.appCanvas.serviceRepaints();
    }

    static {
        // TODO nine elements, like terrain types...
        // "road", "grass", "woods", "mountain", "mountain", "water", "bridge", "town", "town"
        var_boolean_arr_a = new boolean[]{false, true, true, true, true, false, false, true, true};
    }
}

