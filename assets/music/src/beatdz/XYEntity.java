package beatdz;

import java.util.Comparator;

public class XYEntity {

    public static Comparator da = new LangLa_fj();
    public static Comparator db = new LangLa_fk();
    public short cx;
    public short cy;

    public static XYEntity create(int var0, int var1) {
        XYEntity var2;
        (var2 = new XYEntity()).setXY(var0, var1);
        return var2;
    }

    public void setXY(int var1, int var2) {
        this.cx = (short) var1;
        this.cy = (short) var2;
    }

    public int getRe() {
        return Utlis.getRange(this, Char.gI());
    }
    
    public String xySTR()
    {
        return cx+", "+cy;
    }
}
