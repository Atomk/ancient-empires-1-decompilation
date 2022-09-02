/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Canvas
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 */
package com.nokia.mid.ui;

import com.nokia.mid.ui.NokiaCommand;
import com.nokia.mid.ui.NokiaCommandListener;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

public abstract class FullCanvas
extends Canvas {
    public static final int KEY_SOFTKEY1 = -6;
    public static final int KEY_SOFTKEY2 = -7;
    public static final int KEY_SEND = -10;
    public static final int KEY_END = -11;
    public static final int KEY_SOFTKEY3 = -5;
    public static final int KEY_UP_ARROW = -1;
    public static final int KEY_DOWN_ARROW = -2;
    public static final int KEY_LEFT_ARROW = -3;
    public static final int KEY_RIGHT_ARROW = -4;

    protected FullCanvas() {
        super.setFullScreenMode(true);
        super.addCommand((Command)new NokiaCommand(-6, 4));
        super.addCommand((Command)new NokiaCommand(-7, 2));
        super.setCommandListener((CommandListener)new NokiaCommandListener(this));
    }

    public void addCommand(Command cmd) {
        throw new IllegalStateException();
    }

    public int getWidth() {
        return super.getWidth();
    }

    public int getHeight() {
        return super.getHeight();
    }

    public void setCommandListener(CommandListener l) {
        throw new IllegalStateException();
    }

    void press(int i2) {
        this.keyPressed(i2);
        this.keyReleased(i2);
    }
}

