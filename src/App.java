/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.midlet.MIDlet
 */
import javax.microedition.midlet.MIDlet;

public class App
extends MIDlet {
    public static App instance;
    public static AppCanvas var_d_a;

    public void startApp() {
        if (instance == null) {
            instance = this;
            var_d_a = new AppCanvas(this);
        }
    }

    public void destroyApp(boolean bl) {
        var_d_a.d();
        var_d_a = null;
        instance = null;
    }

    public void pauseApp() {
    }

    static {
        instance = null;
        var_d_a = null;
    }
}

