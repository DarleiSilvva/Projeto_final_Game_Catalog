package caatsoft.studio.com.gamecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteViewModel: ViewModel(), KoinComponent {
    val repository:FavoriteRepositoryImpl by inject()

    private val gameListLiveData = MutableLiveData<List<FavoriteGame>>()
    val games  = gameListLiveData as LiveData<List<FavoriteGame>>

    fun getGames() {
        viewModelScope.launch {
            gameListLiveData.value = repository.getGames()
        }
    }

    fun addGame(favoriteGame: FavoriteGame) {
        repository.addGame(favoriteGame)
    }

    fun removeGame(favoriteGame: FavoriteGame){
        repository.removeGame(favoriteGame)
    }
}