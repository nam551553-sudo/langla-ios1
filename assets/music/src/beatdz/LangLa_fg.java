package beatdz;
 class LangLa_fg implements Runnable {
   public void run() {
      while(!LangLa_ep.a()) {
         try {
            synchronized(LangLa_cz.cU) {
               try {
                  LangLa_cz.cU.wait(3600000L);
               } catch (Exception var3) {
                  Utlis.println(var3);
               }
            }

            while(LangLa_cz.cX.size() > 0) {
               LangLa_cz.b((LangLa_fb)LangLa_cz.cX.remove(0));
            }
         } catch (Exception var5) {
            Utlis.println(var5);
         }
      }

      LangLa_cz.cY = false;
   }
}
