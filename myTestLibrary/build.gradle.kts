plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	id("com.github.johnrengelman.shadow") version "8.1.1"
}

android {
	namespace = "com.example.mytestlibrary"
	compileSdk = 34

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

//// Shadow plugin task for creating a fat AAR
//tasks.register<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
//	relocate("okhttp3", "com.example.mytestlibrary.shaded.okhttp3")
//}

tasks.register<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
	archiveClassifier.set("shadow")
	relocate("okhttp3", "com.example.mylibrary.shaded.okhttp3") // Adjust this to your namespace
	configurations = listOf(project.configurations.getByName("releaseRuntimeClasspath"))
}

tasks.register<Copy>("bundleFatAar") {
	dependsOn("shadowJar", "bundleReleaseAar")
	from(tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar").get().archiveFile.get().asFile)
	into("$buildDir/outputs/fat-aar")
	rename { "my-library-fat.aar" }
}

//tasks.register<Copy>("bundleFatAar") {
//	dependsOn("shadowJar")
//
//	// Use the output file from the shadowJar task
//	from(tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar").get().outputs.files.singleFile) {
//		// Include the Shadow JAR file (if needed)
//		include("*.jar")
//	}
//
//	into("$buildDir/outputs/fat-aar") // Output directory for the fat AAR
//	rename { "my-library-fat.aar" }  // Rename the output file
//}