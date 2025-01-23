import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	id("com.github.johnrengelman.shadow") version "8.1.1"
	id("maven-publish")
	id("com.vanniktech.maven.publish") version "0.30.0"
}

android {
	namespace = "com.example.mytestlibrary"
	compileSdk = 35

	defaultConfig {
		minSdk = 24

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
		consumerProguardFiles("proguard-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
	buildFeatures {
		prefab = true
	}

	afterEvaluate {
		configurations.named("releaseRuntimeClasspath") {
			extendsFrom(configurations.implementation.get())
		}
		configurations.named("debugRuntimeClasspath") {
			extendsFrom(configurations.implementation.get())
		}
	}

}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)

	api(libs.okhttp)
}

mavenPublishing {
	coordinates(
		groupId = "io.github.vlad-buhaescu-qoob",
		artifactId = "droid-peaches",
		version = "1.0.7"
	)

	pom {
		name.set("Test maven deployment") // The name of the library
		description.set("Tests all sorts of aspects regarding maven deployment")
		inceptionYear.set("2025")
		url.set("https://github.com/vlad-buhaescu-qoob/droid-peaches")

		licenses {
			license {
				name.set("MIT")
				url.set("https://opensource.org/licenses/MIT")
			}
		}

		developers {
			developer {
				id.set("vlad-buhaescu-qoob")
				name.set("Vlad")
				email.set("vlad.buhaescu@qoobiss.com") // Your email
			}
		}

		scm {
			url.set("https://github.com/vlad-buhaescu-qoob/droid-peaches")
		}
	}

	publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)


	// Enable GPG signing for all publications
	signAllPublications()
}
