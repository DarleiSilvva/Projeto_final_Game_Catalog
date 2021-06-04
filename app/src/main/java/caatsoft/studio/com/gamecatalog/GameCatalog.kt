package caatsoft.studio.com.gamecatalog

import android.app.Application
import caatsoft.studio.com.gamecatalog.dao.GamefavoriteDatabase
import caatsoft.studio.com.gamecatalog.di.networkModule
import caatsoft.studio.com.gamecatalog.di.viewModelModule
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepository
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GameCatalog : Application() {

  companion object {
    private lateinit var instance: GameCatalog

    private val database: GamefavoriteDatabase by lazy {
      GamefavoriteDatabase.buildDatabase(instance)
    }

    val repository: FavoriteRepository by lazy {
      FavoriteRepositoryImpl(
        database.gameDao()
      )
    }
  }

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidLogger()
      androidContext(this@GameCatalog)
      modules(listOf(networkModule, viewModelModule))
    }
    instance = this

    FirebaseDatabase.getInstance().setPersistenceEnabled(true)
  }
}