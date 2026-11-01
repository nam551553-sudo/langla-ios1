package beatdz;
import com.tgame.model.Caption;
import java.util.Vector;

public class LangLa_i extends LangLa_fo {
   private static final String PREVIEW_MARKER = "__GIFT_PREVIEW__";
   public static LangLa_i s;
   private mTextBox a;
   private int b;
   private int c;
   private LangLa_ij d;
   private Item[] e = new Item[12];

   public LangLa_i(MainScreen var1) {
      this.p = var1;
      s = this;
      this.a(230, 255);
      this.a = this.a(78, 44, 115, "", this, 0);
      byte var2 = 30;
      this.a.a = var2;
      this.a.a("");
      this.d = new LangLa_ij((byte)1, this.width / 2 - 48, 82, 96, 128, 32, 4, 3);
      this.b("Xem", 1000);
      this.c(Caption.g, 1001);
   }

   public void a(int var1, Object var2) {
   }

   public void a(int var1, Object var2, LangLa_cy var3) {
   }

   public void a(LangLa_jz var1, int var2, int var3) {
      switch(var1.b) {
      case 5001:
         if (var1.j != null && var1.j.k >= 0 && var1.j.k < this.e.length) {
            this.cL = LangLa_cz.a(var1, this, this.e[var1.j.k]);
         }
         return;
      case 1000:
         this.a((byte)1);
         return;
      case 1001:
         this.a((byte)0);
         return;
      default:
      }
   }

   public void a(Graphics var1) {
      this.a(var1, this.cx, this.cy, this.width, this.height, Caption.fh, (byte)2, false);
      this.b = 68;
      this.c = 35;
      mFont.a(mFont.d, var1, Caption.fi, 10, this.c + 20, 0, -1);
      mFont.a(mFont.d, var1, "Sinh tử hữu mệnh, phú quý tại thiên", this.width / 2, 73, 2, -1);
      this.a(var1, this.d);

      for(int var2 = 0; var2 < this.d.i; ++var2) {
         for(int var3 = 0; var3 < this.d.r; ++var3) {
            if (this.d.b(var2)) {
               int var4 = var2 * this.d.r + var3;
               LangLa_cz.b(var1, var3 * this.d.h, var2 * this.d.h, this.e[var4], var4 == this.d.k);
            }
         }
      }

      this.b(var1);
   }

   public void b() {
      if (this.d != null) {
         this.d.a();
      }
   }

   public Vector c() {
      Vector var1 = super.c();
      if (this.d != null) {
         var1.addElement(this.d.a(5001, this));
      }

      return var1;
   }

   public void d() {
      if (s == this) {
         s = null;
      }
   }

   private void a(byte var1) {
      try {
         Message var2;
         (var2 = Message.c((byte)-110)).writeUTF(this.a.f());
         var2.writeByte(var1);
         var2.send();
      } catch (Exception var3) {
      }
   }

   public static boolean a(String var0, Message var1) {
      if (!PREVIEW_MARKER.equals(var0)) {
         return false;
      }

      try {
         Item[] var2 = new Item[12];
         int var3 = var1.readByte();

         for(int var4 = 0; var4 < var3 && var4 < var2.length; ++var4) {
            Item var5 = new Item();
            var5.read(var1);
            if (var5.id >= 0) {
               var2[var4] = var5;
            }
         }

         if (s != null) {
            s.e = var2;
         }
      } catch (Exception var6) {
      }

      return true;
   }
}
