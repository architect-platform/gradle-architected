plugins {
	kotlin("jvm") version "1.9.25"
	`maven-publish`
}
group = "io.github.architectplatform.plugins"
version = "1.6.3"

java {
	sourceCompatibility = JavaVersion.toVersion("17")
}

kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/architect-platform/gradle-architected")
			credentials {
				username = System.getenv("GITHUB_USER") ?: project.findProperty("githubUser") as String? ?: "github-actions"
				password = System.getenv("REGISTRY_TOKEN") ?: System.getenv("GITHUB_TOKEN") ?: project.findProperty("githubToken") as String?
			}
		}
	}
}

dependencies {
	implementation("io.github.architectplatform:architect-api:1.14.0")
}

publishing {
	publications {
		create<MavenPublication>("gpr") {
			from(components["java"])
			artifactId = "gradle-architected"
			pom {
				name.set("Gradle Architected")
				description.set("An Architect plugin for Gradle")
				url.set("https://github.com/architect-platform/gradle-architected")
				licenses {
					license {
						name.set("Apache-2.0")
						url.set("https://www.apache.org/licenses/MIT-2.0")
					}
				}
				developers {
					developer {
						id.set("alemazzo")
						name.set("Alessandro Mazzoli")
					}
				}
			}
		}
	}
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/architect-platform/gradle-architected")
			credentials {
				username = System.getenv("GITHUB_USER") ?: project.findProperty("githubUser") as String? ?: "github-actions"
				password = System.getenv("REGISTRY_TOKEN") ?: System.getenv("GITHUB_TOKEN") ?: project.findProperty("githubToken") as String?
			}
		}
	}
}


