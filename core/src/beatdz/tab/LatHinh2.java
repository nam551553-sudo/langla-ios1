package beatdz.tab;

import beatdz.Binary2;
import beatdz.Char;
import beatdz.DataCenter;
import beatdz.Graphics;
import beatdz.Item;
import beatdz.LangLa_cy;
import beatdz.LangLa_dz;
import beatdz.LangLa_fo;
import beatdz.LangLa_fq;
import beatdz.LangLa_jz;
import beatdz.MainScreen;
import beatdz.Message;
import beatdz.Utlis;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LatHinh2 extends LangLa_fo {

    private static final int GRID_COLS = 8;
    private static final int GRID_ROWS = 5;
    private static final int GRID_SIZE = GRID_COLS * GRID_ROWS;
    private static final int CELL_SIZE = 26;
    private static final int CELL_SPACING = 6;

    private final BoxItem[] boxes = new BoxItem[GRID_SIZE];
    private final boolean[] opened = new boolean[GRID_SIZE];

    private final int[] uiToServer = new int[GRID_SIZE];
    private boolean shuffledAfterReveal;
    private boolean hasServerMap;

    private final Short[] idItem = new Short[40];
    private boolean hasBoard;

    private int tick;

    private int pendingIndex = -1;
    private boolean waiting;
    private long revealUntil;

    public LangLa_dz btn_TuLat;
    public LangLa_dz btn_Reset;

    public LatHinh2(MainScreen var1) {
        this.a(300, 210);
        this.p = var1;
        this.r = false;
        this.cF = 2;

        btn_TuLat = this.a((this.width / 2) - 30 - 50, getZ(this.height, 90), "Cho Kẹo", this, 9999, -100);
        btn_Reset = this.a((this.width / 2) - 30 + 50, getZ(this.height, 90), "Reset", this, 10001, -100);
        btn_Reset.a(true);
    }

    public void read(Message msg) {
        try {
            byte sub = msg.cmd;
            if (sub == 106) {
                initBoxesIfNeeded();
                for (int i = 0; i < 40; i++) {
                    short id = msg.readShort();
                    idItem[i] = id;
                }
                hasBoard = true;
                waiting = false;
                pendingIndex = -1;
                revealUntil = System.currentTimeMillis() + 900L;
                initMapping();
                shuffledAfterReveal = false;
                hasServerMap = false;
                for (int i = 0; i < GRID_SIZE; i++) {
                    opened[i] = false;
                    if (boxes[i] != null) {
                        boxes[i].item = null;
                    }
                }

                if (msg.reader != null && msg.reader.dis.available() >= GRID_SIZE) {
                    for (int i = 0; i < GRID_SIZE; i++) {
                        uiToServer[i] = msg.readByte();
                    }
                    hasServerMap = true;
                }

                if (msg.reader != null && msg.reader.dis.available() >= GRID_SIZE) {
                    for (int serverIndex = 0; serverIndex < GRID_SIZE; serverIndex++) {
                        boolean op = msg.readBoolean();
                        if (op) {
                            int uiIndex = uiIndexFromServer(serverIndex);
                            if (uiIndex >= 0 && uiIndex < GRID_SIZE) {
                                opened[uiIndex] = true;
                                if (boxes[uiIndex] != null && idItem[serverIndex] != null) {
                                    boxes[uiIndex].item = new Item(idItem[serverIndex]);
                                }
                            }
                        }
                    }
                }
            } else if (sub == 107) {
                initBoxesIfNeeded();
                int index = msg.readByte();
                short id = msg.readShort();
                int uiIndex = uiIndexFromServer(index);
                if (uiIndex >= 0 && uiIndex < GRID_SIZE) {
                    opened[uiIndex] = true;
                    boxes[uiIndex].item = new Item(id);
                }
                waiting = false;
                pendingIndex = -1;
                int openCount = 0;
                for (boolean b : opened) {
                    if (b) {
                        openCount++;
                    }
                }
                if (openCount >= GRID_SIZE) {
                    scheduleReset();
                }
            } else if (sub == 108) {
                scheduleReset();
            }
        } catch (IOException ex) {
            Logger.getLogger(LatHinh2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void a(int var1, Object var2, LangLa_cy var3) {
    }

    @Override
    public void a(int var1, Object var2) {
    }

    @Override
    public void a(Graphics g) {
        this.a(g, this.cx, this.cy, this.width, this.height, " Ai Là Kẻ Chiến Thắng ", (byte) 2, this.r);

        initBoxesIfNeeded();

        tick++;

        boolean revealAll = revealUntil != 0 && System.currentTimeMillis() < revealUntil;
        if (!revealAll && revealUntil != 0 && hasBoard && !shuffledAfterReveal) {
            if (!hasServerMap) {
                shuffleMapping();
            }
            shuffledAfterReveal = true;
            revealUntil = 0L;
        }

        for (int i = 0; i < GRID_SIZE; i++) {
            BoxItem b = boxes[i];
            if (b == null) {
                continue;
            }

            b(g, b.x, b.y, null, false);

            if (revealAll) {
                short id = idItem[i] != null ? idItem[i] : -1;
                if (id != -1) {
                    LangLa_fq.a(g, DataCenter.gI().ItemTemplate[id].idIcon, 0, b.x + (CELL_SIZE / 2), b.y + (CELL_SIZE / 2), 3);
                } else {
                    LangLa_fq.a(g, DataCenter.gI().ItemTemplate[852].idIcon, 0, b.x + (CELL_SIZE / 2), b.y + (CELL_SIZE / 2), 3);
                }
            } else if (opened[i]) {
                Item it = b.item;
                if (it != null) {
                    LangLa_fq.a(g, it.getItemTemplate().idIcon, 0, b.x + (CELL_SIZE / 2), b.y + (CELL_SIZE / 2), 3);
                }
            } else {
                if (tick % 60 <= 5) {
                    g.a((float) 1.15F, (float) 0.15F);
                }
                LangLa_fq.a(g, DataCenter.gI().ItemTemplate[852].idIcon, 0, b.x + (CELL_SIZE / 2), b.y + (CELL_SIZE / 2), 3);
                if (tick % 60 <= 5) {
                    g.h();
                }
            }

            if (opened[i]) {
                Binary2.a(g, -5, 0, b.x + 1, b.y + 1, CELL_SIZE - 2, CELL_SIZE - 2);
            }
        }

        this.b(g);

        if (delayReset != 0 && System.currentTimeMillis() >= delayReset) {
            delayReset = 0;
            resetAll();
        }
    }

    private long delayReset;

    private void scheduleReset() {
        delayReset = System.currentTimeMillis() + 150L;
    }

    private void resetAll() {
        for (int i = 0; i < GRID_SIZE; i++) {
            opened[i] = false;
            boxes[i] = null;
            idItem[i] = null;
        }
        initMapping();
        shuffledAfterReveal = false;
        hasServerMap = false;
        pendingIndex = -1;
        waiting = false;
        hasBoard = false;
        revealUntil = 0L;
    }

    private void initMapping() {
        for (int i = 0; i < GRID_SIZE; i++) {
            uiToServer[i] = i;
        }
    }

    private void shuffleMapping() {
        for (int i = GRID_SIZE - 1; i > 0; i--) {
            int j = Utlis.nextInt(i + 1);
            int tmp = uiToServer[i];
            uiToServer[i] = uiToServer[j];
            uiToServer[j] = tmp;
        }
    }

    private int uiIndexFromServer(int serverIndex) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (uiToServer[i] == serverIndex) {
                return i;
            }
        }
        return serverIndex;
    }

    private void initBoxesIfNeeded() {
        if (boxes[0] != null) {
            return;
        }

        int gridW = GRID_COLS * CELL_SIZE + (GRID_COLS - 1) * CELL_SPACING;
        int startX = (this.width - gridW) / 2;
        int startY = 35;

        for (int i = 0; i < GRID_SIZE; i++) {
            BoxItem b = new BoxItem();
            int col = i % GRID_COLS;
            int row = i / GRID_COLS;
            b.x = startX + col * (CELL_SIZE + CELL_SPACING);
            b.y = startY + row * (CELL_SIZE + CELL_SPACING);
            b.item = new Item(852);
            b.item.level = 0;
            b.item.rangeNE = true;
            boxes[i] = b;
        }
    }

    public void a(LangLa_jz var1, int var2, int var3) {
        if (var1.b >= 2907 && var1.b < 2907 + GRID_SIZE) {
            int index = var1.b - 2907;
            if (index < 0 || index >= GRID_SIZE) {
                return;
            }
            if (revealUntil != 0 && System.currentTimeMillis() < revealUntil) {
                return;
            }
            if (opened[index]) {
                return;
            }
            if (waiting) {
                return;
            }
            if (!checkVeMayMan()) {
                DataCenter.gI().currentScreen.showMessage(" Bạn Không Có Kẹo Rồi ", -65536);
                return;
            }

            if (!hasBoard) {
                waiting = true;
                initBoard();
                return;
            }

            pendingIndex = index;
            waiting = true;
            openCell((byte) uiToServer[index]);
            return;
        }

        if (var1.b == 9999) {
            if (waiting) {
                return;
            }
            if (revealUntil != 0 && System.currentTimeMillis() < revealUntil) {
                return;
            }
            if (!checkVeMayMan()) {
                DataCenter.gI().currentScreen.showMessage(" Bạn Không Có Kẹo Rồi ", -65536);
                return;
            }
            if (!hasBoard) {
                waiting = true;
                initBoard();
                return;
            }
            int index = -1;
            for (int t = 0; t < 50; t++) {
                int rIndex = Utlis.nextInt(GRID_SIZE);
                if (!opened[rIndex]) {
                    index = rIndex;
                    break;
                }
            }
            if (index == -1) {
                scheduleReset();
                return;
            }
            pendingIndex = index;
            waiting = true;
            openCell((byte) uiToServer[index]);
            return;
        }

        if (var1.b == 10001) {
            waiting = true;
            resetBoard();
        }
    }

    public boolean checkVeMayMan() {
        for (Item item : Char.gI().arrItemBag) {
            if (item != null && item.id == 932) {
                return true;
            }
        }
        return false;
    }

    public Vector c() {
        Vector vec = super.c();
        initBoxesIfNeeded();
        for (int i = 0; i < GRID_SIZE; i++) {
            BoxItem b = boxes[i];
            if (b != null) {
                vec.add(new LangLa_jz(2907 + i, b.x, b.y, b.x + CELL_SIZE, b.y + CELL_SIZE, null, this));
            }
        }
        return vec;
    }

    public void initBoard() {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(106);
            msg.send();
        } catch (Exception ex) {
        }
    }

    public void openCell(byte index) {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(107);
            msg.writeByte(index);
            msg.send();
        } catch (Exception ex) {
        }
    }

    public void resetBoard() {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(108);
            msg.send();
        } catch (Exception ex) {
        }
    }

    @Override
    public void b() {
    }

    public int getZ(int total, int p) {
        return total * p / 100;
    }

    public static class BoxItem {

        public int x;
        public int y;
        public Item item;
    }
}
