package beatdz;

import com.badlogic.gdx.graphics.Pixmap;

/** new1-new23 images are downloaded and cached, never packaged in the app. */
final class CostumeAssets {
    static boolean handles(String path) {
        if (path == null || !path.matches("icon(Char|Client)/[0-9]+\\.png")) return false;
        int id = Integer.parseInt(path.substring(path.indexOf('/') + 1, path.length() - 4));
        return id >= 31000 && id <= 32059;
    }

    static String url(String path) {
        int zoom = Math.max(1, Math.min(4, DataCenter.gI().zoomLevel));
        return ClientConfig.COSTUME_IMAGE_BASE_URL + "zoom" + zoom + "/" + path;
    }

    static mImage decode(byte[] data, String path) {
        if (data == null) return null;
        mImage image = Binary.createImage(Utlis.a(data));
        if (image != null && path.startsWith("iconClient/")) {
            int size = 20 * Math.max(1, DataCenter.gI().zoomLevel);
            mImage scaled = mImage.a(size, size);
            if (scaled == null) return image;
            scaled.b.setBlending(Pixmap.Blending.None);
            scaled.b.setFilter(Pixmap.Filter.NearestNeighbour);
            scaled.b.drawPixmap(image.b, 0, 0, image.c, image.d, 0, 0, scaled.c, scaled.d);
            image.b.dispose();
            return scaled;
        }
        return image;
    }
}
