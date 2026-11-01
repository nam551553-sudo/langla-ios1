package beatdz;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** Compiled in isolation with the real loader Runnable classes. */
public final class WorkerWakeupCheck {
    public static void main(String[] args) throws Exception {
        // Work is queued before the workers start; there is intentionally no
        // notification. The old unconditional wait left these jobs asleep.
        LangLa_cz.cW.add(new LangLa_fb());
        LangLa_cz.cX.add(new LangLa_fb());
        Thread cache = new Thread(new LangLa_db());
        Thread network = new Thread(new LangLa_fg());
        cache.setDaemon(true);
        network.setDaemon(true);
        cache.start();
        network.start();
        boolean complete = LangLa_cz.done.await(1500, TimeUnit.MILLISECONDS);
        LangLa_ep.stopped = true;
        synchronized (LangLa_cz.cT) { LangLa_cz.cT.notifyAll(); }
        synchronized (LangLa_cz.cU) { LangLa_cz.cU.notifyAll(); }
        cache.join(1000);
        network.join(1000);
        if (!complete) throw new AssertionError("Queued image load slept without a notification");
        System.out.println("PASS: cache and network workers process work queued before startup");
    }
}

class LangLa_fb { }
class LangLa_ep {
    static volatile boolean stopped;
    static boolean a() { return stopped; }
}
class LangLa_cz {
    static final Object cT = new Object(), cU = new Object();
    static final Vector cW = new Vector(), cX = new Vector();
    static final CountDownLatch done = new CountDownLatch(2);
    static boolean cY;
    static void a(LangLa_fb task) { done.countDown(); }
    static void b(LangLa_fb task) { done.countDown(); }
}
class Utlis {
    static void println(Exception e) { throw new AssertionError(e); }
}
