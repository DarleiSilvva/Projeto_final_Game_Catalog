package caatsoft.studio.com.gamecatalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caatsoft.studio.com.gamecatalog.network.Game
import caatsoft.studio.com.gamecatalog.network.GameResponse
import caatsoft.studio.com.gamecatalog.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

interface MainView {
  fun getFilteredGames(gameResponse: GameResponse)
}

class MainViewModel : ViewModel(), KoinComponent {
  var view: MainView? = null
  private val repository:Repository by inject()
  //val gameListLiveData = MutableLiveData<List<Game>>()

  val games: MutableLiveData<List<Game>> by lazy {
    MutableLiveData<List<Game>>().also {
      loadGames()
    }
  }

  fun getGames(): LiveData<List<Game>> {
    return games
  }

  private fun loadGames() {
    getFilteredGames()
  }

  fun getFilteredGames() {

    viewModelScope.launch(Dispatchers.IO) {
      val filteredGamesResponse = repository.getFilteredGames("json", "name,deck,platforms,original_release_date,image")
      if (filteredGamesResponse.isSuccessful){
        filteredGamesResponse.body()?.let { gameResponse ->
          games.value = gameResponse.results
          view?.getFilteredGames(gameResponse)
        }
      }
    }
  }

}

