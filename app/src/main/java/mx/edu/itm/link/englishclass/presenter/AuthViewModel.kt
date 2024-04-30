package mx.edu.itm.link.englishclass.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.domain.usecase.authusecase.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: AuthUseCases
):ViewModel() {
    var user = MutableStateFlow<FirebaseUser?>(null)
        private set

    private val _login:MutableLiveData<ResponseStatus<String>> by lazy {
        MutableLiveData<ResponseStatus<String>>()
    }
    val login:LiveData<ResponseStatus<String>> get() = _login

    private val _register:MutableLiveData<ResponseStatus<String>> by lazy {
        MutableLiveData<ResponseStatus<String>>()
    }
    val register:LiveData<ResponseStatus<String>> get() = _login



    init {
        getUser()
    }

    fun login(user:String,pass:String) = viewModelScope.launch (Dispatchers.IO) {
        _login.postValue(ResponseStatus.Loading)
        val response=useCases.signIn(email = user, password = pass)
        _login.postValue(response)
    }

    private fun getUser() = viewModelScope.launch {
        user.value=useCases.getCurrentUser()
    }
}