package mx.edu.itm.link.englishclass.user_feature.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.core.utils.UserCollection
import mx.edu.itm.link.englishclass.user_feature.data.repository.UserRepositoryImp
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun bindUserModule(
        auth: FirebaseAuth,
        firestore:FirebaseFirestore
    ): UserRepository= UserRepositoryImp(authRef = auth, firestore = firestore)
}
