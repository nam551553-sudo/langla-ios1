package beatdz;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public final class ImageRequestGateCheck {
    private static void check(boolean result, String message) {
        if (!result) throw new AssertionError(message);
    }

    public static void main(String[] args) throws Exception {
        check(ImageRequestGate.start("client_icon_34", 1000), "initial request");
        check(!ImageRequestGate.start("client_icon_34", 120000), "deduplicate slow pending request");
        ImageRequestGate.finish("client_icon_34", false, 120000);
        check(!ImageRequestGate.start("client_icon_34", 124999), "failure backoff");
        check(ImageRequestGate.start("client_icon_34", 125000), "retry after failed download");
        ImageRequestGate.finish("client_icon_34", true, 125001);
        check(ImageRequestGate.start("client_icon_34", 125002), "request again after cache eviction");
        ImageRequestGate.finish("client_icon_34", true, 125003);

        final AtomicInteger accepted = new AtomicInteger();
        final CountDownLatch start = new CountDownLatch(1);
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try { start.await(); } catch (InterruptedException e) { throw new RuntimeException(e); }
                    if (ImageRequestGate.start("tree_302", 100)) accepted.incrementAndGet();
                }
            });
            threads[i].start();
        }
        start.countDown();
        for (Thread thread : threads) thread.join();
        check(accepted.get() == 1, "only one request may enter the loader queue");
        System.out.println("PASS: image retry, slow-download deduplication, cache eviction and 20 concurrent requests");
    }
}
