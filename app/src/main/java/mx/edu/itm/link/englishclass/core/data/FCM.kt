package mx.edu.itm.link.englishclass.core.data

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.GetLocalUser
import mx.edu.itm.link.englishclass.authentication_feature.domain.usecase.UpdateToken
import javax.inject.Inject

class FCM: FirebaseMessagingService() {

    @Inject
    lateinit var getUserUseCase:GetLocalUser
    @Inject
    lateinit var updateTokenUseCase:UpdateToken

    private val scope= CoroutineScope(Dispatchers.IO)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        try {
            scope.launch {
                val user=getUserUseCase.invoke()
                user?.let {
                    updateTokenUseCase.invoke(idUser = it.id, myToken = token)
                }
            }
        }catch (_:Exception){

        }finally {
            scope.cancel()
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i("FCM",message.data.toString())
        Log.i("FCM",message.notification?.title.toString())
        Log.i("FCM",message.notification?.body.toString())

    }
}