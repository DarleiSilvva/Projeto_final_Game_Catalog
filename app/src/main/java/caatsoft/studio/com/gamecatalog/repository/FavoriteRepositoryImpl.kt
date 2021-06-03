package caatsoft.studio.com.gamecatalog.repository

import caatsoft.studio.com.gamecatalog.dao.GameDao
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame

class FavoriteRepositoryImpl(
    private val gameDao: GameDao) : FavoriteRepository {

    override fun getGames(): List<FavoriteGame> = gameDao.getGames()

    override fun addGame(favoriteGame: FavoriteGame) = gameDao.addGame(favoriteGame)

    override fun removeGame(favoriteGame: FavoriteGame) = gameDao.removeGame(favoriteGame)

    override fun getGameById(favorite_gamesId: String): FavoriteGame = gameDao.getGameById(favorite_gamesId)
}