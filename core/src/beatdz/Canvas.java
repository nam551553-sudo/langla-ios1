package beatdz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.net.InetAddress;
import java.util.Vector;

public abstract class Canvas implements Screen {

    public Graphics graphics;
    public static float elapsed; //sửa
    public OrthographicCamera camera;
    public OrthographicCamera hudCamera;
    public int width;
    public int height;
    public Viewport viewport;
    private InputMultiplexer input;
    public Stage stage;
    public int g = 30;
    public static Vector vTexture = new Vector();
    public static Vector vImage = new Vector();
    private boolean l = false;
    public static boolean disposeGraphics = false;
    private static long m;
    private static int n;
    private static int bientuychinh = 2; //sửa

    private int frameCount = 0;
    private long startTime = System.currentTimeMillis();
    private static int fps = 0;
    private static long ping; //sửa


    public static void setBientuychinh(int value) {
        bientuychinh = value;
    }


    public static int getBientuychinh() {
        return bientuychinh;
    }

    public static int getfps() {
        return fps;
    }


    public static long getping() { // sửa
        return ping;
    }



    public Canvas() {
        try {
            this.create();
        } catch (Exception var2) {
            Utlis.println(var2);
        }
    }

    public void create() {
        try {
            this.width = Gdx.graphics.getWidth();
            this.height = Gdx.graphics.getHeight();
            Vector2 vector2;
            int x = (int) (vector2 = new Vector2()).x;
            int y = (int) vector2.y;
            this.width -= x * 2;
            this.camera = new OrthographicCamera((float) this.width, (float) this.height);
            this.camera.setToOrtho(true, (float) this.width, (float) this.height);
            this.graphics = new Graphics(this);
            this.setZoomLevel();
            this.hudCamera = new OrthographicCamera((float) this.width, (float) this.height);
            this.hudCamera.setToOrtho(true, (float) this.width, (float) this.height);
            this.hudCamera.update();
            Graphics.createFont();
            this.viewport = new MyViewport(Scaling.fill, (float) (this.width * DataCenter.gI().zoomLevel / DataCenter.gI().zoomLevelScreen - this.width % DataCenter.gI().zoomLevelScreen), (float) (this.height * DataCenter.gI().zoomLevel / DataCenter.gI().zoomLevelScreen - this.height % DataCenter.gI().zoomLevelScreen), this.camera);
            ((MyViewport) this.viewport).x = x;
            ((MyViewport) this.viewport).y = y;
            this.stage = new Stage(this.viewport, this.graphics.getSpriteBatch());
            this.input = new InputMultiplexer();
            this.input.addProcessor(new mInputProcessor(this, -x, 0));
            this.input.addProcessor(this.stage);
            Gdx.input.setInputProcessor(this.input);
            Gdx.input.setCatchBackKey(true);
            Gdx.input.setCatchMenuKey(true);
        } catch (Exception var5) {
            Utlis.println(var5);
        }
    }

    public void setZoomLevel() {
        if (Gdx.app.getType() != ApplicationType.Android && Gdx.app.getType() != ApplicationType.iOS) {
            if (this.width >= 640 && this.height >= 480) {
                DataCenter.gI().zoomLevel = 2;
            } else {
                DataCenter.gI().zoomLevel = 1;
            }
        } else if (this.width >= 2560 && this.height >= 1440) {
            DataCenter.gI().zoomLevel = 6;
        } else if (this.width >= 1920 && this.height >= 1200) {
            DataCenter.gI().zoomLevel = 5;
        } else if (this.width >= 1280 && this.height >= 960) {
            DataCenter.gI().zoomLevel = 4;
        } else if (this.width >= 960 && this.height >= 720) {
            DataCenter.gI().zoomLevel = 3;
        } else if (this.width >= 640 && this.height >= 480) {
            DataCenter.gI().zoomLevel = 2;
        } else {
            DataCenter.gI().zoomLevel = 1;
        }

        if (mConfig.gI().readBoolean(15)) {
            --DataCenter.gI().zoomLevel;
        }

        DataCenter.gI().zoomLevel = Math.max(1, DataCenter.gI().zoomLevel);
        DataCenter.gI().zoomLevelScreen = DataCenter.gI().zoomLevel;
        if (DataCenter.gI().zoomLevelScreen == 3 && (mConfig.gI().readByte(2) == 0 || mConfig.gI().readByte(2) == 3)) {
            DataCenter.gI().zoomLevel = 2;
        } else if ((DataCenter.gI().zoomLevelScreen == 4 || DataCenter.gI().zoomLevelScreen == 5 || DataCenter.gI().zoomLevelScreen == 6) && mConfig.gI().readByte(2) == 0) {
            DataCenter.gI().zoomLevel = 3;
        }

        if (DataCenter.gI().zoomLevel > 4) {
            DataCenter.gI().zoomLevel = 4;
        }

        int zl = DataCenter.gI().zoomLevel < 1 ? 1 : DataCenter.gI().zoomLevel;
        DataCenter.gI().widthHud = this.width / zl;
        DataCenter.gI().heightHud = this.height / zl;
    }


