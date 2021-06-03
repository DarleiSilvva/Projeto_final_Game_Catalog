package caatsoft.studio.com.gamecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import caatsoft.studio.com.gamecatalog.GameCatalog
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val repository by lazy { GameCatalog.repository }

    private val gameListLiveData = MutableLiveData<List<FavoriteGame>>()
    val games  = gameListLiveData as LiveData<List<FavoriteGame>>

    fun getGames() {
        viewModelScope.launch {
            gameListLiveData.value = repository.getGames()
        }
    }
}