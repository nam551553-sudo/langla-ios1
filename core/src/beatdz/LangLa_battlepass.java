package beatdz;

import java.util.Vector;

public class LangLa_battlepass extends LangLa_fo {
    private static final int TAB_TICKET = 0;
    private static final int TAB_TASK = 1;
    private static final int STATE_LOCKED = 0;
    private static final int STATE_READY = 1;
    private static final int STATE_RECEIVED = 2;
    private static final int PANEL_W = 360;
    private static final int PANEL_H = 255;
    private static final int TITLE_Y = 16;
    private static final int TAB_BAR_X = 3;
    private static final int TAB_BAR_Y = 30;
    private static final int TAB_BAR_H = 25;
    private static final int TAB_TICKET_X = 4;
    private static final int TAB_TICKET_W = (PANEL_W - TAB_TICKET_X * 2) / 2;
    private static final int TAB_TASK_X = TAB_TICKET_X + TAB_TICKET_W;
    private static final int TAB_TASK_W = TAB_TICKET_W;
    private static final int TAB_H = 19;
    private static final int TAB_INNER_PAD = 4;
    private static final int BODY_Y = 54;
    private static final int HEADER_X = 15;
    private static final int HEADER_Y = 61;
    private static final int HEADER_H = 30;
    private static final int HEADER_PROGRESS_X_OFFSET = 84;
    private static final int HEADER_PROGRESS_Y_OFFSET = 13;
    private static final int HEADER_PROGRESS_RIGHT_PAD = 190;
    private static final int VIP_PRICE = 20000;
    private static final int VIP_BUTTON_RIGHT_PAD = 73;
    private static final int VIP_BUTTON_Y = 230;
    private static final int VIP_BUTTON_W = 58;
    private static final int VIP_BUTTON_H = 19;
    private static final int TICKET_LABEL_X = 29;
    private static final int TICKET_X = 54;
    private static final int TICKET_Y = 62;
    private static final int TICKET_VIEW_H = 152;
    private static final int TICKET_COL_W = 50;
    private static final int FREE_ROW_Y = 3;
    private static final int VIP_ROW_Y = 79;
    private static final int REWARD_ROW_H = 76;
    private static final int REWARD_SLOT_SIZE = 28;
    private static final int REWARD_BUTTON_W = 35;
    private static final int REWARD_BUTTON_H = 20;
    private static final int FOOTER_X = 14;
    private static final int FOOTER_Y = 218;
    private static final int FOOTER_H = 29;
    private static final int FOOTER_BAR_X = 90;
    private static final int FOOTER_BAR_Y = 228;
    private static final int FOOTER_BAR_W = 160;
    private static final int FOOTER_BAR_H = 5;

    private int activeTab;
    private int passLevel;
    private int passExp;
    private int passNeedExp;
    private boolean vipActive;
    private BattlePassReward[] rewards;
    private BattlePassTask[] tasks;
    private LangLa_ii ticketScroll;
    private LangLa_ii taskScroll;
    private LangLa_ij[] freeSlots;
    private LangLa_ij[] vipSlots;
    private LangLa_dz[] freeButtons;
    private LangLa_dz[] vipButtons;
    private LangLa_dz[] taskButtons;
    private LangLa_dz vipButton;

    public LangLa_battlepass(MainScreen screen) {
        this(screen, null);
    }

    public LangLa_battlepass(MainScreen screen, Message msg) {
        super();
        this.p = screen;
        this.r = false;
        this.activeTab = TAB_TICKET;
        this.a(PANEL_W, PANEL_H);
        this.readData(msg);
        this.initControls();
    }

