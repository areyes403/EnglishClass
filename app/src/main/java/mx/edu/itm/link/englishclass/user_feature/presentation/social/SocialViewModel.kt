package mx.edu.itm.link.englishclass.user_feature.presentation.social

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.user_feature.domain.usecase.GetActiveUsers
import mx.edu.itm.link.englishclass.user_feature.domain.usecase.GetCurrentUser
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor(
    private val getUsersUseCase:GetActiveUsers,
    private val getCurrentUserUseCase: GetCurrentUser
):ViewModel() {
    private val _activeUsers = MutableStateFlow<ResponseStatus<List<User>>>(ResponseStatus.Loading)
    val activeUsers : StateFlow<ResponseStatus<List<User>>> get() = _activeUsers

    lateinit var fbUser:FirebaseUser
        private set

    init {
        initUsers()
    }

    private fun initUsers() = viewModelScope.launch(Dispatchers.IO){
        fbUser= getCurrentUserUseCase.invoke()!!

        getUsersUseCase.invoke().collect{
            _activeUsers.value=it
        }
    }
}