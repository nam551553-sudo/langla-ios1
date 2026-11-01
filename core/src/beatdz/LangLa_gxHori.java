package beatdz;

import java.util.Vector;

/**
 * Created by khiem on 10/18/2025.
 *
 * @author Monkey
 */
public class LangLa_gxHori extends LangLa_cz  {
    public LangLa_ii a;
    public LangLa_kd b;
    private LangLa_gw e;
    private Vector[] f;
    private LangLa_cx g;
    private short h = 307;
    private int i;
    private String[][] j;
    public boolean[] c;
    public boolean[] d;

    public LangLa_gxHori(LangLa_kd var1, int var2, int var3, int var4, int var5, int var6, int var7, LangLa_cx var8) {
        boolean var10 = false;
        byte var16 = 60;  // ← Chiều rộng mỗi tab (thay vì 33)
        byte var15 = 0;
        byte var13 = 1;
        LangLa_kd var12 = var1;
        LangLa_gxHori var11 = this;

        this.setXY(var13, var4*2-4);
        DataCenter.gI();
        this.setSize(var15, var6-18);
        this.i = var2;
        this.g = var8;
        this.b = var1;

        // Tính chiều rộng cần thiết cho tất cả các tab
        int totalWidth = var1.c.length * var16;

        if (totalWidth > var2 - 20) {
            // Nếu các tab rộng hơn khung, dùng scroll
            var7 = var2 - 20;
        } else {
            // Nếu đủ chỗ, không cần scroll
            var7 = totalWidth;
        }

        DataCenter.gI();
        // ĐỔI SANG SCROLL NGANG
        this.a = new LangLa_ii(
                (byte)0,        // ← n = 0: SCROLL NGANG
                10, 10,         // x, y
                var7,  // width
                40,             // ← height (chiều cao cố định cho các tab)
                var16,          // ← h = 65 (CHIỀU RỘNG mỗi tab)
                var1.c.length   // số lượng tab
        );

        this.e = new LangLa_gw(0, 0, 0, 16, (byte)0, var1.c.length);
        this.f = new Vector[var1.c.length];
        this.j = new String[var1.c.length][];
        this.c = new boolean[var1.c.length];
        this.d = new boolean[var1.c.length];

        for(var3 = 0; var3 < var11.f.length; ++var3) {
            var11.f[var3] = new Vector();
            String[] var14;
            if ((var14 = var12.c[var3].trim().split(" ")).length >= 3 && !var12.c[var3].contains("\n")) {
                var11.j[var3] = new String[2];
                StringBuilder var10000;
                String[] var10002;
                if (var14.length >= 4) {
                    var11.j[var3][0] = var14[0] + " " + var14[1];
                    var11.j[var3][1] = "";

                    for(var5 = 2; var5 < var14.length; ++var5) {
                        var10000 = new StringBuilder();
                        var10002 = var11.j[var3];
                        var10002[1] = var10000.append(var10002[1]).append(var14[var5]).append(" ").toString();
                    }

                    var11.j[var3][1] = var11.j[var3][1].trim();
                } else {
                    var11.j[var3][0] = var14[0];
                    var11.j[var3][1] = "";

                    for(var5 = 1; var5 < var14.length; ++var5) {
                        var10000 = new StringBuilder();
                        var10002 = var11.j[var3];
                        var10002[1] = var10000.append(var10002[1]).append(var14[var5]).append(" ").toString();
                    }

                    var11.j[var3][1] = var11.j[var3][1].trim();
                }
            } else {
                var11.j[var3] = mFont.c(mFont.e, var12.c[var3], 70);
            }
        }

    }

    public void a() {
        this.a.a();
        this.e.a();
    }

    public LangLa_jz b() {
        return this.a.a(this.b.a, 0, 0, this.g);
    }

    public int c() {
        return this.cx + this.width;
    }

    public void a(int var1) {
        if (!this.d[var1]) {
            this.b.b = var1;

            for(var1 = 0; var1 < this.f.length; ++var1) {
                for(int var2 = 0; var2 < this.f[var1].size(); ++var2) {
                    this.g.cG.removeElement(this.f[var1].elementAt(var2));
                }
            }

            for(var1 = 0; var1 < this.f[this.b.b].size(); ++var1) {
                this.g.cG.addElement(this.f[this.b.b].elementAt(var1));
            }

        }
    }