    private void readData(Message msg) {
        if (msg == null) {
            this.loadEmptyData();
            return;
        }
        try {
            this.passLevel = msg.readShort();
            this.passExp = msg.readInt();
            this.passNeedExp = msg.readInt();
            this.vipActive = msg.readBoolean();
            int rewardSize = msg.readByte();
            this.rewards = new BattlePassReward[rewardSize];
            for (int i = 0; i < rewardSize; ++i) {
                BattlePassReward reward = new BattlePassReward();
                reward.level = msg.readShort();
                reward.freeItem = this.readItem(msg);
                reward.freeState = msg.readByte();
                reward.vipItem = this.readItem(msg);
                reward.vipState = msg.readByte();
                this.rewards[i] = reward;
            }
            int taskSize = msg.readByte();
            this.tasks = new BattlePassTask[taskSize];
            for (int i = 0; i < taskSize; ++i) {
                BattlePassTask task = new BattlePassTask();
                task.id = msg.readByte();
                task.name = msg.readUTF();
                task.progress = msg.readInt();
                task.target = msg.readInt();
                task.exp = msg.readInt();
                task.state = msg.readByte();
                this.tasks[i] = task;
            }
        } catch (Exception e) {
            Utlis.println(e);
            this.loadEmptyData();
        }
    }

    private void loadEmptyData() {
        this.passLevel = 1;
        this.passExp = 0;
        this.passNeedExp = 500;
        this.vipActive = false;
        this.rewards = new BattlePassReward[0];
        this.tasks = new BattlePassTask[0];
    }

    private void loadPreviewData() {
        this.passLevel = 7;
        this.passExp = 320;
        this.passNeedExp = 500;
        this.vipActive = false;
        int[] freeIds = new int[]{193, 419, 193, 419, 193, 419, 193, 419};
        int[] vipIds = new int[]{419, 193, 419, 193, 419, 193, 419, 193};
        this.rewards = new BattlePassReward[freeIds.length];
        for (int i = 0; i < this.rewards.length; ++i) {
            BattlePassReward reward = new BattlePassReward();
            reward.level = i + 1;
            reward.freeItem = this.newPreviewItem(freeIds[i], i % 2 == 0 ? 10000 : 1);
            reward.vipItem = this.newPreviewItem(vipIds[i], i % 2 == 0 ? 3 : 50000);
            reward.freeState = i < 3 ? STATE_RECEIVED : (i == 3 ? STATE_READY : STATE_LOCKED);
            reward.vipState = i < 2 ? STATE_RECEIVED : (i == 3 ? STATE_READY : STATE_LOCKED);
            this.rewards[i] = reward;
        }
        this.tasks = new BattlePassTask[]{
                new BattlePassTask((byte) 0, "Hoàn thành 10 nhiệm vụ ngày", 6, 10, 80, STATE_LOCKED),
                new BattlePassTask((byte) 1, "Tiêu diệt quái phù hợp cấp", 125, 200, 120, STATE_LOCKED),
                new BattlePassTask((byte) 2, "Tham gia hoạt động trong ngày", 1, 1, 150, STATE_READY),
                new BattlePassTask((byte) 3, "Nhận chuyên cần", 1, 1, 60, STATE_RECEIVED)
        };
    }

    private Item newPreviewItem(int id, int amount) {
        Item item = new Item(id, true);
        item.isLock = true;
        item.setAmount(amount);
        return item;
    }

    private Item readItem(Message msg) throws Exception {
        Item item = new Item();
        item.read(msg);
        return item;
    }

