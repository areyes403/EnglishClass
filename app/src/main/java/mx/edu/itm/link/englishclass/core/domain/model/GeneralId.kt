package mx.edu.itm.link.englishclass.core.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class GeneralId(
    var id:String="",
    var name:String=""
):Serializable
