package beatdz.mod.trothu;

import com.tgame.model.Caption;
import beatdz.Char;
import beatdz.Graphics;
import beatdz.LangLa_cx;
import beatdz.LangLa_cy;
import beatdz.LangLa_fo;
import beatdz.LangLa_jz;
import beatdz.LangLa_kc;
import beatdz.MainScreen;
import beatdz.Message;
import beatdz.mFont;
import java.util.Vector;

public class DelSkillVT
extends LangLa_fo {
    private LangLa_kc a;
    private int b;
    private Char ch;

    public DelSkillVT(MainScreen var1, int var2, Char ch) {
        this.b = var2;
        this.ch = ch;
        this.cF = (byte)2;
        this.p = var1;
        this.r = false;
        this.a(190, 137);
        this.a = new LangLa_kc(1000, 0);
        this.a(20, 45, Caption.tD[0], (LangLa_cx)this, this.a);
        this.a(20, 70, Caption.tD[1], (LangLa_cx)this, this.a);
        this.a(this.width / 2 - 25, this.height - 35, Caption.g, this, 1001, -8);
    }

    @Override
    public void a(int var1, Object var2, LangLa_cy var3) {
    }

    @Override
    public void a(int var1, Object var2) {
    }

    @Override
    public void a(Graphics var1) {
        this.a(var1, 0, 0, (int)this.width, (int)this.height, 80, 55, 56);
        mFont.b(mFont.d, var1, Caption.qh, 15, 30, 0, -1, -16777216);
    }

    @Override
    public void b() {
    }

    @Override
    public Vector c() {
        super.c();
        Vector<LangLa_jz> var1 = new Vector<LangLa_jz>();
        var1.addElement(new LangLa_jz(0, 0, 0, this.width, this.height, this.q, this));
        return var1;
    }

    @Override
    public void a(LangLa_jz var1, int var2, int var3) {
        switch (var1.b) {
            case 1001: {
                try {
                    Message var6 = new Message((byte)-119);
                    var6.writeByte(10);
                    var6.writeInt(this.ch.idEntity);
                    var6.writeByte(this.b);
                    var6.writeBoolean(this.a.b == 1);
                    var6.send();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                this.J();
                return;
            }
            case 1002: {
                this.J();
            }
        }
    }
}
