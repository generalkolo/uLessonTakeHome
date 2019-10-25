object GokadaVersions {
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
    const val pinView = "2.0.3"
    const val shimmerLayout = "0.4.0"
    const val fresco = "2.0.0"
    const val multidex = "2.0.1"
    const val overscrollDecor = "1.0.4"
    const val viewPagerTransformer = "1.0.1@aar"
    const val pagerIndicator = "1.0.3"
    const val alexanderMaps = "1.1.0"
    const val googleMaps = "16.1.0"
    const val locationServices = "15.0.1"
    const val deviceName = "1.1.9"
    const val workManager = "2.2.0"
    const val stepView = "1.0.2"
    const val easingAnimation = "2.0@aar"
    const val androidAnimation = "2.3@aar"
    const val playServicesAuth = "17.0.0"
    const val playServicesAuthPhone = "17.1.0"
    const val roundProgressBar = "2.0.3"
    const val materialRatingBar = "1.3.2"
    const val mapsLibrary = "0.5"
    const val debugDatabaseLibrary = "1.0.6"
    const val gson = "2.8.5"
    const val fabric = "2.10.1"
    const val firebaseCore = "17.0.1"
    const val firebaseAnalytics = "17.0.1"
    const val firebaseMessaging = "20.0.0"
    const val fabricClasspath = "1.31.0"
//    const val googleServices = "4.3.2"
    const val gradle = "3.5.0"
    const val testExt = "1.1.1"
    const val mockk = "1.9.3"
    const val rxPermissions = "0.10.2"
    const val rxLocation = "1.0.5"
    const val rxBinding = "3.0.0"
    const val smartLocation = "3.3.3"
    const val places = "2.0.0"
    const val mapDirection = "1.2.0"
    const val glide = "4.9.0"
    const val ankoVersion = "0.10.8"
    const val lottie = "3.0.7"
    const val horizontalCalender = "1.3.4"
    const val materialDatePicker = "4.2.3"
    const val niceSpinner = "1.4.3"
}

object GokadaCoreDependencies {
    const val packageName = "com.gokada.baseApp"
    const val GOOGLE_MAPS_API_KEY = "AIzaSyBtDIkor7WntByDyzdh7pwCGqyfWiegyk8"
    const val GOOGLE_MAPS_API_KEY_BUILD_CONFIG = "\"AIzaSyBtDIkor7WntByDyzdh7pwCGqyfWiegyk8\""
    const val FABRIC_API_KEY = "05aaeca8f0c70e4668afe6d90f66e5b7cb84fef7"

    const val AES_ENCRYPTION_PASSWORD = "\"lbwyBzfgzUIvXZFShJuikaWvLJhIVq36\""

    const val BASE_URL_RIDE_HAILING = "\"https://09yv3jwnq5.execute-api.eu-west-1.amazonaws.com/staging/\""
    const val WALLET_PAYMENT_API_AUTHORIZATION_TOKEN = "\"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIwYnFLR092T3VOemJObWZ6TEgxWHVlIiwibmFtZSI6Imdva2FkYSBzdXBlckFwcCIsImlhdCI6MTUxNjIzOTAyMn0.U2zIJ_LKxIawovursd8z_Qemk8Jz5jT4w3suMHOu1Kw\""
    const val BASE_URL_WALLET = "\"https://gokada-wallet-api.herokuapp.com/wallet/\""
    const val BASE_URL_PAYMENT = "\"http://super-app-lb-staging-564424982.eu-west-1.elb.amazonaws.com:3500/payment/\""

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${GokadaVersions.kotlin}"
    const val coreKtx = "androidx.core:core-ktx:${GokadaVersions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${GokadaVersions.coreKtx}"
    const val androidFragmentKtx = "androidx.fragment:fragment-ktx:${GokadaVersions.coreKtx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${GokadaVersions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${GokadaVersions.lifecycle}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${GokadaVersions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${GokadaVersions.navigation}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${GokadaVersions.constraintLayout}"
    const val multidex = "androidx.multidex:multidex:${GokadaVersions.multidex}"
}

