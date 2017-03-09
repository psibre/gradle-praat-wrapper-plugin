package org.m2ci.msp.praat

import org.gradle.api.*
import org.gradle.internal.os.OperatingSystem

import org.m2ci.msp.praat.linux.PraatWrapperLinuxPlugin
import org.m2ci.msp.praat.mac.PraatWrapperMacPlugin
import org.m2ci.msp.praat.windows.PraatWrapperWindowsPlugin
import org.m2ci.msp.praat.windows64.PraatWrapperWindows64Plugin

class PraatPlugin implements Plugin<Project> {
    void apply(Project project) {
        switch (OperatingSystem.current()) {
            case { it.isLinux() }:
                project.pluginManager.apply(PraatWrapperLinuxPlugin)
                break
            case { it.isMacOsX() }:
                project.pluginManager.apply(PraatWrapperMacPlugin)
                break
            case { it.isWindows() && System.getenv("ProgramFiles(x86)") }:
                project.pluginManager.apply(PraatWrapperWindows64Plugin)
                break
            case { it.isWindows() }:
                project.pluginManager.apply(PraatWrapperWindowsPlugin)
                break
            default:
                project.pluginManager.apply(PraatWrapperPlugin)
                break
        }
    }
}
