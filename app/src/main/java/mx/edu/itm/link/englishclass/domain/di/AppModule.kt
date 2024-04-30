package mx.edu.itm.link.englishclass.domain.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.data.repository.AuthRepositoryImp
import mx.edu.itm.link.englishclass.domain.repository.AuthRepository
import mx.edu.itm.link.englishclass.domain.usecase.authusecase.AuthUseCases
import mx.edu.itm.link.englishclass.domain.usecase.authusecase.GetCurrentUser
import mx.edu.itm.link.englishclass.domain.usecase.authusecase.SignIn
import mx.edu.itm.link.englishclass.domain.usecase.authusecase.SignUp

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideAuthRepository(
        authRef:FirebaseAuth
    ):AuthRepository = AuthRepositoryImp(authRef = authRef)

    @Provides
    fun provideAuthUseCases(
        repository:AuthRepository
    )=AuthUseCases(
        signIn = SignIn(repo = repository),
        signUp = SignUp(repo = repository),
        getCurrentUser = GetCurrentUser(repo = repository)
    )
}