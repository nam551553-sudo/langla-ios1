package beatdz;

import java.util.Vector;

public class LangLa_event extends LangLa_fn {

    private byte[] recipeIds;
    private Item[][] materials;
    private int[][] materialNeeds;
    private int[][] materialHas;
    private Item[] rewards;
    private byte[] weddingRecipeIds;
    private Item[][] weddingMaterials;
    private int[][] weddingMaterialNeeds;
    private int[][] weddingMaterialHas;
    private int[] weddingGoldNeeds;
    private long[] weddingGoldHas;
    private Item[] weddingRewards;
    private byte[] topRanks;
    private String[] topNames;
    private int[] topPoints;
    private Item[][] topRewards;
    private boolean eventEnded;
    private int topRewarded;
    private int selectedTopReward;
    private LangLa_ij[] topRewardSlots;
    private LangLa_dz topRewardButton;
    private LangLa_dz topRewardCloseButton;
    private int currentEventPoint;
    private int commonMilestoneRewarded;
    private int personalMilestoneRewarded;
    private LangLa_ij[][] materialSlots;
    private LangLa_ij[] rewardSlots;
    private LangLa_dz[] changeButtons;
    private LangLa_ii exchangeScroll;
    private LangLa_ij[][] weddingMaterialSlots;
    private LangLa_ij[] weddingGoldSlots;
    private LangLa_ij[] weddingRewardSlots;
    private LangLa_dz[] weddingButtons;
    private LangLa_ii weddingScroll;
    private Item[] weddingGoldItems;
    private Item[] commonMilestoneRewards;
    private Item[] personalMilestoneRewards;
    private LangLa_ij[] commonMilestoneSlots;
    private LangLa_ij[] personalMilestoneSlots;
    private LangLa_dz commonMilestoneButton;
    private LangLa_dz personalMilestoneButton;
    private int[] commonMilestonePoints;
    private int[] personalMilestonePoints;

    public LangLa_event(MainScreen screen, Message msg) {
        super(screen);
        this.r = false;
        this.a(360, 255);
        LangLa_kd tabs = new LangLa_kd(-1000);
        tabs.c = new String[]{"Đổi Kem", "Đổi Cá", "Mốc Thưởng", "BXH"};
        this.a(tabs, 205, 72, 4);
        this.readExchangeItems(msg);
        this.initExchangeSlots();
        this.initWeddingSlots();
        this.initMilestoneSlots();
        this.initTopRewardSlots();
        this.a(0);
    }

