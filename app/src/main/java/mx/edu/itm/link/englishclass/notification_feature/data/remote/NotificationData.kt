package mx.edu.itm.link.englishclass.notification_feature.data.remote

import com.google.gson.annotations.SerializedName

data class NotificationData(
    @SerializedName("title") val title:String,
    @SerializedName("body") val body:String,
    @SerializedName("content_available") val content_available:Boolean,
    @SerializedName("priority") val priority:String,
    @SerializedName("image") val image:String
)
