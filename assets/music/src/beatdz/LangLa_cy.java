package beatdz;import java.util.Vector;

public abstract class LangLa_cy extends LangLa_cz {
   public LangLa_fz cO = new LangLa_fz();
   public LangLa_ga cP;

   public abstract void a(Graphics var1);

   public abstract void b();

   public abstract void p();

   public abstract Vector c();

   public abstract void a(LangLa_jz var1, int var2, int var3);

   public void s() {
   }

   public int b_() {
      return this.cx;
   }

   public int c_() {
      return this.cy;
   }

   public void R() {
      try {
         if (this.cP == null) {
            this.cP = new LangLa_ga();
         }

         Vector var1;
         if ((var1 = this.c()) == null) {
            this.cP.a = new LangLa_jz[0];
         } else {
            this.cP.a = new LangLa_jz[var1.size()];
            if (!(this instanceof LangLa_cs)) {
               for(int var5 = 0; var5 < var1.size(); ++var5) {
                  this.cP.a[var5] = (LangLa_jz)var1.elementAt(var5);
               }

               return;
            }

            LangLa_cs var2 = (LangLa_cs)this;

            for(int var3 = 0; var3 < var1.size(); ++var3) {
               this.cP.a[var3] = (LangLa_jz)var1.elementAt(var3);
               LangLa_jz var10000 = this.cP.a[var3];
               var10000.c += var2.s.cx;
               var10000 = this.cP.a[var3];
               var10000.e += var2.s.cx;
               var10000 = this.cP.a[var3];
               var10000.d += var2.s.cy;
               var10000 = this.cP.a[var3];
               var10000.f += var2.s.cy;
            }
         }
      } catch (Exception var4) {
         Utlis.println(var4);
      }

   }

   public boolean n(int var1) {
      return this.cP != null && this.cP.a(0);
   }

   public void c(Graphics var1) {
      int var2 = var1.a;
      int var3 = var1.b;
      this.a(var1, this.b_(), this.c_());
      this.a(var1);
      this.a(var1, var2, var3);
   }

   public void d(Graphics var1) {
      int var2 = var1.a;
      int var3 = var1.b;
      this.a((Graphics)var1, this.cx, this.cy);
      var1.f(-65536);
      if (this.cP != null) {
         for(int var4 = 0; var4 < this.cP.a.length; ++var4) {
            if (this.cP.a[var4] != null) {
               var1.b(this.cP.a[var4].c, this.cP.a[var4].d, this.cP.a[var4].e - this.cP.a[var4].c, this.cP.a[var4].f - this.cP.a[var4].d);
            }
         }
      }

      this.a(var1, var2, var3);
   }

   public void a(Graphics var1, int var2, int var3) {
      var1.a(-var1.a, -var1.b);
      var1.a(var2, var3);
   }

   public void b(Graphics var1) {
      this.a((Graphics)var1, 0, 0);
      var1.setClip(0, 0, DataCenter.gI().widthScreen, DataCenter.gI().heightScreen);
      var1.a(this.b_(), this.c_());
   }

   public void a(Graphics var1, LangLa_ii var2) {
      var2.a(var1, this.b_(), this.c_(), var2.width, var2.height);
   }

   public void b(Graphics var1, LangLa_ii var2) {
      var2.b(var1, this.b_(), this.c_(), var2.width, var2.height);
   }

   public static void a(Vector var0) {
      try {
         for(int var4 = 0; var4 < var0.size() - 1; ++var4) {
            LangLa_cx var1 = (LangLa_cx)var0.elementAt(var4);

            for(int var5 = var4 + 1; var5 < var0.size(); ++var5) {
               LangLa_cx var2;
               if ((var2 = (LangLa_cx)var0.elementAt(var5)).cF < var1.cF) {
                  LangLa_cx var3 = var2;
                  var2 = var1;
                  var1 = var3;
                  var0.setElementAt(var3, var4);
                  var0.setElementAt(var2, var5);
               }
            }
         }

      } catch (Exception var6) {
      }
   }
}
