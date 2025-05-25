plugins {
	kotlin("jvm") version "1.9.25"
	`maven-publish`
}
group = "io.github.architectplatform.plugins"
version = "1.7.0"

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
}

dependencies {
	implementation("io.github.architectplatform:architect-api:1.14.0")
}