    private void readExchangeItems(Message msg) {
        try {
            int size = msg.readByte();
            this.recipeIds = new byte[size];
            this.materials = new Item[size][];
            this.materialNeeds = new int[size][];
            this.materialHas = new int[size][];
            this.rewards = new Item[size];
            for (int row = 0; row < size; row++) {
                this.recipeIds[row] = msg.readByte();
                int materialCount = msg.readByte();
                this.materials[row] = new Item[materialCount];
                this.materialNeeds[row] = new int[materialCount];
                this.materialHas[row] = new int[materialCount];
                for (int col = 0; col < materialCount; col++) {
                    int itemId = msg.readShort();
                    this.materialNeeds[row][col] = msg.readInt();
                    this.materialHas[row][col] = msg.readInt();
                    this.materials[row][col] = this.newPreviewItem(itemId, this.materialNeeds[row][col]);
                }
                this.rewards[row] = this.newPreviewItem(msg.readShort(), msg.readInt());
            }
            int weddingSize = msg.readByte();
            this.weddingRecipeIds = new byte[weddingSize];
            this.weddingMaterials = new Item[weddingSize][];
            this.weddingMaterialNeeds = new int[weddingSize][];
            this.weddingMaterialHas = new int[weddingSize][];
            this.weddingGoldNeeds = new int[weddingSize];
            this.weddingGoldHas = new long[weddingSize];
            this.weddingGoldItems = new Item[weddingSize];
            this.weddingRewards = new Item[weddingSize];
            for (int row = 0; row < weddingSize; row++) {
                this.weddingRecipeIds[row] = msg.readByte();
                int materialCount = msg.readByte();
                this.weddingMaterials[row] = new Item[materialCount];
                this.weddingMaterialNeeds[row] = new int[materialCount];
                this.weddingMaterialHas[row] = new int[materialCount];
                for (int col = 0; col < materialCount; col++) {
                    int itemId = msg.readShort();
                    this.weddingMaterialNeeds[row][col] = msg.readInt();
                    this.weddingMaterialHas[row][col] = msg.readInt();
                    this.weddingMaterials[row][col] = this.newPreviewItem(itemId, this.weddingMaterialNeeds[row][col]);
                }
                this.weddingGoldNeeds[row] = msg.readInt();
                this.weddingGoldHas[row] = msg.readLong();
                this.weddingGoldItems[row] = this.newPreviewItem(193, this.weddingGoldNeeds[row]);
                this.weddingRewards[row] = this.newPreviewItem(msg.readShort(), msg.readInt());
                this.weddingRewards[row].strOptions = msg.readUTF();
            }
            int topSize = msg.readByte();
            this.topRanks = new byte[topSize];
            this.topNames = new String[topSize];
            this.topPoints = new int[topSize];
            for (int i = 0; i < topSize; i++) {
                this.topRanks[i] = msg.readByte();
                this.topNames[i] = msg.readUTF();
                this.topPoints[i] = msg.readInt();
            }
            this.eventEnded = msg.readBoolean();
            this.topRewarded = msg.readInt();
            this.topRewards = new Item[10][];
            for (int rank = 0; rank < this.topRewards.length; rank++) {
                int rewardCount = msg.readByte();
                this.topRewards[rank] = new Item[rewardCount];
                for (int i = 0; i < rewardCount; i++) {
                    this.topRewards[rank][i] = this.newPreviewItem(msg.readShort(), msg.readInt());
                    this.topRewards[rank][i].strOptions = msg.readUTF();
                }
            }
            this.currentEventPoint = msg.readInt();
            this.commonMilestoneRewarded = msg.readInt();
            this.personalMilestoneRewarded = msg.readInt();
        } catch (Exception e) {
            Utlis.println(e);
            this.recipeIds = new byte[0];
            this.materials = new Item[0][0];
            this.materialNeeds = new int[0][0];
            this.materialHas = new int[0][0];
            this.rewards = new Item[0];
            this.weddingRecipeIds = new byte[0];
            this.weddingMaterials = new Item[0][0];
            this.weddingMaterialNeeds = new int[0][0];
            this.weddingMaterialHas = new int[0][0];
            this.weddingGoldNeeds = new int[0];
            this.weddingGoldHas = new long[0];
            this.weddingGoldItems = new Item[0];
            this.weddingRewards = new Item[0];
            this.topRanks = new byte[0];
            this.topNames = new String[0];
            this.topPoints = new int[0];
            this.topRewards = new Item[0][0];
            this.eventEnded = false;
            this.topRewarded = 0;
            this.selectedTopReward = -1;
            this.currentEventPoint = 0;
            this.commonMilestoneRewarded = 0;
            this.personalMilestoneRewarded = 0;
        }
    }

    private Item newPreviewItem(int id, int amount) {
        Item item = new Item(id, true);
        item.isLock = true;
        item.setAmount(amount);
        return item;
    }

    private void initExchangeSlots() {
        int rows = this.rewards.length;
        this.materialSlots = new LangLa_ij[rows][];
        this.rewardSlots = new LangLa_ij[rows];
        this.changeButtons = new LangLa_dz[rows];
        int scrollX = 8;
        int scrollY = this.a_() + 30;
        int scrollH = this.height - scrollY - 42;
        int startX = 16;
        int startY = 0;
        int slot = 30;
        int gap = 8;
        int rowGap = 49;
        this.exchangeScroll = new LangLa_ii((byte) 1, scrollX, scrollY, this.width - scrollX * 2, scrollH, rowGap, rows);
        for (int row = 0; row < rows; row++) {
            int y = startY + row * rowGap;
            this.materialSlots[row] = new LangLa_ij[this.materials[row].length];
            for (int col = 0; col < this.materials[row].length; col++) {
                int x = startX + col * (slot + 23);
                this.materialSlots[row][col] = new LangLa_ij((byte) 1, x, y, slot, slot, slot, 1, 1);
            }
            int rewardX = startX + 4 * (slot + 23) + gap + 10;
            this.rewardSlots[row] = new LangLa_ij((byte) 1, rewardX, y, slot, slot, slot, 1, 1);
            this.changeButtons[row] = new LangLa_dz(this.width - 73, y + 4, 0, 0, this.isEnough(row) ? "Đổi" : "Không đủ", this, 7000 + row, 7);
            this.changeButtons[row].setSize(50, 20);
        }
    }

