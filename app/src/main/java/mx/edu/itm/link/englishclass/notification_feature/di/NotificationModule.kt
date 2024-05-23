package mx.edu.itm.link.englishclass.notification_feature.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.notification_feature.data.remote.NotificationService
import mx.edu.itm.link.englishclass.notification_feature.data.repository.NotificationRepositoryImp
import mx.edu.itm.link.englishclass.notification_feature.domain.repository.NotificationRespository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {
    @Provides
    @Singleton
    fun provideService():NotificationService{
        val retrofit=Retrofit
            .Builder()
            .baseUrl("https://fcm.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(NotificationService::class.java)
    }

    @Provides
    @Singleton
    fun provideNotificationRepo(service:NotificationService):NotificationRespository=NotificationRepositoryImp(service)
}