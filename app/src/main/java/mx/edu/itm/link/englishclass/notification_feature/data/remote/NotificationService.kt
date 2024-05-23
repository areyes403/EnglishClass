package mx.edu.itm.link.englishclass.notification_feature.data.remote

import mx.edu.itm.link.englishclass.core.utils.SERVER_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationService {
    @Headers("Content-Type:application/json","Authorization:key=${SERVER_KEY}")
    @POST("fcm/send")
    suspend fun sendNotification(@Body notificationDao: NotificationDao):Response<Unit>
}