    private void initWeddingSlots() {
        int rows = this.weddingRewards.length;
        this.weddingMaterialSlots = new LangLa_ij[rows][];
        this.weddingGoldSlots = new LangLa_ij[rows];
        this.weddingRewardSlots = new LangLa_ij[rows];
        this.weddingButtons = new LangLa_dz[rows];
        int scrollX = 8;
        int scrollY = this.a_() + 10;
        int scrollH = this.height - scrollY - 42;
        int startX = 16;
        int startY = 0;
        int slot = 30;
        int rowGap = 49;
        this.weddingScroll = new LangLa_ii((byte) 1, scrollX, scrollY, this.width - scrollX * 2, scrollH, rowGap, rows);
        for (int row = 0; row < rows; row++) {
            int y = startY + row * rowGap;
            this.weddingMaterialSlots[row] = new LangLa_ij[this.weddingMaterials[row].length];
            for (int col = 0; col < this.weddingMaterials[row].length; col++) {
                this.weddingMaterialSlots[row][col] = new LangLa_ij((byte) 1, startX + col * 48, y + 7, slot, slot, slot, 1, 1);
            }
            this.weddingGoldSlots[row] = new LangLa_ij((byte) 1, startX + 144, y + 7, slot, slot, slot, 1, 1);
            this.weddingRewardSlots[row] = new LangLa_ij((byte) 1, startX + 204, y + 7, slot, slot, slot, 1, 1);
            this.weddingButtons[row] = new LangLa_dz(this.width - 72, y + 12, 0, 0, this.isWeddingEnough(row) ? "Đổi" : "Không đủ", this, 7300 + row, 7);
            this.weddingButtons[row].setSize(50, 20);
        }
    }

    private void initMilestoneSlots() {
        this.personalMilestonePoints = new int[]{100, 1000, 3000, 5000, 10000};
        this.commonMilestonePoints = new int[]{1000, 10000, 30000, 50000, 100000};
        this.commonMilestoneRewards = new Item[this.commonMilestonePoints.length];
        this.personalMilestoneRewards = new Item[this.personalMilestonePoints.length];
        this.commonMilestoneSlots = new LangLa_ij[this.commonMilestonePoints.length];
        this.personalMilestoneSlots = new LangLa_ij[this.personalMilestonePoints.length];
        int startX = 88;
        int slotYTop = this.a_() + 64;
        int slotYBottom = this.a_() + 161;
        int gap = 39;
        for (int i = 0; i < this.commonMilestonePoints.length; i++) {
            this.commonMilestoneRewards[i] = this.newPreviewItem(419, 1);
            this.personalMilestoneRewards[i] = this.newPreviewItem(419, 1);
            this.commonMilestoneSlots[i] = new LangLa_ij((byte) 1, startX + i * gap, slotYTop, 28, 28, 28, 1, 1);
            this.personalMilestoneSlots[i] = new LangLa_ij((byte) 1, startX + i * gap, slotYBottom, 28, 28, 28, 1, 1);
        }
        this.commonMilestoneButton = new LangLa_dz(this.width - 86, this.a_() + 45, 0, 0, "Nhận", this, 8200, 7);
        this.commonMilestoneButton.setSize(70, 22);
        this.personalMilestoneButton = new LangLa_dz(this.width - 86, this.a_() + 142, 0, 0, "Nhận", this, 8201, 7);
        this.personalMilestoneButton.setSize(70, 22);
        this.updateMilestoneButtons();
    }

    private void initTopRewardSlots() {
        this.selectedTopReward = -1;
        this.topRewardSlots = new LangLa_ij[8];
        int startX = this.width / 2 - 70;
        int startY = this.a_() + 64;
        for (int i = 0; i < this.topRewardSlots.length; i++) {
            this.topRewardSlots[i] = new LangLa_ij((byte) 1, startX + i % 4 * 46, startY + i / 4 * 45, 30, 30, 30, 1, 1);
        }
        this.topRewardButton = new LangLa_dz(this.width / 2 - 35, this.a_() + 164, 0, 0, "Nhận", this, 8600, 7);
        this.topRewardButton.setSize(70, 22);
        this.topRewardCloseButton = new LangLa_dz(this.width / 2 + 72, this.a_() + 34, 0, 0, "X", this, 8601, 7);
        this.topRewardCloseButton.setSize(22, 18);
    }

    private void updateMilestoneButtons() {
        this.updateMilestoneButton(this.commonMilestoneButton, this.commonMilestoneRewarded, this.commonMilestonePoints);
        this.updateMilestoneButton(this.personalMilestoneButton, this.personalMilestoneRewarded, this.personalMilestonePoints);
    }

    private void updateMilestoneButton(LangLa_dz button, int rewarded, int[] points) {
        boolean canReceive = this.canReceiveMilestone(rewarded, points);
        button.a = "Nhận";
        button.a(false);
        button.d = (short) (canReceive ? 9 : 7);
    }

    public void b() {
        super.b();
        if (this.k() == 0) {
            this.exchangeScroll.a();
            for (int row = 0; row < this.materialSlots.length; row++) {
                for (int col = 0; col < this.materialSlots[row].length; col++) {
                    this.materialSlots[row][col].a();
                }
                this.rewardSlots[row].a();
            }
        } else if (this.k() == 1) {
            this.weddingScroll.a();
            for (int row = 0; row < this.weddingMaterialSlots.length; row++) {
                for (int col = 0; col < this.weddingMaterialSlots[row].length; col++) {
                    this.weddingMaterialSlots[row][col].a();
                }
                this.weddingGoldSlots[row].a();
                this.weddingRewardSlots[row].a();
            }
        } else if (this.k() == 2) {
            for (int i = 0; i < this.commonMilestoneSlots.length; i++) {
                this.commonMilestoneSlots[i].a();
                this.personalMilestoneSlots[i].a();
            }
        }
    }

