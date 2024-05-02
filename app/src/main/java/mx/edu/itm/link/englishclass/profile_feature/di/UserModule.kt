package mx.edu.itm.link.englishclass.profile_feature.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.profile_feature.data.repository.UserRepositoryImp
import mx.edu.itm.link.englishclass.profile_feature.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideUserModule(
        authRef:FirebaseAuth
    ): UserRepository = UserRepositoryImp(authRef = authRef)

}