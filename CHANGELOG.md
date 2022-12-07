Gradle Praat Wrapper Plugin
===========================

[v0.7.0] - 2022-12-07
---------------------

### Added

- Testing on Java 17

### Removed

- Testing on Java 9, 10, 12, 13, 14
- Reliance on Bintray/JCenter

### Changed

- Migrated from Travis CI to GitHub Actions
- Build with Gradle v7.5.1
- Upgraded test dependency
- Capture, log `hdiutil` output on macOS
- Migrated publishing from OJO to OSSRH
- Changed group ID to `io.github.m2ci-msp`
- [all changes since v0.6]

[v0.6] - 2020-04-28
-------------------

### Fixed

- Custom repository for Praat
- Groovy source publishing

### Added

- Testing on OpenJDK 9 through 14
- Testing on macOS High Sierra and Mojave

### Removed

- Testing on Oracle JDK

### Changed

- **Require Gradle v6.2 or later!**
- Build with Gradle v6.3
- Target minimum Java version 8
- Upgrade dependencies
- [all changes since v0.5.2]

[v0.5.2] - 2018-11-05
---------------------

### Changed

- Build with Gradle v4.10.2
- [all changes since v0.5.1]

### Fixed

- Race condition when running `praat` task in parallel on OSX

[v0.5.1] - 2017-11-21
---------------------

### Added

- Test on Java 9

### Changed

- Update provided Praat to v6.0.30
- **Note:** running scripts now requires `--run` argument
- [all changes since v0.5]

[v0.5] - 2017-11-20
-------------------

### Changed

- Restructured to remove platform-specific wrapper plugins
- OS-specific Praat resolved as dependency by project at runtime, instead of being provided on buildscript classpath
- Build with Gradle v4.3.1
- CI Testing on OSX as well as Linux
- [all changes since v0.4]

[v0.4] - 2017-03-13
-------------------

### Changed

- Rename native plugins and internally remap groups to facilitate transitive downstream plugin resolution
- Downgrade provided Praat to v5.4.17 (the last version that runs on Ubuntu 12.04)
- Build with Gradle v3.4.1
- [all changes since v0.3]

[v0.3] - 2017-03-09
-------------------

### Added

- Common base plugin, extended by native plugins

### Changed

- [all changes since v0.2]

### Fixed

- Restored VCS URL in plugin metadata

[v0.2] - 2017-02-17
-------------------

### Added

- Documentation (README, LICENSE, CHANGELOG)
- Support for Windows (64-bit)
- provide Praat version as `praat.version` task property

### Changed

- [all changes since v0.1]

[v0.1] - 2017-02-16
-------------------

### Initial release

Providing Praat v5.4.22 for
- Mac OSX (64-bit)
- Linux (64-bit)
- Windows (32-bit)

[v0.7.0]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.7.0
[all changes since v0.6]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.6...v0.7.0
[v0.6]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.6
[all changes since v0.5.2]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.5.2...v0.6
[v0.5.2]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.5.2
[all changes since v0.5.1]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.5.1...v0.5.2
[v0.5.1]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.5.1
[all changes since v0.5]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.5...v0.5.1
[v0.5]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.5
[all changes since v0.4]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.4...v0.5
[v0.4]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.4
[all changes since v0.3]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.3...v0.4
[v0.3]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.3
[all changes since v0.2]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.2...v0.3
[v0.2]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.2
[all changes since v0.1]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/compare/v0.1...v0.2
[v0.1]: https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/releases/tag/v0.1
