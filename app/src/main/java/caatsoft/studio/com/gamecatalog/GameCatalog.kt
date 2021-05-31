package caatsoft.studio.com.gamecatalog

import android.app.Application
import caatsoft.studio.com.gamecatalog.di.networkModule
import caatsoft.studio.com.gamecatalog.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GameCatalog : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger()
      androidContext(this@GameCatalog)
      modules(listOf(networkModule, viewModelModule))
    }
  }
}