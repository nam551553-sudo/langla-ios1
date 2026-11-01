package beatdz.mod.other;

import beatdz.Char;
import beatdz.DataCenter;
import beatdz.GameSrc;
import beatdz.Item;
import beatdz.LangLa_cx;
import beatdz.Message;
import beatdz.SkillClan;
import beatdz.Utlis;
import beatdz.mTextBox;
import beatdz.mod.trothu.RenTrangBi;
import beatdz.mod.trothu.TabTroThu;

public class InMess {
    public static InMess instance;

    public static InMess gI() {
        if (instance == null) {
            instance = new InMess();
        }
        return instance;
    }

    public void msgPhanThan(Message m) {
        try {
            byte type = m.readByte();
            int idChar = m.readInt();
            if (type != 100 && Char.gI().idEntity != idChar) {
                return;
            }
            LangLa_cx var2 = GameSrc.gI().H();
            switch (type) {
                case 0: {
                    byte type_Ac = m.readByte();
                    if (type_Ac == -1) {
                        Char.gI().deTu = null;
                        break;
                    }
                    Char.gI().deTu = new Char();
                    Char.gI().deTu.readPhanThan(m);
                    if (!(GameSrc.gI().H() instanceof TabTroThu)) break;
                    ((TabTroThu) GameSrc.gI().H())._char = Char.gI().deTu;
                    break;
                }
                case 1: {
                    int num;
                    if (!(var2 instanceof TabTroThu)) break;
                    TabTroThu chucNangNew = (TabTroThu) var2;
                    chucNangNew.pointTiemNang = m.readShort();
                    chucNangNew.pointSucManh = m.readShort();
                    chucNangNew.pointChakra = m.readShort();
                    chucNangNew.pointMp = m.readShort();
                    chucNangNew.pointHp = m.readShort();
                    chucNangNew.optionPT[0] = m.readInt();
                    chucNangNew.optionPT[1] = m.readInt();
                    chucNangNew.optionPT[2] = m.readShort();
                    chucNangNew.optionPT[3] = m.readShort();
                    chucNangNew.optionPT[4] = m.readShort();
                    chucNangNew.optionPT[5] = m.readShort();
                    chucNangNew.optionPT[6] = m.readShort();
                    chucNangNew.optionPT[7] = m.readShort();
                    chucNangNew.optionPT[8] = m.readShort();
                    chucNangNew.optionPT[9] = m.readShort();
                    chucNangNew.optionPT[10] = m.readShort();
                    chucNangNew.optionPT[11] = m.readShort();
                    chucNangNew.optionPT[12] = m.readShort();
                    chucNangNew.optionPT[13] = m.readShort();
                    chucNangNew.optionPT[14] = m.readShort();
                    chucNangNew.optionPT[15] = m.readShort();
                    chucNangNew.optionPT[16] = m.readShort();
                    chucNangNew.optionTC[0] = m.readShort();
                    chucNangNew.optionTC[1] = m.readShort();
                    chucNangNew.optionTC[2] = m.readShort();
                    chucNangNew.optionTC[3] = m.readShort();
                    chucNangNew.optionTC[4] = m.readShort();
                    chucNangNew.optionTC[5] = m.readShort();
                    chucNangNew.optionTC[6] = m.readShort();
                    chucNangNew._char.speedMove = (short) (chucNangNew.optionTC[6] / 100);
                    chucNangNew.optionTC[7] = m.readShort();
                    chucNangNew.optionTC[8] = m.readShort();
                    chucNangNew.optionTC[9] = m.readShort();
                    chucNangNew.optionTC[10] = m.readShort();
                    chucNangNew.optionTC[11] = m.readShort();
                    chucNangNew.optionTC[12] = m.readShort();
                    chucNangNew.optionTC[13] = m.readShort();
                    chucNangNew.optionTC[14] = m.readShort();
                    chucNangNew.optionTC[15] = m.readShort();
                    chucNangNew.optionTC[16] = m.readShort();
                    chucNangNew.optionTC[17] = m.readShort();
                    chucNangNew.textBoxes[0].a("" + chucNangNew.pointSucManh);
                    chucNangNew.textBoxes[1].a("" + chucNangNew.pointChakra);
                    chucNangNew.textBoxes[2].a("" + chucNangNew.pointMp);
                    chucNangNew.textBoxes[3].a("" + chucNangNew.pointHp);
                    mTextBox textBox = chucNangNew.textBoxes[0];
                    textBox.n = num = chucNangNew.pointSucManh;
                    textBox = chucNangNew.textBoxes[1];
                    textBox.n = num = chucNangNew.pointChakra;
                    textBox = chucNangNew.textBoxes[2];
                    textBox.n = num = chucNangNew.pointMp;
                    textBox = chucNangNew.textBoxes[3];
                    textBox.n = num = chucNangNew.pointHp;
                    chucNangNew.isMess = true;
                    break;
                }
                case 2: {
                    byte num = m.readByte();
                    if (!(var2 instanceof TabTroThu)) break;
                    TabTroThu chucNangNew = (TabTroThu) var2;
                    switch (num) {
                        case 0:
                            chucNangNew.readSkills(m);
                    }
                    break;
                }
                case 4: {
                    byte action = m.readByte();
                    if (!(var2 instanceof TabTroThu)) break;
                    TabTroThu chucNangNew = (TabTroThu) var2;
                    chucNangNew._char.infoPhanThan.type = action;
                    chucNangNew.numAction = action;
                    break;
                }
                case 5: {
                    if (Char.gI().deTu == null) return;
                    int s_point = m.readByte();
                    for (int i = 0; i < s_point; i++) {
                        Char.gI().deTu.infoPhanThan.pointRen[i] = m.readInt();
                    }
                    break;
                }
                case 6: {
                    if (Char.gI().deTu == null) break;
                    Char.gI().deTu.exp = m.readLong();
                    if (!(GameSrc.gI().H() instanceof TabTroThu)) break;
                    ((TabTroThu) GameSrc.gI().H())._char.exp = Char.gI().deTu.exp;
                    break;
                }
                case 8: {
                    if (!(var2 instanceof TabTroThu)) break;
                    TabTroThu chucNangNew = (TabTroThu) var2;
                    chucNangNew.skillsViThu = new SkillClan[6];
                    byte size = m.readByte();
                    for (byte i = 0; i < size; i = (byte) (i + 1)) {
                        byte id = m.readByte();
                        byte level = m.readByte();
                        if (i >= 6 || id < 0) continue;
                        SkillClan var7 = DataCenter.gI().getSkillVT(id);
                        var7 = var7.a();
                        var7.a(level);
                        chucNangNew.skillsViThu[i] = var7;
                    }
                    break;
                }
                case 100: {
                    int size = m.readShort();
                    for (int index = 0; index < size; index++) {
                        GameSrc.gI().addChar(m);
                    }
                    break;
                }
                case 101: {
                    Item itemNew = new Item();
                    itemNew.read(m);
                    Char.gI().h((int) 0)[itemNew.index] = itemNew;
                    if (!(var2 instanceof RenTrangBi)) break;
                    RenTrangBi tabRen = (RenTrangBi) var2;
                    tabRen.itemTrangBi[0] = itemNew;
                    tabRen.d = 200;
                    for (int i = 0; i < 3; i++) {
                        if (tabRen.nguyenLieu[i] == null) continue;
                        if (Char.gI().arrItemBag[tabRen.nguyenLieu[i].index] == null
                                || Char.gI().arrItemBag[tabRen.nguyenLieu[i].index].id != tabRen.nguyenLieu[i].id) {
                            tabRen.nguyenLieu[i] = null;
                            continue;
                        }
                        tabRen.nguyenLieu[i].setAmount(Char.gI().arrItemBag[tabRen.nguyenLieu[i].index].getAmount());
                    }
                    tabRen.manhTB = null;
                    tabRen.khoangThach = null;
                    tabRen.nuocTinhLuyen = null;
                    break;
                }
            }
        } catch (Exception e) {
            Utlis.println(e);
        }
    }
}
