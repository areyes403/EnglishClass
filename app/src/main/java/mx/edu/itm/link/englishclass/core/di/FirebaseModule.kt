package mx.edu.itm.link.englishclass.core.di


import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.google.firebase.firestore.firestore
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.core.utils.FirestoreCollecions
import mx.edu.itm.link.englishclass.core.utils.UserCollection
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideAuthRef() = Firebase.auth

    @Provides
    @Singleton
    fun provideFirestoreRef() = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseRef() = Firebase.database

    @UserCollection
    @Provides
    @Singleton
    fun provideUsersCollection()= provideFirestoreRef().collection(FirestoreCollecions.USER)

}



