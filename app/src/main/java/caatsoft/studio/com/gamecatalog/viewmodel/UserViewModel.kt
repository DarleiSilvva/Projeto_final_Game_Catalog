package caatsoft.studio.com.gamecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val userNameLiveData = MutableLiveData<String>()
    val userName  = userNameLiveData as LiveData<String>

    private val urlImageLiveData = MutableLiveData<String>()
    val urlImage  = urlImageLiveData as LiveData<String>

    fun getDataUser() {
        viewModelScope.launch {
            FirebaseDatabase.getInstance().reference.child(PATH_USER)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (data in dataSnapshot.children) {
                            if (data.child(PATH_ID).value.toString() == FirebaseAuth.getInstance().uid.toString()) {
                                userNameLiveData.value = data.child(PATH_USER_NAME).value.toString()
                                urlImageLiveData.value = data.child(PATH_URL_IMAGE).value.toString()
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        throw databaseError.toException()
                    }
                })
        }
    }
    companion object {
        private const val PATH_USER_NAME = "userName"
        private const val PATH_URL_IMAGE = "urlImage"
        private const val PATH_ID = "id"
        private const val PATH_USER = "User"
    }
}