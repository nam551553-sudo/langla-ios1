package beatdz;

/** Uses Kage's existing zoom geometry without the desktop tab/zoom extensions. */
final class DisplayView {
    private final Canvas canvas;

    DisplayView(Canvas canvas) { this.canvas = canvas; }

    static int logicalExtent(int pixels, int zoom) {
        return pixels / Math.max(1, zoom);
    }

    int width() { return logicalExtent(canvas.width, DataCenter.gI().zoomLevelScreen); }
    int height() { return logicalExtent(canvas.height, DataCenter.gI().zoomLevelScreen); }

    void apply() {
        if (canvas.viewport == null || canvas.camera == null) return;
        DataCenter data = DataCenter.gI();
        int screenZoom = Math.max(1, data.zoomLevelScreen);
        int imageZoom = Math.max(1, data.zoomLevel);
        data.widthScreen = width();
        data.heightScreen = height();
        float worldWidth = canvas.width * imageZoom / (float)screenZoom - canvas.width % screenZoom;
        float worldHeight = canvas.height * imageZoom / (float)screenZoom - canvas.height % screenZoom;
        canvas.viewport.setWorldSize(worldWidth, worldHeight);
        canvas.viewport.update(canvas.width, canvas.height, true);
    }

    void refresh() {
        MainScreen screen = DataCenter.gI().currentScreen;
        if (screen == null) return;
        // These methods exist in Kage. The desktop-only onZoomChanged,
        // onViewChanged and synchronizeZoomSelection APIs are not available.
        screen.setSize(width(), height());
        screen.M();
    }
}
