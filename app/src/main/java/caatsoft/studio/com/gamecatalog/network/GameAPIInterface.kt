package caatsoft.studio.com.gamecatalog.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.DecimalFormat

const val API_KEY = "0d01e543df3405978aff55b5890478461fd2f770"

interface GameAPIInterface {
  //@GET("games/?api_key=$API_KEY")
 // @GET("games/?api_key=$API_KEY")
  //suspend fun getGame(): GameResponse
  /*fun getGame(
          @Query("format") format: String
  ): Call<GameResponse>*/

  @GET("games/?api_key=$API_KEY")
  suspend fun getFilteredGames(
          @Query("format") format: String,
          @Query("field_list") field_list : String
  ): GameResponse2

  /*@GET("games/?api_key=$API_KEY")
  fun getFilteredGames(
          @Query("format") format: String,
          @Query("field_list") field_list : String
  ): Call<GameResponse2>*/
}