/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;

public class SpriteSheet {
    public Sprite[] sprites;
    /**
     * Allows to change what sprite "currentIndex" refers to. Useful for creating
     * different animations using the same sheet
     */
    private byte[] spriteReorderTable;
    public short currentIndex = 0;
    public short var_short_b = 0;
    public short l = 0;
    private boolean var_boolean_c = true;
    public boolean var_boolean_a = false;
    private byte var_byte_c = 0;
    public int var_int_c;
    private int var_int_e;
    private long var_long_b = 0L;
    public int var_int_d;
    private int var_int_a;
    public boolean var_boolean_d = true;
    private int[][] var_int_arr_arr_b;
    private short[][] var_short_arr_arr_a;
    private int color_XX = 0xFFE000;   // yellow
    private byte[] var_byte_arr_d;
    private boolean[] var_boolean_arr_a;

    public SpriteSheet(String imageName) throws Exception {
        this.loadSpritesheetWithFilename(imageName, 0);
    }

    public SpriteSheet(String imageName, byte by) throws Exception {
        this.loadSpritesheetWithFilename(imageName, by);
    }

    // TODO what is the second parameter?
    private void loadSpritesheetWithFilename(String imageName, int n) throws Exception {
        InputStream inputStream = AppCanvas.getFileBytesInputStream(imageName + ".sprite");
        // A .sprite asset contains metadata for a collection of sprites
        int tileCount = inputStream.read();
        byte tileWidth = (byte)inputStream.read();
        byte tileHeight = (byte)inputStream.read();

        this.sprites = new Sprite[tileCount];

        try {
            // Loads a spritesheet image. Knowing the single tile sizes,
            // Every element in the image will become a separate sprite
            // all referencing the original spritesheet image object
            // If there is no spritesheet (no image.png) the error will
            // force executing the catch block instead (due to no file found)
            // Not really solid but I guess it works

            byte[] imageBytes = AppCanvas.getFileBytes(imageName + ".png");
            Sprite spriteSheetImage = Sprite.fromByteArray(imageBytes, n);
            int tileCountW = spriteSheetImage.width / tileWidth;
            int tileCountH = spriteSheetImage.height / tileHeight;

            int spriteIndex = 0;
            for (int yIndex = 0; yIndex < tileCountH; ++yIndex) {
                for (int xIndex = 0; xIndex < tileCountW; ++xIndex) {
                    this.sprites[spriteIndex] = new Sprite(spriteSheetImage, xIndex, yIndex, tileWidth, tileHeight);
                    ++spriteIndex;
                }
            }
        }
        catch (Exception exception) {
            // Loads a sprite collection made of single images.
            // Note that here the values of tileWidth and tileHeight
            // found in the '.sprite' asset data are ignored.
            // Also note that in this case (single images) the tile sizes in the
            // '.sprite' files may be wrong (different from the actual size of the images)

            for (int j = 0; j < tileCount; ++j) {
                String fileName = imageName + "_";
                fileName = j < 10 ? fileName + "0" + j : fileName + j;
                fileName = fileName + ".png";
                
                // TODO investigate on the difference between the two loading modes
                if (n != -1) {
                    byte[] imageBytes = AppCanvas.getFileBytes(fileName);
                    this.sprites[j] = Sprite.fromByteArray(imageBytes, n);
                    // TODO refactor for readability -- if(n == -1) ... else ---
                    continue;
                }
                this.sprites[j] = new Sprite(fileName);
            }
        }

         // TODO this sould be closed above the try-catch block, the stream is not used there
        inputStream.close();

        // By default, there is no sprite reordering
        this.spriteReorderTable = new byte[tileCount];
        for (byte j = 0; j < tileCount; j++) {
            this.spriteReorderTable[j] = j;
        }
    }

