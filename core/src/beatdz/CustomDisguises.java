package beatdz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/** Full-frame costumes sent by SRC_Server.Service.sendCustomDisguise (-126). */
final class CustomDisguises {
    private static volatile Map<Integer, Appearance> appearances = new HashMap<Integer, Appearance>();

    static final class Frame {
        final int imageId, offsetX, offsetY, action, width, height;
        Frame(Message message) throws IOException {
            imageId = message.readUnsignedShort();
            offsetX = message.readShort();
            offsetY = message.readShort();
            action = message.readUnsignedShort();
            width = message.readUnsignedShort();
            height = message.readUnsignedShort();
            if (action > 6) throw new IOException("Unknown costume action: " + action);
        }
    }

    static final class Appearance {
        final Frame[][] actions = new Frame[7][];
        Appearance(Frame[] frames) {
            for (int action = 0; action < actions.length; action++) {
                ArrayList<Frame> group = new ArrayList<Frame>();
                for (Frame frame : frames) if (frame.action == action) group.add(frame);
                actions[action] = group.toArray(new Frame[group.size()]);
            }
        }

        Frame frame(int action, int tick) {
            Frame[] frames = actions[action];
            if (frames.length == 0) frames = actions[0];
            if (frames.length == 0) {
                for (Frame[] group : actions) if (group.length > 0) { frames = group; break; }
            }
            if (frames.length == 0) return null;
            int index = action == 5 ? Math.min(Math.max(tick, 0), frames.length - 1)
                    : Math.floorMod(tick, frames.length);
            return frames[index];
        }
    }

    static void read(Message message) throws IOException {
        Map<Integer, Appearance> loaded = new HashMap<Integer, Appearance>();
        int count = message.readUnsignedShort();
        for (int i = 0; i < count; i++) {
            int id = message.readUnsignedShort();
            int length = message.readUnsignedShort();
            if (length * 12 > message.reader.dis.available()) throw new IOException("Truncated costume " + id);
            Frame[] frames = new Frame[length];
            for (int j = 0; j < length; j++) frames[j] = new Frame(message);
            if (length > 0) loaded.put(id, new Appearance(frames));
        }
        if (message.reader.dis.available() != 0) throw new IOException("Unexpected costume data");
        appearances = loaded;
    }

    static void clear() { appearances = new HashMap<Integer, Appearance>(); }

    static void loadBundled() {
        if (com.badlogic.gdx.Gdx.files == null) return;
        com.badlogic.gdx.files.FileHandle file = com.badlogic.gdx.Gdx.files.internal("resource/topup/disguises.bin");
        if (!file.exists()) return;
        Message message = new Message((byte) -126, file.readBytes());
        try {
            read(message);
        } catch (IOException error) {
            Utlis.println(error, "Cannot load packaged costume frames");
        } finally {
            message.close();
        }
    }

    static boolean append(Vector parts, int id) {
        Appearance appearance = appearances.get(id);
        if (appearance == null) return false;
        parts.add(new Part(id, appearance));
        return true;
    }

    static int action(Char character) {
        if (character.status == 4 || character.status == 5) return 5;
        if (character.status == 3 || character.av != null) return 3;
        if (character.status == 1) return 4;
        switch (character.af) {
            case 1: return 1;
            case 2: case 3: return 2;
            case 4: return 6;
            default: return 0;
        }
    }

    /** Stays in the existing body-part pipeline, preserving wings and accessories. */
    static final class Part extends LangLa_jr {
        private static final LangLa_ju[] SORT_FRAMES = new LangLa_ju[256];
        static {
            for (int i = 0; i < SORT_FRAMES.length; i++) SORT_FRAMES[i] = new LangLa_ju((short) 0);
        }
        final Appearance appearance;
        Part(int id, Appearance appearance) {
            this.a = id;
            this.d = SORT_FRAMES;
            this.appearance = appearance;
        }

        void draw(Graphics graphics, Char character, int x, int y, boolean flipped) {
            int action = action(character);
            int tick = action == 5 ? character.e / 3 : DataCenter.gI().i / 3;
            Frame frame = appearance.frame(action, tick);
            if (frame == null) return;
            mImage image = LangLa_fq.reqestIconChar(frame.imageId);
            if (image == null || image == LangLa_fq.a) return;
            int zoom = Math.max(1, DataCenter.gI().zoomLevel);
            int width = frame.width > 0 ? frame.width * zoom : image.c;
            int height = frame.height > 0 ? frame.height * zoom : image.d;
            graphics.a(image, 0, 0, image.c, image.d, flipped ? -360 : 0,
                    x + (flipped ? -frame.offsetX : frame.offsetX), y + frame.offsetY,
                    width, height, 33);
        }
    }
}
