package caatsoft.studio.com.gamecatalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
  val repository:Repository by inject()

  fun getFilteredGames() {

    viewModelScope.launch(Dispatchers.IO) {
      val filteredGamesResponse = repository.getFilteredGames("json", "name,deck,platforms,original_release_date,image")
      if (filteredGamesResponse.isSuccessful){
        filteredGamesResponse.body()?.let { gameResponse ->
          view?.getFilteredGames(gameResponse)
        }
      }
    }
  }

}