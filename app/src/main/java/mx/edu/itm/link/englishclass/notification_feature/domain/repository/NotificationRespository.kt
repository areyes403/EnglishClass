package mx.edu.itm.link.englishclass.notification_feature.domain.repository

import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationDao

interface NotificationRespository {
    suspend fun sendNotification(notification:NotificationDao):ResponseStatus<Unit>
}