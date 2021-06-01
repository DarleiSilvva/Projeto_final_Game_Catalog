package caatsoft.studio.com.gamecatalog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel : ViewModel() {

    val textFavorite = MutableLiveData<String>().apply {
        value = "This is favorite Fragment"
    }
}