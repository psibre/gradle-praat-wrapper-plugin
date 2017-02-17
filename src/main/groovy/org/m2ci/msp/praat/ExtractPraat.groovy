package org.m2ci.msp.praat

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.gradle.internal.os.OperatingSystem

class ExtractPraat extends DefaultTask {

    @OutputFile
    File binary

    @Internal
    String version = this.getClass().getResourceAsStream('/org/praat/version.txt').text.trim()

    ExtractPraat() {
        def binaryName = OperatingSystem.current().isWindows() ? 'praat.exe' : 'praat'
        binary = binary ?: project.file("$temporaryDir/$binaryName")
    }

    @TaskAction
    void extract() {
        binary.withOutputStream { stream ->
            stream << this.getClass().getResourceAsStream('/org/praat/praat')
        }
        binary.executable = true
    }
}
