import javax.microedition.midlet.MIDlet;

// Application entry point
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
    }

    public void destroyApp(boolean bl) {
        appCanvas.handleAppDestroy();
        appCanvas = null;
        instance = null;
    }

    public void pauseApp() {
    }
}

