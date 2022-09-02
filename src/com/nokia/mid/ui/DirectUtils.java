/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Graphics
 *  javax.microedition.lcdui.Image
 */
package com.nokia.mid.ui;

import com.nokia.mid.ui.DirectGraphics;
import com.nokia.mid.ui.DirectGraphicsImp;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class DirectUtils {
    public static DirectGraphics getDirectGraphics(Graphics g2) {
        return new DirectGraphicsImp(g2);
    }

    public static Image createImage(byte[] imageData, int imageOffset, int imageLength) {
        Image source = Image.createImage((byte[])imageData, (int)imageOffset, (int)imageLength);
        Image target = Image.createImage((int)source.getWidth(), (int)source.getHeight());
        target.getGraphics().drawImage(source, 0, 0, 0);
        return target;
    }

    public static Image createImage(int width, int height, int argb) {
        Image img = Image.createImage((int)width, (int)height);
        Graphics g2 = img.getGraphics();
        g2.setColor(argb);
        g2.fillRect(0, 0, width, height);
        g2.setColor(0);
        return img;
    }
}

