[![CI](https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/actions/workflows/main.yml/badge.svg)](https://github.com/m2ci-msp/gradle-praat-wrapper-plugin/actions/workflows/main.yml)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)

Gradle Praat Wrapper Plugin
===========================

A low-level Gradle plugin that provides [Praat](http://praat.org/) v6.0.30 as a custom dependency

Usage
-----

See https://plugins.gradle.org/plugin/io.github.m2ci-msp.praat-wrapper

Note that Gradle v6.2 or higher is required.

Praat task
----------

Applying this plugin creates a `praat` task that downloads and extracts the Praat binary for the current OS into `$buildDir/praat`.
This path is also provided as the `praat.binary` property.
It can then be used in other tasks.

Note that the downloaded Praat package is cached by Gradle as a dependency.

### Example

```
$ cat > build.gradle << EOF

plugins {
    id "io.github.m2ci-msp.praat-wrapper" version "0.7.0"
}

task runPraatScript(type: Exec) {
    dependsOn praat
    commandLine praat.binary, '--run', 'script.praat'
    doFirst {
        file('script.praat').text = "echo This is Praat 'praatVersion\$' running via Gradle $gradle.gradleVersion"
    }
}

EOF
$ gradle -q runPraatScript
This is Praat 6.0.30 running via Gradle 6.3
```
