package beatdz;

public class LangLa_register extends LangLa_fo {
    private mTextBox username;
    private mTextBox password;
    private mTextBox repassword;
    private mTextBox password2;
    private mTextBox phone;
    private mTextBox ref;

    public LangLa_register(MainScreen screen) {
        this.p = screen;
        this.a(300, 270);
        this.username = this.a(128, 40, 150, "", this, 0);
        this.password = this.a(128, 68, 150, "", this, 1);
        this.repassword = this.a(128, 96, 150, "", this, 1);
        this.password2 = this.a(128, 124, 150, "", this, 1);
        this.phone = this.a(128, 152, 150, "", this, 0);
        this.ref = this.a(128, 180, 150, "", this, 0);
        this.username.a = 24;
        this.password.a = 32;
        this.repassword.a = 32;
        this.password2.a = 32;
        this.phone.a = 24;
        this.ref.a = 24;
        this.b("Xác nhận", 1001);
        this.c("Đóng", 1002);
    }

    public void a(Graphics g) {
        this.a(g, this.cx, this.cy, this.width, this.height, "Đăng ký tài khoản", (byte) 2, true);
        mFont.a(mFont.d, g, "Tài khoản:", 18, 50, 0, -1);
        mFont.a(mFont.d, g, "Mật khẩu:", 18, 78, 0, -1);
        mFont.a(mFont.d, g, "Nhập lại MK:", 18, 106, 0, -1);
        mFont.a(mFont.d, g, "Mật khẩu 2:", 18, 134, 0, -1);
        mFont.a(mFont.d, g, "Số điện thoại:", 18, 162, 0, -1);
        mFont.a(mFont.d, g, "Người giới thiệu:", 18, 190, 0, -1);
        this.b(g);
    }

    public void a(LangLa_jz action, int x, int y) {
        switch (action.b) {
            case 1001:
                sendRegister();
                return;
            case 1002:
            case -7:
                this.J();
                return;
            default:
        }
    }

    public void a(int id, Object value) {
    }

    public void a(int id, Object value, LangLa_cy source) {
    }

    public void b() {
    }

    private void sendRegister() {
        Message msg = null;
        try {
            String user = safe(this.username.f()).trim().toLowerCase();
            String pass = safe(this.password.f()).trim().toLowerCase();
            String repass = safe(this.repassword.f()).trim().toLowerCase();
            String pass2 = safe(this.password2.f()).trim().toLowerCase();
            String phoneNumber = safe(this.phone.f()).trim();
            String refName = safe(this.ref.f()).trim().toLowerCase();
            String error = validate(user, pass, repass, pass2, phoneNumber);
            if (error != null) {
                this.p.showMessage(error, -65536);
                return;
            }
            msg = Message.c((byte) -125);
            msg.writeUTF(user);
            msg.writeUTF(pass);
            msg.writeUTF(repass);
            msg.writeUTF(pass2);
            msg.writeUTF(phoneNumber);
            msg.writeUTF(refName);
            msg.createSession();
            this.p.showMessage("Đang gửi đăng ký...", -1);
        } catch (Exception ex) {
            Utlis.println(ex);
        } finally {
            if (msg != null) {
                msg.close();
            }
        }
    }

    private String validate(String user, String pass, String repass, String pass2, String phoneNumber) {
        if (user.length() == 0) {
            return "Không được để trống tài khoản.";
        }
        if (pass.length() == 0) {
            return "Không được để trống mật khẩu.";
        }
        if (repass.length() == 0) {
            return "Không được để trống nhập lại mật khẩu.";
        }
        if (pass2.length() == 0) {
            return "Không được để trống mật khẩu cấp 2.";
        }
        if (phoneNumber.length() == 0) {
            return "Không được để trống số điện thoại.";
        }
        if (pass.length() < 5) {
            return "Mật khẩu tối thiểu 5 ký tự.";
        }
        if (!pass.equals(repass)) {
            return "Hai mật khẩu không khớp.";
        }
        return null;
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }
}
