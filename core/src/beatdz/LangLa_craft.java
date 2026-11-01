package beatdz;

import com.tgame.model.Caption;
import java.util.Vector;

public class LangLa_craft extends LangLa_ge {
    public Item[] a = new Item[3];  // 3 ô: 2 input + 1 preview
    private LangLa_ij[] g = new LangLa_ij[3];
    private LangLa_dz h;  // Nút chế tạo
    public int c;  // Chỉ số slot đang chọn (0, 1, hoặc 2)
    
    public LangLa_craft(MainScreen var1) {
        super(var1, new String[]{"Chế tạo vật phẩm", Caption.H});
        // Tạo 3 grid để hiển thị items (giống Luyện Bí Kíp)
        this.g[0] = new LangLa_ij((byte)1, 50, this.a_() + 50, 50, 50, 50, 1, 1);
        this.g[1] = new LangLa_ij((byte)1, 120, this.a_() + 50, 50, 50, 50, 1, 1);
        this.g[2] = new LangLa_ij((byte)1, 190, this.a_() + 50, 50, 50, 50, 1, 1);
        // Nút chế tạo
        this.h = this.a(this.width / 2 - 35, this.height - 33, "Chế tạo", this, 0, -8);
        this.a(this.h, 0);
    }
    
    public void a() {
        // Load items từ bag vào array t - chỉ lấy items ID 751 (640) và 752 (641)
        this.t = new Item[Char.gI().arrItemBag.length];
        int var1 = 0;
        for(int var2 = 0; var2 < Char.gI().arrItemBag.length; ++var2) {
            if (Char.gI().arrItemBag[var2] != null &&
                (Char.gI().arrItemBag[var2].id == 751 || Char.gI().arrItemBag[var2].id == 752)) {
                this.t[var1] = Char.gI().arrItemBag[var2];
                ++var1;
            }
        }
    }
    
    public void a(int var1) {
        super.a(var1);
    }
    
    public void b() {
        super.b();
        if (this.k() == 0) {
            for(int var3 = 0; var3 < this.g.length; ++var3) {
                this.g[var3].a();
            }
        } else {
            this.a();
        }
    }
    
    public void a(Graphics var1) {
        if (this.j() <= 0) {
            this.a(var1, this.cx, this.cy, this.width, this.height, Caption.q, (byte)2, this.r);
        } else {
            super.a(var1);
        }
        
        if (this.k() == 0) {
            // Hiển thị text hướng dẫn (giống Luyện Bí Kíp)
            mFont.b(mFont.d, var1, "Đặt vật phẩm vào 2 ô", 14, this.a_() + 9, 0, -10831436, -16777216);
            mFont.b(mFont.d, var1, "Cần: 100 phôi cải trang và 50 đá chỉ số ", 14, this.a_() + 25, 0, -10831436, -16777216);
            mFont.b(mFont.d, var1, "càng nhiều đá chỉ số thì cải trang của bạn ", 14, this.a_() + 100, 20, -10831436, -16777216);
            mFont.b(mFont.d, var1, "chỉ số càng cao max là 1000 đá chỉ số nhận đc dòng tăng 50% stcm", 14, this.a_() + 110, 20, -10831436, -16777216);
            
            // Hiển thị 3 ô: 2 input + 1 preview
            a(var1, this.g[0].cx, this.g[0].cy, this.a[0], this.g[0].k >= 0, "Vật phẩm phôi cải trang");
            // Mũi tên giữa ô 1 và ô 2
            mFont.b(mFont.d, var1, "→", 20, this.g[0].cx + 60, this.g[0].cy + 15, -1, -16777216);
            a(var1, this.g[1].cx, this.g[1].cy, this.a[1], this.g[1].k >= 0, "Vật phẩm đá chỉ số");
            // Mũi tên giữa ô 2 và ô 3
            mFont.b(mFont.d, var1, "→", 20, this.g[1].cx + 60, this.g[1].cy + 15, -1, -16777216);
            a(var1, this.g[2].cx, this.g[2].cy, this.a[2], this.g[2].k >= 0, "Xem");
            
            this.b(var1);
        }
    }
    
