# Kotlin_Note_App V1


<h2>Documentation Dokka</h2>
[![Gradle Plugin](https://img.shields.io/gradle-plugin-portal/v/org.jetbrains.dokka?label=Gradle&logo=gradle)](https://plugins.gradle.org/plugin/org.jetbrains.dokka)
[![Kotlin KDoc ](https://kotl.in/badges/beta.svg)]([https://kotlinlang.org/docs/components-stability.html](https://kotlinlang.org/docs/kotlin-doc.html))

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

