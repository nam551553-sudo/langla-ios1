package beatdz;import java.util.Vector;

public class Npc extends Entity {
   public short idd;
   public byte k;
   public Vector l = new Vector();
   public LangLa_ih m;

   public Npc(byte var1, int var2, int var3, int var4) {
      this.status = var1;
      this.idd = (short)var2;
      this.setXY(var3, var4);
      NpcTemplate template = this.getNpcTemplate();
      int width = template == null || template.width <= 0 ? 32 : template.width;
      int height = template == null || template.height <= 0 ? 48 : template.height;
      this.setSize(width, height);
      if (Utlis.nextInt((int)2) == 0) {
         this.dir = 3;
      } else {
         this.dir = 2;
      }

      if (var2 == 28) {
         this.l.addElement(new LangLa_jw(182, 0, 0, -1));
      }

   }

   public NpcTemplate getNpcTemplate() {
      if (DataCenter.gI().NpcTemplate == null || this.idd < 0 || this.idd >= DataCenter.gI().NpcTemplate.length) {
         return null;
      }
      return DataCenter.gI().NpcTemplate[this.idd];
   }

   public void p() {
      XYEntity var1;
      if (this.status != 6 && (var1 = Char.c(this.cx, this.cy)) != null) {
         this.cy = var1.cy;
      }

      this.setXY(this.cx, this.cy);
   }

   public int a() {
      return this.cx - this.width / 2;
   }

   public int b() {
      return this.cx + this.width / 2;
   }

   public int c() {
      return this.cy - this.height;
   }

   public int d() {
      return this.cy;
   }

   public void q() {
      try {
         NpcTemplate template = this.getNpcTemplate();
         if (template == null) {
            return;
         }
         if (this.idd == 72) {
            if (GameSrc.gI().cg.length() > 0) {
               this.status = 0;
            } else {
               this.status = 6;
            }
         }

         if (template.hp <= 0) {
            template.hp = 100;
         }

         if (template.mp <= 0) {
            template.mp = 100;
         }

         if (this.status != 6 && template.a != null && this.status >= 0 && this.status < template.a.length && template.a[this.status] != null && template.a[this.status].length > 0) {
            if (this.m != null && this.m.a(this.m(), this.cy - this.height + 2)) {
               this.m = null;
            }

            ++this.e;
            if (this.e >= template.a[this.status].length) {
               this.e = 0;
            }

            this.k = template.a[this.status][this.e];

            for(int var1 = this.l.size() - 1; var1 >= 0; --var1) {
               LangLa_jw var2;
               (var2 = (LangLa_jw)this.l.elementAt(var1)).b((Entity)this);
               if (var2.r()) {
                  this.l.removeElement(var2);
               }
            }

         }
      } catch (Exception var3) {
      }
   }

   public void a(Graphics var1) {
      try {
         NpcTemplate template = this.getNpcTemplate();
         if (template == null || template.b == null || this.k < 0 || this.k >= template.b.length) {
            return;
         }
         if (this.status != 6) {
            if (!Utlis.f()) {
               LangLa_hz var2 = template.b[this.k];
               if (this.idd == 72) {
                  var2 = DataCenter.gI().NpcTemplate[GameSrc.gI().ch].b[this.k];
               }

               for(int var3 = 0; var3 < var2.a.length; ++var3) {
                  if (this.idd == 72) {
                     LangLa_fq.b(var1, var2.a[var3].a, Utlis.a(var2.a[var3].d), this.cx + var2.a[var3].b, this.cy + var2.a[var3].c, 33);
                  } else if (!this.i() && template.indexData != 235 && this.idd != 72) {
                     LangLa_fq.b(var1, var2.a[var3].a, Utlis.a(var2.a[var3].d), this.cx + var2.a[var3].b, this.cy + var2.a[var3].c, 33);
                  } else {
                     LangLa_fq.b(var1, var2.a[var3].a, Utlis.b(var2.a[var3].d), this.cx - var2.a[var3].b, this.cy + var2.a[var3].c, 33);
                  }
               }

               if (this.idd == 47) {
                  LangLa_fq.b(var1, 4139, 0, this.cx + 30, this.cy, 33);
               }

            }
         }
      } catch (Exception var4) {
      }
   }

   public String f() {
      return this.idd == 72 ? GameSrc.gI().cg : this.s();
   }

   public String g() {
      NpcTemplate template = this.getNpcTemplate();
      return template == null ? "100/100" : template.hp + "/" + template.hp;
   }

   public String h() {
      NpcTemplate template = this.getNpcTemplate();
      return template == null ? "100/100" : template.mp + "/" + template.mp;
   }

   public int r() {
      return this.idd % 5 + 1;
   }

   public void b(Graphics var1) {
      NpcTemplate template = this.getNpcTemplate();
      if ((template == null || template.indexData != 235) && !GameSrc.gI().bb) {
         if (this.status != 4 && this.status != 6) {
            int var2 = this.cy - this.height + 2;
            if (this.idd >= 2) {
               if (this.idd == 72) {
                  var2 -= 10;
                  mFont.a(var1, GameSrc.gI().cg, this.cx, var2, 2, -1);
                  var2 -= 10;
                  mFont.a(mFont.g, var1, this.s(), this.cx, var2, 2, -16648198, 3, 0);
               } else {
                  var2 -= 10;
                  mFont.a(var1, this.s(), this.cx, var2, 2, -1);
                  String detail = template == null || template.detail == null ? "" : template.detail;
                  if (detail.length() > 0) {
                     var2 -= 10;
                     mFont.a(var1, detail, this.cx, var2, 2, -7812062);
                  }
               }
            }

            if (GameSrc.gI().aA != null && GameSrc.gI().aA.equals(this)) {
               var2 -= 10;
               GameSrc.gI().aF.setXY(this.cx, var2);
               GameSrc.gI().aF.b(var1, 0, 0);
            }

            if (this.m != null) {
               this.m.a(var1);
            }
         }

      }
   }

   private String s() {
      NpcTemplate template = this.getNpcTemplate();
      if (template != null && template.name != null && template.name.trim().length() > 0) {
         return template.name;
      }
      return "NPC " + this.idd;
   }

   public int e() {
      return this.idd == 47 ? 4 : 3;
   }
}
