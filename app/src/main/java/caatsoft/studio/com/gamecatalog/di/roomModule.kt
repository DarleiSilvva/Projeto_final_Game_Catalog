package caatsoft.studio.com.gamecatalog.di

import caatsoft.studio.com.gamecatalog.dao.GamefavoriteDatabase
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        val database = GamefavoriteDatabase.buildDatabase(androidContext())
        FavoriteRepositoryImpl(
            database.gameDao()
        )
    }
}