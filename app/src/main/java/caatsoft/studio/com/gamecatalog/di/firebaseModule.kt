package caatsoft.studio.com.gamecatalog.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val firebaseModule = module {
    single {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}