object GokadaGooglePlayDependenciesDependencies {
    const val googleMaps = "com.google.android.gms:play-services-maps:${GokadaVersions.googleMaps}"
    const val googleLocation =  "com.google.android.gms:play-services-location:${GokadaVersions.locationServices}"
    const val places = "com.google.android.libraries.places:places:${GokadaVersions.places}"
    const val placesCompat = "com.google.android.libraries.places:places-compat:${GokadaVersions.places}"
    const val playServicesAuth = "com.google.android.gms:play-services-auth:${GokadaVersions.playServicesAuth}"
    const val playServicesAuthPhone = "com.google.android.gms:play-services-auth-api-phone:${GokadaVersions.playServicesAuthPhone}"
}

object GokadaWorkManagerDependencies {
    const val workManager = "androidx.work:work-runtime-ktx:${GokadaVersions.workManager}"
    const val workManagerRx = "androidx.work:work-rxjava2:${GokadaVersions.workManager}"
}

object GokadaTestDependencies {
    const val junit = "junit:junit:${GokadaVersions.junit}"
    const val mockk = "io.mockk:mockk:${GokadaVersions.mockk}"
    const val coreTesting = "androidx.arch.core:core-testing:${GokadaVersions.lifecycle}"
    const val roomTesting = "androidx.room:room-testing:${GokadaVersions.room}"
}

object GokadaAndroidTestDependencies {
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val espresso = "androidx.test.espresso:espresso-core:${GokadaVersions.espresso}"
    const val testRunner = "androidx.test:runner:${GokadaVersions.runner}"
    const val textExt = "androidx.test.ext:junit:${GokadaVersions.testExt}"
    const val mockk = "io.mockk:mockk-android:${GokadaVersions.mockk}"
    const val workManagerTesting = "androidx.work:work-testing:${GokadaVersions.workManager}"
}

object GokadaViewDependencies {
    const val materialComponent = "com.google.android.material:material:${GokadaVersions.materialComponent}"
    const val rxAnimation = "com.mikhaellopez:rxanimation:${GokadaVersions.rxAnimation}"
    const val shimmerLayout = "com.facebook.shimmer:shimmer:${GokadaVersions.shimmerLayout}"
    const val fresco = "com.facebook.fresco:fresco:${GokadaVersions.fresco}"
    const val pinView = "com.github.mukeshsolanki:android-otpview-pinview:${GokadaVersions.pinView}"
    const val stepView = "com.params.stepview:stepview:${GokadaVersions.stepView}"
}

object GokadaNetworkDependencies {
    const val okhttp = "com.squareup.okhttp3:okhttp:${GokadaVersions.okhttp}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${GokadaVersions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${GokadaVersions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${GokadaVersions.retrofit}"
    const val rxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${GokadaVersions.retrofit}"
    const val stetho = "com.facebook.stetho:stetho:${GokadaVersions.stetho}"
    const val stethoOkhttp = "com.facebook.stetho:stetho-okhttp3:${GokadaVersions.stetho}"
}

object GokadaAsyncDependencies {
    const val rxJava = "io.reactivex.rxjava2:rxjava:${GokadaVersions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${GokadaVersions.rxAndroid}"
}

object GokadaDependencyInjectionDependencies {
    const val javax = "javax.inject:javax.inject:${GokadaVersions.javaxInject}"
    const val javaxAnnotation = "javax.annotation:jsr250-api:${GokadaVersions.javaxAnnotation}"
    const val dagger = "com.google.dagger:dagger:${GokadaVersions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${GokadaVersions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${GokadaVersions.dagger}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${GokadaVersions.dagger}"
}

object GokadaPersistenceDependencies {
    const val room = "androidx.room:room-runtime:${GokadaVersions.room}"
    const val roomRx = "androidx.room:room-rxjava2:${GokadaVersions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${GokadaVersions.room}"
    const val gson = "com.google.code.gson:gson:${GokadaVersions.gson}"
}

