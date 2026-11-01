package beatdz;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/** Local HTTP fixture: no game server or external connectivity required. */
public final class DownloaderRegressionCheck {
    private static void verify() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        AtomicInteger missing = new AtomicInteger();
        server.createContext("/missing.png", exchange -> {
            missing.incrementAndGet();
            exchange.sendResponseHeaders(404, -1);
            exchange.close();
        });
        server.createContext("/ok.png", exchange -> {
            byte[] body = new byte[] {1, 2, 3};
            exchange.sendResponseHeaders(200, body.length);
            exchange.getResponseBody().write(body);
            exchange.close();
        });
        server.start();
        try {
            String base = "http://127.0.0.1:" + server.getAddress().getPort();
            long start = System.currentTimeMillis();
            if (Binary.b(base + "/missing.png") != null || missing.get() != 1)
                throw new AssertionError("Permanent 404 must not retry or return image bytes");
            if (System.currentTimeMillis() - start > 3000)
                throw new AssertionError("Missing image blocked the downloader");
            if (!Arrays.equals(Binary.b(base + "/ok.png"), new byte[] {1, 2, 3}))
                throw new AssertionError("Following valid download failed");
            System.out.println("PASS: HTTP 404 finishes after one request; following asset downloads successfully");
        } finally { server.stop(0); }
    }
    public static void main(String[] args) {
        try { verify(); System.exit(0); }
        catch (Throwable error) { error.printStackTrace(); System.exit(1); }
    }
}
