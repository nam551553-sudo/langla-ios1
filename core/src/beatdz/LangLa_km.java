package beatdz;
import com.tgame.model.Caption;
import java.util.Vector;

public class LangLa_km extends LangLa_fo {
   public static byte[] a;
   private static LangLa_ce[] b;

   public LangLa_km(int var1, MainScreen var2) {
      this.cF = 2;
      this.r = false;
      this.a(250, 200);
      this.p = var2;
      LangLa_dz var4;
      (var4 = this.a(Caption.n, 2001)).setXY(var4.cx, var4.cy - 5);
      b = new LangLa_ce[14];

      LangLa_ce var10000;
      boolean var3;
      for(var1 = 0; var1 < b.length / 2; ++var1) {
         switch(var1) {
         case 3:
            b[4 + var1] = this.a(20, 25 + var1 * 18, Caption.rP[var1], 4 + var1);
            b[var1] = this.a(135, 25 + var1 * 18, Caption.rP[4 + var1], var1);
            break;
         case 4:
         case 5:
            b[var1 * 2] = this.a(20, 28 + (var1 + 1) * 18, Caption.rP[var1 * 2], var1 * 2);
            b[var1 * 2 + 1] = this.a(135, 28 + var1 * 18, Caption.rP[var1 * 2 + 1], var1 * 2 + 1);
            break;
         case 6:
            b[var1 * 2] = this.a(20, 28 + (var1 + 1) * 18, Caption.rP[var1 * 2], var1 * 2);
            var10000 = b[var1 * 2];
            var3 = true;
            var10000.v = var3;
            b[var1 * 2 + 1] = this.a(135, 28 + var1 * 18, Caption.rP[var1 * 2 + 1], var1 * 2 + 1);
            break;
         default:
            b[var1] = this.a(20, 25 + var1 * 18, Caption.rP[var1], var1);
            b[4 + var1] = this.a(135, 25 + var1 * 18, Caption.rP[4 + var1], 4 + var1);
         }
      }

      var10000 = b[9];
      var3 = true;
      var10000.v = var3;
      e();
   }

   public void a(int var1, Object var2) {
   }

   public void a(Graphics var1) {
      this.a(var1, 0, 0, this.width, this.height, 80, 55, 56);
      mFont.a(mFont.d, var1, Caption.m, 15, 15, 0, -1);
      mFont.a(mFont.d, var1, Caption.ly, 15, 105, 0, -1);
   }

   public void a(LangLa_jz var1, int var2, int var3) {
      switch(var1.b) {
      case 2001:
         mConfig.gI().writeBoolean(16, false);
         mConfig.gI().writeBoolean(20, true);
         mConfig.gI().writeBoolean(21, false);
         mConfig.gI().writeBoolean(22, false);
         f();
         j();
      default:
      }
   }

   public void a(int var1, Object var2, LangLa_cy var3) {
      switch(var1) {
      case 8:
         mConfig.gI().writeBoolean(20, b[var1].a);
         break;
      case 9:
         mConfig.gI().writeBoolean(16, b[var1].a);
         break;
      case 10:
         mConfig.gI().writeBoolean(21, b[var1].a);
         break;
      case 11:
         mConfig.gI().writeBoolean(1, b[var1].a);
         SettingsTab.gI().F();
         LangLa_dp.a().b(b[var1].a);
         break;
      case 12:
         mConfig.gI().writeBoolean(22, b[var1].a);
         break;
      case 13:
         mConfig.gI().writeBoolean(0, b[var1].a);
         SettingsTab.gI().F();
         LangLa_dp.a().a(b[var1].a);
         break;
      default:
         LangLa_kw.b[var1] = b[var1].a ? a[var1] : -1;
      }

      g();
      j();
   }

   public static void e() {
      try {
         f();
         byte[] var0;
         if ((var0 = Binary.read("chanels")) != null && var0.length == LangLa_kw.b.length) {
            for(int var1 = 0; var1 < var0.length; ++var1) {
               LangLa_kw.b[var1] = var0[var1];
            }
         }
      } catch (Exception var2) {
         Utlis.println(var2);
      }

      g();
   }

   private static void f() {
      a = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
      LangLa_kw.b = new byte[]{0, 1, 2, -1, -1, -1, -1, 7, 8};
      g();
   }

   private static void g() {
      try {
         if (b != null) {
            for(int var0 = 0; var0 < b.length; ++var0) {
               if (b[var0] != null) {
                  LangLa_ce var10000;
                  boolean var10001;
                  switch(var0) {
                  case 8:
                     var10000 = b[var0];
                     var10001 = mConfig.gI().readBoolean(20);
                     break;
                  case 9:
                     var10000 = b[var0];
                     var10001 = mConfig.gI().readBoolean(16);
                     break;
                  case 10:
                     var10000 = b[var0];
                     var10001 = mConfig.gI().readBoolean(21);
                     break;
                  case 11:
                     var10000 = b[var0];
                     var10001 = mConfig.gI().readBoolean(1);
                     break;
                  case 12:
                     var10000 = b[var0];
                     var10001 = mConfig.gI().readBoolean(22);
                     break;
                  case 13:
                     var10000 = b[var0];
                     var10001 = mConfig.gI().readBoolean(0);
                     break;
                  default:
                     var10000 = b[var0];
                     var10001 = LangLa_kw.b[var0] >= 0;
                  }

                  boolean var2 = var10001;
                  var10000.a = var2;
               }
            }
         }

      } catch (Exception var3) {
         Utlis.println(var3);
      }
   }

   private static void j() {
      Binary.write("chanels", LangLa_kw.b);
   }

   public void b() {
   }

   public Vector c() {
      super.c();
      Vector var1;
      (var1 = new Vector()).addElement(new LangLa_jz(0, 0, 0, this.width, this.height, this.q, this));
      return var1;
   }
}
