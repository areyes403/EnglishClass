package mx.edu.itm.link.englishclass.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.profile_feature.domain.usecase.GetCurrentUser
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUser
):ViewModel() {

    var user = MutableStateFlow<FirebaseUser?>(null)
        private set

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        user.value=getCurrentUserUseCase()
    }
}