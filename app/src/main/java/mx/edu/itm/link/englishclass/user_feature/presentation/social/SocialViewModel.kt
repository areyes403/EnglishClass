package mx.edu.itm.link.englishclass.user_feature.presentation.social

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.GetLocalUser
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.user_feature.domain.usecase.GetActiveUsers
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor(
    private val getUsersUseCase:GetActiveUsers,
    private val localUserUseCase:GetLocalUser
):ViewModel() {

    private val _activeUsers = MutableStateFlow<ResponseStatus<List<User>>>(ResponseStatus.Loading)
    val activeUsers : StateFlow<ResponseStatus<List<User>>> get() = _activeUsers


    private val _user=MutableLiveData<User>()
    val user:LiveData<User> get() = _user

    init {
        initUsers()
    }

    private fun initUsers() = viewModelScope.launch(Dispatchers.IO){

        _user.postValue(localUserUseCase.invoke()!!)

        getUsersUseCase.invoke().collect{
            _activeUsers.value=it
        }
    }

}