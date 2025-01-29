# Keep public API
-keep public class com.example.** { *; }

# Obfuscate everything else
-keepclassmembers class com.example.** { *; }

# Remove unused classes, fields, and methods
-dontwarn
-optimizations !code/simplification/arithmetic