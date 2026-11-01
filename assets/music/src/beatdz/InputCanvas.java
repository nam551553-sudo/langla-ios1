package beatdz;

public class InputCanvas extends Canvas {

    public static long k = System.currentTimeMillis();
    private XYEntity l = new XYEntity();
    private boolean m;

    public void a(char var1) {
        try {
            k = System.currentTimeMillis();
            if (DataCenter.gI().currentScreen != null) {
                MainScreen var10000 = DataCenter.gI().currentScreen;
                char var2 = var1;
                MainScreen var4 = var10000;
                if (!var10000.cE.a(var2)) {
                    var4.cE.cO.a(var2);
                }
            }

        } catch (Exception var3) {
            Utlis.println(var3);
        }
    }

    public void a(int var1) {
        try {
            k = System.currentTimeMillis();
            if (DataCenter.gI().currentScreen != null) {
                MainScreen var10000 = DataCenter.gI().currentScreen;
                int var2 = var1;
                MainScreen var4 = var10000;
                if (!var10000.cE.l(var2)) {
                    var4.cE.cO.a(var2);
                }
            }

        } catch (Exception var3) {
            Utlis.println(var3);
        }
    }

    public void b(int var1) {
        try {
            k = System.currentTimeMillis();
            if (DataCenter.gI().currentScreen != null) {
                MainScreen var10000 = DataCenter.gI().currentScreen;
                int var2 = var1;
                MainScreen var4 = var10000;
                if (!var10000.cE.m(var2)) {
                    var4.cE.cO.b(var2);
                }
            }

        } catch (Exception var3) {
            Utlis.println(var3);
        }
    }

    public void update() {
        try {
            if (DataCenter.gI().currentScreen != null) {
                MainScreen var1 = DataCenter.gI().currentScreen;

                try {
                    LangLa_hd.a().b();
                    LangLa_jh.a().b();
                    LangLa_jj.a().b();
                    LangLa_jl.a().b();
                    var1.j();

                    for (int var3 = 0; var3 < var1.cD.size(); ++var3) {
                        LangLa_cx var2;
                        (var2 = (LangLa_cx) var1.cD.elementAt(var3)).M();
                        var2.N();
                        var2.P();
                    }

                    if (!(var1.cE instanceof LangLa_dl) && Binary2.a != null) {
                        Binary2.a.J();
                    }

                    return;
                } catch (Exception var4) {
                    Utlis.println(var4);
                }
            }

        } catch (Exception var5) {
            Utlis.println(var5);
        }
    }

    public int num ;

    public void paint(Graphics var1) {
        try {
            if (DataCenter.gI().currentScreen != null) {
                Graphics var2 = var1;
                MainScreen var10 = DataCenter.gI().currentScreen;

                try {
                    for (int var3 = 0; var3 < var10.cD.size(); ++var3) {
                        LangLa_cx var10000 = (LangLa_cx) var10.cD.elementAt(var3);
                        Graphics var5 = var2;
                        LangLa_cx var4 = var10000;
                        var10000.c(var2);
                        if (!LangLa_cx.cN) {
                            for (int var7 = 0; var7 < var4.cG.size(); ++var7) {
                                LangLa_cs var6;
                                if ((var6 = (LangLa_cs) var4.cG.elementAt(var7)).w == null && !(var6.s instanceof LangLa_dl)) {
                                    var6.c(var5);
                                }
                            }
                        }
                    }
//                    String s = "Bạn đang sử dụng phiên bản ClientLangLa dành riêng cho các server Làng Lá Lậu - By Beatdz. Chúc bạn chơi game vui vẻ.";
                  //  String s = "Bạn đang sử dụng phiên bản ClientLangLa dành riêng cho Học Viện Làng Lá - By Beatdz. Chúc bạn chơi game vui vẻ.";
//                    num += 2;
//                    mFont.a(var1, s, var10.width - num, var10.height - 8, 0, -1);
//                    if (num % (s.length() * 50) == 0) {
//                        num = 0;
//                    }

                    if (!(var10 instanceof GameSrc)) {
                        if (!(var10 instanceof LangLa_md)) {
                            LangLa_hd.a().a(var2);
                            LangLa_jh.a().a(var2);
                            LangLa_jj.a().a(var2);
                        }

                        return;
                    }

                    if (!GameSrc.gI().i()) {
                        return;
                    }

                    LangLa_hd.a().a(var2);
                    LangLa_jh.a().a(var2);
                    LangLa_jj.a().a(var2);
                    LangLa_jl.a().a(var2);
                } catch (Exception var8) {
                    Utlis.println(var8);
                }
            }

        } catch (Exception var9) {
            Utlis.println(var9);
        }
    }

    public void a(int var1, int var2) {
        try {
            k = System.currentTimeMillis();
            if (DataCenter.gI().currentScreen != null) {
                DataCenter.gI().currentScreen.cE.h(var1, var2);
            }

        } catch (Exception var4) {
            Utlis.println(var4);
        }
    }

    public void b(int var1, int var2) {
        try {
            k = System.currentTimeMillis();
            if (this.m) {
                this.c(this.l.cx, this.l.cy);
            }

            this.m = true;
            this.l.setXY(var1, var2);
            if (DataCenter.gI().currentScreen != null) {
                DataCenter.gI().currentScreen.cE.f(var1, var2);
            }

        } catch (Exception var4) {
            Utlis.println(var4);
        }
    }

    public void c(int var1, int var2) {
        try {
            k = System.currentTimeMillis();
            this.m = false;
            if (DataCenter.gI().currentScreen != null) {
                DataCenter.gI().currentScreen.d(var1, var2);
            }

        } catch (Exception var4) {
            Utlis.println(var4);
        }
    }
}
