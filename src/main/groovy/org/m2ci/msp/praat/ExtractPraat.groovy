package org.m2ci.msp.praat

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

class ExtractPraat extends DefaultTask {

    @OutputFile
    File binary

    ExtractPraat() {
        binary = binary ?: project.file("$temporaryDir/praat")
    }

    @TaskAction
    void extract() {
        binary.withOutputStream { stream ->
            stream << this.getClass().getResourceAsStream('/org/praat/praat')
        }
        binary.executable = true
    }
}
