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
            ResponseStatus.Success(Unit)
        }else{
            val error=result.errorBody()?.string()
            ResponseStatus.Error(error)
        }
    }catch (e:Exception){
        Log.i("videocall","Notification repo response error:${e.localizedMessage}")
        ResponseStatus.Error("Error")
    }

}