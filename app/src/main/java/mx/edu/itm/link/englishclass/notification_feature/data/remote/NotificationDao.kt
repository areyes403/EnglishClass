package mx.edu.itm.link.englishclass.notification_feature.data.remote

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class NotificationDao(
    @SerializedName("to") val to:String,
    @SerializedName("data") val data:Map<String,Any>,
    @SerializedName("notification") var notification:NotificationData?=null
)
