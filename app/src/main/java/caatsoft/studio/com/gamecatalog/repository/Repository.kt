package caatsoft.studio.com.gamecatalog.repository

import caatsoft.studio.com.gamecatalog.network.GameAPIInterface
import caatsoft.studio.com.gamecatalog.network.GameResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

class Repository (): KoinComponent {
    val gameAPIInterface: GameAPIInterface by inject()

    suspend fun getFilteredGames(
       format: String,
       field_list : String
    ): Response<GameResponse>{

        return gameAPIInterface.getFilteredGames(format, field_list)
    }
}