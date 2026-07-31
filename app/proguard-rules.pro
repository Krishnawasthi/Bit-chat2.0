# Indian Mesh - ProGuard Rules

# 1. Keep Room Database Entities and DAOs
-keep class com.indianmesh.database.entity.** { *; }
-keep class com.indianmesh.database.dao.** { *; }
-keepclassmembers class * extends androidx.room.RoomDatabase {
    <init>(...);
}

# 2. Keep Cryptographic Libraries (BouncyCastle)
-keep class org.bouncycastle.** { *; }
-dontwarn org.bouncycastle.**

# 3. Keep Hilt/Dagger generated code
-keep class * extends dagger.internal.Factory
-keep class * extends dagger.internal.MembersInjector
-keep @dagger.Module class *
-keep @dagger.hilt.InstallIn class *
-keep @dagger.hilt.android.AndroidEntryPoint class *
-keep @dagger.hilt.android.HiltAndroidApp class *

# 4. Keep Jetpack Compose 
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# 5. Keep Core Models
-keep class com.indianmesh.core.models.** { *; }
