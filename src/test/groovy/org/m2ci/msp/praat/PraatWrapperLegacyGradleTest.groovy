package org.m2ci.msp.praat

import org.gradle.testkit.runner.GradleRunner
import org.testng.annotations.Test

class PraatWrapperLegacyGradleTest {

    @Test
    void 'Gradle v6.1.1 cannot apply plugin'() {
        def projectDir = File.createTempDir()
        new File(projectDir, 'build.gradle').withWriter {
            def resourceStream = this.class.getResourceAsStream('build.gradle')
            assert resourceStream
            it << resourceStream
        }
        def gradle = GradleRunner.create().withGradleVersion('6.1.1').withProjectDir(projectDir).withPluginClasspath()
        gradle.buildAndFail()
    }

    @Test
    void 'Gradle v6.2 can apply plugin'() {
        def projectDir = File.createTempDir()
        new File(projectDir, 'build.gradle').withWriter {
            def resourceStream = this.class.getResourceAsStream('build.gradle')
            assert resourceStream
            it << resourceStream
        }
        def gradle = GradleRunner.create().withGradleVersion('6.2').withProjectDir(projectDir).withPluginClasspath()
        gradle.build()
    }
}
