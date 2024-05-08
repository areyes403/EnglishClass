package mx.edu.itm.link.englishclass.authentication_feature.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.GetToken
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.InsertLocalUser
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.SignIn
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.UpdateToken
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignIn,
    private val insertUserUseCase:InsertLocalUser,
    private val updateTokenUseCase:UpdateToken,
    private val getTokenUseCase:GetToken
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

    fun updateToken(user:User) = viewModelScope.launch {
        insertUserUseCase.invoke(dataUser = user)
    }
}