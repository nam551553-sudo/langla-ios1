package beatdz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sg188.server.lib.Writer;
import java.io.File;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.lwjgl.opengl.Pbuffer;

/** Real client startup/map rendering fed by actual SRC_Server packet writers. */
public final class ServerMapSceneCheck {
    static Object zero(Class<?> t) {
        if (t == boolean.class) return false;
        if (t == int.class) return 0;
        if (t == long.class) return 0L;
        if (t == float.class) return 1f / 60;
        return null;
    }
    static void verifyScene(String[] args) throws Exception {
        ServerProtocolCheck.verify(args);
        File runtime = new File(args[1]), assets = new File(args[2]);
        Pbuffer context = RenderRegressionCheck.startContext(960, 540);
        ConcurrentLinkedQueue<Runnable> runnables = new ConcurrentLinkedQueue<Runnable>();
        Gdx.app = (com.badlogic.gdx.Application) Proxy.newProxyInstance(com.badlogic.gdx.Application.class.getClassLoader(),
                new Class<?>[]{com.badlogic.gdx.Application.class}, (proxy, method, values) -> {
                    if (method.getName().equals("getType")) return com.badlogic.gdx.Application.ApplicationType.iOS;
                    if (method.getName().equals("postRunnable")) { runnables.add((Runnable)values[0]); return null; }
                    if (method.getName().equals("getGraphics")) return Gdx.graphics;
                    if (method.getName().equals("getInput")) return Gdx.input;
                    if (method.getName().equals("error")) System.err.println(java.util.Arrays.toString(values));
                    return zero(method.getReturnType());
                });
        Gdx.input = (com.badlogic.gdx.Input) Proxy.newProxyInstance(com.badlogic.gdx.Input.class.getClassLoader(),
                new Class<?>[]{com.badlogic.gdx.Input.class}, (proxy, method, values) -> zero(method.getReturnType()));
        Gdx.files = (com.badlogic.gdx.Files) Proxy.newProxyInstance(com.badlogic.gdx.Files.class.getClassLoader(),
                new Class<?>[]{com.badlogic.gdx.Files.class}, (proxy, method, values) -> {
                    if (method.getName().equals("local")) return new FileHandle(new File(runtime, (String)values[0]));
                    if (method.getName().equals("internal")) return new FileHandle(new File(assets, (String)values[0]));
                    if (method.getName().equals("absolute")) return new FileHandle((String)values[0]);
                    if (method.getName().equals("getFileHandle")) return new com.badlogic.gdx.backends.lwjgl.LwjglFiles()
                            .getFileHandle((String)values[0], (com.badlogic.gdx.Files.FileType)values[1]);
                    if (method.getName().equals("classpath")) return new com.badlogic.gdx.backends.lwjgl.LwjglFiles().classpath((String)values[0]);
                    if (method.getReturnType() == boolean.class) return true;
                    if (method.getReturnType() == String.class) return runtime.getAbsolutePath() + "/";
                    return null;
                });
        mConfig.gI().writeByte(18, 0);
        com.tgame.model.Caption.loadVN();
        InputCanvas canvas = new InputCanvas();
        AppListener.gI().canvas = canvas;
        Runnable runnable; while ((runnable = runnables.poll()) != null) runnable.run();
        mFont.a();
        DataCenter data = DataCenter.gI();
        data.widthScreen = canvas.getWidth(); data.heightScreen = canvas.getHeight();
        data.aU = ClientConfig.DATA_BASE_URL; data.aY = "img/zoom";
        data.ba = data.bb = true;
        Char me = new Char(); ServerProtocolCheck.field(null, Char.class, "bq", me);
        GameSrc game = GameSrc.gI(); game.setSize(data.widthHud, data.heightHud);
        data.currentScreen = game;
        data.aO = new LangLa_gg(game);
        Writer self = new Writer(); ServerProtocolCheck.character(11).writeMe(self);
        Controller controller = new Controller();
        Writer framedSelf = new Writer(); framedSelf.writeByte(-127); framedSelf.dos.write(ServerProtocolCheck.bytes(self));
        controller.a(new Message((byte)-122, ServerProtocolCheck.bytes(framedSelf)));
        int mapId = Integer.parseInt(args[3]);
        File mapFile = new File(runtime, "fixture/arr_map_" + mapId + ".beatdz");
        Files.copy(new File(args[0], "data/ArrMap/arr_map_" + mapId + ".bin").toPath(), mapFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        MapService.Map map = new MapService.Map(mapId);
        MapService.Zone zone = new MapService.Zone(map, 0);
        ServerProtocolCheck.ServerChar own = ServerProtocolCheck.character(11);
        ServerProtocolCheck.ServerChar other = ServerProtocolCheck.character(22); other.id = -22;
        zone.players.add(own); zone.players.add(other);
        com.sg188.real.Mob mob = new com.sg188.real.Mob();
        mob.id = 0; mob.idEntity = 1; mob.cx = 340; mob.cy = 300;
        mob.nameChar = ""; mob.hp = mob.hpFull = 100; mob.level = 1;
        zone.monsters.add(mob);
        com.sg188.real.Npc npc = (com.sg188.real.Npc) ServerProtocolCheck.unsafe.allocateInstance(com.sg188.real.Npc.class);
        npc.id = 0; npc.cx = 370; npc.cy = 300;
        zone.npcs.add(npc);
        Writer entered = new Writer(); zone.write(own, entered);
        Message mapPacket = new Message((byte)-103, ServerProtocolCheck.bytes(entered));
        controller.a(new Message((byte)-104, new byte[0]));
        controller.a(mapPacket);
        ServerProtocolCheck.exhausted(mapPacket, "full Zone.write packet");
        if (game.mapLoadError != null || !game.ba || game.al == null || game.O == null)
            throw new AssertionError("Map/UI not initialized: " + game.mapLoadError);
        if (game.vMob.size() != 1 || game.vChar.size() != 1 || game.vNpc.size() != 1) throw new AssertionError("Map entity counts");
        System.out.println("PASS: actual Zone.write -> map " + mapId + ", other character, mob, map geometry and HUD initialized");
        long until = System.currentTimeMillis() + 90000;
        int colored = 0;
        do {
            while ((runnable = runnables.poll()) != null) runnable.run();
            canvas.render(1f / 60);
            Gdx.gl.glFinish();
            if (System.currentTimeMillis() + 1000 >= until) break;
            Thread.sleep(40);
        } while (LangLa_cz.cW.size() > 0 || LangLa_cz.cX.size() > 0 || ImageRequestGate.pendingCount() > 0
                || LangLa_fq.f.values().stream().noneMatch(image -> image != null && image != LangLa_fq.a));
        for (int i = 0; i < 10; i++) { canvas.render(1f / 60); Thread.sleep(30); }
        Pixmap shot = ScreenUtils.getFrameBufferPixmap(0, 0, 960, 540);
        for (int y = 0; y < 500; y++) for (int x = 0; x < 960; x++) {
            int pixel = shot.getPixel(x, y);
            int r = pixel >>> 24, g = (pixel >>> 16) & 255, b = (pixel >>> 8) & 255;
            if (r + g + b > 90) colored++;
        }
        PixmapIO.PNG png = new PixmapIO.PNG();
        png.setFlipY(true);
        png.write(new FileHandle(new File(runtime, "map-" + mapId + ".png")), shot);
        png.dispose();
        shot.dispose();
        long images = java.util.Arrays.asList(LangLa_fq.e, LangLa_fq.f, LangLa_fq.g).stream()
                .flatMap(table -> table.values().stream()).filter(value -> value != null && value != LangLa_fq.a).count();
        System.out.println("Rendered pixels=" + colored + ", decoded images=" + images);
        System.out.println("Tree images: " + LangLa_fq.f.keySet() + ", pending=" + ImageRequestGate.pendingCount());
        boolean worldLoaded = LangLa_fq.f.values().stream().anyMatch(image -> image != null && image != LangLa_fq.a);
        boolean charactersLoaded = LangLa_fq.g.values().stream().anyMatch(image -> image != null && image != LangLa_fq.a);
        if (colored < 20000 || images < 10 || !worldLoaded || !charactersLoaded)
            throw new AssertionError("Map/character/UI image rendering is still empty");
        LangLa_kl profile = new LangLa_kl(game, me, GameSrc.gI().p);
        if (profile.k.c.length != 2 || java.util.Arrays.toString(profile.k.c).contains("Chức nghiệp"))
            throw new AssertionError("Profession tab still present or inventory removed");
        profile.a(1);
        if (profile.k() != 1) throw new AssertionError("Inventory tab is inaccessible");
        System.out.println("PASS: game scene image, profession tab removed and inventory accessible; screenshot saved");
        System.exit(0);
    }
    public static void main(String[] args) {
        try { verifyScene(args); } catch (Throwable error) { error.printStackTrace(); System.exit(1); }
    }
}
