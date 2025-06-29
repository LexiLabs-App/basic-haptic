import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
}

dependencies { kover(project(":basic-haptic")) }

buildscript {
    plugins { alias(libs.plugins.maven.publish)}
    dependencies { classpath(libs.dokka.base) }
}


allprojects {
    group = "app.lexilabs.basic"
    version = rootProject.libs.versions.haptic.get()

    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "com.vanniktech.maven.publish")

//    val javadocJar = tasks.register<Jar>("javadocJar") {
//        dependsOn(tasks.dokkaHtml)
//        archiveClassifier.set("javadoc")
//        from("${layout.buildDirectory}/dokka")
//    }

    /** dokka generation **/
    tasks.register<Delete>("clearDokkaHtml") {
        delete("${projectDir.parent}/docs")
    }
    tasks.withType<DokkaTask>().configureEach{
        pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
            dependsOn("clearDokkaHtml")
            outputDirectory = file("${projectDir.parent}/docs")
            moduleName = project.name
            moduleVersion = project.version.toString()
            customAssets = listOf(file("${projectDir.parent}/images/logo-icon.svg"))
            // Need to create a cool looking theme at some point
            //customStyleSheets = listOf(file("${projectDir.parent}/dokka/styles.css"))
            footerMessage = "(c) 2025 LexiLabs"
            failOnWarning = false
            suppressObviousFunctions = true
            suppressInheritedMembers = false
            offlineMode = false
        }
    }

    extensions.configure<MavenPublishBaseExtension> {

        mavenPublishing {
            publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

            signAllPublications()
            coordinates(group.toString(), project.name, version.toString())
            pom {
                name.set("Basic")
                description.set("Easily integrate vibration into your Kotlin Multiplatform Mobile (KMP / KMM) project")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://raw.githubusercontent.com/LexiLabs-App/basic-haptic/refs/heads/main/LICENSE")
                    }
                }
                url.set("https://github.com/LexiLabs-App/basic-haptic")
                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/LexiLabs-App/basic-haptic/issues")
                }
                scm {
                    connection.set("https://github.com/LexiLabs-App/basic-haptic.git")
                    url.set("https://github.com/LexiLabs-App/basic-haptic")
                }
                developers {
                    developer {
                        id.set("rjamison")
                        name.set("Robert Jamison")
                        email.set("rjamison@lexilabs.app")
                        url.set("https://haptic.basic.lexilabs.app")
                    }
                }
            }
        }
    }
}
