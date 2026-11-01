package beatdz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

public class LangLa_naptien extends LangLa_fo {
    private String title;
    private String qrUrl;
    private mImage qrImage;
    private boolean loadingQr;
    private boolean qrFailed;

    public LangLa_naptien(MainScreen screen, String title, String text, String qrUrl) {
        this.p = screen;
        this.title = title;
        this.qrUrl = qrUrl;
        this.a(260, 260);
        this.loadQr();
    }

    public void a(Graphics g) {
        this.a(g, this.cx, this.cy, this.width, this.height, this.title, (byte) 2, true);
        this.a(g, 18, 42, this.width - 36, this.height - 58, -11, 55, 56);
        int qrSize = 190;
        int qrX = (this.width - qrSize) / 2;
        int qrY = 52;
        g.c(qrX - 4, qrY - 4, qrSize + 8, qrSize + 8);
        if (this.qrImage != null) {
            g.a(this.qrImage, 0, 0, this.qrImage.c, this.qrImage.d, 0, qrX, qrY,
                    qrSize * DataCenter.gI().zoomLevel, qrSize * DataCenter.gI().zoomLevel, 0);
        } else {
            String status = this.qrFailed ? "Không tải được QR" : "Đang tải QR...";
            mFont.b(mFont.d, g, status, this.width / 2, qrY + qrSize / 2 - 6, 2, -1, -16777216);
        }
        this.b(g);
    }

    public void a(LangLa_jz action, int x, int y) {
        switch (action.b) {
            case -7:
                this.J();
                return;
            default:
        }
    }

    public void a(int id, Object value) {
    }

    public void a(int id, Object value, LangLa_cy source) {
    }

    public void b() {
    }

    public void d() {
        if (this.qrImage != null) {
            this.qrImage.a();
            this.qrImage = null;
        }
        super.d();
    }

    private void loadQr() {
        if (this.loadingQr) {
            return;
        }
        this.loadingQr = true;
        this.qrFailed = false;
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl(this.qrUrl);
        request.setTimeOut(12000);
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse response) {
                final byte[] data = response.getResult();
                Gdx.app.postRunnable(new Runnable() {
                    public void run() {
                        loadingQr = false;
                        if (data == null || data.length == 0) {
                            qrFailed = true;
                            return;
                        }
                        mImage img = Binary.createImage(data);
                        if (img == null) {
                            qrFailed = true;
                            return;
                        }
                        if (qrImage != null) {
                            qrImage.a();
                        }
                        qrImage = img;
                    }
                });
            }

            public void failed(Throwable t) {
                Gdx.app.postRunnable(new Runnable() {
                    public void run() {
                        loadingQr = false;
                        qrFailed = true;
                    }
                });
            }

            public void cancelled() {
                failed(null);
            }
        });
    }
}