    // TODO this looks pretty similar to code above, maybe I can recycle this method to have less code
    public SpriteSheet(Sprite spriteSheetImage, int tileWidth, int tileHeight) {
        int tileCountW = spriteSheetImage.width / tileWidth;
        int tileCountH = spriteSheetImage.height / tileHeight;

        int tileCount = tileCountW * tileCountH;
        this.sprites = new Sprite[tileCount];
        for (int yIndex = 0; yIndex < tileCountH; ++yIndex) {
            for (int xIndex = 0; xIndex < tileCountW; ++xIndex) {
                this.sprites[yIndex * tileCountW + xIndex] = new Sprite(spriteSheetImage, xIndex, yIndex, tileWidth, tileHeight);
            }
        }

        this.spriteReorderTable = new byte[tileCount];
        for (byte j = 0; j < tileCount; j++) {
            this.spriteReorderTable[j] = j;
        }
    }

    public SpriteSheet(SpriteSheet other) {
        this.sprites = other.sprites;
        this.spriteReorderTable = other.spriteReorderTable;
        this.currentIndex = other.currentIndex;
        this.var_short_b = other.var_short_b;
        this.l = other.l;
        this.var_boolean_c = other.var_boolean_c;
    }

    // TODO this is used only once
    private SpriteSheet(int spritesWidth, int spritesHeight) {
        //this.spritesWidth = (short)spritesWidth;
        //this.spritesHeight = (short)spritesHeight;
    }

    public short getSpritesWidth() {
        if (this.sprites != null) {
            return this.sprites[0].width;
        }
        return 0;
    }

    public short getSpritesHeight() {
        if (this.sprites != null) {
            return this.sprites[0].height;
        }
        return 0;
    }

    // TODO note that it will do nothing if there's a custom reorder table set up
    public void setCurrentIndex(int currentIndex) {
        if (currentIndex < this.spriteReorderTable.length) {
            this.currentIndex = (byte)currentIndex;
        }
    }

    public void void_b(int mapPixelX, int mapPixelY) {
        this.var_short_b = (short)mapPixelX;
        this.l = (short)mapPixelY;
    }

    public void nextFrame() {
        this.currentIndex++;
        if (this.currentIndex >= this.spriteReorderTable.length) {
            this.currentIndex = 0;
        }
    }

    public void setReorderTable(byte[] table) {
        this.spriteReorderTable = table;
        this.currentIndex = 0;
    }

    public void a(Graphics graphics, int x, int y) {
        if (this.var_byte_c == 2 || this.var_byte_c == 4) {
            graphics.setColor(this.color_XX);
            for (int j = 0; j < 5; ++j) {
                if (!this.var_boolean_arr_a[j]) continue;
                int rectX = (this.var_int_arr_arr_b[j][0] >> 10) + x + this.var_short_b;
                int rectY = (this.var_int_arr_arr_b[j][1] >> 10) + y + this.l;
                graphics.fillRect(rectX, rectY, (int)this.var_byte_arr_d[j], (int)this.var_byte_arr_d[j]);
            }
        } else if (this.var_byte_c == 3) {
            graphics.setColor(0);
            if (this.var_int_d > 0) {
                graphics.drawLine((int)this.var_short_b, (int)this.l, this.var_short_b + 4, this.l - 2);
            } else {
                graphics.drawLine(this.var_short_b - 4, this.l - 2, (int)this.var_short_b, (int)this.l);
            }
        } else if (this.var_boolean_c) {
            int spriteX = this.var_short_b + x;
            int spriteY = this.l + y;
            /* TODO spriteReorderTable and currentIndex are closely related,
            * it should be reflected in their names. frameOrderTable and frameIndex? */
            this.sprites[this.spriteReorderTable[this.currentIndex]].draw(graphics, spriteX, spriteY);
        }
    }

