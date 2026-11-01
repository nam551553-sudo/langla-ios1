package beatdz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Deduplicates asynchronous image loads and allows failed loads to retry. */
final class ImageRequestGate {
    private static final Set<String> pending = new HashSet<String>();
    private static final Map<String, Long> retryAt = new HashMap<String, Long>();

    static synchronized boolean start(String key, long now) {
        Long retry = retryAt.get(key);
        if (pending.contains(key) || (retry != null && now < retry)) return false;
        pending.add(key);
        return true;
    }

    static synchronized void finish(String key, boolean success, long now) {
        pending.remove(key);
        if (success) retryAt.remove(key);
        else retryAt.put(key, now + 5000L);
    }
}
