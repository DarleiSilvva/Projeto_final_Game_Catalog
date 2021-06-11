package caatsoft.studio.com.gamecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import kotlinx.coroutines.launch

class FavoriteViewModel (val favoriteRepository:FavoriteRepositoryImpl): ViewModel() {

    private val gameListLiveData = MutableLiveData<List<FavoriteGame>>()
    val games  = gameListLiveData as LiveData<List<FavoriteGame>>
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getGames() {
        _isLoading.value = true
        viewModelScope.launch {
            gameListLiveData.value = favoriteRepository.getGames()
            _isLoading.value = false
        }
    }

    fun removeGame(favoriteGame: FavoriteGame){
        viewModelScope.launch {
            favoriteRepository.removeGame(favoriteGame)

            val gameList = gameListLiveData.value as MutableList<FavoriteGame>
            gameList.remove(favoriteGame)
            gameListLiveData.value = gameList
        }
    }
}