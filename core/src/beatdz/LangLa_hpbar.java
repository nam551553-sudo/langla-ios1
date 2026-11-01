package beatdz;

public final class LangLa_hpbar {
    private static final int HP_FULL_ICON = 9000;
    private static final int HP_EMPTY_ICON = 9001;

    private LangLa_hpbar() {
    }

    public static void a(Graphics g, int x, int y, int width, int height, int fillWidth, int fallbackBg, int fallbackFill) {
        if (g == null || width <= 0 || height <= 0) {
            return;
        }

        if (fillWidth < 0) {
            fillWidth = 0;
        } else if (fillWidth > width) {
            fillWidth = width;
        }

        g.f(fallbackBg);
        g.c(x, y, width, height);
        if (fillWidth > 0) {
            g.f(fallbackFill);
            g.c(x, y, fillWidth, height);
        }

        mImage empty = LangLa_fq.a(HP_EMPTY_ICON);
        mImage full = LangLa_fq.a(HP_FULL_ICON);
        if (b(empty)) {
            a(g, empty, x, y, width, height, width);
        }
        if (fillWidth > 0 && b(full)) {
            a(g, full, x, y, width, height, fillWidth);
        }
    }

    private static void a(Graphics g, mImage img, int x, int y, int width, int height, int drawWidth) {
        if (drawWidth <= 0) {
            return;
        }

        int zoom = DataCenter.gI().zoomLevel;
        int sourceW = Math.max(1, img.c * drawWidth / width);
        int sourceH = Math.max(1, img.d);
        int destW = drawWidth * zoom;
        int destH = height * zoom;
        g.a(img, 0, 0, sourceW, sourceH, 0, x, y, destW, destH, 20);
    }

    private static boolean b(mImage img) {
        return img != null && img != LangLa_fq.a && img.c > 1 && img.d > 1;
    }
}