    private void initControls() {
        this.ticketScroll = new LangLa_ii((byte) 0, TICKET_X, TICKET_Y, this.width - TICKET_X - 14, TICKET_VIEW_H, TICKET_COL_W, this.rewards.length);
        this.freeSlots = new LangLa_ij[this.rewards.length];
        this.vipSlots = new LangLa_ij[this.rewards.length];
        this.freeButtons = new LangLa_dz[this.rewards.length];
        this.vipButtons = new LangLa_dz[this.rewards.length];
        for (int i = 0; i < this.rewards.length; ++i) {
            int x = i * TICKET_COL_W;
            this.freeSlots[i] = new LangLa_ij((byte) 1, x + 12, FREE_ROW_Y + 16, REWARD_SLOT_SIZE, REWARD_SLOT_SIZE, REWARD_SLOT_SIZE, 1, 1);
            this.vipSlots[i] = new LangLa_ij((byte) 1, x + 12, VIP_ROW_Y + 16, REWARD_SLOT_SIZE, REWARD_SLOT_SIZE, REWARD_SLOT_SIZE, 1, 1);
            this.freeButtons[i] = new LangLa_dz(x + 9, FREE_ROW_Y + REWARD_ROW_H - 28, 0, 0, "", this, 9000 + i, 7);
            this.freeButtons[i].setSize(REWARD_BUTTON_W, REWARD_BUTTON_H);
            this.vipButtons[i] = new LangLa_dz(x + 9, VIP_ROW_Y + REWARD_ROW_H - 28, 0, 0, "", this, 9100 + i, 7);
            this.vipButtons[i].setSize(REWARD_BUTTON_W, REWARD_BUTTON_H);
        }

        int taskY = 94;
        int taskH = this.height - taskY - 18;
        int taskRowH = 48;
        this.taskScroll = new LangLa_ii((byte) 1, 12, taskY, this.width - 24, taskH, taskRowH, this.tasks.length);
        this.taskButtons = new LangLa_dz[this.tasks.length];
        for (int i = 0; i < this.tasks.length; ++i) {
            int y = i * taskRowH;
            this.taskButtons[i] = new LangLa_dz(this.taskScroll.width - 66, y + 14, 0, 0, "", this, 9200 + i, 7);
            this.taskButtons[i].setSize(54, 19);
        }

        this.vipButton = new LangLa_dz(this.width - VIP_BUTTON_RIGHT_PAD, VIP_BUTTON_Y, 0, 0, this.vipActive ? "Đã mở" : "Mua", this, 9300, 7);
        this.vipButton.setSize(VIP_BUTTON_W, VIP_BUTTON_H);
    }

    public void b() {
        if (this.activeTab == TAB_TICKET) {
            this.ticketScroll.a();
            for (int i = 0; i < this.rewards.length; ++i) {
                this.freeSlots[i].a();
                this.vipSlots[i].a();
            }
        } else {
            this.taskScroll.a();
        }
    }

    public void a(Graphics g) {
        int oldX = g.a;
        int oldY = g.b;
        this.a(g, this.cx, this.cy, this.width, this.height, "", (byte) 20, this.r);
        this.a(g, this.cx, this.cy);
        this.paintWoodBody(g);
        this.paintTabs(g);
        if (this.activeTab == TAB_TICKET) {
            this.paintTicketTab(g);
        } else {
            this.paintHeader(g);
            this.paintTaskTab(g);
        }
        this.a(g, oldX, oldY);
    }

    private void paintWoodBody(Graphics g) {
        g.f(9583907);
        g.c(6, BODY_Y, this.width - 12, this.height - 62);
        this.a(g, 8, BODY_Y + 2, this.width - 16, this.height - 69, -22, 55, 56);
    }

    private void paintTabs(Graphics g) {
        mFont.b(mFont.e, g, "Battle Pass", this.width / 2, TITLE_Y, 2, -2560, -16777216);
        this.a(g, TAB_BAR_X, TAB_BAR_Y, this.width - TAB_BAR_X * 2, TAB_BAR_H, -11, 55, 56);
        this.paintTab(g, TAB_TICKET_X, TAB_TICKET_W, "Vé", this.activeTab == TAB_TICKET);
        this.paintTab(g, TAB_TASK_X, TAB_TASK_W, "Nhiệm vụ", this.activeTab == TAB_TASK);
    }

    private void paintTab(Graphics g, int x, int w, String text, boolean selected) {
        int tabW = w - TAB_INNER_PAD;
        int tabY = TAB_BAR_Y + 3;
        Binary2.a(g, selected ? 78 : 76, 0, x, tabY, tabW, TAB_H);
        int textX = x + tabW / 2 + (selected ? 1 : 0);
        int textY = TAB_BAR_Y + (selected ? 12 : 11);
        mFont.a((com.badlogic.gdx.graphics.g2d.BitmapFont) Graphics.a(23.0F), g, text, textX, textY, 2, selected ? -2560 : -1, 0);
    }

