/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 */
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;

public class e {
    public Sprite[] sprites;
    private byte[] var_byte_arr_e;
    public short var_short_d = 0;
    public short var_short_b = 0;
    public short l = 0;
    private boolean var_boolean_c = true;
    private short spritesWidth;
    private short spritesHeight;
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
    private int f = 0xFFE000;
    private byte[] var_byte_arr_d;
    private boolean[] var_boolean_arr_a;

    public e(String imageName) throws Exception {
        this.a(imageName, 0);
    }

    public e(String imageName, byte by) throws Exception {
        this.a(imageName, by);
    }

    private void a(String imageName, int n) throws Exception {
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
            // Loads a sprite collection made of single images
            // Note that here the values of tileWidth and tileHeight
            // found in the .sprite asset data are ignored
            // Also note that in this case (single images) the tile sizes in the
            // sprite files may be wrong (different from the actual size of the images)

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
        this.spritesWidth = this.sprites[0].width;
        this.spritesHeight = this.sprites[0].height;

        this.var_byte_arr_e = new byte[tileCount];
        for (int n6 = 0; n6 < tileCount; n6 = (byte)(n6 + 1)) {
            this.var_byte_arr_e[n6] = (byte)n6;
        }
    }

    public e(Sprite sprite, int width, int height) {
        int n3;
        int n4 = sprite.width / width;
        int n5 = sprite.height / height;
        int n6 = n4 * n5;
        this.sprites = new Sprite[n6];
        for (n3 = 0; n3 < n5; ++n3) {
            for (int j = 0; j < n4; ++j) {
                this.sprites[n3 * n4 + j] = new Sprite(sprite, j, n3, width, height);
            }
        }
        this.spritesWidth = (short)width;
        this.spritesHeight = (short)height;
        this.var_byte_arr_e = new byte[n6];
        for (n3 = 0; n3 < n6; n3 = (int)((byte)(n3 + 1))) {
            this.var_byte_arr_e[n3] = (byte)n3;
        }
    }

    public e(e e2) {
        this.sprites = e2.sprites;
        this.var_byte_arr_e = e2.var_byte_arr_e;
        this.var_short_d = e2.var_short_d;
        this.var_short_b = e2.var_short_b;
        this.l = e2.l;
        this.var_boolean_c = e2.var_boolean_c;
        this.spritesWidth = e2.spritesWidth;
        this.spritesHeight = e2.spritesHeight;
    }

    // TODO this is used only once
    private e(int n, int n2) {
        this.spritesWidth = (short)n;
        this.spritesHeight = (short)n2;
    }

    public short short_a() {
        if (this.sprites != null) {
            return this.sprites[0].width;
        }
        return 0;
    }

    public short short_b() {
        if (this.sprites != null) {
            return this.sprites[0].height;
        }
        return 0;
    }

    public void a(int n) {
        if (n < this.var_byte_arr_e.length) {
            this.var_short_d = (byte)n;
        }
    }

    public void void_b(int n, int n2) {
        this.var_short_b = (short)n;
        this.l = (short)n2;
    }

    public void c() {
        this.var_short_d = (short)(this.var_short_d + 1);
        if (this.var_short_d >= this.var_byte_arr_e.length) {
            this.var_short_d = 0;
        }
    }

    public void a(byte[] byArray) {
        this.var_byte_arr_e = byArray;
        this.var_short_d = 0;
    }

    public void a(Graphics graphics, int x, int y) {
        if (this.var_byte_c == 2 || this.var_byte_c == 4) {
            graphics.setColor(this.f);
            for (int j = 0; j < 5; ++j) {
                if (!this.var_boolean_arr_a[j]) continue;
                int n3 = (this.var_int_arr_arr_b[j][0] >> 10) + x + this.var_short_b;
                int n4 = (this.var_int_arr_arr_b[j][1] >> 10) + y + this.l;
                graphics.fillRect(n3, n4, (int)this.var_byte_arr_d[j], (int)this.var_byte_arr_d[j]);
            }
        } else if (this.var_byte_c == 3) {
            graphics.setColor(0);
            if (this.var_int_d > 0) {
                graphics.drawLine((int)this.var_short_b, (int)this.l, this.var_short_b + 4, this.l - 2);
            } else {
                graphics.drawLine(this.var_short_b - 4, this.l - 2, (int)this.var_short_b, (int)this.l);
            }
        } else if (this.var_boolean_c) {
            int n5 = this.var_short_b + x;
            int n6 = this.l + y;
            this.sprites[this.var_byte_arr_e[this.var_short_d]].draw(graphics, n5, n6);
        }
    }

    public static e a(e e2, int n, int n2, int n3, int n4, byte by) {
        e e3 = null;
        if (e2 != null) {
            e3 = new e(e2);
        } else {
            e3 = new e(24, 24);
            if (by == 2 || by == 4) {
                if (by == 4) {
                    e3.f = 0xEEEEFF;
                }
                e3.var_int_arr_arr_b = new int[5][2];
                e3.var_short_arr_arr_a = new short[5][2];
                e3.var_byte_arr_d = new byte[5];
                e3.var_boolean_arr_a = new boolean[5];
                int n5 = 8192;
                int n6 = 4096;
                for (int j = 0; j < 5; ++j) {
                    e3.var_boolean_arr_a[j] = true;
                    if (by == 4) {
                        e3.var_short_arr_arr_a[j][0] = (short)(AppCanvas.randomGen.nextInt() % 4 << 10);
                        e3.var_short_arr_arr_a[j][1] = (short)(AppCanvas.randomGen.nextInt() % 4 << 10);
                    } else {
                        e3.var_short_arr_arr_a[j][0] = (short)(Math.abs(AppCanvas.randomGen.nextInt()) % n5 + -4096);
                        e3.var_short_arr_arr_a[j][1] = (short)(Math.abs(AppCanvas.randomGen.nextInt()) % n6 + -2048);
                    }
                    e3.var_byte_arr_d[j] = (byte)(Math.abs(AppCanvas.randomGen.nextInt()) % 2 + 1);
                }
            }
        }
        if (e3 != null) {
            e3.var_byte_c = by;
            e3.var_int_c = n3;
            e3.var_int_e = n4;
            e3.var_int_d = n;
            e3.var_int_a = n2;
        }
        return e3;
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
                    this.c();
                    if (this.var_byte_c == 0 && this.var_short_d == 0 && this.var_int_c > 0) {
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
            this.f += -263168;
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

