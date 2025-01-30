# Keep public API
-keep public class com.example.** { *; }

# Obfuscate everything else
-keepclassmembers class com.example.** { *; }

# Keep FoodApp() so it remains public for client projects
-keep class com.example.FoodApp { *; }

# Remove unused classes, fields, and methods
-dontwarn
-optimizations !code/simplification/arithmetic