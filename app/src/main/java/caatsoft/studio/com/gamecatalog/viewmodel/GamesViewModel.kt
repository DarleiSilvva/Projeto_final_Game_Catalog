package caatsoft.studio.com.gamecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caatsoft.studio.com.gamecatalog.network.model.Game
import caatsoft.studio.com.gamecatalog.repository.Repository
import kotlinx.coroutines.launch

class GamesViewModel (private val repository: Repository) : ViewModel() {
  private val gameListLiveData = MutableLiveData<List<Game>>()
  val games  = gameListLiveData as LiveData<List<Game>>

  fun getFilteredGames() {
    viewModelScope.launch {
      val filteredGamesResponse = repository.getFilteredGames(FORMAT, FIELD_LIST)
      if (filteredGamesResponse.isSuccessful){
        filteredGamesResponse.body()?.let { gameResponse ->
          gameListLiveData.value = gameResponse.results
        }
      }
    }
  }
  companion object {
    private const val FORMAT = "json"
    private const val FIELD_LIST = "name,deck,platforms,original_release_date,image"
  }
}

