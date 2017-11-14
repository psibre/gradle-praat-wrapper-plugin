package org.m2ci.msp.praat

import org.gradle.api.*
import org.gradle.api.tasks.Copy
import org.gradle.internal.os.OperatingSystem

class PraatWrapperPlugin implements Plugin<Project> {
    void apply(Project project) {

        project.configurations.maybeCreate('praat')

        def praatVersion = this.getClass().getResourceAsStream('/org/praat/version.txt').text.trim()
        assert praatVersion
        def binary

        project.repositories {
            def praatVersionNoDots = praatVersion.replaceAll(~/\./, '')
            ivy {
                url 'http://www.fon.hum.uva.nl/praat/old/'
                layout 'pattern', {
                    artifact "$praatVersionNoDots/[module]${praatVersionNoDots}_[classifier].[ext]"
                }
            }
        }

        switch (OperatingSystem.current()) {
            case { it.isMacOsX() }:
                project.dependencies.add('praat', [group: 'org.praat', name: 'praat', version: praatVersion, classifier: 'mac64', ext: 'dmg'])
                binary = 'Praat'
                break
            case { it.isLinux() }:
                project.dependencies.add('praat', [group: 'org.praat', name: 'praat', version: praatVersion, classifier: 'linux64', ext: 'tar.gz'])
                binary = 'praat'
                break
            case { it.isWindows() && System.getenv("ProgramFiles(x86)") }:
                project.dependencies.add('praat', [group: 'org.praat', name: 'praatcon', version: praatVersion, classifier: 'win64', ext: 'zip'])
                binary = 'praatcon.exe'
                break
            case { it.isWindows() }:
                project.dependencies.add('praat', [group: 'org.praat', name: 'praatcon', version: praatVersion, classifier: 'win32', ext: 'zip'])
                binary = 'praatcon.exe'
                break
        }

        project.task('praat', type: Copy) {
            from project.configurations.findByName('praat')
            into "$project.buildDir/praat"
            ext.binary = project.file("$destinationDir/$binary")
            ext.version = praatVersion
            filesMatching('*.dmg') { dmgFileDetails ->
                project.exec {
                    commandLine 'hdiutil', 'attach', '-mountpoint', temporaryDir, dmgFileDetails.file
                }
                project.copy {
                    from "$temporaryDir/Praat.app/Contents/MacOS"
                    into destinationDir
                }
                project.exec {
                    commandLine 'hdiutil', 'detach', temporaryDir
                }
                dmgFileDetails.exclude()
            }
            filesMatching('*.tar.gz') { tarFileDetails ->
                project.copy {
                    from project.tarTree(tarFileDetails.file)
                    into destinationDir
                }
                tarFileDetails.exclude()
            }
            filesMatching('*.zip') { zipFileDetails ->
                project.copy {
                    from project.zipTree(zipFileDetails.file)
                    into destinationDir
                }
                zipFileDetails.exclude()
            }
        }
    }
}
