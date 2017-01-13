import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

class PraatExec extends DefaultTask {
    @Input
    String script

    @TaskAction
    void exec() {
        def scriptFile = project.file("$temporaryDir/script.praat")
        scriptFile.text = script
        project.exec {
            commandLine 'praat', '--run', scriptFile
        }
    }
}
