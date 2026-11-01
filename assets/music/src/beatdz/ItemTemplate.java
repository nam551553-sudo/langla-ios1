package beatdz;import beatdz.*;

public class ItemTemplate {
   public short id;
   public String name;
   public String detail;
   public boolean isXepChong;
   public byte gioiTinh;
   public byte type;
   public byte idClass;
   public short idIcon;
   public short levelNeed;
   public int taiPhuNeed;
   public short idMob;
   public short idChar;

   public ItemTemplate(int var1) {
      this.id = (short)var1;
   }
}
