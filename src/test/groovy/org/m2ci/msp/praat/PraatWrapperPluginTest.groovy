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
        def resources = [
                'build.gradle': 'build.gradle',
                'script.praat': null
        ]
        switch (OperatingSystem.current()) {
            case { it.isMacOsX() }:
                resources.'script.praat' = 'script_mac.praat'
                break
            case { it.isLinux() }:
                resources.'script.praat' = 'script_linux.praat'
                break
            case { it.isWindows() }:
                resources.'script.praat' = 'script_windows.praat'
                break
        }
        resources.each { fileName, resourceName ->
            def stream = this.getClass().getResourceAsStream(resourceName)
            assert stream?.available(): "Could not load $resourceName"
            def resourceFile = new File(projectDir, fileName)
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
