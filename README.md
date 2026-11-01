# LangLa iOS1

Dự án iOS riêng được lấy từ mã nguồn ios1 trên máy, không sử dụng mã nguồn repository langla-ios cũ.

Build IPA chưa ký: mở Actions → Build unsigned IPA → Run workflow. Khi thành công, tải artifact LangLa-unsigned-IPA rồi giải nén để lấy file .ipa.

Cấu hình: LibGDX 1.12.1, RoboVM 2.3.23, Gradle 8.7, Java 17, Xcode 16.4 trên macOS 15; kiến trúc thiết bị arm64, bỏ ký mã.

IPA chưa ký cần được ký trước khi cài lên iPhone thông thường.
