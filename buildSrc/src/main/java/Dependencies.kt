import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.example.mydictionary"
    const val compile_sdk = 31
    const val min_sdk = 23
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    const val historyScreen = ":historyScreen"
}

object Versions {

    //Design
    const val appcompat = "1.4.1"
    const val material = "1.5.0"
    const val constraintlayout = "2.1.3"
    const val swiperefreshlayout = "1.1.0"
    const val recycleView = "1.2.1"

    //Kotlin
    const val stdlib = "1.6.10"
    const val core = "1.7.0"
    const val coroutines_version = "1.6.0"

    //Test
    const val junit = "4.+"
    const val ext_junit = "1.1.3"
    const val espressoCore = "3.4.0"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "5.0.0-alpha.2"
    const val coroutinesAdapter = "0.9.2"

    //Koin
    const val koin_version = "3.1.2"

    // Picasso
    const val picasso = "2.71828"
    //Glide
    const val glide = "4.12.0"
    const val glide_compiler = "4.11.0"
    //Coil
    const val coil = "0.11.0"

    //Room
    const val room_version = "2.4.2"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    const val recycleView = "androidx.recyclerview:recyclerview:${Versions.recycleView}"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.junit}"
    const val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin_version}"
    const val android = "io.insert-koin:koin-android:${Versions.koin_version}"
    const val android_compat = "io.insert-koin:koin-android-compat:${Versions.koin_version}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val compiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
}