    private void paintHeader(Graphics g) {
        int x = HEADER_X;
        int y = HEADER_Y;
        int w = this.width - 30;
        this.a(g, x, y, w, HEADER_H, -11, 55, 56);
        mFont.b(mFont.d, g, "Cấp vé: " + this.passLevel, x + 10, y + 9, 0, -1, -16777216);
        int barX = x + HEADER_PROGRESS_X_OFFSET;
        int barY = y + HEADER_PROGRESS_Y_OFFSET;
        int barW = this.width - HEADER_PROGRESS_RIGHT_PAD;
        this.paintBar(g, barX, barY, barW, 8, this.passExp, this.passNeedExp, 4802889, 16763904);
        mFont.b(mFont.d, g, Utlis.numberFormat(this.passExp) + "/" + Utlis.numberFormat(this.passNeedExp), barX + barW / 2, barY - 4, 2, -1, -16777216);
        if (this.activeTab == TAB_TICKET) {
            this.vipButton.a = this.vipActive ? "Đã mở" : "Mua";
            this.vipButton.d = 7;
            this.vipButton.a(this.vipActive);
            this.paintButton(g, this.vipButton);
        }
    }

    private void paintTicketTab(Graphics g) {
        this.updateRewardButtons();
        this.paintTicketSideLabels(g);
        this.a(g, this.ticketScroll);
        this.paintTicketBackground(g);
        for (int i = 0; i < this.rewards.length; ++i) {
            if (!this.ticketScroll.b(i)) {
                continue;
            }
            int x = i * TICKET_COL_W + 3;
            BattlePassReward reward = this.rewards[i];
            this.paintRewardCell(g, x, FREE_ROW_Y, false, reward);
            this.paintRewardCell(g, x, VIP_ROW_Y, true, reward);
            LangLa_cz.b(g, this.freeSlots[i].cx, this.freeSlots[i].cy, reward.freeItem, this.freeSlots[i].k >= 0, "Vé thường");
            LangLa_cz.b(g, this.vipSlots[i].cx, this.vipSlots[i].cy, reward.vipItem, this.vipSlots[i].k >= 0, "Vé VIP");
            this.paintButton(g, this.freeButtons[i]);
            this.paintButton(g, this.vipButtons[i]);
            if (reward.freeState == STATE_LOCKED) {
                this.paintUnavailableOverlay(g, x, FREE_ROW_Y, TICKET_COL_W - 3, REWARD_ROW_H - 3);
            }
            if (!this.vipActive || reward.vipState == STATE_LOCKED) {
                this.paintUnavailableOverlay(g, x, VIP_ROW_Y, TICKET_COL_W - 3, REWARD_ROW_H - 3);
            }
        }
        this.b(g);
        this.paintPassFooter(g);
    }

    private void paintTicketSideLabels(Graphics g) {
        this.a(g, 12, this.ticketScroll.cy + FREE_ROW_Y, 38, REWARD_ROW_H - 3, -11, 55, 56);
        this.a(g, 12, this.ticketScroll.cy + VIP_ROW_Y, 38, REWARD_ROW_H - 3, -11, 55, 56);
        LangLa_fq.a(g, 3052, 0, TICKET_LABEL_X, this.ticketScroll.cy + FREE_ROW_Y + 32, 3);
        LangLa_fq.a(g, 3053, 0, TICKET_LABEL_X, this.ticketScroll.cy + VIP_ROW_Y + 32, 3);
    }

    private void paintTicketBackground(Graphics g) {
        int contentW = Math.max(this.ticketScroll.width, this.rewards.length * TICKET_COL_W);
        this.a(g, 0, FREE_ROW_Y - 3, contentW, REWARD_ROW_H + 3, -11, 55, 56);
        this.a(g, 0, VIP_ROW_Y - 3, contentW, REWARD_ROW_H + 3, -11, 55, 56);
    }

    private void paintRewardCell(Graphics g, int x, int y, boolean vip, BattlePassReward reward) {
        this.a(g, x, y, TICKET_COL_W - 3, REWARD_ROW_H - 3, -22, 55, 56);
        int color = this.passLevel >= reward.level ? -2560 : -1;
        if (vip && !this.vipActive) {
            color = -4473925;
        }
        mFont.a(mFont.b, g, "Lv " + reward.level, x + (TICKET_COL_W - 3) / 2, y + 8, 2, color, -10275328);
    }