    private long startPingTime; // Biến để lưu thời gian bắt đầu tính ping
    private void calculatePing() { //sửa
        try {
            // Khởi tạo socket để kết nối đến server (ví dụ: Google DNS)
            InetAddress address = InetAddress.getByName("8.8.8.8"); // Google DNS
            startPingTime = System.currentTimeMillis(); // Ghi lại thời gian bắt đầu gửi gói tin
            boolean reachable = address.isReachable(1000); // Kiểm tra kết nối, timeout 1 giây

            if (reachable) {
                ping = System.currentTimeMillis() - startPingTime; // Tính thời gian phản hồi
            } else {
                ping = -1; // Nếu không thể kết nối, gán ping là -1
            }

        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    private void render(boolean var1) { //sửa

        // Tính thời gian hiện tại và cập nhật số khung hình
        frameCount++; // Tăng số khung hình

        long currentTime = System.currentTimeMillis(); // Lấy thời gian hiện tại

        // Nếu đã qua 1 giây (1000 milliseconds)
        if (currentTime - startTime >= 1000) {
            fps = frameCount;  // Gán số khung hình vào FPS
            frameCount = 0;    // Đặt lại số khung hình về 0
            startTime = currentTime;  // Đặt lại thời gian bắt đầu

            // In ra số FPS
            //  System.out.println("FPS: " + fps);
            // Tính ping
           // calculatePing(); // Gọi phương thức tính ping
            //  System.out.println("FPS: " + fps + " | Ping: " + ping + " ms");
        }



        elapsed += Gdx.graphics.getDeltaTime(); //ửa
        long var2 = System.currentTimeMillis();

        int var15;
        try {
            Graphics var4;
            if (disposeGraphics) {
                disposeGraphics = false;
                var4 = this.graphics;

                try {
                    if (var4.spriteBatch != null) {
                        var4.spriteBatch.dispose();
                        var4.spriteBatch = null;
                    }

                    var4.getSpriteBatch().setProjectionMatrix(var4.canvas.camera.combined);
                    var4.shapeRenderer.dispose();
                    var4.shapeRenderer = new ShapeRenderer();
                    var4.shapeRenderer.setProjectionMatrix(var4.canvas.camera.combined);
                } catch (Exception var12) {
                    Utlis.println(var12);
                }
            }

            ++DataCenter.gI().i;
            ++DataCenter.gI().h;
            DataCenter.gI().aO.a = false;
            this.camera.update();
            this.update();
            if (var1) {
                if (!Graphics.m()) {
                    Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
                    Gdx.gl.glClear(16384);
                    Gdx.gl.glEnable(3042);
                    Gdx.gl20.glLineWidth((float) DataCenter.gI().zoomLevel);
                    var4 = this.graphics;

                    try {
                        var4.shapeRenderer.setProjectionMatrix(var4.canvas.camera.combined);
                        var4.getSpriteBatch().setProjectionMatrix(var4.canvas.camera.combined);
                    } catch (Exception var11) {
                        Utlis.println(var11);
                    }

                    var4 = this.graphics;

                    try {
                        if (mConfig.gI().readByte(18) > 1) {
                            if (var4.spriteBatch == null) {
                                var4.dispose();
                            }
                        } else {
                            var4.getSpriteBatch().begin();
                            var4.getSpriteBatch().enableBlending();

                        }
                    } catch (Exception var10) {
                        Utlis.println(var10);
                    }

                    Graphics.inHudPass = false;
                    this.graphics.clean();
                    this.graphics.setClip(0, 0, DataCenter.gI().widthScreen, DataCenter.gI().heightScreen);
                    this.paint(this.graphics);
                    LangLa_gg var10000 = DataCenter.gI().aO;
                    Graphics var5 = this.graphics;
                    LangLa_gg var16 = var10000;
                    GameSrc var14;
                    if (var10000.a && GameSrc.gI().i() && !GameSrc.gI().bb && ((var14 = GameSrc.gI()).cL == null || !(var14.cL instanceof LangLa_kn))) {
                        if (DataCenter.gI().i % 6 >= 3) {
                            LangLa_fq.a(var5, 419, 0, var16.b + 1, var16.c + 1, 20);
                        } else {
                            LangLa_fq.a(var5, 419, 0, var16.b, var16.c, 20);
                        }

                        if (var16.f && LangLa_gg.e != null && var16.d <= 4) {
                            var5.a(LangLa_gg.e, 0, 0, LangLa_gg.e.c, LangLa_gg.e.d, 0, var16.b - 25, var16.c - 25, 50 * DataCenter.gI().zoomLevel, 50 * DataCenter.gI().zoomLevel, 0);
                            var5.f(0);
                            var5.d(60);
                            var5.c(0, 0, var16.b - 25, DataCenter.gI().heightScreen);
                            var5.c(var16.b + 25, 0, DataCenter.gI().widthScreen - (var16.b + 25), DataCenter.gI().heightScreen);
                            var5.c(var16.b - 25, 0, 50, var16.c - 25);
                            var5.c(var16.b - 25, var16.c + 25, 50, DataCenter.gI().heightScreen - (var16.c + 25));
                            var5.m = 1.0F;
                            var5.c();
                        }
                    }

                    try {
                        if (mConfig.gI().readByte(18) > 1) {
                            if (var4.spriteBatch == null) {
                                var4.dispose();
                            }
                        } else {
                            var4.j = false;
                            var4.getSpriteBatch().end();
                            var4 = this.graphics;

                            if (!var4.g()) {
                                var4.getSpriteBatch().flush();
                                ScissorStack.popScissors();
                            }
                        }
                    } catch (Exception var9) {
                    }
                } else {
                    Gdx.gl.glClear(16384);
                }
            }

            while (vTexture.size() > 0) {
                try {
                    ((Texture) vTexture.remove(0)).dispose();
                } catch (Exception var8) {
                    Utlis.println("khong xoa dc textures");
                }
            }

            for (var15 = vImage.size() - 1; var15 >= 0; --var15) {
                try {
                    mImage var17 = (mImage) vImage.get(var15);
                    if (var2 - var17.e > 10000L) {
                        vImage.remove(var17);
                        var17.c();
                    }
                } catch (Exception var7) {
                    Utlis.println("khong xoa dc textures");
                }
            }
        } catch (Exception var13) {
            Utlis.println(var13);
        }

        if (Session.r > 0) {
            --Session.r;
        }

        if ((var15 = (25) - (int) (System.currentTimeMillis() - var2)) <= 0) {
            var15 = 1;
        }

        var15 = var15 / bientuychinh;

        // iOS presents frames at the backend's preferred refresh rate.
        if (Gdx.app.getType() != ApplicationType.iOS) {
            Utlis.sleep((long) var15);
        }
    }

    public void render(float var1) {
        this.render(true);
        this.stage.act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333335F));
        this.stage.draw();
    }

