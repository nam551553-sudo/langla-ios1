package beatdz;import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.tgame.model.Caption;
import java.math.BigDecimal;
import java.util.Vector;

public class LangLa_cj extends LangLa_cz {
   public Vector a = new Vector();
   public Vector b;
   public Vector c;
   public boolean d = true;
   private boolean m = false;
   public boolean e;
   public LangLa_jb f;
   public LangLa_jb g;
   private LangLa_jb n;
   private LangLa_jb o;
   public short h;
   public short i;
   private LangLa_cx p;
   private byte q;
   private byte r;
   public byte j;
   private int s;
   private int t;
   private int u;
   private int v;
   private int w;
   private int x;
   private int y;
   private int z;
   private int A;
   private int B;
   private int C;
   private boolean D;
   public boolean k;
   public static mImage l;

   public LangLa_cj(LangLa_cx var1, byte var2, int var3) {
      this.f = new LangLa_jb(this, (byte)2, this.width);
      this.g = new LangLa_jb(this, (byte)3, this.height);
      this.p = var1;
      this.q = var2;
      this.r = (byte)var3;
   }

   public LangLa_cj(LangLa_cx var1, byte var2) {
      this.f = new LangLa_jb(this, (byte)2, this.width);
      this.g = new LangLa_jb(this, (byte)3, this.height);
      this.p = var1;
      this.q = 2;
   }

   public void a(int var1, int var2, boolean var3) {
      this.D = var3;
      LangLa_cs var4 = this.a();
      this.n = new LangLa_jb(var4, (byte)0, var1);
      this.o = new LangLa_jb(var4, (byte)1, var2);
   }

   public void a(LangLa_cs var1) {
      var1.w = this;
      this.a.remove(var1);
      this.g();
   }

   public void b(LangLa_cs var1) {
      var1.w = this;
      this.a.insertElementAt(var1, 0);
      this.g();
   }

   private void g() {
      this.cx = this.cy = this.width = this.height = 0;

      LangLa_cs var1;
      int var2;
      for(var2 = 0; var2 < this.a.size(); ++var2) {
         var1 = (LangLa_cs)this.a.elementAt(var2);
         if (var2 == 0) {
            this.cx = var1.cx;
            this.cy = var1.cy;
         } else {
            if (var1.cx < this.cx) {
               this.cx = var1.cx;
            }

            if (var1.cy < this.cy) {
               this.cy = var1.cy;
            }
         }
      }

      for(var2 = 0; var2 < this.a.size(); ++var2) {
         if ((var1 = (LangLa_cs)this.a.elementAt(var2)).cx + var1.width - this.cx > this.width) {
            this.width = (short)(var1.cx + var1.width - this.cx);
         }

         if (var1.cy + var1.height - this.cy > this.height) {
            this.height = (short)(var1.cy + var1.height - this.cy);
         }
      }

      this.f.a(this.width);
      this.g.a(this.height);
      this.h = this.width;
      this.i = this.height;
      if (this.j == 2) {
         this.a(false);
      } else if (this.j == 1) {
         this.a(true);
      } else if (this.d) {
         this.f.a(this.width);
         this.g.a(this.height);
      } else {
         this.width = this.height = 0;
         this.f.a(this.width);
         this.g.a(this.height);
      }
   }

   public LangLa_cs a() {
      return this.a.size() > 0 ? (LangLa_cs)this.a.lastElement() : null;
   }

   public void c(LangLa_cs var1) {
      Object var2 = this.a.lastElement();
      this.a.removeElement(var1);
      this.a.addElement(var1);
      if (!var1.equals(var2)) {
         this.a.removeElement(var2);
         this.a.addElement(var2);
      }

   }

   public void b() {
      boolean var1 = this.f.a();
      boolean var2 = this.g.a();
      if (var1 && !this.g.b() || var2 && !this.f.b()) {
         this.a(this.j == 1);
      }

      LangLa_cs var4;
      if (this.n != null && this.o != null && !this.c() && !this.d() && (var4 = this.a()) != null && !var4.n(0)) {
         short var5 = var4.cx;
         short var3 = var4.cy;
         this.n.a();
         this.o.a();
         int var6 = var4.cx - var5;
         int var7 = var4.cy - var3;
         this.a(var6, var7, var4);
         if (!this.n.b() && !this.o.b()) {
            this.n = null;
            this.o = null;
            if (this.D) {
               this.f();
            }
         }
      }

   }

