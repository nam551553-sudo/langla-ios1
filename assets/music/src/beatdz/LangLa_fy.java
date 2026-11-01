package beatdz;import beatdz.*;

import beatdz.DataCenter;

public class LangLa_fy implements Runnable {

    boolean a;

    DataCenter b;

    public LangLa_fy(DataCenter var1, boolean var2) {
        this.b = var1;
        this.a = var2;
    }

    public void run() {
        mFont.a();
        this.b.readArrDataGame(this.a);
        LangLa_fq.c();
        LangLa_fq.a(false);
    }
}
