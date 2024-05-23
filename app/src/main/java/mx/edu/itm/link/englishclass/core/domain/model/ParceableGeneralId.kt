package mx.edu.itm.link.englishclass.core.domain.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class ParceableGeneralId(
    val id:String,
    val name:String
):Serializable
