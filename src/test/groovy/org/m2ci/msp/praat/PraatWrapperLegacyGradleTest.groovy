package org.m2ci.msp.praat

import org.gradle.testkit.runner.GradleRunner
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

class PraatWrapperLegacyGradleTest {

    File projectDir

    @BeforeClass
    void setUp() {
        projectDir = File.createTempDir()
        new File(projectDir, 'build.gradle').withWriter {
            def resourceStream = this.class.getResourceAsStream('build.gradle')
            assert resourceStream
            it << resourceStream
        }
    }

    @Test
    void 'Gradle v6.1.1 cannot apply plugin'() {
        GradleRunner.create()
                .withProjectDir(projectDir)
                .withPluginClasspath()
                .withGradleVersion('6.1.1')
                .buildAndFail()
    }

    @Test
    void 'Gradle v6.2 can apply plugin'() {
        GradleRunner.create()
                .withProjectDir(projectDir)
                .withPluginClasspath()
                .withGradleVersion('6.2')
                .build()
    }
}
