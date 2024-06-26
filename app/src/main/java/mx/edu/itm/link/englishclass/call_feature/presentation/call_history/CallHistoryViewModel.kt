package mx.edu.itm.link.englishclass.call_feature.presentation.call_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.GetLocalUser
import mx.edu.itm.link.englishclass.call_feature.domain.model.RealtimeCall
import mx.edu.itm.link.englishclass.call_feature.domain.usecase.GetCallHistory
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import javax.inject.Inject

@HiltViewModel
class CallHistoryViewModel @Inject constructor(
    private val getCallHistoryUseCase:GetCallHistory,
    private val localUserUseCase: GetLocalUser
):ViewModel(){
    private val _Realtime_callHistory=MutableStateFlow<ResponseStatus<List<RealtimeCall>>>(ResponseStatus.Loading)
    val realtimeCallHistory:StateFlow<ResponseStatus<List<RealtimeCall>>> get() = _Realtime_callHistory

    init {
        getHistory()
    }

    private fun getHistory() = viewModelScope.launch(Dispatchers.IO){
        val user = localUserUseCase.invoke()!!
        getCallHistoryUseCase.invoke(user.id).collect{
            _Realtime_callHistory.value=it
        }
    }
}