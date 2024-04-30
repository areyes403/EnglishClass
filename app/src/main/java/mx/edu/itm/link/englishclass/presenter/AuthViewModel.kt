package mx.edu.itm.link.englishclass.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.domain.usecase.authusecase.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCases: AuthUseCases
):ViewModel() {
    var user = MutableStateFlow<FirebaseUser?>(null)
        private set


    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        user.value=useCases.getCurrentUser()
    }
}