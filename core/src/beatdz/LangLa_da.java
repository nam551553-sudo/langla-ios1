package beatdz;
 class LangLa_da implements Runnable {
   public void run() {
      while(!LangLa_ep.a()) {
         try {
            long var1 = System.currentTimeMillis();
            Utlis.sleep(5000L);
            long var3;
            if ((var3 = System.currentTimeMillis() - var1) < 4000L) {
               Session.r += 150;
            } else if (var3 < 5000L) {
               Session.r = (int)((long)Session.r + (5000L - var3) / 20L);
            }
         } catch (Exception var5) {
            Utlis.println(var5);
         }
      }

      LangLa_cz.cY = false;
   }
}
