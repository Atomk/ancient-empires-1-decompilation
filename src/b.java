/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.microedition.midlet.MIDlet
 */
import javax.microedition.midlet.MIDlet;

public class b
extends MIDlet {
    public static b var_b_a;
    public static d var_d_a;

    public void startApp() {
        if (var_b_a == null) {
            var_b_a = this;
            var_d_a = new d(this);
        }
    }

    public void destroyApp(boolean bl) {
        var_d_a.d();
        var_d_a = null;
        var_b_a = null;
    }

    public void pauseApp() {
    }

    static {
        var_b_a = null;
        var_d_a = null;
    }
}

