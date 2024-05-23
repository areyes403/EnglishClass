package mx.edu.itm.link.englishclass.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetorfitModule {

    @Provides
    fun provideRetrofit() = Retrofit.Builder().baseUrl("https://fcm.googleapis.com/").addConverterFactory(GsonConverterFactory.create()).build()
}