package beatdz;
public class LangLa_aa implements Runnable {
   
  public GameSrc a;

  public LangLa_aa(GameSrc var1) {
      this.a = var1;
   }

   public void run() {
      if (GameSrc.bR != null) {
         MainScreen.cC.add(GameSrc.bR);
      }

      GameSrc.bR = Binary2.a((new String[]{"f"})[GameSrc.a(this.a)], (MainScreen)null);
      GameSrc.b(this.a);
      if (GameSrc.a(this.a) > 0) {
         GameSrc.a(this.a, 0);
      }

   }
}