   private void a(int var1, int var2, LangLa_cs var3) {
      for(int var5 = 0; var5 < this.a.size(); ++var5) {
         LangLa_cs var4;
         if (!(var4 = (LangLa_cs)this.a.elementAt(var5)).equals(var3)) {
            var4.setXY(var4.cx + var1, var4.cy + var2);
         }
      }

      this.g();
   }

   public void a(Graphics var1) {
      int var3 = var1.a;
      int var4 = var1.b;
      if (this.f.b() || this.g.b()) {
         switch(this.q) {
         case 0:
            this.a(var1, this.cx, this.cy, this.width, this.height);
            break;
         case 1:
            this.a(var1, this.cx + this.h - this.width, this.cy, this.width, this.height);
            break;
         case 2:
            this.a(var1, this.cx, this.cy + this.i - this.height, this.width, this.height);
            break;
         case 3:
            this.a(var1, this.cx + this.h - this.width, this.cy + this.i - this.height, this.width, this.height);
            break;
         case 4:
            this.a(var1, this.cx, this.cy, this.h, this.height);
            break;
         case 5:
            this.a(var1, this.cx, this.cy + this.i - this.height, this.h, this.height);
            break;
         case 6:
            this.a(var1, this.cx + this.h - this.width, this.cy, this.width, this.i);
            break;
         case 7:
            this.a(var1, this.cx, this.cy, this.width, this.i);
         }
      }

      LangLa_cs var10;
      if (this.m && (this.width > 0 || this.cy > 0) || this.d) {
         this.a(var1, this.cx + DataCenter.gI().currentScreen.cx, this.cy + DataCenter.gI().currentScreen.cy);
         int var5;
         switch(this.r) {
         case 3:
            if (l == null) {
               DataCenter.gI();
               Pixmap var2;
               (var2 = (l = mImage.a(140 * DataCenter.gI().zoomLevel, 70 * DataCenter.gI().zoomLevel)).b).setColor(new Color(0.0F, 0.0F, 0.0F, 0.0F));
               var2.fill();
               mImage var6 = LangLa_fq.a((int)687);
               Binary2.a(var2, var6.b, 120 * DataCenter.gI().zoomLevel, 0 * DataCenter.gI().zoomLevel, Blending.None);
               var6 = LangLa_fq.a((int)82);
               Binary2.a(var2, var6.b, 120 * DataCenter.gI().zoomLevel, 25 * DataCenter.gI().zoomLevel, Blending.None);
               var6 = LangLa_fq.a((int)36);
               Binary2.a(var2, var6.b, 100 * DataCenter.gI().zoomLevel, 0 * DataCenter.gI().zoomLevel, Blending.None);
               var6 = LangLa_fq.a((int)648);
               Binary2.a(var2, var6.b, 100 * DataCenter.gI().zoomLevel, 25 * DataCenter.gI().zoomLevel, Blending.None);
               var6 = LangLa_fq.a((int)49);
               Binary2.a(var2, var6.b, 75 * DataCenter.gI().zoomLevel, 0 * DataCenter.gI().zoomLevel, Blending.None);
               var6 = LangLa_fq.a((int)647);
               Binary2.a(var2, var6.b, 75 * DataCenter.gI().zoomLevel, 25 * DataCenter.gI().zoomLevel, Blending.None);           
               if (!DataCenter.ar) {
                  var6 = LangLa_fq.a((int)81);
                  Binary2.a(var2, var6.b, 50 * DataCenter.gI().zoomLevel, 0 * DataCenter.gI().zoomLevel, Blending.None);
               }

               if (Char.gI().rank >= 0) {
                  var6 = LangLa_fq.a((int)660);
                  Binary2.a(var2, var6.b, 50 * DataCenter.gI().zoomLevel, 25 * DataCenter.gI().zoomLevel, Blending.None);
               }
                var6 = LangLa_fq.a((int)1171);
               Binary2.a(var2, var6.b, 25 * DataCenter.gI().zoomLevel, 25 * DataCenter.gI().zoomLevel, Blending.None);
               var6 = LangLa_fq.a((int)1172);
               Binary2.a(var2, var6.b, 25 * DataCenter.gI().zoomLevel, 0 * DataCenter.gI().zoomLevel, Blending.None);

               var6 = LangLa_fq.a((int)1900); //sửa zoom client
               Binary2.a(var2, var6.b, 25 * DataCenter.gI().zoomLevel, 50 * DataCenter.gI().zoomLevel, Blending.None);
               var6 = LangLa_fq.a((int)1131);
               Binary2.a(var2, var6.b, 0 * DataCenter.gI().zoomLevel, 0 * DataCenter.gI().zoomLevel, Blending.None);



               Binary.disposeImage(l);
            }

            var1.a(l, 0, 0);
         default:
            var5 = 0;
         }

         while(var5 < this.a.size() - 1) {
            var10 = (LangLa_cs)this.a.elementAt(var5);
            this.a(var1, var10.b_(), var10.c_());
            var10.a(var1);
            ++var5;
         }

         this.a(var1, this.cx + DataCenter.gI().currentScreen.cx, this.cy + DataCenter.gI().currentScreen.cy);
         switch(this.r) {
         case 2:
            short var13 = this.height;
            int var11 = this.width - 5;
            int fps = Canvas.getfps();
            long ping = Canvas.getping();
            DataCenter.gI();
            String var10001 = "XY: " + Char.gI().cx + ", " + Char.gI().cy;
            String var10002 = "FPS: " + fps +" FPS";
            String var10003 = "Ping: " + ping +" ms";
            var5 = var13 + 10;
            mFont.a(var1, var10001, var11, var5, 1, -1);
            mFont.a(var1, var10002, var11, var5+40, 1, 0xFFFFFF00);
          //  mFont.a(var1, var10003, var11, var5 + 53, 1, 0xFFFFFF00);

            int var15;
            boolean isBattlefield = GameSrc.gI().getMapTemplate().id == 98 || GameSrc.gI().getMapTemplate().id == 99 || GameSrc.gI().getMapTemplate().id == 100;
            if ((GameSrc.gI().E() || isBattlefield) && GameSrc.gI().timeHoatDong != 0) {
               if (!GameSrc.gI().isHoatDongTime) {
                  var15 = (int)(((long)GameSrc.gI().timeHoatDong - (Utlis.time() - GameSrc.gI().timeStartHoatDong)) / 1000L);
                  if (GameSrc.gI().getMapTemplate().type != 5 && GameSrc.gI().getMapTemplate().type != 6 && GameSrc.gI().getMapTemplate().type != 8 && GameSrc.gI().getMapTemplate().type != 10) {
                     if (var15 > 0) {
                        var10001 = Caption.fq + Utlis.k(var15);
                        var5 += 10;
                        mFont.a(var1, var10001, var11, var5, 1, -1);
                     }
                  } else {
                     var10001 = Caption.fp + Utlis.k(var15);
                     var5 += 10;
                     mFont.a(var1, var10001, var11, var5, 1, -1);
                  }
               } else {
                  var10001 = Caption.fm + Utlis.j((int)((Utlis.time() - GameSrc.gI().timeStartHoatDong) / 1000L));
                  var5 += 10;
                  mFont.a(var1, var10001, var11, var5, 1, -1);
                  if (isBattlefield) {
                     var10001 = "Phe Ch\u00ednh: " + GameSrc.gI().bJ;
                     var5 += 10;
                     mFont.a(var1, var10001, var11, var5, 1, -1);
                     var10001 = "Phe T\u00e0: " + GameSrc.gI().bK;
                     var5 += 10;
                     mFont.a(var1, var10001, var11, var5, 1, -1);
                  } else if (GameSrc.gI().getMapTemplate().type == 6) {
                     var10001 = Caption.fn + GameSrc.gI().bJ;
                     var5 += 10;
                     mFont.a(var1, var10001, var11, var5, 1, -1);
                  } else if (GameSrc.gI().getMapTemplate().type == 10 || GameSrc.gI().getMapTemplate().type == 16) {
                     var10001 = Caption.fo + GameSrc.gI().bL;
                     var5 += 10;
                     mFont.a(var1, var10001, var11, var5, 1, -1);
                  }

                  if (GameSrc.gI().getMapTemplate().type == 6 || GameSrc.gI().getMapTemplate().type == 17) {
                     var10001 = Caption.fn + GameSrc.gI().bL;
                     var5 += 10;
                     mFont.a(var1, var10001, var11, var5, 1, -1);
                  }
               }
            }

            LangLa_ek var7 = LangLa_cd.e();
            if (Char.gI().idClass > 0 && var7 != null) {
               String var14 = var7.d + Caption.kj + Utlis.j(Utlis.n(var7.c));
               if (DataCenter.gI().i % 16 >= 10) {
                  var15 = var5 + 10;
                  mFont.b(mFont.d, var1, var14, var11, var15, 1, -2560, -16777216);
               } else {
                  var15 = var5 + 10;
                  mFont.b(mFont.d, var1, var14, var11, var15, 1, -1, -16777216);
               }
            }
         case 3:
         case 4:
         default:
            break;
         case 5:
            this.b(var1);
            break;
         case 6:
            c(var1);
         }
      }

      if (this.r == 4 && this.q == 0) {
         this.a(var1, 0, 0);
         DataCenter.gI();
         mFont.a((BitmapFont)Graphics.b(80), (Graphics)var1, GameSrc.gI().getMapTemplate().name, DataCenter.gI().widthScreen - 43, 7, 2, -6488, -10275328);
         Binary2.a(var1, -9, 0, 35, 12, 73, (int)7);
         Binary2.a(var1, 30, 0, 35, 12, Char.gI().a(73), (int)7);
         Binary2.a(var1, -9, 0, 39, 23, 66, (int)7);
         Binary2.a(var1, 31, 0, 39, 23, Char.gI().b(66), (int)7);
         LangLa_fq.a(var1, 45, 0, 35, 10, 0);
         if (Char.gI().idClass != 0 && (GameSrc.gI().diemTiemNang > 0 || GameSrc.gI().diemKyNang > 0)) {
            LangLa_fq.a(var1, 713, 0, 18, 37, 0);
         }

         mFont.a((BitmapFont)Graphics.b(78), (Graphics)var1, Char.gI().g(), 72, 15, 2, -1, -10275328);
         mFont.a((BitmapFont)Graphics.b(78), (Graphics)var1, Char.gI().h(), 72, 26, 2, -1, -10275328);
         if (Session.r > 0) {
            mFont.a((mFont)mFont.e, (Graphics)var1, Caption.qg, DataCenter.gI().widthScreen / 2, DataCenter.gI().heightScreen / 2, 2, -16711681, -10275328);
         }

         Graphics var12 = var1;

         try {
            if (GameSrc.gI().aA != null) {
               if (GameSrc.gI().aA instanceof Char) {
                  Char var16 = (Char)GameSrc.gI().aA;
                  Binary2.a(var12, -9, 0, 158, 17, 63, (int)7);
                  Binary2.a(var12, 30, 0, 158, 17, var16.a(63), (int)7);
                  Binary2.a(var12, -9, 0, 157, 27, 61, (int)7);
                  Binary2.a(var12, 31, 0, 157, 27, var16.b(61), (int)7);
                  LangLa_fq.a(var12, 28, 0, 130, 10, 0);
                  mFont.b(mFont.d, var12, GameSrc.gI().aA.f(), 180, 6, 2, -16646423, -11184811);
                  mFont.a((BitmapFont)Graphics.b(78), (Graphics)var12, GameSrc.gI().aA.g(), 188, 21, 2, -1, -10275328);
                  mFont.a((BitmapFont)Graphics.b(78), (Graphics)var12, GameSrc.gI().aA.h(), 188, 30, 2, -1, -10275328);
                  Binary2.a(var12, DataCenter.gI().DataIconChar[var16.idChar].idIcon, 0, 131, 11, 83);
               } else if (GameSrc.gI().aA instanceof Npc) {
                  Npc var17 = (Npc)GameSrc.gI().aA;
                  Binary2.a(var12, 30, 0, 158, 17, 63, (int)7);
                  Binary2.a(var12, 31, 0, 157, 27, 63, (int)7);
                  LangLa_fq.a(var12, 28, 0, 130, 10, 0);
                  if (!DataCenter.ar) {
                     mFont.b(mFont.d, var12, GameSrc.gI().aA.f(), 180, 6, 2, -16646423, -11184811);
                  }

                  mFont.a((BitmapFont)Graphics.b(78), (Graphics)var12, GameSrc.gI().aA.g(), 188, 21, 2, -1, -10275328);
                  mFont.a((BitmapFont)Graphics.b(78), (Graphics)var12, GameSrc.gI().aA.h(), 188, 30, 2, -1, -10275328);
                  mFont.b(mFont.d, var12, Caption.ro[var17.r() - 1], 145, 25, 2, Utlis.h(var17.r()), -11184811);
               } else {
                  Mob var18;
                  if (GameSrc.gI().aA instanceof Mob && (var18 = (Mob)GameSrc.gI().aA).getMobTemplate().type != 10) {
                     Binary2.a(var12, -9, 0, 158, 17, 63, (int)7);
                     Binary2.a(var12, 30, 0, 158, 17, var18.a(63), (int)7);
                     Binary2.a(var12, 32, 0, 157, 27, 61, (int)7);
                     LangLa_fq.a(var12, 28, 0, 130, 10, 0);
                     mFont.b(mFont.d, var12, GameSrc.gI().aA.f(), 180, 6, 2, -16646423, -11184811);
                     mFont.a((BitmapFont)Graphics.b(78), (Graphics)var12, GameSrc.gI().aA.g(), 188, 21, 2, -1, -10275328);
                     mFont.a((BitmapFont)Graphics.b(78), (Graphics)var12, GameSrc.gI().aA.h(), 188, 30, 2, -1, -10275328);
                     mFont.b(mFont.d, var12, Caption.ro[var18.he - 1], 145, 25, 2, Utlis.h(var18.he), -11184811);
                  }
               }
            }
         } catch (Exception var9) {
         }
      }

      if (this.f.b() || this.g.b()) {
         this.a(var1, this.s, this.t);
         var1.setClip(this.u, this.v, this.w, this.x);
      }

      if ((var10 = this.a()) != null) {
         this.a(var1, var10.b_(), var10.c_());
         var10.a(var1);
      }

      this.a(var1, var3, var4);
   }

