package mx.edu.itm.link.englishclass.notification_feature.domain.usecase

import android.util.Log
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationDao
import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationData
import mx.edu.itm.link.englishclass.notification_feature.domain.repository.NotificationRespository
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject

class SendNotification @Inject constructor(
    private val repo:NotificationRespository,
    private val userRepo:UserRepository
) {
    suspend operator fun invoke(uid:String):ResponseStatus<Unit> {
        return when(val tokenResult=userRepo.getRemoteToken(uid = uid)){
            is ResponseStatus.Loading->{ResponseStatus.Loading}
            is ResponseStatus.Error->{ResponseStatus.Error(tokenResult.error)}
            is ResponseStatus.Success->{
                val data= mapOf("key1" to "my first key")
                val notification=NotificationDao(
                    to = tokenResult.data,
                    data = data,
                    notification = NotificationData(
                        title = "Mi notificacion",
                        body = "Este es un test",
                        content_available = true,
                        priority = "hight",
                        image = ""
                    )
                )
                val notificationResult=repo.sendNotification(notification)
                if (notificationResult is ResponseStatus.Success){
                    ResponseStatus.Success(Unit)
                }else{
                    ResponseStatus.Error((notificationResult as ResponseStatus.Error).error)
                }
            }
        }
    }
}