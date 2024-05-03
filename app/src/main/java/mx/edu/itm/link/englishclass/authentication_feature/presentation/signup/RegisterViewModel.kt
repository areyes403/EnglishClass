package mx.edu.itm.link.englishclass.authentication_feature.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.SignUp
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signUpUseCase:SignUp
):ViewModel() {

    private var _registration = MutableLiveData<ResponseStatus<String>>()
    val registration:LiveData<ResponseStatus<String>> get() = _registration

    fun register(email:String,password:String,userData: User) = viewModelScope.launch(Dispatchers.IO){
        _registration.postValue(ResponseStatus.Loading)
        _registration.postValue(signUpUseCase(email = email, password = password, user = userData))
    }
}