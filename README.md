# __Game_Catalog__ 🎮🧩⚽🕹️🀄♟️
O Game Catalog é o projeto final da primeira etapa do estágio de Darlei Silva.

## Descrição 🎮📒
Basicamente, ele é um catálogo de games que contém 4 partes: a autenticação inicial, a parte de exibição dos jogos, os favoritos e a parte com dados do usuário. As três últimas estão em uma única activity, mas cada um é um __fragment__ que é alterado por um bottom navegation. 

### Tecnologias 🧩🖱️💾
* Kotlin
* Android Studio
* Firebase
  * Database
  * Realtime
  * Storage
  * Auth
  * Analytics
  * firestore
* Retrofit
  * gson
  * okhttp3
* Glide
* Room
* Koin
* Coroutines
* viewmodel

## Para começar: 👨🏻‍💻🖥
## Instalação 👨🏻‍💻🖥️
- [x] É necessário baixar o Android Studio
- [x] É necessário ter uma conta no Firebase

### Dependências 🧩📄

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

## Autor
Darlei Silva <h5>https://br.linkedin.com/in/darlei-silva-937a93168<h5>
  
## Histórico da Versão
## Licença
## Agradecimentos