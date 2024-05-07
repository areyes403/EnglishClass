package mx.edu.itm.link.englishclass.authentication_feature.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey var id:String = "",
    @ColumnInfo (name="name") val name:String?,
    @ColumnInfo(name="sur_names") val surNames:String?,
    @ColumnInfo(name="profession")val profession:String?,
    @ColumnInfo(name="photo")var photo:String = ""
)