   public void a(Vector var1, int var2, int var3, int var4, int var5, int var6) {
      this.b = var1;
      this.y = var2;
      this.z = var3;
      this.A = var4;
      this.B = 78;
      this.C = 38;
      this.h();
   }

   public boolean c() {
      if (this.j > 0) {
         return this.j == 1;
      } else {
         return this.d;
      }
   }

   public boolean d() {
      return this.f.b() || this.g.b() || this.j > 0;
   }

   public void e() {
      this.m = true;
      if (!this.f.b() && !this.g.b()) {
         this.h = this.width;
         this.i = this.height;
         this.j = 2;
         this.f.a(0);
         this.g.a(0);
      } else {
         this.a(false);
         this.width = this.height = 0;
         this.f.a(this.width);
         this.g.a(this.height);
      }
   }

   public void f() {
      this.m = true;
      if (!this.f.b() && !this.g.b()) {
         this.j = 1;
         this.f.a(this.h);
         this.g.a(this.i);
      } else {
         this.a(true);
         this.width = this.h;
         this.height = this.i;
         this.f.a(this.h);
         this.g.a(this.i);
      }
   }

   public void a(boolean var1) {
      this.d = var1;
      this.j = 0;
      this.m = false;
      if (var1) {
         this.width = this.h;
         this.height = this.i;
         this.f.a(this.width);
         this.g.a(this.height);
      } else {
         this.width = this.height = 0;
         this.f.a(this.width);
         this.g.a(this.height);
      }
   }

