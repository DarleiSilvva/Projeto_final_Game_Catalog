package caatsoft.studio.com.gamecatalog.dao

import androidx.room.*
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame

@Dao
interface GameDao {

  @Query("SELECT * FROM favorite_games")
  fun getGames(): List<FavoriteGame>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addGame(favoriteGame: FavoriteGame)

  @Delete
  fun removeGame(favoriteGame: FavoriteGame)

  @Query("SELECT * FROM favorite_games WHERE name = :favorite_gamesId")
  fun getGameById(favorite_gamesId: String): FavoriteGame
}