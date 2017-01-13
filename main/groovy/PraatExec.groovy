import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

class PraatExec extends DefaultTask {
    @Input
    String script

    @TaskAction
    void exec() {
        def scriptFile = project.file(script)
        if (!scriptFile.canRead() && !scriptFile.isFile()) {
            scriptFile = project.file("$temporaryDir/script.praat")
            scriptFile.text = script
        }
        assert scriptFile
        project.exec {
            commandLine 'praat', '--run', scriptFile
        }
    }
}
