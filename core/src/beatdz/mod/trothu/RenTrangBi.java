package beatdz.mod.trothu;

import beatdz.LangLa_dz;
import com.tgame.model.Caption;
import beatdz.Char;
import beatdz.DataCenter;
import beatdz.Graphics;
import beatdz.Item;
import beatdz.LangLa_cg;
import beatdz.LangLa_cy;
import beatdz.LangLa_fq;
import beatdz.LangLa_ii;
import beatdz.LangLa_ij;
import beatdz.LangLa_jw;
import beatdz.LangLa_jz;
import beatdz.LangLa_kd;
import beatdz.MainScreen;
import beatdz.Message;
import beatdz.LangLa_ge;
import beatdz.Utlis;
import beatdz.mFont;
import beatdz.mod.other.TextSrc;
import java.util.Vector;

public class RenTrangBi
extends LangLa_ge {
    public Item[] itemTrangBi = new Item[1];
    public Item[] nguyenLieu = new Item[3];
    private LangLa_ij[] g = new LangLa_ij[2];
    private LangLa_dz buttonSubmit;
    private LangLa_cg selectType;
    private LangLa_cg selectLevel;
    private LangLa_cg selectHe;
    private LangLa_cg selectGioiTinh;
    public int c;
    public int d = 0;
    public LangLa_jw e;
    public boolean f;
    public int levelTB;
    public int typeTB;
    public int heTB;
    public int gioiTinhTB;
    public Item manhTB;
    public Item khoangThach;
    public Item nuocTinhLuyen;

    public RenTrangBi(MainScreen var1) {
        super(var1, new String[]{TextSrc.tabRen[0]});
        this.g[0] = new LangLa_ij((byte)1, 60, this.a_() + 20, 96, 64, 32, 1, 3);
        this.g[1] = new LangLa_ij((byte)1, 200, this.a_() + 20, 30, 30, 30, 1, 1);
        this.buttonSubmit = this.a(this.width - 70, this.height - 33, TextSrc.tb[2], this, 0, -8);
        this.a(this.buttonSubmit, 0);
        this.selectType = this.a(40, this.a_() + 55, 100, 5, new LangLa_kd(500, TextSrc.selectRen), this, 0);
        this.selectType.a(0);
        this.selectLevel = this.a(40, this.a_() + 80, 100, 5, new LangLa_kd(501, TextSrc.selectLevel), this, 0);
        this.selectLevel.a(0);
        this.selectHe = this.a(160, this.a_() + 55, 100, 5, new LangLa_kd(502, TextSrc.selectHe), this, 0);
        this.selectHe.a(0);
        this.selectGioiTinh = this.a(160, this.a_() + 80, 100, 5, new LangLa_kd(503, TextSrc.selectGioiTinh), this, 0);
        this.selectGioiTinh.a(0);
    }

    @Override
    public void a() {
        this.t = new Item[Char.gI().arrItemBag.length];
        int var1 = 0;
        for (int var2 = 0; var2 < Char.gI().arrItemBag.length; ++var2) {
            if (Char.gI().arrItemBag[var2] == null) continue;
            int _t = Char.gI().arrItemBag[var2].getItemTemplate().type;
            if (_t < 101 || _t > 103) continue;
            this.t[var1] = Char.gI().arrItemBag[var2];
            ++var1;
        }
    }

    public Item[] b(Item var1) {
        Item[] var2 = Char.gI().arrItemBag;
        if (this.k() == this.j()) {
            var1.TYPE_TEMP = 0;
        }
        return var2;
    }

    @Override
    public void b() {
        super.b();
        if (this.k() == 0) {
            if (this.selectType.v) {
                this.selectType.b(false);
            }
            if (this.selectLevel.v) {
                this.selectLevel.b(false);
            }
            if (this.selectHe.v) {
                this.selectHe.b(false);
            }
            if (this.selectGioiTinh.v) {
                this.selectGioiTinh.b(false);
            }
            for (int var10 = 0; var10 < this.g.length; ++var10) {
                this.g[var10].a();
            }
        } else {
            this.a();
            if (!this.selectType.v) {
                this.selectType.b(true);
            }
            if (!this.selectLevel.v) {
                this.selectLevel.b(true);
            }
            if (!this.selectHe.v) {
                this.selectHe.b(true);
            }
            if (!this.selectGioiTinh.v) {
                this.selectGioiTinh.b(true);
            }
        }
        if (this.e != null) {
            this.e.p();
            if (this.e.r()) {
                this.e = null;
            }
        }
        if (this.d > 0) {
            --this.d;
        }
    }

    @Override
    public void a(int var1, Object var2, LangLa_cy var3) {
        try {
            switch (var1) {
                case 500: {
                    this.typeTB = this.selectType.b.b;
                    break;
                }
                case 501: {
                    this.levelTB = this.selectLevel.b.b;
                    break;
                }
                case 502: {
                    this.heTB = this.selectHe.b.b;
                    break;
                }
                case 503: {
                    this.gioiTinhTB = this.selectGioiTinh.b.b;
                }
            }
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    @Override
    public void a(int var1) {
        super.a(var1);
        if (var1 != 0 && this.f) {
            this.f = false;
        }
    }

    @Override
    public void a(Graphics var1) {
        if (this.j() <= 0) {
            this.a(var1, (int)this.cx, (int)this.cy, (int)this.width, (int)this.height, Caption.q, (byte)2, this.r);
        } else {
            super.a(var1);
        }
        if (this.k() == 0) {
            int color = -10831436;
            mFont.b(mFont.d, var1, "\u0110\u1eb7t M\u1ea3nh trang b\u1ecb, Kho\u00e1ng th\u1ea1ch v\u00e0 N\u01b0\u1edbc tinh luy\u1ec7n v\u00e0o", 14, this.a_() + 9, 0, color, -16777216);
            color = -1;
            mFont.b(mFont.d, var1, "Nguy\u00ean li\u1ec7u: " + Utlis.numberFormat(100 + this.levelTB * 100) + " M\u1ea3nh, " + Utlis.numberFormat(15 + this.levelTB * 15) + " Kho\u00e1ng th\u1ea1ch, 1 N\u01b0\u1edbc tinh luy\u1ec7n", 30, this.a_() + 110, 0, color, -16777216);
            if (Char.gI().bacKhoa < this.getBacKhoa()) {
                color = -65536;
            }
            mFont.b(mFont.d, var1, "B\u1ea1c kh\u00f3a: " + Utlis.numberFormat(this.getBacKhoa()), 30, this.a_() + 125, 0, color, -16777216);
            color = -1;
            if (Char.gI().deTu == null || Char.gI().deTu.infoPhanThan == null || Char.gI().deTu.infoPhanThan.pointRen[this.typeTB] < this.getPointRen()) {
                color = -65536;
            }
            mFont.b(mFont.d, var1, "Y\u00eau c\u1ea7u \u0111i\u1ec3m r\u00e8n: " + Utlis.numberFormat(this.getPointRen()), 30, this.a_() + 140, 0, color, -16777216);
            this.a(var1, this.g[1].cx, this.g[0].cy, this.itemTrangBi[0], this.g[1].k >= 0, TextSrc.tb[0]);
            LangLa_fq.a(var1, 95, 0, 170 - DataCenter.gI().i / 3 % 3, this.a_() + 25, 20);
            if (this.d > 0 && this.d % 14 > 2) {
                mFont.b(mFont.d, var1, TextSrc.successRen, 14, this.a_() + 165, 0, -2560, -16777216);
            }
            this.a(var1, this.g[0]);
            int var2 = 0;
            for (int var3 = 0; var3 < this.g[0].i; ++var3) {
                for (int var4 = 0; var4 < this.g[0].r; ++var4) {
                    this.b(var1, var4 * this.g[0].h, var3 * this.g[0].h, this.nguyenLieu[var3 * this.g[0].r + var4], var2 == this.g[0].k, TextSrc.tb[1]);
                    ++var2;
                }
            }
            this.b(var1);
            if (this.e != null) {
                this.e.b(var1, this.g[1].cx + this.g[1].h / 2, this.g[1].cy + this.g[1].h / 2);
            }
        }
    }

    @Override
    public Vector c() {
        Vector var1 = super.c();
        if (this.k() == 0) {
            for (int var2 = 0; var2 < this.g.length; ++var2) {
                var1.addElement(this.g[var2].a(1001 + var2, this));
            }
        }
        return var1;
    }

    @Override
    public void a(LangLa_jz var1, int var2, int var3) {
        if (this.k() == this.j() && var1.b == 0) {
            this.d();
        }
        super.a(var1, var2, var3);
        if (this.k() == 0) {
            switch (var1.b) {
                case 0: {
                    try {
                        this.manhTB = null;
                        this.khoangThach = null;
                        this.nuocTinhLuyen = null;
                        block12: for (int i = 0; i < this.nguyenLieu.length; ++i) {
                            if (this.nguyenLieu[i] == null) continue;
                            switch (this.nguyenLieu[i].getItemTemplate().type) {
                                case 101: {
                                    this.manhTB = this.nguyenLieu[i];
                                    continue block12;
                                }
                                case 102: {
                                    this.khoangThach = this.nguyenLieu[i];
                                    continue block12;
                                }
                                case 103: {
                                    this.nuocTinhLuyen = this.nguyenLieu[i];
                                }
                            }
                        }
                        if (this.manhTB != null && this.khoangThach != null && this.nuocTinhLuyen != null) {
                            Message var6 = new Message((byte)-35);
                            var6.writeByte(100);
                            var6.writeShort(-1);
                            var6.writeByte(3);
                            for (var2 = 0; var2 < this.nguyenLieu.length; ++var2) {
                                if (this.nguyenLieu[var2] == null) continue;
                                var6.writeShort(this.nguyenLieu[var2].index);
                            }
                            var6.writeByte(this.typeTB);
                            var6.writeInt(this.levelTB + 1);
                            var6.writeByte(this.heTB + 1);
                            var6.writeByte(this.gioiTinhTB);
                            var6.send();
                            break;
                        }
                        DataCenter.gI().currentScreen.showMessage(TextSrc.noNL, -65536);
                        break;
                    }
                    catch (Exception var4) {
                        Utlis.println(var4);
                        return;
                    }
                }
                case 1001: {
                    this.c = 2;
                    this.a(var1.j, var1.j.k);
                    this.cL = RenTrangBi.a(var1, this, this.nguyenLieu[var1.j.k]);
                    break;
                }
                case 1002: {
                    this.c = 2;
                    this.a(var1.j, var1.j.k);
                    this.cL = RenTrangBi.a(var1, this, this.itemTrangBi[0]);
                }
            }
        }
    }

    private void a(LangLa_ii var1, int var2) {
        for (int var3 = 0; var3 < this.g.length; ++var3) {
            this.g[var3].k = -1;
        }
        var1.k = var2;
    }

    @Override
    public void d() {
        super.d();
        for (int var1 = 0; var1 < this.nguyenLieu.length; ++var1) {
            if (this.nguyenLieu[var1] == null) continue;
            int var10001 = this.nguyenLieu[var1].index;
            Char.gI().arrItemBag[var10001] = this.nguyenLieu[var1];
            this.nguyenLieu[var1] = null;
        }
    }

    private int getBacKhoa() {
        int num = 0;
        if (this.typeTB == 1) {
            num = 350000;
            num += 350000 * this.levelTB;
        } else if (this.typeTB % 2 != 0) {
            num = 250000;
            num += 250000 * this.levelTB;
        } else {
            num = 180000;
            num += 180000 * this.levelTB;
        }
        return num;
    }

    private int getPointRen() {
        switch (this.levelTB) {
            case 0: {
                return 0;
            }
            case 1: {
                return 500;
            }
            case 2: {
                return 1200;
            }
            case 3: {
                return 2500;
            }
            case 4: {
                return 7500;
            }
            case 5: {
                return 15000;
            }
        }
        return 999999;
    }
}