    public void a(Graphics g) {
        super.a(g);
        if (this.k() == 0) {
            this.paintExchangeTab(g);
        } else if (this.k() == 1) {
            this.paintWeddingTab(g);
        } else if (this.k() == 2) {
            this.paintMilestoneTab(g);
        } else if (this.k() == 3) {
            this.paintEventTop(g);
        }
    }

    private void paintMilestoneTab(Graphics g) {
        this.updateMilestoneButtons();
        this.paintMilestoneBox(g, 12, this.a_() + 9, this.width - 24, 88, "Mốc chung", this.commonMilestonePoints, this.commonMilestoneRewards, this.commonMilestoneSlots, this.commonMilestoneButton);
        this.paintMilestoneBox(g, 12, this.a_() + 106, this.width - 24, 88, "Mốc đơn", this.personalMilestonePoints, this.personalMilestoneRewards, this.personalMilestoneSlots, this.personalMilestoneButton);
    }

    private void paintMilestoneBox(Graphics g, int x, int y, int w, int h, String title, int[] points, Item[] rewards, LangLa_ij[] slots, LangLa_dz button) {
        this.a(g, x, y, w, h, -11, 55, 56);
        mFont.b(mFont.e, g, title, x + 12, y + 18, 0, -1, -16777216);
        mFont.b(mFont.d, g, "Hiện tại: " + Utlis.numberFormat(this.currentEventPoint) + " điểm", x + w - 8, y + 8, 1, -1, -16777216);
        int lineX = x + 92;
        int lineY = y + 34;
        int lineW = 150;
        g.f(4802889);
        g.c(lineX, lineY, lineW, 3);
        for (int i = 0; i < points.length; i++) {
            int px = lineX + i * (lineW / (points.length - 1));
            g.f(-65536);
            g.c(px, lineY - 8, 1, 16);
            mFont.b(mFont.d, g, Utlis.numberFormat(points[i]), px, i % 2 == 0 ? lineY - 22 : lineY + 10, 2, -65536, -16777216);
            LangLa_cz.a(g, slots[i].cx, slots[i].cy, rewards[i], slots[i].k >= 0, "Mốc thưởng");
            mFont.b(mFont.d, g, "(" + Utlis.numberFormat(points[i]) + ")", slots[i].cx + slots[i].q / 2, slots[i].cy + slots[i].q + 2, 2, -1, -16777216);
        }
        int oldX = g.a;
        int oldY = g.b;
        g.a(button.cx, button.cy);
        button.a(g);
        this.a(g, oldX, oldY);
    }

    private void paintExchangeTab(Graphics g) {
        mFont.b(mFont.d, g, "Sự kiện Giỗ Tổ Hùng Vương 2026", this.width / 2, this.a_() + 12, 2, -2560, -16777216);
        this.a(g, this.exchangeScroll);
        for (int row = 0; row < this.rewards.length; row++) {
            for (int col = 0; col < this.materials[row].length; col++) {
                LangLa_ij slot = this.materialSlots[row][col];
                LangLa_cz.a(g, slot.cx, slot.cy, this.materials[row][col], slot.k >= 0, "Nguyên liệu");
                int color = this.materialHas[row][col] >= this.materialNeeds[row][col] ? -1 : -65536;
                mFont.b(mFont.d, g, "(" + this.materialHas[row][col] + ")", slot.cx + slot.q / 2, slot.cy + slot.q + 4, 2, color, -16777216);
                if (col < this.materials[row].length - 1) {
                    mFont.b(mFont.d, g, "+", slot.cx + slot.q + 10, slot.cy + 9, 0, -1, -16777216);
                }
            }
            LangLa_ij last = this.materialSlots[row][this.materialSlots[row].length - 1];
            LangLa_ij reward = this.rewardSlots[row];
            mFont.b(mFont.d, g, "=", last.cx + last.q + 18, last.cy + 9, 0, -65536, -16777216);
            LangLa_cz.a(g, reward.cx, reward.cy, this.rewards[row], reward.k >= 0, "Vật phẩm nhận");
            mFont.b(mFont.d, g, this.rewards[row].isLock ? "(khóa)" : "(k.khóa)", reward.cx + reward.q / 2, reward.cy + reward.q + 4, 2, -16742145, -16777216);
            LangLa_dz button = this.changeButtons[row];
            int oldX = g.a;
            int oldY = g.b;
            g.a(button.cx, button.cy);
            button.a(g);
            this.a(g, oldX, oldY);
        }
        this.b(g);
        mFont.b(mFont.d, g, "- Số trong () là số lượng vật phẩm bạn đang có trong túi.", 16, this.height - 25, 0, -1, -16777216);
    }

