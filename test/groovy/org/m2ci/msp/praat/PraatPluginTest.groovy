package org.m2ci.msp.praat

import org.gradle.testkit.runner.GradleRunner
import org.testng.annotations.*

import static org.gradle.testkit.runner.TaskOutcome.*

class PraatPluginTest {

    File projectDir
    GradleRunner gradle

    @BeforeClass
    void setUp() {
        projectDir = File.createTempDir()
        ['build.gradle', 'script.praat'].each { resourceName ->
            def stream = this.getClass().getResourceAsStream(resourceName)
            assert stream?.available()
            def resourceFile = new File(projectDir, resourceName)
            resourceFile.withOutputStream {
                it << stream.bytes
            }
        }
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

    @Test
    void testHasPraat() {
        def result = gradle.withArguments('hasPraat').build()
        assert result.task(':hasPraat').outcome == SUCCESS
    }

    @Test
    void testRunPraat() {
        def result = gradle.withArguments('runPraat').build()
        println result.output
        assert result.task(':runPraat').outcome == SUCCESS
    }

    @Test
    void testRunPraatScript() {
        def result = gradle.withArguments('runPraatScript').build()
        println result.output
        assert result.task(':runPraatScript').outcome == SUCCESS
    }
}