   private void a(Graphics var1, int var2, int var3, int var4, int var5) {
      this.s = var1.a;
      this.t = var1.b;
      this.u = var1.i();
      this.v = var1.j();
      this.w = var1.h;
      this.x = var1.i;
      if (var4 > this.w) {
         var4 = this.w;
      }

      if (var5 > this.x) {
         var5 = this.x;
      }

      this.a(var1, var2, var3);
      var1.setClip(0, 0, var4, var5);
   }

   private void h() {
      int var3 = this.B;
      int var4 = this.A;

      for(int var6 = 0; var6 < this.b.size(); ++var6) {
         LangLa_cs var5 = (LangLa_cs)this.b.elementAt(var6);
         int var1;
         int var2;
         if (var3 > 270) {
            var2 = var3 - 270;
            var1 = LangLa_hi.o[90 - var2] * var4 / 1000;
            var2 = LangLa_hi.o[var2] * var4 / 1000;
            var5.cx = (short)(0 + var1 + this.y);
            var5.cy = (short)(0 + var2 + this.z);
         } else if (var3 > 180) {
            var2 = var3 - 180;
            var1 = LangLa_hi.o[var2] * var4 / 1000;
            var2 = LangLa_hi.o[90 - var2] * var4 / 1000;
            var5.cx = (short)(0 - var1 + this.y);
            var5.cy = (short)(0 + var2 + this.z);
         } else if (var3 > 90) {
            var2 = var3 - 90;
            var1 = LangLa_hi.o[90 - var2] * var4 / 1000;
            var2 = LangLa_hi.o[var2] * var4 / 1000;
            var5.cx = (short)(0 - var1 + this.y);
            var5.cy = (short)(0 - var2 + this.z);
         } else {
            var1 = LangLa_hi.o[var3] * var4 / 1000;
            var2 = LangLa_hi.o[90 - var3] * var4 / 1000;
            var5.cx = (short)(0 + var1 + this.y);
            var5.cy = (short)(0 - var2 + this.z);
         }

         var5.setXY(var5.cx - var5.width / 2, var5.cy - var5.height / 2);
         var3 += this.C;
      }

      this.g();
   }