    private boolean isEnough(int row) {
        for (int col = 0; col < this.materialNeeds[row].length; col++) {
            if (this.materialHas[row][col] < this.materialNeeds[row][col]) {
                return false;
            }
        }
        return true;
    }

    private void paintWeddingTab(Graphics g) {
        this.a(g, this.weddingScroll);
        int boxW = this.weddingScroll.width;
        for (int row = 0; row < this.weddingRewards.length; row++) {
            int lineY = row * 49;
            if (!this.weddingScroll.b(row)) {
                continue;
            }
            this.a(g, 2, lineY + 1, boxW - 4, 46, -22, 55, 56);
            for (int col = 0; col < this.weddingMaterials[row].length; col++) {
                LangLa_ij slot = this.weddingMaterialSlots[row][col];
                LangLa_cz.a(g, slot.cx, slot.cy, this.weddingMaterials[row][col], slot.k >= 0, "Nguyên liệu");
                int color = this.weddingMaterialHas[row][col] >= this.weddingMaterialNeeds[row][col] ? -1 : -65536;
                mFont.b(mFont.d, g, "(" + this.weddingMaterialHas[row][col] + ")", slot.cx + slot.q / 2, slot.cy + slot.q + 4, 2, color, -16777216);
                mFont.b(mFont.d, g, "+", slot.cx + slot.q + 8, slot.cy + 9, 0, -1, -16777216);
            }
            LangLa_ij gold = this.weddingGoldSlots[row];
            int goldColor = this.weddingGoldHas[row] >= this.weddingGoldNeeds[row] ? -1 : -65536;
            LangLa_cz.a(g, gold.cx, gold.cy, this.weddingGoldItems[row], gold.k >= 0, "Vàng");
            mFont.b(mFont.d, g, "(" + Utlis.numberFormat(this.weddingGoldHas[row]) + ")", gold.cx + gold.q / 2, gold.cy + gold.q + 4, 2, goldColor, -16777216);
            LangLa_ij reward = this.weddingRewardSlots[row];
            mFont.b(mFont.d, g, "=", reward.cx - 18, reward.cy + 9, 0, -65536, -16777216);
            LangLa_cz.a(g, reward.cx, reward.cy, this.weddingRewards[row], reward.k >= 0, "Sính lễ");
            LangLa_dz button = this.weddingButtons[row];
            int oldX = g.a;
            int oldY = g.b;
            g.a(button.cx, button.cy);
            button.a(g);
            this.a(g, oldX, oldY);
        }
        this.b(g);
        mFont.b(mFont.d, g, "- Mỗi mâm cần 3 vật phẩm và 1.000 vàng.", 24, this.height - 25, 0, -1, -16777216);
    }

    private boolean isWeddingEnough(int row) {
        if (this.weddingGoldHas[row] < this.weddingGoldNeeds[row]) {
            return false;
        }
        for (int col = 0; col < this.weddingMaterialNeeds[row].length; col++) {
            if (this.weddingMaterialHas[row][col] < this.weddingMaterialNeeds[row][col]) {
                return false;
            }
        }
        return true;
    }

    private void paintEventTop(Graphics g) {
        int tableW = this.width - 96;
        int tableX = 18;
        int tableY = this.a_() + 40;
        int headerH = 24;
        int rowH = 18;
        int bodyH = rowH * 10 + 2;
        int rankX = tableX + 30;
        int nameX = tableX + 118;
        int pointX = tableX + tableW - 72;
        int rewardX = tableX + tableW - 26;
        this.a(g, tableX, tableY -40, tableW, headerH, -11, 55, 56);
        this.a(g, tableX, tableY + headerH -40, tableW, bodyH, -11, 55, 56);
        mFont.b(mFont.e, g, "Hạng", rankX, tableY -30, 2, -6488, -10275328);
        mFont.b(mFont.e, g, "Nhân vật", nameX, tableY -30, 2, -6488, -10275328);
        mFont.b(mFont.e, g, "Điểm", pointX, tableY -30, 2, -6488, -10275328);
        mFont.b(mFont.e, g, "Quà", rewardX, tableY -30, 2, -6488, -10275328);
        if (this.topNames.length == 0) {
            mFont.b(mFont.d, g, "Chưa có dữ liệu xếp hạng.", this.width / 2, tableY + 82, 2, -10831436, -16777216);
            this.paintTopRewardPreview(g);
            return;
        }
        for (int i = 0; i < this.topNames.length && i < 10; i++) {
            int y = tableY + headerH + 8 + i * rowH;
            if (i < 3) {
                g.f(13136426);
                g.c(tableX + 3, y - 39, tableW - 6, rowH);
            }
            int color = i < 3 ? -2560 : -3604601;
            mFont.b(mFont.d, g, String.valueOf(this.topRanks[i]), rankX, y -30, 2, color, -16777216);
            mFont.b(mFont.d, g, this.topNames[i], nameX, y -30, 2, color, -16777216);
            mFont.b(mFont.d, g, Utlis.numberFormat(this.topPoints[i]), pointX, y -30, 2, color, -16777216);
            mFont.b(mFont.d, g, "Xem", rewardX, y -30, 2, -16711936, -16777216);
        }
        this.paintTopRewardPreview(g);
    }

