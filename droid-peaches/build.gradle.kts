plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	id("com.github.johnrengelman.shadow") version "8.1.1"
	id("maven-publish")
}

android {
	namespace = "com.example.mytestlibrary"
	compileSdk = 35

	defaultConfig {
		minSdk = 24

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
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

publishing {

	publications {
		create<MavenPublication>("release") {
			afterEvaluate {
				from(components["release"])
			}
			groupId = "io.github.vlad-buhaescu-qoob"     // Customize as needed
			artifactId = "droid-peaches"    // Name of your library
			version = "1.0.1"           // Library version

			pom {
				name.set("Test maven deployment") // The name of the library
				description.set("Tests all sorts of aspects regarding maven deployment")
				inceptionYear.set("2025")
				url.set("https://github.com/vlad-buhaescu-qoob/droid-peaches")

				licenses {
					license {
						name.set("Apache License 2.0")
						url.set("http://www.apache.org/licenses/LICENSE-2.0")
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
					connection.set("scm:git:https://github.com/vlad-buhaescu-qoob/droid-peaches.git")
					developerConnection.set("scm:git:ssh://github.com/vlad-buhaescu-qoob/droid-peaches.git")
					url.set("https://github.com/vlad-buhaescu-qoob/droid-peaches.git")
				}
			}

		}
	}
	repositories {
		maven {
			url = uri("https://github.com/vlad-buhaescu-qoob/droid-peaches") // Replace with your Maven repo URL
			credentials {
				username = "vlad-buhaescu-qoob"
				password =  project.findProperty("mySecretPassword") as String? ?: "defaultPassword"
			}
		}
	}
}

