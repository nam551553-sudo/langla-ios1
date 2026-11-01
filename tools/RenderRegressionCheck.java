package beatdz;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.GdxNativesLoader;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.Pbuffer;
import org.lwjgl.opengl.PixelFormat;

/** Offscreen real-OpenGL check: sprite -> world line -> HUD -> sprite. No game login. */
public final class RenderRegressionCheck {
    private static Object defaultValue(Class<?> type) {
        if (type == boolean.class) return false;
        if (type == int.class) return 0;
        if (type == long.class) return 0L;
        if (type == float.class) return 0f;
        return null;
    }

    static Pbuffer startContext(final int width, final int height) throws Exception {
        Pbuffer context = new Pbuffer(width, height, new PixelFormat(), null);
        context.makeCurrent();
        GdxNativesLoader.load();
        java.lang.reflect.Constructor<?> glConstructor = Class.forName("com.badlogic.gdx.backends.lwjgl.LwjglGL20").getDeclaredConstructor();
        glConstructor.setAccessible(true);
        Gdx.gl = Gdx.gl20 = (GL20) glConstructor.newInstance();
        Gdx.app = (Application) Proxy.newProxyInstance(Application.class.getClassLoader(), new Class<?>[]{Application.class},
                (proxy, method, values) -> method.getName().equals("getType") ? ApplicationType.Desktop : defaultValue(method.getReturnType()));
        Gdx.graphics = (com.badlogic.gdx.Graphics) Proxy.newProxyInstance(com.badlogic.gdx.Graphics.class.getClassLoader(),
                new Class<?>[]{com.badlogic.gdx.Graphics.class}, (proxy, method, values) -> {
                    if (method.getName().equals("getWidth") || method.getName().equals("getBackBufferWidth")) return width;
                    if (method.getName().equals("getHeight") || method.getName().equals("getBackBufferHeight")) return height;
                    if (method.getName().equals("getGL20")) return Gdx.gl20;
                    return defaultValue(method.getReturnType());
                });
        Gdx.files = new LwjglFiles();
        return context;
    }

    public static void main(String[] args) throws Exception {
        Pbuffer context = startContext(128, 96);
        DataCenter.gI().zoomLevel = DataCenter.gI().zoomLevelScreen = 1;
        FixtureCanvas canvas = new FixtureCanvas();
        canvas.camera = new OrthographicCamera();
        canvas.camera.setToOrtho(true, 128, 96);
        canvas.hudCamera = new OrthographicCamera();
        canvas.hudCamera.setToOrtho(true, 128, 96);
        Graphics graphics = new Graphics(canvas);
        canvas.graphics = graphics;
        graphics.getSpriteBatch().setProjectionMatrix(canvas.camera.combined);
        mImage image = mImage.a(2, 2);
        image.b.setColor(Color.RED);
        image.b.fill();
        Gdx.gl.glViewport(0, 0, 128, 96);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        graphics.getSpriteBatch().begin();
        boolean legacyOverlap = args.length > 1 && args[1].equals("legacy");
        if (legacyOverlap) graphics.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line);
        graphics.setClip(0, 0, 128, 96);
        graphics.a(image, 0, 0, 2, 2, 0, 0, 0, 32, 32, 0);
        graphics.shapeRenderer.setColor(Color.GREEN);
        if (legacyOverlap) graphics.shapeRenderer.line(0, 50, 100, 50);
        else graphics.drawWorldLine(0, 50, 100, 50);
        canvas.beginHudPass(graphics);
        graphics.a(image, 0, 0, 2, 2, 0, 40, 0, 32, 32, 0);
        canvas.endHudPass(graphics);
        graphics.a(image, 0, 0, 2, 2, 0, 80, 0, 32, 32, 0);
        graphics.getSpriteBatch().end();
        if (legacyOverlap) graphics.shapeRenderer.end();
        Gdx.gl.glFinish();
        ByteBuffer pixels = BufferUtils.newByteBuffer(128 * 96 * 4);
        Gdx.gl.glReadPixels(0, 0, 128, 96, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, pixels);
        for (int x : new int[]{8, 48, 88}) {
            int offset = ((96 - 8) * 128 + x) * 4;
            if ((pixels.get(offset) & 255) < 220 || (pixels.get(offset + 1) & 255) > 30)
                throw new AssertionError("Black/corrupt sprite at x=" + x);
        }
        if (Gdx.gl.glGetError() != GL20.GL_NO_ERROR) throw new AssertionError("OpenGL error");
        Pixmap downloaded = new Pixmap(Gdx.files.absolute(args[0]));
        if (downloaded.getWidth() < 2) throw new AssertionError("Downloaded icon did not decode");
        downloaded.dispose();
        context.destroy();
        System.out.println("PASS: real OpenGL sprite/line/HUD/world rendering and downloaded game icon decoding");
        System.exit(0);
    }

    private static class FixtureCanvas extends Canvas {
        public void create() { }
        public void paint(Graphics g) { }
        public void update() { }
        public void a(char c) { }
        public void a(int a) { }
        public void b(int a) { }
        public void a(int a, int b) { }
        public void b(int a, int b) { }
        public void c(int a, int b) { }
    }
}