    private void paintTopRewardPreview(Graphics g) {
        if (this.selectedTopReward < 0 || this.selectedTopReward >= this.topRewards.length) {
            return;
        }
        int x = this.width / 2 - 105;
        int y = this.a_() + 32;
        this.a(g, x, y, 210, 160, -11, 55, 56);
        mFont.b(mFont.e, g, "Quà TOP " + (this.selectedTopReward + 1), this.width / 2, y + 12, 2, -2560, -16777216);
        int oldXClose = g.a;
        int oldYClose = g.b;
        g.a(this.topRewardCloseButton.cx, this.topRewardCloseButton.cy);
        this.topRewardCloseButton.a(g);
        this.a(g, oldXClose, oldYClose);
        Item[] rewards = this.topRewards[this.selectedTopReward];
        for (int i = 0; i < this.topRewardSlots.length && i < rewards.length; i++) {
            LangLa_ij slot = this.topRewardSlots[i];
            LangLa_cz.a(g, slot.cx, slot.cy, rewards[i], slot.k >= 0, "Quà TOP");
        }
        int oldX = g.a;
        int oldY = g.b;
        this.topRewardButton.d = (short) (this.eventEnded && this.topRewarded == 0 ? 9 : 7);
        g.a(this.topRewardButton.cx, this.topRewardButton.cy);
        this.topRewardButton.a(g);
        this.a(g, oldX, oldY);
    }

