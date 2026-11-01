//package beatdz;
//
//public class LangLa_caseOpening {}
//
//    private Item[] items;
//
//    private int reelX = 0;
//    private float speed = 0;
//    private boolean rolling = false;
//
//    private int targetIndex = 0;
//    private int centerX, centerY;
//
//    // màu rarity
//    private int[] rarityColor = {
//            0x00BFFF, // xanh
//            0x8A2BE2, // tím
//            0xFF69B4, // hồng
//            0xFF0000, // đỏ
//            0xFFD700  // vàng (hiếm)
//    };
//
//    public LangLa_caseOpening(int width, int height) {
//        centerX = width / 2;
//        centerY = height / 2;
//
//        // tạo item demo
//        items = new Item[30];
//        for (int i = 0; i < items.length; i++) {
//            items[i] = new Item(100 + i, true);
//        }
//    }
//
//    // ===== START ROLL =====
//    public void startRoll() {
//        rolling = true;
//        reelX = 0;
//        speed = 80f;
//
//        // random trúng (ưu tiên giữa)
//        targetIndex = 10 + Utlis.random(0, 10);
//    }
//
//    // ===== UPDATE =====
//    public void update() {
//        if (!rolling) return;
//
//        reelX += speed;
//
//        // ease-out giống CS2
//        speed *= 0.96f;
//
//        // dừng chính xác
//        int stopX = targetIndex * 60;
//
//        if (speed < 1.5f && reelX >= stopX) {
//            rolling = false;
//            reelX = stopX;
//        }
//    }
//
//    // ===== VẼ =====
//    public void draw(Graphics g) {
//
//        int baseY = centerY;
//
//        for (int i = 0; i < items.length; i++) {
//
//            int x = i * 60 - reelX;
//            int drawX = centerX - 150 + x;
//
//            int dist = Math.abs(drawX - centerX);
//
//            // scale theo khoảng cách (giống CS2)
//            int scale = 100 - dist / 2;
//            if (scale < 50) scale = 50;
//
//            int size = 40 * scale / 100;
//
//            // chọn màu rarity
//            int color = rarityColor[i % rarityColor.length];
//
//            // ===== glow =====
//            if (!rolling && i == targetIndex) {
//                LangLa_fq.b(g, drawX - 8, baseY - 8, size + 16, size + 16, 0xFFFF00);
//                LangLa_fq.b(g, drawX - 4, baseY - 4, size + 8, size + 8, color);
//            }
//
//            // ===== nền item =====
//            LangLa_fq.b(g, drawX - size / 2 - 2, baseY - size / 2 - 2, size + 4, size + 4, color);
//
//            // ===== item =====
//            Binary2.a(g,
//                    items[i].getItemTemplate().idIcon,
//                    0,
//                    drawX,
//                    baseY,
//                    3);
//
//            // ===== blur giả lập (item xa mờ) =====
//            if (dist > 120) {
//                g.a(50); // alpha
//            } else {
//                g.a(100);
//            }
//        }
//
//        // ===== vạch giữa =====
//        LangLa_fq.b(g, centerX - 2, baseY - 35, 4, 70, 0xFF0000);
//    }
//}
