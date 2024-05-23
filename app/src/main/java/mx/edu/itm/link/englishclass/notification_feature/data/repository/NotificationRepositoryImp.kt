package mx.edu.itm.link.englishclass.notification_feature.data.repository

import android.util.Log
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationDao
import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationService
import mx.edu.itm.link.englishclass.notification_feature.domain.repository.NotificationRespository
import javax.inject.Inject

class NotificationRepositoryImp(
    private val notificationService:NotificationService
) :NotificationRespository{
    override suspend fun sendNotification(notification: NotificationDao): ResponseStatus<Unit> = try {

        val result=notificationService.sendNotification(notificationDao = notification)

        if (result.isSuccessful){
            Log.i("videocall","Notification repo response: success")
            ResponseStatus.Success(Unit)
        }

        val error=result.errorBody()?.string()
        Log.i("videocall","Notification repo response error:${error}")
        ResponseStatus.Error(error)
    }catch (e:Exception){
        Log.i("videocall","Notification repo response error:${e.localizedMessage}")
        ResponseStatus.Error("Error")
    }

}