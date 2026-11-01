package beatdz;

import java.util.HashMap;
import java.util.Map;

/**
 * State registry for Kage's single active account. The legacy game still owns
 * global singletons, so additional live accounts cannot safely be activated.
 */
public final class MultiSession {
    public interface State { void initialize(); }
    public interface Factory<T> { T create(); }
    public static final int LIMIT = 1;
    private static final Map<String, Integer> KEYS = new HashMap<String, Integer>();
    private static final Context ROOT = new Context();

    public static final class Context {
        public final int slot = 0;
        private final Map<Integer, State> states = new HashMap<Integer, State>();
        private Context() { }

        public synchronized <T extends State> T state(String key, Factory<T> factory) {
            return state(register(key), factory);
        }

        @SuppressWarnings("unchecked")
        public synchronized <T extends State> T state(int key, Factory<T> factory) {
            State existing = states.get(key);
            if (existing != null) return (T) existing;
            T value = factory.create();
            // Publish before initialization to support recursive singleton access.
            states.put(key, value);
            try {
                value.initialize();
                return value;
            } catch (RuntimeException failure) {
                states.remove(key);
                throw failure;
            } catch (Error failure) {
                states.remove(key);
                throw failure;
            }
        }
    }

    private MultiSession() { }
    public static synchronized int register(String key) {
        Integer id = KEYS.get(key);
        if (id == null) { id = KEYS.size(); KEYS.put(key, id); }
        return id;
    }
    public static Context current() { return ROOT; }
    public static <T extends State> T state(int key, Factory<T> factory) {
        return ROOT.state(key, factory);
    }
    public static int count() { return 1; }
    public static int selected() { return 0; }
    public static boolean visible() { return true; }
    public static int barHeight() { return 0; }
    public static String storageName(String name) { return name; }
    public static boolean add() { return false; }
    public static void select(int slot) {
        if (slot != 0) throw new IllegalArgumentException("Kage supports one active account");
    }
    public static void run(Context context, Runnable action) {
        if (context != ROOT) throw new IllegalArgumentException("Unknown account context");
        action.run();
    }
    public static Runnable bind(final Runnable action) {
        return new Runnable() { @Override public void run() { MultiSession.run(ROOT, action); } };
    }
}
