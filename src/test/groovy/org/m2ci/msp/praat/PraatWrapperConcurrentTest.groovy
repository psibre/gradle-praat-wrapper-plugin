package org.m2ci.msp.praat

import org.gradle.testkit.runner.GradleRunner
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.gradle.testkit.runner.TaskOutcome.UP_TO_DATE

class PraatWrapperConcurrentTest {

    GradleRunner gradle

    @BeforeClass
    void setUp() {
        def rootProjectDir = File.createTempDir()
        new File(rootProjectDir, 'settings.gradle').withWriter { settings ->
            6.times { p ->
                def projectDir = new File(rootProjectDir, "proj_$p")
                settings.println "include '$projectDir.name'"
                projectDir.mkdirs()
                new File(projectDir, 'build.gradle').withWriter {
                    it << this.class.getResourceAsStream('build.gradle')
                }
            }
        }
        gradle = GradleRunner.create().withProjectDir(rootProjectDir).withPluginClasspath().forwardOutput()
    }

    @Test
    void testConcurrentExtraction() {
        def result = gradle.withArguments('testPraat', '--parallel').build()
        println result.output
        result.tasks.each { task ->
            assert task.outcome in [SUCCESS, UP_TO_DATE]
        }
    }
}
