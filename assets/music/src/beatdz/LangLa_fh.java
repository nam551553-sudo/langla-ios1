package beatdz;import com.tgame.model.Caption;

 class LangLa_fh implements Runnable {
   public void run() {
      try {
         for(int var1 = 0; var1 < DataCenter.gI().ai.size(); ++var1) {
            String var2 = (String)DataCenter.gI().ai.get(var1);
            StringBuilder var10000 = (new StringBuilder()).append(DataCenter.gI().aU).append(DataCenter.gI().aY.replaceAll("img", "imgios"));
            DataCenter.gI();
            byte[] var6;
            if ((var6 = Binary.c(var10000.append(DataCenter.a(var2) + 1).append("/").append(var2).toString())) != null) {
               Binary.a(var6, true);
            }
         }

         LangLa_cz.cZ = false;
         Binary.writeBoolean("arr_full", true);
         DataCenter.gI().a(false);
         DataCenter.bd = 1;
         Caption.loadVN();
         GameSrc var5 = GameSrc.gI();

         try {
            var5.al.a(var5.mapID);
         } catch (Exception var3) {
         }

         DataCenter.gI().aH = false;
      } catch (Exception var4) {
         DataCenter.gI().setScreen((MainScreen)(new CheckVersionScreen()));
      }
   }
}