    private void paintRewardState(Graphics g, int x, int y, int state, boolean enabled) {
        if (!enabled) {
            LangLa_fq.a(g, 409, 0, x, y + 14, 3);
        } else if (state == STATE_RECEIVED) {
            LangLa_fq.a(g, 11, 0, x, y + 14, 3);
        } else if (state == STATE_READY) {
            LangLa_fq.a(g, 713, 0, x, y + 14, 3);
        }
    }

    private void paintLockedOverlay(Graphics g, int x, int y, int w, int h) {
        g.d(44);
        g.f(-1);
        g.c(x + 1, y + 1, w - 2, h - 2);
        g.d(100);
        LangLa_fq.a(g, 409, 0, x + w / 2, y + h / 2, 3);
    }

    private void paintUnavailableOverlay(Graphics g, int x, int y, int w, int h) {
        g.d(42);
        g.f(-1);
        g.c(x + 1, y + 1, w - 2, h - 2);
        g.d(100);
    }

    private void paintPassFooter(Graphics g) {
//        this.a(g, FOOTER_X, FOOTER_Y +10, this.width - FOOTER_X * 2, FOOTER_H, -11, 55, 56);
        int x = FOOTER_X + FOOTER_BAR_X;
        int y = FOOTER_BAR_Y;
        int w = FOOTER_BAR_W;
        mFont.b(mFont.d, g, "Cấp vé: " + this.passLevel, FOOTER_X + 10, FOOTER_Y + 15, 0, -1, -16777216);
        this.paintBar(g, x -30 , y+2, w, FOOTER_BAR_H, this.passExp, this.passNeedExp, 4802889, 16763904);
        mFont.b(mFont.d, g, Utlis.numberFormat(this.passExp) + "/" + Utlis.numberFormat(this.passNeedExp), x + w / 2 -18, FOOTER_Y + 4, 2, -1, -16777216);
        if (this.activeTab == TAB_TICKET) {
            int priceX = this.vipButton.cx + 61;
            mFont.b(mFont.d, g, this.vipActive ? "VIP đã mở" : "Giá: " + Utlis.numberFormat(VIP_PRICE) + " vàng", priceX, FOOTER_Y + 4, 1, -2560, -16777216);
            this.vipButton.a = this.vipActive ? "Đã mở" : "Mua";
            this.vipButton.d = 7;
            this.vipButton.a(this.vipActive);
            this.paintButton(g, this.vipButton);
        }
    }

    private void paintTaskTab(Graphics g) {
        this.updateTaskButtons();
        this.a(g, this.taskScroll);
        for (int i = 0; i < this.tasks.length; ++i) {
            if (!this.taskScroll.b(i)) {
                continue;
            }
            int y = i * 48;
            BattlePassTask task = this.tasks[i];
            this.a(g, 2, y + 1, this.taskScroll.width - 4, 44, -22, 55, 56);
            mFont.b(mFont.e, g, task.name, 12, y + 8, 0, -1, -16777216);
            this.paintBar(g, 12, y + 27, 150, 7, task.progress, task.target, 4802889, 16763904);
            mFont.b(mFont.d, g, this.safeProgress(task.progress, task.target) + "/" + task.target, 170, y + 20, 0, -1, -16777216);
            mFont.b(mFont.d, g, "+" + task.exp + " exp vé", 238, y + 20, 2, -2560, -16777216);
            this.paintButton(g, this.taskButtons[i]);
        }
        this.b(g);
    }

    private void paintButton(Graphics g, LangLa_dz button) {
        int oldX = g.a;
        int oldY = g.b;
        g.a(button.cx, button.cy);
        button.a(g);
        this.a(g, oldX, oldY);
    }

    private void paintBar(Graphics g, int x, int y, int w, int h, int value, int max, int bgColor, int fillColor) {
        int progress = max <= 0 ? 0 : value * w / max;
        if (progress < 0) {
            progress = 0;
        }
        if (progress > w) {
            progress = w;
        }
        g.f(bgColor);
        g.c(x, y, w, h);
        g.f(fillColor);
        g.c(x + 1, y + 1, progress > 1 ? progress - 2 : progress, h - 2);
    }

