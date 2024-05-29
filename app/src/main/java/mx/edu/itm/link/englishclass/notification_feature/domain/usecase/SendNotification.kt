package mx.edu.itm.link.englishclass.notification_feature.domain.usecase

import android.util.Log
import mx.edu.itm.link.englishclass.core.domain.model.GeneralId
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.ARGS.CALL_UID
import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationDao
import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationData
import mx.edu.itm.link.englishclass.notification_feature.domain.repository.NotificationRespository
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject

class SendNotification @Inject constructor(
    private val repo:NotificationRespository,
    private val userRepo:UserRepository
) {
    suspend operator fun invoke(receptor:GeneralId,idCall:String,emisor:GeneralId):ResponseStatus<Unit> {
        return when(val tokenResult=userRepo.getRemoteToken(uid = receptor.id)){
            is ResponseStatus.Loading->{ResponseStatus.Loading}
            is ResponseStatus.Error->{ResponseStatus.Error(tokenResult.error)}
            is ResponseStatus.Success->{
                val data= mapOf(CALL_UID to idCall, "emisor" to emisor)
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
                    Log.i("notification","SendNotificationUseCase success")
                    ResponseStatus.Success(Unit)
                }else{
                    Log.i("notification","SendNotificationUseCase success")
                    ResponseStatus.Error((notificationResult as ResponseStatus.Error).error)
                }
            }
        }
    }
}