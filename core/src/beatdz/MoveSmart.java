package beatdz;import java.util.Vector;

public class MoveSmart {
   private static int a;
   private static int b;
   private static int c;
   private static int d;
   private static int e;
   private static int f;
   private static Vector g = new Vector();
   private static Object h = new Object();
   private static boolean i;
   private static int j = 0;
   private static int k = 0;

   public static void a() {
      i = true;
      g.removeAllElements();

      try {
         (new Thread(new LangLa_hw())).start();
      } catch (Exception var1) {
         Utlis.println(var1);
      }
   }

   public static void b() {
      i = false;
      g.removeAllElements();
      n();
   }

   private static void n() {
      if (k == 1) {
         synchronized(h) {
            h.notify();
         }
      } else {
         k = 2;
      }

   }

   public static Vector a(XYEntity var0, XYEntity var1, boolean var2) {
      Vector var3 = new Vector();
      if ((Char.gI().I() != null || SettingsTab.gI().autoTrain || var2 || true) && !GameSrc.gI().a(var0, var1)) {
         var3.add(var0);
         var3.add(var1);
         return var3;
      } else if ((var0.cx == var1.cx || var0.cy == var1.cy) && !GameSrc.gI().a(var0, var1)) {
         var3.add(var0);
         var3.add(var1);
         return var3;
      } else {
         XYEntity var7 = XYEntity.create(var1.cx, var0.cy);
         if (!GameSrc.gI().a(var0, var7) && !GameSrc.gI().a(var1, var7)) {
            var3.add(var0);
            var3.add(var7);
            var3.add(var1);
            return var3;
         } else {
            var7 = XYEntity.create(var0.cx, var1.cy);
            if (!GameSrc.gI().a(var0, var7) && !GameSrc.gI().a(var1, var7)) {
               var3.add(var0);
               var3.add(var7);
               var3.add(var1);
               return var3;
            } else {
               int var8 = var0.cy;
               if (var1.cy < var8) {
                  var8 = var1.cy;
               }

               for(int var4 = 1; var4 < 200; ++var4) {
                  var8 -= var4 * 10;
                  XYEntity var5 = XYEntity.create(var0.cx, var8);
                  XYEntity var6 = XYEntity.create(var1.cx, var8);
                  if (GameSrc.gI().a(var0, var5) || GameSrc.gI().a(var1, var6)) {
                     return null;
                  }

                  if (!GameSrc.gI().a(var5, var6)) {
                     var3.add(var0);
                     var3.add(var5);
                     var3.add(var6);
                     var3.add(var1);
                     return var3;
                  }
               }

               return var3;
            }
         }
      }
   }

   public static void a(int var0, int var1, int var2) {
      if (k == 1 || var0 != a || var1 != b || var2 != c || d != GameSrc.gI().mapID || e != Char.gI().cx || f != Char.gI().cy) {
         a = var0;
         b = var1;
         c = var2;
         g.removeAllElements();
         d = GameSrc.gI().mapID;
         e = Char.gI().cx;
         f = Char.gI().cy;
         i = true;
         j = 0;
         n();
      }
   }

   public static Vector c() {
      return g;
   }

   public static void d() {
      if (!i) {
         g.clear();
      } else {
         if (g.size() > 0 && Char.gI().pointMove == null) {
            g.removeElementAt(0);
            if (g.size() > 0) {
               XYEntity var0 = (XYEntity)g.firstElement();
               Char.gI().pointMove = new PointMove(var0.cx, var0.cy, Char.gI().speedMove, Char.gI().speedMove);
               Char.gI().a(Char.gI().pointMove);
               DataCenter.gI();
            }
         }

      }
   }

   public static boolean e() {
      return g.size() > 0;
   }

   
   static Object f() {
      return h;
   }

   
   static int a(int var0) {
      k = var0;
      return var0;
   }

   
   static boolean g() {
      return i;
   }

   
   static int h() {
      return e;
   }

   
   static int i() {
      return f;
   }

   
   static int j() {
      return b;
   }

   
   static int k() {
      return c;
   }

   
   static int b(int var0) {
      e = var0;
      return var0;
   }

   
   static int c(int var0) {
      f = var0;
      return var0;
   }

   
   static int d(int var0) {
      b = var0;
      return var0;
   }

   
   static int e(int var0) {
      c = var0;
      return var0;
   }

   
   static int l() {
      return k;
   }

   
   static Vector m() {
      return g;
   }
}
