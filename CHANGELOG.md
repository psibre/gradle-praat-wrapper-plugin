Gradle Praat Wrapper Plugin
===========================

[Unreleased]
------------

### Changed

- Rename native plugins and internally remap groups to facilitate transitive downstream plugin resolution
- Downgrade provided Praat to v5.4.17 (the last version that runs on Ubuntu 12.04)
- Build with Gradle v3.4.1

[v0.3] - 2017-03-09
-------------------

### Added

- Common base plugin, extended by native plugins

### Fixed

- Restored VCS URL in plugin metadata

[v0.2] - 2017-02-17
-------------------

### Added

- Documentation (README, LICENSE, CHANGELOG)
- Support for Windows (64-bit)
- provide Praat version as `praat.version` task property

[v0.1] - 2017-02-16
-------------------

### Initial release

Providing Praat v5.4.22 for
- macOs (64-bit)
- Linux (64-bit)
- Windows (32-bit)

[Unreleased]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.3...HEAD
[v0.3]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.2...v0.3
[v0.2]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.1...v0.2
[v0.1]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/tree/v0.1