    public static SpriteSheet a(SpriteSheet sheet, int n, int n2, int n3, int n4, byte by) {
        SpriteSheet newSpriteSheet = null;
        if (sheet != null) {
            newSpriteSheet = new SpriteSheet(sheet);
        } else {
            newSpriteSheet = new SpriteSheet(24, 24);
            if (by == 2 || by == 4) {
                if (by == 4) {
                    newSpriteSheet.color_XX = 0xEEEEFF;    // White (almost)
                }
                newSpriteSheet.var_int_arr_arr_b = new int[5][2];
                newSpriteSheet.var_short_arr_arr_a = new short[5][2];
                newSpriteSheet.var_byte_arr_d = new byte[5];
                newSpriteSheet.var_boolean_arr_a = new boolean[5];
                int n5 = 8192;
                int n6 = 4096;
                for (int j = 0; j < 5; ++j) {
                    newSpriteSheet.var_boolean_arr_a[j] = true;
                    if (by == 4) {
                        newSpriteSheet.var_short_arr_arr_a[j][0] = (short)(AppCanvas.randomGen.nextInt() % 4 << 10);
                        newSpriteSheet.var_short_arr_arr_a[j][1] = (short)(AppCanvas.randomGen.nextInt() % 4 << 10);
                    } else {
                        newSpriteSheet.var_short_arr_arr_a[j][0] = (short)(Math.abs(AppCanvas.randomGen.nextInt()) % n5 + -4096);
                        newSpriteSheet.var_short_arr_arr_a[j][1] = (short)(Math.abs(AppCanvas.randomGen.nextInt()) % n6 + -2048);
                    }
                    newSpriteSheet.var_byte_arr_d[j] = (byte)(Math.abs(AppCanvas.randomGen.nextInt()) % 2 + 1);
                }
            }
        }
        if (newSpriteSheet != null) {
            newSpriteSheet.var_byte_c = by;
            newSpriteSheet.var_int_c = n3;
            newSpriteSheet.var_int_e = n4;
            newSpriteSheet.var_int_d = n;
            newSpriteSheet.var_int_a = n2;
        }
        return newSpriteSheet;
    }

    public void void_a() {
        if (this.var_boolean_d) {
            this.var_long_b += 50L;
            switch (this.var_byte_c) {
                case 2: 
                case 4: {
                    this.e();
                    break;
                }
                case 3: {
                    this.void_b(this.var_short_b + this.var_int_d, this.l + this.var_int_a);
                    break;
                }
                default: {
                    this.void_b(this.var_short_b + this.var_int_d, this.l + this.var_int_a);
                    if (this.var_long_b < (long)this.var_int_e) break;
                    this.nextFrame();
                    if (this.var_byte_c == 0 && this.currentIndex == 0 && this.var_int_c > 0) {
                        --this.var_int_c;
                        if (this.var_int_c <= 0) {
                            this.var_boolean_d = false;
                        }
                    }
                    this.var_long_b = 0L;
                }
            }
        }
    }

    private void e() {
        if (this.var_byte_c != 4) {
            this.color_XX += -263168;
        }
        for (int j = 0; j < 5; ++j) {
            if (!this.var_boolean_arr_a[j]) continue;
            if (this.var_byte_c == 4) {
                int[] nArray = this.var_int_arr_arr_b[j];
                nArray[0] = nArray[0] + this.var_short_arr_arr_a[j][0];
                int[] nArray2 = this.var_int_arr_arr_b[j];
                nArray2[1] = nArray2[1] + this.var_short_arr_arr_a[j][1];
                if (this.var_short_arr_arr_a[j][0] < 0) {
                    short[] sArray = this.var_short_arr_arr_a[j];
                    sArray[0] = (short)(sArray[0] + 256);
                } else if (this.var_short_arr_arr_a[j][0] > 0) {
                    short[] sArray = this.var_short_arr_arr_a[j];
                    sArray[0] = (short)(sArray[0] - 256);
                }
                if (this.var_short_arr_arr_a[j][1] < 0) {
                    short[] sArray = this.var_short_arr_arr_a[j];
                    sArray[1] = (short)(sArray[1] + 256);
                    continue;
                }
                if (this.var_short_arr_arr_a[j][1] <= 0) continue;
                short[] sArray = this.var_short_arr_arr_a[j];
                sArray[1] = (short)(sArray[1] - 256);
                continue;
            }
            int[] nArray = this.var_int_arr_arr_b[j];
            nArray[0] = nArray[0] + this.var_short_arr_arr_a[j][0];
            int[] nArray3 = this.var_int_arr_arr_b[j];
            nArray3[1] = nArray3[1] + this.var_short_arr_arr_a[j][1];
            short[] sArray = this.var_short_arr_arr_a[j];
            sArray[1] = (short)(sArray[1] + 256);
        }
        if (this.var_long_b >= (long)this.var_int_e) {
            this.var_boolean_d = false;
        }
    }
}

