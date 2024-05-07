package mx.edu.itm.link.englishclass.user_feature.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.GetLocalUser
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val localUserUseCase:GetLocalUser
):ViewModel() {
    private val _user=MutableLiveData<User>()
    val user:LiveData<User> get() = _user

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        _user.postValue(localUserUseCase.invoke())
    }
}