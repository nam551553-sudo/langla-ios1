package beatdz;

import beatdz.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.listener.AppEventListener;
import com.listener.SDKListener;
import java.util.Vector;

public class AppListener extends Game {

    public InputCanvas canvas;
    public static String keyApp = "4563b0fde5eb26fccee6e6a6d5f6e784";
    private static AppListener instance;
    public static mImage c;
    public static mImage d;
    public static mImage e;
    public static mImage f;
    public static boolean g;
    public int h;
    public SDKListener listener;
    public String j = null;
    public static boolean k;
    public Vector l = new Vector();
    private static boolean u = true;
    public String m;
    public String n;
    public int o = 0;
    public int width;
    public int height;
    public AppEventListener app;
    public boolean s;
    public String v = "id = Làng Lá\r\nLocalHost = 100:localhost:2907:2908";

    public void createCanvas() {
        this.canvas.setZoomLevel();
        this.canvas.create();
        DataCenter.gI().createScreen(0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    public void setAppEventListener(AppEventListener var1) {
        this.app = var1;
    }

    public static void b() {
        if (!LangLa_ep.a()) {
            (new Thread(new LangLa_ig())).start();
        }
    }

    public static void c() {
        try {
            if (c != null) {
                c.a();
            }

            if (f != null) {
                f.a();
            }

            f = Binary2.a("e", (MainScreen) null);
            c = AutoPro.getIconTool3();
        } catch (Exception var0) {
        }
    }

    public static AppListener gI() {
        if (instance == null) {
            instance = new AppListener();
        }

        return instance;
    }

    public void create() {
        String var2;
        if (Gdx.app.getGraphics().getGLVersion().getMajorVersion() < 2) {
            var2 = "Rất tiếc! Máy tính của bạn cần hổ trợ tối thiểu phiên bản OpenGL 2.0 trở lên để chơi được Làng Lá - Hoá Giải Huyết Thù.";
            String var3 = "Thông báo";
            LangLa_dd.a.a(var3, var2);
            gI();
            exit();
        } else {
            MoveSmart.a();
            instance = this;
            this.canvas = new InputCanvas();
            if (Gdx.app.getType() == ApplicationType.Desktop) {
                short[] var1 = Binary.b();
                this.width = var1[0];
                this.height = var1[1];
                if (this.width != this.canvas.getWidth() || this.height != this.canvas.height || this.height <= 240) {
                    this.setWindowedMode();
                    this.canvas.setZoomLevel();
                    this.canvas.create();
                }
            }

            DataCenter.gI().createScreen(0, this.canvas.getWidth(), this.canvas.getHeight());
            Session var10000 = Session.gI();
            var2 = keyApp;
            var10000.keyApp = var2;
            var10000 = Session.gI();
            if (Controller.instance == null) {
                Controller.instance = new Controller();
            }

            Controller var4 = Controller.instance;
            var10000.handler = var4;
            this.setScreen(this.canvas);
            this.o = 0;
        }
    }

    public void setWindowedMode() {
        if (Gdx.app.getType() == ApplicationType.Desktop) {
         //   Gdx.graphics.setWindowedMode(this.width, this.height);
        }

    }

    public AppEventListener getAppEventListener() {
        return this.app;
    }

    public static boolean isDesktop() {
        return Gdx.app.getType() == ApplicationType.Desktop && u;
    }

    public static void exit() {
        Gdx.app.exit();
        System.exit(0);
    }

    public static void i() {
        DataCenter.aR = 0;
    }

    public void j() {
    }

    public void k() {
        if (this.app != null) {
            this.app.a();
        }

    }
}
