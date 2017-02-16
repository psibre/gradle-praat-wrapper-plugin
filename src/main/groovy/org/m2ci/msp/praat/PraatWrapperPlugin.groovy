package org.m2ci.msp.praat

import org.gradle.api.*

class PraatWrapperPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('praat', type: ExtractPraat)
    }
}
