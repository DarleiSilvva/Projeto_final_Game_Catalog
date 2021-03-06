<p align="center">
  <img width="150" height="150" src="https://user-images.githubusercontent.com/66731191/121453157-0d845680-c977-11eb-82d5-c0139f9f1c84.png">
</p>

# <h1 align="center">__Game_Catalog__ đŽđ§ŠâŊđšī¸đâī¸</h1>
<p align="center"> The Game Catalog is the final project of the first stage of Darlei Silva's internship.</p>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/DarleiSilvva/Projeto_final_Game_Catalog/blob/master/LICENSE)


## Description đŽđ
Game_Catalog, as the name implies, is a game catalog that contains 4 parts: the initial authentication, the game display part, the favorites and the part with user data. The last three are in a single activity, but each one is a __fragment__ that is changed by a bottom navigation.

_The screens are in Portuguese, but the project language is in English and it is translated to both languages._

<p align="center"> Login </p>
<p align="center">
  <img width="350" height="571" src="https://user-images.githubusercontent.com/66731191/121407773-0c7f0500-c936-11eb-9ccc-eb1129744634.png"> 
</p>


<p align="center"> Register </p>
<p align="center">
  <img width="350" height="571" src="https://user-images.githubusercontent.com/66731191/121407694-f7a27180-c935-11eb-8dd6-f912b5f24d5e.png"> 
</p>


<p align="center"> Games </p>
<p align="center">
  <img width="350" height="571" src="https://user-images.githubusercontent.com/66731191/121407758-0852e780-c936-11eb-9fdb-0c7030718d8d.png"> 
</p>


<p align="center"> Favorites </p>
<p align="center">
  <img width="350" height="571" src="https://user-images.githubusercontent.com/66731191/121407726-ff621600-c935-11eb-88f4-f45e9af968bf.png"> 
</p>


<p align="center"> User data </p>
<p align="center">
  <img width="350" height="571" src="https://user-images.githubusercontent.com/66731191/121407788-10ab2280-c936-11eb-978b-a15a2bcdd416.png"> 
</p>


## Technologies đ§Šđąī¸đž
* Kotlin
* Android Studio
* Firebase
  * Database
  * Realtime
  * Storage
  * Auth
  * Analytics
  * Firestore
* Retrofit
  * gson
  * okhttp3
* Glide
* Room
* Koin
* Coroutines
* Viewmodel

## Architecture đī¸đ§Š
MVVM

## To starting: đ¨đģâđģđĨ
### InstalaÃ§ÃŖo đ¨đģâđģđĨī¸
- [x] Ã necessÃĄrio baixar o Android Studio
- [x] Ã necessÃĄrio ter uma conta no Firebase
- [x] Ã necessÃĄrio ter acesso a API de games: https://www.giantbomb.com/

### Dependencies đ§Šđ

```gradle
ext {
    koinVersion = "2.0.1"
    moshiVersion = "1.8.0"
    glideVersion = "4.12.0"
    retrofitVersion = "2.9.0"
    room_version = "2.2.5"
    corekotlin_version = "1.5.0"
    appcompat_version = "1.3.0"
    material_version = "1.3.0"
    code_gson_version = "2.8.6"
    constraintlayout_version = "2.0.4"
    retrofit2_gson_version = "2.6.1"
    retrofit2_okhttp3_version = "4.8.1"
    coroutines_version = "1.4.1"
    support_v4_version = "1.0.0"
    navigation_fragment_ktx_version = "2.3.5"
    navigation_ui_ktx_version = "2.3.5"
    lifecycle_extensions_version = "2.2.0"
    lifecycle_viewmodel_version = "2.4.0-alpha01"
    test_ext_junit_version = "1.1.2"
    espresso_cor_version = "3.3.0"
    firebase_core_version = "19.0.0"
    firebase_database_version = "20.0.0"
    firebase_ui_database_version = "4.3.2"
}

dependencies {
    implementation platform('com.google.firebase:firebase-bom:28.0.1')

    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation "androidx.core:core-ktx:$corekotlin_version"
    implementation "androidx.appcompat:appcompat:$appcompat_version"
    implementation "com.google.android.material:material:$material_version"
    implementation "com.google.code.gson:gson:$code_gson_version"
    implementation "androidx.constraintlayout:constraintlayout:$constraintlayout_version"
    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit2_gson_version"
    implementation "com.squareup.okhttp3:logging-interceptor:$retrofit2_okhttp3_version"
    implementation "com.squareup.moshi:moshi:$moshiVersion"
    implementation "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    implementation "com.github.bumptech.glide:glide:$glideVersion"

    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    implementation "androidx.legacy:legacy-support-v4:$support_v4_version"
    implementation "androidx.navigation:navigation-fragment-ktx:$navigation_fragment_ktx_version"
    implementation "androidx.navigation:navigation-ui-ktx:$navigation_ui_ktx_version"

    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_extensions_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_viewmodel_version"

    implementation "org.koin:koin-android:$koinVersion"
    implementation "org.koin:koin-androidx-scope:$koinVersion"
    implementation "org.koin:koin-androidx-viewmodel:$koinVersion"
    testImplementation 'junit:junit:4.+'
    androidTestImplementation "com.github.bumptech.glide:compiler:$glideVersion"
    androidTestImplementation "androidx.test.ext:junit:$test_ext_junit_version"
    androidTestImplementation "androidx.test.espresso:espresso-core:$espresso_cor_version"

    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"

    implementation 'com.google.firebase:firebase-storage-ktx'
    implementation 'com.google.firebase:firebase-auth-ktx'
    implementation "com.google.firebase:firebase-core:$firebase_core_version"
    implementation 'com.google.firebase:firebase-firestore-ktx'
    implementation "com.google.firebase:firebase-database:$firebase_database_version"
    implementation "com.firebaseui:firebase-ui-database:$firebase_ui_database_version"
    implementation 'com.google.firebase:firebase-analytics-ktx'

}
```

## Author đ¨đģâđģđĨī¸
Darlei Silva <h5>https://br.linkedin.com/in/darlei-silva-937a93168<h5>
  
## Version History đž
 1.0
 
## Thanks đ¤đŧ
Lucas Souza (https://github.com/Lhmsouza), the mentor, he helped from the beginning in the execution of the project. And Lucas Cabral (https://github.com/LucasCabralDevv) who helped with the Networking part at times.
