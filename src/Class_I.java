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
    public static final byte PORTRAIT_VALADORN = 1;     // Valadorn (the main antagonist in the campaign, Galamar's brother)
    public static final byte PORTRAIT_CAPTAIN = 2;      // Galamar's troops captain

    public static final byte ARROW_UP = 0;
    public static final byte ARROW_DOWN = 1;

    public static final byte PLAYER_BLUE = 0;
    public static final byte PLAYER_RED = 1;
    /** Used just to generate the images for buildings without owner. */
    public static final byte PLAYER_NEUTRAL = 2;

    private static final byte LEVEL_TYPE_CAMPAIGN = 0;
    private static final byte LEVEL_TYPE_SKIRMISH = 1;

    private static final byte CAMPAIGN_0_REGROUP = 0;
    private static final byte CAMPAIGN_1_FRIENDS_ENEMIES = 1;
    private static final byte CAMPAIGN_2_ESCORT = 2;
    private static final byte CAMPAIGN_3_REINFORCEMENTS = 3;
    private static final byte CAMPAIGN_4_WYVERN_RESCUE = 4;
    private static final byte CAMPAIGN_5_SIEGE = 5;
    private static final byte CAMPAIGN_6_FINAL_ASSAULT = 6;

    private byte[] levelsData = new byte[1];
    public static AppCanvas appCanvas;
    private static final String[] skirmishMapNames;
    private byte levelType;
    /** Main menu items without the "save game" option */
    public String[] mainMenuStringsNoSave = new String[]{
        AppCanvas.getGameText(1),  // NEW GAME
        AppCanvas.getGameText(2),  // SELECT LEVEL
        AppCanvas.getGameText(4),  // LOAD GAME
        AppCanvas.getGameText(5),  // SKIRMISH
        AppCanvas.getGameText(6),  // SETTINGS
        AppCanvas.getGameText(7),  // INSTRUCTIONS
        AppCanvas.getGameText(8),  // ABOUT
        AppCanvas.getGameText(9)}; // EXIT
    // Same as above, adds SAVE GAME as first element
    private String[] _mainMenuOptionsWithSave = new String[] {
        AppCanvas.getGameText(3),       // SAVE GAME
        this.mainMenuStringsNoSave[0],    // NEW GAME
        this.mainMenuStringsNoSave[1],     // ...
        this.mainMenuStringsNoSave[2],
        this.mainMenuStringsNoSave[3],
        this.mainMenuStringsNoSave[4],
        this.mainMenuStringsNoSave[5],
        this.mainMenuStringsNoSave[6],
        this.mainMenuStringsNoSave[7]
    };
    private static final byte[][] mapSheetReorderTable;
    private static final byte[] statusPoisonReorderTable;
    private static final byte[] statusStarReorderTable;
    private long var_long_k = 0L;
    private long var_long_a;
    private boolean var_boolean_s = false;
    public static final int[] playerColors;
    public SpriteSheet[][] var_e_arr_arr_b;
    private static final byte[] waterTilesIndex;
    public static final byte[] terrainTypeDefense;
    public static final byte[] terrainType_XXX;
    public static final String[] terrainTypeNames;
    /** The cost of traversing a specific terrain type. */
    public static final byte[] terrainMovCost;
    // TODO: firstBuildingTileIndex or terreinTilesCount
    public int var_int_t;
    public Sprite[] miniMapTerrainTiles;
    /** Conversion table that takes a tile index and gives the corresponding terrain type.
     * (e.g. there are many grass tiles with different borders, but they have the same GRASS terrain type) */
    protected byte[] tileIdToTerrainType;
    private short _mapPixelsWidth;
    private short _mapPixelsHeight;
    public short var_short_f;
    public short var_short_a;
    public short mapTilesWidth;
    public short mapTilesHeight;
    private Sprite spriteTombstone;
    public Sprite[] var_h_arr_c;
    public Sprite spriteMenuPointer;
    public Sprite spriteGold;
    public SpriteSheet mapCursorSheet;
    private SpriteSheet mapCursorMoveUnit;
    public SpriteSheet uiArrowSheet;
    public SpriteSheet uiPanelFrameSheet;
    public SpriteSheet uiBtnIconSheet;
    /** Shown on map when a unit dies, just after the blue spark animation. */
    private SpriteSheet mapUnitExplosionSheet;
    /** Blue spark animation shown:
     * - on map when a unit dies, before the explosion - https://youtu.be/6MTmxnNygSw?t=568
     * - when a unit levels up (gets a star)
     * - wisp attack FX in battle screen */
    public SpriteSheet blueSparkSheet;
    /** "Explosion" FX in fight screen when a unit dies. */
    public SpriteSheet redsparkSheet;
    public SpriteSheet uiStatusSheet;
    public static final byte STATUS_SHEET_POISON = 0;
    public static final byte STATUS_SHEET_AURA = 1;
    public static final byte STATUS_SHEET_STAR = 2;
    /** Campaign characters heads, shown in story dialogues. */
    public SpriteSheet uiPortraitSheet;
    public short mapCursorX;
    public short mapCursorY;
    // TODO rename to mapTiles
    public byte[][] mapTerrain;
    // TODO if == 1 means "unit was selected"
    public byte var_byte_i;
    public byte var_byte_e;
    /** Time since game start, in milliseconds */
    public long time;
    private int currentLevel;
    public int var_int_h;
    public int var_int_w;
    /** Units that can be attacked, or tombsones that can be raised by aunit TODO which unit? */
    private Unit[] targetableUnits_XX = null;
    private Unit var_c_h = null;// TODO probably "_selectedUnit"
    public int var_int_c;
    public int var_int_v;
    /** Stores pathfinding data and/or tiles that can be attacked
     * by a unit in a certain moment. Has the same sizes as the map.
     * The cell value can be either:
     * • 0: nothing to do here
     * • 127: the unit can attack this tile (melee or ranged)
     * • other value: movement points left after the unit reaches that location
     */
    public byte[][] unitActionsMatrix;
    /** Determines whether to draw the movement/attack area border. */
    protected boolean drawAreaBorder = false;
    /** True when the action area must be displayed with red lines (for attack range or "raise" range) */
    protected boolean useRedAreaBorder = false;
    public boolean var_boolean_n = true;
    public Vector<Unit> mapUnitsList = new Vector<Unit>();
    public int var_int_g = 0;
    public boolean var_boolean_u = true;
    public boolean B = true;
    private Vector<short[]> _pathSteps = null;
    public int H;
    public int var_int_k;
    public long var_long_c;
    private byte levelPlayersCount = (byte)2;
    private final byte[] players = new byte[]{PLAYER_BLUE, PLAYER_RED};
    public byte currentPlayerIndex_XX;  // TODO is there any difference between this and the field below?
    public byte currentPlayer = PLAYER_BLUE;
    private short _turnIndex;
    /** References the king for each player, use the player ID as index. */
    private Unit[] _mapKings;
    public int[] playersMoney;
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
    protected int splashPhase;
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
    private int _requestedTutorialIndex = -1;
    /** Automatically set by the UI class, used for timed alerts and showing campaign backstory. */
    public g timedInfobox;
    private g _panelMapObjective;
    /** The strongest unit that the map/level allows to buy. */
    public int strongestAllowedUnitType = Unit.WYVERN;
    public g var_g_a;
    public g var_g_d;
    public g var_g_f;
    public boolean var_boolean_d = false;
    public int var_int_r;
    // TODO buildings (key is index, value is array [mapX, mapY, ???])
    public byte[][] var_byte_arr_arr_e;
    private static final String[] var_java_lang_String_arr_d;
    public StringBuffer var_java_lang_StringBuffer_a = new StringBuffer();
    public int D;
    public Unit var_c_d = null;
    /** Will pulsate between white and gray. Used for drawing movement area and attack range area. */
    protected int colorRangeBorder = 0;
    private int colorRangeBorderIncrement = -15790321;
    public int C = 6;
    public int G = this.C >> 1;
    private int K;  // TODO something to do with king position
    private int var_int_u; // TODO something to do with king position
    public byte var_byte_b = 0;
    public int var_int_f;
    public int var_int_x;
    public Unit var_c_g;
    public Unit var_c_a;
    public int var_int_A = 0;
    public long var_long_j;
    // TODO thisis probably aiSoldiers, and is used only in two connected ethods so it can probably be a local variable passed as parameter unles this is intentional
    private Unit[] var_c_arr_c;
    // TODO used only in two connected ethods so it can probably be a local variable passed as parameter unles this is intentional
    private int var_int_z;
    public int var_int_o;
    /** Unit that ended its turn in this update. */
    public Unit unitWhoseTurnEnded = null;
    /** Set to -1 on mission complete. */
    private short currentLevelStep = 0;
    private long _waitStartTime;
    private int _waitDuration;
    /** A panel showing a dialogue or the background story for the current campaign level. */
    public g storyPanel;
    private boolean _waitRequested = false;
    public boolean var_boolean_o = false;
    public boolean var_boolean_w = false;
    private int _cameraTargetMapX = -1;
    private int _cameraTargetMapY = -1;
    public boolean var_boolean_A = true;
    public boolean var_boolean_i = true;
    public f var_f_b;
    public f var_f_a;
    public long var_long_f;
    public boolean var_boolean_k;
    public SpriteSheet spriteSheetSoul;
    public Sprite spritePanelDefense;
    public SpriteSheet ssBattleFxWyvernFireball;
    public SpriteSheet ssBattleFxKingWave;
    public SpriteSheet ssBattleFxCatapultStone;
    public SpriteSheet ssBattleFxSwordSlash;
    public SpriteSheet var_e_f;
    public SpriteSheet ssBattleFxSpiderSpit;
    public boolean var_boolean_q = false;
    public long var_long_m;
    public long var_long_b;
    protected boolean defenderDidCounterattack;
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
            this.splashPhase = 0;
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
        AppCanvas.stopFirstSound();
        AppCanvas.releaseSoundResources(0);
        if (this.var_byte_d == 1) {
            return;
        }
        this.var_byte_d = 1;
        Unit.game = this;

        //AppCanvas.readAssetsPackage("/1.pak");
        this.mapCursorSheet = new SpriteSheet("cursor");
        this.spriteGold = new Sprite("gold.png");
        this.uiPortraitSheet = new SpriteSheet("portrait");
        this.redsparkSheet = new SpriteSheet("redspark");
        this.mapUnitExplosionSheet = new SpriteSheet("smoke");
        this.blueSparkSheet = new SpriteSheet("spark");
        this.uiStatusSheet = new SpriteSheet("status");
        this.spriteTombstone = new Sprite("tombstone.png");
        this.mapCursorSheet.setReorderTable(mapSheetReorderTable[0]); // frames 0 and 1 (tile selector anim)
        this.mapCursorMoveUnit = new SpriteSheet(this.mapCursorSheet);
        this.mapCursorMoveUnit.setReorderTable(mapSheetReorderTable[3]); // frame 4 (movement destination sprite)

        // TODO deharcode and document like you did below for buildings sprites
        this.var_e_arr_arr_b = new SpriteSheet[2][11];
        byte[] byArray = AppCanvas.getFileBytes("unit_icons.png");
        for (byte playerIndex = 0; playerIndex < 2; playerIndex++) {
            byte[] imageBytesObj = new byte[byArray.length];
            System.arraycopy(byArray, 0, imageBytesObj, 0, byArray.length);
            Sprite object = Sprite.fromByteArray(imageBytesObj, playerIndex);
            for (byte unitType = 0; unitType < 11; unitType++) {
                this.var_e_arr_arr_b[playerIndex][unitType] = new SpriteSheet(new Sprite(object, unitType, 0, 24, object.height), 24, 24);
            }
        }

        // TThis is the only time the game reads a ".prop" file
        InputStream inputStream = AppCanvas.getFileBytesInputStream("tiles0.prop");
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        short numTerrainTiles = dataInputStream.readShort();
        /*short s3 = */dataInputStream.readShort();   // Unused variable
        this.tileIdToTerrainType = new byte[numTerrainTiles];
        for (short i = 0; i < numTerrainTiles; i++) {
            this.tileIdToTerrainType[i] = dataInputStream.readByte();
        }
        // TODO should close inputStream and dataInputStream...

        SpriteSheet tempSpritesheet = new SpriteSheet("tiles0");
        Sprite[] hArray = tempSpritesheet.sprites;
        this.var_int_t = hArray.length;
        tempSpritesheet = null;

        byte[] imgBuildingsBytes = AppCanvas.getFileBytes("buildings.png");
        // The "buildings" image contains 3 tiles for the BLUE building, the code creates other 2 copies for red and neutral player
        Sprite[] buildingsSpritesArr = new Sprite[9];
        for (byte playerIndex = 0; playerIndex <= PLAYER_NEUTRAL; playerIndex++) {
            byte[] imgCopy = new byte[imgBuildingsBytes.length];
            System.arraycopy(imgBuildingsBytes, 0, imgCopy, 0, imgBuildingsBytes.length);
            Sprite buildingsTilesetWithPlayerColor = Sprite.fromByteArray(imgCopy, playerIndex);
            for (short tileIndex = 0; tileIndex < 3; tileIndex++) {
                // Each item of this array will be a single tile from the buildings tileset, colored for a specific player.
                // blue_ton, blue_castle, blue_castle_top, red_town, red_castle, red_castle_top, neutral_tow, neutral_castle, neutral_castle_topn
                buildingsSpritesArr[playerIndex * 3 + tileIndex] = new Sprite(buildingsTilesetWithPlayerColor, tileIndex, 0, 24, 24);
            }
        }
        this.var_h_arr_c = new Sprite[hArray.length + buildingsSpritesArr.length];
        System.arraycopy(hArray, 0, this.var_h_arr_c, 0, hArray.length);
        System.arraycopy(buildingsSpritesArr, buildingsSpritesArr.length - 3, this.var_h_arr_c, hArray.length, 3);
        System.arraycopy(buildingsSpritesArr, 0, this.var_h_arr_c, hArray.length + 3, buildingsSpritesArr.length - 3);

        // 10x10 tiles used in the minimap
        // This set also contains sprites for neutral and red buildings,
        // while all other sheets contain sprites only for the blue player
        tempSpritesheet = new SpriteSheet("stiles0");
        this.miniMapTerrainTiles = tempSpritesheet.sprites;
        tempSpritesheet = null;

        this.var_h_arr_a = new Sprite[2];
        this.var_int_l = waterTilesIndex[0];
        this.var_h_arr_a[0] = this.var_h_arr_c[waterTilesIndex[0]];
        this.var_h_arr_a[1] = this.var_h_arr_c[waterTilesIndex[1]];
        //AppCanvas.e();
        this.var_g_c = new g(this, (byte)3, 9);
        this.var_g_g = new g(this, (byte)5, 10);
    }

    public void d(int gameActionCode) {
        this.var_boolean_s = true;
        if (this.levelType == LEVEL_TYPE_CAMPAIGN && this.var_byte_d == 1 && this.var_byte_i == 0) {
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
                    this.playersMoney[this.currentPlayer] += 1000;
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

    //public void n() { }   // unused

    public byte[] getGameSaveData() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeByte(this.levelType);
        dataOutputStream.writeByte(this.currentLevel);
        dataOutputStream.writeByte(this.levelPlayersCount);
        dataOutputStream.writeByte(this.currentPlayerIndex_XX);
        dataOutputStream.writeShort(this._turnIndex);
        dataOutputStream.writeByte(this.strongestAllowedUnitType);
        for (int i = 0; i < this.levelPlayersCount; ++i) {
            dataOutputStream.writeByte(this.var_byte_arr_b[i]);
            dataOutputStream.writeShort(this.playersMoney[i]);
        }
        for (int i = 0; i < this.var_byte_arr_arr_e.length; ++i) {
            if (this.mapTerrain[this.var_byte_arr_arr_e[i][0]][this.var_byte_arr_arr_e[i][1]] < this.var_int_t) continue;
            dataOutputStream.writeByte(this.mapTerrain[this.var_byte_arr_arr_e[i][0]][this.var_byte_arr_arr_e[i][1]]);
        }
        dataOutputStream.writeByte(this.mapUnitsList.size());
        int unitsCount = this.mapUnitsList.size();
        for (int i = 0; i < unitsCount; ++i) {
            Unit unit = this.mapUnitsList.elementAt(i);
            dataOutputStream.writeByte(unit.unitType);
            dataOutputStream.writeByte(unit.owner);
            dataOutputStream.writeByte(unit.state);
            dataOutputStream.writeByte(unit.statusFlags);
            dataOutputStream.writeByte(unit.quantity);
            dataOutputStream.writeByte(unit.stars);
            dataOutputStream.writeShort(unit.xp);
            dataOutputStream.writeShort(unit.mapX);
            dataOutputStream.writeShort(unit.mapY);
            dataOutputStream.writeShort(unit.turnOfDeath);
        }
        dataOutputStream.writeShort(this.currentLevelStep);
        // TODO why would you save wait time? I don't think you can even save the game while you are waiting
        dataOutputStream.writeInt((short)this._waitStartTime);
        dataOutputStream.writeInt(this._waitDuration);
        dataOutputStream.writeByte(this._waitRequested ? 0 : 1);
        byte[] byArray = byteArrayOutputStream.toByteArray();
        dataOutputStream.close();
        return byArray;
    }

    private void loadSavedGameData(byte[] savedGameData) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(savedGameData);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        this.levelType = dataInputStream.readByte();
        this.currentLevel = dataInputStream.readByte();
        this.levelPlayersCount = dataInputStream.readByte();
        this.loadLevelData(this.currentLevel);
        this.currentPlayerIndex_XX = dataInputStream.readByte();
        this.currentPlayer = this.players[this.currentPlayerIndex_XX];
        this._turnIndex = dataInputStream.readShort();
        this.strongestAllowedUnitType = dataInputStream.readByte();
        for (int i = 0; i < this.levelPlayersCount; ++i) {
            this.var_byte_arr_b[i] = dataInputStream.readByte();
            this.playersMoney[i] = dataInputStream.readShort();
        }
        for (int i = 0; i < this.var_byte_arr_arr_e.length; ++i) {
            if (this.mapTerrain[this.var_byte_arr_arr_e[i][0]][this.var_byte_arr_arr_e[i][1]] < this.var_int_t)
                continue;
            this.mapTerrain[this.var_byte_arr_arr_e[i][0]][this.var_byte_arr_arr_e[i][1]] = dataInputStream.readByte();
        }
        this.mapUnitsList = new Vector<Unit>();
        int unitsCount = dataInputStream.readByte();
        for (int i = 0; i < unitsCount; ++i) {
            byte unitType = dataInputStream.readByte();
            byte unitOwner = dataInputStream.readByte();
            byte unitState = dataInputStream.readByte();
            byte unitStatusFlags = dataInputStream.readByte();
            byte unitQuantity = dataInputStream.readByte();
            byte unitStars = dataInputStream.readByte();
            short unitXP = dataInputStream.readShort();
            short unitPosX = dataInputStream.readShort();
            short unitPosY = dataInputStream.readShort();
            short unitTurnOfDeath = dataInputStream.readShort();
            Unit unit = Unit.spawn(unitType, unitOwner, unitPosX, unitPosY);
            unit.state = unitState;
            unit.xp = unitXP;
            unit.stars = unitStars;
            unit.statusFlags = unitStatusFlags;
            unit.updateStatusModifiers();
            unit.quantity = unitQuantity;
            unit.turnOfDeath = unitTurnOfDeath;

            if (unitType == Unit.KING) {
                this._mapKings[unitOwner] = unit;
            }

            if (this.levelType != LEVEL_TYPE_CAMPAIGN)
                continue;

            if (unitType == Unit.KING) {
                if (unitOwner == PLAYER_BLUE) {
                    unit.customName = AppCanvas.getGameText(43); // GALAMAR
                    continue;
                }

                // ??? it shouldn't be possible to have more than 3 stars
                if (unitStars == 4)
                    continue;

                unit.customName = AppCanvas.getGameText(44); // VALADORN
                continue;
            }

            if (this.currentLevel != CAMPAIGN_2_ESCORT || unitOwner != PLAYER_BLUE || unitType != Unit.LIZARD)
                continue;

            unit.customName = AppCanvas.getGameText(45); // LIZARD CHIEF
        }

        if (this.currentLevel == CAMPAIGN_2_ESCORT) {
            for (int i = 5; i < 10; ++i) {
                this.mapTerrain[i][12] = waterTilesIndex[0];
            }
        }

        this.currentLevelStep = dataInputStream.readShort();
        this._waitStartTime = dataInputStream.readInt();
        this._waitDuration = dataInputStream.readInt();
        this._waitRequested = dataInputStream.readByte() != 0;
        dataInputStream.close();
        this.setMapCursorTo(this._mapKings[this.currentPlayer].mapX, this._mapKings[this.currentPlayer].mapY);
        this.void_a(this._mapKings[this.currentPlayer].mapPixelX, (int)((SpriteSheet)this._mapKings[this.currentPlayer]).l);
    }

    public static void loadSettingsData() {
        try {
            byte[] settingsData = appCanvas.loadPersistentData("settings");
            for (int i = 0; i < AppCanvas.settings.length; ++i) {
                AppCanvas.settings[i] = (settingsData[0] & 1 << i) != 0;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void updateSettings() {
        try {
            boolean valuesChanged = false;
            for (int i = 0; i < AppCanvas.settings.length; ++i) {
                boolean isChoiceSelected = this.choiceGroupSettings.isSelected(i);
                if (isChoiceSelected == AppCanvas.settings[i]) continue;
                AppCanvas.settings[i] = isChoiceSelected;
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
            this.mapCursorSheet.setReorderTable(mapSheetReorderTable[0]);
        }
        this.var_c_i = c2;
        this.var_c_b = c3;
    }

    // TODO handlePostCombat
    public void o() {
        SpriteSheet e2;
        if (this.var_c_i.quantity <= 0) {
            this.var_c_c = this.var_c_i;
            AppCanvas.playSound(3, 1);
        } else if (this.var_c_i.tryLevelUp()) {
            e2 = this.a(this.uiStatusSheet, this.var_c_i.mapPixelX + 3, ((SpriteSheet)this.var_c_i).l + 3, 0, 0, 1, 800);
            e2.setReorderTable(statusStarReorderTable);
            this.a(this.blueSparkSheet, this.var_c_i.mapPixelX, ((SpriteSheet)this.var_c_i).l, 0, 0, 1, 50);
        }
        if (this.var_c_b.quantity <= 0) {
            this.var_c_c = this.var_c_b;
            AppCanvas.playSound(3, 1);
        } else if (this.var_c_i.isType(Unit.SPIDER_FLAG)) {
            e2 = this.a(this.uiStatusSheet, this.var_c_b.mapPixelX + 4, ((SpriteSheet)this.var_c_b).l + 3, 0, 0, 1, 800);
            e2.setReorderTable(statusPoisonReorderTable);
            this.a(this.blueSparkSheet, this.var_c_b.mapPixelX, ((SpriteSheet)this.var_c_b).l, 0, 0, 1, 50);
            this.var_c_b.addStatus(Unit.STATUS_POISON);
        } else if (this.var_c_b.tryLevelUp()) {
            e2 = this.a(this.uiStatusSheet, this.var_c_b.mapPixelX + 3, ((SpriteSheet)this.var_c_b).l + 3, 0, 0, 1, 800);
            e2.setReorderTable(statusStarReorderTable);
            this.a(this.blueSparkSheet, this.var_c_b.mapPixelX, ((SpriteSheet)this.var_c_b).l, 0, 0, 1, 50);
            AppCanvas.playSound(-1, 1);
        }
        if (this.var_c_c != null) {
            this.a(this.blueSparkSheet, this.var_c_c.mapPixelX, ((SpriteSheet)this.var_c_c).l, 0, 0, 1, 50);
        }
        this.var_long_g = this.time;
        if (this.var_byte_arr_b[this.currentPlayerIndex_XX] == 0) {
            this.var_long_j = this.time;
            this.var_byte_b = (byte)6;
        }
        this.mapCursorSheet.setReorderTable(mapSheetReorderTable[0]);
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

    public SpriteSheet a(SpriteSheet sheet, int mapPixelX, int mapPixelY, int n3, int n4, int n5, int n6) {
        SpriteSheet e3 = SpriteSheet.a(sheet, n3, n4, n5, n6, (byte)0);
        e3.setMapPixelCoords(mapPixelX, mapPixelY);
        this.var_java_util_Vector_f.addElement(e3);
        return e3;
    }

    public void a(Unit unit) {
        this.var_boolean_t = true;
        this.var_boolean_r = !this.var_boolean_d;
        this.colorRangeBorder = 0xFFFFFFF;
        this.var_byte_i = 1;
        this.var_boolean_v = true;
        this.fillMatrixWithValue_XX(this.unitActionsMatrix, 0);
        unit.updatePathfindData(this.unitActionsMatrix);
        this.drawAreaBorder = true;
        this.useRedAreaBorder = false;
        this.mapCursorSheet.setReorderTable(mapSheetReorderTable[2]);
    }

    public void c() {
        if (this.var_byte_i == 3) {
            this.var_c_h.setPosition(this.var_int_c, this.var_int_v);
            this.var_c_h.updatePathfindData(this.unitActionsMatrix);
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
                this.currentLevel = CAMPAIGN_0_REGROUP;
            }
            this.levelType = LEVEL_TYPE_CAMPAIGN;
            this.var_byte_arr_b[1] = 0;
            this.spriteGameTitle = null;
            this.spriteMacrospaceLogo = null;
            this.var_boolean_l = true;
            appCanvas.repaint();
            appCanvas.serviceRepaints();
            this.m();
            this.loadLevelData(this.currentLevel);
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
            this.var_g_f.a((byte)1, AppCanvas.cenX, AppCanvas.cenY, g2, 48);
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
                if (this.levelType == LEVEL_TYPE_CAMPAIGN) {
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
            this.var_g_a.a((byte)1, AppCanvas.cenX, AppCanvas.cenY, g2, 48);
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
            this.var_g_d.a((byte)1, AppCanvas.cenX, AppCanvas.cenY, g2, 48);
        } else if (g2 == this.var_g_d) {
            this.var_byte_arr_b[1] = n == 0 ? (byte)0 : 1;
            this.levelType = LEVEL_TYPE_SKIRMISH;
            this.strongestAllowedUnitType = Unit.WYVERN;
            this.var_boolean_l = true;
            appCanvas.repaint();
            appCanvas.serviceRepaints();
            this.m();
            this.loadLevelData(this.var_int_h);
            this.currentLevel = this.var_int_h;
            this._panelMapObjective.a((byte)0, 0, 0, null, 0);
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
                g4.showMenuOptions(this._mainMenuOptionsWithSave);
                g4.a((byte)1, AppCanvas.cenX, AppCanvas.cenY, g2, 48);
            } else if (string.equals(AppCanvas.getGameText(27))) {  // MOVE
                this.var_boolean_d = false;
                this.a(this.var_c_h);
            } else if (string.equals(AppCanvas.getGameText(28))) {  // ATTACK
                this.fillMatrixWithValue_XX(this.unitActionsMatrix, 0);
                this.var_byte_e = this.var_byte_i;
                this.var_byte_i = (byte)6;
                this.var_boolean_v = true;
                this.targetableUnits_XX = this.var_c_h.searchInAttackRange(this.var_c_h.mapX, this.var_c_h.mapY, Unit.FILTER_ENEMy);
                this.var_int_w = 0;
                this.drawAreaBorder = true;
                this.useRedAreaBorder = true;
                this.var_c_h.updateAttackMatrix_XX(this.unitActionsMatrix, (int)this.var_c_h.mapX, (int)this.var_c_h.mapY);
                this.mapCursorSheet.setReorderTable(mapSheetReorderTable[1]);
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
                g.a(this, null, AppCanvas.getGameText(39), 1000, true); // TURN END
                this.var_byte_i = (byte)8;
                this.var_long_c = this.time;
            } else if (string.equals(AppCanvas.getGameText(33))) { // OCCUPY
                if (this.a((int)this.var_c_h.mapX, (int)this.var_c_h.mapY, this.var_c_h)) {
                    this.void_a((int)this.var_c_h.mapX, (int)this.var_c_h.mapY, (int)this.var_c_h.owner);
                    g.a(this, null, AppCanvas.getGameText(38), 1000, true); // OCCUPIED
                    this.var_byte_i = (byte)9;
                    AppCanvas.playSound(-1, 1);
                    this.var_long_c = this.time;
                }
                this.var_c_h.void_b();
            } else if (string.equals(AppCanvas.getGameText(34))) {  // RAISE
                this.var_byte_i = (byte)7;
                this.targetableUnits_XX = this.var_c_h.searchInAttackRange(this.var_c_h.mapX, this.var_c_h.mapY, Unit.FILTER_TOMBSTONE);
                this.drawAreaBorder = true;
                this.useRedAreaBorder = true;
                this.var_c_h.updateAttackMatrix_XX(this.unitActionsMatrix, (int)this.var_c_h.mapX, (int)this.var_c_h.mapY);
                this.var_boolean_r = true;
            } else if (string.equals(AppCanvas.getGameText(35))) { // MAP
                g g6 = new g(this, g.TYPE_MINIMAP, 0);
                g6.a((byte)0, 0, 0, g2, 0);
            } else if (string.equals(AppCanvas.getGameText(36))) { // OBJECTIVE
                this._panelMapObjective.a((byte)0, 0, 0, g2, 0);
            } else {
                this.var_c_h = this.buyUnitAndSpawnAtCoords((byte)n, this.var_c_h.mapX, this.var_c_h.mapY);
                this.var_boolean_d = true;
                this.a(this.var_c_h);
            }
        }
        if (!bl) return;
        g2.a(true);
    }

    private Unit buyUnitAndSpawnAtCoords(byte unitType, int mapX, int mapY) {
        byte playerIndex = this.currentPlayerIndex_XX;
        this.playersMoney[playerIndex] -= Unit.unitsDataPrice[unitType];
        return Unit.spawn(unitType, this.currentPlayer, mapX, mapY);
    }

    public SpriteSheet a(byte playerIndex, byte unitType) {
        return this.var_e_arr_arr_b[playerIndex][unitType];
    }

    // TODO write a tool to read level data
    // level here means "map"
    public void loadLevelData(int levelIndex) throws Exception {
        String filename;
        this.var_java_util_Vector_c = new Vector<SpriteSheet>();
        this._turnIndex = 0;
        this.currentPlayer = PLAYER_BLUE;
        this.currentPlayerIndex_XX = 0;
        this.currentLevelStep = 0;
        this._mapKings = null;
        this.mapUnitsList = new Vector<Unit>();
        this.var_c_h = null;
        this.targetableUnits_XX = null;
        this.var_c_d = null;
        this.var_g_g.var_c_a = null;
        this.mapTerrain = null;
        this.unitActionsMatrix = null;
        // TODO even though levelPlayersCount is not a constant, the game assumes there is always two players
        this.playersMoney = new int[this.levelPlayersCount];
        if (this.levelType == LEVEL_TYPE_SKIRMISH) {
            this.playersMoney[PLAYER_BLUE] = 1000;
            this.playersMoney[PLAYER_RED] = 1000;
        } else {
            this.playersMoney[PLAYER_BLUE] = 300;
            this.playersMoney[PLAYER_RED] = 300;
        }
        this.var_boolean_v = true;
        //AppCanvas.readAssetsPackage("/1.pak");
        int levelFileSuffix = levelIndex;
        if (this.levelType == LEVEL_TYPE_CAMPAIGN) {
            if (levelIndex == 6) {
                levelFileSuffix = 5;
            }
            // Campaign map (story mode)
            filename = "m" + levelFileSuffix;
        } else {
            // Skirmish map (play against AI or other player with no story)
            filename = "s" + levelFileSuffix;
        }
        DataInputStream dataInputStream = new DataInputStream(AppCanvas.getFileBytesInputStream(filename));
        this.mapTilesWidth = (short)dataInputStream.readInt();
        this.mapTilesHeight = (short)dataInputStream.readInt();
        this.mapTerrain = new byte[this.mapTilesWidth][this.mapTilesHeight];
        this.unitActionsMatrix = new byte[this.mapTilesWidth][this.mapTilesHeight];
        int n3 = 0;
        byte[][] byArray = new byte[30][3];
        for (short mapX = 0; mapX < this.mapTilesWidth; mapX++) {
            for (short mapY = 0; mapY < this.mapTilesHeight; mapY++) {
                this.mapTerrain[mapX][mapY] = dataInputStream.readByte();
                this.unitActionsMatrix[mapX][mapY] = 0;
                if (this.mapTerrain[mapX][mapY] < this.var_int_t)
                    continue;
                byArray[n3][0] = (byte)mapX;
                byArray[n3][1] = (byte)mapY;
                byArray[n3][2] = (byte)this.int_a(mapX, (int)mapY);
                ++n3;
            }
        }
        this.var_byte_arr_arr_e = new byte[n3][3];
        System.arraycopy(byArray, 0, this.var_byte_arr_arr_e, 0, n3);
        byArray = null;
        this._mapPixelsWidth = (short)(this.mapTilesWidth * 24);
        this._mapPixelsHeight = (short)(this.mapTilesHeight * 24);
        if (levelIndex == 6) {
            dataInputStream.close();
            dataInputStream = new DataInputStream(AppCanvas.getFileBytesInputStream("m" + levelIndex));
            dataInputStream.readInt();
            dataInputStream.readInt();
        }
        int n4 = dataInputStream.readInt();
        dataInputStream.skip(n4 * 4);
        int unitsCount = dataInputStream.readInt();
        this._mapKings = new Unit[this.levelPlayersCount];
        for (short s = 0; s < unitsCount; s++) {
            byte by = dataInputStream.readByte();
            int n6 = dataInputStream.readShort() * 24 / 16;
            int n7 = dataInputStream.readShort() * 24 / 16;
            // TODO clever trick to save two values in one byte. 0-10 is blue player, 11-21 is red player
            byte unitType = (byte)(by % 11);
            byte unitOwner = (byte)(by / 11);
            Unit unit = Unit.spawn(unitType, unitOwner, n6 / 24, n7 / 24);
            if (unitType == Unit.KING) {
                this._mapKings[unitOwner] = unit;
            }
        }
        dataInputStream.close();
        //AppCanvas.e();
        if (this.levelType == LEVEL_TYPE_CAMPAIGN) {
            // Level name (e.g. REGROUP) and its objective, shown at level start
            this._panelMapObjective = g.a(this, AppCanvas.getGameText(48 + this.currentLevel), AppCanvas.getGameText(55 + this.currentLevel), -1, false);
            // 'The Kingdom of Thorin is divided. Betrayed by his own twin brother Valadorn [...]''
            this.storyPanel = g.a(this, null, AppCanvas.getGameText(103 + this.currentLevel), -1, false);
            this.a(false);
            this.pauseLevelProgress(500);
            AppCanvas.playSound(-1, 1);
        } else {
            // 36: OBJECTIVE
            // 62: 'Destroy the enemy king!'
            this._panelMapObjective = g.a(this, AppCanvas.getGameText(36), AppCanvas.getGameText(62), -1, false);
        }
        this.var_boolean_w = false;
        if (this._mapKings[PLAYER_RED] != null) {
            this.K = this._mapKings[PLAYER_RED].mapX;
            this.var_int_u = this._mapKings[PLAYER_RED].mapY;
        } else {
            this.K = 0;
            this.var_int_u = 0;
        }
        this.setMapCursorTo(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
        this.void_a(this._mapKings[PLAYER_BLUE].mapPixelX, (int)((SpriteSheet)this._mapKings[PLAYER_BLUE]).l);
    }

    public void h() {
        this.var_int_w = 0;
        this.var_c_h = null;
        this.targetableUnits_XX = new Unit[0];
        this.fillMatrixWithValue_XX(this.unitActionsMatrix, 0);
        this.drawAreaBorder = false;
        this.useRedAreaBorder = false;
    }

    private void fillMatrixWithValue_XX(byte[][] byArray, int value) {
        for (int x = 0; x < this.mapTilesWidth; ++x) {
            for (int y = 0; y < this.mapTilesHeight; ++y) {
                byArray[x][y] = (byte)value;
            }
        }
    }

    public void c(Unit unit) {
        this.b((Unit)null);
        this.fillMatrixWithValue_XX(this.unitActionsMatrix, 0);
        this.drawAreaBorder = false;
        if (this.var_boolean_o) {
            return;
        }
        if (this.var_byte_arr_b[this.currentPlayerIndex_XX] == 1) {
            this.var_boolean_n = true;
            this.mapCursorSheet.setReorderTable(mapSheetReorderTable[0]);
            this.var_byte_i = (byte)3;
            this.var_g_h = new g(this, (byte)0, 8);
            this.var_g_h.showMenuOptions(this.getUnitPossibleActions(unit, (byte)0));
            this.var_g_h.a((byte)8, 0, this.var_g_c.var_int_g, null, 0);
            AppCanvas.playSound(-1, 1);
        } else if (this.var_byte_arr_b[this.currentPlayerIndex_XX] == 0) {
            this.var_byte_b = (byte)4;
        }
    }

    // TODO by == 1 could mean "can move"?
    private String[] getUnitPossibleActions(Unit unit, byte by) {
        Vector<String> vector = new Vector<String>();
        // TODO isType(4) will return true ONLY if the unit is a king, but that bitfalg is ambiguous (28). Also see line 1876
        if (by == 1 && this.var_c_h.isType((short)4) && this.getTerrainType(this.var_c_h.mapX, this.var_c_h.mapY) == f.TERRAIN_CASTLE) {
            vector.addElement(AppCanvas.getGameText(29));   // BUY
        }
        if (this.a((int)unit.mapX, (int)unit.mapY, unit)) {
            vector.addElement(AppCanvas.getGameText(33));   // OCCUPY
        }
        if ((by == 1 || unit.unitType != Unit.CATAPULT) && unit.searchInAttackRange(unit.mapX, unit.mapY, Unit.FILTER_ENEMy).length > 0) {
            vector.addElement(AppCanvas.getGameText(28));   // ATTACK
        }
        if (unit.isType(Unit.WIZARD_FLAG) && unit.searchInAttackRange(unit.mapX, unit.mapY, Unit.FILTER_TOMBSTONE).length > 0) {
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
                if (this.var_int_m < 15) {
                    ++this.var_int_m;
                    ++this.N;
                    break;
                }
                if (this.time % 100L == 0L) {
                    this.var_boolean_p = !this.var_boolean_p;
                }
                if (Class_I.appCanvas.pressedKeysActions == 0 || !this.var_boolean_c || this.var_int_g != 0) break;
                g g2 = new g(this, (byte)0, 0);
                g2.showMenuOptions(this.mainMenuStringsNoSave);
                g2.a((byte)1, AppCanvas.cenX, AppCanvas.cenY, null, 48);
                Class_I.appCanvas.pressedKeysActions = 0;
            }
        }
    }

    public void e() throws Exception {
        int n;
        // Assumes game runs at 20 fps (1000 / 50 == 20)
        this.time += 50L;
        if (this.var_byte_d == 2) {
            this.b();
            return;
        }
        this.void_a();
        if (this._requestedTutorialIndex != -1) {
            if (AppCanvas.settings[AppCanvas.SETTINGS_HELP]) {
                // 85 is the first tutorial string - "Select and move units by pressing 5 [...]""
                this.storyPanel = g.a(this, AppCanvas.getGameText(85 + this._requestedTutorialIndex), PORTRAIT_NONE, (byte)2);
            }
            // Reset to display the panel only once
            this._requestedTutorialIndex = -1;
        }
        if (this.var_int_q == 0) {
            int n2;
            if (this.var_byte_d == 0) {
                this.j();
            } else {
                if (this.time - this.var_long_e >= 300L) {
                    this.var_boolean_a = !this.var_boolean_a;
                    this.var_long_e = this.time;
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
                            this.loadLevelData(this.currentLevel);
                            this.var_byte_i = 0;
                        } else if (this.var_byte_i == 11) {
                            this.var_int_r = 0;
                            this.var_long_c = this.time;
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
                if (this._pathSteps != null) {
                    this.H = (this.H + 1) % 12;
                }
                if (this.drawAreaBorder) {
                    // This will make the color "pulsate". When the color is white or gets too dark, change increment direction.
                    this.colorRangeBorder += this.colorRangeBorderIncrement;
                    if (this.colorRangeBorder >= 0xFFFFFF) {
                        this.colorRangeBorder = 0xFFFFFF;
                        this.colorRangeBorderIncrement = -986895;
                    } else if (this.colorRangeBorder <= 0x9F9F9F) {
                        this.colorRangeBorder = 0x9F9F9F;
                        this.colorRangeBorderIncrement = 986895;
                    }
                }
                if (this.var_boolean_n && this.time - this.var_long_k >= 200L) {
                    this.mapCursorSheet.nextFrame();
                    this.var_long_k = this.time;
                }
                {
                    int cursorMapX = this.mapCursorX * 24;
                    int cursorMapY = this.mapCursorY * 24;
                    int cursorPixelX = this.mapCursorSheet.var_short_b;
                    int cursorPixelY = this.mapCursorSheet.l;
                    if (cursorMapX > cursorPixelX) {
                        cursorPixelX += 8;
                    } else if (cursorMapX < cursorPixelX) {
                        cursorPixelX -= 8;
                    }
                    if (cursorMapY > cursorPixelY) {
                        cursorPixelY += 8;
                    } else if (cursorMapY < cursorPixelY) {
                        cursorPixelY -= 8;
                    }
                    this.mapCursorSheet.setMapPixelCoords(cursorPixelX, cursorPixelY);
                }
                if (this.var_byte_i == 8) {
                    if (this.var_int_k == 0 && this.timedInfobox == null) {
                        this.startNextTurn();
                        this.var_byte_i = 0;
                    }
                } else if (this.var_byte_i == 9) {
                    if (this.timedInfobox == null) {
                        this.var_byte_i = 0;
                    }
                } else if (this.var_byte_i == 11) {
                    if (!this.var_boolean_y && this.var_int_r == 0 && this.time - this.var_long_c >= 1000L) {
                        g temmp_g_XXX = new g(this, (byte)0, 0);
                        temmp_g_XXX.showMenuOptions(this.mainMenuStringsNoSave);
                        temmp_g_XXX.a((byte)1, AppCanvas.cenX, AppCanvas.cenY, null, 48);
                        temmp_g_XXX.var_boolean_g = false;
                        this.var_int_r = 1;
                    }
                } else if (this.var_byte_i == 10) {
                    if (this.timedInfobox == null) {
                        this.var_boolean_y = true;
                        this.var_int_m = 0;
                    }
                } else if (this.var_byte_i == 13) {
                    if (this.var_int_n == 0) {
                        this.var_c_i.attack(this.var_c_b);
                        this.var_c_b.b(400);
                        this.a(this.redsparkSheet, this.var_c_b.mapPixelX, ((SpriteSheet)this.var_c_b).l, 0, 0, 2, 50);
                        this.var_long_d = this.time;
                        ++this.var_int_n;
                    } else if (this.var_int_n == 1) {
                        if (this.time - this.var_long_d >= 800L) {
                            this.setMapCursorTo(this.var_c_i.mapX, this.var_c_i.mapY);
                            if (this.var_c_b.canCounterattackMelee(this.var_c_i, (int)this.var_c_i.mapX, (int)this.var_c_i.mapY)) {
                                this.var_c_b.attack(this.var_c_i);
                                this.var_c_i.b(400);
                                this.a(this.redsparkSheet, this.var_c_i.mapPixelX, ((SpriteSheet)this.var_c_i).l, 0, 0, 2, 50);
                                this.var_long_d = this.time;
                                ++this.var_int_n;
                            } else {
                                this.o();
                            }
                        }
                    } else if (this.time - this.var_long_d >= 800L) {
                        this.o();
                    }
                } else if (this.var_c_c != null) {
                    if (this.time - this.var_long_g >= 300L) {
                        // TODO context hint: mapUnitExplosionSheet is used only here, and the animation is shown only ehen a unit dies
                        this.a(this.mapUnitExplosionSheet, this.var_c_c.mapPixelX, ((SpriteSheet)this.var_c_c).l, 0, -3, 1, 100);
                        if (this.levelType == LEVEL_TYPE_CAMPAIGN && this._mapKings[PLAYER_RED] != null && this.var_c_c == this._mapKings[PLAYER_RED] && this.currentLevel != CAMPAIGN_4_WYVERN_RESCUE) {
                            if (this.currentLevel != CAMPAIGN_6_FINAL_ASSAULT) {
                                this.mapUnitsList.removeElement(this.var_c_c);
                                this._mapKings[PLAYER_RED] = null;
                            }
                        } else if (this.var_c_c.unitType == Unit.SKELETON) {
                            // When a skeleton dies, it disappears without creating a tombstone
                            this.mapUnitsList.removeElement(this.var_c_c);
                        } else {
                            this.var_c_c.state = Unit.STATE_TOMBSTONE;
                            this.var_c_c.turnOfDeath = this._turnIndex;
                        }
                        this.var_c_c = null;
                        this.var_g_g.b();
                    }
                } else if (this.var_c_e != null) {
                    if (this.time - this.var_long_i >= 400L) {
                        this.mapUnitsList.removeElement(this.var_c_e);
                        // TODO rename second parameter to playerIndex_ZZ
                        Unit unitSkeleton = Unit.spawn(Unit.SKELETON, this.var_byte_f, this.var_c_e.mapX, this.var_c_e.mapY);
                        unitSkeleton.void_b();
                        this.var_c_e = null;
                    }
                } else if (this.var_byte_arr_b[this.currentPlayerIndex_XX] == 0) {
                    this.p();
                } else if (Class_I.appCanvas.a_instance == this && this.var_int_g == 0) {
                    if (this.var_boolean_t && appCanvas.isRequestingAction(AppCanvas.ACTION_UI_CONFIRM)) {
                        appCanvas.handleKeyPressedAction(AppCanvas.ACTION_CONFIRM);
                        appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_UI_CONFIRM);
                    }
                    if (this.var_byte_i == 6 || this.var_byte_i == 7) {
                        if (appCanvas.isRequestingAction(AppCanvas.ACTION_LEFT)) {
                            --this.var_int_w;
                            if (this.var_int_w < 0) {
                                this.var_int_w = this.targetableUnits_XX.length - 1;
                            }
                            appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_LEFT);
                            this.var_boolean_v = true;
                        } else if (appCanvas.isRequestingAction(AppCanvas.ACTION_RIGHT)) {
                            ++this.var_int_w;
                            if (this.var_int_w >= this.targetableUnits_XX.length) {
                                this.var_int_w = 0;
                            }
                            appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_RIGHT);
                            this.var_boolean_v = true;
                        } else if (appCanvas.isRequestingAction(AppCanvas.ACTION_UP)) {
                            --this.var_int_w;
                            if (this.var_int_w < 0) {
                                this.var_int_w = this.targetableUnits_XX.length - 1;
                            }
                            appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_UP);
                            this.var_boolean_v = true;
                        } else if (appCanvas.isRequestingAction(AppCanvas.ACTION_DOWN)) {
                            ++this.var_int_w;
                            if (this.var_int_w >= this.targetableUnits_XX.length) {
                                this.var_int_w = 0;
                            }
                            appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_DOWN);
                            this.var_boolean_v = true;
                        }
                        this.setMapCursorTo(this.targetableUnits_XX[this.var_int_w].mapX, this.targetableUnits_XX[this.var_int_w].mapY);
                        if (this.var_boolean_v) {
                            this.var_g_g.b();
                        }
                        if (appCanvas.isRequestingAction(AppCanvas.ACTION_CONFIRM)) {
                            if (this.var_byte_i == 6) {
                                this.b(this.var_c_h, this.targetableUnits_XX[this.var_int_w]);
                            } else if (this.var_byte_i == 7) {
                                this.void_a(this.targetableUnits_XX[this.var_int_w], this.currentPlayer);
                                this.var_c_h.void_b();
                                this.var_byte_i = 0;
                            }
                            this.h();
                            this.var_boolean_r = false;
                            this.var_boolean_t = false;
                        }
                        this.var_boolean_v = false;
                    } else {
                        if (this.time - this.var_long_a >= 150L && this.mapCursorSheet.var_short_b % 24 == 0 && this.mapCursorSheet.l % 24 == 0) {
                            if (appCanvas.isRequestingAction(AppCanvas.ACTION_LEFT)) {
                                if (this.var_boolean_s || appCanvas.stoppedRequestingAction(AppCanvas.ACTION_LEFT)) {
                                    if (this.mapCursorX > 0) {
                                        this.mapCursorX--;
                                    }
                                    this.var_boolean_s = false;
                                    this.var_boolean_v = true;
                                    this.var_long_a = this.time;
                                }
                            } else if (appCanvas.isRequestingAction(AppCanvas.ACTION_RIGHT) && (this.var_boolean_s || appCanvas.stoppedRequestingAction(AppCanvas.ACTION_RIGHT))) {
                                if (this.mapCursorX < this.mapTilesWidth - 1) {
                                    this.mapCursorX++;
                                }
                                this.var_boolean_s = false;
                                this.var_boolean_v = true;
                                this.var_long_a = this.time;
                            }
                            if (appCanvas.isRequestingAction(AppCanvas.ACTION_UP)) {
                                if (this.var_boolean_s || appCanvas.stoppedRequestingAction(AppCanvas.ACTION_UP)) {
                                    if (this.mapCursorY > 0) {
                                        this.mapCursorY--;
                                    }
                                    this.var_boolean_s = false;
                                    this.var_boolean_v = true;
                                    this.var_long_a = this.time;
                                }
                            } else if (appCanvas.isRequestingAction(AppCanvas.ACTION_DOWN) && (this.var_boolean_s || appCanvas.stoppedRequestingAction(AppCanvas.ACTION_DOWN))) {
                                if (this.mapCursorY < this.mapTilesHeight - 1) {
                                    this.mapCursorY++;
                                }
                                this.var_boolean_s = false;
                                this.var_boolean_v = true;
                                this.var_long_a = this.time;
                            }
                            if (this.var_boolean_v) {
                                if (this.var_byte_i == 1) {
                                    if (this.unitActionsMatrix[this.mapCursorX][this.mapCursorY] > 0) {
                                        this._pathSteps = this.var_c_h.pathSteps(this.var_c_h.mapX, this.var_c_h.mapY, this.mapCursorX, this.mapCursorY);
                                    }
                                } else {
                                    this.var_g_g.b();
                                }
                            }
                            this.var_boolean_v = false;
                        }
                        if (this.var_byte_i == 1) {
                            if (appCanvas.isRequestingAction(AppCanvas.ACTION_CONFIRM) && this.var_c_h != null) {
                                Unit unit_c_a = this.tryGetUnit(this.mapCursorX, this.mapCursorY, SEARCH_ALIVE);
                                if (this.unitActionsMatrix[this.mapCursorX][this.mapCursorY] > 0 && (unit_c_a == null || unit_c_a == this.var_c_h)) {
                                    this.var_int_c = this.var_c_h.mapX;
                                    this.var_int_v = this.var_c_h.mapY;
                                    this.var_c_h.void_a(this.mapCursorX, this.mapCursorY);
                                    this.b(this.var_c_h);
                                    this.var_boolean_n = false;
                                    this.drawAreaBorder = false;
                                    this._pathSteps = null;
                                    this.var_boolean_r = false;
                                    this.var_boolean_t = false;
                                    this.var_byte_i = (byte)2;
                                    // TODO this -1 should be an error...
                                    AppCanvas.playSound(-1, 1);
                                }
                                appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_CONFIRM);
                            }
                        } else if (this.var_byte_i == 0) {
                            if (appCanvas.isRequestingAction(AppCanvas.ACTION_UNIT_INFO_OR_SCROLL_DOWN)) {
                                this.var_c_h = this.tryGetUnit(this.mapCursorX, this.mapCursorY, SEARCH_ALIVE);
                                if (this.var_c_h != null) {
                                    g temp_g_YYY = new g(this, g.TYPE_UNIT_INFO, 0);
                                    temp_g_YYY.a((byte)0, 0, 0, null, 0);
                                }
                                appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_UNIT_INFO_OR_SCROLL_DOWN);
                            } else if (appCanvas.isRequestingAction(AppCanvas.ACTION_GOTO_KING)) {
                                if (this._mapKings[this.currentPlayer] != null) {
                                    this.setMapCursorTo(this._mapKings[this.currentPlayer].mapX, this._mapKings[this.currentPlayer].mapY);
                                    this.void_a(this._mapKings[this.currentPlayer].mapPixelX + 12, ((SpriteSheet)this._mapKings[this.currentPlayer]).l + 12);
                                }
                            } else if (appCanvas.isRequestingAction(AppCanvas.ACTION_UNIT_RANGE)) {
                                this.var_c_h = this.tryGetUnit(this.mapCursorX, this.mapCursorY, SEARCH_ALIVE);
                                if (this.var_c_h != null) {
                                    this.fillMatrixWithValue_XX(this.unitActionsMatrix, 0);
                                    this.var_c_h.updateTotalAttackRangeMatrix(this.unitActionsMatrix);
                                    this.useRedAreaBorder = true;
                                    this.drawAreaBorder = true;
                                }
                                appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_UNIT_RANGE);
                            } else if (!appCanvas.isRequestingAction(128) && !appCanvas.isRequestingAction(AppCanvas.ACTION_SCROLL_UP) && (appCanvas.isRequestingAction(AppCanvas.ACTION_CONFIRM) || appCanvas.isRequestingAction(AppCanvas.ACTION_UI_CONFIRM))) {
                                this.var_c_h = this.tryGetUnit(this.mapCursorX, this.mapCursorY, SEARCH_ALIVE);
                                if (this.var_c_h != null && this.var_c_h.state == 0 && this.var_c_h.owner == this.currentPlayer) {
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
                                appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_CONFIRM);
                                appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_UI_CONFIRM);
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
                int unitsCount = this.mapUnitsList.size();
                for (n = 0; n < unitsCount; ++n) {
                    this.mapUnitsList.elementAt(n).void_a();
                }
                if (this.time - this.var_long_l >= 300L) {
                    this.var_int_y = (this.var_int_y + 1) % this.var_h_arr_a.length;
                    this.var_h_arr_c[this.var_int_l] = this.var_h_arr_a[this.var_int_y];
                    this.var_long_l = this.time;
                }
                this.d();
            }
            if (this.var_boolean_r && appCanvas.isRequestingAction(AppCanvas.ACTION_CANCEL)) {
                if (this.var_byte_i == 1) {
                    this.var_byte_i = 0;
                    this.fillMatrixWithValue_XX(this.unitActionsMatrix, 0);
                    this.drawAreaBorder = false;
                    this.useRedAreaBorder = false;
                    this._pathSteps = null;
                    this.mapCursorSheet.setReorderTable(mapSheetReorderTable[0]);
                    this.setMapCursorTo(this.var_c_h.mapX, this.var_c_h.mapY);
                    this.var_c_h = null;
                } else if (this.var_byte_i == 6) {
                    this.var_byte_i = this.var_byte_e;
                    this.drawAreaBorder = false;
                    this.var_g_h.a((byte)8, 0, 40, null, 0);
                    this.mapCursorSheet.setReorderTable(mapSheetReorderTable[0]);
                    this.setMapCursorTo(this.var_c_h.mapX, this.var_c_h.mapY);
                }
                appCanvas.handleKeyReleasedAction(AppCanvas.ACTION_CANCEL);
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

    private void void_a(Unit c2, byte playerID) {
        this.var_c_e = c2;
        this.var_byte_f = playerID;
        this.a(this.blueSparkSheet, c2.mapPixelX - 8, ((SpriteSheet)c2).l - 8, 1, 1, 3, 50);
        this.a(this.blueSparkSheet, c2.mapPixelX + 8, ((SpriteSheet)c2).l - 8, -1, 1, 3, 50);
        this.a(this.blueSparkSheet, c2.mapPixelX - 8, ((SpriteSheet)c2).l + 8, 1, -1, 3, 50);
        this.a(this.blueSparkSheet, c2.mapPixelX + 8, ((SpriteSheet)c2).l + 8, -1, -1, 3, 50);
        this.var_long_i = this.time;
    }

    private void d() {
        if (this.var_c_d == null) {
            this.d(this.mapCursorSheet.var_short_b + 12, this.mapCursorSheet.l + 12);
        } else {
            this.d(this.var_c_d.mapPixelX + 12, ((SpriteSheet)this.var_c_d).l + 12);
        }
    }

    public void b(Unit c2) {
        this.var_c_d = c2;
        this.var_int_g = c2 == null ? --this.var_int_g : ++this.var_int_g;
    }

    public boolean boolean_c(int n, int n2) {
        return this.var_short_f == this.int_b(n) && this.var_short_a == this.int_a(n2);
    }

    private boolean boolean_b(int mapX, int mapY) {
        return this.boolean_c(mapX * 24 + 12, mapY * 24 + 12);
    }

    public int int_b(int n) {
        int n2;
        if (this._mapPixelsWidth > AppCanvas.width2) {
            n2 = AppCanvas.cenX - n;
            if (n2 > 0) {
                n2 = 0;
            } else if (n2 < AppCanvas.width2 - this._mapPixelsWidth) {
                n2 = (short)(AppCanvas.width2 - this._mapPixelsWidth);
            }
        } else {
            n2 = (AppCanvas.width2 - this._mapPixelsWidth) / 2;
        }
        return n2;
    }

    public int int_a(int n) {
        int n2;
        if (this._mapPixelsHeight > AppCanvas.height2) {
            n2 = AppCanvas.cenY - n;
            if (n2 > 0) {
                n2 = 0;
            } else if (n2 < AppCanvas.height2 - this._mapPixelsHeight) {
                n2 = (short)(AppCanvas.height2 - this._mapPixelsHeight);
            }
        } else {
            n2 = (AppCanvas.height2 - this._mapPixelsHeight) / 2;
        }
        return n2;
    }

    // TODO may have something to do with setting camera pan target
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

    private void setMapCursorTo(int mapX, int mapY) {
        this.mapCursorX = (short)mapX;
        this.mapCursorY = (short)mapY;
        this.mapCursorSheet.setMapPixelCoords(mapX * 24, mapY * 24);
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
        int minScreenX = this.var_short_f < 0 ? this.var_short_f % 24 : this.var_short_f;
        int screenY = this.var_short_a < 0 ? this.var_short_a % 24 : this.var_short_a;
        if (this.useRedAreaBorder) {
            // Multiply with red
            graphics.setColor(this.colorRangeBorder & 0xFF0000);
        } else {
            graphics.setColor(this.colorRangeBorder);
        }
        for (short mapY = s2; mapY <= s4; mapY++) {
            int screenX = minScreenX;
            for (short mapX = s; mapX <= s3; mapX++) {
                // TODO maybe this ethod draws the map
                byte tileIndex = this.mapTerrain[mapX][mapY];
                this.var_h_arr_c[tileIndex].draw(graphics, screenX, screenY);
                // TODO should use the method "getTerrainType"
                if (this.tileIdToTerrainType[tileIndex] == f.TERRAIN_CASTLE) {
                    // Draws the top part of the castle
                    this.var_h_arr_c[tileIndex + 1].draw(graphics, screenX, screenY - 24);
                }
                // Draw the movement/attack area border, if this tile is reachable by the currently selected unit
                // TODO not sure why the unit's location tile has a border too, it should have value > 0
                if (this.drawAreaBorder && this.unitActionsMatrix[mapX][mapY] > 0) {
                    // Line at current tiles's left. All lines are drawn in the inner side of the tile.
                    if (mapX > 0 && this.unitActionsMatrix[mapX - 1][mapY] <= 0) {
                        graphics.fillRect(screenX, screenY, 2, 24);
                    }
                    if (mapX < this.mapTilesWidth - 1 && this.unitActionsMatrix[mapX + 1][mapY] <= 0) {
                        graphics.fillRect(screenX + 24 - 2, screenY, 2, 24);
                    }
                    if (mapY > 0 && this.unitActionsMatrix[mapX][mapY - 1] <= 0) {
                        graphics.fillRect(screenX, screenY, 24, 2);
                    }
                    if (mapY < this.mapTilesHeight - 1 && this.unitActionsMatrix[mapX][mapY + 1] <= 0) {
                        graphics.fillRect(screenX, screenY + 24 - 2, 24, 2);
                    }
                }
                screenX += 24;
            }
            screenY += 24;
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
        if (this.splashPhase == 0) {
            graphics.setColor(0xFFFFFF);
            graphics.fillRect(0, 0, Class_I.appCanvas.width, Class_I.appCanvas.height);
            Class_I.a(graphics, 0, this.var_int_m, 15, 0, this.spriteMacrospaceLogo, (Class_I.appCanvas.width - this.spriteMacrospaceLogo.width) / 2, (Class_I.appCanvas.height - this.spriteMacrospaceLogo.height) / 2, 0, 0);
        } else if (this.splashPhase == 1) {
            Class_I.a(graphics, 0xFFFFFF, this.var_int_m, 15, 0, null, (Class_I.appCanvas.width - this.spriteMacrospaceLogo.width) / 2, (Class_I.appCanvas.height - this.spriteMacrospaceLogo.height) / 2, this.spriteMacrospaceLogo.width, this.spriteMacrospaceLogo.height);
        } else if (this.splashPhase == 2) {
            graphics.setColor(-16777216);
            graphics.fillRect(0, 0, Class_I.appCanvas.width, Class_I.appCanvas.height);
            if (this.var_int_m >= 15) {
                this.spriteGameTitle.draw(graphics, Class_I.appCanvas.width - this.spriteGameTitle.width >> 1, (Class_I.appCanvas.height - this.spriteGameTitle.height) / 3);
                graphics.setColor(0xFFFFFF);
                graphics.setFont(AppCanvas.fontSmallPlain);
                // TODO there's a pretty similar method in 'a' class
                if (this.var_boolean_p && this.var_int_g == 0) {
                    // PRESS ANY KEY
                    graphics.drawString(AppCanvas.getGameText(25), AppCanvas.cenX, AppCanvas.height2 * 3 / 4, 17);
                }
                // ©MACROSPACE LTD.
                graphics.drawString(AppCanvas.getGameText(15), AppCanvas.cenX, AppCanvas.height2 - 1, 33);
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
                graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                if (this._mapPixelsWidth < AppCanvas.width2 || this._mapPixelsHeight < AppCanvas.height2) {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, AppCanvas.width2, AppCanvas.height2);
                }
                this.d(graphics);
                int unitsCount = this.mapUnitsList.size();
                for (int i = 0; i < unitsCount; ++i) {
                    Unit unit = this.mapUnitsList.elementAt(i);
                    if (unit.state == Unit.STATE_TOMBSTONE) {
                        this.spriteTombstone.draw(graphics, this.var_short_f + unit.mapPixelX, this.var_short_a + ((SpriteSheet)unit).l);
                        continue;
                    }
                    if (unit == this.var_c_h) continue;
                    unit.a(graphics, (int)this.var_short_f, (int)this.var_short_a);
                }
                // TODO redundant assignment
                unitsCount = this.mapUnitsList.size();
                for (int i = 0; i < unitsCount; ++i) {
                    this.mapUnitsList.elementAt(i).drawInfoOverlay(graphics, this.var_short_f, this.var_short_a);
                }
                if (this._pathSteps != null) {
                    graphics.setColor(0xFFFFFF);
                    short[][] pathStepsCopy = new short[this._pathSteps.size()][];
                    this._pathSteps.copyInto((Object[])pathStepsCopy);
                    int numSteps = pathStepsCopy.length;
                    final byte X = 0;
                    final byte Y = 1;
                    for (int i = 0; i < numSteps; ++i) {
                        int n5 = pathStepsCopy[i][X] * 24 + this.var_short_f;
                        int n6 = pathStepsCopy[i][Y] * 24 + this.var_short_a;
                        int n7 = n5 + 12;
                        int n8 = n6 + 12;
                        short[] currentStep = pathStepsCopy[i];
                        if (i != 0) {
                            short[] prevStep = pathStepsCopy[i - 1];
                            if (prevStep[X] == currentStep[X] + 1) {
                                this.a(graphics, n7, n8 - this.G, 12, 0, true);
                            } else if (prevStep[X] == currentStep[X] - 1) {
                                this.a(graphics, n5, n8 - this.G, 12, 0, false);
                            } else if (prevStep[Y] == currentStep[Y] + 1) {
                                this.a(graphics, n7 - this.G, n8, 0, 12, true);
                            } else if (prevStep[Y] == currentStep[Y] - 1) {
                                this.a(graphics, n7 - this.G, n6, 0, 12, false);
                            }
                        }
                        if (i == numSteps - 1) {
                            graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                            this.mapCursorMoveUnit.a(graphics, n5 - 1, n6 - 4);
                            continue;
                        }
                        short[] nextStep = pathStepsCopy[i + 1];
                        if (nextStep[X] == currentStep[X] + 1) {
                            this.a(graphics, n7, n8 - this.G, 12, 0, false);
                            continue;
                        }
                        if (nextStep[X] == currentStep[X] - 1) {
                            this.a(graphics, n5, n8 - this.G, 12, 0, true);
                            continue;
                        }
                        if (nextStep[Y] == currentStep[Y] + 1) {
                            this.a(graphics, n7 - this.G, n8, 0, 12, false);
                            continue;
                        }
                        if (nextStep[Y] == currentStep[Y] - 1) {
                            this.a(graphics, n7 - this.G, n6, 0, 12, true);
                        }
                    }
                }
                graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                if (this.var_c_h != null) {
                    this.var_c_h.a(graphics, (int)this.var_short_f, (int)this.var_short_a);
                    this.var_c_h.drawInfoOverlay(graphics, this.var_short_f, this.var_short_a);
                }
                if (this.var_boolean_n) {
                    this.mapCursorSheet.a(graphics, this.var_short_f - 1, this.var_short_a - 1);
                }
                if (this.var_byte_i == 11 && !this.var_boolean_y) {
                    String textGameOver = AppCanvas.getGameText(23);  // GAME OVER
                    graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
                    graphics.setFont(AppCanvas.fontSmallPlain);
                    graphics.setColor(-16777216);
                    graphics.fillRect(0, 0, AppCanvas.width2, AppCanvas.height2);
                    int n3 = AppCanvas.cenY - AppCanvas.fontSmallPlain.getHeight() / 2;
                    graphics.setColor(-1);
                    graphics.drawString(textGameOver, AppCanvas.cenX, n3, 17);
                }
                for (int n = 0; n < this.var_java_util_Vector_c.size(); ++n) {
                    this.var_java_util_Vector_c.elementAt(n).a(graphics, this.var_short_f, this.var_short_a);
                }
            }
        }
        for (int n = 0; n < this.var_java_util_Vector_e.size(); ++n) {
            this.var_java_util_Vector_e.elementAt(n).a(graphics);
        }
        if (this.var_int_g == 0) {
            if (this.var_boolean_r) {
                this.uiBtnIconSheet.setCurrentIndex(BTN_ICON_BACK);
                this.uiBtnIconSheet.a(graphics, AppCanvas.width2 - this.uiBtnIconSheet.getSpritesWidth(), AppCanvas.height2 - this.uiBtnIconSheet.getSpritesHeight());
            }
            if (this.var_boolean_t) {
                this.uiBtnIconSheet.setCurrentIndex(BTN_ICON_CONFIRM);
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

    public static final byte SEARCH_ALIVE = 0;
    public static final byte SEARCH_TOMBSTONE = 1;

    /**
     * Looks for a unit at the given coordinates and returns it only if it mets the search condition.
     * @param mapX
     * @param mapY
     * @param searchType Determines if trying to get a regular unit or a tombstone (dead unit)
     */
    public Unit tryGetUnit(int mapX, int mapY, byte searchType) {
        int unitsCount = this.mapUnitsList.size();
        for (int i = 0; i < unitsCount; ++i) {
            Unit unit = this.mapUnitsList.elementAt(i);

            if (mapX != unit.mapX || mapY != unit.mapY) continue;

            if (searchType == SEARCH_ALIVE && unit.state != Unit.STATE_TOMBSTONE) {
                return unit;
            }
            if(searchType == SEARCH_TOMBSTONE && unit.state == Unit.STATE_TOMBSTONE) {
                return unit;
            }
        }
        return null;
    }

    public byte getTerrainType(int mapX, int mapY) {
        return this.tileIdToTerrainType[this.mapTerrain[mapX][mapY]];
    }

    /** Returns the defence a certain terrain type provides to a specific type of unit. */
    public int getTerrainDefenceForUnit(byte terrainType, Unit unit) {
        int terrainDefence = terrainTypeDefense[terrainType];
        // Lizards get +2 defence in water
        if (unit.isType(Unit.LIZARD_FLAG) && terrainType == f.TERRAIN_WATER) {
            terrainDefence += 2;
        }
        return terrainDefence;
    }

    private void startNextTurn() {
        this._turnIndex++;
        // This line changes the current player, but there's only two, so...
        this.currentPlayerIndex_XX = (byte)((this.currentPlayerIndex_XX + 1) % this.players.length);
        this.currentPlayer = this.players[this.currentPlayerIndex_XX];
        for (int i = this.mapUnitsList.size() - 1; i >= 0; --i) {
            Unit unit = this.mapUnitsList.elementAt(i);
            // Maybe this means the unit is dead? Since it has to be removed.
            if (unit.state == Unit.STATE_TOMBSTONE) {
                // TODO the game info says the tombstone disappears after one turn, but code says 2 full turns
                if (this._turnIndex - unit.turnOfDeath < 3) continue;
                this.mapUnitsList.removeElement(unit);
                continue;
            }
            unit.state = 0;

            // If a unit stack controlled by a player starts its turn on a building (town/castle)
            // owned by the same player, 2 units are healed/recovered
            if (this.currentPlayer == unit.owner && this.isBuildingAndOwnedByPlayer(unit.mapX, unit.mapY, unit.owner)) {
                unit.quantity += 2;
                if (unit.quantity > 10) {
                    unit.quantity = 10;
                }
            }

            // Remove poison (movement debuff) from enemy units since it can be active only for one turn (theirs)
            if (this.currentPlayer != unit.owner) {
                unit.removeStatus(Unit.STATUS_POISON);
            }
        }

        // TODO you get money for each occupied town and castle
        for (int mapX = 0; mapX < this.mapTerrain.length; ++mapX) {
            for (int mapY = 0; mapY < this.mapTerrain[mapX].length; ++mapY) {
                if (!this.isBuildingAndOwnedByPlayer(mapX, mapY, this.currentPlayer))
                    continue;
                if (this.getTerrainType(mapX, mapY) == f.TERRAIN_TOWN) {
                    byte playerIndex = this.currentPlayerIndex_XX;
                    this.playersMoney[playerIndex] += 30;
                    continue;
                }
                if (this.getTerrainType(mapX, mapY) != f.TERRAIN_CASTLE)
                    continue;
                byte playerIndex = this.currentPlayerIndex_XX;
                this.playersMoney[playerIndex] += 50;
            }
        }
        int n = this.mapCursorX;
        short s = this.mapCursorY;
        this.setMapCursorTo(this.K, this.var_int_u);
        this.K = n;
        this.var_int_u = s;
        this.var_boolean_v = true;
        // TODO probably ised in campaign first level where for a bit you play with no enemies
        if (this.searchUnitsCount(SEARCH_ANY, 0, this.currentPlayer) <= 0) {
            this.startNextTurn();
        }
    }

    // TODO rename to canUnitOccupyBuildingBelow
    // TODO first two paeams are unused
    private boolean a(int n, int n2, Unit c2) {
        // TODO isType(8) will return true if the unit is a king OR a soldier, bitflags are ambiguous
        if (c2.isType((short)8) && this.getTerrainType(c2.mapX, c2.mapY) == f.TERRAIN_TOWN && !this.isBuildingAndOwnedByPlayer(c2.mapX, c2.mapY, c2.owner)) {
            return true;
        }
        // TODO isType(16) will return true ONLY if the unit is a king, but king's bitfalg is ambiguous (28)- Also see line 969
        return c2.isType((short)16) && this.getTerrainType(c2.mapX, c2.mapY) == f.TERRAIN_CASTLE && !this.isBuildingAndOwnedByPlayer(c2.mapX, c2.mapY, c2.owner);
    }

    private void void_a(int mapX, int mapY, int playerID) {
        if (this.mapTerrain[mapX][mapY] >= this.var_int_t) {
            this.mapTerrain[mapX][mapY] = (byte)(this.var_int_t + (playerID + 1) * 3 + (this.mapTerrain[mapX][mapY] - this.var_int_t) % 3);
        }
    }

    // TODO getBuildingOwner
    public int int_a(int mapX, int mapY) {
        if (this.mapTerrain[mapX][mapY] >= this.var_int_t) {
            return (this.mapTerrain[mapX][mapY] - this.var_int_t) / 3 - 1;
        }
        return -1;
    }

    /** Returns whether at the provided map coordinates there is a building owned by the specified player. */
    private boolean isBuildingAndOwnedByPlayer(int mapX, int mapY, byte playerIndex) {
        return this.int_a(mapX, mapY) == playerIndex;
    }

    /**
     * Returns the number of units found using the provided search parameters.
     * @param unitType Pass a value like Unit.SOLDIER, or pass ANY to allow any type.
     * @param unitState Pass a value like Unit.STATE_* or pass ANY to allow any state.
     * @param playerID Unit must be owned by this player.
     */
    private int searchUnitsCount(int unitType, int unitState, byte playerID) {
        return this.searchUnits(unitType, unitState, playerID).length;
    }

    final int SEARCH_ANY = -1;

    /**
     * Returns an array of all units owned by a specific player that also respect some conditions.
     * @param type Unit type. Pass a value like Unit.SOLDIER, or pass ANY to allow any type.
     * @param state Unit state. Pass a value like Unit.STATE_* or pass ANY to allow any state.
     * @param playerID Unit must be owned by this player.
     */
    private Unit[] searchUnits(int type, int state, byte playerID) {
        Vector<Unit> vector = new Vector<Unit>();
        int unitsCount = this.mapUnitsList.size();
        for (int i = 0; i < unitsCount; ++i) {
            Unit unit = this.mapUnitsList.elementAt(i);

            if (unit.owner != playerID) continue;

            if (type == SEARCH_ANY || unit.unitType == type)  {
                if(state == SEARCH_ANY || state == unit.state) {
                    vector.addElement(unit);
                }
            }
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

    /** Returns whether the current player can purchase a unit of the selected type.
     * The unit must be enabled for purchase, the player must have enough money
     * and there has to be a free square near the king (which is assumed to be at the castle)
    */
    private boolean canPurchaseUnit(int unitType) {
        int kingX = this._mapKings[this.currentPlayer].mapX;
        int kingY = this._mapKings[this.currentPlayer].mapY;
        boolean canPlayerAffordUnit = Unit.unitsDataPrice[unitType] <= this.playersMoney[this.currentPlayerIndex_XX];
        boolean isUnitBuyable = Unit.unitsDataPrice[unitType] > 0;
        return canPlayerAffordUnit
                && isUnitBuyable
                && (
                    kingX > 0 && this.tryGetUnit(kingX - 1, kingY, SEARCH_ALIVE) == null
                    || kingX < this.mapTilesWidth - 1 && this.tryGetUnit(kingX + 1, kingY, SEARCH_ALIVE) == null
                    || kingY > 0 && this.tryGetUnit(kingX, kingY - 1, SEARCH_ALIVE) == null
                    || kingY < this.mapTilesHeight - 1 && this.tryGetUnit(kingX, kingY + 1, SEARCH_ALIVE) == null
                    );
    }

    // TODO 90% sure this handles AI turn, the last call shows a "turn end" panel
    public void p() throws Exception {
        if (this.var_byte_b == 4) {
            if (this.var_c_g != null || this.var_c_a != null) {
                this.var_byte_b = (byte)5;
                this.var_c_h.updateAttackMatrix_XX(this.unitActionsMatrix, (int)this.var_c_h.mapX, (int)this.var_c_h.mapY);
                this.useRedAreaBorder = true;
                this.drawAreaBorder = true;
                this.var_long_j = this.time;
                if (this.var_c_g != null) {
                    this.mapCursorSheet.setReorderTable(mapSheetReorderTable[1]);
                    this.setMapCursorTo(this.var_c_g.mapX, this.var_c_g.mapY);
                } else if (this.var_c_a != null) {
                    this.setMapCursorTo(this.var_c_a.mapX, this.var_c_a.mapY);
                }
            } else {
                if (this.a((int)this.var_c_h.mapX, (int)this.var_c_h.mapY, this.var_c_h)) {
                    this.void_a((int)this.var_c_h.mapX, (int)this.var_c_h.mapY, (int)this.var_c_h.owner);
                    g.a(this, null, AppCanvas.getGameText(38), 1000, true);
                    AppCanvas.playSound(-1, 1);
                    this.var_byte_i = (byte)9;
                    this.var_long_c = this.time;
                } else {
                    this.var_byte_i = 0;
                }
                this.var_c_h.void_b();
                this.var_c_h = null;
                this.var_byte_b = 0;
            }
            this.var_boolean_n = true;
        } else if (this.var_byte_b == 5) {
            if (this.time - this.var_long_j >= 500L) {
                if (this.var_c_g != null) {
                    this.b(this.var_c_h, this.var_c_g);
                } else if (this.var_c_a != null) {
                    this.void_a(this.var_c_a, this.currentPlayer);
                    this.var_c_a = null;
                    this.var_byte_b = (byte)7;
                    this.var_c_h.void_b();
                }
                this.drawAreaBorder = false;
                this.useRedAreaBorder = false;
            }
        } else if (this.var_byte_b == 7) {
            if (this.var_c_e == null) {
                this.var_byte_b = 0;
                this.var_byte_i = 0;
            }
        } else if (this.var_byte_b == 6) {
            if (this.time - this.var_long_j >= 1000L) {
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
                    if (this.boolean_c(this.var_c_h.mapPixelX + 12, ((SpriteSheet)this.var_c_h).l + 12)) {
                        this.var_int_A = 1;
                        this.var_long_j = this.time;
                    }
                } else if (this.var_int_A == 1) {
                    if (this.time - this.var_long_j >= 400L) {
                        this.drawAreaBorder = true;
                        this.var_int_A = 2;
                        this.var_byte_i = 1;
                        this.var_long_j = this.time;
                    }
                } else if (this.var_int_A == 2) {
                    if (this.time - this.var_long_j >= 300L) {
                        this.mapCursorX = (short)this.var_int_f;
                        this.mapCursorY = (short)this.var_int_x;
                        this.mapCursorSheet.setMapPixelCoords(this.var_int_f * 24, this.var_int_x * 24);
                        this._pathSteps = this.var_c_h.pathSteps(this.var_c_h.mapX, this.var_c_h.mapY, this.mapCursorX, this.mapCursorY);
                        this.var_int_A = 3;
                        this.var_long_j = this.time;
                    }
                } else if (this.var_int_A == 3 && this.time - this.var_long_j >= 300L) {
                    this._pathSteps = null;
                    this.var_c_h.void_a(this.var_int_f, this.var_int_x);
                    this.var_byte_b = (byte)2;
                    this.var_int_A = 0;
                    this.var_byte_i = (byte)2;
                }
                return;
            }
            int unitsCount = this.mapUnitsList.size();
            for (int j = 0; j < unitsCount; ++j) {
                Unit c2 = this.mapUnitsList.elementAt(j);
                if (c2.owner != this.currentPlayer || c2.state == Unit.STATE_ALREADY_ACTED || c2.state == Unit.STATE_TOMBSTONE) continue;
                if (c2.unitType == Unit.KING) {
                    if (this.searchUnitsCount(SEARCH_ANY, 0, this.currentPlayer) != 1) continue;
                    // If the king is at the castle, try to buy exactly one unit
                    // The eventual new unit will be spawned immediately and added at the end of the units list,
                    // but will not perform its turn again since the loop stops iterating at the old unitsCount
                    if (this.getTerrainType(c2.mapX, c2.mapY) == f.TERRAIN_CASTLE && this.isBuildingAndOwnedByPlayer(c2.mapX, c2.mapY, this.currentPlayer)) {
                        if (this.searchUnitsCount(Unit.SOLDIER, SEARCH_ANY, this.currentPlayer) < 2 && this.canPurchaseUnit(Unit.SOLDIER)) {
                            c2 = this.buyUnitAndSpawnAtCoords(Unit.SOLDIER, c2.mapX, c2.mapY);
                        } else {
                            int suitableUnitsCount = 0;
                            byte[] suitableUnits = new byte[Unit.unitsDataPrice.length];
                            // Start from archer because soldiers were handled in the other if branch
                            for (byte unitType = Unit.ARCHER; unitType < Unit.unitsDataPrice.length; unitType++) {
                                if (this.searchUnitsCount(unitType, SEARCH_ANY, this.currentPlayer) >= 1 && Unit.unitsDataPrice[unitType] < 600 || !this.canPurchaseUnit(unitType)) continue;
                                suitableUnits[suitableUnitsCount] = unitType;
                                ++suitableUnitsCount;
                            }
                            if (suitableUnitsCount > 0) {
                                byte randomType = suitableUnits[Math.abs(AppCanvas.randomInt()) % suitableUnitsCount];
                                c2 = this.buyUnitAndSpawnAtCoords(randomType, c2.mapX, c2.mapY);
                            }
                        }
                    }
                }
                this.setMapCursorTo(c2.mapX, c2.mapY);
                this.b(c2);
                this.var_c_h = c2;
                this.var_boolean_n = true;
                this.fillMatrixWithValue_XX(this.unitActionsMatrix, 0);
                this.var_c_h.updatePathfindData(this.unitActionsMatrix);
                this.drawAreaBorder = false;
                this.var_c_arr_c = this.searchUnits(Unit.SOLDIER, SEARCH_ANY, this.currentPlayer);
                int n7 = 666;
                this.var_int_z = -1;
                for (int i = 0; i < this.var_byte_arr_arr_e.length; ++i) {
                    int mapX = this.var_byte_arr_arr_e[i][0];
                    int mapY = this.var_byte_arr_arr_e[i][1];
                    if (this.getTerrainType(mapX, mapY) != f.TERRAIN_TOWN) continue;
                    int n3 = this.isBuildingAndOwnedByPlayer(mapX, mapY, c2.owner) ? 1 : 0;
                    final int manhattanDist = Math.abs(mapX - c2.mapX) + Math.abs(mapY - c2.mapY);
                    if (this.currentLevel != CAMPAIGN_2_ESCORT && (c2.unitType != Unit.SOLDIER || n3 != 0) && (c2.unitType == Unit.SOLDIER || n3 == 0) || manhattanDist >= n7) continue;
                    n7 = manhattanDist;
                    this.var_int_z = mapX;
                    this.var_int_o = mapY;
                }
                this.var_byte_b = (byte)3;
                int maxActionValue = 0;
                // TODO this is a bit stupid since we have mapTilesHigh and mapTilesWidth, which this matrix is based on
                int mapWidth = this.unitActionsMatrix.length;
                int mapHeight = this.unitActionsMatrix[0].length;
                for (int x = 0; x < mapWidth; ++x) {
                    for (int y = 0; y < mapHeight; ++y) {
                        Unit c3 = this.tryGetUnit(x, y, SEARCH_ALIVE);
                        if (this.unitActionsMatrix[x][y] <= 0 || c3 != null && c3 != c2) continue;
                        if (!c2.isType(Unit.CATAPULT_FLAG) || c3 == c2) {
                            Unit[] cArray = c2.searchInAttackRange(x, y, Unit.FILTER_ENEMy);
                            for (int k = 0; k < cArray.length; ++k) {
                                int actionValue = this.a(c2, x, y, cArray[k], null);
                                if (actionValue <= maxActionValue) continue;
                                this.var_c_g = cArray[k];
                                maxActionValue = actionValue;
                                this.var_int_f = x;
                                this.var_int_x = y;
                            }
                        }
                        if (c2.isType(Unit.WIZARD_FLAG)) {
                            this.targetableUnits_XX = c2.searchInAttackRange(x, y, Unit.FILTER_TOMBSTONE);
                            for (int k = 0; k < this.targetableUnits_XX.length; ++k) {
                                int actionValue = this.a(c2, x, y, null, this.targetableUnits_XX[k]);
                                if (actionValue <= maxActionValue) continue;
                                this.var_c_a = this.targetableUnits_XX[k];
                                maxActionValue = actionValue;
                                this.var_int_f = x;
                                this.var_int_x = y;
                            }
                        }
                        int actionValue = this.a(c2, x, y, null, null);
                        if (actionValue <= maxActionValue) continue;
                        this.var_c_g = null;
                        this.var_c_a = null;
                        maxActionValue = actionValue;
                        this.var_int_f = x;
                        this.var_int_x = y;
                    }
                }
                return;
            }
            this.var_c_arr_c = null;
            g.a(this, null, AppCanvas.getGameText(39), 1000, true); // TURN END
            this.var_byte_i = (byte)8;
            this.var_long_c = this.time;
        }
    }

    // TODO I'm guessing this method returns the AI value of a specific action, like a basic chess AI
    private int a(Unit c2, int mapX, int mapY, Unit c3, Unit tombstone) {
        int n5 = 0;
        switch (c2.unitType) {
            case Unit.SOLDIER: {
                if (this._mapKings[c2.owner] != null && this.var_int_z != -1) {
                    int n4 = this.mapTilesWidth - Math.abs(this.var_int_z - mapX) + this.mapTilesHeight - Math.abs(this.var_int_o - mapY);
                    n5 += n4 * n4;
                }
                if (terrainMovCost[this.getTerrainType(mapX, mapY)] <= 1) {
                    n5 += 5;
                }
                for (short i = 0; i < this.var_c_arr_c.length; ++i) {
                    if (this.var_c_arr_c[i] == c2) continue;
                    int n3 = this.var_c_arr_c[i].mapX - c2.mapX + (this.var_c_arr_c[i].mapY - c2.mapY);
                    n5 += n3 * n3;
                }
                if (this.getTerrainType(mapX, mapY) != f.TERRAIN_TOWN || this.isBuildingAndOwnedByPlayer(mapX, mapY, c2.owner) || c3 != null) break;
                n5 += 200;
                break;
            }
            case Unit.WIZARD: {
                if (tombstone == null) break;
                n5 += 100;
                break;
            }
            case Unit.KING: {
                if (mapX != c2.mapX || mapY != c2.mapY) break;
                n5 += 200;
                break;
            }
        }
        if (c3 != null) {
            if(!c3.canCounterattackMelee(c2, mapX, mapY)) {
                n5 += c2.estimatedPowerAt(mapX, mapY) * 2;
            }
            else {
                n5 += c2.estimatedPowerAt(mapX, mapY) - c3.estimatedPowerAt(mapX, mapY) + 10 - c3.quantity;
            }
            if (c3.unitType == Unit.KING) {
                n5 += 10;
            }
        }
        n5 += this.getTerrainDefenceForUnit(this.getTerrainType(mapX, mapY), c2) * 2;
        for (byte playerId = 0; playerId < this._mapKings.length; ++playerId) {
            if (playerId == this.currentPlayerIndex_XX || this._mapKings[playerId] == null) continue;
            n5 += (this.mapTilesWidth - Math.abs(mapX - this._mapKings[playerId].mapX) + this.mapTilesHeight - Math.abs(mapY - this._mapKings[playerId].mapY)) * 2;
            break;
        }
        if (this.getTerrainType(mapX, mapY) == f.TERRAIN_TOWN && this.isBuildingAndOwnedByPlayer(mapX, mapY, c2.owner)) {
            n5 += (10 - c2.quantity) * 2;
        }
        if (c2.quantity < 5 && c2.unitType != Unit.SOLDIER && this.var_int_z != -1) {
            int n4 = this.mapTilesWidth - Math.abs(this.var_int_z - mapX) + this.mapTilesHeight - Math.abs(this.var_int_o - mapY);
            n5 += n4 * n4;
        }
        if (this.currentLevel == CAMPAIGN_2_ESCORT && this.var_int_z != -1) {
            int n4 = Math.abs(this.var_int_z - mapX) - 1;
            int n3 = Math.abs(this.var_int_o - mapY) - 3;
            if (n4 < 0) {
                n4 = 0;
            }
            if (n3 < 0) {
                n3 = 0;
            }
            int n6 = this.mapTilesWidth - n4 * 2 + this.mapTilesHeight - n3 * 2;
            n5 += n6 * n6;
        }
        int manhattanDist = Math.abs(mapX - c2.mapX) + Math.abs(mapY - c2.mapY);
        n5 += 10 * manhattanDist / (Unit.unitsDataMOV[c2.unitType] - 1);
        return n5;
    }

    /** Prevents showing panels or checking progress/victory/lose condition for the specified time. */
    private void pauseLevelProgress(int millis) {
        this._waitRequested = true;
        this._waitDuration = millis;
        this._waitStartTime = this.time;
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
        if (this._waitRequested) {
            if (this.time - this._waitStartTime < (long)this._waitDuration) return;
            this._waitRequested = false;
        }
        if (this.var_boolean_w) {
            if (this.currentLevelStep != 0) return;
            this.var_boolean_y = true;
            this.var_int_m = 0;
            this.var_byte_i = (byte)11;
            this.currentLevelStep = 1;
            return;
        }
        if (this.levelType == LEVEL_TYPE_SKIRMISH) {
            if (this._mapKings[PLAYER_BLUE].state != Unit.STATE_TOMBSTONE && this._mapKings[PLAYER_RED].state != Unit.STATE_TOMBSTONE) return;
            this.i();
            return;
        }
        if (this.var_byte_d != 1 || this.levelType != LEVEL_TYPE_CAMPAIGN) {
            return;
        }
        if (this.currentLevelStep == 0) {
            this._mapKings[PLAYER_BLUE].customName = AppCanvas.getGameText(43); // "GALAMAR"
            this.storyPanel.a((byte)0, 0, 0, null, 0);
            ++this.currentLevelStep;
        }
        if (this.storyPanel != null) {
            if (this.storyPanel.var_byte_e != 3) return;
            this.storyPanel = null;
            AppCanvas.stopFirstSound();
        } else if (this._cameraTargetMapX != -1) {
            if (!this.boolean_b(this._cameraTargetMapX, this._cameraTargetMapY)) return;
            this._cameraTargetMapX = -1;
            this._cameraTargetMapY = -1;
        }
        if (this.var_byte_i != 11 && this._mapKings[PLAYER_BLUE].state == Unit.STATE_TOMBSTONE) {
            this.i();
            return;
        }
        if (this.currentLevel == CAMPAIGN_0_REGROUP) {
            switch (this.currentLevelStep) {
                // TODO the step 0 is executed somewhere else...look for string 103 and this.currentLevelStep
                case 1: {
                    this.playersMoney[PLAYER_BLUE] = 0;
                    this.setCameraTargetTile(8, 9);
                    ++this.currentLevelStep;
                    break;
                }
                case 2: {
                    this.pauseLevelProgress(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    // "Sire, your troops are weary after last night's battle. [...]"
                    this.storyPanel = g.a(this, AppCanvas.getGameText(111), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    // This step will not be executed until the dialogue started in the previous step is finished
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 5: {
                    this.pauseLevelProgress(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    // This step will not be executed until the wait time (set in the previous step) is elapsed
                    // "Sound advice, captain. Ready the troops [...]"
                    this.storyPanel = g.a(this, AppCanvas.getGameText(112), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    // The game closes the previous dialogue for a moment before showing this one
                    // "Valadorn's army should be easy to spot with their red uniforms."
                    this.storyPanel = g.a(this, AppCanvas.getGameText(113), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    // Shows the panel for the map's objective, that must be closed manually
                    // The panel was created in the "loadLevelData" method
                    // The following call also automatically sets "timedInfobox"
                    this._panelMapObjective.a((byte)0, 0, 0, null, 0);
                    ++this.currentLevelStep;
                    break;
                }
                case 9: {
                    // Prevent progress until the panel with map objective is closed
                    if (this.timedInfobox != null) break;
                    this.a(true);
                    this._requestedTutorialIndex = 0;
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    // Allows to show the next tutorial when you select a unit
                    if (this.var_byte_i != 1) break;
                    this._requestedTutorialIndex = 1;
                    ++this.currentLevelStep;
                    break;
                }
                case 11: {
                    // Allows to show the next tutorial as soon as the unit finishes moving (you press END MOVE)
                    if (this.unitWhoseTurnEnded == null) break;
                    this._requestedTutorialIndex = 2;
                    ++this.currentLevelStep;
                    break;
                }
                case 12: {
                    // Again, allows to show the next tutorial when you finish moving another unit
                    // Note that this can happen in the same turn as athe previous step (which is expected)
                    // but it can also happen on your second turn. In that case you will see two tutorials in a row
                    // (since you second turn has turnIndex == 2, you shortcut the next step)
                    if (this.unitWhoseTurnEnded == null) break;
                    this._requestedTutorialIndex = 3;
                    ++this.currentLevelStep;
                    break;
                }
                case 13: {
                    // Allows to show the next tutorial as soon as you moved all three of your units
                    if (this.searchUnitsCount(SEARCH_ANY, Unit.STATE_ALREADY_ACTED, PLAYER_BLUE) >= 3) {
                        this._requestedTutorialIndex = 4;
                        ++this.currentLevelStep;
                        break;
                    }
                    if (this._turnIndex < 1) break;
                    // You can reach this if you end your first turn before moving all of your units
                    // Skips the tutorial (4) since it explains the "end turn" mechanic that you already used to get here
                    ++this.currentLevelStep;
                    break;
                }
                case 14: {
                    // Show tutorial if this is at least your second turn
                    if (this._turnIndex < 2) break;
                    this._requestedTutorialIndex = 5;
                    ++this.currentLevelStep;
                    break;
                }
                case 15: {
                    // The bridge is at (5; 8), so this step is activated when a unit goes on the bridge or below/right of it
                    if (this.unitWhoseTurnEnded == null || this.unitWhoseTurnEnded.mapX < 4 || this.unitWhoseTurnEnded.mapY < 7) break;
                    final int enemyMapX = 5;
                    final int enemyMapY = 8;
                    Unit.spawn(Unit.SOLDIER, PLAYER_RED, enemyMapX, 8);
                    this.a(this.blueSparkSheet, enemyMapX * 24, enemyMapY * 24, 0, 0, 2, 50);
                    this.a(false);
                    this.pauseLevelProgress(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 16: {
                    // "Spies!! Valadorn and his Red legion [...]"
                    this.storyPanel = g.a(this, AppCanvas.getGameText(114), PORTRAIT_CAPTAIN, (byte)4);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 17: {
                    this._requestedTutorialIndex = 6;
                    ++this.currentLevelStep;
                    break;
                }
                case 18: {
                    if (this.var_byte_i != 1 || this.currentPlayer != PLAYER_BLUE) break;
                    this._requestedTutorialIndex = 7;
                    ++this.currentLevelStep;
                    break;
                }
                case 19: {
                    // Proceed only if you conquered the castle
                    if (!this.isBuildingAndOwnedByPlayer(8, 9, PLAYER_BLUE) || this.var_byte_i != 0) break;
                    this.pauseLevelProgress(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 20: {
                    this.g();
                }
            }
            if (this.searchUnitsCount(SEARCH_ANY, Unit.STATE_TOMBSTONE, PLAYER_BLUE) == 1) {
                this.i();
            }
        } else if (this.currentLevel == CAMPAIGN_1_FRIENDS_ENEMIES) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.playersMoney[PLAYER_BLUE] = 0;
                    this.strongestAllowedUnitType = Unit.LIZARD;
                    this.setCameraTargetTile(9, 12);
                    break;
                }
                case 2: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(115), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 4: {
                    this._panelMapObjective.a((byte)0, 0, 0, null, 0);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 5: {
                    if (this.timedInfobox != null) break;
                    this._requestedTutorialIndex = 9;
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    if (!this.isBuildingAndOwnedByPlayer(9, 12, PLAYER_BLUE) && !this.isBuildingAndOwnedByPlayer(11, 13, PLAYER_BLUE)) break;
                    this._requestedTutorialIndex = 10;
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    if (!this.isBuildingAndOwnedByPlayer(9, 12, PLAYER_BLUE) || !this.isBuildingAndOwnedByPlayer(11, 13, PLAYER_BLUE)) break;
                    this._requestedTutorialIndex = 11;
                    this.a(false);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.setCameraTargetTile(12, 1);
                    break;
                }
                case 9: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(116), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 11: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(117), PORTRAIT_GALAMAR, (byte)4);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 12: {
                    if (this._mapKings[PLAYER_BLUE].mapX < 11 || this._mapKings[PLAYER_BLUE].mapY > 4 || this._mapKings[PLAYER_BLUE].state != Unit.STATE_ALREADY_ACTED) break;
                    this.setCameraTargetTile(12, 1);
                    this.a(false);
                    break;
                }
                case 13: {
                    Unit.spawn(Unit.SPIDER, PLAYER_RED, 4, 0);
                    Unit.spawn(Unit.SPIDER, PLAYER_RED, 1, 1);
                    Unit.spawn(Unit.SPIDER, PLAYER_RED, 1, 5);
                    Unit unitLizard_1 = Unit.spawn(Unit.LIZARD, PLAYER_BLUE, 12, 1);
                    this.b(unitLizard_1);
                    unitLizard_1.updatePathfindData(this.unitActionsMatrix);
                    // TODO I think this makes the lizard walk to the location
                    // Ref: https://youtu.be/6MTmxnNygSw?t=371
                    unitLizard_1.void_a(9, 2);
                    this.pauseLevelProgress(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 14: {
                    if (this.var_byte_i == 1) break;
                    Unit unitLizard_2 = Unit.spawn(Unit.LIZARD, PLAYER_BLUE, 12, 1);
                    this.b(unitLizard_2);
                    unitLizard_2.updatePathfindData(this.unitActionsMatrix);
                    unitLizard_2.void_a(10, 1);
                    this.pauseLevelProgress(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 15: {
                    if (this.var_byte_i == 1) break;
                    this.storyPanel = g.a(this, AppCanvas.getGameText(118), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 16: {
                    this.setCameraTargetTile(1, 1);
                    break;
                }
                case 17: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 18: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(119), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 19: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(120), PORTRAIT_CAPTAIN, (byte)4);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 20: {
                    this._requestedTutorialIndex = 12;
                    ++this.currentLevelStep;
                    break;
                }
                case 21: {
                    this._requestedTutorialIndex = 13;
                    ++this.currentLevelStep;
                    break;
                }
                case 22: {
                    if (this.searchUnitsCount(SEARCH_ANY, SEARCH_ANY, PLAYER_RED) != this.searchUnitsCount(SEARCH_ANY, Unit.STATE_TOMBSTONE, PLAYER_RED)) break;
                    this.pauseLevelProgress(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 23: {
                    this.g();
                }
            }
        } else if (this.currentLevel == CAMPAIGN_2_ESCORT) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.playersMoney[PLAYER_BLUE] = 0;
                    this.tryGetUnit((int)14, (int)12, SEARCH_ALIVE).customName = AppCanvas.getGameText(45); // LIZARD CHIEF
                    this.setCameraTargetTile(7, 12);
                    break;
                }
                case 2: {
                    for (int j = 5; j < 10; ++j) {
                        this.a(this.redsparkSheet, j * 24, 288, 0, 0, 4, 50);
                    }
                    AppCanvas.playSound(1, 3);
                    this.pauseLevelProgress(200);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    for (int j = 5; j < 10; ++j) {
                        this.mapTerrain[j][12] = waterTilesIndex[0];
                    }
                    this.pauseLevelProgress(300);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    // 'Sire, the bridge has been destroyed!'
                    this.storyPanel = g.a(this, AppCanvas.getGameText(121), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 5: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 6: {
                    // 'Valadorn must be expecting us - we must find another way across. This could be a trap.'
                    this.storyPanel = g.a(this, AppCanvas.getGameText(122), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    // 'Troops! Keep your eyes open and protect the Lizard Chief at all cost.'
                    this.storyPanel = g.a(this, AppCanvas.getGameText(123), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.a(true);
                    this._panelMapObjective.a((byte)0, 0, 0, null, 0);
                    ++this.currentLevelStep;
                }
            }
            if (this.currentLevelStep != -1) {
                if (this._mapKings[PLAYER_BLUE].mapX == 1 && this._mapKings[PLAYER_BLUE].mapY == 13 && this._mapKings[PLAYER_BLUE].state == Unit.STATE_ALREADY_ACTED) {
                    this.g();
                }
                if (this.searchUnitsCount(Unit.LIZARD, Unit.STATE_TOMBSTONE, PLAYER_BLUE) == 1) {
                    this.i();
                }
            }
        } else if (this.currentLevel == CAMPAIGN_3_REINFORCEMENTS) {
            if (this.var_byte_i == 1 && this.currentPlayer == PLAYER_BLUE) {
                if (this.var_boolean_A && this.var_c_h.unitType == Unit.WIZARD) {
                    this._requestedTutorialIndex = 15;
                    this.var_boolean_A = false;
                }
                if (this.var_boolean_i && this.var_c_h.unitType == Unit.WISP) {
                    this._requestedTutorialIndex = 16;
                    this.var_boolean_i = false;
                }
            }
            switch (this.currentLevelStep) {
                case 1: {
                    this.var_boolean_A = true;
                    this.var_boolean_i = true;
                    this.strongestAllowedUnitType = Unit.SPIDER;
                    this.setCameraTargetTile(2, 0);
                    break;
                }
                case 2: {
                    this.setCameraTargetTile(2, 12);
                    break;
                }
                case 3: {
                    // 'Your majesty, the Red legion! Watch out for their long range catapult!'
                    this.storyPanel = g.a(this, AppCanvas.getGameText(124), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 5: {
                    this._panelMapObjective.a((byte)0, 0, 0, null, 0);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    if (this._turnIndex != 10) break;
                    this.setCameraTargetTile(2, 0);
                    this.a(false);
                    break;
                }
                case 7: {
                    this._mapKings[PLAYER_RED] = Unit.spawn(Unit.KING, PLAYER_RED, 2, 0);
                    this._mapKings[PLAYER_RED].customName = AppCanvas.getGameText(44); // VALADORN
                    Unit.spawn(Unit.SPIDER, PLAYER_RED, 0, 0);
                    this.a(this.blueSparkSheet, 48, 0, 0, 0, 4, 50);
                    this.a(this.blueSparkSheet, 0, 0, 0, 0, 4, 50);
                    this.void_a(2, 0, PLAYER_RED);
                    this.void_a(0, 0, PLAYER_RED);
                    this.pauseLevelProgress(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    // 'What is this treachery! The city has turned against us!'
                    this.storyPanel = g.a(this, AppCanvas.getGameText(125), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 9: {
                    // 'As predictable as ever, brother! I have you now!'
                    this.storyPanel = g.a(this, AppCanvas.getGameText(126), PORTRAIT_VALADORN, (byte)8);
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 11: {
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 12: {
                    if (this._mapKings[PLAYER_RED] != null) break;
                    // 'Retreat!! Curse you Galamar! You won't be so lucky next time!'
                    this.storyPanel = g.a(this, AppCanvas.getGameText(127), PORTRAIT_VALADORN, (byte)8);
                    this.a(false);
                    ++this.currentLevelStep;
                    break;
                }
                case 13: {
                    this.pauseLevelProgress(600);
                    ++this.currentLevelStep;
                    break;
                }
                case 14: {
                    this.a(true);
                    this.g();
                }
            }
        } else if (this.currentLevel == CAMPAIGN_4_WYVERN_RESCUE) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.strongestAllowedUnitType = Unit.CATAPULT;
                    this.setCameraTargetTile(2, 2);
                    break;
                }
                case 2: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(128), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(129), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 5: {
                    this._panelMapObjective.a((byte)0, 0, 0, null, 0);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    if (this._mapKings[PLAYER_RED].state != Unit.STATE_TOMBSTONE) break;
                    this.setCameraTargetTile(2, 2);
                    this.a(false);
                    break;
                }
                case 7: {
                    // TODO no it's not unused, that spawns a new unit
                    //Unit c4 = Unit.a((byte)8, (byte)0, 2, 2); // unused
                    this.setCameraTargetTile(2, 2);
                    this.pauseLevelProgress(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.setCameraTargetTile(2, 2);
                    this.pauseLevelProgress(750);
                    break;
                }
                case 9: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(130), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    this.pauseLevelProgress(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 11: {
                    this.a(true);
                    this.g();
                }
            }
        } else if (this.currentLevel == CAMPAIGN_5_SIEGE) {
            switch (this.currentLevelStep) {
                case 1: {
                    this._mapKings[PLAYER_RED].customName = AppCanvas.getGameText(44); // VALADORN
                    this.strongestAllowedUnitType = Unit.WYVERN;
                    this.setCameraTargetTile(this._mapKings[PLAYER_RED].mapX, this._mapKings[PLAYER_RED].mapY);
                    break;
                }
                case 2: {
                    this.pauseLevelProgress(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(131), PORTRAIT_CAPTAIN, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 4: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 5: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(132), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 6: {
                    this._panelMapObjective.a((byte)0, 0, 0, null, 0);
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    if (this.timedInfobox != null) break;
                    this._requestedTutorialIndex = 17;
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    if (this._mapKings[PLAYER_RED] != null) break;
                    this.a(false);
                    ++this.currentLevelStep;
                    break;
                }
                case 9: {
                    this.pauseLevelProgress(800);
                    ++this.currentLevelStep;
                    break;
                }
                case 10: {
                    this.a(true);
                    this.g();
                }
            }
        } else if (this.currentLevel == CAMPAIGN_6_FINAL_ASSAULT) {
            switch (this.currentLevelStep) {
                case 1: {
                    this.strongestAllowedUnitType = Unit.WYVERN;
                    this.setCameraTargetTile(13, 0);
                    break;
                }
                case 2: {
                    Unit.spawn(Unit.CATAPULT, PLAYER_RED, 12, 0);
                    Unit.spawn(Unit.SOLDIER, PLAYER_RED, 13, 0);
                    this.a(this.blueSparkSheet, 312, 0, 0, 0, 4, 50);
                    this.a(this.blueSparkSheet, 288, 0, 0, 0, 4, 50);
                    this.void_a(13, 0, PLAYER_RED);
                    this.pauseLevelProgress(800);
                    ++this.currentLevelStep;
                    break;
                }
                case 3: {
                    this.setCameraTargetTile(1, 12);
                    break;
                }
                case 4: {
                    Unit.spawn(Unit.GOLEM, PLAYER_RED, 1, 11);
                    Unit.spawn(Unit.SOLDIER, PLAYER_RED, 1, 12);
                    this.a(this.blueSparkSheet, 24, 288, 0, 0, 4, 50);
                    this.a(this.blueSparkSheet, 24, 264, 0, 0, 4, 50);
                    this.void_a(1, 12, PLAYER_RED);
                    this.pauseLevelProgress(800);
                    ++this.currentLevelStep;
                    break;
                }
                case 5: {
                    this.setCameraTargetTile(1, 1);
                    break;
                }
                case 6: {
                    this._mapKings[PLAYER_RED] = Unit.spawn(Unit.KING, PLAYER_RED, 1, 1);
                    this._mapKings[PLAYER_RED].customName = AppCanvas.getGameText(44); // VALADORN
                    Unit.spawn(Unit.WYVERN, PLAYER_RED, 0, 1);
                    Unit.spawn(Unit.SOLDIER, PLAYER_RED, 1, 2);
                    this.a(this.blueSparkSheet, 24, 24, 0, 0, 4, 50);
                    this.a(this.blueSparkSheet, 0, 24, 0, 0, 4, 50);
                    this.a(this.blueSparkSheet, 24, 48, 0, 0, 4, 50);
                    this.void_a(1, 1, PLAYER_RED);
                    this.void_a(1, 2, PLAYER_RED);
                    this.pauseLevelProgress(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 7: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(133), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 8: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(134), PORTRAIT_VALADORN, (byte)8);
                    ++this.currentLevelStep;
                    break;
                }
                case 9: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_BLUE].mapX, this._mapKings[PLAYER_BLUE].mapY);
                    break;
                }
                case 10: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(135), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 11: {
                    this._panelMapObjective.a((byte)0, 0, 0, null, 0);
                    this.a(true);
                    ++this.currentLevelStep;
                    break;
                }
                case 12: {
                    if (this._mapKings[PLAYER_RED].quantity > 0) break;
                    this.a(false);
                    ++this.currentLevelStep;
                    break;
                }
                case 13: {
                    this.pauseLevelProgress(1000);
                    ++this.currentLevelStep;
                    break;
                }
                case 14: {
                    this.setCameraTargetTile(this._mapKings[PLAYER_RED].mapX, this._mapKings[PLAYER_RED].mapY);
                    break;
                }
                case 15: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(136), PORTRAIT_VALADORN, (byte)8);
                    ++this.currentLevelStep;
                    break;
                }
                case 16: {
                    this.storyPanel = g.a(this, AppCanvas.getGameText(137), PORTRAIT_GALAMAR, (byte)4);
                    ++this.currentLevelStep;
                    break;
                }
                case 17: {
                    this.pauseLevelProgress(500);
                    ++this.currentLevelStep;
                    break;
                }
                case 18: {
                    // "With Galamar victorious, the spell controlling Valadorn is broken [...]"
                    this.storyPanel = g.a(this, null, AppCanvas.getGameText(110), -1, true);
                    ++this.currentLevelStep;
                    break;
                }
                case 19: {
                    this.a(true);
                    this.i();
                }
            }
        }
        this.unitWhoseTurnEnded = null;
    }

    // TODO I'm pretty sure this is correct (or close to the answer) because it's used in campaign steps, but cannot figure out why it works. I thought this method started the camera easing but I'm still mising something
    private void setCameraTargetTile(int mapX, int mapY) {
        this._cameraTargetMapX = mapX;
        this._cameraTargetMapY = mapY;
        this.setMapCursorTo(mapX, mapY);
        ++this.currentLevelStep;
    }

    // TODO rename handleMissionComplete()
    private void g() {
        AppCanvas.playSound(2, 1);
        this.var_byte_i = (byte)10;
        g.a(this, null, AppCanvas.getGameText(37), 1000, true); // MISSION COMPLETE
        this.var_long_c = this.time;
        this.currentLevelStep = -1;
    }

    public void i() {
        this.var_boolean_w = true;
        this.currentLevelStep = 0;
        this.pauseLevelProgress(800);
    }

    // TODO there is a class with the same name in a.java (which inherits this class)
    public void a(Unit attacker, Unit defender) throws Exception {
        this.var_byte_d = (byte)2;
        this.var_boolean_e = true;
        this.var_int_m = 0;
        this.var_boolean_k = false;
        this.var_c_i = attacker;
        this.var_c_b = defender;
        //AppCanvas.readAssetsPackage("/2.pak");
        // TODO this image does not exist. A child class assign "defencepanel" to this field, which exists
        // Since the image does not exist, this is the same as assigning null
        this.spritePanelDefense = null;//new Sprite("defpanel.png");
        this.spriteSheetSoul = new SpriteSheet("soul");
        this.var_f_b = new f(this, attacker, true);
        this.var_f_a = new f(this, defender, false);
        //AppCanvas.e();
        this.var_f_b.var_f_a = this.var_f_a;
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
            appCanvas.repaint();
            appCanvas.serviceRepaints();
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
            if (this.time - this.var_long_f >= 300L) {
                this.var_f_a = null;
                this.var_f_b = null;
                this.spriteSheetSoul = null;
                this.spritePanelDefense = null;
                this.ssBattleFxWyvernFireball = null;
                this.ssBattleFxKingWave = null;
                this.ssBattleFxCatapultStone = null;
                this.ssBattleFxSwordSlash = null;
                this.var_e_f = null;
                this.ssBattleFxSpiderSpit = null;
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
        appCanvas.repaint();
        appCanvas.serviceRepaints();
    }

    public void e(Graphics graphics) {
        graphics.setClip(0, 0, AppCanvas.width2, AppCanvas.height2);
        int x = 0;
        int y = 0;
        if (this.var_boolean_q) {
            x = AppCanvas.randomInt() % 5;
            y = AppCanvas.randomInt() % 3;
        }
        this.var_f_b.a(graphics, x, y);
        this.var_f_a.a(graphics, x + AppCanvas.cenX, y);
        graphics.setColor(0);
        graphics.fillRect(AppCanvas.cenX - 1 + x, y, 2, AppCanvas.height2);
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
                    graphics.setClip(AppCanvas.cenX, 0, AppCanvas.cenX, AppCanvas.height2);
                } else {
                    graphics.setClip(0, 0, AppCanvas.cenX, AppCanvas.height2);
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
        this.var_long_b = this.time;
    }

    static {
        skirmishMapNames = new String[]{"Island Cross", "Rocky Bay"};
        mapSheetReorderTable = new byte[][]{{0, 1}, {2, 3}, {0, 1}, {4}};
        statusPoisonReorderTable = new byte[]{0};
        statusStarReorderTable = new byte[]{2};
        // These are the colors for each team - red, blue, green (unused)
        //    EDIT: actually the player with index 2 is neutral, see the code that creates the nuildings spritesheets
        // Hex values: 0xFF0000FF (blue), 0xFFFF0000 (red), 0xFF00FF00 (green)
        // Note that setColor() takes only the least significant 24bits of the number,
        // 8 per channel, so the first 8 bits (two hex digits) of these values are ignored.
        playerColors = new int[]{-16776961, -65536, -16711936};
        waterTilesIndex = new byte[]{21, 22};

        // Terrain type variables
        // TODO these have 9 elements each, check for magic numbers
        // TODO any variabe used to access these arrays are terrainType, like I did to rename all the unitType
        // TODO Rename defense -> defence in all code (it's called like this in lang.dat...devs are British)
        terrainTypeDefense = new byte[]{0, 1, 2, 2, 3, 0, 0, 3, 3};
        terrainType_XXX = new byte[]{18, 3, 1, 2, 0, 21, 20, 23, 24};   // TODO used only once in the app
        // TODO The first "mountain" should be "hill", one of the last two towns is probably the castle
        terrainTypeNames = new String[]{"road", "grass", "woods", "mountain", "mountain", "water", "bridge", "town", "town"};
        terrainMovCost = new byte[]{1, 1, 2, 2, 3, 3, 1, 1, 1};
        var_java_lang_String_arr_d = new String[] {"14281428", "18241824"};
    }
}

