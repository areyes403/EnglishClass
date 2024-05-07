package mx.edu.itm.link.englishclass.authentication_feature.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.authentication_feature.data.repository.RoomRepositoryImp
import mx.edu.itm.link.englishclass.authentication_feature.data.room.UserDao
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.RoomRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun bindRoomRepository(
        userDao:UserDao
    ):RoomRepository=RoomRepositoryImp(userDao)
}