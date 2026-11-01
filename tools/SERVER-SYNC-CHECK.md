# Server synchronization and black-screen regression check

Status: Build 3 authorized by the user with missing-asset maps excluded from the acceptance check. Map 1 remains a known rendering failure. The map 1 rendering check fails because its background PNGs are absent from both supplied asset sources and the live image host. No replacement art or unrelated same-number icon was used.

## Changes

- Match current SRC_Server Char.writeMe, Char.write and Disciple.writeMap packet layouts, including six extension slots, optional book and disciple. Remove client-only wing/profession reads that shifted all following packet fields.
- Remove the profession tab while keeping the inventory tab selectable.
- Retain client icon CPU pixmaps needed to assemble the HUD and rebuild it after asynchronous image completion.
- Guard mob animation frame indices and synchronize SettingsTab initialization.
- Stop retrying permanent HTTP 4xx asset failures in the downloader; remove unrelated internet probes from retry handling.
- Log map and main-data decoding failures.

## Tests on Windows, 2026-11-01

The current local SRC_Server Java sources were compiled afresh (not its stale target/classes). Tests use its actual packet writers and actual data files with in-memory character/item fixtures. No live database, account login or iPhone was used.

- PASS: actual Char.writeMe packet consumed completely by the client.
- PASS: both equipment sets, bag, extension slot 5, optional book and disciple.
- PASS: three actual Char.write records preserve subsequent map/mob packet bytes.
- PASS: actual Zone.write packet initializes map geometry, other character, mob, NPC and HUD.
- PASS: map 0 offscreen OpenGL scene, 87,083 colored pixels, 66 decoded images; profession absent and inventory accessible.
- PASS: local HTTP fixture gets one 404 request and the following valid image downloads successfully.
- FAIL: map 1 scene has no decoded tree backgrounds; HUD and character render but the world stays black. The scene test exits with failure, rather than accepting HUD pixels as a complete map.

## Missing original assets

Expected logical map block dimensions (image zoom variants can be scaled):

| Path | Width | Height |
|---|---:|---:|
| iconTree/432.png | 820 | 820 |
| iconTree/434.png | 820 | 4 |
| iconTree/440.png | 148 | 109 |
| iconTree/441.png | 180 | 276 |

Checked supplied directories: xampp/htdocs/langlaapp/img and Desktop/htdocszzz/htdocs/img. The current host http://160.250.128.28/img/zoom2/iconTree/ returns HTTP 404 for these images. A chest icon named 432.png in another category is not this map background.

Build 3 includes this repair. The user explicitly requested an unsigned IPA while skipping the maps with missing images. This does not remove or block those maps in game, and they may still show a black background. No map PNGs were invented or substituted. Full map 1 rendering still requires the original missing assets and a repeat of ServerMapSceneCheck. The Windows Java compilation passed; physical iPhone login was not tested.