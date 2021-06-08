package caatsoft.studio.com.gamecatalog

import android.app.Application
import caatsoft.studio.com.gamecatalog.dao.GamefavoriteDatabase
import caatsoft.studio.com.gamecatalog.di.firebaseModule
import caatsoft.studio.com.gamecatalog.di.networkModule
import caatsoft.studio.com.gamecatalog.di.roomModule
import caatsoft.studio.com.gamecatalog.di.viewModelModule
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepository
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GameCatalog : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger()
      androidContext(this@GameCatalog)
      modules(listOf(firebaseModule, networkModule, viewModelModule, roomModule))
    }
  }
}