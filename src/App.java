/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.midlet.MIDlet
 */
import javax.microedition.midlet.MIDlet;

public class App
extends MIDlet {
    public static App instance = null;
    private static AppCanvas appCanvas = null;

    public void startApp() {
        if (instance == null) {
            instance = this;
            appCanvas = new AppCanvas(this);
        }
    }

    public void destroyApp(boolean bl) {
        appCanvas.handleAppDestroy();
        appCanvas = null;
        instance = null;
    }

    public void pauseApp() {
    }
}

