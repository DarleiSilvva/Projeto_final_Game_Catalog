package caatsoft.studio.com.gamecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    val textUser = MutableLiveData<String>().apply {
        value = "This is user Fragment"
    }
}