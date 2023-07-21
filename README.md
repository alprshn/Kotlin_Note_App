# Kotlin_Note_App V1


<h2>Documentation Dokka</h2>
[![Kotlin Beta](https://kotl.in/badges/beta.svg)](https://kotlinlang.org/docs/components-stability.html)
[![JetBrains official project](https://jb.gg/badges/official.svg)](https://github.com/JetBrains#jetbrains-on-github)
[![Maven Central](https://img.shields.io/maven-central/v/org.jetbrains.dokka/org.jetbrains.dokka.gradle.plugin?label=MavenCentral&logo=apache-maven)](https://search.maven.org/artifact/org.jetbrains.dokka/org.jetbrains.dokka.gradle.plugin)
[![Gradle Plugin](https://img.shields.io/gradle-plugin-portal/v/org.jetbrains.dokka?label=Gradle&logo=gradle)](https://plugins.gradle.org/plugin/org.jetbrains.dokka)
https://github.com/Kotlin/dokka
<h3>Setup Dokka</h3>

This will be written in build.gradle(Module:app)
```
plugins {
    id("org.jetbrains.dokka") version "1.8.20"
}
```

and to start it in terminal
```
 ./gradlew dokkaHtml  
```

