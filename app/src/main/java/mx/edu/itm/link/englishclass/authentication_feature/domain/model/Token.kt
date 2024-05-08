package mx.edu.itm.link.englishclass.authentication_feature.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Token (
    var token:String="",
    @ServerTimestamp
    var timestamp: Date?=null
)