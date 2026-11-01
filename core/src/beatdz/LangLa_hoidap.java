package beatdz;

import java.util.Vector;

public class LangLa_hoidap extends LangLa_fn {

    private static int lastTab;
    private int maxQuestion;
    private int answered;
    private int point;
    private boolean hasQuestion;
    private String question;
    private String[] answers;
    private int[] milestones;
    private boolean[] claimed;
    private int[] bac;
    private int[] bacKhoa;
    private int[] vang;
    private int[] vangKhoa;
    private Item[][] rewardItems;
    private byte[] topRanks;
    private String[] topNames;
    private int[] topPoints;
    private LangLa_dz[] answerButtons;
    private LangLa_dz[] claimButtons;
    private LangLa_ij[][] rewardSlots;
    private LangLa_ii rewardScroll;

    public LangLa_hoidap(MainScreen screen, Message msg) {
        super(screen);
        this.r = false;
        this.a(360, 255);
        LangLa_kd tabs = new LangLa_kd(-1000);
        tabs.c = new String[]{"Hỏi đáp", "Nhận thưởng", "BXH"};
        this.a(tabs, 205, 72, 3);
        this.readPanel(msg);
        this.initControls();
        if (lastTab < 0 || lastTab > 2) {
            lastTab = 0;
        }
        this.a(lastTab);
    }

    public void a(int tab) {
        lastTab = tab;
        super.a(tab);
    }

