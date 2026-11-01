package beatdz;

/** Connection and protocol defaults shared by desktop, Android and iOS. */
public final class ClientConfig {
    public static final String SERVER_IP = "160.250.128.28";
    public static final int GAME_PORT = 2907;
    public static final int CHECK_PORT = 2908;
    public static final int PROTOCOL_VERSION = 81;
    public static final int CONTENT_VERSION = 131;
    public static final String DATA_BASE_URL = "http://" + SERVER_IP + "/";
    public static final String COSTUME_IMAGE_BASE_URL = DATA_BASE_URL + "img/";
    public static final String SERVER_LIST_URL = DATA_BASE_URL + "serverlist.txt";
    public static final String LOCAL_BACKGROUND = "resource/langlahuyenthoai.jpg";

    private ClientConfig() {
    }
}
