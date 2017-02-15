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

    @DataProvider
    Object[][] testTaskNames() {
        [
                'help',
                'hasPlugin',
                'hasPraat',
                'praatTaskHasPath',
                'runPraat',
                'runPraatScript',
                'runPraatListScript'
        ].collate(1)
    }

    @Test(dataProvider = 'testTaskNames')
    void testTask(taskName) {
        def result = gradle.withArguments(taskName).build()
        println result.output
        assert result.task(":$taskName").outcome == SUCCESS
    }
}