    public Vector c() {
        Vector v = super.c();
        if (this.k() == 0) {
            for (int row = 0; row < this.materialSlots.length; row++) {
                for (int col = 0; col < this.materialSlots[row].length; col++) {
                    LangLa_ij slot = this.materialSlots[row][col];
                    v.addElement(new LangLa_jz(7100 + row * 10 + col, this.exchangeScroll.cx + slot.cx, this.exchangeScroll.cy + slot.cy - this.exchangeScroll.f, this.exchangeScroll.cx + slot.cx + slot.width, this.exchangeScroll.cy + slot.cy + slot.height - this.exchangeScroll.f, null, this));
                }
                LangLa_ij reward = this.rewardSlots[row];
                v.addElement(new LangLa_jz(7200 + row, this.exchangeScroll.cx + reward.cx, this.exchangeScroll.cy + reward.cy - this.exchangeScroll.f, this.exchangeScroll.cx + reward.cx + reward.width, this.exchangeScroll.cy + reward.cy + reward.height - this.exchangeScroll.f, null, this));
                LangLa_dz button = this.changeButtons[row];
                v.addElement(new LangLa_jz(7000 + row, this.exchangeScroll.cx + button.cx, this.exchangeScroll.cy + button.cy - this.exchangeScroll.f, this.exchangeScroll.cx + button.cx + button.width, this.exchangeScroll.cy + button.cy + button.height - this.exchangeScroll.f, null, this));
            }
            v.addElement(this.exchangeScroll.a(7600, this));
        } else if (this.k() == 1) {
            for (int row = 0; row < this.weddingMaterialSlots.length; row++) {
                for (int col = 0; col < this.weddingMaterialSlots[row].length; col++) {
                    LangLa_ij slot = this.weddingMaterialSlots[row][col];
                    v.addElement(new LangLa_jz(7400 + row * 10 + col, this.weddingScroll.cx + slot.cx, this.weddingScroll.cy + slot.cy - this.weddingScroll.f, this.weddingScroll.cx + slot.cx + slot.width, this.weddingScroll.cy + slot.cy + slot.height - this.weddingScroll.f, null, this));
                }
                LangLa_ij gold = this.weddingGoldSlots[row];
                v.addElement(new LangLa_jz(7600 + row, this.weddingScroll.cx + gold.cx, this.weddingScroll.cy + gold.cy - this.weddingScroll.f, this.weddingScroll.cx + gold.cx + gold.width, this.weddingScroll.cy + gold.cy + gold.height - this.weddingScroll.f, null, this));
                LangLa_ij reward = this.weddingRewardSlots[row];
                v.addElement(new LangLa_jz(7500 + row, this.weddingScroll.cx + reward.cx, this.weddingScroll.cy + reward.cy - this.weddingScroll.f, this.weddingScroll.cx + reward.cx + reward.width, this.weddingScroll.cy + reward.cy + reward.height - this.weddingScroll.f, null, this));
                LangLa_dz button = this.weddingButtons[row];
                v.addElement(new LangLa_jz(7300 + row, this.weddingScroll.cx + button.cx, this.weddingScroll.cy + button.cy - this.weddingScroll.f, this.weddingScroll.cx + button.cx + button.width, this.weddingScroll.cy + button.cy + button.height - this.weddingScroll.f, null, this));
            }
            v.addElement(this.weddingScroll.a(7700, this));
        } else if (this.k() == 2) {
            for (int i = 0; i < this.commonMilestoneSlots.length; i++) {
                LangLa_ij common = this.commonMilestoneSlots[i];
                v.addElement(new LangLa_jz(8000 + i, common.cx, common.cy, common.cx + common.width, common.cy + common.height, null, this));
                LangLa_ij personal = this.personalMilestoneSlots[i];
                v.addElement(new LangLa_jz(8100 + i, personal.cx, personal.cy, personal.cx + personal.width, personal.cy + personal.height, null, this));
            }
            v.addElement(new LangLa_jz(8200, this.commonMilestoneButton.cx, this.commonMilestoneButton.cy, this.commonMilestoneButton.cx + this.commonMilestoneButton.width, this.commonMilestoneButton.cy + this.commonMilestoneButton.height, null, this));
            v.addElement(new LangLa_jz(8201, this.personalMilestoneButton.cx, this.personalMilestoneButton.cy, this.personalMilestoneButton.cx + this.personalMilestoneButton.width, this.personalMilestoneButton.cy + this.personalMilestoneButton.height, null, this));
        } else if (this.k() == 3) {
            int tableW = this.width - 96;
            int tableX = 18;
            int tableY = this.a_() + 40;
            int headerH = 24;
            int rowH = 18;
            int rewardX = tableX + tableW - 42;
            for (int i = 0; i < this.topNames.length && i < 10; i++) {
                int y = tableY + headerH + 8 + i * rowH - 40;
                v.addElement(new LangLa_jz(8500 + i, rewardX, y, rewardX + 32, y + rowH, null, this));
            }
            if (this.selectedTopReward >= 0) {
                int previewX = this.width / 2 - 105;
                int previewY = this.a_() + 32;
                int previewW = 210;
                int previewH = 160;
                v.addElement(new LangLa_jz(8602, 0, 0, this.width, previewY, null, this));
                v.addElement(new LangLa_jz(8602, 0, previewY + previewH, this.width, this.height, null, this));
                v.addElement(new LangLa_jz(8602, 0, previewY, previewX, previewY + previewH, null, this));
                v.addElement(new LangLa_jz(8602, previewX + previewW, previewY, this.width, previewY + previewH, null, this));
                for (int i = 0; i < this.topRewardSlots.length; i++) {
                    LangLa_ij slot = this.topRewardSlots[i];
                    v.addElement(new LangLa_jz(8550 + i, slot.cx, slot.cy, slot.cx + slot.width, slot.cy + slot.height, null, this));
                }
                v.addElement(new LangLa_jz(8600, this.topRewardButton.cx, this.topRewardButton.cy, this.topRewardButton.cx + this.topRewardButton.width, this.topRewardButton.cy + this.topRewardButton.height, null, this));
                v.addElement(new LangLa_jz(8601, this.topRewardCloseButton.cx, this.topRewardCloseButton.cy, this.topRewardCloseButton.cx + this.topRewardCloseButton.width, this.topRewardCloseButton.cy + this.topRewardCloseButton.height, null, this));
            }
        }
        return v;
    }

