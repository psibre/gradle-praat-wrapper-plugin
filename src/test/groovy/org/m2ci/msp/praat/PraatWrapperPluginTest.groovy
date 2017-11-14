package org.m2ci.msp.praat

import org.gradle.internal.os.OperatingSystem
import org.gradle.testkit.runner.GradleRunner
import org.testng.annotations.*

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class PraatWrapperPluginTest {

    File projectDir
    GradleRunner gradle

    @BeforeClass
    void setUp() {
        projectDir = File.createTempDir()
        ['build.gradle', 'script.praat'].each { resourceName ->
            def stream = this.getClass().getResourceAsStream(resourceName)
            assert stream?.available(): "Could not load $resourceName"
            def resourceFile = new File(projectDir, resourceName)
            resourceFile.withOutputStream {
                it << stream.bytes
            }
        }
        gradle = GradleRunner.create().withProjectDir(projectDir).withPluginClasspath()
    }

    @Test
    void testHasPlugin() {
        def result = gradle.withArguments('hasPlugin').build()
        assert result.task(':hasPlugin').outcome == SUCCESS
    }

    @Test
    void testCanExtractPraat() {
        def result = gradle.withArguments('canExtractPraat').build()
        assert result.task(':canExtractPraat').outcome == SUCCESS
    }

    @Test
    void testCanRunPraat() {
        def result = gradle.withArguments('--quiet', 'canRunPraat').build()
        println result.output
        assert result.task(':canRunPraat').outcome == SUCCESS
        def expected = this.getClass().getResourceAsStream('/org/praat/version.txt').text.trim()
        def actual = result.output.trim()
        if (OperatingSystem.current().isWindows()) {
            actual = new File(projectDir, 'output.txt').text.trim()
            println actual
        }
        assert actual == expected
    }

    @Test
    void testPraatVersion() {
        def result = gradle.withArguments('testPraatVersion').build()
        assert result.task(':testPraatVersion').outcome == SUCCESS
    }
}