   private void b(Graphics var1) {
      var1.clean();
      Vector var2;
      if ((var2 = this.c) != null) {
         for(int var4 = 0; var4 < var2.size(); ++var4) {
            LangLa_cs var3 = (LangLa_cs)var2.elementAt(var4);
            this.a(var1, var3.b_(), var3.c_());
            if (var4 < GameSrc.gI().aE.size() && !var3.v) {
               Skill var5;
               Skill var7;
               float var6 = (float)((int)((var5 = var7 = (Skill)GameSrc.gI().aE.elementAt(var4)).time - Utlis.time()));
               float var8 = (float)(var5.coolDown / 25);
               if ((var8 = !(var6 <= 0.0F) && var8 != 0.0F ? var6 / var8 : 26.0F) <= 25.0F && var8 > 0.0F) {
                  LangLa_fq.a(var1, 102, 0, 0, 25, 25 * DataCenter.gI().zoomLevel, (int)var8 * DataCenter.gI().zoomLevel, 0, 25 - (int)var8, 25, (int)var8, 36);
               }

               if (var7.f() > 0.0F) {
                  mFont var10000 = mFont.b;
                  float var10002 = var7.f() / 1000.0F;
                  boolean var9 = true;
                  var8 = var10002;
                  mFont.b(var10000, var1, String.valueOf((new BigDecimal(Float.toString(var8))).setScale(2, 4)), 12, 12, 2, -1, -16777216);
               }
            }
         }
      }

      this.a(var1, this.cx + DataCenter.gI().currentScreen.cx, this.cy + DataCenter.gI().currentScreen.cy);
   }

   private static void c(Graphics var0) {
      int var1 = 5 + AppListener.gI().o;
      int var2 = 47 + AppListener.gI().o / 2;

      for(int var4 = 0; var4 < Char.gI().vEffect.size(); ++var4) {
         Effect var3;
         (var3 = (Effect)Char.gI().vEffect.elementAt(var4)).a(var0, var1, var2, Char.gI());
         var3.setXY(var1, var2);
         var1 += 18;
      }

   }
}
