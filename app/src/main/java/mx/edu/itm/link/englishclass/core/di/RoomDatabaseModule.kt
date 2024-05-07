package mx.edu.itm.link.englishclass.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.authentication_feature.data.room.AppDatabase
import mx.edu.itm.link.englishclass.authentication_feature.data.room.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context:Context
    ): AppDatabase = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "myAppDatabase")
        .fallbackToDestructiveMigrationFrom()
        .build()

    @Provides
    fun provideUserDao(database:AppDatabase):UserDao= database.userDao()
}