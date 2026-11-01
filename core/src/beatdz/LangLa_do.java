package beatdz;
 public  class LangLa_do implements Runnable {
   
   public  LangLa_fb a;
   
   public  CheckVersionScreen b;

   public LangLa_do(CheckVersionScreen var1, LangLa_fb var2) {
      this.b = var1;
      this.a = var2;
   }

   public void run() {
      try {
         StringBuilder var10000 = (new StringBuilder()).append(DataCenter.gI().aU).append(DataCenter.gI().aY);
         DataCenter.gI();
         byte[] var1;
         if ((var1 = Binary.c(var10000.append(DataCenter.a(this.a.d) + 1).append("/").append(this.a.d).toString())) == null) {
            Utlis.sleep(1000L);
            var10000 = (new StringBuilder()).append(DataCenter.gI().aU).append(DataCenter.gI().aY);
            DataCenter.gI();
            if ((var1 = Binary.c(var10000.append(DataCenter.a(this.a.d) + 1).append("/").append(this.a.d).toString())) == null) {
               Utlis.sleep(1000L);
               var10000 = (new StringBuilder()).append(DataCenter.gI().aU).append(DataCenter.gI().aY);
               DataCenter.gI();
               if ((var1 = Binary.c(var10000.append(DataCenter.a(this.a.d) + 1).append("/").append(this.a.d).toString())) == null) {
                  CheckVersionScreen.a(this.b);
                  return;
               }
            }
         }

         Binary.a(var1, false);
         if (this.a.d.contains("Client")) {
            LangLa_fq.b();
         } else if (this.a.d.contains("Char")) {
            LangLa_fq.a();
         } else if (this.a.d.contains("font")) {
            mFont.a();
         }

         Binary.writeInt("arr_check_" + this.a.a, var1.length);
         this.a.f = true;
         CheckVersionScreen.b(this.b);
      } catch (Exception var2) {
         CheckVersionScreen.a(this.b);
         CheckVersionScreen.c(this.b).clear();
         CheckVersionScreen.d = true;
      }
   }
}
