package beatdz.mod.trothu;

import beatdz.LangLa_dz;
import com.tgame.model.Caption;
import beatdz.Char;
import beatdz.DataCenter;
import beatdz.GameSrc;
import beatdz.Graphics;
import beatdz.Item;
import beatdz.ItemOption;
import beatdz.LangLa_cl;
import beatdz.LangLa_cs;
import beatdz.LangLa_cx;
import beatdz.LangLa_cy;
import beatdz.LangLa_fq;
import beatdz.LangLa_gl;
import beatdz.LangLa_ii;
import beatdz.LangLa_ij;
import beatdz.LangLa_jz;
import beatdz.LangLa_kc;
import beatdz.LangLa_kd;
import beatdz.LangLa_lf;
import beatdz.MainScreen;
import beatdz.Message;
import beatdz.MobTemplate;
import beatdz.Skill;
import beatdz.SkillClan;
import beatdz.LangLa_ge;
import beatdz.LangLa_mk;
import beatdz.Utlis;
import beatdz.mFont;
import beatdz.mTextBox;
import beatdz.mod.other.TextSrc;
import beatdz.mod.trothu.DelSkillVT;
import beatdz.mod.trothu.UpSkillVT;
import java.util.Arrays;
import java.util.Vector;

public class TabTroThu
extends LangLa_ge {
    public Char _char;
    public LangLa_lf navBar1;
    private String[] C;
    private LangLa_ij[] buttonsItem;
    private LangLa_ij sachKiNang;
    private LangLa_ii tabInfo;
    private LangLa_ii tabIconSkills1;
    private LangLa_ii tabIconSkills2;
    private LangLa_ii tabInfoSkills;
    private LangLa_ii[] tabTiemNang;
    private LangLa_lf[] buttonSub;
    private LangLa_dz updateAction;
    private LangLa_dz submit_TN;
    private LangLa_dz cancel_TN;
    private LangLa_dz submit_Skill;
    private LangLa_dz select_Skill;
    public mTextBox[] textBoxes;
    private LangLa_kc buttonTrangThai;
    private LangLa_lf tabTT;
    public int[] optionTC = new int[18];
    public int[] optionPT = new int[17];
    private int I = 32;
    public int a;
    public int pointSucManh;
    public int pointChakra;
    public int pointMp;
    public int pointHp;
    public int pointTiemNang;
    public Skill[] skills;
    public SkillClan[] skillsViThu;
    public int pointSkill;
    public int idSelect;
    public boolean isMess;
    public byte numAction;
    private Item sach;
    public LangLa_lf navBar2;
    private LangLa_ii tabIconSkillVT;
    private LangLa_ii tabInfoSkillVT;
    private int index_img;
    private long timePainMob;
    private MobTemplate mob;
    private int index_sk = -1;
    private LangLa_dz btn_upVT;
    private LangLa_dz btn_delVT;

    public TabTroThu(MainScreen var1) {
        super(var1, TextSrc.title_chucNang);
        this.C = TextSrc.option_phanThan;
        this.a((int)this.width, this.height + 20);
        GameSrc.gI().aC = new LangLa_gl[0];
        GameSrc.gI().aB = new LangLa_gl[0];
        this.navBar1 = new LangLa_lf(new LangLa_kd(1001, this.C), 4, this.a_(), this.width - 8, 23, 57, this);
        this.navBar2 = new LangLa_lf(new LangLa_kd(1002, TextSrc.navBar2), 4, this.a_(), this.width - 8, 23, 57, this);
        this.a(0);
        this.setup();
    }

    private void setup() {
        int i;
        this.a(299, 240);
        this._char = Char.gI().deTu;
        this.skills = new Skill[0];
        this.skillsViThu = new SkillClan[6];
        int num1 = 25;
        int num2 = 50;
        int num3 = 50;
        this.buttonsItem = new LangLa_ij[19];
        for (i = 0; i < 10; ++i) {
            if (i % 2 == 0) {
                if (i > 0) {
                    num3 += this.I;
                }
                this.buttonsItem[i] = new LangLa_ij((byte)1, 25, num3, 30, 30, this.I, 1, 1);
                continue;
            }
            this.buttonsItem[i] = new LangLa_ij((byte)1, 240, num3, 30, 30, this.I, 1, 1);
        }
        for (i = 1; i < 7; ++i) {
            this.buttonsItem[9 + i] = new LangLa_ij((byte)1, 25 + this.I * i, num2 - 10 + this.I * 5, 30, 30, this.I, 1, 1);
        }
        this.buttonsItem[16] = new LangLa_ij((byte)1, num1 + this.I * 6, num2 + this.I * 3, this.I, this.I, this.I, 1, 1);
        // Mắt nằm thẳng trên Sách (cột num1 + I), Cánh thẳng trên Thời trang
        // (cột num1 + I*6) — một hàng phía trên (num2 + I*2).
        this.buttonsItem[17] = new LangLa_ij((byte)1, num1 + this.I, num2 + this.I * 2, this.I, this.I, this.I, 1, 1);
        this.buttonsItem[18] = new LangLa_ij((byte)1, num1 + this.I * 6, num2 + this.I * 2, this.I, this.I, this.I, 1, 1);
        this.sachKiNang = new LangLa_ij((byte)1, 40, 25 + this.I * 4, 30, 30, this.I, 1, 1);
        int n1 = 10;
        int n2 = 66;
        int xTab = 13;
        int yTab = 63;
        this.tabInfo = new LangLa_ii((byte)1, n1, n2, 200, 155, 12, TextSrc.info_pt.length);
        this.tabIconSkills1 = new LangLa_ii((byte)1, xTab, yTab, 40, 150, 30, 5);
        this.tabInfoSkills = new LangLa_ii((byte)1, xTab += 37, yTab, 180, 132, 25, 10);
        this.tabInfoSkillVT = new LangLa_ii((byte)1, xTab, yTab, 240, 125, 25, 10);
        this.tabIconSkills2 = new LangLa_ii((byte)1, xTab += 200, yTab, 80, 150, 30, 5);
        this.submit_Skill = this.a(this.width / 2 + 50, this.height - 43, Caption.cP, this, 3001, 7);
        this.submit_Skill.setSize(40, 20);
        this.select_Skill = this.a(this.submit_Skill.cx - 43, this.height - 43, Caption.bk, this, 3000, 7);
        this.select_Skill.setSize(40, 20);
        this.tabTiemNang = new LangLa_ii[4];
        this.tabTiemNang[0] = new LangLa_ii((byte)1, n1, n2 + 108, 40, 12, 24, 4);
        this.tabTiemNang[1] = new LangLa_ii((byte)1, n1, n2 + 22, this.width - 8 - 30, 130, 13, Caption.rd.length);
        this.tabTiemNang[2] = new LangLa_ii((byte)1, n1, n2 + 22, this.width - 8 - 30, 130, 13, this.optionTC.length);
        this.tabTiemNang[3] = new LangLa_ii((byte)1, n1, n2 + 22, this.width - 8 - 30, 130, 13, Caption.rf.length);
        this.buttonSub = new LangLa_lf[2];
        this.buttonSub[0] = new LangLa_lf(new LangLa_kd(2000, Caption.sh), 13, 55, 274, 1, 90, this, false);
        this.buttonSub[1] = new LangLa_lf(new LangLa_kd(2011, Caption.sD), 13, 55, 275, 1, 88, this, false);
        this.textBoxes = new mTextBox[4];
        this.tabIconSkillVT = new LangLa_ii((byte)1, 12, 58, 40, 180, 27, 6);
        Vector<LangLa_cs> var10 = new Vector<LangLa_cs>();
        DataCenter.gI();
        int xBt = this.width - 65;
        DataCenter.gI();
        int yBT = this.height - 40;
        DataCenter.gI();
        LangLa_dz var8 = this.a(xBt, yBT, Caption.cP, this, 2010, 7);
        var8.setSize(40, 20);
        this.submit_TN = var8;
        var10.addElement(this.submit_TN);
        var8 = this.a(this.submit_TN.cx - 45, this.submit_TN.cy, Caption.cQ, this, 2009, 7);
        var8.setSize(40, 20);
        var10.addElement(var8);
        this.cancel_TN = var8;
        var10.addElement(this.cancel_TN);
        this.textBoxes[0] = this.a(90, this.a_() + 55, 90, "", this, 2);
        var10.addElement(this.textBoxes[0]);
        if (this._char.idClass != 3 && this._char.idClass != 4) {
            var8 = this.a(190, this.a_() + 55 + 3, "", this, 2001, 58);
            var10.addElement(var8);
            var8 = this.a(215, this.a_() + 55 + 3, "", this, 2002, 57);
            var10.addElement(var8);
            var8 = this.a(240, this.a_() + 55 + 3, "", this, 2012, 244);
            var10.addElement(var8);
        }
        this.textBoxes[1] = this.a(90, this.a_() + 55 + 25, 90, "", this, 2);
        var10.addElement(this.textBoxes[1]);
        var8 = this.a(190, this.a_() + 55 + 25 + 3, "", this, 2003, 58);
        var10.addElement(var8);
        var8 = this.a(215, this.a_() + 55 + 25 + 3, "", this, 2004, 57);
        var10.addElement(var8);
        var8 = this.a(240, this.a_() + 55 + 25 + 3, "", this, 2014, 244);
        var10.addElement(var8);
        this.textBoxes[2] = this.a(90, this.a_() + 55 + 50, 90, "", this, 2);
        var10.addElement(this.textBoxes[2]);
        var8 = this.a(190, this.a_() + 55 + 50 + 3, "", this, 2005, 58);
        var10.addElement(var8);
        var8 = this.a(215, this.a_() + 55 + 50 + 3, "", this, 2006, 57);
        var10.addElement(var8);
        var8 = this.a(240, this.a_() + 55 + 50 + 3, "", this, 2016, 244);
        var10.addElement(var8);
        this.textBoxes[3] = this.a(90, this.a_() + 55 + 75, 90, "", this, 2);
        var10.addElement(this.textBoxes[3]);
        var8 = this.a(190, this.a_() + 55 + 75 + 3, "", this, 2007, 58);
        var10.addElement(var8);
        var8 = this.a(215, this.a_() + 55 + 75 + 3, "", this, 2008, 57);
        var10.addElement(var8);
        var8 = this.a(240, this.a_() + 55 + 75 + 3, "", this, 2018, 244);
        var10.addElement(var8);
        this.buttonSub[0].a(var10, 0);
        this.buttonSub[0].a(2);
        this.updateAction = this.a(this.width / 2 - 20, this.height - 40, "Thay \u0111\u1ed5i", this, 3002, 7);
        this.updateAction.setSize(50, 18);
        this.updateAction.b(true);
        this.updateAction.a(true);
        this.tabTT = new LangLa_lf(new LangLa_kd(2055, new String[]{"", ""}), 1, 1, 1, 1, 1, this, false);
        this.buttonTrangThai = new LangLa_kc(1005, this._char.infoPhanThan.type);
        LangLa_cl nghi = this.a(30, 95, TextSrc.trangThai[0] + ": " + TextSrc.detail_trangThai[0], (LangLa_cx)this, this.buttonTrangThai);
        LangLa_cl theo = this.a(30, 120, TextSrc.trangThai[1] + ": " + TextSrc.detail_trangThai[1], (LangLa_cx)this, this.buttonTrangThai);
        LangLa_cl luyen = this.a(30, 145, TextSrc.trangThai[2] + ": " + TextSrc.detail_trangThai[2], (LangLa_cx)this, this.buttonTrangThai);
        LangLa_cl baove = this.a(30, 170, TextSrc.trangThai[3] + ": " + TextSrc.detail_trangThai[3], (LangLa_cx)this, this.buttonTrangThai);
        this.tabTT.a(nghi, 0);
        this.tabTT.a(theo, 0);
        this.tabTT.a(luyen, 0);
        this.tabTT.a(baove, 0);
        this.tabTT.a(1);
        if (this._char.sachChienDau > 0) {
            this.sach = new Item(435);
            this.sach.strOptions = "207,0,-1;208,0,-1";
            this.sach.a(this._char.sachChienDau);
            if (this._char.sachChienDau > 17) {
                StringBuilder options = new StringBuilder();
                this.sach.strOptions = options.append(this.sach.strOptions).append(";311,300,-1").toString();
                options = new StringBuilder();
                this.sach.strOptions = options.append(this.sach.strOptions).append(";336,").append(this._char.lvPhanThan).append(",").append(this._char.lvPhanThanMax).toString();
                options = new StringBuilder();
                this.sach.strOptions = options.append(this.sach.strOptions).append(";337,").append(this._char.expPhanThan).append(",-1").toString();
            } else if (this._char.sachChienDau > 16) {
                StringBuilder options = new StringBuilder();
                this.sach.strOptions = options.append(this.sach.strOptions).append(";311,150,-1").toString();
            }
        }
        if (this._char.arrItemBody[10] != null && this._char.arrItemBody[10].id >= 476 && this._char.arrItemBody[10].id <= 484) {
            this.mob = DataCenter.gI().MobTemplate[this._char.arrItemBody[10].id - 225];
        }
        this.btn_upVT = this.a(65, this.height - 45, "N\u00e2ng c\u1ea5p", this, 3004, 7);
        this.btn_upVT.setSize(50, 20);
        this.btn_delVT = this.a(130, this.height - 45, "X\u00f3a b\u1ecf", this, 3005, 7);
        this.btn_delVT.setSize(50, 20);
    }

    @Override
    public void a(Graphics graphics) {
        try {
            super.a(graphics);
            block1 : switch (this.k()) {
                case 0: {
                    this.navBar1.a(graphics);
                    if (this.navBar1.b.b != 3) {
                        this.submit_Skill.b(true);
                        this.select_Skill.b(true);
                    }
                    switch (this.navBar1.b.b) {
                        case 0: {
                            this.paintTB(graphics);
                            break block1;
                        }
                        case 1: {
                            this.paintInfo(graphics);
                            break block1;
                        }
                        case 2: {
                            this.paintTiemNang(graphics);
                            break block1;
                        }
                        case 3: {
                            this.paintSkill(graphics);
                            break block1;
                        }
                        case 4: {
                            this.a(graphics, this.cx + 4, this.cy + this.a_() + 22);
                            this.a(graphics, 3, 0, this.width - 15, this.G() - this.a_() + 5, 61, 55, 56);
                            this.a(graphics, 10, 5, 270, 35, -20, 84, 5, 1, 1);
                            this.a(graphics, 10, 40, 270, 135, -22, 84, 5, 1, 1);
                            DataCenter.gI();
                            mFont.a(mFont.e, graphics, TextSrc.chuY_tt, this.width / 2, 23, 2, -2560, 2, 1, 3);
                            break block1;
                        }
                    }
                    this.a(graphics, this.cx + 4, this.cy + this.a_() + 22);
                    this.a(graphics, 3, 0, this.width - 15, this.G() - this.a_() + 5, 61, 55, 56);
                    break;
                }
                case 1: {
                    // Tab "Mở rộng" (Vĩ thú) đã bị bỏ khỏi title_chucNang, nên
                    // k()==1 giờ là Túi — super.a() đã render grid rồi, không
                    // paint gì thêm. Giữ Vĩ thú code dưới để có thể bật lại.
                    if (this.k() == this.j()) break block1;
                    this.navBar2.a(graphics);
                    if (!this.btn_upVT.v) {
                        this.btn_upVT.b(true);
                    }
                    if (!this.btn_delVT.v) {
                        this.btn_delVT.b(true);
                    }
                    if (!this.submit_Skill.v) {
                        this.submit_Skill.b(true);
                    }
                    if (!this.select_Skill.v) {
                        this.select_Skill.b(true);
                    }
                    switch (this.navBar2.b.b) {
                        case 0: {
                            this.paintSkillViThu(graphics);
                        }
                    }
                }
            }
        }
        catch (Exception var8) {
            Utlis.println(var8);
        }
    }

    @Override
    public void a(LangLa_jz var1, int var2, int var3) {
        if (this.k() == this.j() && var1.b == 0) {
            this.d();
        }
        super.a(var1, var2, var3);
        block4 : switch (this.k()) {
            case 0: {
                switch (var1.b) {
                    case 1001: {
                        if (var1.j.k < 0) break block4;
                        this.navBar1.a(var1.j.k);
                        this.tabTT.a(1);
                        this.updateAction.b(true);
                        switch (var1.j.k) {
                            case 2: {
                                this.buttonSub[0].a(0);
                                try {
                                    Message m = new Message((byte)-119);
                                    m.writeByte(1);
                                    m.writeInt(Math.abs(this._char.idEntity));
                                    m.writeByte(0);
                                    m.send();
                                    m.close();
                                }
                                catch (Exception var6) {
                                    Utlis.println(var6);
                                }
                                break;
                            }
                            case 3: {
                                this.a = -1;
                                this.messkill(-1, (byte)0);
                                break;
                            }
                            case 4: {
                                this.tabTT.a(0);
                                this.updateAction.b(false);
                            }
                        }
                        if (var1.j.k != 2) {
                            this.buttonSub[0].a(1);
                        }
                        return;
                    }
                    case 1030: {
                        this.a(var1.j, var1.j.k);
                        this.cL = TabTroThu.a(var1, this, this.sach);
                        if (this.v() || this.cL == null || !(this.cL instanceof LangLa_mk)) break block4;
                        ((LangLa_mk)this.cL).a();
                        break;
                    }
                    case 1200: {
                        break;
                    }
                    case 1201: {
                        this.a = var1.j.k;
                        break;
                    }
                    case 1202: {
                        this.a = var1.j.k + 5;
                        break;
                    }
                    case 2000: {
                        switch (var1.j.k) {
                            case 0: 
                            case 1: 
                            case 2: {
                                this.buttonSub[0].a(var1.j.k);
                                this.A();
                            }
                        }
                        break;
                    }
                    case 2001: {
                        if (this.textBoxes[0].e() - 1 < this.textBoxes[0].n) break block4;
                        this.textBoxes[0].a(this.textBoxes[0].e() - 1);
                        ++this.pointTiemNang;
                        break;
                    }
                    case 2002: {
                        if (this.pointTiemNang <= 0) break block4;
                        this.textBoxes[0].a(this.textBoxes[0].e() + 1);
                        --this.pointTiemNang;
                        break;
                    }
                    case 2003: {
                        if (this.textBoxes[1].e() - 1 < this.textBoxes[1].n) break block4;
                        this.textBoxes[1].a(this.textBoxes[1].e() - 1);
                        ++this.pointTiemNang;
                        break;
                    }
                    case 2004: {
                        if (this.pointTiemNang <= 0) break block4;
                        this.textBoxes[1].a(this.textBoxes[1].e() + 1);
                        --this.pointTiemNang;
                        break;
                    }
                    case 2005: {
                        if (this.textBoxes[2].e() - 1 < this.textBoxes[2].n) break block4;
                        this.textBoxes[2].a(this.textBoxes[2].e() - 1);
                        ++this.pointTiemNang;
                        break;
                    }
                    case 2006: {
                        if (this.pointTiemNang <= 0) break block4;
                        this.textBoxes[2].a(this.textBoxes[2].e() + 1);
                        --this.pointTiemNang;
                        break;
                    }
                    case 2007: {
                        if (this.textBoxes[3].e() - 1 < this.textBoxes[3].n) break block4;
                        this.textBoxes[3].a(this.textBoxes[3].e() - 1);
                        ++this.pointTiemNang;
                        break;
                    }
                    case 2008: {
                        if (this.pointTiemNang <= 0) break block4;
                        this.textBoxes[3].a(this.textBoxes[3].e() + 1);
                        --this.pointTiemNang;
                        break;
                    }
                    case 2009: {
                        this.pointTiemNang += this.textBoxes[0].e() + this.textBoxes[1].e() + this.textBoxes[2].e() + this.textBoxes[3].e() - (this.pointSucManh + this.pointChakra + this.pointMp + this.pointHp);
                        this.textBoxes[0].a("" + this.pointSucManh);
                        this.textBoxes[1].a("" + this.pointChakra);
                        this.textBoxes[2].a("" + this.pointMp);
                        this.textBoxes[3].a("" + this.pointHp);
                        break;
                    }
                    case 2010: {
                        try {
                            Message var5 = new Message((byte)-119);
                            var5.writeByte(1);
                            var5.writeInt(Math.abs(this._char.idEntity));
                            var5.writeByte(1);
                            var5.writeShort(this.textBoxes[0].e());
                            var5.writeShort(this.textBoxes[1].e());
                            var5.writeShort(this.textBoxes[2].e());
                            var5.writeShort(this.textBoxes[3].e());
                            var5.send();
                        }
                        catch (Exception var14) {
                            Utlis.println(var14);
                        }
                        break;
                    }
                    case 2012: {
                        if (this.pointTiemNang > 10) {
                            this.textBoxes[0].a(this.textBoxes[0].e() + 10);
                            this.pointTiemNang -= 10;
                            break;
                        }
                        if (this.pointTiemNang <= 0) break block4;
                        this.textBoxes[0].a(this.textBoxes[0].e() + this.pointTiemNang);
                        this.pointTiemNang = 0;
                        break;
                    }
                    case 2014: {
                        if (this.pointTiemNang > 10) {
                            this.textBoxes[1].a(this.textBoxes[1].e() + 10);
                            this.pointTiemNang -= 10;
                            break;
                        }
                        if (this.pointTiemNang <= 0) break block4;
                        this.textBoxes[1].a(this.textBoxes[1].e() + this.pointTiemNang);
                        this.pointTiemNang = 0;
                        break;
                    }
                    case 2016: {
                        if (this.pointTiemNang > 10) {
                            this.textBoxes[2].a(this.textBoxes[2].e() + 10);
                            this.pointTiemNang -= 10;
                            break;
                        }
                        if (this.pointTiemNang <= 0) break block4;
                        this.textBoxes[2].a(this.textBoxes[2].e() + this.pointTiemNang);
                        this.pointTiemNang = 0;
                        break;
                    }
                    case 2018: {
                        if (this.pointTiemNang > 10) {
                            this.textBoxes[3].a(this.textBoxes[3].e() + 10);
                            this.pointTiemNang -= 10;
                            break;
                        }
                        if (this.pointTiemNang <= 0) break block4;
                        this.textBoxes[3].a(this.textBoxes[3].e() + this.pointTiemNang);
                        this.pointTiemNang = 0;
                        break;
                    }
                    case 3000: {
                        this.selectSkill();
                        break;
                    }
                    case 3001: {
                        this.upSkill();
                        break;
                    }
                    case 3002: {
                        try {
                            Message var5 = new Message((byte)-119);
                            var5.writeByte(4);
                            var5.writeInt(Math.abs(this._char.idEntity));
                            var5.writeByte(this.numAction);
                            var5.send();
                        }
                        catch (Exception var14) {
                            Utlis.println(var14);
                        }
                        break;
                    }
                    default: {
                        if (var1.j.k == 0 && var1.b - 1010 >= 0 && var1.b - 1010 < 19) {
                            this.a(var1.j, var1.j.k);
                            this.cL = TabTroThu.a(var1, this, this._char.arrItemBody[var1.b - 1010]);
                            if (!this.v() && this.cL != null && this.cL instanceof LangLa_mk) {
                                ((LangLa_mk)this.cL).a();
                            }
                        }
                        if (this.navBar1.b.b != 4) break block4;
                        this.tabTT.a(0);
                        this.updateAction.b(false);
                        break;
                    }
                }
                break;
            }
            case 1: {
                // Mở rộng đã bỏ → k==1 là Túi. Chạy reset như default cũ, bỏ
                // qua toàn bộ Vĩ thú click handling (giữ dưới để bật lại sau).
                if (this.k() == this.j()) {
                    this.buttonSub[0].a(1);
                    this.tabTT.a(1);
                    this.updateAction.b(true);
                    break;
                }
                try {
                    Message m = new Message((byte)-119);
                    m.writeByte(8);
                    m.writeInt(Math.abs(this._char.idEntity));
                    m.send();
                    m.close();
                }
                catch (Exception var6) {
                    Utlis.println(var6);
                }
                switch (var1.b) {
                    case 1002: {
                        if (var1.j.k < 0) break;
                        this.index_sk = -1;
                        this.navBar2.a(var1.j.k);
                        break;
                    }
                    case 1201: {
                        this.index_sk = var1.j.k;
                        break;
                    }
                    case 3004: {
                        this.p.a(new UpSkillVT(this.p, this.index_sk, this._char));
                        break;
                    }
                    case 3005: {
                        this.p.a(new DelSkillVT(this.p, this.index_sk, this._char));
                    }
                }
                this.buttonSub[0].a(1);
                this.tabTT.a(1);
                this.updateAction.b(true);
                break;
            }
            default: {
                this.buttonSub[0].a(1);
                this.tabTT.a(1);
                this.updateAction.b(true);
            }
        }
    }

    @Override
    public Vector c() {
        Vector var1 = super.c();
        switch (this.k()) {
            case 0: {
                var1.addElement(this.navBar1.c());
                switch (this.navBar1.b.b) {
                    case 0: {
                        for (int var4 = 0; var4 < this.buttonsItem.length; ++var4) {
                            LangLa_jz langLaJz = this.buttonsItem[var4].a(1010 + var4, this);
                            var1.addElement(langLaJz);
                        }
                        var1.addElement(this.sachKiNang.a(1030, this));
                        break;
                    }
                    case 1: {
                        var1.addElement(this.tabInfo.a(1200, this));
                        break;
                    }
                    case 2: {
                        var1.addElement(this.tabTiemNang[this.buttonSub[0].b.b].a(2000, this));
                        var1.addElement(this.buttonSub[0].c());
                        break;
                    }
                    case 3: {
                        var1.addElement(this.tabIconSkills1.a(1201, this));
                        var1.addElement(this.tabIconSkills2.a(1202, this));
                        var1.addElement(this.tabInfoSkills.a(1203, this));
                    }
                }
                break;
            }
            case 1: {
                // Mở rộng đã bỏ → k==1 là Túi. super.c() đã add region grid
                // cho Túi, TabTroThu không add gì thêm. Giữ Vĩ thú region để
                // bật lại nếu cần.
                if (this.k() == this.j()) break;
                var1.addElement(this.navBar2.c());
                switch (this.navBar2.b.b) {
                    case 0: {
                        var1.addElement(this.tabIconSkillVT.a(1201, this));
                    }
                }
            }
        }
        return var1;
    }

    @Override
    public void a() {
    }

    public int l() {
        return this.navBar1.b.b;
    }

    @Override
    public void b() {
        try {
            super.b();
            if (this.k() != 0) {
                this.a();
                return;
            }
            if (this.tabInfo != null) {
                this.tabInfo.a();
            }
            if (this.tabInfoSkills != null) {
                this.tabInfoSkills.a();
            }
            if (this.tabTiemNang[this.buttonSub[0].b.b] != null) {
                this.tabTiemNang[this.buttonSub[0].b.b].a();
            }
            for (int var3 = 0; var3 < this.textBoxes.length; ++var3) {
                this.textBoxes[var3].a(true);
            }
            if (this.a >= 0) {
                this.updateButtonSkill();
            }
            if (this.numAction != this.buttonTrangThai.b) {
                this.numAction = (byte)this.buttonTrangThai.b;
            }
            if (this.numAction != this._char.infoPhanThan.type) {
                this.updateAction.a(false);
            } else {
                this.updateAction.a(true);
            }
            this.navBar1.b();
            if (this.k() != 1) {
                this.btn_upVT.b(true);
                this.btn_delVT.b(true);
            }
        }
        catch (Exception var2) {
            Utlis.println(var2);
        }
    }

    private void A() {
        this.buttonSub[0].a(this.buttonSub[0].b.b);
        switch (this.buttonSub[0].b.b) {
            case 0: {
                return;
            }
            case 1: {
                this.tabTiemNang[0].d();
                return;
            }
            case 2: {
                this.tabTiemNang[1].d();
                return;
            }
            case 3: {
                this.tabTiemNang[2].d();
            }
        }
    }

    private void paintTB(Graphics graphics) {
        this.a(graphics, this.cx + 4, this.cy + this.a_() + 22);
        this.a(graphics, 3, 0, this.width - 15, this.G() - this.a_() + 5, 61, 55, 56);
        LangLa_fq.a(graphics, 16, 0, 145, 175, 33);
        int dX = 18;
        int dY = 10;
        for (int i = 0; i < 19; ++i) {
            Item item = this._char.arrItemBody[i];
            if (i < 10) {
                if (i % 2 == 0) {
                    dX = 18;
                    if (i > 0) {
                        dY += this.I;
                    }
                } else {
                    dX = 242;
                }
            } else {
                dY = 10 + this.I * 4;
                if (i == 10) {
                    dX = 18;
                }
                dX += this.I;
                if (i == 16) {
                    dX = 18 + this.I * 6;
                    dY = 10 + this.I * 3;
                } else if (i == 17) {
                    // Mắt — thẳng trên Sách (18 + I, 10 + I*3), nâng 1 hàng.
                    dX = 18 + this.I;
                    dY = 10 + this.I * 2;
                } else if (i == 18) {
                    // Cánh — thẳng trên Thời trang (18 + I*6, 10 + I*3).
                    dX = 18 + this.I * 6;
                    dY = 10 + this.I * 2;
                }
            }
            this.a(graphics, dX, dY, item, this.buttonsItem[i].k == 0, TextSrc.option_tb[i]);
        }
        this.a(graphics, 18 + this.I, 10 + this.I * 3, this.sach, this.sachKiNang.k == 0, "S\u00e1ch");
        DataCenter.gI();
        this._char.a(graphics, 145, 108, this._char.a(this._char.arrItemBody, false), DataCenter.gI().i / 8 % 2, this._char.m, false);
        this._char.b(graphics, 145, 45);
    }

    private void paintInfo(Graphics graphics) {
        this.a(graphics, this.cx + 4, this.cy + this.a_() + 22);
        this.a(graphics, 3, 0, this.width - 15, this.G() - this.a_() + 5, 61, 55, 56);
        this.a(graphics, this.tabInfo);
        int nu = 0;
        for (int i = 0; i < TextSrc.info_pt.length; ++i) {
            int var4 = i * this.tabInfo.h;
            if (!this.tabInfo.c(var4)) continue;
            int nY = nu + this.tabInfo.h / 2;
            switch (i) {
                case 0: {
                    int j;
                    long exp = this._char.exp;
                    for (j = 0; j < DataCenter.gI().exps.length && exp >= DataCenter.gI().exps[j]; exp -= DataCenter.gI().exps[j], ++j) {
                    }
                    float var6 = (float)exp * 100.0f / (float)DataCenter.gI().exps[j];
                    mFont.a(mFont.d, graphics, TextSrc.info_pt[0] + ": " + j + " + " + String.format("%.2f", Float.valueOf(var6)) + "%", 10, nY, 0, -1, 0);
                    break;
                }
                case 1: {
                    mFont.a(mFont.d, graphics, TextSrc.info_pt[1] + ": " + DataCenter.gI().DataNameChar[this._char.idClass].name + " (" + DataCenter.gI().DataNameClass[this._char.idhe].name + ")", 10, nY, 0, -1, 0);
                    break;
                }
                case 2: {
                    mFont.a(mFont.d, graphics, TextSrc.info_pt[2] + ": " + this._char.hp + "/" + this._char.hpFull, 10, nY, 0, -1, 0);
                    break;
                }
                case 3: {
                    mFont.a(mFont.d, graphics, TextSrc.info_pt[3] + ": " + this._char.mp + "/" + this._char.mpFull, 10, nY, 0, -1, 0);
                    break;
                }
                case 4: {
                    mFont.a(mFont.d, graphics, TextSrc.info_pt[4] + ": " + Utlis.numberFormat(this._char.taiPhu), 10, nY, 0, -1, 0);
                    break;
                }
                case 5: {
                    mFont.a(mFont.d, graphics, TextSrc.info_pt[5] + ": " + Utlis.k(this._char.infoPhanThan.timeRenLuyen), 10, nY, 0, -1, 0);
                    break;
                }
                case 6: {
                    mFont.a(mFont.d, graphics, TextSrc.info_pt[6] + ": " + this._char.infoPhanThan.perBonusExp + "%", 10, nY, 0, -1, 0);
                    break;
                }
                default: {
                    mFont.a(mFont.d, graphics, TextSrc.info_pt[i] + ": " + Utlis.numberFormat(this._char.infoPhanThan.pointRen[i - 7]), 10, nY, 0, -1, 0);
                }
            }
            nu += 14;
        }
        this.b(graphics);
        this.tabInfo.b(graphics, 55, -13);
    }

    private void paintTiemNang(Graphics graphics) {
        this.a(graphics, this.cx + 4, this.cy + this.a_() + 22);
        this.a(graphics, 3, 0, this.width - 15, this.G() - this.a_() + 5, 61, 55, 56);
        DataCenter.gI();
        this.buttonSub[0].a(graphics);
        switch (this.buttonSub[0].b.b) {
            case 0: {
                int numX = 20;
                int numY = 35;
                String text = Caption.rc[1];
                mFont.a(mFont.d, graphics, text, numX += 6, numY += 10, 0, -1, 0);
                text = Caption.rc[2];
                mFont.a(mFont.d, graphics, text, numX, numY += 24, 0, -1, 0);
                text = Caption.rc[3];
                mFont.a(mFont.d, graphics, text, numX, numY += 25, 0, -1, 0);
                text = Caption.rc[4];
                mFont.a(mFont.d, graphics, text, numX, numY += 26, 0, -1, 0);
                if (this.v() && this._char.idClass > 0) {
                    text = Caption.dd;
                    DataCenter.gI();
                    mFont.a(mFont.b, graphics, text, numX, numY += 21, 0, -1, -10275328);
                    graphics.f(-1);
                    graphics.a(numX + 6, numY + 4, numX + 6 + mFont.a(mFont.b, Caption.dd), numY + 4);
                }
                mFont.a(mFont.d, graphics, Caption.rc[0] + " " + this.pointTiemNang + " " + Caption.dc, numX, numY += 23, 0, -1, 0);
                break;
            }
            case 1: {
                this.a(graphics, this.tabTiemNang[1]);
                int var4 = 5;
                for (int i = 0; i < Caption.rd.length; ++i) {
                    if (i > 0) {
                        var4 += this.tabTiemNang[1].h;
                    }
                    if (!this.tabTiemNang[1].c(var4)) continue;
                    if (i == 0) {
                        mFont.a(mFont.d, graphics, Caption.rd[i] + ": " + this.optionPT[i] * 90 / 100 + " - " + this.optionPT[i], 10, var4, 0, -1, 0);
                        continue;
                    }
                    mFont.a(mFont.d, graphics, Caption.rd[i] + ": " + this.optionPT[i] + (i != 5 && i != 16 ? "" : "%"), 10, var4, 0, -1, 0);
                }
                this.b(graphics);
                this.tabTiemNang[1].b(graphics, -17, -13);
                break;
            }
            case 2: {
                this.a(graphics, this.tabTiemNang[2]);
                int var5 = 5;
                for (int var3 = 0; var3 < this.tabTiemNang[2].i; ++var3) {
                    if (var3 > 0) {
                        var5 += this.tabTiemNang[2].h;
                    }
                    if (!this.tabTiemNang[2].c(var5)) continue;
                    if (var3 == 10) {
                        if (this._char.idhe > 0) {
                            mFont.a(mFont.d, graphics, Caption.re[var3] + Caption.ro[this._char.L() - 1] + ": " + this.optionTC[var3], 10, var5, 0, -1, 0);
                            continue;
                        }
                        mFont.a(mFont.d, graphics, Caption.kl + ": " + this.optionTC[var3], 10, var5, 0, -1, 0);
                        continue;
                    }
                    if (var3 == 11) {
                        if (this._char.idhe > 0) {
                            mFont.a(mFont.d, graphics, Caption.re[var3] + Caption.ro[this._char.M() - 1] + ": " + this.optionTC[var3], 10, var5, 0, -1, 0);
                            continue;
                        }
                        mFont.a(mFont.d, graphics, Caption.km + ": " + this.optionTC[var3], 10, var5, 0, -1, 0);
                        continue;
                    }
                    mFont.a(mFont.d, graphics, Caption.re[var3] + ": " + this.optionTC[var3] + (var3 != 8 && var3 != 9 ? "" : "%"), 10, var5, 0, -1, 0);
                }
                this.b(graphics);
                this.tabTiemNang[2].b(graphics, -17, -13);
            }
        }
    }

    private void paintSkill(Graphics graphics) {
        this.a(graphics, this.cx + 4, this.cy + this.a_() + 22);
        this.a(graphics, 2, 2, 40, 170, -20, 55, 56);
        this.a(graphics, 248, 2, 40, 170, -20, 55, 56);
        this.a(graphics, 45, 2, 200, 170, -23, 55, 56);
        if (this.skills.length > 0) {
            int numY;
            int i;
            DataCenter.gI();
            int var2 = 15;
            int var3 = 40;
            mFont.c(mFont.d, graphics, Caption.rc[0] + " " + this.pointSkill + " " + Caption.dc, 50, this.tabInfoSkills.height + 30, 0, -6488, 0);
            this.a(graphics, this.tabIconSkills1);
            int numX = 1;
            for (i = 0; i < 5; ++i) {
                numY = i * this.tabIconSkills1.h;
                if (this.tabIconSkills1.c(numY)) {
                    LangLa_fq.a(graphics, this.skills[i].getSkillTemplate().idIcon, 0, numX, numY + 1, 20);
                }
                if (i != this.a) continue;
                graphics.f(-1);
// paint_BorderItem not in src
            }
            this.a(graphics, this.tabIconSkills2);
            numX = 10;
            for (i = 0; i < 5; ++i) {
                numY = i * this.tabIconSkills2.h;
                if (this.tabIconSkills2.c(numY)) {
                    if (i + 5 >= this.skills.length) {
                        LangLa_fq.a(graphics, 460, 0, numX, numY + 1, 20);
                    } else {
                        LangLa_fq.a(graphics, this.skills[i + 5].getSkillTemplate().idIcon, 0, numX, numY + 1, 20);
                    }
                }
                if (i + 5 != this.a) continue;
                graphics.f(-1);
// paint_BorderItem not in src
            }
            this.a(graphics, this.tabInfoSkills);
            if (this.a >= 0 && this.a < this.skills.length) {
                Skill skill = this.skills[this.a];
                Skill nextSkill = TabTroThu.getNextSkill(skill.idTemplate, skill.level + 1);
                int dX = 10;
                int dY = 10;
                mFont.a(mFont.d, graphics, skill.getSkillTemplate().name, dX, dY, 0, -16663468, 0);
                mFont.a(mFont.d, graphics, Caption.rH[4] + skill.getSkillTemplate().levelMax, dX, dY += 13, 0, -1, 0);
                dY += 13;
                if (skill.getSkillTemplate().detail != null && skill.getSkillTemplate().detail.length() > 0) {
                    mFont.a(mFont.d, graphics, skill.getSkillTemplate().detail, dX, dY, 0, -1, 0);
                    dY += 13;
                }
                if (skill.getSkillTemplate().type >= 5) {
                    mFont.a(mFont.d, graphics, Caption.cV, dX, dY, 0, -7812062, 0);
                    dY += 13;
                } else if (skill.d()) {
                    mFont.a(mFont.d, graphics, Caption.lk, dX, dY, 0, -7812062, 0);
                    dY += 13;
                }
                if (nextSkill != null) {
                    if (nextSkill.levelNeed <= this._char.level()) {
                        mFont.a(mFont.d, graphics, Caption.fy + nextSkill.levelNeed, dX, dY, 0, -1, 0);
                    } else {
                        mFont.a(mFont.d, graphics, Caption.fy + nextSkill.levelNeed, dX, dY, 0, -65536, 0);
                    }
                    dY += 13;
                }
                if (skill.level == 0) {
                    mFont.a(mFont.d, graphics, Caption.cW + "1" + Caption.cX, dX, dY, 0, -65536, 0);
                    mFont.a(mFont.d, graphics, TextSrc.hoc, dX, dY += 13, 0, -1, 0);
                } else if (skill.level == skill.getSkillTemplate().levelMax) {
                    mFont.a(mFont.d, graphics, Caption.cY, dX, dY, 0, -2560, 0);
                    this.paintDetailSkill(graphics, skill, dX, dY += 13);
                } else {
                    mFont.a(mFont.d, graphics, Caption.cZ + skill.level, dX, dY, 0, -2560, 0);
                    dY += 13;
                    dY = this.paintDetailSkill(graphics, skill, dX, dY);
                    if (nextSkill != null) {
                        mFont.a(mFont.d, graphics, Caption.da + nextSkill.level, dX, dY, 0, -65536, 0);
                        this.paintDetailSkill(graphics, nextSkill, dX, dY += 13);
                    }
                }
                if (this.a >= 0) {
                    this.submit_Skill.b(false);
                    this.select_Skill.b(false);
                } else {
                    this.submit_Skill.b(true);
                    this.select_Skill.b(true);
                }
            } else {
                this.submit_Skill.b(true);
                this.select_Skill.b(true);
                DataCenter.gI();
                mFont.a(mFont.e, graphics, "Click v\u00e0o k\u1ef9 n\u0103ng \u0111\u1ec3 xem th\u00f4ng tin", this.tabInfoSkills.width / 2 + 10, this.tabInfoSkills.height / 2, 2, -2560, 2, 1, 3);
            }
        }
        this.b(graphics);
    }

    private void paintSkillViThu(Graphics graphics) {
        this.a(graphics, this.cx + 4, this.cy + this.a_() + 22);
        this.a(graphics, 2, 2, 40, 170, -20, 55, 56);
        this.a(graphics, 45, 2, 240, 170, -23, 55, 56);
        if (this.skillsViThu.length > 0) {
            DataCenter.gI();
            if (this.timePainMob < System.currentTimeMillis()) {
                ++this.index_img;
                if (this.index_img >= 2) {
                    this.index_img = 0;
                }
                this.timePainMob = System.currentTimeMillis() + 250L;
            }
            this.a(graphics, this.tabIconSkillVT);
            int numX = 1;
            for (int i = 0; i < 6; ++i) {
                int numY = i * this.tabIconSkillVT.h;
                if (this.tabIconSkillVT.c(numY)) {
                    if (this.skillsViThu[i] == null) {
                        LangLa_fq.a(graphics, 460, 0, numX, numY, 20);
                    } else {
                        LangLa_fq.a(graphics, this.skillsViThu[i].idIcon, 0, numX, numY, 20);
                    }
                }
                if (i != this.index_sk) continue;
                graphics.f(-1);
// paint_BorderItem not in src
            }
            this.a(graphics, this.tabInfoSkillVT);
            if (this.mob != null && this.index_sk == -1) {
                LangLa_fq.b(graphics, this.mob.b[this.index_img].a[0].a, Utlis.a(this.mob.b[this.index_img].a[0].d), 125, this.a_() + 100 + this.mob.b[this.index_img].a[0].c, 33);
                this.btn_upVT.b(true);
                this.btn_delVT.b(true);
            }
            if (this.index_sk != -1 && this.skillsViThu[this.index_sk] != null) {
                mFont.c(mFont.d, graphics, this.skillsViThu[this.index_sk].name, 15, 15, 0, -16663468, 0);
                mFont.c(mFont.d, graphics, Caption.cV, 15, 35, 0, -7812062, 0);
                mFont.c(mFont.d, graphics, Caption.cW + (this.skillsViThu[this.index_sk].levelNeed + 1), 15, 55, 0, -2560, 0);
                ItemOption[] options = this.skillsViThu[this.index_sk].b();
                int var2 = options.length;
                int nY = 80;
                for (int i = 0; i < var2; ++i) {
                    String inf = options[i].a(0);
                    if (inf == null || inf.length() <= 0) continue;
                    mFont.c(mFont.d, graphics, inf, 15, nY, 0, -1, 0);
                    nY += 20;
                }
                if (this.skillsViThu[this.index_sk] != null) {
                    if (this.btn_upVT.v) {
                        this.btn_upVT.b(false);
                    }
                    if (this.btn_delVT.v) {
                        this.btn_delVT.b(false);
                    }
                }
            }
        }
        this.b(graphics);
    }

    private static void a(Vector var0, SkillClan var1) {
        ItemOption[] var5 = var1.b();
        int var2 = var5.length;
        for (int var3 = 0; var3 < var2; ++var3) {
            String var4 = var5[var3].a(0);
            if (var4 == null || var4.length() <= 0) continue;
            var0.addElement(var4);
        }
    }

    private int paintDetailSkill(Graphics graphics, Skill skill, int dX, int dY) {
        if (skill.idTemplate >= 15 && skill.idTemplate <= 17) {
            mFont.a(mFont.d, graphics, Caption.cT + (skill.rangeNgang + this._char.aU), dX, dY, 0, -1, 0);
            dY += 13;
        }
        if (skill.getSkillTemplate().type < 5) {
            mFont.a(mFont.d, graphics, Caption.rH[7] + skill.mpUse, dX, dY, 0, -1, 0);
            mFont.a(mFont.d, graphics, Caption.rH[8] + (float)skill.coolDown / 1000.0f + Caption.cU, dX, dY += 13, 0, -1, 0);
            dY += 13;
            if (!skill.d()) {
                mFont.a(mFont.d, graphics, Caption.rH[9] + (skill.rangeNgang + this._char.aU), dX, dY, 0, -1, 0);
                mFont.a(mFont.d, graphics, Caption.rH[10] + skill.maxTarget, dX, dY += 13, 0, -1, 0);
                dY += 13;
            }
        } else if (skill.idTemplate == 29) {
            dY += 13;
        }
        ItemOption[] var6 = skill.g();
        int var3 = var6.length;
        for (int i = 0; i < var3; ++i) {
            String var5 = var6[i].a(this._char.idClass);
            if (var5 == null || var5.length() <= 0) continue;
            mFont.a(mFont.d, graphics, var5, dX, dY, 0, -1, 0);
            dY += 13;
        }
        return dY;
    }

    private void a(LangLa_ii var1, int var2) {
        if (this.buttonsItem != null) {
            for (int var4 = 0; var4 < this.buttonsItem.length; ++var4) {
                if (this.buttonsItem[var4] == null) continue;
                this.buttonsItem[var4].k = -1;
            }
        }
        if (this.sachKiNang != null) {
            this.sachKiNang.k = -1;
        }
        var1.k = var2;
    }

    private boolean v() {
        return this._char.name.equals(Char.gI().name);
    }

    @Override
    public void a(int var1, Object var2, LangLa_cy var3) {
        super.a(var1, var2, var3);
        switch (var1) {
            default: 
        }
    }

    @Override
    public void a(int var1) {
        super.a(var1);
        if (this.navBar1 != null && this.k() == 0) {
            this.navBar1.a(this.navBar1.b.b);
        }
    }

    @Override
    public void d() {
        super.d();
    }

    private static Skill getNextSkill(int var0, int var1) {
        for (int var2 = 0; var2 < DataCenter.gI().Skill.length; ++var2) {
            if (DataCenter.gI().Skill[var2].idTemplate != var0 || DataCenter.gI().Skill[var2].level != var1) continue;
            return DataCenter.gI().Skill[var2];
        }
        return null;
    }

    public void upSkill() {
        this.messkill(this.skills[this.a].idTemplate, (byte)1);
    }

    public void selectSkill() {
        this.messkill(this.skills[this.a].idTemplate, (byte)2);
    }

    private void messkill(int idSkill, byte type) {
        try {
            Message var1 = new Message((byte)-119);
            var1.writeByte(2);
            var1.writeInt(Math.abs(this._char.idEntity));
            var1.writeByte(type);
            if (type > 0) {
                var1.writeShort(idSkill);
            }
            var1.send();
        }
        catch (Exception var2) {
            Utlis.println(var2);
        }
    }

    private void updateButtonSkill() {
        if (this._char.idClass != 0 && this.v()) {
            this.select_Skill.b(false);
            this.submit_Skill.b(false);
            if (this.a >= 0 && this.a < this.skills.length) {
                Skill var1 = this.skills[this.a];
                if (var1 == null) {
                    return;
                }
                if (var1.level != 0 && var1.c()) {
                    this.select_Skill.a(false);
                    if (this.idSelect == var1.id) {
                        this.select_Skill.a(true);
                    } else {
                        this.select_Skill.a(false);
                    }
                } else {
                    this.select_Skill.a(true);
                }
                if (this.pointSkill <= 0 || var1.level >= var1.getSkillTemplate().levelMax || TabTroThu.getNextSkill((int)var1.idTemplate, (int)(var1.level + 1)).levelNeed > this._char.level()) {
                    this.submit_Skill.a(true);
                    return;
                }
                this.submit_Skill.a(false);
            } else {
                this.select_Skill.a(true);
                this.submit_Skill.a(true);
            }
        } else {
            this.select_Skill.b(true);
            this.submit_Skill.b(true);
        }
    }

    public int m() {
        return this.k();
    }

    public int n() {
        return this.navBar1.b.b;
    }

    public void readSkills(Message m) {
        try {
            this.pointSkill = m.readShort();
            this.idSelect = m.readShort();
            this.skills = new Skill[m.readShort()];
            for (int var3 = 0; var3 < this.skills.length; ++var3) {
                Skill var5;
                short var4 = m.readShort();
                this.skills[var3] = var5 = DataCenter.gI().Skill[var4].cloneSkill();
            }
            Arrays.sort(this.skills, Skill.a);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
