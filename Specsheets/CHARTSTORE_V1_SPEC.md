# Chartstore V1
- /{unique ID}
- - clientstore.json or serverstore.json: stores chart identifier and other useful metadata for means of identifying and updating a chart
- - chartinfo.json: stores information like available difficulties and their authors, included themes and other chart metadata [spec pending]
- - assets/
- - - audio.opus
- - - thumbnail.png
- - - banner.png
- - - poster.png
- - - mv.mp4
- - themes[?]/ # Optional directory, will only be present if chartinfo.json configures it, used for custom themes that aren't separately on the server
- - - {Theme} [Spec Pending]
- - charts/
- - - {Chart}: chart or charts as defined in the NECCHART spec

## This spec assumes non-destructive decompression of archives, and is comparatively inefficient and slow.
## This is for game scope storage, not archive construction
## Themes may be transferred to the Theme Store instead of being stored per-chart
