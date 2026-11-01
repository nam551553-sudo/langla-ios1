package beatdz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.files.FileHandle;
import com.sg188.server.lib.Writer;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import sun.misc.Unsafe;

/** Uses freshly compiled SRC_Server writers; no database or account login. */
public final class ServerProtocolCheck {
    static final Unsafe unsafe;
    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (Exception e) { throw new ExceptionInInitializerError(e); }
    }
    static class Preview extends Char { @Override public void U() { } }
    static class ServerChar extends com.sg188.real.Char { @Override public int GetTaiPhu() { return 123; } }
    static class ServerItem extends com.sg188.real.Item {
        @Override public com.sg188.data.ItemTemplate getItemTemplate() {
            com.sg188.data.ItemTemplate t = new com.sg188.data.ItemTemplate(id);
            t.type = DataCenter.gI().ItemTemplate[id].type;
            return t;
        }
        @Override public boolean isTypeTrangBi() { return new Item(id).isTypeTrangBi(); }
    }
    static class ServerDisciple extends com.sg188.real.Disciple {
        ServerDisciple() { super(null); }
        @Override public void updateAllChiSo() { }
        @Override public int level() { return 20; }
    }

    static void field(Object object, Class<?> owner, String name, Object value) throws Exception {
        Field f = owner.getDeclaredField(name); f.setAccessible(true); f.set(object, value);
    }

    static ServerChar character(int id) throws Exception {
        ServerChar c = (ServerChar) unsafe.allocateInstance(ServerChar.class);
        c.Info = new InfoChar.InfoChar();
        c.Point = new InfoChar.InfoPoint();
        c.InfoGame = new InfoChar.InfoGame();
        c.Bag = (InfoChar.InfoInventory) unsafe.allocateInstance(InfoChar.InfoInventory.class);
        c.Bag.arrItemBody = new com.sg188.real.Item[19];
        c.Bag.arrItemBody2 = new com.sg188.real.Item[19];
        c.Bag.arrItemBag = new com.sg188.real.Item[27];
        c.Bag.arrItemExtend = new com.sg188.real.Item[6];
        c.Info.username = "fixture"; c.Info.name = "NhanGia" + id;
        c.Info.idEntity = id; c.Info.cx = 300; c.Info.cy = 300;
        c.Info.idChar = 0; c.Info.idClass = 1;
        c.Point.hp = c.maxHP = 1000; c.Point.mp = c.maxMP = 500;
        c.Point.exp = 123456; c.movementSpeed = 500;
        c.Skill = new InfoChar.InfoSkill();
        com.sg188.data.Skill skill = (com.sg188.data.Skill) unsafe.allocateInstance(com.sg188.data.Skill.class);
        skill.id = 1; c.Skill.skillFight = skill; c.Skill.arraySkill = new com.sg188.data.Skill[]{skill};
        c.listEffect = new ArrayList<>(); c.listSkill = new java.util.Vector<>();
        c.letters = new ArrayList<>(); c.friends = new HashMap<>();
        return c;
    }

    static byte[] bytes(Writer writer) throws Exception { writer.flush(); return writer.baos.toByteArray(); }
    static ServerItem item(int id, int slot) throws Exception {
        ServerItem item = (ServerItem) unsafe.allocateInstance(ServerItem.class);
        item.id = (short)id; item.index = slot; item.strOptions = "1,10;2,20";
        item.amount = 3; item.expiry = -1;
        return item;
    }
    static void exhausted(Message m, String label) throws Exception {
        if (m.reader.dis.available() != 0) throw new AssertionError(label + ": unread bytes=" + m.reader.dis.available());
    }

    static void verify(String[] args) throws Exception {
        File server = new File(args[0]);
        File runtime = new File(args[1]); runtime.mkdirs();
        Gdx.app = (com.badlogic.gdx.Application) java.lang.reflect.Proxy.newProxyInstance(
                com.badlogic.gdx.Application.class.getClassLoader(), new Class<?>[]{com.badlogic.gdx.Application.class},
                (proxy, method, values) -> {
                    if (method.getName().equals("getType")) return com.badlogic.gdx.Application.ApplicationType.iOS;
                    if (method.getReturnType() == boolean.class) return false;
                    if (method.getReturnType() == int.class) return 0;
                    if (method.getReturnType() == long.class) return 0L;
                    return null;
                });
        Gdx.files = (com.badlogic.gdx.Files) java.lang.reflect.Proxy.newProxyInstance(
                com.badlogic.gdx.Files.class.getClassLoader(), new Class<?>[]{com.badlogic.gdx.Files.class},
                (proxy, method, values) -> {
                    if (method.getName().equals("local")) return new FileHandle(new File(runtime, (String) values[0]));
                    if (method.getName().equals("internal")) return new FileHandle(new File(runtime, "bundled/" + values[0]));
                    if (method.getName().equals("absolute")) return new FileHandle((String) values[0]);
                    if (method.getReturnType() == boolean.class) return true;
                    if (method.getReturnType() == String.class) return runtime.getAbsolutePath() + "/";
                    return null;
                });
        mConfig settings = (mConfig) unsafe.allocateInstance(mConfig.class);
        byte[] config = new byte[25]; config[18] = 2;
        field(settings, mConfig.class, "config", config);
        field(null, mConfig.class, "instance", settings);
        Binary.a = "fixture/";
        File content = new File(runtime, "fixture/arr_data_game.beatdz"); content.getParentFile().mkdirs();
        Files.copy(new File(server, "data/arr_data_game.bin").toPath(), content.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        DataCenter data = DataCenter.gI();
        data.aU = "invalid://offline/";
        data.zoomLevel = data.zoomLevelScreen = 1;
        data.widthScreen = data.widthHud = 640; data.heightScreen = data.heightHud = 360;
        data.readArrDataGame(false);
        Message extra = new Message((byte)-113, Files.readAllBytes(new File(server, "data/arr_data_game2.bin").toPath()));
        data.readArrDataGame2(extra);
        exhausted(extra, "server item data");
        if (data.MapTemplate == null || data.Skill == null || data.MobTemplate == null)
            throw new AssertionError("Server content did not load");
        System.out.println("Server content: maps=" + data.MapTemplate.length + ", mobs=" + data.MobTemplate.length + ", items=" + data.ItemTemplate.length);
        Preview me = new Preview(); field(null, Char.class, "bq", me);
        GameSrc.gI();
        Writer self = new Writer(); character(11).writeMe(self);
        Message selfMessage = new Message((byte)-127, bytes(self));
        me.readMe(selfMessage);
        exhausted(selfMessage, "self character");
        if (!me.name.equals("NhanGia11") || me.hp != 1000) throw new AssertionError("self fields");
        System.out.println("PASS: actual Char.writeMe -> client readMe, packet fully consumed");
        ServerChar equipped = character(12);
        equipped.Bag.arrItemBody[0] = item(28, 0);
        equipped.Bag.arrItemBody2[0] = item(28, 0);
        equipped.Bag.arrItemExtend[5] = item(28, 5);
        equipped.Bag.arrItemBag[2] = item(28, 2);
        equipped.Bag.itemSach = item(435, 0);
        ServerDisciple child = (ServerDisciple) unsafe.allocateInstance(ServerDisciple.class);
        child.owner = equipped; child.Info = new InfoChar.InfoChar(); child.Point = new InfoChar.InfoPoint();
        child.Bag = (InfoChar.InfoInventory) unsafe.allocateInstance(InfoChar.InfoInventory.class);
        child.Bag.arrItemBody = new com.sg188.real.Item[19];
        child.Info.name = "DeTuFixture"; child.Point.hp = child.maxHP = 100;
        equipped.id = 12; equipped.disciple = child;
        Writer full = new Writer(); equipped.writeMe(full);
        Message fullMessage = new Message((byte)-127, bytes(full));
        me.readMe(fullMessage); exhausted(fullMessage, "equipped self with book and disciple");
        int bodySlot = DataCenter.gI().ItemTemplate[28].type;
        if (me.arrItemExtend[5] == null || me.arrItemBag[2] == null || me.arrItemBody[bodySlot] == null
                || me.deTu == null || !me.deTu.name.equals("DeTuFixture")) throw new AssertionError("equipped fields: slot=" + bodySlot
                        + ", extend=" + me.arrItemExtend[5] + ", bag=" + me.arrItemBag[2] + ", disciple=" + me.deTu);
        System.out.println("PASS: actual writeMe with both equipment sets, bag item, extension slot 5, book and disciple");
        Writer others = new Writer();
        for (int id = 21; id < 24; id++) {
            others.writeInt(id); ServerChar other = character(id);
            if (id == 22) { child.owner = other; other.disciple = child; }
            other.write(others);
        }
        others.writeInt(0x12345678);
        Message otherMessage = new Message((byte)-103, bytes(others));
        for (int id = 21; id < 24; id++) {
            if (otherMessage.readInt() != id) throw new AssertionError("other record boundary " + id);
            Preview other = new Preview(); other.read(otherMessage);
            if (!other.name.equals("NhanGia" + id)) throw new AssertionError("other fields");
        }
        if (otherMessage.readInt() != 0x12345678) throw new AssertionError("mob data boundary");
        exhausted(otherMessage, "other characters");
        System.out.println("PASS: 3 actual Char.write records preserve following map/mob bytes");
    }

    public static void main(String[] args) throws Exception {
        verify(args);
        if (args.length > 2) {
            Reader map = new Reader(Files.readAllBytes(new File(args[2]).toPath()));
            java.lang.reflect.Method read = GameSrc.class.getDeclaredMethod("readBlockMap", Reader.class);
            read.setAccessible(true); read.invoke(GameSrc.gI(), map);
            for (int id : new int[]{171,432,434,440,441}) {
                if (id < GameSrc.gI().f.length) {
                    DataBlockMap block = GameSrc.gI().f[id];
                    System.out.println("TREE " + id + ": " + block.width + "x" + block.height + " type=" + block.type);
                }
            }
        }
        System.exit(0);
    }
}