    private void updateRewardButtons() {
        for (int i = 0; i < this.rewards.length; ++i) {
            this.updateRewardButton(this.freeButtons[i], this.rewards[i].freeState, true);
            this.updateRewardButton(this.vipButtons[i], this.rewards[i].vipState, this.vipActive);
        }
    }

    private void updateTaskButtons() {
        for (int i = 0; i < this.tasks.length; ++i) {
            this.updateButtonByState(this.taskButtons[i], this.tasks[i].state, true);
            this.taskButtons[i].setSize(54, 19);
        }
    }

    private void updateButtonByState(LangLa_dz button, int state, boolean enabledByPass) {
        if (!enabledByPass) {
            button.a = "Khóa";
            button.d = 7;
        } else if (state == STATE_RECEIVED) {
            button.a = "Đã nhận";
            button.d = 7;
        } else if (state == STATE_READY) {
            button.a = "Nhận";
            button.d = 9;
        } else {
            button.a = "Nhận";
            button.d = 7;
        }
        button.setSize(REWARD_BUTTON_W, REWARD_BUTTON_H);
    }

    private void updateRewardButton(LangLa_dz button, int state, boolean enabledByPass) {
        button.a = "Nhận";
        button.d = 7;
        button.setSize(REWARD_BUTTON_W, REWARD_BUTTON_H);
        button.a(!enabledByPass || state != STATE_READY);
    }

    private int safeProgress(int progress, int target) {
        if (progress < 0) {
            return 0;
        }
        if (target > 0 && progress > target) {
            return target;
        }
        return progress;
    }

    public Vector c() {
        Vector v = super.c();
        v.addElement(new LangLa_jz(8800, TAB_TICKET_X, TAB_BAR_Y + 3, TAB_TICKET_X + TAB_TICKET_W - TAB_INNER_PAD, TAB_BAR_Y + 3 + TAB_H, null, this));
        v.addElement(new LangLa_jz(8801, TAB_TASK_X, TAB_BAR_Y + 3, TAB_TASK_X + TAB_TASK_W - TAB_INNER_PAD, TAB_BAR_Y + 3 + TAB_H, null, this));
        if (this.activeTab == TAB_TICKET) {
            v.addElement(new LangLa_jz(9300, this.vipButton.cx, this.vipButton.cy, this.vipButton.cx + this.vipButton.width, this.vipButton.cy + this.vipButton.height, null, this));
            for (int i = 0; i < this.rewards.length; ++i) {
                if (!this.ticketScroll.b(i)) {
                    continue;
                }
                LangLa_ij free = this.freeSlots[i];
                LangLa_ij vip = this.vipSlots[i];
                LangLa_dz freeButton = this.freeButtons[i];
                LangLa_dz vipButton = this.vipButtons[i];
                v.addElement(new LangLa_jz(9400 + i, this.ticketScroll.cx + free.cx - this.ticketScroll.f, this.ticketScroll.cy + free.cy, this.ticketScroll.cx + free.cx + free.width - this.ticketScroll.f, this.ticketScroll.cy + free.cy + free.height, null, this));
                v.addElement(new LangLa_jz(9500 + i, this.ticketScroll.cx + vip.cx - this.ticketScroll.f, this.ticketScroll.cy + vip.cy, this.ticketScroll.cx + vip.cx + vip.width - this.ticketScroll.f, this.ticketScroll.cy + vip.cy + vip.height, null, this));
                v.addElement(new LangLa_jz(9000 + i, this.ticketScroll.cx + freeButton.cx - this.ticketScroll.f, this.ticketScroll.cy + freeButton.cy, this.ticketScroll.cx + freeButton.cx + freeButton.width - this.ticketScroll.f, this.ticketScroll.cy + freeButton.cy + freeButton.height, null, this));
                v.addElement(new LangLa_jz(9100 + i, this.ticketScroll.cx + vipButton.cx - this.ticketScroll.f, this.ticketScroll.cy + vipButton.cy, this.ticketScroll.cx + vipButton.cx + vipButton.width - this.ticketScroll.f, this.ticketScroll.cy + vipButton.cy + vipButton.height, null, this));
            }
            v.addElement(this.ticketScroll.a(9600, this));
        } else {
            for (int i = 0; i < this.tasks.length; ++i) {
                LangLa_dz button = this.taskButtons[i];
                v.addElement(new LangLa_jz(9200 + i, this.taskScroll.cx + button.cx, this.taskScroll.cy + button.cy - this.taskScroll.f, this.taskScroll.cx + button.cx + button.width, this.taskScroll.cy + button.cy + button.height - this.taskScroll.f, null, this));
            }
            v.addElement(this.taskScroll.a(9700, this));
        }
        return v;
    }

