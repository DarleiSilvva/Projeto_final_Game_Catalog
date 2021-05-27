package caatsoft.studio.com.gamecatalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caatsoft.studio.com.gamecatalog.network.GameAPIInterface
import caatsoft.studio.com.gamecatalog.network.GameResponse2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface MainView {
  fun getFilteredGames(GameResponse: GameResponse2)
}

class MainViewModel : ViewModel(), KoinComponent {
  val gameAPIInterface: GameAPIInterface by inject()
  var view: MainView? = null

  fun getFilteredGames() {
    var gameResponse: GameResponse2? = null

    viewModelScope.launch(Dispatchers.IO) {
      val filteredGames = gameAPIInterface.getFilteredGames("json", "name,deck,platforms,original_release_date,image")
      filteredGames.enqueue(object : Callback<GameResponse2> {
        override fun onResponse(call: Call<GameResponse2>, response: Response<GameResponse2>) {
          response.let {
            gameResponse = it.body()!!

          }
        }
        override fun onFailure(call: Call<GameResponse2>, t: Throwable) {
        }
      })
      withContext(Dispatchers.Main) {
        gameResponse?.let { view?.getFilteredGames(it) }
      }
    }
  }
}