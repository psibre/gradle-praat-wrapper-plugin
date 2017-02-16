Gradle Praat Wrapper Plugin
===========================

A low-level Gradle plugin that provides [Praat](http://praat.org/) on the classpath

Usage
-----

The plugin comes in several flavors:

### macOS

See https://plugins.gradle.org/plugin/org.m2ci.msp.praat-wrapper-mac

### Linux

See https://plugins.gradle.org/plugin/org.m2ci.msp.praat-wrapper-linux

### Windows (32-bit)

See https://plugins.gradle.org/plugin/org.m2ci.msp.praat-wrapper-windows

### Windows (64-bit)

See https://plugins.gradle.org/plugin/org.m2ci.msp.praat-wrapper-windows64

Praat task
----------

Applying this plugin (in the correct flavor for your platform) creates a `praat` task that extracts the `praat` binary into its temporary directory.
It can then be used in other tasks.

### Example

```
$ cat > build.gradle << EOF

plugins {
    id "org.m2ci.msp.praat-wrapper-mac" version "0.1"
}

task runPraatScript(type: Exec) {
    dependsOn praat
    commandLine praat.binary, 'script.praat'
    doFirst {
        file('script.praat').text = """echo This is Praat 'praatVersion\$' running via Gradle $gradle.gradleVersion"""
    }
}

EOF
$ gradle -q runPraatScript
This is Praat 5.4.22 running via Gradle 3.3
```