    public void a(LangLa_jz item, int x, int y) {
        super.a(item, x, y);
        if (item.b == 8800) {
            this.activeTab = TAB_TICKET;
            return;
        }
        if (item.b == 8801) {
            this.activeTab = TAB_TASK;
            return;
        }
        if (item.b >= 9000 && item.b < 9000 + this.rewards.length) {
            this.claimReward(false, item.b - 9000);
            return;
        }
        if (item.b >= 9100 && item.b < 9100 + this.rewards.length) {
            this.claimReward(true, item.b - 9100);
            return;
        }
        if (item.b >= 9200 && item.b < 9200 + this.tasks.length) {
            this.claimTask(item.b - 9200);
            return;
        }
        if (item.b >= 9400 && item.b < 9400 + this.rewards.length) {
            int index = item.b - 9400;
            LangLa_ij slot = this.freeSlots[index];
            this.cL = LangLa_cz.a(this, this.ticketScroll.cx + slot.cx + slot.q - this.ticketScroll.f, this.ticketScroll.cy + slot.cy, slot.q, this.rewards[index].freeItem);
            return;
        }
        if (item.b >= 9500 && item.b < 9500 + this.rewards.length) {
            int index = item.b - 9500;
            LangLa_ij slot = this.vipSlots[index];
            this.cL = LangLa_cz.a(this, this.ticketScroll.cx + slot.cx + slot.q - this.ticketScroll.f, this.ticketScroll.cy + slot.cy, slot.q, this.rewards[index].vipItem);
            return;
        }
        if (item.b == 9300) {
            this.buyVip();
        }
    }

    public void a(int id, Object obj) {
    }

    public void a(int id, Object obj, LangLa_cy source) {
    }

    private void claimReward(boolean vip, int index) {
        BattlePassReward reward = this.rewards[index];
        int state = vip ? reward.vipState : reward.freeState;
        if (vip && !this.vipActive) {
            DataCenter.gI().currentScreen.showMessage("Bạn chưa mở vé VIP", -65536);
            return;
        }
        if (state != STATE_READY) {
            DataCenter.gI().currentScreen.showMessage(state == STATE_RECEIVED ? "Đã nhận mốc này" : "Chưa đủ cấp vé", -65536);
            return;
        }
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(120);
            msg.writeBoolean(vip);
            msg.writeShort(reward.level);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    private void claimTask(int index) {
        BattlePassTask task = this.tasks[index];
        if (task.state != STATE_READY) {
            DataCenter.gI().currentScreen.showMessage(task.state == STATE_RECEIVED ? "Đã nhận nhiệm vụ này" : "Nhiệm vụ chưa hoàn thành", -65536);
            return;
        }
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(121);
            msg.writeByte(task.id);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    private void buyVip() {
        if (this.vipActive) {
            DataCenter.gI().currentScreen.showMessage("Vé VIP đã được mở", -1);
            return;
        }
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(122);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    private static class BattlePassReward {
        int level;
        Item freeItem;
        Item vipItem;
        int freeState;
        int vipState;
    }

    private static class BattlePassTask {
        byte id;
        String name;
        int progress;
        int target;
        int exp;
        int state;

        BattlePassTask() {
        }

        BattlePassTask(byte id, String name, int progress, int target, int exp, int state) {
            this.id = id;
            this.name = name;
            this.progress = progress;
            this.target = target;
            this.exp = exp;
            this.state = state;
        }
    }
}
