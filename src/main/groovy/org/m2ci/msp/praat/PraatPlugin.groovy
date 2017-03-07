package org.m2ci.msp.praat

import org.gradle.api.*

class PraatPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('praat', type: PraatConfig)
    }
}
