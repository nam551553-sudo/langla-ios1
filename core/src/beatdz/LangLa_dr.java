package beatdz;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.utils.Disposable;

public class LangLa_dr implements Disposable {
   private int a = 8000;
   private AudioDevice b;
   private short[] c;
   private static LangLa_dr d;
   private static boolean e;

   public LangLa_dr(short[] var1) {
      d = this;
      this.b = Gdx.audio.newAudioDevice(2000, true);
      this.c = var1;
   }

   public void dispose() {
      try {
         if (Gdx.app.getType() != ApplicationType.iOS) {
            e = false;
            if (this.b != null) {
               this.b.dispose();
            }
         }

      } catch (Exception var1) {
      }
   }

   
   static short[] a(LangLa_dr var0) {
      return var0.c;
   }

   
   static AudioDevice b(LangLa_dr var0) {
      return var0.b;
   }

   
   static boolean a(boolean var0) {
      e = true;
      return true;
   }
}
