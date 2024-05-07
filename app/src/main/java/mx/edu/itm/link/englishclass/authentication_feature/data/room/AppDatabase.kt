package mx.edu.itm.link.englishclass.authentication_feature.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.edu.itm.link.englishclass.authentication_feature.domain.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
}