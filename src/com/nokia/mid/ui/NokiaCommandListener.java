/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.lcdui.Command
 *  javax.microedition.lcdui.CommandListener
 *  javax.microedition.lcdui.Displayable
 */
package com.nokia.mid.ui;

import com.nokia.mid.ui.FullCanvas;
import com.nokia.mid.ui.NokiaCommand;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

class NokiaCommandListener
implements CommandListener {
    FullCanvas fc;

    NokiaCommandListener(FullCanvas f2) {
        this.fc = f2;
    }

    public void commandAction(Command c2, Displayable d2) {
        this.fc.press(((NokiaCommand)c2).getKey());
    }
}

