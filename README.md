[![Build Status](https://travis-ci.org/m2ci-msp/gradle-praat-wrapper-plugin.svg?branch=master)](https://travis-ci.org/m2ci-msp/gradle-praat-wrapper-plugin)

Gradle Praat Wrapper Plugin
===========================

A low-level Gradle plugin that provides [Praat](http://praat.org/) on the classpath

Usage
-----

See https://plugins.gradle.org/plugin/org.m2ci.msp.praat-wrapper

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
    id "org.m2ci.msp.praat-wrapper" version "0.4"
}

task runPraatScript(type: Exec) {
    dependsOn praat
    commandLine praat.binary, 'script.praat'
    doFirst {
        file('script.praat').text = "echo This is Praat 'praatVersion\$' running via Gradle $gradle.gradleVersion"
    }
}

EOF
$ gradle -q runPraatScript
This is Praat 5.4.17 running via Gradle 3.4.1
```
