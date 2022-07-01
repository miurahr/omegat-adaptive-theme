plugins {
    java
    checkstyle
    distribution
    maven
    id("org.omegat.gradle") version "1.5.7"
    id("com.palantir.git-version") version "0.13.0" apply false
}

// handle case when build from source distribution
val dotgit = project.file(".git")
if (dotgit.exists()) {
    // calculate version string from git tag, hash and commit distance
    apply(plugin = "com.palantir.git-version")
    val versionDetails: groovy.lang.Closure<com.palantir.gradle.gitversion.VersionDetails> by extra
    val details = versionDetails()
    val baseVersion = details.lastTag.substring(1)
    if (details.isCleanTag) {  // release version
        version = baseVersion
    } else {  // snapshot version
        version = baseVersion + "-" + details.commitDistance + "-" + details.gitHash + "-SNAPSHOT"
    }
} else {
    println("Read version property from gradle.properties.")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

omegat {
    version = "5.7.1"
    pluginClass = "tokyo.northside.omegat.theme.AdaptiveTheme"
}

dependencies {
    packIntoJar("com.github.weisj:darklaf-core:3.0.0")
    packIntoJar("com.github.weisj:swing-dsl-laf-support:0.1.0")
}

checkstyle {
    isIgnoreFailures = true
    toolVersion = "7.1"
}

distributions {
    main {
        contents {
            from(tasks["jar"], "README.md", "COPYING", "CHANGELOG.md")
        }
    }
}
