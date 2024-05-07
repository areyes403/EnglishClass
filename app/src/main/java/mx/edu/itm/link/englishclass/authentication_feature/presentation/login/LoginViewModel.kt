package mx.edu.itm.link.englishclass.authentication_feature.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.InsertLocalUser
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.SignIn
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignIn,
    private val insertUser:InsertLocalUser
):ViewModel() {

    private val _login: MutableLiveData<ResponseStatus<User>> by lazy {
        MutableLiveData<ResponseStatus<User>>()
    }
    val login: LiveData<ResponseStatus<User>> get() = _login

    fun login(user:String,pass:String) = viewModelScope.launch (Dispatchers.IO) {
        _login.postValue(ResponseStatus.Loading)
        val response=signInUseCase(email = user, password = pass)
        _login.postValue(response)
    }

    fun insertUser(user:User) = viewModelScope.launch {
        insertUser.invoke(dataUser = user)
    }
}