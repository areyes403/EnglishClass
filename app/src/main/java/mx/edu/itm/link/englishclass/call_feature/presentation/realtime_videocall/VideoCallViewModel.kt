package mx.edu.itm.link.englishclass.call_feature.presentation.realtime_videocall

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.database.ServerValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.GetLocalUser
import mx.edu.itm.link.englishclass.call_feature.domain.model.CallState
import mx.edu.itm.link.englishclass.call_feature.domain.model.RealtimeCall
import mx.edu.itm.link.englishclass.call_feature.domain.usecase.ObserveCall
import mx.edu.itm.link.englishclass.call_feature.domain.usecase.RequestCall
import mx.edu.itm.link.englishclass.core.domain.model.GeneralId
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.notification_feature.domain.usecase.SendNotification
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import java.util.*
import javax.inject.Inject
@HiltViewModel
class VideoCallViewModel @Inject constructor(
    private val observeVideoCallUseCase:ObserveCall,
    private val getLocalUserUseCase:GetLocalUser,
    private val requestCallUseCase:RequestCall,
    //private val sendNotificationUseCase:SendNotification
): ViewModel() {

    private val _videoRealtimeCall=MutableStateFlow<ResponseStatus<RealtimeCall>>(ResponseStatus.Loading)
    val videoRealtimeCall:StateFlow<ResponseStatus<RealtimeCall>> get() =_videoRealtimeCall
    lateinit var user:User

    private val _requestCall = MutableLiveData<ResponseStatus<String>>()
    val requestCall:LiveData<ResponseStatus<String>> get() = _requestCall


    var callUID=""
        private set

    init {
        getUser()
    }

    private fun getUser() = viewModelScope.launch {
        user=getLocalUserUseCase.invoke()!!
    }

    fun setCallUID(id:String,e:GeneralId?,r:GeneralId?){
        if (id.isBlank()){

            _requestCall.value=ResponseStatus.Loading

            viewModelScope.launch(Dispatchers.IO){
                val call=RealtimeCall(
                    id = getUniqueUID(),
                    emisor = e,
                    receptor = r,
                    state = CallState.CONNECTING.toString(),
                    created_At = ServerValue.TIMESTAMP
                )
                _requestCall.postValue(requestCallUseCase(data = call))
                //startObserver(uid = "")
                //sendNotification(receptor = r!!.id, id = call.id, emisor = e!!)
            }
        }else{
            startObserver(uid = id)
        }
    }

    fun endCall(){

    }

    /*
    private suspend fun sendNotification(receptor:String,id: String,emisor:GeneralId){
        sendNotificationUseCase(receptor, idCall = id, emisor = emisor)
    }

     */

    private fun startObserver(uid:String) = viewModelScope.launch(Dispatchers.IO){
        observeVideoCallUseCase.invoke(uid).collect{
            _videoRealtimeCall.value=it
        }
    }

    private fun getUniqueUID():String = UUID.randomUUID().toString()
}