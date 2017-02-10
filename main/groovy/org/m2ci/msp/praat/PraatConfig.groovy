package org.m2ci.msp.praat

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

class PraatConfig extends DefaultTask {

    @Input
    String path = which('praat')

    @TaskAction
    void setup() {
        project.logger.lifecycle "Praat path: $path"
    }

    String which(String binary) {
        System.getenv('PATH').split(':').collect { new File(it, 'praat') }.find { it.canExecute() }
    }
}