    public Vector c() {
        Vector var1 = super.c();
        if (this.k() == 0) {
            // Thêm clickable areas cho 3 ô
            for(int var2 = 0; var2 < this.g.length; ++var2) {
                var1.addElement(this.g[var2].a(2001 + var2, this));
            }
        }
        return var1;
    }
    
    public void a(LangLa_jz var1, int var2, int var3) {
        if (this.k() == this.j() && var1.b == 0) {
            this.d();
        }
        
        super.a(var1, var2, var3);
        if (this.k() == 0) {
            switch(var1.b) {
            case 0:  // Nút chế tạo
                this.craftItem();
                return;
            case 2001:  // Click vào ô 1
                this.c = 0;
                this.a(var1.j, var1.j.k);
                this.cL = LangLa_cz.a(var1, this, this.a[0]);
                return;
            case 2002:  // Click vào ô 2
                this.c = 1;
                this.a(var1.j, var1.j.k);
                this.cL = LangLa_cz.a(var1, this, this.a[1]);
                return;
            case 2003:  // Click vào ô 3 (preview)
                // Không cho chọn vào ô preview
                return;
            }
        }
    }
    
    public void updatePreview() {
        // Nếu có đủ 2 vật phẩm, hiển thị preview vật phẩm kết quả
        if (this.a[0] != null && this.a[1] != null) {
            // Tạo preview item 1202
            try {
                this.a[2] = new Item(753);
                this.a[2].isLock = true;
            } catch (Exception e) {
                this.a[2] = null;
            }
        } else {
            this.a[2] = null;
        }
    }
    
    private void a(LangLa_ii var1, int var2) {
        for(int var3 = 0; var3 < this.g.length; ++var3) {
            this.g[var3].k = -1;
        }
        var1.k = var2;
    }
    
    public void d() {
        super.d();
        // Trả items về bag khi đóng tab
        for(int var1 = 0; var1 < this.a.length; ++var1) {
            if (this.a[var1] != null && var1 < 2) {  // Chỉ trả 2 ô input, không trả preview
                Char.gI().arrItemBag[this.a[var1].index] = this.a[var1];
                this.a[var1] = null;
            }
        }
    }
    
    private void craftItem() {
        try {
            // Kiểm tra có đủ items không
            if (this.a[0] == null || this.a[1] == null) {
                DataCenter.gI().currentScreen.showMessage("Vui lòng đặt đủ vật phẩm vào 2 ô", -65536);
                return;
            }
            
            // Kiểm tra item ID và số lượng
            if (this.a[0].id != 751 || this.a[0].getAmount() <= 5000) {
                DataCenter.gI().currentScreen.showMessage("Cần ít nhất 100 Phôi cải trang", -65536);
                return;
            }
            
            if (this.a[1].id != 752 || this.a[1].getAmount() <= 50) {
                DataCenter.gI().currentScreen.showMessage("Cần ít nhất 50 vật phẩm đá chỉ số", -65536);
                return;
            }
            
            // Gửi message đến server - chỉ gửi TYPE_TEMP = 0 (bag)
            Message msg = new Message((byte)-53);
            msg.writeByte((byte)0);  // TYPE_TEMP = 0 (bag)
            msg.writeShort(this.a[0].index);
            msg.writeByte((byte)0);  // TYPE_TEMP = 0 (bag)
            msg.writeShort(this.a[1].index);
            msg.send();
            
        } catch (Exception var4) {
            Utlis.println(var4);
        }
    }
    
    public Item[] b(Item var1) {
        // Luôn trả về arrItemBag với TYPE_TEMP = 0
        var1.TYPE_TEMP = 0;
        return Char.gI().arrItemBag;
    }
    
    private int e() {
        return 0;
    }
}
