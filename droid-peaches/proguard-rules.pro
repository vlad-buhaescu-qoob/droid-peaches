# 1️⃣ Keep the FoodApp class and its methods (including Composables)
-keep,public class com.example.mytestlibrary.FoodApp { *; }

# 2️⃣ Keep only Composable methods inside FoodApp
-keepclassmembers class com.example.mytestlibrary.** {
    @androidx.compose.runtime.Composable public *;
}

-keep,allowobfuscation class com.example.mytestlibrary.** { *; }

# 6️⃣ Keep StringConcatFactory (Fix Java 8+ Issues)
-keep class java.lang.invoke.StringConcatFactory { *; }
-keep class java.lang.invoke.** { *; }
-dontwarn java.lang.invoke.**

-keepclassmembers class ** {
    @androidx.compose.runtime.Composable public *;
}

# 3️⃣ Keep the main FoodApp class (if it's located elsewhere)
-keep class com.example.FoodApp { *; }

# 4️⃣ Allow obfuscation for all other classes
-keep,allowobfuscation class com.example.** { *; }


