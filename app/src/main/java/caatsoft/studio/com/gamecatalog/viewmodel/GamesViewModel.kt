package com.test.testtwo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtwo.network.Game
import com.test.testtwo.network.Repository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class GamesViewModel (private val repository: Repository) : ViewModel(), KoinComponent {
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

