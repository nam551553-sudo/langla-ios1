# LangLa iOS1

IPA được build từ dự án ios1 hiện tại.

Build 2 khóa màn hình ngang và sửa luồng tải/vẽ ảnh sau đăng nhập. Xem tools/IOS-DISPLAY-FIX.md để biết thay đổi và kiểm tra.

Vào Actions → Build unsigned IPA → Run workflow. Khi thành công, tải artifact LangLa-iOS1-build2-unsigned rồi giải nén file .ipa. Bản IPA chưa ký cần được ký trước khi cài lên iPhone.

Cấu hình: LibGDX 1.12.1, RoboVM 2.3.23, Gradle 8.7, Java 17, Xcode 16.4 trên macOS 15; arm64.
