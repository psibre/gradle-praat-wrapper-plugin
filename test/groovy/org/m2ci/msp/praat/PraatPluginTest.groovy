package org.m2ci.msp.praat

import org.gradle.testkit.runner.GradleRunner
import org.testng.annotations.*

import static org.gradle.testkit.runner.TaskOutcome.*

class PraatPluginTest {

    File projectDir
    File buildScriptFile
    GradleRunner gradle

    @BeforeClass
    void setUp() {
        projectDir = File.createTempDir()
        def buildScript = this.getClass().getResourceAsStream('build.gradle').text
        assert buildScript
        buildScriptFile = new File(projectDir, 'build.gradle')
        buildScriptFile.text = buildScript
        gradle = GradleRunner.create().withProjectDir(projectDir).withPluginClasspath()
    }

    @Test
    void testConfigure() {
        def result = gradle.build()
        assert result.task(':help').outcome == SUCCESS
    }

    @Test
    void testHasPlugin() {
        def result = gradle.withArguments('hasPlugin').build()
        assert result.task(':hasPlugin').outcome == SUCCESS
    }
}
