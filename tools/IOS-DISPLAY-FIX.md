# iOS build 2: orientation and missing graphics

- Lock both the RoboVM view controller and Info.plist to landscape; require full screen on iPad.
- Update canvas, camera, viewport and HUD dimensions after an iOS size change without recreating the login/session.
- Keep SpriteBatch and ShapeRenderer in separate begin/end passes. The previous overlap reproduces black sprites after the HUD pass in a real offscreen OpenGL context.
- Check loader queues before waiting. Previously, jobs queued before a worker began waiting could sleep for one hour after a lost notification.
- Retry placeholder icons after failed loads, with one pending request per image and a five second retry delay.
- Decode the existing bundled resource/a background when the configured JPEG is absent; checking the cached image no longer uploads textures from the background loader thread.
- Use ClientConfig.SERVER_LIST_URL instead of the obsolete hardcoded URL, which returned HTTP 404 during verification.

Verification performed on Windows:

- Gradle :ios:compileJava compiled core and the iOS launcher.
- ImageRequestGateCheck: slow-download deduplication, retry delay, cache eviction and 20 simultaneous requests.
- WorkerWakeupCheck: real cache/network Runnable classes process jobs queued before startup, without a notification.
- RenderRegressionCheck: real offscreen OpenGL draws sprites before/after a line and HUD/world camera changes; downloads and decodes an icon from the configured game data server.
- RenderRegressionCheck with the `legacy` argument reproduces a black sprite at x=48; the fixed path passes.

The offscreen test does not log into a game account or replace testing a signed build on a physical iPhone. GitHub Actions builds the unsigned arm64 IPA on macOS/Xcode.
