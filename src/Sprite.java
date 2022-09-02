/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Sprite {
    public Image image;
    private boolean var_boolean_a = false;
    private short b;
    private short var_short_a;
    public short width;
    public short height;

    public Sprite(Sprite h2, int n, int n2, int width, int height) {
        this.image = h2.image;
        this.width = (short)width;
        this.height = (short)height;
        this.b = (short)(n * width + h2.b);
        this.var_short_a = (short)(n2 * height + h2.var_short_a);
        this.var_boolean_a = true;
    }

    public Sprite(String filename) throws Exception {
        byte[] imageData = d.byte_arr_a(filename);
        this.image = Image.createImage((byte[])imageData, (int)0, (int)imageData.length);
        this.width = (short)this.image.getWidth();
        this.height = (short)this.image.getHeight();
        this.var_boolean_a = false;
    }

    public void draw(Graphics graphics, int x, int y) {
        if (this.var_boolean_a) {
            int clipX = graphics.getClipX();
            int clipY = graphics.getClipY();
            int clipWidth = graphics.getClipWidth();
            int clipHeight = graphics.getClipHeight();
            graphics.clipRect(x, y, (int)this.width, (int)this.height);
            graphics.drawImage(this.image, x - this.b, y - this.var_short_a, 20);
            graphics.setClip(clipX, clipY, clipWidth, clipHeight);
        } else {
            graphics.drawImage(this.image, x, y, 20);
        }
    }

    private Sprite() {
    }

    public static Sprite h_a(byte[] imageData, int n) {
        Object object;
        if (n != 0) {
            object = new byte[imageData.length];
            System.arraycopy(imageData, 0, object, 0, imageData.length);
            Sprite.void_a(object, n);
            imageData = object;
        }
        object = new Sprite();
        Image image = Image.createImage((byte[])imageData, (int)0, (int)imageData.length);
        object.width = (short)image.getWidth();
        object.height = (short)image.getHeight();
        object.image = image;
        return object;
    }

    public static void void_a(byte[] byArray, int n) {
        try {
            int n2;
            int n3;
            int n4;
            int n5 = 33;
            int n6 = byArray.length - 3;
            for (n4 = 0; n4 < n6; ++n4) {
                if (byArray[n4] != 80 || byArray[n4 + 1] != 76 || byArray[n4 + 2] != 84) continue;
                n5 = n4 - 4;
                break;
            }
            n4 = n5;
            n6 = 0;
            n6 = ((byArray[n4] & 0xFF) << 24 | (byArray[n4 + 1] & 0xFF) << 16 | (byArray[n4 + 2] & 0xFF) << 8 | byArray[n4 + 3] & 0xFF) & 0xFFFFFFFF;
            n4 += 4;
            int n7 = -1;
            for (n3 = 0; n3 < 4; ++n3) {
                n7 = Sprite.a(byArray[n4 + n3], n7);
            }
            n3 = 1;
            boolean bl = false;
            boolean bl2 = false;
            for (n2 = n4 += 4; n2 < n4 + n6; n2 += 3) {
                int n8 = byArray[n2] & 0xFF;
                int n9 = byArray[n2 + 1] & 0xFF;
                int n10 = byArray[n2 + 2] & 0xFF;
                if (n10 > n8 && n10 > n9) {
                    if (n == 1) {
                        int n11 = n8;
                        n8 = n10;
                        n10 = n11;
                        n9 /= 2;
                    } else if (n == 2) {
                        n8 = n10;
                        n9 = n10;
                    }
                    byArray[n2] = (byte)n8;
                    byArray[n2 + 1] = (byte)n9;
                    byArray[n2 + 2] = (byte)n10;
                }
                n7 = Sprite.a(byArray[n2], n7);
                n7 = Sprite.a(byArray[n2 + 1], n7);
                n7 = Sprite.a(byArray[n2 + 2], n7);
            }
            n2 = n5 + 8 + n6;
            byArray[n2] = (byte)((n7 ^= 0xFFFFFFFF) >> 24);
            byArray[n2 + 1] = (byte)(n7 >> 16);
            byArray[n2 + 2] = (byte)(n7 >> 8);
            byArray[n2 + 3] = (byte)n7;
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static int a(byte by, int n) {
        int n2 = by & 0xFF;
        n ^= n2;
        for (int j = 0; j < 8; ++j) {
            if ((n & 1) != 0) {
                n = n >>> 1 ^ 0xEDB88320;
                continue;
            }
            n >>>= 1;
        }
        return n;
    }
}

