package caatsoft.studio.com.gamecatalog.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame

const val DATABASE_VERSION = 1

@Database(
    entities = [FavoriteGame::class],
    version = DATABASE_VERSION
)
abstract class GamefavoriteDatabase : RoomDatabase() {

  companion object {
    private const val DATABASE_NAME = "favoriteGameDatabase"

    fun buildDatabase(context: Context): GamefavoriteDatabase {
      return Room.databaseBuilder(
          context,
          GamefavoriteDatabase::class.java,
          DATABASE_NAME
      )
          .allowMainThreadQueries()
          .build()
    }

  }

  abstract fun gameDao(): GameDao
}