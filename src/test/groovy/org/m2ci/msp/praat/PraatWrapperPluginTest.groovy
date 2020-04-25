package org.m2ci.msp.praat

import org.gradle.internal.os.OperatingSystem
import org.gradle.testkit.runner.GradleRunner
import org.testng.annotations.BeforeClass
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.gradle.testkit.runner.TaskOutcome.UP_TO_DATE

class PraatWrapperPluginTest {

    GradleRunner gradle

    @BeforeClass
    void setUp() {
        def projectDir = File.createTempDir()
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
            new File(projectDir, fileName).withWriter {
                def resourceStream = this.class.getResourceAsStream(resourceName)
                assert resourceStream
                it << resourceStream
            }
        }
        gradle = GradleRunner.create().withProjectDir(projectDir).withPluginClasspath()
    }

    @DataProvider
    Object[][] taskNames() {
        // task name to run, and whether to chase it with a test task named "testName"
        [
                ['hasPlugin', false],
                ['praat', true],
                ['canRunPraat', false],
                ['testPraatVersion', false]
        ]
    }

    @Test(dataProvider = 'taskNames')
    void testTasks(String taskName, boolean runTestTask) {
        def result = gradle.withArguments(taskName).build()
        println result.output
        assert result.task(":$taskName").outcome in [SUCCESS, UP_TO_DATE]
        if (runTestTask) {
            def testTaskName = 'test' + taskName.capitalize()
            result = gradle.withArguments(testTaskName).build()
            println result.output
            assert result.task(":$taskName").outcome == UP_TO_DATE
            assert result.task(":$testTaskName").outcome == SUCCESS
        }
    }
}
