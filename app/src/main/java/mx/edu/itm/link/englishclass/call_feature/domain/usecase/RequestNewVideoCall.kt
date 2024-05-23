package mx.edu.itm.link.englishclass.call_feature.domain.usecase

import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationDao
import mx.edu.itm.link.englishclass.notification_feature.domain.usecase.SendNotification
import javax.inject.Inject

class RequestNewVideoCall @Inject constructor(

) {
    suspend operator fun invoke(notification:NotificationDao){

    }
}