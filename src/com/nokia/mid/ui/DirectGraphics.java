/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Image
 */
package com.nokia.mid.ui;

import javax.microedition.lcdui.Image;

public interface DirectGraphics {
    public static final int FLIP_HORIZONTAL = 8192;
    public static final int FLIP_VERTICAL = 16384;
    public static final int ROTATE_90 = 90;
    public static final int ROTATE_180 = 180;
    public static final int ROTATE_270 = 270;
    public static final int TYPE_BYTE_1_GRAY = 1;
    public static final int TYPE_BYTE_1_GRAY_VERTICAL = -1;
    public static final int TYPE_BYTE_2_GRAY = 2;
    public static final int TYPE_BYTE_4_GRAY = 4;
    public static final int TYPE_BYTE_8_GRAY = 8;
    public static final int TYPE_BYTE_332_RGB = 332;
    public static final int TYPE_USHORT_4444_ARGB = 4444;
    public static final int TYPE_USHORT_444_RGB = 444;
    public static final int TYPE_USHORT_555_RGB = 555;
    public static final int TYPE_USHORT_1555_ARGB = 1555;
    public static final int TYPE_USHORT_565_RGB = 565;
    public static final int TYPE_INT_888_RGB = 888;
    public static final int TYPE_INT_8888_ARGB = 8888;

    public void drawImage(Image var1, int var2, int var3, int var4, int var5);

    public void drawPixels(byte[] var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10);

    public void drawPixels(int[] var1, boolean var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10);

    public void drawPixels(short[] var1, boolean var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10);

    public void drawPolygon(int[] var1, int var2, int[] var3, int var4, int var5, int var6);

    public void drawTriangle(int var1, int var2, int var3, int var4, int var5, int var6, int var7);

    public void fillPolygon(int[] var1, int var2, int[] var3, int var4, int var5, int var6);

    public void fillTriangle(int var1, int var2, int var3, int var4, int var5, int var6, int var7);

    public int getAlphaComponent();

    public int getNativePixelFormat();

    public void getPixels(byte[] var1, byte[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9);

    public void getPixels(int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

    public void getPixels(short[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8);

    public void setARGBColor(int var1);
}

