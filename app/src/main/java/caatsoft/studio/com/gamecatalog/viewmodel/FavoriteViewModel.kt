package caatsoft.studio.com.gamecatalog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteViewModel (val repository:FavoriteRepositoryImpl): ViewModel() {

    private val gameListLiveData = MutableLiveData<List<FavoriteGame>>()
    val games  = gameListLiveData as LiveData<List<FavoriteGame>>

    fun getGames() {
        viewModelScope.launch {
            gameListLiveData.value = repository.getGames()
        }
    }

    fun addGame(favoriteGame: FavoriteGame) {
        viewModelScope.launch {
            repository.addGame(favoriteGame)
        }

    }

    fun removeGame(favoriteGame: FavoriteGame){
        viewModelScope.launch {
            repository.removeGame(favoriteGame)

            val gameList = gameListLiveData.value as MutableList<FavoriteGame>
            gameList.remove(favoriteGame)
            gameListLiveData.value = gameList
        }
    }
}