    public void a(LangLa_jz item, int x, int y) {
        super.a(item, x, y);
        if (this.k() == 0) {
            if (item.b >= 7000 && item.b < 7000 + this.recipeIds.length) {
                this.exchangeItem(this.recipeIds[item.b - 7000]);
                return;
            }
            if (item.b >= 7100 && item.b < 7200) {
                int row = (item.b - 7100) / 10;
                int col = (item.b - 7100) % 10;
                if (row >= 0 && row < this.materials.length && col >= 0 && col < this.materials[row].length) {
                    LangLa_ij slot = this.materialSlots[row][col];
                    this.cL = LangLa_cz.a(this, this.exchangeScroll.cx + slot.cx + slot.q, this.exchangeScroll.cy + slot.cy - this.exchangeScroll.f, slot.q, this.materials[row][col]);
                }
                return;
            }
            if (item.b >= 7200 && item.b < 7200 + this.rewards.length) {
                int row = item.b - 7200;
                LangLa_ij slot = this.rewardSlots[row];
                this.cL = LangLa_cz.a(this, this.exchangeScroll.cx + slot.cx + slot.q, this.exchangeScroll.cy + slot.cy - this.exchangeScroll.f, slot.q, this.rewards[row]);
            }
        } else if (this.k() == 1) {
            if (item.b >= 7300 && item.b < 7300 + this.weddingRecipeIds.length) {
                this.exchangeWeddingItem(this.weddingRecipeIds[item.b - 7300]);
                return;
            }
            if (item.b >= 7400 && item.b < 7500) {
                int row = (item.b - 7400) / 10;
                int col = (item.b - 7400) % 10;
                if (row >= 0 && row < this.weddingMaterials.length && col >= 0 && col < this.weddingMaterials[row].length) {
                    LangLa_ij slot = this.weddingMaterialSlots[row][col];
                    this.cL = LangLa_cz.a(this, this.weddingScroll.cx + slot.cx + slot.q, this.weddingScroll.cy + slot.cy - this.weddingScroll.f, slot.q, this.weddingMaterials[row][col]);
                }
                return;
            }
            if (item.b >= 7500 && item.b < 7500 + this.weddingRewards.length) {
                int row = item.b - 7500;
                LangLa_ij slot = this.weddingRewardSlots[row];
                this.cL = LangLa_cz.a(this, this.weddingScroll.cx + slot.cx + slot.q, this.weddingScroll.cy + slot.cy - this.weddingScroll.f, slot.q, this.weddingRewards[row]);
                return;
            }
            if (item.b >= 7600 && item.b < 7600 + this.weddingGoldItems.length) {
                int row = item.b - 7600;
                LangLa_ij slot = this.weddingGoldSlots[row];
                this.cL = LangLa_cz.a(this, this.weddingScroll.cx + slot.cx + slot.q, this.weddingScroll.cy + slot.cy - this.weddingScroll.f, slot.q, this.weddingGoldItems[row]);
                return;
            }
        } else if (this.k() == 2) {
            if (item.b >= 8000 && item.b < 8000 + this.commonMilestoneRewards.length) {
                int index = item.b - 8000;
                LangLa_ij slot = this.commonMilestoneSlots[index];
                this.cL = LangLa_cz.a(this, slot.cx + slot.q, slot.cy, slot.q, this.commonMilestoneRewards[index]);
                return;
            }
            if (item.b >= 8100 && item.b < 8100 + this.personalMilestoneRewards.length) {
                int index = item.b - 8100;
                LangLa_ij slot = this.personalMilestoneSlots[index];
                this.cL = LangLa_cz.a(this, slot.cx + slot.q, slot.cy, slot.q, this.personalMilestoneRewards[index]);
                return;
            }
            if (item.b == 8200 || item.b == 8201) {
                int rewarded = item.b == 8200 ? this.commonMilestoneRewarded : this.personalMilestoneRewarded;
                int[] points = item.b == 8200 ? this.commonMilestonePoints : this.personalMilestonePoints;
                if (!this.canReceiveMilestone(rewarded, points)) {
                    DataCenter.gI().currentScreen.showMessage("Chưa đủ điểm sự kiện", -65536);
                } else {
                    this.receiveMilestone(item.b == 8200 ? 0 : 1);
                }
            }
        } else if (this.k() == 3) {
            if (item.b >= 8500 && item.b < 8510) {
                this.selectedTopReward = item.b - 8500;
                return;
            }
            if (item.b >= 8550 && item.b < 8558) {
                int index = item.b - 8550;
                if (this.selectedTopReward >= 0 && this.selectedTopReward < this.topRewards.length && index < this.topRewards[this.selectedTopReward].length) {
                    LangLa_ij slot = this.topRewardSlots[index];
                    this.cL = LangLa_cz.a(this, slot.cx + slot.q, slot.cy, slot.q, this.topRewards[this.selectedTopReward][index]);
                }
                return;
            }
            if (item.b == 8600) {
                this.receiveTopReward();
                return;
            }
            if (item.b == 8601) {
                this.selectedTopReward = -1;
                return;
            }
            if (item.b == 8602) {
                this.selectedTopReward = -1;
            }
        }
    }

    private boolean canReceiveMilestone(int rewarded, int[] points) {
        for (int i = 0; i < points.length; i++) {
            if ((rewarded & 1 << i) == 0 && this.currentEventPoint >= points[i]) {
                return true;
            }
        }
        return false;
    }

    private void receiveMilestone(int type) {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(111);
            msg.writeByte(type);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    private void receiveTopReward() {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(113);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    public void a(int id, Object obj) {
    }

    public void a(int id, Object obj, LangLa_cy source) {
    }

    private void exchangeItem(int recipe) {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(110);
            msg.writeByte(recipe);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    private void exchangeWeddingItem(int recipe) {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(112);
            msg.writeByte(recipe);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }
}
