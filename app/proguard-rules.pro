# Keep public API (classes and methods intended for clients)
-keep public class com.example.** {
    public *;
}

# Obfuscate internal classes and methods
-keep class com.example.internal.** { *; }