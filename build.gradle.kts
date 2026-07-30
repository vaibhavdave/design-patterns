plugins {
    java
}

allprojects {
    group = "com.designpatterns"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    // NOTE: the `libs` version-catalog accessor is only generated for a project's own build
    // script, not for scripts configured indirectly through subprojects {} / allprojects {}, so
    // the shared test dependencies here are declared with literal coordinates. Per-module
    // build.gradle.kts files (e.g. Spring dependencies) DO get the `libs` accessor and use it.
    dependencies {
        "testImplementation"(platform("org.junit:junit-bom:5.10.3"))
        "testImplementation"("org.junit.jupiter:junit-jupiter")
        "testImplementation"("org.assertj:assertj-core:3.26.3")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }
}
