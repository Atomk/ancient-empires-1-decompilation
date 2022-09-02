/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Command
 */
package com.nokia.mid.ui;

import javax.microedition.lcdui.Command;

class NokiaCommand
extends Command {
    int key;

    NokiaCommand(int key, int type) {
        super("", type, 1);
        this.key = key;
    }

    int getKey() {
        return this.key;
    }
}

