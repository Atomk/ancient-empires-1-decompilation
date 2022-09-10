import javax.microedition.midlet.MIDlet;

// Application entry point
// https://it.wikipedia.org/wiki/MIDlet
// https://docs.oracle.com/javame/8.0/api/meep/api/javax/microedition/midlet/MIDlet.html
public class App
extends MIDlet {
    public static App instance = null;
    private static AppCanvas appCanvas = null;

    public void startApp() {
        if (instance == null) {
            instance = this;
            appCanvas = new AppCanvas(this);
        }
        //else {
        //  // Only in v1.0.1
        //  appCanvas.f();
        //}
    }

    public void destroyApp(boolean bl) {
        // Only in Motorola versions, this block replaces the first statement below:
        //if (appCanvas != null) {
        //    appCanvas.b();
        //}
        appCanvas.handleAppDestroy();
        appCanvas = null;
        instance = null;
    }

    public void pauseApp() {
        // Only in v1.0.1
        //if (appCanvas != null) {
        //  appCanvas.d();
        //}
    }
}

