package mx.edu.itm.link.englishclass.authentication_feature.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.authentication_feature.data.repository.AuthRepositoryImp
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    fun provideAuthRepository(
        authRef:FirebaseAuth,
        firestoreRef:FirebaseFirestore
    ): AuthRepository = AuthRepositoryImp(authRef = authRef, firetoreRef = firestoreRef)
}