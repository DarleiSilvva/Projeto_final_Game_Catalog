package caatsoft.studio.com.gamecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame
import caatsoft.studio.com.gamecatalog.network.model.Game
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import caatsoft.studio.com.gamecatalog.repository.Repository
import kotlinx.coroutines.launch

class GamesViewModel (private val repository: Repository, val favoriteRepository: FavoriteRepositoryImpl) : ViewModel() {

  private val gameListLiveData = MutableLiveData<List<Game>>()
  val games  = gameListLiveData as LiveData<List<Game>>
  private val _isLoading = MutableLiveData(true)
  val isLoading: LiveData<Boolean> = _isLoading

  fun getFilteredGames() {
    _isLoading.value = true
    viewModelScope.launch {
      val filteredGamesResponse = repository.getFilteredGames(FORMAT, FIELD_LIST)
      if (filteredGamesResponse.isSuccessful){
        filteredGamesResponse.body()?.let { gameResponse ->
          gameListLiveData.value = gameResponse.results
          _isLoading.value = false
        }
      }
    }
  }

  fun addGame(favoriteGame: FavoriteGame) {
    viewModelScope.launch {
      favoriteRepository.addGame(favoriteGame)
    }

  }
  companion object {
    private const val FORMAT = "json"
    private const val FIELD_LIST = "name,deck,platforms,original_release_date,image"
  }
}

