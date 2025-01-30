import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	id("com.github.johnrengelman.shadow") version "8.1.1"
	id("maven-publish")
	id("com.vanniktech.maven.publish") version "0.30.0"
	alias(libs.plugins.compose.compiler)
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
		isCoreLibraryDesugaringEnabled = true
	}
	kotlinOptions {
		jvmTarget = "11"
	}
	buildFeatures {
		prefab = true
		compose = true
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
//	implementation("com.android.tools:r8:8.0.25") // Latest R8

	api(libs.okhttp)

	coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
//	implementation(project(":droid-peaches"))
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	implementation("androidx.navigation:navigation-compose:2.7.6")
}

mavenPublishing {
	coordinates(
		groupId = "io.github.vlad-buhaescu-qoob",
		artifactId = "droid-peaches",
		version = "1.0.20"
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
