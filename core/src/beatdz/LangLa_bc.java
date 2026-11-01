package beatdz;

import java.util.Vector;

public class LangLa_bc extends LangLa_fo {

   private LangLa_ii[] a = new LangLa_ii[8]; // 8 ô
   private Item[] b = new Item[8];           // 8 item
   private int c = -1;

   public LangLa_bc(MainScreen var1, Message var2) {
      this.p = var1;
      this.cF = 2;
      this.a(269, 200); // tăng height chút cho đủ 2 hàng
      this.H();

      this.I().setXY(this.width - 22, 20);

      // nút OK
      this.a(this.width / 2 - 33, this.height - 33, "", this, 1000, 672);

      this.q = new LangLa_il((byte)0, 0, 0, this.width, this.height, 1, 1);
      this.q.p = this;

      int spacing = 34;
      int startX = 20;
      int startY = 100;

      // 🔹 tạo 8 ô (2 hàng)
      for (int i = 0; i < this.a.length; ++i) {
         int x = startX + (i % 4) * spacing;
         int y = startY + (i / 4) * spacing;

         this.a[i] = new LangLa_ii(
                 (byte)1,
                 x,
                 y,
                 (byte)(30),
                 (byte)(30),
                 (byte)(1),
                 (byte)(1)
         );
      }

      // 🔹 load item từ server
      try {
         for (int i = 0; i < this.b.length; ++i) {
            this.b[i] = new Item();
            this.b[i].read(var2);

            // set level demo (có thể bỏ nếu server gửi)
            byte base = 8;
            this.b[i].level = (byte)(base + i * 2);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // 🔹 xử lý click
   public void a(LangLa_jz var1, int var2, int var3) {
      this.c = -1;
      super.a(var1, var2, var3);

      switch (var1.b) {
         case 300:
            this.c = (Integer) var1.k;

            Item item = this.b[this.c].a(); // clone
            item.level = 0;

            this.cL = a(this,
                    this.a[this.c].cx + 32,
                    this.a[this.c].cy,
                    30,
                    item);
            return;

         case 1000:
            this.J();
            GameSrc.gI();
            GameSrc.F();
            return;
      }
   }

   // 🔹 tạo button list
   public Vector c() {
      Vector v = new Vector();

      for (int i = 0; i < this.a.length; ++i) {
         v.addElement(this.a[i].a(300, this, i));
      }

      v.addElement(this.q.a(-9999, this));
      return v;
   }

   public void a(int var1, Object var2, LangLa_cy var3) {
   }

   public void a(int var1, Object var2) {
   }

   // 🔹 vẽ UI
   public void a(Graphics g) {
      LangLa_fq.a(g, 671, 0, 0, 0, 20);

      for (int i = 0; i < this.a.length; ++i) {
         b(g,
                 this.a[i].cx,
                 this.a[i].cy,
                 this.b[i],
                 this.c == i);
      }
   }

   // 🔹 update
   public void b() {
      for (int i = 0; i < this.a.length; ++i) {
         this.a[i].a();
      }
   }
}