    public int getWidth() {
        return DataCenter.gI().zoomLevel != DataCenter.gI().zoomLevelScreen ? this.width / DataCenter.gI().zoomLevelScreen : this.width / DataCenter.gI().zoomLevel;
    }

    public int getHeight() {
        return DataCenter.gI().zoomLevel != DataCenter.gI().zoomLevelScreen ? this.height / DataCenter.gI().zoomLevelScreen : this.height / DataCenter.gI().zoomLevel;
    }

    public void changeZoomScreen(int delta) {
        resetZoomScreen();
    }

    public void resetZoomScreen() {
        try {
            if (DataCenter.gI().zoomLevelScreen == DataCenter.gI().zoomLevel) {
                return;
            }
            DataCenter.gI().zoomLevelScreen = DataCenter.gI().zoomLevel;
            applyZoomScreen();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    private void applyZoomScreen() {
        DataCenter.gI().widthScreen = this.getWidth();
        DataCenter.gI().heightScreen = this.getHeight();

        float worldW = (float) (this.width * DataCenter.gI().zoomLevel / DataCenter.gI().zoomLevelScreen - this.width % DataCenter.gI().zoomLevelScreen);
        float worldH = (float) (this.height * DataCenter.gI().zoomLevel / DataCenter.gI().zoomLevelScreen - this.height % DataCenter.gI().zoomLevelScreen);
        this.viewport.setWorldSize(worldW, worldH);
        this.viewport.update(this.width, this.height, true);
    }

    public void beginHudPass(Graphics g) {
        try {
            if (g == null || this.hudCamera == null) return;
            Graphics.inHudPass = true;
            if (g.spriteBatch != null) {
                g.spriteBatch.setProjectionMatrix(this.hudCamera.combined);
            }
            if (g.shapeRenderer != null && g.shapeRenderer.isDrawing()) {
                ShapeRenderer.ShapeType st = g.shapeRenderer.getCurrentType();
                g.shapeRenderer.end();
                g.shapeRenderer.setProjectionMatrix(this.hudCamera.combined);
                if (st != null) g.shapeRenderer.begin(st);
            }
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    public void endHudPass(Graphics g) {
        try {
            if (g == null || this.camera == null) return;
            Graphics.inHudPass = false;
            if (g.spriteBatch != null) {
                g.spriteBatch.setProjectionMatrix(this.camera.combined);
            }
            if (g.shapeRenderer != null && g.shapeRenderer.isDrawing()) {
                ShapeRenderer.ShapeType st = g.shapeRenderer.getCurrentType();
                g.shapeRenderer.end();
                g.shapeRenderer.setProjectionMatrix(this.camera.combined);
                if (st != null) g.shapeRenderer.begin(st);
            }
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    public abstract void update();

    public abstract void paint(Graphics var1);

    public abstract void a(int var1);

    public abstract void a(char var1);

    public abstract void b(int var1);

    public abstract void a(int var1, int var2);

    public abstract void b(int var1, int var2);

    public abstract void c(int var1, int var2);

    public void show() {
    }

    public void resize(int var1, int var2) {
        if (var1 <= 0 || var2 <= 0) return;
        this.width = var1;
        this.height = var2;
        this.hudCamera.setToOrtho(true, var1, var2);
        this.hudCamera.update();
        int zoom = Math.max(1, DataCenter.gI().zoomLevel);
        DataCenter.gI().widthHud = var1 / zoom;
        DataCenter.gI().heightHud = var2 / zoom;
        this.applyZoomScreen();
        if (DataCenter.gI().currentScreen != null) {
            DataCenter.gI().currentScreen.setSize(DataCenter.gI().widthHud, DataCenter.gI().heightHud);
        }
        this.stage.getViewport().update(this.width, this.height, true);
    }

    public void pause() {
        LangLa_dp var1;
        (var1 = LangLa_dp.a()).a = true;
        var1.b();
        if (GameSrc.gI().cx.size() > 0) {
            for (int var2 = GameSrc.gI().cx.size() - 1; var2 >= 0; --var2) {
                ((LangLa_en) GameSrc.gI().cx.get(var2)).d();
            }
        }

        if (Gdx.app.getType() != ApplicationType.Desktop) {
            this.l = true;
            if (Gdx.app.getType() != ApplicationType.iOS) {
                (new Thread(new LangLa_dj(this))).start();
            }
        }
    }

    public void resume() {
        this.l = false;
        LangLa_dp var1;
        (var1 = LangLa_dp.a()).a = false;
        var1.c = false;

        try {
            if (mConfig.gI().readBoolean(1)) {
                var1.f();
            }

            if (GameSrc.gI().cx.size() > 0) {
                for (int var3 = GameSrc.gI().cx.size() - 1; var3 >= 0; --var3) {
                    ((LangLa_en) GameSrc.gI().cx.get(var3)).a();
                }
            }

        } catch (Exception var2) {
            Utlis.println(var2);
        }
    }

    public void hide() {
    }

    public void dispose() {
        LangLa_dp var1;
        (var1 = LangLa_dp.a()).d();
        var1.e();
    }

    static boolean a(Canvas var0) {
        return var0.l;
    }

    static void a(Canvas var0, boolean var1) {
        var0.render(false);
    }
}
