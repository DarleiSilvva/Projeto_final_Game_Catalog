package caatsoft.studio.com.gamecatalog.repository

import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame

interface FavoriteRepository {

  fun getGames(): List<FavoriteGame>

  fun addGame(favoriteGame: FavoriteGame)

  fun removeGame(favoriteGame: FavoriteGame)

  fun getGameById(favorite_gamesId: String): FavoriteGame
}