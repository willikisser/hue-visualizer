pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    val sonarqubePluginVersion: String by settings
    val sonarqubePluginId: String by settings
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
        id(sonarqubePluginId) version sonarqubePluginVersion
    }
}
rootProject.name="hue-visualizer"