object GokadaUtilityDependencies {
    const val timber = "com.jakewharton.timber:timber:${GokadaVersions.timber}"
    const val pagerIndicator = "com.romandanylyk:pageindicatorview:${GokadaVersions.pagerIndicator}"
    const val recyclerAnimator = "jp.wasabeef:recyclerview-animators:${GokadaVersions.recyclerAnimator}"
    const val overscrollDecor = "me.everything:overscroll-decor-android:${GokadaVersions.overscrollDecor}"
    const val viewPagerTransformer = "com.eftimoff:android-viewpager-transformers:${GokadaVersions.viewPagerTransformer}"
    const val easingAnimation = "com.daimajia.easing:library:${GokadaVersions.easingAnimation}"
    const val androidAnimation = "com.daimajia.androidanimations:library:${GokadaVersions.androidAnimation}"
    const val deviceName = "com.jaredrummler:android-device-names:${GokadaVersions.deviceName}"
    const val roundCornerProgressBar = "com.akexorcist:RoundCornerProgressBar:${GokadaVersions.roundProgressBar}"
    const val materialRatingBar = "me.zhanghai.android.materialratingbar:library:${GokadaVersions.materialRatingBar}"
    const val mapsLibrary = "com.google.maps.android:android-maps-utils:${GokadaVersions.mapsLibrary}"
    const val debugDatabaseLibrary = "com.amitshekhar.android:debug-db:${GokadaVersions.debugDatabaseLibrary}"
    const val rxPermissions = "com.github.tbruyelle:rxpermissions:${GokadaVersions.rxPermissions}"
    const val rxLocation = "com.patloew.rxlocation:rxlocation:${GokadaVersions.rxLocation}"
    const val rxBinding = "com.jakewharton.rxbinding3:rxbinding:${GokadaVersions.rxBinding}"
    const val rxBindingCore = "com.jakewharton.rxbinding3:rxbinding-core:${GokadaVersions.rxBinding}"
    const val rxBindingAppCompat = "com.jakewharton.rxbinding3:rxbinding-appcompat:${GokadaVersions.rxBinding}"
    const val rxBindingMaterial = "com.jakewharton.rxbinding3:rxbinding-material:${GokadaVersions.rxBinding}"
    const val smartLocation = "io.nlopez.smartlocation:library:${GokadaVersions.smartLocation}"
    const val smartLocationRx = "io.nlopez.smartlocation:rx:${GokadaVersions.smartLocation}"
    const val mapDirection = "com.akexorcist:google-direction-library:${GokadaVersions.mapDirection}"
    const val glide = "com.github.bumptech.glide:glide:${GokadaVersions.glide}"
    const val ankoVersion = "org.jetbrains.anko:anko-commons:${GokadaVersions.ankoVersion}"
    const val lottie = "com.airbnb.android:lottie:${GokadaVersions.lottie}"
    const val horizontalCalender = "devs.mulham.horizontalcalendar:horizontalcalendar:${GokadaVersions.horizontalCalender}"
    const val materialDatePicker = "com.wdullaer:materialdatetimepicker:${GokadaVersions.materialDatePicker}"
    const val niceSpinner = "com.github.arcadefire:nice-spinner:${GokadaVersions.niceSpinner}"
    const val alexanderMaps = "com.github.jd-alexander:library:${GokadaVersions.alexanderMaps}"
}

object GokadaFirebaseDependencies {
    const val messaging = "com.google.firebase:firebase-messaging:${GokadaVersions.firebaseMessaging}"
    const val analytics = "com.google.firebase:firebase-analytics:${GokadaVersions.firebaseAnalytics}"
}

object GokadaClasspaths {
    const val gradle = "com.android.tools.build:gradle:${GokadaVersions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${GokadaVersions.kotlin}"
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${GokadaVersions.navigation}"
    const val fabric = "io.fabric.tools:gradle:${GokadaVersions.fabricClasspath}"
//    const val googleServices = "com.google.gms:google-services:${GokadaVersions.googleServices}"
}

object GokadaAnalyticsDependencies {
    const val fabric = "com.crashlytics.sdk.android:crashlytics:${GokadaVersions.fabric}"
    const val analytics = "com.google.firebase:firebase-analytics:${GokadaVersions.firebaseAnalytics}"
    const val firebaseCore = "com.crashlytics.sdk.android:crashlytics:${GokadaVersions.fabric}"
}