    private void readPanel(Message msg) {
        try {
            this.maxQuestion = msg.readInt();
            this.answered = msg.readInt();
            this.point = msg.readInt();
            this.hasQuestion = msg.readBoolean();
            this.question = "";
            this.answers = new String[]{"", "", "", ""};
            if (this.hasQuestion) {
                this.question = msg.readUTF();
                for (int i = 0; i < 4; i++) {
                    this.answers[i] = msg.readUTF();
                }
            }
            int rewardSize = msg.readByte();
            this.milestones = new int[rewardSize];
            this.claimed = new boolean[rewardSize];
            this.bac = new int[rewardSize];
            this.bacKhoa = new int[rewardSize];
            this.vang = new int[rewardSize];
            this.vangKhoa = new int[rewardSize];
            this.rewardItems = new Item[rewardSize][];
            for (int i = 0; i < rewardSize; i++) {
                this.milestones[i] = msg.readInt();
                this.claimed[i] = msg.readBoolean();
                this.bac[i] = msg.readInt();
                this.bacKhoa[i] = msg.readInt();
                this.vang[i] = msg.readInt();
                this.vangKhoa[i] = msg.readInt();
                int itemCount = msg.readByte();
                this.rewardItems[i] = new Item[itemCount];
                for (int j = 0; j < itemCount; j++) {
                    this.rewardItems[i][j] = this.newPreviewItem(msg.readShort(), msg.readInt());
                    this.rewardItems[i][j].strOptions = msg.readUTF();
                }
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
        } catch (Exception e) {
            Utlis.println(e);
            this.maxQuestion = 20;
            this.answered = 0;
            this.point = 0;
            this.hasQuestion = false;
            this.question = "";
            this.answers = new String[]{"", "", "", ""};
            this.milestones = new int[0];
            this.claimed = new boolean[0];
            this.rewardItems = new Item[0][0];
            this.topRanks = new byte[0];
            this.topNames = new String[0];
            this.topPoints = new int[0];
        }
    }

    private Item newPreviewItem(int id, int amount) {
        Item item = new Item(id, true);
        item.isLock = true;
        item.setAmount(amount);
        return item;
    }

    private void initControls() {
        this.answerButtons = new LangLa_dz[4];
        int x = 28;
        int y = this.a_() + 134;
        for (int i = 0; i < this.answerButtons.length; i++) {
            this.answerButtons[i] = new LangLa_dz(x + i % 2 * 155, y + i / 2 * 31, 0, 0, this.answers[i], this, 9000 + i, 7);
            this.answerButtons[i].setSize(140, 24);
        }
        this.claimButtons = new LangLa_dz[this.milestones.length];
        this.rewardSlots = new LangLa_ij[this.milestones.length][];
        int scrollX = 8;
        int scrollY = this.a_() + 30;
        int scrollH = this.height - scrollY - 42;
        int rowH = 49;
        this.rewardScroll = new LangLa_ii((byte) 1, scrollX, scrollY, this.width - scrollX * 2, scrollH, rowH, this.milestones.length);
        for (int row = 0; row < this.milestones.length; row++) {
            int rowY = row * rowH;
            this.claimButtons[row] = new LangLa_dz(this.rewardScroll.width - 76, rowY + 6, 0, 0, this.claimed[row] ? "Đã nhận" : "Nhận", this, 9100 + row, 7);
            this.claimButtons[row].setSize(62, 22);
            int itemCount = this.rewardItems[row] == null ? 0 : this.rewardItems[row].length;
            this.rewardSlots[row] = new LangLa_ij[itemCount];
            int maxItem = itemCount > 4 ? 4 : itemCount;
            int startX = this.rewardScroll.width / 2 - maxItem * 17;
            for (int i = 0; i < itemCount; i++) {
                this.rewardSlots[row][i] = new LangLa_ij((byte) 1, startX + i % 4 * 34, rowY + 7 + i / 4 * 26, 26, 26, 26, 1, 1);
            }
        }
    }

    public void b() {
        super.b();
        if (this.k() == 1) {
            this.rewardScroll.a();
            for (int row = 0; row < this.rewardSlots.length; row++) {
                for (int i = 0; i < this.rewardSlots[row].length; i++) {
                    this.rewardSlots[row][i].a();
                }
            }
        }
    }

    public void a(Graphics g) {
        super.a(g);
        if (this.k() == 0) {
            this.paintQuestionTab(g);
        } else if (this.k() == 1) {
            this.paintRewardTab(g);
        } else if (this.k() == 2) {
            this.paintTopTab(g);
        }
    }

    private void paintQuestionTab(Graphics g) {
        int y = this.a_() + 12;
        this.a(g, 14, y, this.width - 28, 52, -11, 55, 56);
        mFont.b(mFont.e, g, "Số câu: " + this.answered + "/" + this.maxQuestion, 28, y + 12, 0, -1, -16777216);
        mFont.b(mFont.e, g, "Điểm: " + Utlis.numberFormat(this.point), this.width - 28, y + 12, 1, -2560, -16777216);
        mFont.b(mFont.d, g, "Đã trả lời: " + this.answered + "/" + this.maxQuestion, 28, y + 32, 0, -1, -16777216);
        this.a(g, 14, y + 64, this.width - 28, 56, -11, 55, 56);
        mFont.b(mFont.d, g, this.hasQuestion ? this.question : "Hôm nay đã hết câu hỏi hoặc chưa có dữ liệu.", this.width / 2, y + 82, 2, -1, -16777216);
        if (this.hasQuestion) {
            for (int i = 0; i < this.answerButtons.length; i++) {
                LangLa_dz button = this.answerButtons[i];
                int oldX = g.a;
                int oldY = g.b;
                g.a(button.cx, button.cy);
                button.a(g);
                this.a(g, oldX, oldY);
            }
        }
    }

    private void paintRewardTab(Graphics g) {
        int y = this.a_() + 14;
        mFont.b(mFont.e, g, "Điểm hiện tại: " + Utlis.numberFormat(this.point), this.width / 2, y, 2, -2560, -16777216);
        this.a(g, this.rewardScroll);
        for (int row = 0; row < this.milestones.length; row++) {
            int rowY = row * 49 + 1;
            this.a(g, 2, rowY, this.rewardScroll.width - 4, 46, -11, 55, 56);
            mFont.b(mFont.d, g, "Mốc " + Utlis.numberFormat(this.milestones[row]), 14, rowY + 13, 0, -1, -16777216);
            for (int i = 0; i < this.rewardSlots[row].length; i++) {
                LangLa_ij slot = this.rewardSlots[row][i];
                LangLa_cz.a(g, slot.cx, slot.cy, this.rewardItems[row][i], slot.k >= 0, this.claimed[row] ? "Đã nhận" : "Quà hỏi đáp");
            }
            LangLa_dz button = this.claimButtons[row];
            button.a = this.claimed[row] ? "Đã nhận" : "Nhận";
            button.d = (short) (this.canReceiveReward(row) ? 9 : 7);
            int oldX = g.a;
            int oldY = g.b;
            g.a(button.cx, button.cy);
            button.a(g);
            this.a(g, oldX, oldY);
        }
        this.b(g);
    }

    private void paintTopTab(Graphics g) {
        int tableW = this.width - 48;
        int tableX = 24;
        int tableY = this.a_() + 32;
        int rowH = 18;
        this.a(g, tableX, tableY -25, tableW, 24, -11, 55, 56);
        this.a(g, tableX, tableY + 1, tableW, rowH * 10 + 2, -11, 55, 56);
        mFont.b(mFont.e, g, "Hạng", tableX + 32, tableY - 15, 2, -6488, -10275328);
        mFont.b(mFont.e, g, "Nhân vật", tableX + tableW / 2, tableY - 15, 2, -6488, -10275328);
        mFont.b(mFont.e, g, "Điểm", tableX + tableW - 46, tableY - 15, 2, -6488, -10275328);
        if (this.topNames.length == 0) {
            mFont.b(mFont.d, g, "Chưa có dữ liệu xếp hạng.", this.width / 2, tableY + 92, 2, -10831436, -16777216);
            return;
        }
        for (int i = 0; i < this.topNames.length && i < 10; i++) {
            int y = tableY + 32 + i * rowH;
            int color = i < 3 ? -2560 : -3604601;
            mFont.b(mFont.d, g, String.valueOf(this.topRanks[i]), tableX + 32, y - 15, 2, color, -16777216);
            mFont.b(mFont.d, g, this.topNames[i], tableX + tableW / 2, y - 15, 2, color, -16777216);
            mFont.b(mFont.d, g, Utlis.numberFormat(this.topPoints[i]), tableX + tableW - 46, y - 15, 2, color, -16777216);
        }
    }

    public Vector c() {
        Vector v = super.c();
        if (this.k() == 0 && this.hasQuestion) {
            for (int i = 0; i < this.answerButtons.length; i++) {
                LangLa_dz button = this.answerButtons[i];
                v.addElement(new LangLa_jz(9000 + i, button.cx, button.cy, button.cx + button.width, button.cy + button.height, null, this));
            }
        } else if (this.k() == 1) {
            for (int row = 0; row < this.claimButtons.length; row++) {
                LangLa_dz button = this.claimButtons[row];
                v.addElement(new LangLa_jz(9100 + row, this.rewardScroll.cx + button.cx, this.rewardScroll.cy + button.cy - this.rewardScroll.f, this.rewardScroll.cx + button.cx + button.width, this.rewardScroll.cy + button.cy + button.height - this.rewardScroll.f, null, this));
                for (int i = 0; i < this.rewardSlots[row].length; i++) {
                    LangLa_ij slot = this.rewardSlots[row][i];
                    v.addElement(new LangLa_jz(9200 + row * 10 + i, this.rewardScroll.cx + slot.cx, this.rewardScroll.cy + slot.cy - this.rewardScroll.f, this.rewardScroll.cx + slot.cx + slot.width, this.rewardScroll.cy + slot.cy + slot.height - this.rewardScroll.f, null, this));
                }
            }
            v.addElement(this.rewardScroll.a(9300, this));
        }
        return v;
    }

    private boolean canReceiveReward(int row) {
        return row >= 0 && row < this.milestones.length && !this.claimed[row] && this.point >= this.milestones[row];
    }

    public void a(LangLa_jz item, int x, int y) {
        super.a(item, x, y);
        if (item.b >= 9000 && item.b < 9004) {
            this.answer(item.b - 9000);
            return;
        }
        if (item.b >= 9100 && item.b < 9100 + this.milestones.length) {
            int index = item.b - 9100;
            if (this.canReceiveReward(index)) {
                this.claim(index);
            } else {
                DataCenter.gI().currentScreen.showMessage(this.claimed[index] ? "Đã nhận mốc này" : "Chưa đủ điểm", -65536);
            }
            return;
        }
        if (item.b >= 9200 && item.b < 9300) {
            int row = (item.b - 9200) / 10;
            int col = (item.b - 9200) % 10;
            if (row >= 0 && row < this.rewardItems.length && col >= 0 && col < this.rewardItems[row].length) {
                LangLa_ij slot = this.rewardSlots[row][col];
                this.cL = LangLa_cz.a(this, this.rewardScroll.cx + slot.cx + slot.q, this.rewardScroll.cy + slot.cy - this.rewardScroll.f, slot.q, this.rewardItems[row][col]);
            }
        }
    }

    private void answer(int option) {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(115);
            msg.writeByte(option);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    private void claim(int index) {
        try {
            Message msg = new Message((byte) 122);
            msg.writeByte(116);
            msg.writeByte(index);
            msg.send();
        } catch (Exception e) {
            Utlis.println(e);
        }
    }

    public void a(int id, Object obj) {
    }

    public void a(int id, Object obj, LangLa_cy source) {
    }
}
