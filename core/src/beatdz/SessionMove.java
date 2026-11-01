package beatdz;
public class SessionMove {
   public static short x;
   public static short y;
   public static Message c = null;

   public static void update(boolean var0) {
      try {
         if (Char.gI().cy < 30) {
            Char.gI().cy = 30;
         }

         if (Char.gI().aw != null || GameSrc.gI().bN || GameSrc.an) {
            return;
         }

         int xNew = Char.gI().cx - x;
         int yNew = Char.gI().cy - y;
         if ((xNew == 0 || yNew == 0) && xNew <= 127 && xNew >= -128 && yNew <= 127 && yNew >= -128) {
            if (xNew != 0) {
               x = Char.gI().cx;
               y = Char.gI().cy;
               if (Char.gI().status != 2 && Char.gI().status != 7) {
                  Session.gI().sendCharX(xNew);
               } else {
                  Session.gI().sendCharXWhenMove(xNew);
               }
            } else {
               if (yNew != 0) {
                  x = Char.gI().cx;
                  y = Char.gI().cy;
                  if (Char.gI().status != 2 && Char.gI().status != 7) {
                     Session.gI().sendCharY(yNew);
                     return;
                  }

                  Session.gI().sendCharYWhenMove(yNew);
               }

               return;
            }
         } else {
            x = Char.gI().cx;
            y = Char.gI().cy;
            if (Char.gI().status != 2 && Char.gI().status != 7) {
               Session.gI().sendCharXY(x, y);
            } else {
               Session.gI().sendCharXYWhenMove(x, y);
            }
         }
      } catch (Exception var2) {
         Utlis.println(var2);
      }

   }
}