    public void a(Graphics var1, int var2) {
        DataCenter.gI();
        int var3 = var1.a;  // Lưu clip x
        int var4 = var1.b;  // Lưu clip y

        this.a(var1, this.g.cx + this.cx, this.g.cy + this.cy);

        // Vẽ nền
        this.a(var1, this.width + 1, 6, this.i - this.width - 8, this.height - 12, var2, 55, 56);

        // Kiểm tra có cần scroll không
        if (!this.e.a && this.b.c.length * 60 <= this.a.width) {
            // Không cần scroll
            this.a.b(var1, this.g.cx + this.cx, this.g.cy , this.a.width + 6, this.a.height);
        } else {
            // Cần scroll - set clip và translate
            this.a.a(var1, this.g.cx + this.cx, this.g.cy , this.a.width + 6, this.a.height);
        }


        // Vẽ từng tab
        for(var2 = 0; var2 < this.b.c.length; ++var2) {
            if (this.a.b(var2) && !this.d[var2]) {

                // Tính tọa độ X cho mỗi tab
                int tabX = var2 * 60;  // Mỗi tab rộng 65px
                int tabY = 0;
//                LangLa_fq.a(var1, 15, 0,tabX+ 35, this.a.h/2+2, 3);
//                Binary2.a(var1, 24, 0,tabX+4,  this.a.h/2-7, this.a.h, 20, 20, 20);
//                Binary2.a(var1, 60, 0, tabX+4,  this.a.h/2-7, this.width - 4, 20, 20, 20);

                // Vẽ nền tab nếu được chọn
                if (var2 == this.b.b) {
                    var1.c(-2908119);
                }

                var1.c();

                // Tính tọa độ Y cho text
                int textY = this.a.h/2+2;  // Tọa độ Y cố định
                if (this.j[var2].length >= 2) {
                    textY = 0;  // Nếu có 2 dòng, dịch lên trên
                }

                // Vẽ text của tab
                for(int var6 = 0; var6 < this.j[var2].length; ++var6) {
                    int lineY = textY + var6 * 10;  // Mỗi dòng cách nhau 10px

                    if (var2 == this.b.b) {
                        // Tab được chọn - màu highlight
                        mFont.a((mFont)mFont.f, (Graphics)var1,
                                this.j[var2][var6],
                                tabX + 32,  // Căn giữa tab (65/2 ≈ 32)
                                lineY,
                                2,          // Căn giữa
                                -16427443,  // Màu vàng
                                0);
                    } else if (this.a.l == var2) {
                        // Tab đang hover
                        mFont.a((mFont)mFont.f, (Graphics)var1,
                                this.j[var2][var6],
                                tabX + 32,
                                lineY + 1,
                                2,
                                -11131643,  // Màu xám nhạt
                                0);
                    } else {
                        // Tab bình thường
                        mFont.a((mFont)mFont.f, (Graphics)var1,
                                this.j[var2][var6],
                                tabX + 32,
                                lineY,
                                2,
                                -11131643,  // Màu xám
                                0);
                    }
                }

                // Vẽ icon đỏ (nếu có thông báo)
                if (this.c[var2]) {
                    LangLa_fq.a(var1, 713, 0, tabX + 55, this.a.h/2+2, 0);  // Icon ở góc phải trên
                }
            }
        }

        // Reset clip nếu có scroll
        if (this.e.a || this.b.c.length * 65 > this.a.width) {
            this.a(var1, 0, 0);
            var1.setClip(0, 0, DataCenter.gI().widthScreen, DataCenter.gI().heightScreen);
        }

        this.a(var1, var3, var4);
    }

    public void a(LangLa_cs var1, int var2) {
        this.f[var2].addElement(var1);
    }

    public void a(Vector var1, int var2) {
        for(int var3 = 0; var3 < var1.size(); ++var3) {
            this.f[var2].addElement((LangLa_cs)var1.elementAt(var3));
        }

    }

    public void d() {
        for(int var1 = 0; var1 < this.f.length; ++var1) {
            for(int var2 = 0; var2 < this.f[var1].size(); ++var2) {
                this.g.cG.removeElement(this.f[var1].elementAt(var2));
            }
        }

    }

    public void a(boolean var1) {
        for(int var3 = 0; var3 < this.f.length; ++var3) {
            for(int var4 = 0; var4 < this.f[var3].size(); ++var4) {
                LangLa_cs var2;
                if ((var2 = (LangLa_cs)this.f[var3].elementAt(var4)) instanceof LangLa_dz) {
                    ((LangLa_dz)var2).b(var1);
                } else if (var2 instanceof mTextBox) {
                    var2.a(var1);
                } else if (var2 instanceof LangLa_cl) {
                    var2.b(var1);
                }
            }
        }

    }
}
