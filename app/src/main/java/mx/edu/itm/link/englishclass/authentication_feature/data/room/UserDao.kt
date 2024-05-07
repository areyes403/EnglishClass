package mx.edu.itm.link.englishclass.authentication_feature.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.google.firebase.firestore.auth.User
import mx.edu.itm.link.englishclass.authentication_feature.domain.model.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM userentity")
    suspend fun getAll():List<UserEntity>

    @Query("SELECT * FROM userentity WHERE id = :id")
    suspend fun getUser(id:String):UserEntity?

    @Insert
    suspend fun insert(user:UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)
}