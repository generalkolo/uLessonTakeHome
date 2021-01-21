object AppVersions {
    const val targetSdkVersion = 29
    const val compileSdkVersion = 28
    const val minSdkVersion = 19

    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.3.41"
    const val coreKtx = "1.0.2"
    const val lifecycle = "2.0.0"
    const val junit = "4.12"
    const val espresso = "3.2.0"
    const val runner = "1.2.0"

    const val javaxInject = "1"
    const val exoPlayer = "2.10.7"
    const val javaxAnnotation = "1.0"
    const val room = "2.2.0-alpha01"
    const val rxJava = "2.2.10"
    const val rxAndroid = "2.1.1"
    const val okhttp = "3.12.0"
    const val retrofit = "2.6.0"
    const val recyclerAnimator = "3.0.0"
    const val materialComponent = "1.1.0-alpha07"
    const val dagger = "2.24"
    const val stetho = "1.5.1"
    const val timber = "4.7.1"
    const val navigation = "2.1.0-rc01"
    const val constraintLayout = "2.0.0-beta1"
    const val rxAnimation = "0.0.6"
    const val multidex = "2.0.1"
    const val overscrollDecor = "1.0.4"
    const val viewPagerTransformer = "1.0.1@aar"
    const val androidAnimation = "2.3@aar"
    const val gson = "2.8.5"
    const val gradle = "3.5.0"
    const val testExt = "1.1.1"
    const val mockk = "1.9.3"
    const val rxPermissions = "0.10.2"
    const val glide = "4.9.0"
    const val ankoVersion = "0.10.8"
    const val lottie = "3.0.7"
}

object AppCoreDependencies {
    const val packageName = "com.ulesson.takeHome"

    const val BASE_URL = "\" https://jackiechanbruteforce.ulesson.com/\""

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${AppVersions.kotlin}"
    const val coreKtx = "androidx.core:core-ktx:${AppVersions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${AppVersions.coreKtx}"
    const val androidFragmentKtx = "androidx.fragment:fragment-ktx:${AppVersions.coreKtx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${AppVersions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${AppVersions.lifecycle}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${AppVersions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${AppVersions.navigation}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${AppVersions.constraintLayout}"
    const val multidex = "androidx.multidex:multidex:${AppVersions.multidex}"
}

object AppTestDependencies {
    const val junit = "junit:junit:${AppVersions.junit}"
    const val mockk = "io.mockk:mockk:${AppVersions.mockk}"
    const val coreTesting = "androidx.arch.core:core-testing:${AppVersions.lifecycle}"
    const val roomTesting = "androidx.room:room-testing:${AppVersions.room}"
}

object AppAndroidTestDependencies {
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val espresso = "androidx.test.espresso:espresso-core:${AppVersions.espresso}"
    const val testRunner = "androidx.test:runner:${AppVersions.runner}"
    const val textExt = "androidx.test.ext:junit:${AppVersions.testExt}"
    const val mockk = "io.mockk:mockk-android:${AppVersions.mockk}"
}

object AppViewDependencies {
    const val materialComponent =
        "com.google.android.material:material:${AppVersions.materialComponent}"
    const val rxAnimation = "com.mikhaellopez:rxanimation:${AppVersions.rxAnimation}"
}

object AppNetworkDependencies {
    const val okhttp = "com.squareup.okhttp3:okhttp:${AppVersions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${AppVersions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${AppVersions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${AppVersions.retrofit}"
    const val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${AppVersions.retrofit}"
    const val stetho = "com.facebook.stetho:stetho:${AppVersions.stetho}"
    const val stethoOkhttp = "com.facebook.stetho:stetho-okhttp3:${AppVersions.stetho}"
}

object AppAsyncDependencies {
    const val rxJava = "io.reactivex.rxjava2:rxjava:${AppVersions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${AppVersions.rxAndroid}"
}

object AppDependencyInjectionDependencies {
    const val javax = "javax.inject:javax.inject:${AppVersions.javaxInject}"
    const val javaxAnnotation = "javax.annotation:jsr250-api:${AppVersions.javaxAnnotation}"
    const val dagger = "com.google.dagger:dagger:${AppVersions.dagger}"
    const val daggerAndroidSupport =
        "com.google.dagger:dagger-android-support:${AppVersions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${AppVersions.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${AppVersions.dagger}"
}

object AppPersistenceDependencies {
    const val room = "androidx.room:room-runtime:${AppVersions.room}"
    const val roomRx = "androidx.room:room-rxjava2:${AppVersions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${AppVersions.room}"
    const val gson = "com.google.code.gson:gson:${AppVersions.gson}"
}

object AppUtilityDependencies {
    const val timber = "com.jakewharton.timber:timber:${AppVersions.timber}"
    const val recyclerAnimator = "jp.wasabeef:recyclerview-animators:${AppVersions.recyclerAnimator}"
    const val overscrollDecor =
        "me.everything:overscroll-decor-android:${AppVersions.overscrollDecor}"
    const val viewPagerTransformer =
        "com.eftimoff:android-viewpager-transformers:${AppVersions.viewPagerTransformer}"
    const val exoPlayer = "com.google.android.exoplayer:exoplayer:${AppVersions.exoPlayer}"
    const val androidAnimation =
        "com.daimajia.androidanimations:library:${AppVersions.androidAnimation}"
    const val rxPermissions = "com.github.tbruyelle:rxpermissions:${AppVersions.rxPermissions}"
    const val glide = "com.github.bumptech.glide:glide:${AppVersions.glide}"
    const val ankoVersion = "org.jetbrains.anko:anko-commons:${AppVersions.ankoVersion}"
    const val lottie = "com.airbnb.android:lottie:${AppVersions.lottie}"
}

object Classpaths {
    const val gradle = "com.android.tools.build:gradle:${AppVersions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${AppVersions.kotlin}"
    const val navigation =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${AppVersions.navigation}"
}
