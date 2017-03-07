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
        def binaryName = 'praat'
        switch (OperatingSystem.current()) {
            case { it.isMacOsX() }:
                binaryName += '-mac'
                break
            case { it.isLinux() }:
                binaryName += '-linux'
                break
            case { it.isWindows() }:
                // detect architecture:
                binaryName = System.getenv("ProgramFiles(x86)") ? 'praat-windows64.exe' : 'praat-windows.exe'
                break
            default:
                break
        }
        binary = binary ?: project.file("$temporaryDir/$binaryName")
    }

    @TaskAction
    void extract() {
        binary.withOutputStream { stream ->
            stream << this.getClass().getResourceAsStream("/org/praat/$binary.name")
        }
        binary.executable = true
    }
}
